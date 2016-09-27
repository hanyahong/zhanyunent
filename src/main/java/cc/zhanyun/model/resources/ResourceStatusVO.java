package cc.zhanyun.model.resources;

import org.springframework.data.annotation.Id;

public class ResourceStatusVO {
    @Id
    private String oid;
    private String status;
    private String images;

    public String getOid() {
        return this.oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImages() {
        return this.images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}


