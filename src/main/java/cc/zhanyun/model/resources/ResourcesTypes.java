
package cc.zhanyun.model.resources;


import io.swagger.annotations.ApiModel;

import java.util.List;

import org.springframework.data.annotation.Id;


@ApiModel(description = "")
public class ResourcesTypes {

    @Id
    private String oid = null;
    private String uid = null;
    private List<ResourcesTypeOne> typelist = null;


    public String getUid() {

        return this.uid;

    }


    public void setUid(String uid) {

        this.uid = uid;

    }


    public String getOid() {

        return this.oid;

    }


    public void setOid(String oid) {

        this.oid = oid;

    }


    public List<ResourcesTypeOne> getTypelist() {

        return this.typelist;

    }


    public void setTypelist(List<ResourcesTypeOne> typelist) {

        this.typelist = typelist;

    }

}


