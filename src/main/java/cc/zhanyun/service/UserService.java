package cc.zhanyun.service;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.user.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public abstract interface UserService {
    public abstract UserReturnInfo userRegister(UserVO paramUserVO);

    public abstract UserReturnVerify verifyUserByUsername(String paramString);

    public abstract UserReturnVerify verifyUserByPhone(String paramString);

    public abstract UserReturnVerify verifyUserByEmail(String paramString);

    public abstract UserReturnInfo userLogin(UserVO paramUserVO);

    public abstract Info requestSmsCode(String paramString);

    public abstract Info verifySmsCode(String paramString1, String paramString2);

    public abstract Info addUserInfo(UserAccount paramUserAccount);

    public abstract Info updateInfo(UserInfoVO paramUserInfoVO);

    public abstract Info updatePassword(
            UserPasswordModify paramUserPasswordModify);

    public abstract Info uploadImage(String paramString,
                                     MultipartFile paramMultipartFile);

    public abstract UserInfoVO selUserInfo(String oid);

    public abstract Info updateUserCompany(UserCompany userCompany);

    public abstract Info updateUserBaseInfoService(UserInfoVO userInfoVO);

    public abstract UserCompany selUserCompanyService(String oid);

    public abstract Info forgetPwdPhoneVerity(String phone);

    public abstract Info forgetPwdSmsVerify(String paramString1, String paramString2);

    public abstract Info updateUserPhone(UserVO userVO);
}
