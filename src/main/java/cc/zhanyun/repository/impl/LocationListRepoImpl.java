package cc.zhanyun.repository.impl;

import cc.zhanyun.model.location.Location;
import cc.zhanyun.model.location.LocationList;
import cc.zhanyun.repository.LocationListRepository;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hyh on 16-9-5.
 */
@Repository
public class LocationListRepoImpl {
    @Autowired
    private LocationListRepository locationListRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 增加场地独立列表
     *
     * @param locationList
     */
    public void addLocationList(LocationList locationList) {
        locationListRepository.save(locationList);
    }

    /**
     * 查询场地独立列表
     *
     * @param oid
     */
    public void delLocationList(String oid) {
        locationListRepository.delete(oid);
    }

    /**
     * 多条查询
     *
     * @param uid
     * @return
     */
    public List<LocationList> selLocationList(String uid) {
        return locationListRepository.findByUid(uid);
    }



}
