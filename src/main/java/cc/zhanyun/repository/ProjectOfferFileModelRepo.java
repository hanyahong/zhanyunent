package cc.zhanyun.repository;

import cc.zhanyun.model.ProjectOfferFileModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by hyh on 16-9-12.
 */
public interface ProjectOfferFileModelRepo extends MongoRepository<ProjectOfferFileModel, String> {


    public List<ProjectOfferFileModel> findByUid(String uid, Pageable pageable);

    public List<ProjectOfferFileModel> findByStatus(String status, Pageable pageable);
}
