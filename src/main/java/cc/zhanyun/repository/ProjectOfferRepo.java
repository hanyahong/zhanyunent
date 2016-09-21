package cc.zhanyun.repository;

import cc.zhanyun.model.ProjectOffer;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public abstract interface ProjectOfferRepo
        extends MongoRepository<ProjectOffer, String> {
    @Query(fields = "{'oid':1,'name':1,'project.location.address':1,'offer.client.name':1,'offer.status':1,'date':1}")
    public abstract List<ProjectOffer> findByUid(String paramString, Pageable pageable);

    @Query(fields = "{'oid':1,'name':1,'project.location.address':1,'offer.client.name':1,'offer.status':1,'date':1}")
    public abstract List<ProjectOffer> findByofferStatusAndUid(Integer paramInteger, String paramString, Pageable pageable);

    @Query(fields = "{'oid':1}")
    public abstract ProjectOffer findByOthername(String paramString);
}


