package cc.zhanyun.service.impl;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.resources.ResourceList;
import cc.zhanyun.model.resources.ResourceStatusVO;
import cc.zhanyun.model.resources.ResourceTypeVO;
import cc.zhanyun.model.resources.Resources;
import cc.zhanyun.repository.impl.ResourceListRepoImpl;
import cc.zhanyun.service.ResourceListService;
import cc.zhanyun.service.ResourceService;
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
    private ResourceService resourceService;
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
        Info info = new Info();
        try {

            //遍历
            for (ResourceList r : resourceListList) {

                Resources resources = new Resources();
                resources.setClassification(r.getClassification());
                resources.setSimplename(r.getSimplename());
                resources.setUnit(r.getUnit());
                resources.setUid(uid);
                resources.setUnitprice(r.getUnitprice());
                //新增资源
                ResourceStatusVO rsvo = resourceService.addResource(resources);
                //新增oid
                String oid = rsvo.getOid();
                r.setOid(oid);
                r.setUid(uid);
                //新增资源列表
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
        Info info = new Info();
        try {
            resourceListRepo.addResourceList(resourceList);
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
            resources.setClassification(r.getClassification());
            resources.setSimplename(r.getSimplename());
            resources.setName(r.getName());
            if (r.getStatus() == 1) {
                //增加资源实体
                ResourceStatusVO in = resourceService.addResource(resources);
                if (in.getStatus().equals("添加成功")) {
                    //增加资源独立列表
                    r.setOid(in.getOid());
                    r.setUid(tokenUtil.tokenToOid());
                    resourceListRepo.addResourceList(r);
                    //状态值返回
                    info.setStatus("y");
                } else {
                    info.setStatus("n");
                }
            } else if (r.getStatus() == 2) {
                //修改资源实体
                Info in = resourceService.updateResourceMainInfo(resources);
                //修改资源独立列表
                resourceListRepo.addResourceList(r);
                //设置返回值
                info.setStatus("y");
                //增加元素
                infoList.add(info);
            } else if (r.getStatus() == null) {
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
        Resources resources = resourceService.selResourceOne(resourceTypeVO.getOid());
        if (resources != null) {
            resourceService.updateResourceType(resourceTypeVO.getNewType(), resourceTypeVO.getOid());
            info.setStatus("y");
        } else {
            info.setStatus("n");
        }
        return info;
    }
}