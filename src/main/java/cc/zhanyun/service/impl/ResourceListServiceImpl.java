package cc.zhanyun.service.impl;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.resources.ResourceList;
import cc.zhanyun.model.resources.ResourceStatusVO;
import cc.zhanyun.model.resources.ResourceTypeVO;
import cc.zhanyun.model.resources.Resources;
import cc.zhanyun.repository.impl.ResourceListRepoImpl;
import cc.zhanyun.repository.impl.ResourcesRepoImpl;
import cc.zhanyun.service.ResourceListService;
import cc.zhanyun.service.ResourceService;
import cc.zhanyun.util.RandomUtil;
import cc.zhanyun.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyh on 16-9-5.
 */
@Service
public class ResourceListServiceImpl implements ResourceListService {
    //注入资源列表操作层Bean
    @Autowired
    private ResourceListRepoImpl resourceListRepo;
    //注入资源操作层Bean
    @Autowired
    private ResourcesRepoImpl resourcesRepo;
    //注入token工具Bean
    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 按照分类查询资源列表
     *
     * @return
     */
    @Override
    public List<ResourceList> selResourceListService(String type, Integer num, Integer size) {
        Pageable pageable = null;
        if (size != null) {
            pageable = new PageRequest(num, size);
        }
        String uid = tokenUtil.tokenToOid();
        return resourceListRepo.selResourceListByUidAndType(uid, type, pageable);
    }


    /**
     * 删除所有资源列表
     *
     * @return
     */
    @Override
    public Info delResourceListService(String type) {
        //返回函数
        Info info = new Info();
        try {
            //按照分类查询 返回集合
            PageableInfo pageableInfo = new PageableInfo();
            List<ResourceList> rlist = selResourceListService(type, pageableInfo.getNum(), pageableInfo.getSize());
            //遍历删除
            for (ResourceList r : rlist) {
                resourceListRepo.delResourceListByOid(r.getOid());
            }
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }

    /**
     * 以分类 增加列表
     *
     * @param resourceListList
     * @return
     */
    @Override
    public Info addResourceListService(List<ResourceList> resourceListList, String type) {
        String uid = tokenUtil.tokenToOid();
        String oid = RandomUtil.getRandomFileName();
        Info info = new Info();
        try {

            //遍历
            for (ResourceList r : resourceListList) {

                Resources resources = new Resources();

                resources.setUid(uid);
                resources.setOid(oid);
                resources.setName(r.getName());
                resources.setClassification(type);
                resources.setSimplename(r.getSimplename());
                resources.setUnitprice(r.getUnitprice());
                resources.setUnit(r.getUnit());
                //新增资源
                resourcesRepo.addResources(resources);
                //新增独立资源列表
                r.setOid(oid);
                r.setUid(uid);
                resourceListRepo.addResourceList(r);
            }
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }

    /**
     * 增加资源列表单条
     *
     * @param resourceList
     * @return
     */
    @Override
    public Info addResourceListOne(ResourceList resourceList) {
        String oid = RandomUtil.getRandomFileName();
        String uid = tokenUtil.tokenToOid();
        Info info = new Info();
        try {
            //单条增加资源
            Resources resources = new Resources();
            resources.setName(resourceList.getName());
            resources.setUid(uid);
            resources.setOid(oid);
            resources.setSimplename(resourceList.getSimplename());
            resources.setUnitprice(resourceList.getUnitprice());
            resources.setUnit(resourceList.getUnit());
            resources.setClassification(resourceList.getClassification());

            resourcesRepo.addResources(resources);
            //增加资源独立列表(电脑)
            resourceList.setOid(oid);
            resourceList.setUid(uid);
            resourceListRepo.addResourceList(resourceList);
            //返回值
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }

    /**
     * 更新
     *
     * @param resourceList
     * @return
     */
    @Override
    public Info updateResourceListOne(ResourceList resourceList) {
        Info info = new Info();
        try {
            //单条修改资源信息
            Resources resources = new Resources();
            resources.setOid(resourceList.getOid());
            resources.setUid(tokenUtil.tokenToOid());
            resources.setName(resourceList.getName());
            resources.setSimplename(resourceList.getSimplename());
            resources.setUnitprice(resourceList.getUnitprice());
            resources.setUnit(resourceList.getUnit());
            resources.setClassification(resourceList.getClassification());

            resourcesRepo.updateResourceMain(resources);
            //增加资源独立列表(电脑)
            resourceListRepo.addResourceList(resourceList);
            //返回值
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }

    /**
     * 删除资源单条
     *
     * @param oid
     * @return
     */
    @Override
    public Info delResourceListOne(String oid) {
        Info info = new Info();
        try {
            resourceListRepo.delResourceListByOid(oid);
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }


    /***
     * 批量操作
     * @param resourceListList
     * @return
     */
    @Override
    public List<Info> batchOperation(List<ResourceList> resourceListList, String type) {
        List<Info> infoList = new ArrayList<Info>();
        //删除旧版列表
        delResourceListService(type);
        //判断是否有新的变化 新增
        for (ResourceList r : resourceListList) {
            //如果不为空,有变化
            Info info = new Info();
            Resources resources = new Resources();
            resources.setOid(r.getOid());
            resources.setUid(tokenUtil.tokenToOid());
            resources.setUnit(r.getUnit());
            resources.setUnitprice(r.getUnitprice());
            resources.setClassification(type);
            resources.setSimplename(r.getSimplename());
            resources.setName(r.getName());
            if (r.getStatus() == 1) {
                r.setStatus(3);
                //增加资源
                addResourceListOne(r);
            } else if (r.getStatus() == 2) {
                r.setStatus(3);
                //更新资源
                updateResourceListOne(r);
            } else if (r.getStatus() == 3 || r.getStatus() == null) {
                r.setStatus(3);
                //增加资源独立列表
                resourceListRepo.addResourceList(r);
                //设置返回值
                info.setStatus("y");
            }

        }
        return infoList;
    }


    /**
     * 修改资源类型
     *
     * @param resourceTypeVO
     * @return
     */

    @Override
    public Info updateType(ResourceTypeVO resourceTypeVO) {
        Info info = new Info();
        String uid = tokenUtil.tokenToOid();
        //查询分类
        Resources resources = resourcesRepo.selResourcesOne(resourceTypeVO.getOid());
        if (resources != null) {
            resourcesRepo.updateResourcTypes(resourceTypeVO.getNewType(), resourceTypeVO.getOid());
            info.setStatus("y");
        } else {
            info.setStatus("n");
        }
        return info;
    }
}