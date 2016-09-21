package cc.zhanyun.repository;

import cc.zhanyun.model.resources.ResourcesTypes;
import org.springframework.data.mongodb.repository.MongoRepository;

public abstract interface ResourcesTypesRepo
        extends MongoRepository<ResourcesTypes, String> {
    public abstract ResourcesTypes findByUid(String paramString);
}


