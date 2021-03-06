package cc.zhanyun.model;

import org.springframework.data.annotation.Id;

/**
 * Created by hyh on 16-9-12.
 */
public class ProjectOfferDefaultFileModel {
    @Id
    private String oid;
    private String name;
    private String url;
    private String image;
    private String simage;
    private String oldoid;
    private String othername;

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    public String getOldoid() {
        return oldoid;
    }

    public void setOldoid(String oldoid) {
        this.oldoid = oldoid;
    }

    public String getSimage() {
        return simage;
    }

    public void setSimage(String simage) {
        this.simage = simage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String uid;

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
