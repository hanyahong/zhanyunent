package cc.zhanyun.model.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

import org.springframework.data.annotation.Id;

@ApiModel(description = "")
public class Clientmanager {
    @Id
    private String oid = null;

    private String name = null;
    private String sex = null;
    private String company = null;
    private String dept = null;
    private String tel = null;
    private String email = null;
    private String industry = null;
    private String website = null;
    private String address = null;
    private String _abstract = null;
    private String username = null;
    private String image = null;
    private String uid = null;
    private String qq = null;
    private String remarks = null;
    private Integer status = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String get_abstract() {
        return this._abstract;
    }

    public void set_abstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ApiModelProperty("客户唯一编号")
    @JsonProperty("oid")
    public String getOid() {
        return this.oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    @ApiModelProperty("客户姓名")
    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty("客户性别")
    @JsonProperty("sex")
    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @ApiModelProperty("客户公司")
    @JsonProperty("company")
    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @ApiModelProperty("客户部门")
    @JsonProperty("dept")
    public String getDept() {
        return this.dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @ApiModelProperty("客户电话")
    @JsonProperty("tel")
    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @ApiModelProperty("客户邮件")
    @JsonProperty("email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ApiModelProperty("客户行业")
    @JsonProperty("industry")
    public String getIndustry() {
        return this.industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @ApiModelProperty("客户网站")
    @JsonProperty("website")
    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @ApiModelProperty("客户地址")
    @JsonProperty("address")
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ApiModelProperty("个人简介")
    @JsonProperty("abstract")
    public String getAbstract() {
        return this._abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        Clientmanager clientmanager = (Clientmanager) o;

        return (Objects.equals(this.oid, clientmanager.oid))
                && (Objects.equals(this.name, clientmanager.name))
                && (Objects.equals(this.sex, clientmanager.sex))
                && (Objects.equals(this.company, clientmanager.company))
                && (Objects.equals(this.dept, clientmanager.dept))
                && (Objects.equals(this.tel, clientmanager.tel))
                && (Objects.equals(this.email, clientmanager.email))
                && (Objects.equals(this.industry, clientmanager.industry))
                && (Objects.equals(this.website, clientmanager.website))
                && (Objects.equals(this.address, clientmanager.address))
                && (Objects.equals(this._abstract, clientmanager._abstract));
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.oid, this.name, this.sex,
                this.company, this.dept, this.tel, this.email, this.industry,
                this.website, this.address, this._abstract});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Clientmanager {\n");

        sb.append("  oid: ").append(this.oid).append("\n");
        sb.append("  name: ").append(this.name).append("\n");
        sb.append("  sex: ").append(this.sex).append("\n");
        sb.append("  company: ").append(this.company).append("\n");
        sb.append("  dept: ").append(this.dept).append("\n");
        sb.append("  tel: ").append(this.tel).append("\n");
        sb.append("  email: ").append(this.email).append("\n");
        sb.append("  industry: ").append(this.industry).append("\n");
        sb.append("  website: ").append(this.website).append("\n");
        sb.append("  address: ").append(this.address).append("\n");
        sb.append("  _abstract: ").append(this._abstract).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
