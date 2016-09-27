
package cc.zhanyun.model.file;


import org.springframework.data.annotation.Id;


public class FileManager {

    @Id
    private String oid;//ID
    private String name;//文件名称
    private String basepath;//基础路径
    private String url;//远程路径
    private String date;//更新时间
    private String othername;//系统名称
    private String uid;//拥有者
    private String offerOid;//报价单id
    private String type;//文件类型
    private String postfix;//后缀

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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


