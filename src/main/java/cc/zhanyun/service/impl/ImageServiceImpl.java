package cc.zhanyun.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cc.zhanyun.util.constant.Constant;
import cc.zhanyun.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.image.Image;
import cc.zhanyun.model.image.ImageProperty;
import cc.zhanyun.model.vo.ImageProVO;
import cc.zhanyun.repository.impl.ImageRepoImpl;
import cc.zhanyun.service.ImageService;
import cc.zhanyun.util.RandomUtil;
import cc.zhanyun.util.TokenUtil;
import cc.zhanyun.util.fileutil.FileUtil;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepoImpl imageRepo;
    @Autowired
    private TokenUtil tokenutil;

    public Info saveImageService(Image image) {
        Info info = new Info();
        try {
            this.imageRepo.addImage(image);
            info.setStatus("成功");
        } catch (Exception e) {
            info.setStatus("失败");
        }

        return info;
    }

    /**
     * 保存图片
     *
     * @param file
     * @param oid
     * @param imagelocation
     * @return
     */
    public Info saveImageOneService(MultipartFile file, String oid,
                                    String imagelocation) {
        System.out.println(file.getOriginalFilename());
        //标志
        Info info = new Info();
        //基础路径
        String url = Constant.BASEPATH;
        //文件服务器网址
        String fileUrl = Constant.IMAGESURL;
        //获取token
        String uid = this.tokenutil.tokenToOid();
        //定义图片文件夹名称
        String folder = "images";
        //生成文件名
        String othername = FileUtil.getOtherName(file);
        //缩列图文件名
        String othername2 = "s" + othername;

        //生成上传文件位置
        String saveUrl = FileUtil.createUserFolder(uid, url, folder) + othername;
        String saveUrl2 = FileUtil.createUserFolder(uid, url, folder) + othername2;
        //上传文件
        Integer status = FileUtil.uploadFile(file, saveUrl);
        if (status.intValue() == 1) {

            //图片信息设置
            ImageProperty pro = new ImageProperty();
            pro.setOid(RandomUtil.getRandomFileName());
            pro.setDate(new Date().toString());
            pro.setBasePath(url);
            pro.setName(othername);
            pro.setType(file.getContentType());
            pro.setUid(uid);
            pro.setSize(Long.valueOf(file.getSize()));
            pro.setUrl(fileUrl + uid + "/" + folder + "/" + othername);
            //生产缩略图

            Boolean index = ImageUtil.compress(saveUrl, saveUrl2, file.getContentType());
            if (index == true) {
                pro.setSurl(fileUrl + uid + "/" + folder + "/" + othername2);
            }
            //持久化
            Integer in = this.imageRepo.addImageOne(pro, oid);


            if (in.intValue() == 1) {
                info.setStatus("上传成功");
            } else {
                info.setStatus("上传失败");
            }
        } else if (status.intValue() == 0) {
            info.setStatus("上传失败，服务器错误");
        } else if (status.intValue() == 2) {
            info.setStatus("上传失败，文件不能为空");
        }
        return info;
    }

    public Info delImageService(String oid, String ioid) {
        Info info = new Info();
        try {
            this.imageRepo.delImage(oid, ioid);
            info.setStatus("删除成功");
        } catch (Exception e) {
            info.setStatus("删除失败");
        }
        return info;
    }

    public List<ImageProVO> selImagesByOid(String oid) {
        //置换集合
        List<ImageProVO> pvolist = new ArrayList<ImageProVO>();
        Image image = this.imageRepo.selImage(oid);
        try {
            List<ImageProperty> plist = image.getProperty();

            for (ImageProperty p : plist) {
                ImageProVO pvo = new ImageProVO();
                pvo.setUrl(p.getUrl());
                pvo.setOid(p.getOid());
                pvolist.add(pvo);
            }
        } catch (Exception localException) {
        }

        return pvolist;
    }

    public List<ImageProVO> selImagesByUid(String uid) {
        return null;
    }

}
