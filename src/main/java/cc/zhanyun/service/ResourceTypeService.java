package cc.zhanyun.service;

import cc.zhanyun.model.resources.ResourcesTypeOne;
import cc.zhanyun.model.resources.ResourcesTypes;
import org.springframework.data.domain.Pageable;

import java.util.List;

public abstract interface ResourceTypeService {
    public abstract void saveTypeOne(ResourcesTypes paramResourcesTypes);

    public abstract List<ResourcesTypeOne> selTypeAll();

    public abstract ResourcesTypes selTypeOne(String paramString);

    public abstract void delTypeOne(String paramString);
}


