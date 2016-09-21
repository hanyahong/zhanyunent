/*    */
package cc.zhanyun.model.vo;
/*    */ 
/*    */

import ch.qos.logback.core.net.server.Client;

/*    */
/*    */ public class ProjectVO
/*    */ {
    /*    */
    @org.springframework.data.annotation.Id
/*    */ private String oid;
    /*    */   private String name;
    /*    */   private String address;
    /*    */   private Integer status;
    /*    */   private Client client;

    /*    */
/*    */
    public String getOid()
/*    */ {
/* 16 */
        return this.oid;
/*    */
    }

    /*    */
/*    */
    public void setOid(String oid) {
/* 20 */
        this.oid = oid;
/*    */
    }

    /*    */
/*    */
    public String getName() {
/* 24 */
        return this.name;
/*    */
    }

    /*    */
/*    */
    public void setName(String name) {
/* 28 */
        this.name = name;
/*    */
    }

    /*    */
/*    */
    public String getAddress() {
/* 32 */
        return this.address;
/*    */
    }

    /*    */
/*    */
    public void setAddress(String address) {
/* 36 */
        this.address = address;
/*    */
    }

    /*    */
/*    */
    public Integer getStatus() {
/* 40 */
        return this.status;
/*    */
    }

    /*    */
/*    */
    public void setStatus(Integer status) {
/* 44 */
        this.status = status;
/*    */
    }

    /*    */
/*    */
    public Client getClient() {
/* 48 */
        return this.client;
/*    */
    }

    /*    */
/*    */
    public void setClient(Client client) {
/* 52 */
        this.client = client;
/*    */
    }
/*    */
}


/* Location:              G:\zy18.jar!\cc\zhanyun\model\vo\ProjectVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */