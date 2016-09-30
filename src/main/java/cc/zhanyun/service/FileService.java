package cc.zhanyun.service;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import cc.zhanyun.model.Info;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public abstract interface FileService {
    /**
     * 文件上传
     *
     * @param oid
     * @return
     */
    public abstract Info uploadFile(MultipartFile paramMultipartFile, String oid);

    /**
     * 文件批量上传
     *
     * @param oid
     * @param paramList
     */
    public abstract List<Info> batchUploadFiles(List<MultipartFile> paramList, String oid);

    /**
     * 单个文件下载
     *
     * @param libOid
     * @param fileOid
     * @return
     */
    public abstract String downloadFileOfFileLib(String libOid, String fileOid);

    /**
     * 单个文件删除
     *
     * @param libOid
     * @param fileOid
     * @return
     */
    public abstract Info delFileOfFileLib(String libOid, String fileOid);

    /**
     * 报价单下载
     *
     * @param paramString
     * @return
     */
    public abstract String downloadFile(String paramString);

}


