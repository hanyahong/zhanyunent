package cc.zhanyun.repository.impl;

import cc.zhanyun.model.client.ClientmanagerList;
import cc.zhanyun.repository.ClientListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hyh on 16-9-9.
 */
@Repository
public class ClientListRepoImpl {

    @Autowired
    private ClientListRepository clientListRepository;

    /**
     * 单条查询
     *
     * @param oid
     * @return
     */
    public ClientmanagerList selClientListByOid(String oid) {
        return clientListRepository.findOne(oid);
    }

    /**
     * 单条删除
     *
     * @param oid
     */
    public void delClientListByOid(String oid) {
        clientListRepository.delete(oid);
    }

    /**
     * 以uid 查询列表
     *
     * @param uid
     * @return
     */
    public List<ClientmanagerList> selClientListByUid(String uid, Pageable pageable) {

        return clientListRepository.findByUid(uid, pageable);
    }

    /**
     * 单条增加
     */
    public void addClientListOne(ClientmanagerList clientmanagerList) {
        clientListRepository.save(clientmanagerList);
    }


}
