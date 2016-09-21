package cc.zhanyun.repository;

import cc.zhanyun.model.project.Project;
import cc.zhanyun.model.vo.ProjectVO;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public abstract interface ProjectRepository
        extends MongoRepository<Project, String> {
    @Query(value = "{'_id':{'$ne':null}}", fields = "{'oid':1,'name':1,'client':1}")
    public abstract List<ProjectVO> findByIdNotNull(Pageable pageable);

    @Query(value = "{'status':{'$ne':null}}", fields = "{'oid':1,'name':1,'location.address':1,'offer.status':1,'client':1}")
    public abstract List<ProjectVO> findByStatus(Integer paramInteger, Pageable pageable);
}

