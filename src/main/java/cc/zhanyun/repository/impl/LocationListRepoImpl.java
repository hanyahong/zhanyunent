package cc.zhanyun.repository.impl;

import cc.zhanyun.model.location.LocationList;
import cc.zhanyun.repository.LocationListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hyh on 16-9-5.
 */
@Repository
public class LocationListRepoImpl {
    @Autowired
    private LocationListRepository locationListRepository;

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

    public List<LocationList> selLocationList(String uid, Pageable pageable) {
        return locationListRepository.findByUid(uid, pageable);
    }
}
