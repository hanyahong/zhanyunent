package cc.zhanyun.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cc.zhanyun.model.user.UserAccount;
import cc.zhanyun.repository.impl.UserRepoImpl;

@Repository
public class TokenUtil {
    @Autowired
    private UserRepoImpl userRepo;
    @Autowired
    private HttpServletRequest request;

    public String tokenToOid() {
        UserAccount u = null;
        try {
            String token = "20160913146807";
//             String token = this.request.getHeader("token");

            u = this.userRepo.selUserByToken(token);
        } catch (Exception localException) {

        }

        return u.getOid();
    }

}
