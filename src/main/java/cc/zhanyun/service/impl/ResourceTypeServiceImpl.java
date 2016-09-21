
package cc.zhanyun.service.impl;


import cc.zhanyun.model.resources.ResourcesTypeOne;
import cc.zhanyun.model.resources.ResourcesTypes;
import cc.zhanyun.repository.impl.ResourcesTypeRepoImpl;
import cc.zhanyun.service.ResourceTypeService;
import cc.zhanyun.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ResourceTypeServiceImpl
        implements ResourceTypeService {

    @Autowired
    private ResourcesTypeRepoImpl typeRepo;

    @Autowired
    private TokenUtil token;


    public void saveTypeOne(ResourcesTypes type) {

        this.typeRepo.saveResourceType(type);

    }


    public List<ResourcesTypeOne> selTypeAll() {

        String uid = this.token.tokenToOid();

        ResourcesTypes rt = this.typeRepo.selResourceTypes(uid);

        List<ResourcesTypeOne> olist = rt.getResourcesoid();

        return olist;

    }


    public ResourcesTypes selTypeOne(String oid) {

        return this.typeRepo.selResourceTypeOne(oid);

    }


    public void delTypeOne(String oid) {

        this.typeRepo.delResourceType(oid);

    }

}


