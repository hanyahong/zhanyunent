package cc.zhanyun.model.user;

import org.springframework.data.annotation.Id;

public class UserAccount {
    //用户账户信息
    @Id
    private String oid;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String token;
    private String createtime;
    private String updatetime;
    private String qq;
    //用户基本信息
    //姓名
    private String name;
    //性别
    private String sex;
    //部门
    private String dept;
    //岗位
    private String job;
    //头像
    private String userimage;
    //年龄
    private String age;
    //
    private String userintroduction;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUserintroduction() {
        return userintroduction;
    }

    public void setUserintroduction(String userintroduction) {
        this.userintroduction = userintroduction;
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


