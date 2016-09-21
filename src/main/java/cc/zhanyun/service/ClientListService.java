package cc.zhanyun.service;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.client.Clientmanager;
import cc.zhanyun.model.client.ClientmanagerList;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hyh on 16-9-9.
 */
@Service
public interface ClientListService {
    /**
     * 单条查询
     */
    public ClientmanagerList selCLientListOne(String oid);

    /**
     * 单条增加
     */
    public Info addClientListOne(ClientmanagerList clientmanagerList);

    /**
     * 单条删除
     */
    public Info delClientListOne(String oid);

    /**
     * 批量列表查询
     */
    public List<ClientmanagerList> selClientListByUid(Integer num ,Integer size);

    /**
     * 批量操作
     */
    public List<Info> batchOperation(List<ClientmanagerList> clientmanagerListList);
}
