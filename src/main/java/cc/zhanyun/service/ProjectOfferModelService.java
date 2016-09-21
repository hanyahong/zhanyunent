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

    public Info addProjectOfferModel(ProjectOfferModel projectOfferModel);

    public Info delProjectOfferModel(String oid);

    public List<ProjectOfferModel> selProjectOfferModelList(Integer num ,Integer size);

    public ProjectOfferModel selProjectOfferModel(String oid);

    public List<ProjectOfferModel> selProjectOfferModelListByStatus(String status, Integer num ,Integer size);

    public Info updateProjectOfferModelStatus(Info info);
}
