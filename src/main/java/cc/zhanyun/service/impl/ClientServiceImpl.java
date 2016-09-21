package cc.zhanyun.service.impl;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.client.Clientmanager;
import cc.zhanyun.model.image.Image;
import cc.zhanyun.model.vo.ClientVO;
import cc.zhanyun.repository.impl.ClientRepoImpl;
import cc.zhanyun.service.ClientService;
import cc.zhanyun.service.ImageService;
import cc.zhanyun.util.RandomUtil;
import cc.zhanyun.util.TokenUtil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepoImpl clientRepoImpl;
    @Autowired
    private TokenUtil token;
    @Autowired
    private ImageService imageService;

    public Info addClientOne(Clientmanager client) {
        Info info = new Info();
        String oid = RandomUtil.getRandomFileName();
        String imageOid = RandomUtil.getRandomFileName();
        String uid = this.token.tokenToOid();
        try {
            //添加客户
            client.setOid(oid);
            client.setUid(uid);
            client.setImage(imageOid);
            this.clientRepoImpl.addClient(client);
            //添加客户图库
            Image image = new Image();
            image.setOid(imageOid);
            image.setUid(this.token.tokenToOid());
            this.imageService.saveImageService(image);
            //返回值设定
            info.setOid(oid);
            info.setStatus("添加成功");
        } catch (Exception e) {
            info.setStatus("添加失败");
        }

        return info;
    }

    public Info updateClientOne(Clientmanager client) {
        Info info = new Info();
        try {
            this.clientRepoImpl.addClient(client);
            info.setStatus("添加成功");
        } catch (Exception e) {
            info.setStatus("添加失败");
        }

        return info;
    }

    /**
     * 增加客户头像
     *
     * @param file
     * @param oid
     * @return
     */
    public Info addClientImage(MultipartFile file, String oid) {
        Info info = new Info();
        try {
            //查询客户信息
            String imageOid = selClientInfo(oid).getImage();
            //添加属性
            String imagelocation = "客户头像";
            //添加
            this.imageService
                    .saveImageOneService(file, imageOid, imagelocation);
            info.setStatus("成功");
        } catch (Exception e) {
            info.setStatus("失败");
        }

        return info;
    }

    public Clientmanager selClientInfo(String oid) {
        return this.clientRepoImpl.selClientById(oid);
    }

    public Info delClientInfo(String oid) {
        Info info = new Info();
        try {
            this.clientRepoImpl.delClient(oid);
            info.setStatus("成功");
        } catch (Exception e) {
            info.setStatus("失败");
        }

        return info;
    }

    public List<ClientVO> selClientList(Integer num, Integer size) {
        String uid = this.token.tokenToOid();
        Pageable pageable = new PageRequest(num, size);
        return this.clientRepoImpl.selClients(uid, pageable);
    }

    @Override
    public List<Info> batchClient(List<Clientmanager> clist) {
        List<Info> ilist = new ArrayList<Info>();
        for (Clientmanager c : clist) {
            if (c.getName() != null) {
                if (c.getStatus() == 0) {
                    Info in = delClientInfo(c.getOid());
                    if (in.getStatus().equals("成功")) {

                    }
                } else if (c.getStatus() == 1) {
                    addClientOne(c);
                } else if (c.getStatus() == 2) {
                    updateClientOne(c);
                }
            }


        }
        return ilist;
    }
}
