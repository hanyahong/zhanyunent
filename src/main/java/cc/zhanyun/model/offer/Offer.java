package cc.zhanyun.model.offer;

import cc.zhanyun.model.client.Clientmanager;
import cc.zhanyun.model.location.Houses;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;


@ApiModel(description = "")
public class Offer {
    @Id
    private String oid = null;

    private String name = null;
    private String address = null;
    private String content = null;
    private Clientmanager client = null;
    private String tax = null;

    private String benefit = null;
    private String total = null;
    private String createby = null;
    private String updatedtime = null;
    private Integer status = null;
    private List<Houses> houses = new ArrayList();
    private List<Resourcetypes> resourcetypes = new ArrayList();
    private String totalnotax;
    private String discount;
    private String proportion;
    private String taxation;
    private String totaltax;
    private String uid;


    public String getTaxation() {
        return this.taxation;
    }

    public void setTaxation(String taxation) {
        this.taxation = taxation;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTotalnotax() {
        return this.totalnotax;
    }

    public void setTotalnotax(String totalnotax) {
        this.totalnotax = totalnotax;
    }

    public String getDiscount() {
        return this.discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getProportion() {
        return this.proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getTotaltax() {
        return this.totaltax;
    }

    public void setTotaltax(String totaltax) {
        this.totaltax = totaltax;
    }

    public Clientmanager getClient() {
        return this.client;
    }

    public void setClient(Clientmanager client) {
        this.client = client;
    }


    @ApiModelProperty("报价单编号")
    @JsonProperty("oid")
    public String getOid() {
        return this.oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    @ApiModelProperty("报价单地址")
    @JsonProperty("address")
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @ApiModelProperty("报价单名称")
    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ApiModelProperty("报价单描述")
    @JsonProperty("content")
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @ApiModelProperty("报价含税部分")
    @JsonProperty("tax")
    public String getTax() {
        return this.tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }


    @ApiModelProperty("报价优惠部分")
    @JsonProperty("benefit")
    public String getBenefit() {
        return this.benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }


    @ApiModelProperty("报价总计，此值应该动态计算")
    @JsonProperty("total")
    public String getTotal() {
        return this.total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


    @ApiModelProperty("报价创建人")
    @JsonProperty("createby")
    public String getCreateby() {
        return this.createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }


    @ApiModelProperty("报价修改时间")
    @JsonProperty("updatedtime")
    public String getUpdatedtime() {
        return this.updatedtime;
    }

    public void setUpdatedtime(String updatedtime) {
        this.updatedtime = updatedtime;
    }


    @ApiModelProperty("报价状态：-报价中,-进行中,-已完成,-已废弃")
    @JsonProperty("status")
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    @ApiModelProperty("")
    @JsonProperty("houses")
    public List<Houses> getHouses() {
        return this.houses;
    }

    public void setHouses(List<Houses> houses) {
        this.houses = houses;
    }


    @ApiModelProperty("")
    @JsonProperty("resourcetypes")
    public List<Resourcetypes> getResourcetypes() {
        return this.resourcetypes;
    }

    public void setResourcetypes(List<Resourcetypes> resourcetypes) {
        this.resourcetypes = resourcetypes;
    }


}


