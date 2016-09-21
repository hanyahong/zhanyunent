package cc.zhanyun.model.html;

import org.springframework.data.annotation.Id;

public class Html {
    @Id
    private String oid;
    private String htmlname;
    private String url;
    private String offeroid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOfferoid() {
        return offeroid;
    }

    public void setOfferoid(String offeroid) {
        this.offeroid = offeroid;
    }

    public String getHtmlname() {
        return htmlname;
    }

    public void setHtmlname(String htmlname) {
        this.htmlname = htmlname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
