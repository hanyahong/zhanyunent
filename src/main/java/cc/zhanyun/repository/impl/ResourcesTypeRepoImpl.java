package cc.zhanyun.repository.impl;

import cc.zhanyun.model.resources.ResourcesTypes;
import cc.zhanyun.repository.ResourcesTypesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ResourcesTypeRepoImpl {
    @Autowired
    private ResourcesTypesRepo typesRepo;

    public void saveResourceType(ResourcesTypes type) {
        this.typesRepo.save(type);
    }


    public ResourcesTypes selResourceTypes(String uid) {
        return this.typesRepo.findByUid(uid);
    }


    public ResourcesTypes selResourceTypeOne(String oid) {
        return (ResourcesTypes) this.typesRepo.findOne(oid);
    }


    public void delResourceType(String oid) {
        this.typesRepo.delete(oid);
    }
}


