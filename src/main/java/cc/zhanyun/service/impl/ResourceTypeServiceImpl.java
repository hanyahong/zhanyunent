
package cc.zhanyun.service.impl;


import cc.zhanyun.model.Info;
import cc.zhanyun.model.resources.ResourceTypeVO;
import cc.zhanyun.model.resources.ResourcesTypeOne;
import cc.zhanyun.model.resources.ResourcesTypes;
import cc.zhanyun.model.vo.ResourcesVO;
import cc.zhanyun.repository.impl.ResourcesTypeRepoImpl;
import cc.zhanyun.service.ResourceTypeService;
import cc.zhanyun.util.RandomUtil;
import cc.zhanyun.util.TokenUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ResourceTypeServiceImpl
        implements ResourceTypeService {

    @Autowired
    private ResourcesTypeRepoImpl typeRepo;
    @Autowired
    private TokenUtil token;


    /**
     * 初始化分类
     */
    public void saveTypeOfOneUser() {
        ResourcesTypes resourcesTypes = new ResourcesTypes();
        resourcesTypes.setOid(token.tokenToOid());
        resourcesTypes.setUid(token.tokenToOid());
        List<ResourcesTypeOne> rtList = new ArrayList<ResourcesTypeOne>();
        ResourcesTypeOne one1 = new ResourcesTypeOne();


        one1.setOid(RandomUtil.getRandomFileName());
        one1.setName("视频");
        rtList.add(one1);

        ResourcesTypeOne one2 = new ResourcesTypeOne();
        one2.setName("音频");
        one2.setOid(RandomUtil.getRandomFileName());
        rtList.add(one2);

        ResourcesTypeOne one3 = new ResourcesTypeOne();
        one3.setOid(RandomUtil.getRandomFileName());
        one3.setName("灯光");
        rtList.add(one3);

        //添加
        resourcesTypes.setTypelist(rtList);
        this.typeRepo.saveResourceType(resourcesTypes);
    }

    /**
     * 查询资源分类库
     *
     * @return
     */
    public List<ResourcesTypeOne> selTypeOfOneUser() {
        ResourcesTypes resourcesTypes = this.typeRepo.selResourceTypeOne(token.tokenToOid());

        return resourcesTypes.getTypelist();
    }

    /**
     * 删除单条分类
     */
    public Info delTypeOne(String oid) {
        Info info = new Info();
        try {
            //查询类库
            this.typeRepo.delResourceType(oid, token.tokenToOid());
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }

    /**
     * 增加单条分类
     *
     * @param resourcesTypeOne
     */
    @Override
    public Info addTypeOne(ResourcesTypeOne resourcesTypeOne) {
        Info info = new Info();
        try {
            resourcesTypeOne.setOid(RandomUtil.getRandomFileName());
            //更新类型
            this.typeRepo.addResourceTypeOfOne(resourcesTypeOne, token.tokenToOid());
            info.setStatus("y");
        } catch (Exception e) {
            //
            info.setStatus("n");
        }
        return info;
    }

    @Override
    public Info updateTypeOne(ResourceTypeVO resourcesVO) {
        Info info = new Info();
        try {
            //更新
            this.typeRepo.updateResourceTypeOfOne(resourcesVO, token.tokenToOid());
            info.setStatus("y");
        } catch (Exception e) {
            //
            info.setStatus("n");
        }
        return info;
    }

}


