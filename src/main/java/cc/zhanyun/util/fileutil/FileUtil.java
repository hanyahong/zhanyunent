package cc.zhanyun.util.fileutil;

import cc.zhanyun.util.RandomUtil;

import java.io.BufferedOutputStream;

import java.io.File;

import java.io.FileOutputStream;

import java.io.InputStream;
import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {


    /**
     * 文件上传
     *
     * @param file
     * @param saveUrl
     * @return
     */
    public static Integer uploadFile(MultipartFile file, String saveUrl) {
        //如果文件不为空
        if (!file.isEmpty()) {
            try {
                //转换字节流
                byte[] bytes = file.getBytes();
                //创建文件
                File downFile = new File(saveUrl);
                //输出流
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(downFile));

                stream.write(bytes);
                stream.close();

                return Integer.valueOf(1);
            } catch (Exception e) {
                e.printStackTrace();
                return Integer.valueOf(0);
            }
        }
        return Integer.valueOf(2);
    }


    /**
     * 删除文件夹
     *
     * @param sPath
     * @return
     */
    public boolean DeleteFolder(String sPath) {
        boolean flag = false;
        File file = new File(sPath);

        if (!file.exists()) {
            return flag;
        }

        if (file.isFile()) {
            return deleteFile(sPath);
        }
        return deleteDirectory(sPath);
    }

    /**
     * 删除文件路径
     *
     * @param sPath
     * @return
     */
    public boolean deleteDirectory(String sPath) {
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);

        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            return false;
        }
        boolean flag = true;

        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            } else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            return false;
        }
        if (dirFile.delete()) {
            return true;
        }
        return false;
    }

    /**
     * 删除文件
     *
     * @param sPath
     * @return
     */
    public boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);

        if ((file.isFile()) && (file.exists())) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 格式化时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getFormatDate() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.format(date);
        String dateAndTime = format.format(date);
        return dateAndTime;
    }

    /**
     * 创建用户别名(带后缀)
     *
     * @param file
     * @return
     */
    public static String getOtherName(MultipartFile file) {
        String postfix = getPostfix(file);
        String random = RandomUtil.getRandomFileName();
        String othername = random + "." + postfix;
        return othername;
    }

    /**
     * 获取文件后缀
     *
     * @param file
     * @return
     */
    public static String getPostfix(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String postfix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return postfix;
    }

    /**
     * 创建用户文件夹
     *
     * @param oid
     * @param url
     * @param folder
     * @return
     */
    public static String createUserFolder(String oid, String url, String folder) {
        String uid = oid + File.separator;
        String filesurl = folder + File.separator;
        File file = new File(url + uid + filesurl);
        // 判断文件路径是否存在
        if ((!file.exists()) && (!file.isDirectory())) {
            file.mkdirs();
        }
        return url + uid + filesurl;
    }

    /**
     * 文件类型校验
     *
     * @param file
     * @return
     */
    public static Integer verifyFileType(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if ((suffix.equals("xls")) || (suffix.equals("xlsx"))) {
            return Integer.valueOf(1);
        }
        return Integer.valueOf(0);
    }

}
