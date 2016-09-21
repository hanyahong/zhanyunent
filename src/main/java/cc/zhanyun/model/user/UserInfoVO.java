package cc.zhanyun.model.user;

import org.springframework.data.annotation.Id;

/**
 * Created by hyh on 16-9-1.
 */
public class UserInfoVO {


    @Id
    private String oid;
    private String name;
    private String email;
    private String phone;
    private String sex;
    private String company;
    private String companyengname;
    private String dept;
    private String job;
    private String website;
    private String qq;
    private String username;
    private String age;
    private String userintroduction;
    private String userimage;

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public String getUserintroduction() {
        return userintroduction;
    }

    public void setUserintroduction(String userintroduction) {
        this.userintroduction = userintroduction;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyengname() {
        return this.companyengname;
    }

    public void setCompanyengname(String companyengname) {
        this.companyengname = companyengname;
    }

    public String getDept() {
        return this.dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


}