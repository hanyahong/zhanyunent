
package cc.zhanyun.model.vo;


import ch.qos.logback.core.net.server.Client;


public class ProjectVO {

    @org.springframework.data.annotation.Id
    private String oid;
    private String name;
    private String address;
    private Integer status;
    private Client client;


    public String getOid() {

        return this.oid;

    }


    public void setOid(String oid) {

        this.oid = oid;

    }


    public String getName() {

        return this.name;

    }


    public void setName(String name) {

        this.name = name;

    }


    public String getAddress() {

        return this.address;

    }


    public void setAddress(String address) {

        this.address = address;

    }


    public Integer getStatus() {

        return this.status;

    }


    public void setStatus(Integer status) {

        this.status = status;

    }


    public Client getClient() {

        return this.client;

    }


    public void setClient(Client client) {

        this.client = client;

    }

}


