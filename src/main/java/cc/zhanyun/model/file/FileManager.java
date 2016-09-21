
package cc.zhanyun.model.file;


import org.springframework.data.annotation.Id;


public class FileManager {

    @Id
    private String oid;
    private String name;
    private String basepath;
    private String url;
    private String date;
    private String othername;
    private String uid;
    private String offerOid;


    public String getBasepath() {

        return this.basepath;

    }


    public void setBasepath(String basepath) {

        this.basepath = basepath;

    }


    public String getOfferOid() {

        return this.offerOid;

    }


    public void setOfferOid(String offerOid) {

        this.offerOid = offerOid;

    }


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


    public String getName() {

        return this.name;

    }


    public void setName(String name) {

        this.name = name;

    }


    public String getUrl() {

        return this.url;

    }


    public void setUrl(String url) {

        this.url = url;

    }


    public String getDate() {

        return this.date;

    }


    public void setDate(String date) {

        this.date = date;

    }


    public String getOthername() {

        return this.othername;

    }


    public void setOthername(String othername) {

        this.othername = othername;

    }

}


