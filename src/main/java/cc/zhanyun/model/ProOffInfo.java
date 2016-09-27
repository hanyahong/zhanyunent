package cc.zhanyun.model;

import org.springframework.data.annotation.Id;

/**
 * Created by hyh on 16-9-27.
 */
public class ProOffInfo {
    @Id
    private String oid;
    private String imageOid;
    private String fileAndImages;
    private String status;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getImageOid() {
        return imageOid;
    }

    public void setImageOid(String imageOid) {
        this.imageOid = imageOid;
    }

    public String getFileAndImages() {
        return fileAndImages;
    }

    public void setFileAndImages(String fileAndImages) {
        this.fileAndImages = fileAndImages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
