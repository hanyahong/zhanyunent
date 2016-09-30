package cc.zhanyun.repository.impl;

import cc.zhanyun.model.ProjectOffer;
import cc.zhanyun.model.location.Images;
import cc.zhanyun.model.vo.OfferVO;
import cc.zhanyun.repository.ProjectOfferRepo;
import com.mongodb.BasicDBObject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


public class ProjectOfferRepoImpl {
    @Autowired
    ProjectOfferRepo por;
    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 单条增加
     *
     * @param projectOffer
     */
    public void saveProOfferOne(ProjectOffer projectOffer) {
        this.por.save(projectOffer);
    }

    /**
     * 以 othername 单条查询
     *
     * @param othername
     * @return
     */
    public String selOidByOthername(String othername) {
        ProjectOffer po = this.por.findByOthername(othername);
        return po.getOid();
    }

    /**
     * 单条删除
     *
     * @param oid
     */
    public void delProOfferOne(String oid) {
        this.por.delete(oid);
    }

    /**
     * 单条查询
     *
     * @param oid
     * @return
     */
    public ProjectOffer selProOfferOne(String oid) {
        return (ProjectOffer) this.por.findOne(oid);
    }

    /**
     * 查询列表
     *
     * @param uid
     * @return
     */
    public List<ProjectOffer> selProOfferList(String uid, Pageable pageable) {
        Query query = new Query();//查询条件
        query.addCriteria(Criteria.where("uid").is(uid));
        Sort sort = new Sort(Sort.Direction.ASC, "date");//排序条件
        query.with(sort);
        query.with(pageable);

        List<ProjectOffer> polist = mongoTemplate.find(query, ProjectOffer.class);

        return polist;
    }

    /**
     * 按照状态查询
     *
     * @param status
     * @param uid
     * @return
     */
    public List<ProjectOffer> selProOfferOfStatusList(Integer status, String uid, Pageable pageable) {
        Query query = new Query();//查询条件
        query.addCriteria(Criteria.where("uid").is(uid).and("offer.status").is(status));
        Sort sort = new Sort(Sort.Direction.DESC, "date");//排序条件
        query.with(sort);
        query.with(pageable);
        List<ProjectOffer> plist = mongoTemplate.find(query, ProjectOffer.class);

        return plist;
    }


    public void updateProjectOfferStatus(OfferVO ov) {
        BasicDBObject basicDBObject = new BasicDBObject();

        basicDBObject.put("$set", new BasicDBObject("offer.status", ov
                .getStatus()));


        Update update = new BasicUpdate(basicDBObject);

        this.mongoTemplate.upsert(new Query(Criteria.where("_id").is(ov.getOid())), update, ProjectOffer.class);
    }


    public Integer addProjectImage(Images images, String oid) {
        try {
            Query query = Query.query(Criteria.where("_id").is(oid));
            Update update = new Update();
            update.addToSet("project.images", images);
            this.mongoTemplate.upsert(query, update, ProjectOffer.class);
        } catch (Exception e) {
            return Integer.valueOf(0);
        }
        return Integer.valueOf(1);
    }


    public Integer removeProjectImage(String oid, String imageoid) {
        try {
            Query query = Query.query(Criteria.where("_id").is(oid)
                    .and("project.images._id").is(imageoid));
            Update update = new Update();
            update.unset("projectoffer.$");
            this.mongoTemplate.updateFirst(query, update, ProjectOffer.class);
        } catch (Exception e) {
            return Integer.valueOf(0);
        }
        return Integer.valueOf(1);
    }
}


