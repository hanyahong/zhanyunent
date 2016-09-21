/*    */
package cc.zhanyun.model.vo;
/*    */ 
/*    */

import org.springframework.data.annotation.Id;

/*    */
/*    */ public class OfferStatusVO {
    /*    */
    @Id
/*    */ private String oid;
    /*    */   private Integer status;

    /*    */
/*    */
    public String getOid() {
/* 11 */
        return this.oid;
/*    */
    }

    /*    */
/*    */
    public void setOid(String oid) {
/* 15 */
        this.oid = oid;
/*    */
    }

    /*    */
/*    */
    public Integer getStatus() {
/* 19 */
        return this.status;
/*    */
    }

    /*    */
/*    */
    public void setStatus(Integer status) {
/* 23 */
        this.status = status;
/*    */
    }
/*    */
}


/* Location:              G:\zy18.jar!\cc\zhanyun\model\vo\OfferStatusVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */