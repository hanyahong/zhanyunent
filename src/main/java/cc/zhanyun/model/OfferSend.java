package cc.zhanyun.model;

public class OfferSend {
    private String to;
    private String fileTemplateOid;
    private String setText;
    private String offerOid;
    private String name;

    public String getSetText() {
        return this.setText;
    }

    public void setSetText(String setText) {
        this.setText = setText;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFileTemplateOid() {
        return this.fileTemplateOid;
    }

    public void setFileTemplateOid(String fileTemplateOid) {
        this.fileTemplateOid = fileTemplateOid;
    }

    public String getOfferOid() {
        return this.offerOid;
    }

    public void setOfferOid(String offerOid) {
        this.offerOid = offerOid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


