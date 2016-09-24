package cc.zhanyun.service;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.resources.ResourceTypeVO;
import cc.zhanyun.model.resources.ResourcesTypeOne;
import cc.zhanyun.model.resources.ResourcesTypes;

import java.util.List;

public abstract interface ResourceTypeService {
    //单条保存用户分类库(用户层面,单条增加类型集合)(默认初始化一个)
    public abstract void saveTypeOfOneUser();

    //查询分类
    public abstract List<ResourcesTypeOne> selTypeOfOneUser();

    //删除分类库中的一个
    public abstract Info delTypeOne(String oid);

    //增加分类库中的一个
    public Info addTypeOne(ResourcesTypeOne resourcesTypeOne);

    //修改分类库中的一个
    public Info updateTypeOne(ResourceTypeVO resourceTypeVO);
}


