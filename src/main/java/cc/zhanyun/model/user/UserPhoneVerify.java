package cc.zhanyun.model.user;

import java.util.Date;

/**
 * 用户电话认证实体
 */
public class UserPhoneVerify {
    @org.springframework.data.annotation.Id
    private String oid;
    private String phone;
    private String code;
    private Date time;

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getOid() {
        return this.oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}


