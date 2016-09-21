package cc.zhanyun.model.offer;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;


@ApiModel(description = "")
public class Selectedresources {
    private String name = null;
    private String simplename = null;
    private Integer amount = null;
    private String unit = null;
    private Integer days = null;
    private double unitprice;
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
    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
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
    public Integer getDays() {
        return this.days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }


    @ApiModelProperty("资源单价")
    @JsonProperty("unitprice")
    public double getUnitprice() {
        return this.unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        Selectedresources selectedresources = (Selectedresources) o;


        return (Objects.equals(this.name, selectedresources.name)) && (Objects.equals(this.simplename, selectedresources.simplename)) && (Objects.equals(this.amount, selectedresources.amount)) && (Objects.equals(this.unit, selectedresources.unit)) && (Objects.equals(this.days, selectedresources.days)) && (Objects.equals(Double.valueOf(this.unitprice), Double.valueOf(selectedresources.unitprice)));
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name, this.simplename, this.amount, this.unit, this.days, Double.valueOf(this.unitprice)});
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


