package cc.zhanyun.service;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.ProjectOfferModel;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hyh on 16-9-9.
 */
@Service
public interface ProjectOfferModelService {
    //增加
    public Info addProjectOfferModel(ProjectOfferModel projectOfferModel);

    //删除
    public Info delProjectOfferModel(String oid);

    //查询列表
    public List<ProjectOfferModel> selProjectOfferModelList(Integer num, Integer size);

    //单条查询
    public ProjectOfferModel selProjectOfferModel(String oid);

    //状态查询
    public List<ProjectOfferModel> selProjectOfferModelListByStatus(String status, Integer num, Integer size);

    //更新
    public Info updateProjectOfferModelStatus(Info info);
}
