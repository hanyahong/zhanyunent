package cc.zhanyun.service.impl;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.image.Image;
import cc.zhanyun.model.resources.ResourcesTypeOne;
import cc.zhanyun.model.resources.ResourcesTypes;
import cc.zhanyun.model.user.*;
import cc.zhanyun.repository.impl.UserRepoImpl;
import cc.zhanyun.repository.impl.UserSmsRepoImpl;
import cc.zhanyun.service.ImageService;
import cc.zhanyun.service.ResourceTypeService;
import cc.zhanyun.service.UserService;
import cc.zhanyun.util.RandomUtil;
import cc.zhanyun.util.TokenUtil;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepoImpl uri;
    @Autowired
    private UserSmsRepoImpl sms;
    @Autowired
    private ResourceTypeService resourceType;
    @Autowired
    private TokenUtil token;
    @Autowired
    private ImageService imageService;

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    public UserReturnInfo userRegister(UserVO user) {

        UserReturnVerify ver = null;
        String oid = RandomUtil.getRandomFileName();
        String imageOid = RandomUtil.getRandomFileName();
        user.setOid(oid);

        if (user.getUsername() != null) {
            ver = verifyUserByUsername(user.getUsername());
        } else if (user.getPhone() != null) {
            UserAccount u = this.uri.selUserByPhone(user.getPhone());
            UserReturnVerify v = new UserReturnVerify();
            if (u != null) {
                v.setInfo("该电话已被注册");
                ver = v;
            } else {
                v.setInfo("该电话可用");
                ver = v;
            }
        } else if (user.getEmail() != null) {
            ver = verifyUserByEmail(user.getEmail());
        }

        UserReturnInfo in = new UserReturnInfo();

        try {
            if ((ver.getInfo().equals("该用户名已被注册"))
                    || (ver.getInfo().equals("该邮箱已被注册"))
                    || (ver.getInfo().equals("该电话已被注册"))) {
                in.setStatus("该账户已注册，不可用");
            } else {
                //token
                String token = RandomUtil.getRandomFileName();
                UserAccount u = new UserAccount();
                u.setOid(oid);
                u.setPassword(user.getPassword());
                u.setPhone(user.getPhone());
                u.setEmail(user.getEmail());
                u.setUsername(user.getUsername());
                u.setUserimage(imageOid);

                u.setToken(token);

                this.uri.addUser(u);

                in.setStatus("注册成功");
                in.setOid(oid);
                in.setToken(u.getToken());
                //初始化默认资源分类
                this.resourceType.saveTypeOfOneUser(oid);

                Image image = new Image();
                image.setOid(imageOid);
                image.setUid(oid);

                this.imageService.saveImageService(image);
                // 返回
                in.setOid(oid);
                in.setPhone(user.getPhone());
                in.setImages(imageOid);
                // 暂时生成
                in.setToken(token);
                in.setStatus("注册成功");

            }
        } catch (Exception e) {
            in.setStatus("注册失败，不能输入空值");
        }
        return in;
    }

    /**
     * 验证用户名
     *
     * @param username
     * @return
     */
    public UserReturnVerify verifyUserByUsername(String username) {
        UserReturnVerify ver = new UserReturnVerify();
        UserAccount userAccount = this.uri.selUserByUsername(username);
        if (userAccount != null) {
            ver.setInfo("该用户名已被注册");
        } else {
            ver.setInfo("该用户可用");
        }
        return ver;
    }

    /**
     * 验证手机号
     */
    public UserReturnVerify verifyUserByPhone(String phone) {
        UserReturnVerify ver = new UserReturnVerify();

        UserAccount userAccount = this.uri.selUserByPhone(phone);
        if (userAccount != null) {
            ver.setInfo("该电话已被注册");
        } else {
            Info info = requestSmsCode(phone);
            ver.setInfo(info.getStatus());
        }
        return ver;
    }

    /**
     * 验证邮件
     */
    public UserReturnVerify verifyUserByEmail(String email) {
        UserReturnVerify ver = new UserReturnVerify();
        UserAccount userAccount = this.uri.selUserByEmail(email);
        if (userAccount != null) {
            ver.setInfo("该邮箱已被注册");
        } else {
            ver.setInfo("该邮箱可用");
        }
        return ver;
    }

    /**
     * 用户登录
     */
    public UserReturnInfo userLogin(UserVO user) {
        UserAccount u = null;
        UserReturnInfo msg = new UserReturnInfo();

        try {
            if (user.getUsername() != null) {
                u = this.uri.selUserByUsername(user.getUsername());
            } else if (user.getPhone() != null) {
                u = this.uri.selUserByPhone(user.getPhone());
            } else if (user.getEmail() != null) {
                u = this.uri.selUserByEmail(user.getEmail());
            }

            if (u.getPassword().equals(user.getPassword())) {
                if (u.getToken() != null) {
                    msg.setOid(u.getOid());
                    msg.setStatus("登录成功");
                    msg.setToken(u.getToken());
                    msg.setImages(u.getUserimage());
                } else {
                    msg.setStatus("未授权");
                }
            } else {
                msg.setStatus("密码错误");
            }
            return msg;
        } catch (Exception e) {
            msg.setStatus("用户不存在");
        }
        return msg;
    }

    /**
     * 发送手机验证码
     */
    public Info requestSmsCode(String phone) {
        Info in = new Info();
        String url = "http://gw.api.taobao.com/router/rest";
        String code = RandomUtil.createRandomVcode();
        TaobaoClient client = new DefaultTaobaoClient(url, "23357149",
                "8fd289cf17374db94e1b2184462b822e");

        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("1");
        req.setSmsType("normal");
        req.setSmsFreeSignName("展云互联");

        req.setRecNum(phone);
        req.setSmsTemplateCode("SMS_8926569");
        req.setSmsParamString("{msg:'" + code + "'}");
        try {
            AlibabaAliqinFcSmsNumSendResponse rsp = (AlibabaAliqinFcSmsNumSendResponse) client
                    .execute(req);

            UserPhoneVerify userPhoneVerify = new UserPhoneVerify();
            userPhoneVerify.setOid(phone);
            userPhoneVerify.setPhone(phone);
            userPhoneVerify.setCode(code);
            userPhoneVerify.setTime(new Date());
            this.sms.addSms(userPhoneVerify);
            in.setStatus("发送成功");
        } catch (ApiException e) {
            in.setStatus("发送失败");
        }

        return in;
    }

    /**
     * 验证手机短信验证码
     *
     * @param code
     * @param phone
     * @return
     */
    public Info verifySmsCode(String code, String phone) {
        Info in = new Info();
        try {
            UserPhoneVerify s = this.sms.selSms(phone);

            String c = s.getCode();
            Date date1 = s.getTime();
            Date date2 = new Date();

            long r = (date2.getTime() - date1.getTime()) / 60L / 1000L;
            if (r < 10L) {
                if (code.equals(c)) {
                    in.setStatus("验证成功");

                    //验证成功 删除验证码
                    this.sms.delSms(s.getOid());
                } else {
                    in.setStatus("验证失败");
                }
            } else {
                in.setStatus("验证超时");
            }
        } catch (Exception e) {
            in.setStatus("验证超时");
        }

        return in;
    }

    /**
     * 添加用户账户信息
     *
     * @param userAccount
     * @return
     */
    public Info addUserInfo(UserAccount userAccount) {
        Info in = new Info();
        try {
            this.uri.addUser(userAccount);

            in.setStatus("添加成功");
        } catch (Exception e) {
            in.setStatus("添加失败");
        }

        return in;
    }

    /**
     * 跟新用户信息
     *
     * @param uivo
     * @return
     */
    public Info updateInfo(UserInfoVO uivo) {
        Info in = new Info();
        try {
            this.uri.updateUser(uivo);
            in.setStatus("修改成功");
        } catch (Exception e) {
            in.setStatus("修改失败");
        }
        return in;
    }

    /**
     * 修改用户密码
     *
     * @param up
     * @return
     */
    public Info updatePassword(UserPasswordModify up) {
        Info info = new Info();
        try {
            // 查询就密码是否正确
            String oid = token.tokenToOid();
            up.setOid(oid);
            UserAccount userAccount = uri.selUserById(oid);
            if (userAccount.getPassword().equals(up.getOldpwd())) {
                this.uri.updateUserPwd(up);
                info.setStatus("修改成功");
            } else {
                info.setStatus("密码错误");
            }

        } catch (Exception e) {
            info.setStatus("修改失败");
        }
        return info;
    }

    /**
     * 忘记密码
     *
     * @param up
     * @return
     */
    public Info forgetPassword(UserPasswordModify up) {
        String oid = token.tokenToOid();
        Info info = new Info();
        try {
            //查询
            UserAccount userAccount = uri.selUserByPhone(up.getPhone());
            if (userAccount != null) {
                this.uri.forgetUserPwd(up, oid);
                info.setStatus("y");
            } else {
                info.setStatus("n");
            }

        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }

    /**
     * 上传用户头像
     */
    public Info uploadImage(String oid, MultipartFile file) {
        Info info = new Info();
        try {
            String imageOid = selUserInfo(this.token.tokenToOid()).getUserimage();

            String imagelocation = "用户头像";
            this.imageService
                    .saveImageOneService(file, imageOid, imagelocation);
            info.setStatus("成功");
        } catch (Exception e) {
            info.setStatus("失败");
        }

        return info;
    }

    /**
     * 单条查询用户
     */
    public UserInfoVO selUserInfo(String oid) {
        return this.uri.selUserInfoOne(oid);
    }

    /**
     * 增加公司信息
     */
    @Override
    public Info updateUserCompany(UserCompany userCompany) {
        Info info = new Info();
        String oid = token.tokenToOid();
        userCompany.setOid(oid);
        try {
            this.uri.updateCompany(userCompany);
            info.setStatus("成功");
            info.setOid(userCompany.getOid());
        } catch (Exception e) {
            info.setStatus("失败");
            info.setOid(userCompany.getOid());
        }

        return info;
    }

    /**
     * 修改基本用户信息
     *
     * @param userInfoVO
     * @return
     */
    @Override
    public Info updateUserBaseInfoService(UserInfoVO userInfoVO) {
        Info info = new Info();
        String oid = token.tokenToOid();
        userInfoVO.setOid(oid);
        try {
            this.uri.updateUserBaseInfo(userInfoVO);
            info.setStatus("成功");
            info.setOid(userInfoVO.getOid());
        } catch (Exception e) {
            info.setStatus("失败");
            info.setOid(userInfoVO.getOid());
        }
        return info;
    }

    @Override
    public UserCompany selUserCompanyService(String oid) {
        UserAccount userAccount = this.uri.selUserCompanyInfo(oid);
        UserCompany userCompany = new UserCompany();
        userCompany.setAddress(userAccount.getAddress());
        userCompany.setCompany(userAccount.getCompany());
        userCompany.setCompanylogo(userAccount.getCompanylogo());
        userCompany.setIndustry(userAccount.getIndustry());
        userCompany.setOid(oid);
        userCompany.setTel(userAccount.getTel());
        userCompany.setWebsite(userAccount.getWebsite());
        userCompany.setCompanyintroduction(userAccount.getCompanyintroduction());
        return userCompany;
    }

    /**
     * 忘记密码手机验证
     */
    @Override
    public Info forgetPwdPhoneVerity(String phone) {
        Info info = new Info();
        //验证手机是否存在
        UserAccount userAccount = this.uri.selUserByPhone(phone);
        //判断
        if (userAccount.getOid() != null) {
            //继续
            Info in = requestSmsCode(phone);
            if (in.getStatus().equals("发送成功")) {
                info.setStatus("y");

            } else {
                info.setStatus("n");
            }

        } else {
            //返回
            info.setStatus("phoneIsNotExist");
        }

        return info;
    }

    /**
     *
     */
    @Override
    public Info forgetPwdSmsVerify(String code, String phone) {
        Info info = new Info();
        //验证
        Info in = verifySmsCode(code, phone);
        if (in.getStatus().equals("验证成功")) {
            //验证成功,返回token
            UserAccount userAccount = uri.selUserByPhone(phone);
            info.setToken(userAccount.getToken());
            info.setStatus("y");
        } else {
            info.setStatus("n");
        }
        return info;
    }

    @Override
    public Info updateUserPhone(UserVO userVO) {
        userVO.setOid(token.tokenToOid());
        Info info = new Info();
        //验证码校验
        Info in = verifySmsCode(userVO.getCode(), userVO.getPhone());
        if (in.getStatus().equals("验证成功")) {
            try {
                uri.updateUserPhone(userVO);
                info.setStatus("y");
            } catch (Exception e) {
                info.setStatus("n");
            }
        } else {
            info.setStatus("n");
        }
        return info;
    }


}
