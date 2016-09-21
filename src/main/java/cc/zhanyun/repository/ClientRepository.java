package cc.zhanyun.repository;

import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.client.Clientmanager;
import cc.zhanyun.model.vo.ClientVO;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface ClientRepository
        extends MongoRepository<Clientmanager, String> {
    @Query(fields = "{'oid':1,'name':1,'tel':1,'company':1}")
    public abstract List<ClientVO> findByUid(String uid,Pageable pageable);

    public abstract Clientmanager findByOid(String paramString);

    public abstract Clientmanager findByNameAndUid(String paramString,String uid);
}
