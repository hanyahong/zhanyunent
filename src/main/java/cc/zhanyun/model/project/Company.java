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
/*    */ public class Company
/*    */ {
    /* 15 */   private String name = null;

    /*    */
/*    */ 
/*    */ 
/*    */
    @ApiModelProperty("承包公司名称")
/*    */
    @JsonProperty("name")
/*    */ public String getName()
/*    */ {
/* 23 */
        return this.name;
/*    */
    }

    /*    */
/* 26 */
    public void setName(String name) {
        this.name = name;
    }

    /*    */
/*    */ 
/*    */ 
/*    */
    public boolean equals(Object o)
/*    */ {
/* 32 */
        if (this == o) {
/* 33 */
            return true;
/*    */
        }
/* 35 */
        if ((o == null) || (getClass() != o.getClass())) {
/* 36 */
            return false;
/*    */
        }
/* 38 */
        Company company = (Company) o;
/* 39 */
        return Objects.equals(this.name, company.name);
/*    */
    }

    /*    */
/*    */
    public int hashCode()
/*    */ {
/* 44 */
        return Objects.hash(new Object[]{this.name});
/*    */
    }

    /*    */
/*    */
    public String toString()
/*    */ {
/* 49 */
        StringBuilder sb = new StringBuilder();
/* 50 */
        sb.append("class Company {\n");
/*    */     
/* 52 */
        sb.append("  name: ").append(this.name).append("\n");
/* 53 */
        sb.append("}\n");
/* 54 */
        return sb.toString();
/*    */
    }
/*    */
}


/* Location:              G:\zy18.jar!\cc\zhanyun\model\project\Company.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */