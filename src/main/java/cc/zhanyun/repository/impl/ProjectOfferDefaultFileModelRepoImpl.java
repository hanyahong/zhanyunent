package cc.zhanyun.repository.impl;

import cc.zhanyun.model.ProjectOfferDefaultFileModel;
import cc.zhanyun.repository.ProjectOfferDefaultFileModelRepo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hyh on 16-9-12.
 */
public class ProjectOfferDefaultFileModelRepoImpl {

    @Autowired
    private ProjectOfferDefaultFileModelRepo repo;

    /**
     * 增加默认模板
     *
     * @param projectOfferDefaultFileModel
     * @return
     */
    public void addProjectOfferDefaultFileModel(
            ProjectOfferDefaultFileModel projectOfferDefaultFileModel) {
        repo.save(projectOfferDefaultFileModel);
    }

    public ProjectOfferDefaultFileModel selProjectOfferDefaultFileModel(String oid) {
        return repo.findOne(oid);
    }


}
