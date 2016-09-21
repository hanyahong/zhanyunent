package cc.zhanyun.repository.impl;

import cc.zhanyun.model.ProjectOfferFileModel;
import cc.zhanyun.repository.ProjectOfferFileModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by hyh on 16-9-12.
 */
public class ProjectOfferFileModelRepoImpl {

    @Autowired
    private ProjectOfferFileModelRepo projectOfferFileModelRepo;

    public void addProjectOfferFileModel(ProjectOfferFileModel projectOfferFileModel) {
        projectOfferFileModelRepo.save(projectOfferFileModel);
    }

    public void delProjectOfferFileModel(String oid) {
        projectOfferFileModelRepo.delete(oid);
    }

    public List<ProjectOfferFileModel> selProjectOfferFileModelList(String uid, Pageable pageable) {
        return projectOfferFileModelRepo.findByUid(uid, pageable);
    }

    public List<ProjectOfferFileModel> selProjectOfferFileModelByStatus(String status, Pageable pageable) {
        return projectOfferFileModelRepo.findByStatus(status, pageable);
    }

    public ProjectOfferFileModel selProjectOfferFileModelByOid(String oid) {
        return projectOfferFileModelRepo.findOne(oid);
    }

    public List<ProjectOfferFileModel> selProjectOfferFileModekAll(Pageable pageable) {
        return projectOfferFileModelRepo.findAll();
    }

}
