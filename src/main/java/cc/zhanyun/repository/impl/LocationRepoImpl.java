package cc.zhanyun.repository.impl;

import cc.zhanyun.model.location.Location;
import cc.zhanyun.model.location.LocationList;
import cc.zhanyun.model.vo.LocationVO;
import cc.zhanyun.repository.LocationRepository;


import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


@Repository
public class LocationRepoImpl {
    @Autowired
    private LocationRepository locationRepo;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 增加场地
     *
     * @param location
     */
    public void addLocation(Location location) {
        this.locationRepo.save(location);
    }

    /**
     * 查询场地
     *
     * @param uid
     * @return
     */
    public List<LocationVO> selLocation(String uid, Pageable pageable) {
        List<LocationVO> lvlist = new ArrayList();

        List<Location> rlist = this.locationRepo.findByUid(uid, pageable);

        for (Location l : rlist) {
            LocationVO lvo = new LocationVO();
            lvo.setOid(l.getOid());//ID
            lvo.setName(l.getName());//名称
            lvo.setAddress(l.getAddress());//地址
            lvlist.add(lvo);
        }

        return lvlist;
    }

    /**
     * 删除场地
     *
     * @param oid
     * @return
     */
    public Location selLocationById(String oid) {
        Location location = (Location) this.locationRepo.findOne(oid);
        return location;
    }

    /**
     * 以oid 删除 场地
     *
     * @param oid
     */

    public void delLocationById(String oid) {
        this.locationRepo.delete(oid);
    }

    /**
     * 更新部分字段
     *
     * @param location
     */
    public void updateLocationMain(Location location) {
        BasicDBObject basicDBObject = new BasicDBObject();

        basicDBObject
                .put("$set", new BasicDBObject("name", location
                        .getName())
                        .append("website", location.getWebsite())//网址
                        .append("name", location.getName())//名称
                );
        Update update = new BasicUpdate(basicDBObject);
        this.mongoTemplate.upsert(new Query(
                Criteria.where("_id").is(location.getOid())), update, "location");
    }

    /**
     * 更新部分字段
     *
     * @param location
     */
    public void updateLocationBase(Location location) {
        BasicDBObject basicDBObject = new BasicDBObject();

        basicDBObject
                .put("$set", new BasicDBObject("name", location
                        .getName())
                        .append("address", location.getAddress())//联系地址
                        .append("phone", location.getPhone())//联系电话
                        .append("contacts", location.getContacts())//联系人
                        .append("website", location.getContacts())//网址
                );
        Update update = new BasicUpdate(basicDBObject);

        this.mongoTemplate.upsert(new Query(
                Criteria.where("_id").is(location.getOid())), update, "location");
    }
}


