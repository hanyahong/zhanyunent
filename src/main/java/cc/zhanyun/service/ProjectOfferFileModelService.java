package cc.zhanyun.service;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.ProjectOfferDefaultFileModel;
import cc.zhanyun.model.ProjectOfferFileModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by hyh on 16-9-12.
 */
public interface ProjectOfferFileModelService {
    /**
     * 查询单条
     *
     * @param oid
     * @return
     */
    public ProjectOfferFileModel selProjectOfferFileModel(String oid);

    /**
     * 查询多条模板
     *
     * @return
     */
    public List<ProjectOfferFileModel> selProjectOfferFileModelList(Integer num ,Integer size);

    /**
     * 增加模板
     *
     * @param projectOfferFileModel
     * @return
     */
    public Info addProjectOfferFileModel(ProjectOfferFileModel projectOfferFileModel);

    /**
     * 删除模板
     *
     * @param oid
     * @return
     */
    public Info delProjectOfferFileModel(String oid);

    /**
     * 按照状态查询
     *
     * @param status
     * @return
     */
    public List<ProjectOfferFileModel> selProjectOfferFileModelByStatus(String status, Integer num ,Integer size);

    /**
     * 更新状态值
     *
     * @param projectOfferFileModel
     * @return
     */
    public Info updateProjectOfferFileModelStatus(ProjectOfferFileModel projectOfferFileModel);

    /**
     * 选择默认模板
     */
    public Info setDefaultModel(ProjectOfferFileModel projectOfferFileModel);

    /**
     * 查询默认模板
     *
     * @return
     */
    public ProjectOfferDefaultFileModel selDefaultModel();

    /**
     * 查询报价单模板
     *
     * @return
     */
    public List<ProjectOfferFileModel> selProjectOfferFileModelAllService(Integer num ,Integer size);

}
