package cc.zhanyun.model;

import cc.zhanyun.model.offer.Offer;
import org.springframework.data.annotation.Id;

/**
 * Created by hyh on 16-9-9.
 */
public class ProjectOfferModel {
    @Id
    private String oid;
    private String name;
    private Offer offer;
    private String uid;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}
