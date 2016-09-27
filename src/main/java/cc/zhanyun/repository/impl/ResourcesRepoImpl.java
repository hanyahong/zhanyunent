package cc.zhanyun.repository.impl;

import cc.zhanyun.model.resources.Resources;
import cc.zhanyun.model.resources.ResourcesTypes;
import cc.zhanyun.repository.ResourcesReponsitory;
import com.mongodb.BasicDBObject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


@Repository
public class ResourcesRepoImpl {
    @Autowired
    private ResourcesReponsitory resourcesReponsitory;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 增加资源
     *
     * @param resources
     */
    public void addResources(Resources resources) {
        this.resourcesReponsitory.save(resources);
    }

    /**
     * 查询资源
     *
     * @param uid
     * @param pageable
     * @return
     */
    public List<Resources> selResources(String uid, Pageable pageable) {
        List<Resources> rlist = this.resourcesReponsitory.findByUid(uid, pageable);

        return rlist;
    }

    /**
     * 查询所有资源
     *
     * @param pageable
     * @return
     */
    public Page<Resources> selResourcesAll(Pageable pageable) {
        return this.resourcesReponsitory.findAll(pageable);
    }

    /**
     * 查询某些资源
     *
     * @param uid
     * @param pageable
     * @return
     */
    public List<Resources> selResourcesSome(String uid, Pageable pageable) {
        return this.resourcesReponsitory.findByUid(uid, pageable);
    }

    /**
     * 按分类查询资源
     *
     * @param classification
     * @param uid
     * @param pageable
     * @return
     */
    public List<Resources> selResourcesByType(String classification, String uid, Pageable pageable) {
        return this.resourcesReponsitory.findByClassificationAndUid(classification, uid, pageable);
    }

    /**
     * 查询单个资源
     *
     * @param oid
     * @return
     */
    public Resources selResourcesOne(String oid) {
        return (Resources) this.resourcesReponsitory.findOne(oid);
    }

    /**
     * 删除单个资源
     *
     * @param oid
     */
    public void delResourcesOne(String oid) {
        this.resourcesReponsitory.delete(oid);
    }

    /**
     * 修改单个资源
     *
     * @param resources
     */
    public void updateResourcesOne(Resources resources) {
        BasicDBObject basicDBObject = new BasicDBObject();

        basicDBObject
                .put("$set", new BasicDBObject("name", resources
                        .getName())
                        .append("classification", resources
                                .getClassification())
                        .append("simplename", resources.getSimplename())
                        .append("amount", resources.getAmount())
                        .append("unit", resources.getUnit())
                        .append("unitprice", resources.getUnitprice())
                        .append("remark", resources.getRemark()));


        Update update = new BasicUpdate(basicDBObject);

        this.mongoTemplate.upsert(new Query(
                Criteria.where("_id").is(resources.getOid())), update, "resources");
    }

    /**
     * 修改部分字段
     *
     * @param resources
     */
    public void updateResourceMain(Resources resources) {
        BasicDBObject basicDBObject = new BasicDBObject();

        basicDBObject
                .put("$set", new BasicDBObject("name", resources
                        .getName())
                        .append("classification", resources
                                .getClassification())
                        .append("simplename", resources.getSimplename())
                        .append("unit", resources.getUnit())
                        .append("unitprice", resources.getUnitprice())
                );


        Update update = new BasicUpdate(basicDBObject);

        this.mongoTemplate.upsert(new Query(
                Criteria.where("_id").is(resources.getOid())), update, "resources");
    }


    /**
     * 修改分类
     *
     * @param type
     * @param oid
     */
    public void updateResourcTypes(String type, String oid) {
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("$set", new BasicDBObject("classification", type));
        Update update = new BasicUpdate(basicDBObject);
        this.mongoTemplate.upsert(new Query(
                Criteria.where("_id").is(oid)), update, "resources");
    }
}


