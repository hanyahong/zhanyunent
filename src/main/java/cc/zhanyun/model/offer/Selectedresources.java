package cc.zhanyun.model.offer;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "")
public class Selectedresources {
    private String name = null;
    private String simplename = null;
    private Double amount = null;
    private String unit = null;
    private Double days = null;
    private String unitprice;
    private String subTotal;
    private String specifications;//规格
    private String remarks;//备注

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSubTotal() {
        return this.subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }


    @ApiModelProperty("资源名称")
    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ApiModelProperty("资源简称")
    @JsonProperty("simplename")
    public String getSimplename() {
        return this.simplename;
    }

    public void setSimplename(String simplename) {
        this.simplename = simplename;
    }


    @ApiModelProperty("资源数量")
    @JsonProperty("amount")
    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    @ApiModelProperty("资源单位")
    @JsonProperty("unit")
    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    @ApiModelProperty("资源天数")
    @JsonProperty("days")
    public Double getDays() {
        return this.days;
    }

    public void setDays(Double days) {
        this.days = days;
    }


    @ApiModelProperty("资源单价")
    @JsonProperty("unitprice")
    public String getUnitprice() {
        return this.unitprice;
    }

    public void setUnitprice(String unitprice) {
        this.unitprice = unitprice;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Selectedresources {\n");

        sb.append("  name: ").append(this.name).append("\n");
        sb.append("  simplename: ").append(this.simplename).append("\n");
        sb.append("  amount: ").append(this.amount).append("\n");
        sb.append("  unit: ").append(this.unit).append("\n");
        sb.append("  days: ").append(this.days).append("\n");
        sb.append("  unitprice: ").append(this.unitprice).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}


