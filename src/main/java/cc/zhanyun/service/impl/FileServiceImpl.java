package cc.zhanyun.service.impl;

import cc.zhanyun.model.ProjectOffer;
import cc.zhanyun.model.file.FileManager;
import cc.zhanyun.model.user.UserAccount;
import cc.zhanyun.repository.impl.FileRepoImpl;
import cc.zhanyun.repository.impl.UserRepoImpl;
import cc.zhanyun.service.EmailService;
import cc.zhanyun.service.FileService;
import cc.zhanyun.service.ProjectOfferFileModelService;
import cc.zhanyun.util.Constant;
import cc.zhanyun.util.TokenUtil;
import cc.zhanyun.util.fileutil.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepoImpl fileRepoImpl;
    @Autowired
    private TokenUtil tokenutil;
    @Autowired
    private EmailService email;
    @Autowired
    private ProjectOfferServiceImpl posi;
    @Autowired
    private UserRepoImpl userRepo;
    @Autowired
    private ProjectOfferFileModelService projectOfferFileModelService;

    @Autowired
    private HttpServletResponse response;

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public String uploadFile(MultipartFile file) {
        if (FileUtil.verifyFileType(file).intValue() == 1) {
            //文件存储基本路径
            String url = Constant.BASEPATH;
            String oid = this.tokenutil.tokenToOid();
            String folder = "fileModel";
            String othername = FileUtil.getOtherName(file);
            String saveFile = FileUtil.createUserFiles(oid, url, folder);
            //上传文件
            Integer status = FileUtil.uploadFile(file, saveFile);
            //数据库持久化
            // 如果文件上传成功
            if (status.intValue() == 1) {
                //存储对象
                FileManager fileManager = new FileManager();
                fileManager.setName(file.getOriginalFilename());
                System.out.println(file.getOriginalFilename());
                fileManager.setOthername(othername);
                fileManager.setUid(oid);
                fileManager.setUrl(oid + File.separator + folder + File.separator);
                fileManager.setBasepath(url);
                fileManager.setType(file.getContentType());


                //数据持久化
                this.fileRepoImpl.fileUpload(fileManager);
                //返回值
                return "上传成功";
            }
            if (status.intValue() == 0) {
                return "上传失败";
            }
            return "上传失败，文件是空的";
        }
        return "非法格式，请重新选择正确格式！！";
    }

    /**
     * 下载文件
     *
     * @param oid
     * @return
     */
    public FileSystemResource downloadFile(String oid) {
        //获取token
        String uid = this.tokenutil.tokenToOid();
        //查询报价单信息
        ProjectOffer po = this.posi.selProjectOfferOne(oid);
        //查询用户
        UserAccount userAccount = this.userRepo.selUserById(uid);
        String url = this.email.mongoDBToFile(po, userAccount);
        FileSystemResource file = new FileSystemResource(url);

        return file;
    }

    public void uploadFiles(List<MultipartFile> flist) {
    }

}
