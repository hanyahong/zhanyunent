package cc.zhanyun.service.impl;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.ProjectOfferDefaultFileModel;
import cc.zhanyun.model.ProjectOfferFileModel;
import cc.zhanyun.model.image.Image;
import cc.zhanyun.model.image.ImageProperty;
import cc.zhanyun.repository.impl.ProjectOfferDefaultFileModelRepoImpl;
import cc.zhanyun.repository.impl.ProjectOfferFileModelRepoImpl;
import cc.zhanyun.service.ProjectOfferFileModelService;
import cc.zhanyun.util.RandomUtil;
import cc.zhanyun.util.TokenUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hyh on 16-9-12.
 */
@Service
public class ProjectOfferFileModelServiceImpl implements ProjectOfferFileModelService {
    @Autowired
    private ProjectOfferFileModelRepoImpl repo;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private ProjectOfferDefaultFileModelRepoImpl projectOfferDefaultFileModelRepo;

    @Override
    public ProjectOfferFileModel selProjectOfferFileModel(String oid) {
        return repo.selProjectOfferFileModelByOid(oid);
    }

    @Override
    public List<ProjectOfferFileModel> selProjectOfferFileModelList(Integer num, Integer size) {

        Pageable pageable = null;
        if (size != null) {
            pageable = new PageRequest(num, size);
        }

        return repo.selProjectOfferFileModelList(tokenUtil.tokenToOid(), pageable);
    }

    @Override
    public Info addProjectOfferFileModel(ProjectOfferFileModel projectOfferFileModel) {
        String oid = RandomUtil.getRandomFileName();
        Info info = new Info();
        projectOfferFileModel.setOid(oid);
        try {
            repo.addProjectOfferFileModel(projectOfferFileModel);
            info.setStatus("y");
            //添加模板库预览图
            //末库模板缩略图
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }

    @Override
    public Info delProjectOfferFileModel(String oid) {
        Info info = new Info();
        try {
            repo.delProjectOfferFileModel(oid);
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }

    /**
     * 状态值:1  2  3
     *
     * @param status
     * @return
     */
    @Override
    public List<ProjectOfferFileModel> selProjectOfferFileModelByStatus(String status, Integer num, Integer size) {
        Pageable pageable = null;
        if (size != null) {
            pageable = new PageRequest(num, size);
        }

        return repo.selProjectOfferFileModelByStatus(status, pageable);
    }

    @Override
    public Info updateProjectOfferFileModelStatus(ProjectOfferFileModel projectOfferFileModel) {
        return null;
    }

    @Override
    public Info setDefaultModel(ProjectOfferFileModel projectOfferFileModel) {
        Info info = new Info();
        //查询模板库
        try {
            ProjectOfferFileModel pro = selProjectOfferFileModel(projectOfferFileModel.getOid());
            ProjectOfferDefaultFileModel prode = new ProjectOfferDefaultFileModel();
            prode.setOid(projectOfferFileModel.getOid());
            prode.setName(projectOfferFileModel.getName());
            prode.setUid(tokenUtil.tokenToOid());
            prode.setUrl(pro.getUrl());

            projectOfferDefaultFileModelRepo.addProjectOfferDefaultFileModel(prode);
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }

    @Override
    public ProjectOfferDefaultFileModel selDefaultModel() {

        return projectOfferDefaultFileModelRepo.selProjectOfferDefaultFileModel(tokenUtil.tokenToOid());
    }

    @Override
    public List<ProjectOfferFileModel> selProjectOfferFileModelAllService(Integer num, Integer size) {
        Pageable pageable = null;
        if (size != null) {
            pageable = new PageRequest(num, size);
        }

        return repo.selProjectOfferFileModekAll(pageable);
    }
}
