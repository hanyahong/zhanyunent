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
/*    */ public class Attachments
/*    */ {
    /* 15 */   private String name = null;
    /* 16 */   private String img = null;
    /* 17 */   private String oid = null;

    /*    */
/*    */ 
/*    */ 
/*    */
    @ApiModelProperty("图片名称")
/*    */
    @JsonProperty("name")
/*    */ public String getName()
/*    */ {
/* 25 */
        return this.name;
/*    */
    }

    /*    */
/* 28 */
    public void setName(String name) {
        this.name = name;
    }

    /*    */
/*    */ 
/*    */ 
/*    */ 
/*    */
    @ApiModelProperty("图片路径")
/*    */
    @JsonProperty("img")
/*    */ public String getImg()
/*    */ {
/* 37 */
        return this.img;
/*    */
    }

    /*    */
/* 40 */
    public void setImg(String img) {
        this.img = img;
    }

    /*    */
/*    */ 
/*    */ 
/*    */ 
/*    */
    @ApiModelProperty("图片ID")
/*    */
    @JsonProperty("oid")
/*    */ public String getOid()
/*    */ {
/* 49 */
        return this.oid;
/*    */
    }

    /*    */
/* 52 */
    public void setOid(String oid) {
        this.oid = oid;
    }

    /*    */
/*    */ 
/*    */ 
/*    */
    public boolean equals(Object o)
/*    */ {
/* 58 */
        if (this == o) {
/* 59 */
            return true;
/*    */
        }
/* 61 */
        if ((o == null) || (getClass() != o.getClass())) {
/* 62 */
            return false;
/*    */
        }
/* 64 */
        Attachments attachments = (Attachments) o;
/*    */     
/*    */ 
/* 67 */
        return (Objects.equals(this.name, attachments.name)) && (Objects.equals(this.img, attachments.img)) && (Objects.equals(this.oid, attachments.oid));
/*    */
    }

    /*    */
/*    */
    public int hashCode()
/*    */ {
/* 72 */
        return Objects.hash(new Object[]{this.name, this.img, this.oid});
/*    */
    }

    /*    */
/*    */
    public String toString()
/*    */ {
/* 77 */
        StringBuilder sb = new StringBuilder();
/* 78 */
        sb.append("class Attachments {\n");
/*    */     
/* 80 */
        sb.append("  name: ").append(this.name).append("\n");
/* 81 */
        sb.append("  img: ").append(this.img).append("\n");
/* 82 */
        sb.append("  oid: ").append(this.oid).append("\n");
/* 83 */
        sb.append("}\n");
/* 84 */
        return sb.toString();
/*    */
    }
/*    */
}


/* Location:              G:\zy18.jar!\cc\zhanyun\model\project\Attachments.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */