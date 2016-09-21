package cc.zhanyun.model.location;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Location {
    @Id
    private String oid;
    private String name;
    private String address;
    private String contacts;
    private String introduction;
    private String images;
    private List<Houses> houses = new ArrayList();
    private String uid;
    private String website;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // 批量导入状态值
    private Integer status;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContacts() {
        return this.contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getImages() {
        return this.images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public List<Houses> getHouses() {
        return this.houses;
    }

    public void setHouses(List<Houses> houses) {
        this.houses = houses;
    }
}
