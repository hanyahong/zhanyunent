package cc.zhanyun.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.user.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cc.zhanyun.util.Constant;
import cc.zhanyun.model.Info;
import cc.zhanyun.model.ProjectOffer;
import cc.zhanyun.model.client.Clientmanager;
import cc.zhanyun.model.file.FileManager;
import cc.zhanyun.model.html.Html;
import cc.zhanyun.model.image.Image;
import cc.zhanyun.model.offer.Offer;
import cc.zhanyun.model.offer.Resourcetypes;
import cc.zhanyun.model.offer.Selectedresources;
import cc.zhanyun.model.user.UserAccount;
import cc.zhanyun.model.vo.OfferVO;
import cc.zhanyun.model.vo.ProjectOfferVO;
import cc.zhanyun.repository.impl.ClientRepoImpl;
import cc.zhanyun.repository.impl.FileRepoImpl;
import cc.zhanyun.repository.impl.HtmlRespImpl;
import cc.zhanyun.repository.impl.OfferRepoImpl;
import cc.zhanyun.repository.impl.ProjectOfferRepoImpl;
import cc.zhanyun.repository.impl.ProjectRepoImpl;
import cc.zhanyun.service.EmailService;
import cc.zhanyun.service.ImageService;
import cc.zhanyun.service.ProjectOfferService;
import cc.zhanyun.service.UserService;
import cc.zhanyun.util.DateUtil;
import cc.zhanyun.util.RandomUtil;
import cc.zhanyun.util.TokenUtil;
import cc.zhanyun.util.excel.ExcelToHtml;
import cc.zhanyun.util.fileutil.FileUtil;

@Service
public class ProjectOfferServiceImpl implements ProjectOfferService {
    @Autowired
    private ProjectOfferRepoImpl pori;
    @Autowired
    private ProjectRepoImpl pri;
    @Autowired
    private OfferRepoImpl ori;
    @Autowired
    private ClientRepoImpl client;
    @Autowired
    private TokenUtil tokenutil;
    @Autowired
    private ImageService imageServiceImpl;
    @Autowired
    private FileRepoImpl fileImpl;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;
    @Autowired
    private HtmlRespImpl htmlRespImpl;

    public Info addProjectOfferOne(ProjectOffer po) {
        String othername = RandomUtil.getRandomFileName();
        String imageOid = RandomUtil.getRandomFileName();
        String oid = RandomUtil.getRandomFileName();
        String date = DateUtil.getCurDate();

        Offer offer = po.getOffer();
        List<Double> offertotal = new ArrayList();
        List<Resourcetypes> tlist = po.getOffer().getResourcetypes();

        for (Resourcetypes t : tlist) {
            List<Selectedresources> slist = t.getSelectedresources();

            List<Double> dlist = new ArrayList();
            for (Selectedresources s : slist) {
                Integer amount = s.getAmount();
                double unitprice = s.getUnitprice();

                double total = amount.intValue() * unitprice;

                s.setSubTotal(String.valueOf(total));

                dlist.add(Double.valueOf(total));
            }

            double tt = 0.0D;
            for (int i = 0; i < dlist.size(); i++) {
                tt += ((Double) dlist.get(i)).doubleValue();
            }

            t.setTypetotal(Double.toString(tt));

            offertotal.add(Double.valueOf(tt));
        }
        double at = 0.0D;

        for (int j = 0; j < offertotal.size(); j++) {
            at += ((Double) offertotal.get(j)).doubleValue();
        }

        po.getOffer().setTotalnotax(Double.toString(at));
        //税金计算

        double t = Double.valueOf(offer.getTax()).doubleValue();
        offer.setTaxation(Double.toString(at / ((1 - t / 100)) * t));

        double totaltax = at + t * at;
        offer.setTotaltax(Double.toString(totaltax));

        po.setOthername(othername);
        po.getProject().setImageOid(imageOid);
        po.setUid(this.tokenutil.tokenToOid());
        po.setOid(oid);

        po.setDate(date);
        Info info = new Info();
        try {
            this.pori.saveProOfferOne(po);

            this.pri.addProject(po.getProject());

            this.ori.addOffer(po.getOffer());

            Image image = new Image();
            image.setOid(imageOid);
            image.setUid(this.tokenutil.tokenToOid());

            this.imageServiceImpl.saveImageService(image);
            info.setOid(oid);
            info.setStatus("添加成功");
        } catch (Exception e) {
            info.setStatus("添加失败");
        }

        String clientName = po.getOffer().getClient().getName();
        String clientTel = po.getOffer().getClient().getTel();
        String clientCompany = po.getOffer().getClient().getCompany();
        Clientmanager c = this.client.selClientByName(clientName, tokenutil.tokenToOid());
        if (c == null) {
            Clientmanager clientmanager = new Clientmanager();
            clientmanager.setName(clientName);
            clientmanager.setUid(tokenutil.tokenToOid());
            if (clientTel != null) {
                clientmanager.setTel(clientTel);
            }
            if (clientCompany != null) {
                clientmanager.setCompany(clientCompany);
            }
            this.client.addClient(clientmanager);
        }
        return info;
    }

    public Info updateProjectOfferOne(ProjectOffer po) {
        Info info = new Info();
        try {

            String date = DateUtil.getCurDate();
            po.setDate(date);
            Offer offer = po.getOffer();
            List<Double> offertotal = new ArrayList<Double>();
            List<Resourcetypes> tlist = po.getOffer().getResourcetypes();
            //循环资源类型
            for (Resourcetypes t : tlist) {
                List<Selectedresources> slist = t.getSelectedresources();

                List<Double> dlist = new ArrayList<Double>();
                //循环资源
                for (Selectedresources s : slist) {
                    Integer amount = s.getAmount();
                    double unitprice = s.getUnitprice();

                    double total = amount.intValue() * unitprice;

                    s.setSubTotal(String.valueOf(total));

                    dlist.add(Double.valueOf(total));
                }
                //
                double tt = 0.0D;
                for (int i = 0; i < dlist.size(); i++) {
                    tt += ((Double) dlist.get(i)).doubleValue();
                }

                t.setTypetotal(Double.toString(tt));

                offertotal.add(Double.valueOf(tt));
            }
            double at = 0.0D;

            for (int j = 0; j < offertotal.size(); j++) {
                at += ((Double) offertotal.get(j)).doubleValue();
            }

            po.getOffer().setTotalnotax(Double.toString(at));

            double t = Double.valueOf(offer.getTax()).doubleValue();
            offer.setTaxation(Double.toString(t * at));

            double totaltax = at + t * at;
            offer.setTotaltax(Double.toString(totaltax));
            po.setUid(this.tokenutil.tokenToOid());

            try {
                this.pori.saveProOfferOne(po);

                this.pri.addProject(po.getProject());

                this.ori.addOffer(po.getOffer());

                info.setStatus("添加成功");
            } catch (Exception e) {
                info.setStatus("添加失败");
            }

            String clientName = po.getOffer().getClient().getName();
            String clientTel = po.getOffer().getClient().getTel();
            String clientCompany = po.getOffer().getClient().getCompany();
            Clientmanager c = this.client.selClientByName(clientName, tokenutil.tokenToOid());
            if (c == null) {
                Clientmanager clientmanager = new Clientmanager();
                clientmanager.setName(clientName);
                clientmanager.setUid(tokenutil.tokenToOid());
                if (clientTel != null) {
                    clientmanager.setTel(clientTel);
                }
                if (clientCompany != null) {
                    clientmanager.setCompany(clientCompany);
                }
                this.client.addClient(clientmanager);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    public void delProjectOfferOne(String oid) {
        this.pori.delProOfferOne(oid);
    }

    public ProjectOffer selProjectOfferOne(String oid) {
        return this.pori.selProOfferOne(oid);
    }

    public List<ProjectOfferVO> selProjectOfferList(Integer num ,Integer size) {
        Pageable pageable = null;

        if (size != null) {
            pageable = new PageRequest( num , size);
        }
        List<ProjectOffer> polist = this.pori.selProOfferList(this.tokenutil
                .tokenToOid(), pageable);
        List<ProjectOfferVO> povolist = new ArrayList();

        for (ProjectOffer p : polist) {
            ProjectOfferVO povo = new ProjectOfferVO();
            povo.setOid(p.getOid());
            povo.setName(p.getName());
            povo.setAddress(p.getProject().getLocation().getAddress());
            povo.setStatus(p.getOffer().getStatus());
            povo.setClientmanager(p.getOffer().getClient().getName());
            povo.setDate(p.getDate());
            povolist.add(povo);
        }

        return povolist;
    }

    public List<ProjectOfferVO> selProjectOfferOfStatus(Integer status, Integer num ,Integer size) {
        Pageable pageable = null;
        if (size != null) {
            pageable = new PageRequest( num ,size);
        }
        List<ProjectOffer> polist = this.pori.selProOfferOfStatusList(status,
                this.tokenutil.tokenToOid(), pageable);
        List<ProjectOfferVO> povolist = new ArrayList();

        for (ProjectOffer p : polist) {
            ProjectOfferVO povo = new ProjectOfferVO();
            povo.setOid(p.getOid());
            povo.setName(p.getName());
            povo.setAddress(p.getProject().getLocation().getAddress());
            povo.setStatus(p.getOffer().getStatus());
            povo.setClientmanager(p.getOffer().getClient().getName());
            povolist.add(povo);
        }

        return povolist;
    }

    public void updateProjectOfferStatus(OfferVO ovo) {
        this.pori.updateProjectOfferStatus(ovo);
    }

    /**
     * 上传
     *
     * @param file
     * @param oid
     * @return
     */
    public Info updateProjectImage(MultipartFile file, String oid) {
        Info info = new Info();
        info.setOid(oid);
        try {
            ProjectOffer po = selProjectOfferOne(oid);
            String imageOid = po.getProject().getImageOid();

            String imagelocation = "场地效果图";
            this.imageServiceImpl.saveImageOneService(file, imageOid,
                    imagelocation);
            info.setStatus("成功");
        } catch (Exception e) {
            info.setStatus("失败");
        }
        return info;
    }


    /**
     * 在线预览excel
     */
    @Override
    public String selOfferOnline(String oid) {
        // 路径生成
        String basePath = Constant.BASEPATH;
        String uid = tokenutil.tokenToOid();
        String folder = "html";
        String htmlpath = FileUtil.createUserFiles(uid, basePath, folder);
        Html html = htmlRespImpl.selHtmlByOfferOid(oid);

        // 查询报价单
        ProjectOffer pOffer = selProjectOfferOne(oid);
        // 如果不为空,进行下一步
        if (pOffer != null) {
            if (html == null) {
                UserInfoVO uvo = userService.selUserInfo(uid);
                UserAccount userAccount = new UserAccount();
                userAccount.setName(uvo.getName());
                userAccount.setCompany(uvo.getCompany());
                userAccount.setPhone(uvo.getPhone());
                userAccount.setCompanyengname(uvo.getCompanyengname());
                userAccount.setWebsite(uvo.getWebsite());

                // 查询
                FileManager fm = fileImpl.selFileByOfferoid(
                        tokenutil.tokenToOid(), oid);
                if (fm == null) {
                    // 如果没有,立刻生成文件
                    emailService.MongodbToFile(null, pOffer, userAccount);
                }
                // excel文件路径
                String excelPath = basePath + fm.getUrl() + fm.getOthername();
                // 转化为在在线html
                String htmlName = ExcelToHtml.excelToHtml(excelPath, htmlpath);
                String htmlUrlString = uid + File.separator + folder
                        + File.separator;
                // 持久化html页面信息
                Html html2 = new Html();
                html2.setOid(RandomUtil.getRandomFileName());
                html2.setOfferoid(oid);
                html2.setUrl(htmlUrlString);
                html2.setHtmlname(htmlName);
                htmlRespImpl.addHtml(html2);
                // 返回在线地址
                return uid + File.separator + folder + File.separator
                        + htmlName;

            } else {
                // 如果数据库中已经有了,直接调用地址
                return html.getUrl() + html.getHtmlname();
            }
        }
        return null;
    }

    /**
     * 批量上传图片
     *
     * @param multipartFileList
     * @return
     */
    @Override
    public List<Info> batchUpdateProjectOfferImage(List<MultipartFile> multipartFileList, String oid) {
        List<Info> ilist = new ArrayList<Info>();
        for (MultipartFile m : multipartFileList) {
            Info in = updateProjectImage(m, oid);

            ilist.add(in);
        }
        return ilist;
    }
}
