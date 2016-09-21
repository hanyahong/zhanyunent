/*    */
package cc.zhanyun.model;
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
/*    */ public class Error
/*    */ {
    /* 15 */   private Integer code = null;
    /* 16 */   private String message = null;
    /* 17 */   private String fields = null;

    /*    */
/*    */ 
/*    */
    @ApiModelProperty("")
/*    */
    @JsonProperty("code")
/*    */ public Integer getCode()
/*    */ {
/* 24 */
        return this.code;
/*    */
    }

    /*    */
/* 27 */
    public void setCode(Integer code) {
        this.code = code;
    }

    /*    */
/*    */ 
/*    */ 
/*    */
    @ApiModelProperty("")
/*    */
    @JsonProperty("message")
/*    */ public String getMessage()
/*    */ {
/* 35 */
        return this.message;
/*    */
    }

    /*    */
/* 38 */
    public void setMessage(String message) {
        this.message = message;
    }

    /*    */
/*    */ 
/*    */ 
/*    */
    @ApiModelProperty("")
/*    */
    @JsonProperty("fields")
/*    */ public String getFields()
/*    */ {
/* 46 */
        return this.fields;
/*    */
    }

    /*    */
/* 49 */
    public void setFields(String fields) {
        this.fields = fields;
    }

    /*    */
/*    */ 
/*    */ 
/*    */
    public boolean equals(Object o)
/*    */ {
/* 55 */
        if (this == o) {
/* 56 */
            return true;
/*    */
        }
/* 58 */
        if ((o == null) || (getClass() != o.getClass())) {
/* 59 */
            return false;
/*    */
        }
/* 61 */
        Error error = (Error) o;
/*    */     
/*    */ 
/* 64 */
        return (Objects.equals(this.code, error.code)) && (Objects.equals(this.message, error.message)) && (Objects.equals(this.fields, error.fields));
/*    */
    }

    /*    */
/*    */
    public int hashCode()
/*    */ {
/* 69 */
        return Objects.hash(new Object[]{this.code, this.message, this.fields});
/*    */
    }

    /*    */
/*    */
    public String toString()
/*    */ {
/* 74 */
        StringBuilder sb = new StringBuilder();
/* 75 */
        sb.append("class Error {\n");
/*    */     
/* 77 */
        sb.append("  code: ").append(this.code).append("\n");
/* 78 */
        sb.append("  message: ").append(this.message).append("\n");
/* 79 */
        sb.append("  fields: ").append(this.fields).append("\n");
/* 80 */
        sb.append("}\n");
/* 81 */
        return sb.toString();
/*    */
    }
/*    */
}


/* Location:              G:\zy18.jar!\cc\zhanyun\model\Error.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */