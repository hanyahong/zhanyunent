/*    */
package cc.zhanyun.model.vo;
/*    */ 
/*    */

import com.fasterxml.jackson.annotation.JsonProperty;
/*    */ import io.swagger.annotations.ApiModel;
/*    */ import io.swagger.annotations.ApiModelProperty;
/*    */ import org.springframework.data.annotation.Id;

/*    */
/*    */ 
/*    */
@ApiModel(description = "")
/*    */ public class ResourcesVO
/*    */ {
    /*    */
    @Id
/*    */ private String oid;
    /*    */   private String simplename;
    /*    */   private String classification;

    /*    */
/*    */
    @ApiModelProperty("ID")
/*    */
    @JsonProperty("oid")
/*    */ public String getOid()
/*    */ {
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
    @ApiModelProperty("名称")
/*    */
    @JsonProperty("simplename")
/*    */ public String getSimplename() {
/* 31 */
        return this.simplename;
/*    */
    }

    /*    */
/*    */
    public void setSimplename(String simplename) {
/* 35 */
        this.simplename = simplename;
/*    */
    }

    /*    */
/*    */
    @ApiModelProperty("分类")
/*    */
    @JsonProperty("classification")
/*    */ public String getClassification() {
/* 41 */
        return this.classification;
/*    */
    }

    /*    */
/*    */
    public void setClassification(String classification) {
/* 45 */
        this.classification = classification;
/*    */
    }
/*    */
}


/* Location:              G:\zy18.jar!\cc\zhanyun\model\vo\ResourcesVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */