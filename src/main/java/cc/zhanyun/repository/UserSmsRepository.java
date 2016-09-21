package cc.zhanyun.repository;

import cc.zhanyun.model.user.UserPhoneVerify;
import org.springframework.data.mongodb.repository.MongoRepository;

public abstract interface UserSmsRepository
        extends MongoRepository<UserPhoneVerify, String> {
    public abstract UserPhoneVerify findByPhone(String paramString);
}


