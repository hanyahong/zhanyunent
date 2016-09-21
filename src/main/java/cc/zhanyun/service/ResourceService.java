package cc.zhanyun.service;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.resources.ResourceStatusVO;
import cc.zhanyun.model.resources.Resources;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public abstract interface ResourceService {
    public abstract ResourceStatusVO addResource(Resources paramResources);

    public abstract Info updateResource(Resources paramResources);

    public abstract List<Resources> selResourceList(Integer num ,Integer size);

    public abstract Info delResourceOne(String paramString);

    public abstract Resources selResourceOne(String paramString);

    public abstract Info addResourceImage(MultipartFile paramMultipartFile, String paramString);

    public abstract List<Resources> selResourceOneByType(String paramString, Integer num ,Integer size);

    public abstract List<Info> batchOperationResource(List<Resources> rlist);

    public abstract Info updateResourceMainInfo(Resources resources);

    public abstract Info updateResourceType(String type, String oid);
}


