package cc.zhanyun.model.user;

import org.springframework.data.annotation.Id;


public class UserReturnVerify {
    private String status;
    private String info;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


}


