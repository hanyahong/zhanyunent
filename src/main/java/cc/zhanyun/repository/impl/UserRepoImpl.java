package cc.zhanyun.repository.impl;

import cc.zhanyun.model.user.*;
import cc.zhanyun.repository.UserRepository;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepoImpl {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private MongoTemplate mongoTemplate;

    public void addUser(UserAccount userAccount) {
        this.userRepo.save(userAccount);
    }

    /**
     * 查询用户信息
     *
     * @param oid
     * @return
     */
    public UserInfoVO selUserInfoOne(String oid) {
        return this.userRepo.findByOid(oid);
    }

    /**
     * 查询用户信息
     *
     * @param oid
     * @return
     */
    public UserAccount selUserCompanyInfo(String oid) {
        return this.userRepo.findOne(oid);
    }


    /**
     * 更新用户(手机1.0)
     *
     * @param uivo
     */
    public void updateUser(UserInfoVO uivo) {
        BasicDBObject basicDBObject = new BasicDBObject();

        basicDBObject.put(
                "$set",
                new BasicDBObject("name", uivo

                        .getName()).append("sex", uivo.getSex())
                        .append("company", uivo.getCompany())
                        .append("dept", uivo.getDept())
                        .append("phone", uivo.getPhone())
                        .append("email", uivo.getEmail())
                        .append("website", uivo.getWebsite())
                        .append("job", uivo.getJob()));

        Update update = new BasicUpdate(basicDBObject);

        this.mongoTemplate.upsert(
                new Query(Criteria.where("_id").is(uivo.getOid())), update,
                "userAccount");
    }


    /**
     * 更新用户手机
     *
     * @param userVO
     */
    public void updateUserPhone(UserVO userVO) {
        BasicDBObject basicDBObject = new BasicDBObject();

        basicDBObject.put(
                "$set",
                new BasicDBObject("phone", userVO.getPhone()));

        Update update = new BasicUpdate(basicDBObject);

        this.mongoTemplate.upsert(
                new Query(Criteria.where("_id").is(userVO.getOid())), update,
                "userAccount");
    }

    /**
     * 更新密码
     *
     * @param up
     */
    public void updateUserPwd(UserPasswordModify up) {
        BasicDBObject basicDBObject = new BasicDBObject();

        basicDBObject
                .put("$set", new BasicDBObject("password", up.getNewpwd()));

        Update update = new BasicUpdate(basicDBObject);

        this.mongoTemplate.upsert(
                new Query(Criteria.where("_id").is(up.getOid())), update,
                "userAccount");
    }

    /**
     * 更新密码
     *
     * @param up
     */
    public void forgetUserPwd(UserPasswordModify up, String oid) {
        BasicDBObject basicDBObject = new BasicDBObject();

        basicDBObject
                .put("$set", new BasicDBObject("password", up.getNewpwd()));

        Update update = new BasicUpdate(basicDBObject);

        this.mongoTemplate.upsert(
                new Query(Criteria.where("_id").is(oid)), update,
                "userAccount");
    }

    /**
     * 以 用户名 查询用户信息
     *
     * @param username
     * @return
     */
    public UserAccount selUserByUsername(String username) {
        return this.userRepo.findByUsername(username);
    }

    /**
     * 以 电话 查询用户信息
     *
     * @param phone
     * @return
     */
    public UserAccount selUserByPhone(String phone) {
        return this.userRepo.findByPhone(phone);
    }

    /**
     * 以 邮箱 查询用户信息
     *
     * @param email
     * @return
     */
    public UserAccount selUserByEmail(String email) {
        UserAccount userAccount = this.userRepo.findByEmail(email);

        return userAccount;
    }

    /**
     * 删除用户
     *
     * @param oid
     */
    public void delUser(String oid) {
        this.userRepo.delete(oid);
    }

    /**
     * 以 token 查询用户信息
     *
     * @param token
     * @return
     */
    public UserAccount selUserByToken(String token) {
        return this.userRepo.findByToken(token);
    }

    /**
     * 以 oid 查询用户信息
     *
     * @param oid
     * @return
     */
    public UserAccount selUserById(String oid) {
        return this.userRepo.findOne(oid);
    }

    /**
     * 保存用户头像
     *
     * @param uivo
     * @return
     */
    public Integer saveUserImage(UserImage uivo) {
        try {
            BasicDBObject basicDBObject = new BasicDBObject();

            basicDBObject.put("$set",
                    new BasicDBObject("image", uivo.getImage()));

            Update update = new BasicUpdate(basicDBObject);

            this.mongoTemplate.upsert(
                    new Query(Criteria.where("_id").is(uivo.getOid())), update,
                    "userAccount");
        } catch (Exception e) {
            return Integer.valueOf(0);
        }
        return Integer.valueOf(1);
    }

    /**
     * 更新公司信息
     *
     * @param userCompany
     */
    public void updateCompany(UserCompany userCompany) {
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put(
                "$set",
                new BasicDBObject("company", userCompany.getCompany()

                ).append("companyenglish", userCompany.getCompanyengname())
                        .append("address", userCompany.getAddress())
                        .append("industry", userCompany.getIndustry())
                        .append("companyintroduction", userCompany.getCompanyintroduction())
                        .append("tel", userCompany.getTel())
                        .append("website", userCompany.getWebsite())
        );

        Update update = new BasicUpdate(basicDBObject);

        this.mongoTemplate.upsert(
                new Query(Criteria.where("_id").is(userCompany.getOid())), update,
                "userAccount");
    }

    /**
     * 更新用户基本信息(pc)
     *
     * @param userInfoVO
     */
    public void updateUserBaseInfo(UserInfoVO userInfoVO) {
        BasicDBObject basicDBObject = new BasicDBObject();

        basicDBObject.put(
                "$set",
                new BasicDBObject("name", userInfoVO.getName())
                        .append("sex", userInfoVO.getSex())
                        .append("phone", userInfoVO.getPhone())
                        .append("email", userInfoVO.getEmail())
                        .append("age", userInfoVO.getAge())
                        .append("userintroduction", userInfoVO.getUserintroduction())
                        .append("username", userInfoVO.getUsername())
                        .append("qq", userInfoVO.getQq()));


        Update update = new BasicUpdate(basicDBObject);

        this.mongoTemplate.upsert(
                new Query(Criteria.where("_id").is(userInfoVO.getOid())), update,
                "userAccount");
    }

}
