package cc.zhanyun.repository;

import cc.zhanyun.model.location.LocationList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by hyh on 16-9-5.
 */
public interface LocationListRepository extends MongoRepository<LocationList, String> {
    /**
     * 以 uid 查询
     *
     * @param uid
     * @return
     */
    public List<LocationList> findByUid(String uid,Pageable pageable);
}
