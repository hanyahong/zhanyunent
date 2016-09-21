package cc.zhanyun.repository.impl;

import cc.zhanyun.model.user.UserPhoneVerify;

import cc.zhanyun.repository.UserSmsRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

@Repository
public class UserSmsRepoImpl {
    @Autowired
    private UserSmsRepository userRepo;

    public void addSms(UserPhoneVerify sms) {
        this.userRepo.save(sms);
    }

    public UserPhoneVerify selSms(String phone) {
        return this.userRepo.findByPhone(phone);
    }

    public void delSms(String oid) {
        this.userRepo.delete(oid);
    }

}
