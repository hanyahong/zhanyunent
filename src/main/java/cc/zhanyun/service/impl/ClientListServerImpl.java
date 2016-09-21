package cc.zhanyun.service.impl;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.client.Clientmanager;
import cc.zhanyun.model.client.ClientmanagerList;
import cc.zhanyun.repository.impl.ClientListRepoImpl;
import cc.zhanyun.service.ClientListService;
import cc.zhanyun.service.ClientService;
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
    private ClientService clientService;

    /**
     * 单条查询
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
        //返回值载体
        Info info = new Info();
        try {
            //添加一个用户信息
            Clientmanager clientmanager = new Clientmanager();
            clientmanager.setName(clientmanagerList.getName());
            clientmanager.setTel(clientmanagerList.getTel());
            clientmanager.setSex(clientmanagerList.getSex());
            clientmanager.setCompany(clientmanagerList.getCompany());
            clientmanager.setEmail(clientmanagerList.getEmail());
            Info in = clientService.addClientOne(clientmanager);
            //返回一个oid
            String oid = in.getOid();
            //添加独立列表
            clientmanagerList.setOid(oid);
            clientmanagerList.setUid(tokenUtil.tokenToOid());
            clientListRepo.addClientListOne(clientmanagerList);
            //返回值设定
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }

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

    @Override
    public List<Info> batchOperation(List<ClientmanagerList> clientmanagerListList) {
        //传值对象
        List<Info> infoList = new ArrayList<Info>();
        //查询该用户的列表
        PageableInfo pageableInfo = new PageableInfo();
        List<ClientmanagerList> cmll = selClientListByUid(pageableInfo.getNum(), pageableInfo.getSize());
        //遍历删除
        for (ClientmanagerList c : cmll) {
            delClientListOne(c.getOid());
        }
        for (ClientmanagerList cl : clientmanagerListList) {
            Info info = new Info();
            if (cl.getStatus() != null) {
                //对象传值
                Clientmanager clientmanager = new Clientmanager();
                clientmanager.setEmail(cl.getEmail());
                clientmanager.setName(cl.getName());
                clientmanager.setCompany(cl.getCompany());
                clientmanager.setSex(cl.getSex());
                clientmanager.setTel(cl.getTel());
                if (cl.getStatus() == 1) {
                    //新增
                    Info in = clientService.addClientOne(clientmanager);
                    if (in.getOid() != null) {
                        //新增分类
                        String oid = in.getOid();
                        String uid = tokenUtil.tokenToOid();
                        cl.setOid(oid);
                        cl.setUid(uid);
                        addClientListOne(cl);
                        //返回值
                        info.setStatus("y");
                    } else {
                        info.setStatus("n");
                    }

                } else if (cl.getStatus() == 2) {
                    //修改
                    Info in = clientService.updateClientOne(clientmanager);
                    if (in.getStatus().equals("添加成功")) {
                        info.setStatus("y");
                    } else {
                        info.setStatus("n");
                    }
                }
            }
            infoList.add(info);
        }
        return infoList;
    }
}
