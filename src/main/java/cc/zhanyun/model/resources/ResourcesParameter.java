/*    */
package cc.zhanyun.model.resources;
/*    */ 
/*    */

import com.fasterxml.jackson.annotation.JsonProperty;
/*    */ import io.swagger.annotations.ApiModel;
/*    */ import io.swagger.annotations.ApiModelProperty;
/*    */ import java.util.Objects;
/*    */ import org.springframework.data.annotation.Id;

/*    */
/*    */ 
/*    */ 
/*    */
@ApiModel(description = "")
/*    */ public class ResourcesParameter
/*    */ {
    /*    */
    @Id
/* 15 */ private String oid = null;
    /*    */
/* 17 */   private String name = null;
    /* 18 */   private String value = null;

    /*    */
/*    */ 
/*    */ 
/*    */
    @ApiModelProperty("资源属性ID")
/*    */
    @JsonProperty("oid")
/*    */ public String getOid()
/*    */ {
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
/*    */ 
/*    */
    @ApiModelProperty("资源属性名称")
/*    */
    @JsonProperty("name")
/*    */ public String getName()
/*    */ {
/* 39 */
        return this.name;
/*    */
    }

    /*    */
/*    */
    public void setName(String name) {
/* 43 */
        this.name = name;
/*    */
    }

    /*    */
/*    */ 
/*    */ 
/*    */
    @ApiModelProperty("资源属性值")
/*    */
    @JsonProperty("value")
/*    */ public String getValue()
/*    */ {
/* 52 */
        return this.value;
/*    */
    }

    /*    */
/*    */
    public void setValue(String value) {
/* 56 */
        this.value = value;
/*    */
    }

    /*    */
/*    */
    public boolean equals(Object o)
/*    */ {
/* 61 */
        if (this == o) {
/* 62 */
            return true;
/*    */
        }
/* 64 */
        if ((o == null) || (getClass() != o.getClass())) {
/* 65 */
            return false;
/*    */
        }
/* 67 */
        ResourcesParameter resourcesParameter = (ResourcesParameter) o;
/*    */     
/*    */ 
/* 70 */
        return (Objects.equals(this.oid, resourcesParameter.oid)) && (Objects.equals(this.name, resourcesParameter.name)) && (Objects.equals(this.value, resourcesParameter.value));
/*    */
    }

    /*    */
/*    */
    public int hashCode()
/*    */ {
/* 75 */
        return Objects.hash(new Object[]{this.oid, this.name, this.value});
/*    */
    }

    /*    */
/*    */
    public String toString()
/*    */ {
/* 80 */
        StringBuilder sb = new StringBuilder();
/* 81 */
        sb.append("class ResoucesParameter {\n");
/*    */     
/* 83 */
        sb.append("  oid: ").append(this.oid).append("\n");
/* 84 */
        sb.append("  name: ").append(this.name).append("\n");
/* 85 */
        sb.append("  value: ").append(this.value).append("\n");
/* 86 */
        sb.append("}\n");
/* 87 */
        return sb.toString();
/*    */
    }
/*    */
}


/* Location:              G:\zy18.jar!\cc\zhanyun\model\resources\ResourcesParameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */