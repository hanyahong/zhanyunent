/*    */
package cc.zhanyun.model.vo;
/*    */ 
/*    */

import org.springframework.data.annotation.Id;

/*    */
/*    */ public class ClientimageVO {
    /*    */
    @Id
/*    */ private String oid;
    /*    */   private String image;

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
    public String getImage() {
/* 19 */
        return this.image;
/*    */
    }

    /*    */
/*    */
    public void setImage(String image) {
/* 23 */
        this.image = image;
/*    */
    }
/*    */
}


/* Location:              G:\zy18.jar!\cc\zhanyun\model\vo\ClientimageVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */