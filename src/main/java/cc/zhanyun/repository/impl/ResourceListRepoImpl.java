package cc.zhanyun.repository.impl;

import cc.zhanyun.model.resources.ResourceList;
import cc.zhanyun.repository.ResourceListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hyh on 16-9-5.
 */

public class ResourceListRepoImpl {
    //
    @Autowired
    private ResourceListRepo resourceListRepo;

    /**
     * 单条增加
     *
     * @param resourceList
     */
    public void addResourceList(ResourceList resourceList) {
        resourceListRepo.save(resourceList);
    }

    /**
     * 单条删除
     *
     * @param oid
     */
    public void delResourceListByOid(String oid) {
        resourceListRepo.delete(oid);
    }

    /**
     * 查询列表
     *
     * @param uid
     * @return
     */
    public List<ResourceList> selResourceListByUidAndType(String uid, String type, Pageable pageable) {
        List<ResourceList> resourceLists = resourceListRepo.findByUidAndClassification(uid, type, pageable);

        return resourceLists;
    }


}
