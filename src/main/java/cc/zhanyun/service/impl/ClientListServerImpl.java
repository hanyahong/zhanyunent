package cc.zhanyun.service.impl;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.client.Clientmanager;
import cc.zhanyun.model.client.ClientmanagerList;
import cc.zhanyun.repository.impl.ClientListRepoImpl;
import cc.zhanyun.repository.impl.ClientRepoImpl;
import cc.zhanyun.service.ClientListService;
import cc.zhanyun.service.ClientService;
import cc.zhanyun.util.RandomUtil;
import cc.zhanyun.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyh on 16-9-9.
 */
@Service
public class ClientListServerImpl implements ClientListService {

    @Autowired
    private ClientListRepoImpl clientListRepo;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private ClientRepoImpl clientRepo;

    /**
     * 单条查询 客户独立列表
     *
     * @param oid
     * @return
     */
    @Override
    public ClientmanagerList selCLientListOne(String oid) {
        return clientListRepo.selClientListByOid(oid);
    }

    /**
     * 独立添加客户信息及列表
     *
     * @param clientmanagerList
     * @return
     */
    @Override
    public Info addClientListOne(ClientmanagerList clientmanagerList) {
        String oid = RandomUtil.getRandomFileName();
        String uid = tokenUtil.tokenToOid();
        String imageOid = RandomUtil.getRandomFileName();
        //返回值载体
        Info info = new Info();
        try {
            //添加一个用户信息
            Clientmanager clientmanager = new Clientmanager();

            clientmanager.setOid(oid);
            clientmanager.setUid(uid);
            clientmanager.setName(clientmanagerList.getName());
            clientmanager.setTel(clientmanagerList.getTel());
            clientmanager.setSex(clientmanagerList.getSex());
            clientmanager.setCompany(clientmanagerList.getCompany());
            clientmanager.setEmail(clientmanagerList.getEmail());
            clientmanager.setWechat(clientmanager.getWechat());
            clientmanager.setQq(clientmanager.getQq());
            clientmanager.setImage(imageOid);

            clientRepo.addClient(clientmanager);
            //添加独立列表(电脑端使用)
            clientmanagerList.setOid(oid);
            clientmanagerList.setUid(uid);
            clientListRepo.addClientListOne(clientmanagerList);
            //返回值设定
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }

    /**
     * 修改客户
     *
     * @param clientmanagerList
     * @return
     */
    @Override
    public Info updateClientListOne(ClientmanagerList clientmanagerList) {
        //返回值载体
        Info info = new Info();
        try {
            //添加一个用户信息
            Clientmanager clientmanager = new Clientmanager();
            clientmanager.setUid(clientmanagerList.getUid());
            clientmanager.setOid(clientmanagerList.getOid());
            clientmanager.setName(clientmanagerList.getName());
            clientmanager.setTel(clientmanagerList.getTel());
            clientmanager.setSex(clientmanagerList.getSex());
            clientmanager.setCompany(clientmanagerList.getCompany());
            clientmanager.setEmail(clientmanagerList.getEmail());
            clientmanager.setWechat(clientmanagerList.getWechat());
            clientmanager.setQq(clientmanagerList.getQq());
            clientmanager.setImage(clientmanager.getImage());

            clientRepo.updateClientBase(clientmanager);
            //添加独立列表(电脑端使用)
            clientListRepo.addClientListOne(clientmanagerList);
            //返回值设定
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }

    /**
     * 单条删除
     *
     * @param oid
     * @return
     */
    @Override
    public Info delClientListOne(String oid) {
        Info info = new Info();
        try {
            //删除独立列表
            clientListRepo.delClientListByOid(oid);
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }

    /**
     * 查询列表
     *
     * @return
     */
    @Override
    public List<ClientmanagerList> selClientListByUid(Integer num, Integer size) {
        //获取Page 信息
        Pageable pageable = new PageRequest(num, size);
        return clientListRepo.selClientListByUid(tokenUtil.tokenToOid(), pageable);

    }

    /**
     * 客户批量操作(独立列表,电脑端)
     *
     * @param clientmanagerListList
     * @return
     */
    @Override
    public List<Info> batchOperation(List<ClientmanagerList> clientmanagerListList) {
        //传值对象
        List<Info> infoList = new ArrayList<Info>();
        //查询该用户的列表
        List<ClientmanagerList> cmll = selClientListByUid(0, 10000);
        //删除所有旧的列表(考虑配需问题要全部删除)
        for (ClientmanagerList c : cmll) {
            delClientListOne(c.getOid());
        }
        //遍历添加

        for (ClientmanagerList cl : clientmanagerListList) {
            System.out.println(cl.getName() + cl.getStatus());
            Info in = null;
            if (cl.getStatus() == 1) {
                //修改状态值
                cl.setStatus(3);
                //添加
                in = addClientListOne(cl);
            } else if (cl.getStatus() == 2) {
                //修改状态值
                cl.setStatus(3);
                //更新
                in = updateClientListOne(cl);

            } else if (cl.getStatus() == null || cl.getStatus() == 3) {
                //修改状态值
                cl.setStatus(3);
                //原封不动的添加
                clientListRepo.addClientListOne(cl);
            }
            infoList.add(in);
        }

        return infoList;
    }
}
