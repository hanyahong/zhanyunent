package cc.zhanyun.repository;

import cc.zhanyun.model.ProjectOffer;
import cc.zhanyun.model.ProjectOfferModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by hyh on 16-9-9.
 */
public interface ProjectOfferModelRepo extends MongoRepository<ProjectOfferModel, String> {


    public List<ProjectOfferModel> findByUid(String uid, Pageable pageable);

    public ProjectOfferModel findByOidAndUid(String oid, String uid);

    public List<ProjectOfferModel> findByStatusAndUid(String status, Pageable pageable, String uid);
}
