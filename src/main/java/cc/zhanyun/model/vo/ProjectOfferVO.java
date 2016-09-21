package cc.zhanyun.model.vo;

import org.springframework.data.annotation.Id;

public class ProjectOfferVO {
    @Id
    private String oid;
    private String name;
    private String address;
    private Integer status;
    private String clientmanager;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getClientmanager() {
        return this.clientmanager;
    }

    public void setClientmanager(String clientmanager) {
        this.clientmanager = clientmanager;
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}


