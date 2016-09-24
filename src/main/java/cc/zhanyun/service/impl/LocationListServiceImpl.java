package cc.zhanyun.service.impl;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.location.Location;
import cc.zhanyun.model.location.LocationList;
import cc.zhanyun.model.vo.LocationImageOidVO;
import cc.zhanyun.repository.impl.LocationListRepoImpl;
import cc.zhanyun.repository.impl.LocationRepoImpl;
import cc.zhanyun.service.LocationListService;
import cc.zhanyun.service.LocationService;
import cc.zhanyun.util.RandomUtil;
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
    private LocationRepoImpl lri;
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
        String oid = RandomUtil.getRandomFileName();
        try {
            for (LocationList l : locationListList) {

                //添加场地
                Location location = new Location();
                location.setOid(oid);
                location.setAddress(l.getAddress());
                location.setWebsite(l.getWebsite());
                location.setName(l.getName());
                location.setPhone(l.getPhone());
                location.setContacts(l.getContacts());
                lri.addLocation(location);
                //添加场地独立列表
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
     * 查询场地独立列表
     *
     * @return
     */
    @Override
    public List<LocationList> selLocationListService() {
        String uid = tokenUtil.tokenToOid();
        List<LocationList> llist = locationListRepo.selLocationList(uid);
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
        List<LocationList> llist = selLocationListService();
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
        String uid = tokenUtil.tokenToOid();
        String oid = RandomUtil.getRandomFileName();
        locationList.setOid(oid);
        locationList.setUid(uid);
        Info info = new Info();
        try {
            //增加场地独立list(电脑端使用)
            locationListRepo.addLocationList(locationList);
            //增加场地
            Location location = new Location();
            location.setOid(locationList.getOid());
            location.setUid(locationList.getUid());
            location.setContacts(locationList.getContacts());
            location.setName(locationList.getName());
            location.setAddress(locationList.getAddress());
            location.setPhone(locationList.getPhone());
            lri.addLocation(location);
            //返回值设定
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }

    /**
     * 更新场地
     *
     * @param locationList
     * @return
     */
    @Override
    public Info updateLocationListOne(LocationList locationList) {

        Info info = new Info();
        try {
            //跟新场地独立list(电脑端使用)
            locationListRepo.addLocationList(locationList);
            //更新场地
            Location location = new Location();

            location.setOid(locationList.getOid());
            location.setUid(locationList.getUid());
            location.setContacts(locationList.getContacts());
            location.setName(locationList.getName());
            location.setAddress(locationList.getAddress());
            location.setPhone(locationList.getPhone());
            location.setWebsite(location.getWebsite());

            lri.updateLocationBase(location);
            //返回值设定
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }

    /**
     * 删除单条
     *
     * @param oid
     * @return
     */
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
            System.out.print(l.getStatus());
            //判断:1 表示新增,2表示修改
            if (l.getStatus() == 1) {

                l.setStatus(3);
                //增加场地
                addLocationListOne(l);
                info.setStatus("y");
            } else if (l.getStatus() == 2) {
                l.setStatus(3);
                //修改场地
                updateLocationListOne(l);
            } else if (l.getStatus() == null || l.getStatus() == 3) {
                l.setStatus(3);
                //没有发生变化,原封不动添加
                locationListRepo.addLocationList(l);
                info.setStatus("y");
            }
            //增加队列值
            infoList.add(info);
        }
        return infoList;
    }


}
