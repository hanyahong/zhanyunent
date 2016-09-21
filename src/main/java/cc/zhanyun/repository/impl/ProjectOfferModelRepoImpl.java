package cc.zhanyun.repository.impl;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.ProjectOfferModel;
import cc.zhanyun.repository.ProjectOfferModelRepo;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * Created by hyh on 16-9-9.
 */

public class ProjectOfferModelRepoImpl {


    @Autowired
    private ProjectOfferModelRepo projectOfferModelRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 增加
     *
     * @param projectOfferModel
     */
    public void addProjectOfferModel(ProjectOfferModel projectOfferModel) {
        projectOfferModelRepo.save(projectOfferModel);
    }

    /**
     * 删除
     */
    public void delProjectOfferModel(String oid) {
        projectOfferModelRepo.delete(oid);
    }

    /**
     * 查询单条
     */
    public ProjectOfferModel selProjectOfferModel(String oid) {
        return projectOfferModelRepo.findOne(oid);
    }

    /**
     * 列表查询
     */
    public List<ProjectOfferModel> selProjectOfferModelList(String uid, Pageable pageable) {
        return projectOfferModelRepo.findByUid(uid, pageable);
    }

    /**
     * 修改模板状态
     */
    public void updateProjectOfferModelStatus(Info info) {
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("$set", new BasicDBObject("status", info.getStatus()));
        Update update = new BasicUpdate(basicDBObject);
        this.mongoTemplate.upsert(new Query(Criteria.where("_id")
                .is(info.getOid())), update, "projectOfferModel");
    }

    /**
     * 以 模板状态查询
     */
    public List<ProjectOfferModel> selProjectOfferModelByStatus(String status, Pageable pageable) {
        return projectOfferModelRepo.findByStatus(status, pageable);
    }
}
