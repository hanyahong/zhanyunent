
package cc.zhanyun.api;


import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class ApiResponseMessage {
    public static final int ERROR = 1;
    public static final int WARNING = 2;
    public static final int INFO = 3;
    public static final int OK = 4;
    public static final int TOO_BUSY = 5;
    int code;
    String type;
    String message;


    public ApiResponseMessage() {
    }


    public ApiResponseMessage(int code, String message) {

        this.code = code;

        switch (code) {

            case 1:

                setType("error");

                break;

            case 2:

                setType("warning");

                break;

            case 3:

                setType("info");

                break;

            case 4:

                setType("ok");

                break;

            case 5:

                setType("too busy");

                break;

            default:

                setType("unknown");

        }


        this.message = message;

    }


    @javax.xml.bind.annotation.XmlTransient
    public int getCode() {

        return this.code;

    }


    public void setCode(int code) {

        this.code = code;

    }


    public String getType() {

        return this.type;

    }


    public void setType(String type) {

        this.type = type;

    }


    public String getMessage() {

        return this.message;

    }


    public void setMessage(String message) {

        this.message = message;

    }

}


