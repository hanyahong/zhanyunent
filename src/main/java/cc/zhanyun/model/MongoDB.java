/*    */
package cc.zhanyun.model;

/*    */
/*    */ public class MongoDB {
    /*  4 */   private String ip = "";
    /*    */
/*  6 */   private String port = "";
    /*    */
/*  8 */   private String defaultDB = "admin";
    /*    */
/* 10 */   private String defaultCol = "";

    /*    */
/*    */
    public String getIp() {
/* 13 */
        return this.ip;
/*    */
    }

    /*    */
/*    */
    public void setIp(String ip) {
/* 17 */
        this.ip = ip;
/*    */
    }

    /*    */
/*    */
    public String getPort() {
/* 21 */
        return this.port;
/*    */
    }

    /*    */
/*    */
    public void setPort(String port) {
/* 25 */
        this.port = port;
/*    */
    }

    /*    */
/*    */
    public String getDefaultDB() {
/* 29 */
        return this.defaultDB;
/*    */
    }

    /*    */
/*    */
    public void setDefaultDB(String defaultDB) {
/* 33 */
        this.defaultDB = defaultDB;
/*    */
    }

    /*    */
/*    */
    public String getDefaultCol() {
/* 37 */
        return this.defaultCol;
/*    */
    }

    /*    */
/*    */
    public void setDefaultCol(String defaultCol) {
/* 41 */
        this.defaultCol = defaultCol;
/*    */
    }
/*    */
}


/* Location:              G:\zy18.jar!\cc\zhanyun\model\MongoDB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */