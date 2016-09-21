package cc.zhanyun.model.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class ResourcesTypeOne {
    @Id
    private String oid;
    private String name;
    private List<String> resources = new ArrayList();

    public List<String> getResources() {
        return this.resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOid() {
        return this.oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}


