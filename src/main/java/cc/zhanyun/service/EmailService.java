package cc.zhanyun.service;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import cc.zhanyun.util.Constant;
import cc.zhanyun.model.Info;
import cc.zhanyun.model.OfferSend;
import cc.zhanyun.model.ProjectOffer;
import cc.zhanyun.model.Status;
import cc.zhanyun.model.file.FileManager;
import cc.zhanyun.model.offer.Resourcetypes;
import cc.zhanyun.model.offer.Selectedresources;
import cc.zhanyun.model.user.UserAccount;
import cc.zhanyun.repository.impl.FileRepoImpl;
import cc.zhanyun.repository.impl.UserRepoImpl;
import cc.zhanyun.util.RandomUtil;
import cc.zhanyun.util.TokenUtil;
import cc.zhanyun.util.fileutil.FileUtil;
import cc.zhanyun.util.myutil.PoiUtil;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TokenUtil tokenutil;
    @Autowired
    private UserRepoImpl userImpl;
    @Autowired
    private ProjectOfferService pos;
    @Autowired
    private FileRepoImpl fileImpl;

    /**
     * 发送邮件
     *
     * @param offerSend
     * @return
     */
    public Info sendAttachmentsMail(OfferSend offerSend) {
        Info in = new Info();
        String uid = this.tokenutil.tokenToOid();
        // 基本路径

        //获取文件绝对路径(打包后可用必须)
        // URL au = this.getClass().getClassLoader().getResource(Constant.BASEPATH);
        String basePath = Constant.BASEPATH;
        System.out.println("发送邮件及文件下载地址：" + basePath);
        try {
            //发送邮件
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //查询用户
            UserAccount u = this.userImpl.selUserById(uid);
            //设置邮箱基本设置
            helper.setFrom(Constant.EMAIL);
            helper.setTo(offerSend.getTo());
            helper.setSubject(u.getCompany() + offerSend.getName()
                    + "报价单,请您查收--" + u.getName());
            helper.setText("报价单");

            //查询用户信息
            UserAccount userAccount = this.userImpl.selUserById(uid);
            //查询报价单信息
            ProjectOffer po = this.pos.selProjectOfferOne(offerSend
                    .getOfferOid());
            //设置模板(暂时固定)
            offerSend.setFileTemplateOid("1");
            //查询文件信息
            FileManager fileManager = this.fileImpl.fileDownload(offerSend
                    .getFileTemplateOid());
            String url = "1";
            if (fileManager != null) {
                url = fileManager.getUrl();
            }

            String filePath = MongodbToFile(url, po, userAccount);
            if (filePath != null) {
                FileSystemResource file = new FileSystemResource(new File(
                        basePath + File.separator + filePath));

                String name = this.fileImpl.selFileByOfferoid(uid,
                        offerSend.getOfferOid()).getName();
                helper.addAttachment(name, file);

                this.mailSender.send(mimeMessage);
                in.setStatus("发送成功");
            } else {
                in.setStatus("发送失败，模板有空格或空行");
            }
        } catch (Exception e) {
            e.printStackTrace();
            in.setStatus("发送失败");
        }
        return in;
    }

    /**
     * 解析数据库数据到文件
     *
     * @param url
     * @param po
     * @param userAccount
     * @return
     */
    public String MongodbToFile(String url, ProjectOffer po, UserAccount userAccount) {
        // 获取oid
        String oid = this.tokenutil.tokenToOid();
        // 获取模板路径
        String basePath = Constant.BASEPATH;
        // test
        System.out.println(basePath);
        // 如果模板为空,使用默认模板
        if (url.equals("1")) {
            // 默认模板路径
            url = basePath + "offerlist.xls";
        }
        try {
            //创建文件
            File f = new File(url);
            //输入流
            InputStream in = new FileInputStream(url);
            //poi格式
            POIFSFileSystem fs = new POIFSFileSystem(in);
            //新建hssf对象
            HSSFWorkbook workbook = new HSSFWorkbook(fs);
            //查看sheet0中
            HSSFSheet sheet = workbook.getSheetAt(0);

            List<Status> slist = new ArrayList<Status>();
            HSSFRow row;
            for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
                row = sheet.getRow(j);

                for (int k = 0; k < row.getLastCellNum(); k++) {
                    String value = row.getCell(k).getStringCellValue().trim();
                    Status status = new Status();
                    status.setCell(Integer.valueOf(k));
                    status.setRow(Integer.valueOf(j));
                    status.setValue(value);
                    slist.add(status);
                }
            }

            Integer index = Integer.valueOf(0);
            for (Status s : slist) {

                if (s.getValue().equals("offerName")) {
                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(userAccount.getCompany() + "报价单");
                } else if (s.getValue().equals("clientCompany")) {

                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(
                                    po.getOffer().getClient().getCompany());
                } else if (s.getValue().equals("clientName")) {
                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(po.getOffer().getClient().getName());
                } else if (s.getValue().equals("userPhone")) {
                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(userAccount.getPhone());
                } else if (s.getValue().equals("projectAddress")) {

                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(
                                    po.getProject().getLocation().getAddress());
                } else if (s.getValue().equals("projectName")) {
                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(po.getName());
                } else if (s.getValue().equals("preparePlanTime")) {
                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(po.getProject().getPrepareplantime());
                } else if (s.getValue().equals("startPlanTime")) {
                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(po.getProject().getStartplantime());
                } else if (s.getValue().equals("leavePlanTime")) {
                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(po.getProject().getLeaveplantime());
                } else if (s.getValue().equals("projectOid")) {
                    sheet.getRow(s.getRow())
                            .getCell(s.getCell().intValue())
                            .setCellValue(po.getOid());
                } else if (s.getValue().equals("offerDate")) {
                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(po.getProject().getCreatetime());
                } else if (s.getValue().equals("userName")) {
                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(userAccount.getName());
                } else if (s.getValue().equals("userCompany")) {
                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(userAccount.getCompany());
                } else if (s.getValue().equals("userPhone")) {
                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(userAccount.getPhone());
                } else if (s.getValue().equals("proportion")) {
                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(po.getOffer().getProportion());
                } else if (s.getValue().equals("tax")) {
                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(po.getOffer().getTax());
                } else if (s.getValue().equals("discount")) {
                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(po.getOffer().getDiscount());
                } else if (s.getValue().equals("englishName")) {
                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(userAccount.getCompanyengname());
                } else if (s.getValue().equals("url")) {
                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(userAccount.getWebsite());
                } else if (s.getValue().equals("序号")) {
                    index = s.getRow();
                } else if (s.getValue().equals("afterTax")) {
                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(po.getOffer().getTotaltax());
                } else if (s.getValue().equals("taxation")) {
                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(po.getOffer().getTaxation());
                } else if (s.getValue().equals("TotalAllItem")) {
                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(po.getOffer().getTotaltax());
                } else if (s.getValue().equals("offerDate")) {
                    sheet.getRow(s.getRow().intValue())
                            .getCell(s.getCell().intValue())
                            .setCellValue(po.getDate());
                }
            }

            Integer in1 = Integer.valueOf(index.intValue() + 1);

            List<Resourcetypes> resourceList = po.getOffer().getResourcetypes();

            Integer count = Integer.valueOf(resourceList.size());

            if (count.intValue() != 0) {
                for (int g = (resourceList.size() - 1); g >= 0; g--) {
                    Resourcetypes r = resourceList.get(g);
                    if (r.getSelectedresources().size() > 1) {
                        // 插入空白行 3行
                        PoiUtil.insertRow(sheet, in1, 2);
                        // 获取sheet2中的样式
                        HSSFSheet sheet2 = workbook.getSheetAt(1);
                        // 遍历sheet2中的三行数据

                        // 赋值每一行的样式 到 制定的行
                        PoiUtil.copyRow(workbook, sheet2.getRow(0),
                                sheet.createRow(in1.intValue() + 1), true);
                        // 赋值每一行的样式 到 制定的行
                        PoiUtil.copyRow(workbook, sheet2.getRow(2),
                                sheet.createRow(in1.intValue() + 2), true);

                        // @//
                        // 获取新加入的行
                        HSSFRow row2 = sheet.getRow(in1.intValue() + 1);

                        // 对其进行遍历每个单元格
                        for (int k = 0; k < row2.getLastCellNum(); k++) {
                            HSSFFont font = row2.getCell(k).getCellStyle().getFont(workbook);
                            // 获取每个单元各种的数据
                            String value2 = row2.getCell(k)
                                    .getStringCellValue().trim();
                            // 判断如果与给定值相等，进行等值替换
                            if (value2.equals("resourceType")) {
                                row2.getCell(k).setCellValue(r.getName());
                            }
                        }
                        // 获取新加入的行
                        HSSFRow row6 = sheet.getRow(in1.intValue() + 2);
                        // 对其进行遍历每个单元格
                        for (int k = 0; k < row6.getLastCellNum(); k++) {
                            // 获取每个单元各种的数据
                            String value2 = row6.getCell(k)
                                    .getStringCellValue().trim();
                            // 判断如果与给定值相等，进行等值替换
                            if (value2.equals("TypeTotal")) {
                                row6.getCell(k).setCellValue(r.getTypetotal());
                            }
                        }

                        // 获取报价单每个分类具体有多少个设备
                        Integer rsize = Integer.valueOf(r
                                .getSelectedresources().size());
                        // 如果不少于1件设备，我们可以进行操作
                        if (rsize.intValue() > 1) {

                            // 对不少于1件的分类进行遍历操作
                            for (int w = 0; w < rsize.intValue(); w++) {
                                // 每次插入一行空白行
                                PoiUtil.insertRow(
                                        sheet,
                                        Integer.valueOf(w + in1.intValue() + 2),
                                        Integer.valueOf(1));
                                // 每次插入赋值过来的样式
                                PoiUtil.copyRow(
                                        workbook,
                                        sheet2.getRow(1),
                                        sheet.createRow(w + in1.intValue() + 2),
                                        true);
                                // 每一行要填写的值
                                Selectedresources s = (Selectedresources) r
                                        .getSelectedresources().get(w);
                                // 需要赋值的行数
                                HSSFRow row3 = sheet.getRow(w + in1.intValue()
                                        + 2);
                                // 遍历每一行的每一列，将值传入需要赋值的行数
                                for (int x = 0; x < row3.getLastCellNum(); x++) {

                                    String value3 = row3.getCell(x)
                                            .getStringCellValue().trim();

                                    if (value3.equals("simpleName")) {
                                        row3.getCell(x).setCellValue(
                                                s.getSimplename());
                                    } else if (value3.equals("resourceName")) {
                                        row3.getCell(x).setCellValue(
                                                s.getName());
                                    } else if (value3.equals("resourceID")) {
                                        row3.getCell(x).setCellValue(
                                                String.valueOf(w + 1));
                                    } else if (value3.equals("resourceCount")) {
                                        row3.getCell(x).setCellValue(
                                                s.getAmount().toString());
                                    } else if (value3.equals("resourceUnit")) {
                                        row3.getCell(x).setCellValue(
                                                s.getUnit().toString());
                                    } else if (value3.equals("days")) {
                                        row3.getCell(x).setCellValue(
                                                s.getDays().toString());
                                    } else if (value3.equals("unitPrice")) {
                                        row3.getCell(x).setCellValue(
                                                Double.toString(s
                                                        .getUnitprice()));
                                    } else if (value3.equals("subTotal")) {
                                        row3.getCell(x).setCellValue(
                                                s.getSubTotal());
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 定义输出路径
            String folder = "offerFiles";
            String fileSaveUrl = FileUtil
                    .createUserFiles(oid, basePath, folder);
            String saveurl = oid + File.separator + folder + File.separator;

            FileManager fm = this.fileImpl.selFileByOfferoid(oid, po.getOid());

            String newFileName = null;

            if (fm == null) {
                newFileName = RandomUtil.getRandomFileName() + ".xls";
                FileOutputStream out = new FileOutputStream(fileSaveUrl
                        + newFileName);
                //打印
                System.out.println(fileSaveUrl + newFileName);
                workbook.write(out);
                out.close();
                FileManager filemanager = new FileManager();
                filemanager.setDate(new Date().toString());
                filemanager.setName(po.getName() + ".xls");
                filemanager.setOthername(newFileName);
                filemanager.setBasepath(basePath);
                filemanager.setUrl(saveurl);
                filemanager.setUid(oid);
                filemanager.setOfferOid(po.getOid());

                this.fileImpl.fileUpload(filemanager);
            } else {
                newFileName = fm.getOthername();

                FileOutputStream out = new FileOutputStream(fileSaveUrl
                        + newFileName);

                workbook.write(out);
                out.close();

                FileManager filemanager = new FileManager();

                filemanager.setOid(fm.getOid());
                filemanager.setDate(new Date().toString());
                filemanager.setName(fm.getName());
                filemanager.setOthername(newFileName);
                filemanager.setUrl(fm.getUrl());
                filemanager.setUid(fm.getUid());
                filemanager.setBasepath(fm.getBasepath());
                filemanager.setOfferOid(fm.getOfferOid());

                this.fileImpl.fileUpload(filemanager);
            }

            return saveurl + newFileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
