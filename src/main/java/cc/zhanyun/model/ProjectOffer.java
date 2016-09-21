package cc.zhanyun.model;

import cc.zhanyun.model.offer.Offer;
import cc.zhanyun.model.project.Project;

public class ProjectOffer {
    @org.springframework.data.annotation.Id
    private String oid;
    private String name;
    private Project project;
    private Offer offer;
    private String uid;
    private String othername;
    private String date;

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

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Offer getOffer() {
        return this.offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}
