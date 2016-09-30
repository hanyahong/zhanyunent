package cc.zhanyun.service;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.image.Image;
import cc.zhanyun.model.image.ImageProperty;
import cc.zhanyun.model.vo.ImageProVO;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public abstract interface ImageService {
    public abstract Info saveImageService(Image paramImage);

    public abstract Info saveImageOneService(MultipartFile paramMultipartFile,
                                             String paramString1, String paramString2);

    public abstract Info delImageService(String paramString1,
                                         String paramString2);

    public abstract List<ImageProperty> selImagesByOid(String paramString);

    public abstract List<ImageProVO> selImagesByUid(String paramString);
}
