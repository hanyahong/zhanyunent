package cc.zhanyun.model.user;

/**
 * Created by hyh on 16-9-1.
 */
public class UserBaseInfo {
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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
}
