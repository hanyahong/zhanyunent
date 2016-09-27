package cc.zhanyun.repository;

import cc.zhanyun.model.file.FileLib;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hyh on 16-9-27.
 */
@Repository
public interface FileLibRepositry extends MongoRepository<FileLib, String> {
    public List<FileLib> findByUid(String uid);
}
