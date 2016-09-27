package cc.zhanyun.repository;

import cc.zhanyun.model.file.FileManager;
import org.springframework.data.mongodb.repository.MongoRepository;

public abstract interface OfferFileRepository extends
        MongoRepository<FileManager, String> {
    public abstract FileManager findByUidAndOfferOid(String paramString1,
                                                     String paramString2);
}
