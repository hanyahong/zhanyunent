/*    */
package cc.zhanyun.model.vo;
/*    */ 
/*    */

import org.springframework.data.annotation.Id;

/*    */
/*    */ public class OfferVO {
    /*    */
    @Id
/*    */ private String oid;
    /*    */   private String name;
    /*    */   private String address;
    /*    */   private Integer status;

    /*    */
/*    */
    public String getOid() {
/* 13 */
        return this.oid;
/*    */
    }

    /*    */
/*    */
    public void setOid(String oid) {
/* 17 */
        this.oid = oid;
/*    */
    }

    /*    */
/*    */
    public String getName() {
/* 21 */
        return this.name;
/*    */
    }

    /*    */
/*    */
    public void setName(String name) {
/* 25 */
        this.name = name;
/*    */
    }

    /*    */
/*    */
    public String getAddress() {
/* 29 */
        return this.address;
/*    */
    }

    /*    */
/*    */
    public void setAddress(String address) {
/* 33 */
        this.address = address;
/*    */
    }

    /*    */
/*    */
    public Integer getStatus() {
/* 37 */
        return this.status;
/*    */
    }

    /*    */
/*    */
    public void setStatus(Integer status) {
/* 41 */
        this.status = status;
/*    */
    }
/*    */
}


/* Location:              G:\zy18.jar!\cc\zhanyun\model\vo\OfferVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */