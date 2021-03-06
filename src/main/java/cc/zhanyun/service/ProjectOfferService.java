package cc.zhanyun.service;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.ProOffInfo;
import cc.zhanyun.model.ProjectOffer;
import cc.zhanyun.model.vo.OfferVO;
import cc.zhanyun.model.vo.ProjectOfferVO;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public abstract interface ProjectOfferService {
    public abstract ProOffInfo addProjectOfferOne(ProjectOffer paramProjectOffer);

    public abstract Info updateProjectOfferOne(ProjectOffer paramProjectOffer);

    public abstract void delProjectOfferOne(String paramString);

    public abstract ProjectOffer selProjectOfferOne(String paramString);

    public abstract List<ProjectOfferVO> selProjectOfferList(Integer num, Integer size);

    public abstract List<ProjectOfferVO> selProjectOfferOfStatus(
            Integer paramInteger, Integer num, Integer size);

    public abstract void updateProjectOfferStatus(OfferVO paramOfferVO);

    public abstract Info updateProjectImage(MultipartFile paramMultipartFile,
                                            String paramString);

    /**
     * 在线预览
     *
     * @param oid
     * @return
     */
    public abstract String selOfferOnline(String oid);

    /**
     * 在线预览,即看即删
     *
     * @return
     */
    public abstract String selOfferOnlineAndDelete(ProjectOffer projectOffer);

    /**
     * @param multipartFileList
     * @param oid
     * @return
     */
    public abstract List<Info> batchUpdateProjectOfferImage(List<MultipartFile> multipartFileList, String oid);
}