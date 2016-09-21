package cc.zhanyun.service.impl;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.image.Image;
import cc.zhanyun.model.resources.ResourceList;
import cc.zhanyun.model.resources.ResourceStatusVO;
import cc.zhanyun.model.resources.Resources;
import cc.zhanyun.repository.impl.ResourcesRepoImpl;
import cc.zhanyun.service.ImageService;
import cc.zhanyun.service.ResourceService;
import cc.zhanyun.util.RandomUtil;
import cc.zhanyun.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Service
public class ResourcesServiceImpl
        implements ResourceService {
    @Autowired
    private ResourcesRepoImpl resourceRepo;
    @Autowired
    private TokenUtil token;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ResourceListServiceImpl resourceListService;

    /**
     * 增加单个资源
     *
     * @param resources
     * @return
     */
    public ResourceStatusVO addResource(Resources resources) {
        ResourceStatusVO rsvo = new ResourceStatusVO();
        String uid = this.token.tokenToOid();
        String oid = RandomUtil.getRandomFileName();
        String images = RandomUtil.getRandomFileName();
        try {
            //增加资源
            resources.setOid(oid);
            resources.setUid(uid);
            resources.setImages(images);
            this.resourceRepo.addResources(resources);
            //增加图片库
            Image image = new Image();
            image.setOid(images);
            image.setUid(this.token.tokenToOid());
            this.imageService.saveImageService(image);
            //增加资源列表
            ResourceList resourceList = new ResourceList();
            resourceList.setUid(uid);
            resourceList.setOid(oid);
            resourceList.setSimplename(resources.getSimplename());
            resourceList.setName(resources.getName());
            resourceList.setUnit(resources.getUnit());
            resourceList.setClassification(resources.getClassification());
            resourceList.setUnitprice(resources.getUnitprice());

            this.resourceListService.addResourceListOne(resourceList);
            //添加返回值
            rsvo.setOid(oid);
            rsvo.setStatus("添加成功");
            rsvo.setImages(images);
        } catch (Exception e) {
            rsvo.setStatus("添加失败");
        }

        return rsvo;
    }

    /**
     * 更新单个资源
     *
     * @param resources
     * @return
     */
    public Info updateResource(Resources resources) {
        Info info = new Info();
        try {
            //更新资源
            this.resourceRepo.addResources(resources);
            //更新资源列表
            ResourceList resourceList = new ResourceList();
            resourceList.setUid(resources.getUid());
            resourceList.setOid(resources.getOid());
            resourceList.setSimplename(resources.getSimplename());
            resourceList.setUnit(resources.getUnit());
            resourceList.setClassification(resources.getClassification());
            resourceList.setUnitprice(resources.getUnitprice());
            resourceList.setName(resources.getName());
            this.resourceListService.addResourceListOne(resourceList);
            //返回值
            info.setStatus("添加成功");
        } catch (Exception e) {
            info.setStatus("添加失败");
        }

        return info;
    }

    /**
     * 查询资源列表
     *
     * @return
     */
    public List<Resources> selResourceList(Integer num, Integer size) {
        Pageable pageable = null;
        if (size != null) {
            pageable = new PageRequest(num, size);
        }
        return this.resourceRepo.selResources(this.token.tokenToOid(), pageable);
    }

    /**
     * 删除单个资源
     *
     * @param oid
     * @return
     */
    public Info delResourceOne(String oid) {
        Info info = new Info();
        try {
            //删除资源
            this.resourceRepo.delResourcesOne(oid);
            //删除资源列表
            this.resourceListService.delResourceListOne(oid);
            //返回值
            info.setStatus("删除成功");
        } catch (Exception e) {
            info.setStatus("删除失败");
        }

        return info;
    }

    /**
     * 查询单个资源
     *
     * @param oid
     * @return
     */
    public Resources selResourceOne(String oid) {
        return this.resourceRepo.selResourcesOne(oid);
    }

    /**
     * 增加资源图片
     *
     * @param file
     * @param oid
     * @return
     */
    public Info addResourceImage(MultipartFile file, String oid) {
        Info info = new Info();
        try {
            Resources resources = selResourceOne(oid);
            String imageOid = resources.getImages();
            String imagelocation = "资源图片";
            this.imageService.saveImageOneService(file, imageOid, imagelocation);
            info.setStatus("成功");
        } catch (Exception e) {
            info.setStatus("失败");
        }

        return info;
    }

    /**
     * 查询资源类型
     *
     * @param type
     * @return
     */
    public List<Resources> selResourceOneByType(String type, Integer num, Integer size) {
        Pageable pageable = null;
        if (size != null) {
            pageable = new PageRequest(num, size);
        }
        return this.resourceRepo.selResourcesByType(type, this.token.tokenToOid(), pageable);
    }

    /**
     * 批量操作
     *
     * @param rlist
     * @return 0 ,1 ,2
     */
    @Override
    public List<Info> batchOperationResource(List<Resources> rlist) {
        List<Info> inlist = new ArrayList<Info>();
        //遍历
        for (Resources r : rlist) {
            Info info = new Info();
            if (r.getStatus() == 0) {
                Info in = delResourceOne(r.getOid());
                if (in.getStatus().equals("删除成功")) {
                    info.setOid(r.getOid());
                    info.setStatus("y");
                } else {
                    info.setOid(r.getOid());
                    info.setStatus("n");
                }
            } else if (r.getStatus() == 1) {
                ResourceStatusVO rvo = addResource(r);
                if (rvo.getStatus().equals("添加成功")) {
                    info.setStatus("y");
                    info.setOid(rvo.getOid());
                } else {
                    info.setStatus("n");
                    info.setOid(rvo.getOid());
                }
            } else if (r.getStatus() == 2) {
                Info in = updateResource(r);
                if (in.getStatus().equals("添加成功")) {
                    info.setOid(r.getOid());
                    info.setStatus("y");
                } else {
                    info.setOid(r.getOid());
                    info.setStatus("n");
                }
            }
            inlist.add(info);
        }
        return inlist;
    }

    /**
     * 更新资源部分信息
     *
     * @param resources
     * @return
     */

    public Info updateResourceMainInfo(Resources resources) {
        Info info = new Info();
        try {
            resourceRepo.updateResourceMain(resources);
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }

    @Override
    public Info updateResourceType(String type, String oid) {
        Info info = new Info();
        try {
            resourceRepo.updateResourcTypes(type, oid);
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }
}


