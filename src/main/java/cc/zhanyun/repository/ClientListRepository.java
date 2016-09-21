package cc.zhanyun.repository;

import cc.zhanyun.model.client.ClientmanagerList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by hyh on 16-9-9.
 */
public interface ClientListRepository extends MongoRepository<ClientmanagerList,String> {
    public List<ClientmanagerList> findByUid(String uid, Pageable pageable);
}
