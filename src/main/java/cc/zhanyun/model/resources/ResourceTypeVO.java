package cc.zhanyun.model.resources;

import org.springframework.data.annotation.Id;

/**
 * Created by hyh on 16-9-7.
 */
public class ResourceTypeVO {
    @Id
    private String oid;
    private String newType;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getNewType() {
        return newType;
    }

    public void setNewType(String newType) {
        this.newType = newType;
    }
}
