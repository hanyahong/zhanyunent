/*    */
package cc.zhanyun.model;
/*    */ 
/*    */

import org.springframework.data.annotation.Id;

/*    */
/*    */ public class UserLeanCloud
/*    */ {
    /*    */
    @Id
/*    */ private String oid;
    /*    */   private String email;
    /*    */   private String sessionToken;
    /*    */   private String username;
    /*    */   private String password;
    /*    */   private String phone;
    /*    */   private String appKey;

    /*    */
/*    */
    public String getOid() {
/* 17 */
        return this.oid;
/*    */
    }

    /*    */
/*    */
    public void setOid(String oid) {
/* 21 */
        this.oid = oid;
/*    */
    }

    /*    */
/*    */
    public String getEmail() {
/* 25 */
        return this.email;
/*    */
    }

    /*    */
/*    */
    public void setEmail(String email) {
/* 29 */
        this.email = email;
/*    */
    }

    /*    */
/*    */
    public String getSessionToken() {
/* 33 */
        return this.sessionToken;
/*    */
    }

    /*    */
/*    */
    public void setSessionToken(String sessionToken) {
/* 37 */
        this.sessionToken = sessionToken;
/*    */
    }

    /*    */
/*    */
    public String getUsername() {
/* 41 */
        return this.username;
/*    */
    }

    /*    */
/*    */
    public void setUsername(String username) {
/* 45 */
        this.username = username;
/*    */
    }

    /*    */
/*    */
    public String getPassword() {
/* 49 */
        return this.password;
/*    */
    }

    /*    */
/*    */
    public void setPassword(String password) {
/* 53 */
        this.password = password;
/*    */
    }

    /*    */
/*    */
    public String getPhone() {
/* 57 */
        return this.phone;
/*    */
    }

    /*    */
/*    */
    public void setPhone(String phone) {
/* 61 */
        this.phone = phone;
/*    */
    }

    /*    */
/*    */
    public String getAppKey() {
/* 65 */
        return this.appKey;
/*    */
    }

    /*    */
/*    */
    public void setAppKey(String appKey) {
/* 69 */
        this.appKey = appKey;
/*    */
    }
/*    */
}


/* Location:              G:\zy18.jar!\cc\zhanyun\model\UserLeanCloud.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */