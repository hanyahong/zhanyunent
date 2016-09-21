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
@RequestMapping(value = {"/public"}, produces = {"application/json"})
@Api(value = "/public", description = "the public API")
public class PublicApi {
    @Autowired
    private UserServiceImpl service;

    /**
     * 用户注册
     *
     * @param uservo
     * @return
     */
    @ApiOperation(value = "用户注册", notes = "用户注册", response = UserReturnInfo.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "注册成功", response = UserReturnInfo.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "注册失败", response = Void.class)})
    @RequestMapping(value = {"/register"}, produces = {"application/json"}, consumes = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<UserReturnInfo> userRegPost(
            @ApiParam("注册信息") @RequestBody UserVO uservo) {
        UserReturnInfo in = null;

        in = this.service.userRegister(uservo);

        return new ResponseEntity(in, HttpStatus.OK);
    }

    /**
     * 用户登陆
     *
     * @param user
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "用户登录", notes = "用户登录", response = UserReturnInfo.class, responseContainer = "List")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = UserReturnInfo.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = UserReturnInfo.class)})
    @RequestMapping(value = {"/login"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<UserReturnInfo> userLoginGet(
            @ApiParam("登录信息") @RequestBody UserVO user)
            throws NotFoundException {
        UserReturnInfo in = this.service.userLogin(user);
        return new ResponseEntity(in, HttpStatus.OK);
    }

    /**
     * 用户手机验证
     *
     * @param phone
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "用户手机验证", notes = "用户手机验证", response = UserReturnVerify.class, responseContainer = "List")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = UserReturnVerify.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = UserReturnVerify.class)})
    @RequestMapping(value = {"/phoneVerfication/{phone}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public UserReturnVerify phonePost(
            @ApiParam("手机号码") @PathVariable("phone") String phone)
            throws NotFoundException {
        return this.service.verifyUserByPhone(phone);
    }

    /**
     * 用户邮箱验证
     *
     * @param user
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "用户邮箱验证", notes = "用户邮箱验证", response = UserReturnVerify.class, responseContainer = "List")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = UserReturnVerify.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = UserReturnVerify.class)})
    @RequestMapping(value = {"/emailVerfication"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public UserReturnVerify emailPost(@ApiParam("登录信息") @RequestBody UserVO user)
            throws NotFoundException {
        return this.service.verifyUserByEmail(user.getPhone());
    }

    /**
     * 用户名验证
     *
     * @param user
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "用户用户名验证", notes = "用户用户名验证", response = UserReturnVerify.class, responseContainer = "List")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = UserReturnVerify.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = UserReturnVerify.class)})
    @RequestMapping(value = {"/usernameVerfication"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public UserReturnVerify usernamePost(@ApiParam("登录信息") @RequestBody UserVO user)
            throws NotFoundException {
        return this.service.verifyUserByUsername(user.getPhone());
    }


    /**
     * 手机短信验证
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "用户注册手机号码短信验证", notes = "用户注册手机号码短信验证", response = Void.class)
    @RequestMapping(value = {"/sendSMS"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<Info> sendSMSPost(
            @ApiParam("用户信息") @RequestBody UserVO user) {
        Info in = this.service.verifySmsCode(user.getCode(), user.getPhone());
        return new ResponseEntity(in, HttpStatus.OK);
    }

    /**
     * 验证验证码(忘记密码)
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "验证验证码(忘记密码)", notes = "验证验证码(忘记密码)", response = Info.class)
    @RequestMapping(value = {"/forgetPwd"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<Info> sendSMSForgetPost(
            @ApiParam("用户信息") @RequestBody UserVO user) {
        Info in = this.service.forgetPwdSmsVerify(user.getCode(), user.getPhone());
        return new ResponseEntity(in, HttpStatus.OK);
    }

    /**
     * 获取验证码(忘记密码)
     *
     * @param phone
     * @return
     */
    @ApiOperation(value = "忘记密码时验证手机", notes = "忘记密码时验证手机", response = UserReturnVerify.class, responseContainer = "List")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = UserReturnVerify.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = UserReturnVerify.class)})
    @RequestMapping(value = {"/forgetPwd/{phone}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public Info SendphoneForgetPwdPost(
            @ApiParam("手机号码") @PathVariable("phone") String phone) {

        Info in = this.service.forgetPwdPhoneVerity(phone);
        return in;
    }

}
