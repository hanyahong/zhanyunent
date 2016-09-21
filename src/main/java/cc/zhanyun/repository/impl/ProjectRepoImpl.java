package cc.zhanyun.repository.impl;

import cc.zhanyun.model.project.Project;
import cc.zhanyun.model.vo.ProjectVO;
import cc.zhanyun.repository.OfferRepository;
import cc.zhanyun.repository.ProjectRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
public class ProjectRepoImpl {
    @Autowired
    private ProjectRepository projectRepo;

    public void addProject(Project project) {
        this.projectRepo.save(project);
    }


    public List<ProjectVO> selProject(Pageable pageable) {
        return this.projectRepo.findByIdNotNull(pageable);
    }


    public List<ProjectVO> selProjectOfStatus(Integer status, Pageable pageable) {
        return this.projectRepo.findByStatus(status, pageable);
    }


    public Project selProjectOne(String oid) {
        return (Project) this.projectRepo.findOne(oid);
    }


    public void delProjectOne(String oid) {
        this.projectRepo.delete(oid);
    }
}


