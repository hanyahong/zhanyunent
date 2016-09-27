package cc.zhanyun.repository.impl;

import cc.zhanyun.model.file.FileManager;
import cc.zhanyun.repository.OfferFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OfferFileRepoImpl {
    @Autowired
    private OfferFileRepository fileRepo;

    /**
     * 增加报价单文件信息
     *
     * @param file
     */
    public void addFileInfoOne(FileManager file) {
        this.fileRepo.save(file);
    }

    /**
     * 查询报价单文件信息
     *
     * @param oid
     * @return
     */
    public FileManager selFileInfoOne(String oid) {
        return (FileManager) this.fileRepo.findOne(oid);
    }

    /**
     * 删除报价单文件信息
     *
     * @param oid
     */
    public void delFileInfoOne(String oid) {
        this.fileRepo.delete(oid);
    }

    /**
     * 查询报价单 按照报价单ID查询
     *
     * @param uid
     * @param offeroid
     * @return
     */
    public FileManager selFileByOfferoid(String uid, String offeroid) {
        return this.fileRepo.findByUidAndOfferOid(uid, offeroid);
    }
}
