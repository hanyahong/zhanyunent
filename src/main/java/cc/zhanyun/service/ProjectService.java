package cc.zhanyun.service;

import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.project.Project;
import cc.zhanyun.model.vo.ProjectVO;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public abstract interface ProjectService {
    public abstract void addProjectOne(Project paramProject);

    public abstract void delProjectOne(String paramString);

    public abstract Project selProjectOne(String paramString);

    public abstract void updateProjectOne(Project paramProject);

    public abstract List<ProjectVO> selProjectsList(Integer num ,Integer size);
}


