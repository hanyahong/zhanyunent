/*    */
package cc.zhanyun.model.location;
/*    */ 
/*    */

import org.springframework.data.annotation.Id;

/*    */
/*    */ public class Images {
    /*    */
    @Id
/*    */ private String imageoid;
    /*    */   private String name;
    /*    */   private String url;
    /*    */   private String projectoid;

    /*    */
/*    */
    public String getProjectoid() {
/* 13 */
        return this.projectoid;
/*    */
    }

    /*    */
/*    */
    public void setProjectoid(String projectoid) {
/* 17 */
        this.projectoid = projectoid;
/*    */
    }

    /*    */
/*    */
    public String getImageoid() {
/* 21 */
        return this.imageoid;
/*    */
    }

    /*    */
/*    */
    public void setImageoid(String imageoid) {
/* 25 */
        this.imageoid = imageoid;
/*    */
    }

    /*    */
/*    */
    public String getName() {
/* 29 */
        return this.name;
/*    */
    }

    /*    */
/*    */
    public void setName(String name) {
/* 33 */
        this.name = name;
/*    */
    }

    /*    */
/*    */
    public String getUrl() {
/* 37 */
        return this.url;
/*    */
    }

    /*    */
/*    */
    public void setUrl(String url) {
/* 41 */
        this.url = url;
/*    */
    }
/*    */
}


/* Location:              G:\zy18.jar!\cc\zhanyun\model\location\Images.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */