/*    */
package cc.zhanyun.model.resources;
/*    */ 
/*    */

import io.swagger.annotations.ApiModel;
/*    */ import java.util.List;
/*    */ import org.springframework.data.annotation.Id;

/*    */
/*    */ 
/*    */
@ApiModel(description = "")
/*    */ public class ResourcesTypes
/*    */ {
    /*    */
    @Id
/* 12 */ private String oid = null;
    /*    */
/* 14 */   private List<ResourcesTypeOne> resourcesoid = null;
    /*    */   private String uid;

    /*    */
/*    */
    public String getUid() {
/* 18 */
        return this.uid;
/*    */
    }

    /*    */
/*    */
    public void setUid(String uid) {
/* 22 */
        this.uid = uid;
/*    */
    }

    /*    */
/*    */
    public String getOid() {
/* 26 */
        return this.oid;
/*    */
    }

    /*    */
/*    */
    public void setOid(String oid) {
/* 30 */
        this.oid = oid;
/*    */
    }

    /*    */
/*    */
    public List<ResourcesTypeOne> getResourcesoid() {
/* 34 */
        return this.resourcesoid;
/*    */
    }

    /*    */
/*    */
    public void setResourcesoid(List<ResourcesTypeOne> resourcesoid) {
/* 38 */
        this.resourcesoid = resourcesoid;
/*    */
    }
/*    */
}


/* Location:              G:\zy18.jar!\cc\zhanyun\model\resources\ResourcesTypes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */