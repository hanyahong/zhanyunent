/*    */
package cc.zhanyun.model.vo;
/*    */ 
/*    */

import org.springframework.data.annotation.Id;

/*    */
/*    */ public class HousesVO {
    /*    */
    @Id
/*    */ private String oid;
    /*    */   private String hoid;
    /*    */   private String images;
    /*    */   private String caseimages;

    /*    */
/*    */
    public String getCaseimages() {
/* 13 */
        return this.caseimages;
/*    */
    }

    /*    */
/*    */
    public void setCaseimages(String caseimages) {
/* 17 */
        this.caseimages = caseimages;
/*    */
    }

    /*    */
/*    */
    public String getOid() {
/* 21 */
        return this.oid;
/*    */
    }

    /*    */
/*    */
    public void setOid(String oid) {
/* 25 */
        this.oid = oid;
/*    */
    }

    /*    */
/*    */
    public String getHoid() {
/* 29 */
        return this.hoid;
/*    */
    }

    /*    */
/*    */
    public void setHoid(String hoid) {
/* 33 */
        this.hoid = hoid;
/*    */
    }

    /*    */
/*    */
    public String getImages() {
/* 37 */
        return this.images;
/*    */
    }

    /*    */
/*    */
    public void setImages(String images) {
/* 41 */
        this.images = images;
/*    */
    }
/*    */
}


/* Location:              G:\zy18.jar!\cc\zhanyun\model\vo\HousesVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */