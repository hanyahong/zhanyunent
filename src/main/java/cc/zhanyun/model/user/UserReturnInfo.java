package cc.zhanyun.model.user;

public class UserReturnInfo {
    private String oid;
    private String token;
    private String phone;
    private String status;
    private String images;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getOid() {
        /* 10 */
        return this.oid;
    }

    public void setOid(String oid) {
		/* 14 */
        this.oid = oid;
    }

    public String getToken() {
		/* 18 */
        return this.token;
    }

    public void setToken(String token) {
		/* 22 */
        this.token = token;
    }

    public String getPhone() {
		/* 26 */
        return this.phone;
    }

    public void setPhone(String phone) {
		/* 30 */
        this.phone = phone;
    }

    public String getStatus() {
		/* 34 */
        return this.status;
    }

    public void setStatus(String status) {
		/* 38 */
        this.status = status;
    }

}
