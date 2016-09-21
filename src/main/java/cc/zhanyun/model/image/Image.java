
package cc.zhanyun.model.image;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;


public class Image {

    @Id
    private String oid;
    private String uid;
    private List<ImageProperty> property = new ArrayList();


    public List<ImageProperty> getProperty() {

        return this.property;

    }


    public void setProperty(List<ImageProperty> property) {

        this.property = property;

    }


    public String getOid() {

        return this.oid;

    }


    public void setOid(String oid) {

        this.oid = oid;

    }


    public String getUid() {

        return this.uid;

    }


    public void setUid(String uid) {

        this.uid = uid;

    }

}


