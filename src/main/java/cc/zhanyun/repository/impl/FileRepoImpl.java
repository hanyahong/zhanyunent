package cc.zhanyun.repository.impl;

import cc.zhanyun.model.file.FileManager;
import cc.zhanyun.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileRepoImpl {
    @Autowired
    private FileRepository fileRepo;

    public void fileUpload(FileManager file) {
        this.fileRepo.save(file);
    }

    public FileManager fileDownload(String oid) {
        return (FileManager) this.fileRepo.findOne(oid);
    }

    public void delFile(String oid) {
        this.fileRepo.delete(oid);
    }

    public FileManager selFileByOfferoid(String uid, String offeroid) {
        return this.fileRepo.findByUidAndOfferOid(uid, offeroid);
    }
}
