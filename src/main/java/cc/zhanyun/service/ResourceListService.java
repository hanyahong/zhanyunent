package cc.zhanyun.service;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.resources.ResourceList;
import cc.zhanyun.model.resources.ResourceTypeVO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hyh on 16-9-5.
 */
@Service
public interface ResourceListService {
    /**
     * 查询全部资源独立列表
     *
     * @return
     */
    public List<ResourceList> selResourceListService(String type, Integer num ,Integer size);

    /**
     * 按照分类全部删除独立列表
     *
     * @param
     * @return
     */
    public Info delResourceListService(String type);

    /**
     * 按分类 插入全部
     *
     * @param resourceListList
     * @return
     */
    public Info addResourceListService(List<ResourceList> resourceListList, String type);

    /**
     * 单条增加资源列表
     *
     * @param resourceList
     * @return
     */
    public Info addResourceListOne(ResourceList resourceList);

    /**
     * 单条修改资源列表
     *
     * @param resourceList
     * @return
     */
    public Info updateResourceListOne(ResourceList resourceList);

    /**
     * 单条删除资源列表
     *
     * @param oid
     * @return
     */
    public Info delResourceListOne(String oid);


    /**
     * 按分类批量操作
     *
     * @param resourceListList
     * @return
     */
    public List<Info> batchOperation(List<ResourceList> resourceListList, String type);

    /**
     * @param resourceTypeVO
     * @return
     */
    public Info updateType(ResourceTypeVO resourceTypeVO);
}
