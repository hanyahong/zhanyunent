/*    */
package cc.zhanyun.model.project;
/*    */ 
/*    */

import com.fasterxml.jackson.annotation.JsonProperty;
/*    */ import io.swagger.annotations.ApiModel;
/*    */ import io.swagger.annotations.ApiModelProperty;
/*    */ import java.util.Objects;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */
@ApiModel(description = "")
/*    */ public class Creator
/*    */ {
    /* 15 */   private String oid = null;
    /* 16 */   private String name = null;

    /*    */
/*    */ 
/*    */ 
/*    */
    @ApiModelProperty("创建者ID")
/*    */
    @JsonProperty("oid")
/*    */ public String getOid()
/*    */ {
/* 24 */
        return this.oid;
/*    */
    }

    /*    */
/* 27 */
    public void setOid(String oid) {
        this.oid = oid;
    }

    /*    */
/*    */ 
/*    */ 
/*    */ 
/*    */
    @ApiModelProperty("创建者姓名")
/*    */
    @JsonProperty("name")
/*    */ public String getName()
/*    */ {
/* 36 */
        return this.name;
/*    */
    }

    /*    */
/* 39 */
    public void setName(String name) {
        this.name = name;
    }

    /*    */
/*    */ 
/*    */ 
/*    */
    public boolean equals(Object o)
/*    */ {
/* 45 */
        if (this == o) {
/* 46 */
            return true;
/*    */
        }
/* 48 */
        if ((o == null) || (getClass() != o.getClass())) {
/* 49 */
            return false;
/*    */
        }
/* 51 */
        Creator creator = (Creator) o;
/*    */     
/* 53 */
        return (Objects.equals(this.oid, creator.oid)) && (Objects.equals(this.name, creator.name));
/*    */
    }

    /*    */
/*    */
    public int hashCode()
/*    */ {
/* 58 */
        return Objects.hash(new Object[]{this.oid, this.name});
/*    */
    }

    /*    */
/*    */
    public String toString()
/*    */ {
/* 63 */
        StringBuilder sb = new StringBuilder();
/* 64 */
        sb.append("class Creator {\n");
/*    */     
/* 66 */
        sb.append("  oid: ").append(this.oid).append("\n");
/* 67 */
        sb.append("  name: ").append(this.name).append("\n");
/* 68 */
        sb.append("}\n");
/* 69 */
        return sb.toString();
/*    */
    }
/*    */
}


/* Location:              G:\zy18.jar!\cc\zhanyun\model\project\Creator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */