package cc.zhanyun.repository.impl;

import cc.zhanyun.model.resources.ResourceTypeVO;
import cc.zhanyun.model.resources.ResourcesTypeOne;
import cc.zhanyun.model.resources.ResourcesTypes;
import cc.zhanyun.repository.ResourcesTypesRepo;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


@Repository
public class ResourcesTypeRepoImpl {
    @Autowired
    private ResourcesTypesRepo typesRepo;
    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 保存库
     *
     * @param type
     */
    public void saveResourceType(ResourcesTypes type) {
        this.typesRepo.save(type);
    }

    /**
     * 查询库
     *
     * @param uid
     * @return
     */
    public ResourcesTypes selResourceTypeOne(String uid) {
        return this.typesRepo.findByUid(uid);
    }

    /**
     * 删除单条库中子文档
     *
     * @param oid
     */
    public Integer delResourceType(String oid, String uid) {
        Integer integer = 0;
        try {
            Query query = new Query(Criteria.where("_id").is(uid));
            Update update = new Update();
            update.pull("typelist", new BasicDBObject("_id", oid));
            this.mongoTemplate.updateFirst(query, update, ResourcesTypes.class);
            integer = 1;
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return integer;
    }

    /**
     * 增加单条库中子文档
     */
    public Integer addResourceTypeOfOne(ResourcesTypeOne resourcesTypeOne, String uid) {
        try {
            //条件
            Query query = new Query(Criteria.where("_id").is(uid));
            //参数
            Update update = new Update();
            update.addToSet("typelist", resourcesTypeOne);
            //增加
            this.mongoTemplate.upsert(query, update, ResourcesTypes.class);
        } catch (Exception e) {
            return Integer.valueOf(0);
        }
        return Integer.valueOf(1);
    }

    /**
     * 修改单条库中子文档
     */
    public Integer updateResourceTypeOfOne(ResourceTypeVO rtvo, String uid) {
        Integer in = 0;
        try {
            //查询条件
            Query query = new Query(Criteria.where("_id").is(uid).and("typelist._id").is(rtvo.getOid()));
            //更新值
            Update update = new Update();
            //修改的键
            update.set("typelist.$.name", rtvo.getNewType());
            this.mongoTemplate.upsert(query, update, ResourcesTypes.class);
            in = 1;
        } catch (Exception e) {
            //
            e.printStackTrace();

        }
        return in;
    }
}


