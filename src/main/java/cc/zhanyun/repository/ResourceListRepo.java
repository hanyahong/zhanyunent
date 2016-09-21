package cc.zhanyun.repository;

import cc.zhanyun.model.resources.ResourceList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hyh on 16-9-5.
 */

public interface ResourceListRepo extends MongoRepository<ResourceList, String> {
    /**
     * 以 uid 查询
     *
     * @param uid
     * @return
     */
    public List<ResourceList> findByUidAndClassification(String uid, String type, Pageable pageable);
}
