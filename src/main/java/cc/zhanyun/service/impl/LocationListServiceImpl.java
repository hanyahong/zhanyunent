package cc.zhanyun.service.impl;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.location.Location;
import cc.zhanyun.model.location.LocationList;
import cc.zhanyun.model.vo.LocationImageOidVO;
import cc.zhanyun.repository.impl.LocationListRepoImpl;
import cc.zhanyun.service.LocationListService;
import cc.zhanyun.service.LocationService;
import cc.zhanyun.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyh on 16-9-5.
 */
@Service
public class LocationListServiceImpl implements LocationListService {
    @Autowired
    private LocationListRepoImpl locationListRepo;
    @Autowired
    private LocationService locationService;
    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 增加场地独立列表
     *
     * @param locationListList
     * @return
     */
    @Override
    public Info addLocationListService(List<LocationList> locationListList) {
        Info info = new Info();
        String uid = tokenUtil.tokenToOid();
        try {
            for (LocationList l : locationListList) {

                //添加场地
                Location location = new Location();
                location.setAddress(l.getAddress());
                location.setWebsite(l.getWebsite());
                location.setName(l.getName());
                location.setPhone(l.getPhone());
                LocationImageOidVO lovo = locationService.addLocation(location);
                //添加场地独立列表
                String oid = lovo.getOid();
                l.setOid(oid);
                l.setUid(uid);
                locationListRepo.addLocationList(l);
            }
            //返回值设置
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }

        return info;
    }

    /**
     * 查询场地地理列表
     *
     * @return
     */
    @Override
    public List<LocationList> selLocationListService(Integer num, Integer size) {
        String uid = tokenUtil.tokenToOid();
        Pageable pageable = null;
        if (size != null) {
            pageable = new PageRequest(num, size);
        }
        List<LocationList> llist = locationListRepo.selLocationList(uid, pageable);
        return llist;
    }

    /**
     * 删除场地独立列表
     *
     * @return
     */
    @Override
    public Info delLocationListService() {
        Info info = new Info();
        //查询列表
        PageableInfo pageableInfo = new PageableInfo();
        List<LocationList> llist = selLocationListService(pageableInfo.getNum(), pageableInfo.getSize());
        try {
            for (LocationList l : llist) {
                String oid = l.getOid();
                locationListRepo.delLocationList(oid);
            }
            //设置返回值
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }

        return info;
    }

    @Override
    public Info addLocationListOne(LocationList locationList) {
        Info info = new Info();
        try {
            locationListRepo.addLocationList(locationList);
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }

    @Override
    public Info delLocationListOne(String oid) {
        Info info = new Info();
        try {
            locationListRepo.delLocationList(oid);
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }

    /**
     * 批量操作(添加/修改)
     *
     * @param locationListList
     * @return
     */
    @Override
    public List<Info> batchOperation(List<LocationList> locationListList) {
        List<Info> infoList = new ArrayList<Info>();
        //删除旧场地列表
        delLocationListService();
        //检测是否有变动
        for (LocationList l : locationListList) {

            Location location = new Location();
            Info info = new Info();
            location.setOid(l.getOid());
            location.setName(l.getName());
            location.setWebsite(l.getWebsite());
            location.setUid(tokenUtil.tokenToOid());

            //判断:1 表示新增,2表示修改
            if (l.getStatus() == 1) {
                //场地实体增加
                LocationImageOidVO locationImageOidVO = locationService.addLocation(location);
                if (locationImageOidVO != null) {
                    info.setStatus("y");
                    //增加场地独立列表
                    l.setOid(locationImageOidVO.getOid());
                    l.setUid(tokenUtil.tokenToOid());
                    locationListRepo.addLocationList(l);
                } else {
                    info.setStatus("n");

                }
                info.setStatus("y");
            } else if (l.getStatus() == 2) {
                //2 表示修改
                Info in = locationService.updateLocationMainInfo(location);

                locationListRepo.addLocationList(l);
                info.setStatus(in.getStatus());
            } else if (l.getStatus() == null) {
                locationListRepo.addLocationList(l);
                info.setStatus("y");
            }
            //增加队列值
            infoList.add(info);


        }
        return infoList;
    }


}
