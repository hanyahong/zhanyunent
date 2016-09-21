package cc.zhanyun.repository.impl;

import cc.zhanyun.model.offer.Offer;
import cc.zhanyun.model.vo.OfferStatusVO;
import cc.zhanyun.model.vo.OfferVO;
import cc.zhanyun.repository.OfferRepository;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Repository
@Service
public class OfferRepoImpl {
    @Autowired
    private OfferRepository offerRepo;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<OfferVO> selOffer() {
        return this.offerRepo.findByIdNotNull();
    }


    public Offer selOfferById(String oid) {
        return (Offer) this.offerRepo.findOne(oid);
    }


    public void addOffer(Offer offer) {
        this.offerRepo.save(offer);
    }


    public void delOffer(String oid) {
        this.offerRepo.delete(oid);
    }


    public List<OfferVO> selOfferByStatus(Integer status) {
        List<OfferVO> olist = this.offerRepo.findByStatus(status);

        return olist;
    }


    public void updateOfferStatus(OfferStatusVO osvo) {
        BasicDBObject basicDBObject = new BasicDBObject();

        basicDBObject
                .put("$set", new BasicDBObject("status", osvo.getStatus()));


        Update update = new BasicUpdate(basicDBObject);

        this.mongoTemplate.upsert(new Query(
                Criteria.where("_id").is(osvo.getOid())), update, "offer");
    }
}


