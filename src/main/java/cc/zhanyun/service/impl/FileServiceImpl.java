package cc.zhanyun.service.impl;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.ProjectOffer;
import cc.zhanyun.model.file.FileLib;
import cc.zhanyun.model.file.FileManager;
import cc.zhanyun.model.user.UserAccount;
import cc.zhanyun.repository.impl.FileLibRepositryImpl;
import cc.zhanyun.repository.impl.OfferFileRepoImpl;
import cc.zhanyun.repository.impl.UserRepoImpl;
import cc.zhanyun.service.EmailService;
import cc.zhanyun.service.FileService;
import cc.zhanyun.util.DateUtil;
import cc.zhanyun.util.RandomUtil;
import cc.zhanyun.util.TokenUtil;
import cc.zhanyun.util.constant.Constant;
import cc.zhanyun.util.fileutil.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private OfferFileRepoImpl fileRepoImpl;//报价单Dao
    @Autowired
    private TokenUtil tokenutil;//token工具
    @Autowired
    private EmailService email;//文件解析
    @Autowired
    private ProjectOfferServiceImpl posi;//报价单服务
    @Autowired
    private UserRepoImpl userRepo;//用户Dao
    @Autowired
    private FileLibRepositryImpl fileLibRepositry;

    /**
     * 上传文件
     *
     * @param file
     * @return
     */

    public Info uploadFile(MultipartFile file, String oid) {
        Info info = new Info();//标示函数

        //基本参数设定
        String url = Constant.BASEPATH;
        String uid = this.tokenutil.tokenToOid();
        String folder = Constant.FILELIB;//文件库文件夹名称
        String othername = FileUtil.getOtherName(file);
        String saveFolder = FileUtil.createUserFolder(uid, url, folder);
        //上传文件
        Integer in = FileUtil.uploadFile(file, saveFolder + othername);
        if (in == 1) {
            //上传成功,进行持久化操作
            FileLib fileLib = fileLibRepositry.selFileLib(oid);
            //如果为null,初始化一个
            if (fileLib == null) {
                FileLib fileLib1 = new FileLib();
                fileLib1.setOid(oid);
                fileLib1.setUid(uid);
                fileLibRepositry.addFileLib(fileLib1);
            }
            //如果存在,正常操作,添加单条文件信息
            FileManager fileManager = new FileManager();
            fileManager.setOid(RandomUtil.getRandomFileName());//随机oid
            fileManager.setType(file.getContentType());
            fileManager.setBasepath(url);
            fileManager.setDate(DateUtil.getCurDate());
            fileManager.setName(file.getOriginalFilename());
            fileManager.setPostfix(FileUtil.getPostfix(file));
            fileManager.setUrl(saveFolder + othername);
            fileLibRepositry.addOneOfFileInFileLib(fileManager, oid);
            //返回值
            info.setStatus("y");
        } else if (in == 2) {
            info.setStatus("n");
        } else if (in == 3) {
            info.setStatus("fileEmpty");
        }
        return info;
    }


    /**
     * 批量上传
     *
     * @param files
     */
    @Override
    public List<Info> batchUploadFiles(List<MultipartFile> files, String oid) {
        List<Info> infoList = new ArrayList<Info>();

        for (MultipartFile p : files) {
            Info in = new Info();
            //上传
            Info info = uploadFile(p, oid);
            if (info.getStatus().equals("y")) {
                in.setStatus("y");
            } else {
                in.setStatus("n");
            }
            infoList.add(in);
        }

        return infoList;
    }

    @Override
    public String downloadFileOfFileLib(String libOid, String fileOid) {
        String url = null;
        //查询
        FileLib fileLib = fileLibRepositry.selFileLib(libOid);
        List<FileManager> list = fileLib.getFileManagerList();
        for (FileManager f : list) {
            if (f.getOid().equals(fileOid)) {
                url = Constant.BASEPATH + f.getUrl();
            }
        }
        return url;
    }

    @Override
    public Info delFileOfFileLib(String libOid, String fileOid) {
        Info info = new Info();
        Integer in = fileLibRepositry.delOneOfFileInFileLib(libOid, fileOid);
        if (in == 1) {
            info.setStatus("y");
        } else {
            info.setStatus("n");
        }
        return info;
    }


    /**
     * 下载报价单
     *
     * @param oid
     * @return
     */

    public String downloadFile(String oid) {
        //获取token
        String uid = this.tokenutil.tokenToOid();
        //查询报价单信息
        ProjectOffer po = this.posi.selProjectOfferOne(oid);
        //查询用户
        UserAccount userAccount = this.userRepo.selUserById(uid);//查询用户信息
        String url = this.email.mongoDBToFile(po, userAccount);//生产文件
        return url;
    }


}
