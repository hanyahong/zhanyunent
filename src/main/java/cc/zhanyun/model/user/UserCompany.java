package cc.zhanyun.model.user;

import org.springframework.data.annotation.Id;

/**
 * Created by hyh on 16-9-1.
 */
public class UserCompany {
    @Id
    private String oid;
    //用户公司名称
    private String company;
    //用户英文名称
    private String companyengname;
    //公司logo
    private String companylogo;
    //公司网址
    private String website;
    //公司电话
    private String tel;
    //公司地址
    private String address;
    //公司简介
    private String companyintroduction;
    //公司行业
    private String industry;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyengname() {
        return companyengname;
    }

    public void setCompanyengname(String companyengname) {
        this.companyengname = companyengname;
    }

    public String getCompanylogo() {
        return companylogo;
    }

    public void setCompanylogo(String companylogo) {
        this.companylogo = companylogo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyintroduction() {
        return companyintroduction;
    }

    public void setCompanyintroduction(String companyintroduction) {
        this.companyintroduction = companyintroduction;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
