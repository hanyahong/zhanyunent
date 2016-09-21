package cc.zhanyun.repository;

import cc.zhanyun.model.user.UserAccount;
import cc.zhanyun.model.user.UserCompany;
import cc.zhanyun.model.user.UserInfoVO;
import cc.zhanyun.model.user.UserReturnVerify;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public abstract interface UserRepository
        extends MongoRepository<UserAccount, String> {
    /**
     * 以 电话查询
     *
     * @param paramString
     * @return
     */
    public abstract UserAccount findByPhone(String paramString);

    public abstract UserAccount findByUsername(String paramString);

    public abstract UserAccount findByEmail(String paramString);

    public abstract UserAccount findByToken(String paramString);


    public abstract UserInfoVO findByOid(String paramString);

}
