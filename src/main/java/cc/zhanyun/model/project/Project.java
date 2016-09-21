
package cc.zhanyun.model.project;


import cc.zhanyun.model.file.FileManager;
import cc.zhanyun.model.location.Location;

import java.util.ArrayList;
import java.util.List;


public class Project {

    @org.springframework.data.annotation.Id
    private String oid;//项目唯一id
    private String group;//项目分组
    private String name;//项目名称
    private String py;//项目拼音
    private String progress;//项目进度
    private String status;//状态
    private Location location;//场地信息
    private Company company;//项目公司信息
    private cc.zhanyun.model.client.Clientmanager clientmanager;//项目客户信息
    private String prepareplantime;//项目预进场时间
    private String preparetime;//项目进场时间
    private String startplantime;//项目开始时间
    private String starttime;//项目开始时间
    private String leaveplantime;//项目预计结束时间
    private String leavetime;//项目结束时间
    private Creator creator;//项目创建者
    private String createtime;//项目创建时间
    private String uid;//项目归属者
    private String imageOid;//项目图片库id
    private String description;//项目描述
    private String requirement;//项目要求 文字
    private List<FileManager> file = new ArrayList<FileManager>();//项目附件

    public List<FileManager> getFile() {
        return file;
    }

    public void setFile(List<FileManager> file) {
        this.file = file;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getImageOid() {

        return this.imageOid;

    }


    public void setImageOid(String imageOid) {

        this.imageOid = imageOid;

    }


    public String getUid() {

        return this.uid;

    }


    public void setUid(String uid) {

        this.uid = uid;

    }


    public String getOid() {

        return this.oid;

    }


    public void setOid(String oid) {

        this.oid = oid;

    }


    public String getGroup() {

        return this.group;

    }


    public void setGroup(String group) {

        this.group = group;

    }


    public String getName() {

        return this.name;

    }


    public void setName(String name) {

        this.name = name;

    }


    public String getPy() {

        return this.py;

    }


    public void setPy(String py) {

        this.py = py;

    }


    public String getProgress() {

        return this.progress;

    }


    public void setProgress(String progress) {

        this.progress = progress;

    }


    public String getStatus() {

        return this.status;

    }


    public void setStatus(String status) {

        this.status = status;

    }


    public Location getLocation() {

        return this.location;

    }


    public void setLocation(Location location) {

        this.location = location;

    }


    public Company getCompany() {

        return this.company;

    }


    public void setCompany(Company company) {

        this.company = company;

    }


    public cc.zhanyun.model.client.Clientmanager getClientmanager() {

        return this.clientmanager;

    }


    public void setClientmanager(cc.zhanyun.model.client.Clientmanager clientmanager) {

        this.clientmanager = clientmanager;

    }


    public String getPrepareplantime() {

        return this.prepareplantime;

    }


    public void setPrepareplantime(String prepareplantime) {

        this.prepareplantime = prepareplantime;

    }


    public String getPreparetime() {

        return this.preparetime;

    }


    public void setPreparetime(String preparetime) {

        this.preparetime = preparetime;

    }


    public String getStartplantime() {

        return this.startplantime;

    }


    public void setStartplantime(String startplantime) {

        this.startplantime = startplantime;

    }


    public String getStarttime() {

        return this.starttime;

    }


    public void setStarttime(String starttime) {

        this.starttime = starttime;

    }


    public String getLeaveplantime() {

        return this.leaveplantime;

    }


    public void setLeaveplantime(String leaveplantime) {

        this.leaveplantime = leaveplantime;

    }


    public String getLeavetime() {

        return this.leavetime;

    }


    public void setLeavetime(String leavetime) {

        this.leavetime = leavetime;

    }


    public Creator getCreator() {

        return this.creator;

    }


    public void setCreator(Creator creator) {

        this.creator = creator;

    }


    public String getCreatetime() {

        return this.createtime;

    }


    public void setCreatetime(String createtime) {

        this.createtime = createtime;

    }


    public String getDescription() {

        return this.description;

    }


    public void setDescription(String description) {

        this.description = description;

    }

}


