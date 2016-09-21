package cc.zhanyun.service.impl;


import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.project.Project;

import cc.zhanyun.model.vo.ProjectVO;

import cc.zhanyun.repository.impl.ProjectRepoImpl;

import cc.zhanyun.service.ProjectService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.stereotype.Service;


@Repository
@Service
public class ProjectServiceImpl
        implements ProjectService {
    @Autowired
    ProjectRepoImpl rpi;


    public void addProjectOne(Project project) {
        this.rpi.addProject(project);
    }


    public void delProjectOne(String oid) {
        this.rpi.delProjectOne(oid);
    }


    public Project selProjectOne(String oid) {
        return this.rpi.selProjectOne(oid);
    }


    public void updateProjectOne(Project project) {
        this.rpi.addProject(project);
    }


    public List<ProjectVO> selProjectsList(Integer num, Integer size) {
        Pageable pageable = null;
        if (size != null) {
            pageable = new PageRequest(num, size);
        }
        return this.rpi.selProject(pageable);
    }

}

