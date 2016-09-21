/*    */
package cc.zhanyun.api;
/*    */ 
/*    */

import javax.xml.bind.annotation.XmlRootElement;

/*    */
/*    */
@XmlRootElement
/*    */ public class ApiResponseMessage
/*    */ {
    /*    */   public static final int ERROR = 1;
    /*    */   public static final int WARNING = 2;
    /*    */   public static final int INFO = 3;
    /*    */   public static final int OK = 4;
    /*    */   public static final int TOO_BUSY = 5;
    /*    */ int code;
    /*    */ String type;
    /*    */ String message;

    /*    */
/*    */
    public ApiResponseMessage() {
    }

    /*    */
/*    */
    public ApiResponseMessage(int code, String message)
/*    */ {
/* 21 */
        this.code = code;
/* 22 */
        switch (code) {
/*    */
            case 1:
/* 24 */
                setType("error");
/* 25 */
                break;
/*    */
            case 2:
/* 27 */
                setType("warning");
/* 28 */
                break;
/*    */
            case 3:
/* 30 */
                setType("info");
/* 31 */
                break;
/*    */
            case 4:
/* 33 */
                setType("ok");
/* 34 */
                break;
/*    */
            case 5:
/* 36 */
                setType("too busy");
/* 37 */
                break;
/*    */
            default:
/* 39 */
                setType("unknown");
/*    */
        }
/*    */     
/* 42 */
        this.message = message;
/*    */
    }

    /*    */
/*    */
    @javax.xml.bind.annotation.XmlTransient
/*    */ public int getCode() {
/* 47 */
        return this.code;
/*    */
    }

    /*    */
/*    */
    public void setCode(int code) {
/* 51 */
        this.code = code;
/*    */
    }

    /*    */
/*    */
    public String getType() {
/* 55 */
        return this.type;
/*    */
    }

    /*    */
/*    */
    public void setType(String type) {
/* 59 */
        this.type = type;
/*    */
    }

    /*    */
/*    */
    public String getMessage() {
/* 63 */
        return this.message;
/*    */
    }

    /*    */
/*    */
    public void setMessage(String message) {
/* 67 */
        this.message = message;
/*    */
    }
/*    */
}


/* Location:              G:\zy18.jar!\cc\zhanyun\api\ApiResponseMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */