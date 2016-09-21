package cc.zhanyun.service;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

public abstract interface FileService {
    public abstract String uploadFile(MultipartFile paramMultipartFile);

    public abstract FileSystemResource downloadFile(String paramString);

    public abstract void uploadFiles(List<MultipartFile> paramList);


}


