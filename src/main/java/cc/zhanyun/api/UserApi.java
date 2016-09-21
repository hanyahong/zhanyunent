package cc.zhanyun.api;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.user.*;
import cc.zhanyun.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = {"/user"}, produces = {"application/json"})
@Api(value = "/user", description = "the register API")
public class UserApi {
    @Autowired
    private UserServiceImpl service;


    /**
     * 修改用户信息
     *
     * @param uivo
     * @return
     */
    @ApiOperation(value = "修改用户信息", notes = "修改单条用户信息", response = Void.class)
    @RequestMapping(value = {"/userinfo"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public ResponseEntity<Info> userInfoPut(
            @ApiParam("用户详细信息") @RequestBody UserInfoVO uivo) {
        Info info = this.service.updateInfo(uivo);
        return new ResponseEntity(info, HttpStatus.OK);
    }

    /**
     * 修改用户基本信息(PC)
     *
     * @param uivo
     * @return
     */
    @ApiOperation(value = "修改用户信息(PC)", notes = "修改单条用户信息(PC)", response = Void.class)
    @RequestMapping(value = {"/userbaseinfo"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public ResponseEntity<Info> userBaseInfoPut(
            @ApiParam("用户详细信息") @RequestBody UserInfoVO uivo) {
        Info info = this.service.updateUserBaseInfoService(uivo);
        return new ResponseEntity(info, HttpStatus.OK);
    }

    /**
     * 修改用户公司信息(PC)
     *
     * @param userCompany
     * @return
     */
    @ApiOperation(value = "修改用户公司信息(PC)", notes = "修改用户公司信息(PC)", response = Void.class)
    @RequestMapping(value = {"/usercompany"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public ResponseEntity<Info> userCompanyPut(

            @ApiParam("用户详细信息") @RequestBody UserCompany userCompany) {
        Info info = this.service.updateUserCompany(userCompany);
        return new ResponseEntity(info, HttpStatus.OK);
    }

    /**
     * 查询用户基本资料信息
     *
     * @param oid
     * @return
     */
    @ApiOperation(value = "查询用户基本资料信息(app 1.0)", notes = "查询用户基本资料信息(app1.0)", response = Void.class, responseContainer = "List")
    @RequestMapping(value = {"/userinfo/{oid}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public UserInfoVO userInfoPost(
            @ApiParam("用户id") @PathVariable("oid") String oid) {
        return this.service.selUserInfo(oid);
    }

    /**
     * 查询用户公司信息(PC)
     *
     * @param oid
     * @return
     */
    @ApiOperation(value = "查询用户公司信息(PC)", notes = "查询用户公司信息(PC)", response = Void.class, responseContainer = "List")
    @RequestMapping(value = {"/usercompany/{oid}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public UserCompany userCompanyPost(
            @ApiParam("用户id") @PathVariable("oid") String oid) {
        return this.service.selUserCompanyService(oid);
    }

    /**
     * 修改用户密码
     *
     * @param up
     * @return
     */
    @ApiOperation(value = "修改用户密码", notes = "修改单条用户密码", response = Void.class, responseContainer = "List")
    @RequestMapping(value = {"/userpwd"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<Info> userPasswordPost(
            @ApiParam("用户详细信息") @RequestBody UserPasswordModify up) {
        Info info = this.service.updatePassword(up);
        return new ResponseEntity(info, HttpStatus.OK);
    }


    /**
     * 上传头像
     *
     * @param oid
     * @param file
     * @return
     */
    @ApiOperation(value = "上传头像", notes = "上传头像", response = Info.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Info.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = cc.zhanyun.model.Error.class)})
    @RequestMapping(value = {"/upload/{oid}"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<Info> userImageUpload(
            @ApiParam(value = "用户ID", required = true) @PathVariable("oid") String oid,
            MultipartFile file) {
        Info in = this.service.uploadImage(oid, file);
        return new ResponseEntity<Info>(in, HttpStatus.OK);
    }

    /**
     * 忘记密码
     *
     * @param up
     * @return
     */
    @ApiOperation(value = "忘记密码", notes = "忘记密码", response = Void.class, responseContainer = "List")
    @RequestMapping(value = {"/forgetuserpwd"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<Info> forgetUserPasswordPost(
            @ApiParam("用户详细信息") @RequestBody UserPasswordModify up) {
        Info info = this.service.forgetPassword(up);
        return new ResponseEntity<Info>(info, HttpStatus.OK);
    }

    /**
     * 修改手机号
     *
     * @param userVO
     * @return
     */
    @ApiOperation(value = "修改手机号", notes = "修改手机号", response = Info.class)
    @RequestMapping(value = {"/update/phone"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public ResponseEntity<Info> updateUserPhonePut(
            @ApiParam("信息") @RequestBody UserVO userVO) {
        Info info = this.service.updateUserPhone(userVO);
        return new ResponseEntity<Info>(info, HttpStatus.OK);
    }

}
