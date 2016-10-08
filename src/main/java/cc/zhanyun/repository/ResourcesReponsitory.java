package cc.zhanyun.repository;

import cc.zhanyun.model.resources.Resources;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public abstract interface ResourcesReponsitory extends
        MongoRepository<Resources, String> {
    public abstract List<Resources> findByUid(String paramString, Pageable pageable);

    public abstract List<Resources> findByClassificationAndUid(
            String paramString1, String paramString2, Pageable pageable);
}
