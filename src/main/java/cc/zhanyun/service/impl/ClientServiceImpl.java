package cc.zhanyun.service.impl;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.client.Clientmanager;
import cc.zhanyun.model.client.ClientmanagerList;
import cc.zhanyun.model.image.Image;
import cc.zhanyun.model.vo.ClientVO;
import cc.zhanyun.repository.impl.ClientListRepoImpl;
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
    @Autowired
    private ClientListRepoImpl clientListRepo;

    /**
     * 增加单条客户信息
     *
     * @param client
     * @return
     */
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
            //添加客户独立的列表(电脑端使用)
            ClientmanagerList cml = new ClientmanagerList();
            cml.setOid(oid);
            cml.setUid(uid);
            cml.setWechat(client.getWechat());
            cml.setName(client.getName());
            cml.setQq(client.getQq());
            cml.setStatus(3);
            clientListRepo.addClientListOne(cml);
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
            //更新
            this.clientRepoImpl.addClient(client);
            //独立列表更新(电脑端)
            ClientmanagerList c=new ClientmanagerList();
            c.setOid(client.getOid());
            c.setUid(client.getUid());
            c.setQq(client.getQq());
            c.setName(client.getName());
            c.setWechat(client.getWechat());
            c.setCompany(client.getCompany());
            c.setEmail(client.getEmail());
            c.setTel(client.getTel());
            this.clientListRepo.addClientListOne(c);
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

    /**
     * 查询客户信息
     *
     * @param oid
     * @return
     */
    public Clientmanager selClientInfo(String oid) {
        return this.clientRepoImpl.selClientById(oid);
    }

    public Info delClientInfo(String oid) {
        Info info = new Info();
        try {
            this.clientRepoImpl.delClient(oid);
            this.clientListRepo.delClientListByOid(oid);
            info.setStatus("成功");
        } catch (Exception e) {
            info.setStatus("失败");
        }

        return info;
    }

    /**
     * 查询客户列表
     *
     * @param num
     * @param size
     * @return
     */
    public List<ClientVO> selClientList(Integer num, Integer size) {
        String uid = this.token.tokenToOid();
        Pageable pageable = new PageRequest(num, size);
        return this.clientRepoImpl.selClients(uid, pageable);
    }

    /**
     * 批量操作
     *
     * @param clist
     * @return
     */
    @Override
    public List<Info> batchClient(List<Clientmanager> clist) {
        List<Info> ilist = new ArrayList<Info>();
        for (Clientmanager c : clist) {
            //如果姓名不为空
            if (c.getName() != null) {
                if (c.getStatus() == 1) {
                    addClientOne(c);
                } else if (c.getStatus() == 2) {
                    updateClientOne(c);
                }
            }
        }
        return ilist;
    }
}
