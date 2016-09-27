package cc.zhanyun.service.impl;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.ProjectOfferModel;
import cc.zhanyun.repository.ProjectOfferModelRepo;
import cc.zhanyun.repository.impl.ProjectOfferModelRepoImpl;
import cc.zhanyun.service.ProjectOfferModelService;
import cc.zhanyun.util.RandomUtil;
import cc.zhanyun.util.TokenUtil;
import cc.zhanyun.util.fileutil.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hyh on 16-9-9.
 */
@Service
public class ProjectOfferModelServiceImpl implements ProjectOfferModelService {

    @Autowired
    private ProjectOfferModelRepoImpl projectOfferModelRepo;
    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 增加报价单模板
     *
     * @param projectOfferModel
     * @return
     */
    @Override
    public Info addProjectOfferModel(ProjectOfferModel projectOfferModel) {

        String uid = tokenUtil.tokenToOid();
        Info info = new Info();
        try {

            projectOfferModel.setUid(uid);
            projectOfferModelRepo.addProjectOfferModel(projectOfferModel);
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }

        return info;
    }

    /**
     * 删除报价单模板
     *
     * @param oid
     * @return
     */
    @Override
    public Info delProjectOfferModel(String oid) {
        Info info = new Info();
        try {
            projectOfferModelRepo.delProjectOfferModel(oid);
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }

    /**
     * 查询报价单模板列表
     *
     * @param num
     * @param size
     * @return
     */
    @Override
    public List<ProjectOfferModel> selProjectOfferModelList(Integer num, Integer size) {
        String uid = tokenUtil.tokenToOid();
        Pageable pageable = null;
        if (size != null) {
            pageable = new PageRequest(num, size);
        }
        List<ProjectOfferModel> ss = projectOfferModelRepo.selProjectOfferModelList(uid, pageable);
        return ss;

    }

    /**
     * 单条查询报价单模板
     *
     * @param oid
     * @return
     */
    @Override
    public ProjectOfferModel selProjectOfferModel(String oid) {

        return projectOfferModelRepo.selProjectOfferModel(oid, tokenUtil.tokenToOid());
    }

    /**
     * 按照状态查询报价单模板
     *
     * @param status
     * @param num
     * @param size
     * @return
     */
    @Override
    public List<ProjectOfferModel> selProjectOfferModelListByStatus(String status, Integer num, Integer size) {
        Pageable pageable = null;
        if (size != null) {
            pageable = new PageRequest(num, size);
        }

        return projectOfferModelRepo.selProjectOfferModelByStatus(status, pageable, tokenUtil.tokenToOid());
    }

    /**
     * 更新报价单模板
     *
     * @param info
     * @return
     */
    @Override
    public Info updateProjectOfferModelStatus(Info info) {
        Info in = new Info();
        try {
            projectOfferModelRepo.updateProjectOfferModelStatus(info);
            in.setStatus("y");
        } catch (Exception e) {
            in.setStatus("n");
        }
        return in;
    }
}
