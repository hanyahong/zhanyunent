package cc.zhanyun.model;

import org.springframework.data.annotation.Id;

/**
 * Created by hyh on 16-9-12.
 */
public class ProjectOfferFileModel {
    @Id
    private String oid;//唯一id
    private String name;//模板名称
    private String url;//模板地址
    private String createdate;//模板创建日期
    private String status;//模板状态
    private String uid;//再时不使用
    private String image;
    private String simage;
    private String othername;//系统名称

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSimage() {
        return simage;
    }

    public void setSimage(String simage) {
        this.simage = simage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
}
