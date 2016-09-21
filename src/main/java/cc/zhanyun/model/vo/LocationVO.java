/*    */
package cc.zhanyun.model.vo;
/*    */ 
/*    */

import org.springframework.data.annotation.Id;

/*    */
/*    */ public class LocationVO {
    /*    */
    @Id
/*    */ private String oid;
    /*    */   private String name;
    /*    */   private String address;

    /*    */
/*    */
    public String getOid() {
/* 12 */
        return this.oid;
/*    */
    }

    /*    */
/*    */
    public void setOid(String oid) {
/* 16 */
        this.oid = oid;
/*    */
    }

    /*    */
/*    */
    public String getName() {
/* 20 */
        return this.name;
/*    */
    }

    /*    */
/*    */
    public void setName(String name) {
/* 24 */
        this.name = name;
/*    */
    }

    /*    */
/*    */
    public String getAddress() {
/* 28 */
        return this.address;
/*    */
    }

    /*    */
/*    */
    public void setAddress(String address) {
/* 32 */
        this.address = address;
/*    */
    }
/*    */
}


/* Location:              G:\zy18.jar!\cc\zhanyun\model\vo\LocationVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */