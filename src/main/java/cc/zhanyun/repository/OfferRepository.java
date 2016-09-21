package cc.zhanyun.repository;

import cc.zhanyun.model.offer.Offer;
import cc.zhanyun.model.vo.OfferVO;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public abstract interface OfferRepository
        extends MongoRepository<Offer, String> {
    @Query(value = "{'_id':{'$ne':null}}", fields = "{'oid':1,'name':1,'address':1,'status':1}")
    public abstract List<OfferVO> findByIdNotNull();

    @Query(fields = "{'oid':1,'name':1,'address':1,'status':1}")
    public abstract List<OfferVO> findByStatus(Integer paramInteger);
}


