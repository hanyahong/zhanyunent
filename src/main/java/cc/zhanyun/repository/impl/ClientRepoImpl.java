package cc.zhanyun.repository.impl;

import cc.zhanyun.model.client.Clientmanager;
import cc.zhanyun.model.vo.ClientVO;
import cc.zhanyun.model.vo.ClientimageVO;
import cc.zhanyun.repository.ClientRepository;
import com.mongodb.BasicDBObject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


@Repository
public class ClientRepoImpl {
    @Autowired
    private ClientRepository clientrepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 单条增加
     *
     * @param client
     */
    public void addClient(Clientmanager client) {
        this.clientrepo.save(client);
    }

    /**
     * 列表查询
     *
     * @param uid
     * @param pageable
     * @return
     */
    public List<ClientVO> selClients(String uid, Pageable pageable) {
        return this.clientrepo.findByUid(uid, pageable);
    }

    /**
     * 以 uid 列表查询
     *
     * @param uid
     * @param pageable
     * @return
     */
    public List<ClientVO> selClientsOfIDAndName(String uid, Pageable pageable) {
        return this.clientrepo.findByUid(uid, pageable);
    }

    /**
     * 单条查询
     *
     * @param oid
     * @return
     */
    public Clientmanager selClientById(String oid) {
        Clientmanager client = this.clientrepo.findByOid(oid);


        return client;
    }

    /**
     * 以name 单条查询
     *
     * @param name
     * @return
     */
    public Clientmanager selClientByName(String name,String uid) {
        return this.clientrepo.findByNameAndUid(name,uid);
    }

    /**
     * 单条删除
     *
     * @param oid
     */
    public void delClient(String oid) {
        this.clientrepo.delete(oid);
    }

    /**
     * 单挑更新
     *
     * @param client
     */
    public void updateClient(Clientmanager client) {
        BasicDBObject basicDBObject = new BasicDBObject();

        basicDBObject.put("$set", new BasicDBObject("name", client

                .getName())
                .append("sex", client.getSex())
                .append("company", client.getCompany())
                .append("dept", client.getDept())
                .append("tel", client.getTel())
                .append("email", client.getEmail())
                .append("industry", client.getIndustry())
                .append("website", client.getWebsite())
                .append("address", client.getAddress())
                .append("_abstract", client.getAbstract()));

        Update update = new BasicUpdate(basicDBObject);

        this.mongoTemplate.upsert(new Query(Criteria.where("_id")
                .is(client.getOid())), update, "clientmanager");
    }

    /**
     * 单挑保存 图片
     *
     * @param civo
     * @return
     */
    public Integer saveClientImage(ClientimageVO civo) {
        try {
            BasicDBObject basicDBObject = new BasicDBObject();

            basicDBObject.put("$set", new BasicDBObject("image", civo
                    .getImage()));

            Update update = new BasicUpdate(basicDBObject);

            this.mongoTemplate.upsert(new Query(
                    Criteria.where("_id").is("57a1b1ffbc9e2a54e5523d0b")), update, "clientmanager");

        } catch (Exception e) {
            return Integer.valueOf(0);
        }
        return Integer.valueOf(1);
    }
}


