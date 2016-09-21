package cc.zhanyun.model.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;


@ApiModel(description = "")
public class Resources {
    @Id
    private String oid = null;

    private String name = null;
    private String classification = null;
    private String simplename = null;
    private Integer amount = null;
    private String unit = null;
    private BigDecimal unitprice = null;
    private String remark = null;
    private List<ResourcesParameter> parameter = new ArrayList();
    private String images;
    private String uid;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImages() {
        return this.images;
    }

    public void setImages(String images) {
        this.images = images;
    }


    @ApiModelProperty("资源ID")
    @JsonProperty("oid")
    public String getOid() {
        return this.oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }


    @ApiModelProperty("资源名称")
    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ApiModelProperty("资源分类")
    @JsonProperty("classification")
    public String getClassification() {
        return this.classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
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


    @ApiModelProperty("资源单价")
    @JsonProperty("unitprice")
    public BigDecimal getUnitprice() {
        return this.unitprice;
    }

    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }


    @ApiModelProperty("资源备注")
    @JsonProperty("remark")
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    @ApiModelProperty("")
    @JsonProperty("parameter")
    public List<ResourcesParameter> getParameter() {
        return this.parameter;
    }

    public void setParameter(List<ResourcesParameter> parameter) {
        this.parameter = parameter;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        Resources resources = (Resources) o;


        return (Objects.equals(this.oid, resources.oid)) && (Objects.equals(this.name, resources.name)) && (Objects.equals(this.classification, resources.classification)) && (Objects.equals(this.simplename, resources.simplename)) && (Objects.equals(this.amount, resources.amount)) && (Objects.equals(this.unit, resources.unit)) && (Objects.equals(this.unitprice, resources.unitprice)) && (Objects.equals(this.remark, resources.remark)) && (Objects.equals(this.parameter, resources.parameter));
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.oid, this.name, this.classification, this.simplename, this.amount, this.unit, this.unitprice, this.remark, this.parameter});
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Resources {\n");

        sb.append("  oid: ").append(this.oid).append("\n");
        sb.append("  name: ").append(this.name).append("\n");
        sb.append("  classification: ").append(this.classification).append("\n");
        sb.append("  simplename: ").append(this.simplename).append("\n");
        sb.append("  amount: ").append(this.amount).append("\n");
        sb.append("  unit: ").append(this.unit).append("\n");
        sb.append("  unitprice: ").append(this.unitprice).append("\n");
        sb.append("  remark: ").append(this.remark).append("\n");
        sb.append("  parameter: ").append(this.parameter).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}


