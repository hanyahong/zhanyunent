package cc.zhanyun.service.impl;

import java.util.ArrayList;
import java.util.List;

import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.location.LocationList;
import cc.zhanyun.repository.impl.LocationListRepoImpl;
import cc.zhanyun.service.LocationListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.image.Image;
import cc.zhanyun.model.location.Houses;
import cc.zhanyun.model.location.Location;
import cc.zhanyun.model.vo.LocationImageHouseOidVO;
import cc.zhanyun.model.vo.LocationImageOidVO;
import cc.zhanyun.model.vo.LocationVO;
import cc.zhanyun.repository.impl.LocationRepoImpl;
import cc.zhanyun.service.LocationService;
import cc.zhanyun.util.RandomUtil;
import cc.zhanyun.util.TokenUtil;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepoImpl locationRepo;
    @Autowired
    private TokenUtil tokenutil;
    @Autowired
    private ImageServiceImpl imageServiceImpl;
    @Autowired
    private LocationListRepoImpl locationListRepo;

    /**
     * 单条添加场地
     *
     * @param location
     * @return
     */
    public LocationImageOidVO addLocation(Location location) {
        LocationImageOidVO li = new LocationImageOidVO();//返回值载体函数

        String oid = RandomUtil.getRandomFileName();//场地ID
        String uid = this.tokenutil.tokenToOid();//用户归属ID
        String locationImage = RandomUtil.getRandomFileName();//场地效果图图库ID
        List<Houses> house = location.getHouses();//获取场地房间
        List<LocationImageHouseOidVO> hilist = new ArrayList();//创建房间集合函数
        try {
            //创建场地效果图图片库
            Image image = new Image();
            image.setOid(locationImage);
            image.setUid(uid);
            this.imageServiceImpl.saveImageService(image);
            //如果场地房间不为空
            if (house.size() != 0) {
                for (Houses h : house) {
                    String houseImages = RandomUtil.getRandomFileName();//房间照片图库ID
                    String houseCaseImages = RandomUtil.getRandomFileName();//房间案例图库ID
                    h.setImages(houseImages);//
                    h.setCaseimages(houseCaseImages);
                    //添加场地房间图片库
                    Image image2 = new Image();
                    image2.setOid(houseImages);
                    image2.setUid(uid);
                    this.imageServiceImpl.saveImageService(image2);
                    //添加场地房间案例图片库
                    Image image3 = new Image();
                    image3.setOid(houseCaseImages);
                    image3.setUid(uid);
                    this.imageServiceImpl.saveImageService(image3);
                    //注入图片库ID
                    LocationImageHouseOidVO lihovo = new LocationImageHouseOidVO();
                    lihovo.setImages(houseImages);
                    lihovo.setCaseImages(houseCaseImages);
                    hilist.add(lihovo);//添加
                }
                li.setLhlist(hilist);
            }

            location.setHouses(house);
            location.setOid(oid);
            location.setUid(uid);
            location.setImages(locationImage);//场地效果图设置
            this.locationRepo.addLocation(location);//添加场地
            //添加场地独立列表
            LocationList locationList = new LocationList();
            locationList.setUid(uid);
            locationList.setOid(oid);
            locationList.setAddress(location.getAddress());
            locationList.setName(location.getName());
            locationList.setWebsite(location.getWebsite());
            locationList.setPhone(location.getPhone());
            locationList.setStatus(3);
            locationListRepo.addLocationList(locationList);
            //返回值设定
            li.setOid(oid);
            li.setLocationimages(locationImage);
            li.setLhlist(hilist);
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return li;
    }

    /**
     * 查询场地信息
     *
     * @param oid
     * @return
     */
    public Location selLocationInfo(String oid) {
        return this.locationRepo.selLocationById(oid);
    }

    /**
     * 删除场地信息
     *
     * @param oid
     * @return
     */
    public Info delLocationInfo(String oid) {
        Info info = new Info();
        try {
            //删除场地信息
            this.locationRepo.delLocationById(oid);
            //删除场地独立列表
            this.locationListRepo.delLocationList(oid);
            //返回值设定
            info.setStatus("成功");
        } catch (Exception e) {
            info.setStatus("失败");
        }
        return info;
    }

    /**
     * 查询场地列表
     *
     * @return
     */
    public List<LocationVO> selLocationList(Integer num, Integer size) {
        String uid = this.tokenutil.tokenToOid();
        Pageable pageable = null;
        if (size != null) {
            pageable = new PageRequest(num, size);
        }
        return this.locationRepo.selLocation(uid, pageable);
    }

    public Info addLocationImage(String oid, MultipartFile file) {
        Info info = new Info();
        try {
            Location location = selLocationInfo(oid);
            String imageOid = location.getImages();

            String imagelocation = "场地效果图";
            this.imageServiceImpl.saveImageOneService(file, imageOid,
                    imagelocation);
            info.setStatus("成功");
        } catch (Exception e) {
            info.setStatus("失败");
        }

        return info;
    }

    public Info addLocationRoomImage(String oid, String houseOid,
                                     MultipartFile file) {
        Info info = new Info();
        try {
            Location location = this.locationRepo.selLocationById(oid);
            List<Houses> hlist = location.getHouses();
            String imageOid = null;
            for (Houses h : hlist) {
                if (h.getOid().equals(houseOid)) {
                    imageOid = h.getImages();
                }
            }

            String imagelocation = "会议室图片";
            this.imageServiceImpl.saveImageOneService(file, imageOid,
                    imagelocation);
            info.setStatus("成功");
        } catch (Exception e) {
            info.setStatus("失败");
        }

        return info;
    }

    public LocationImageOidVO updateLocation(Location location) {
        //参数值与预模型
        String uid = this.tokenutil.tokenToOid();
        List<Houses> house = location.getHouses();
        LocationImageOidVO li = new LocationImageOidVO();
        List<LocationImageHouseOidVO> llist = new ArrayList();
        //图片库处理
        if (house.size() != 0) {
            for (Houses h : house) {
                String houseImages = null;
                LocationImageHouseOidVO lihovo = new LocationImageHouseOidVO();
                //如果图库为空,创建
                if (h.getImages() == null) {
                    houseImages = RandomUtil.getRandomFileName();
                    h.setImages(houseImages);

                    Image image2 = new Image();
                    image2.setOid(houseImages);
                    image2.setUid(uid);
                    this.imageServiceImpl.saveImageService(image2);

                    lihovo.setImages(houseImages);
                }
                //会议室案例图库,创建
                String houseCaseImages = null;
                if (h.getCaseimages() == null) {
                    houseCaseImages = RandomUtil.getRandomFileName();
                    h.setCaseimages(houseCaseImages);

                    Image image3 = new Image();
                    image3.setOid(houseCaseImages);
                    image3.setUid(uid);
                    this.imageServiceImpl.saveImageService(image3);

                    lihovo.setCaseImages(houseCaseImages);
                }

                llist.add(lihovo);
            }

            location.setHouses(house);
            location.setUid(uid);
        }

        try {
            //更新场地信息
            this.locationRepo.addLocation(location);
            //更新场地独立列表
            LocationList locationList = new LocationList();
            locationList.setUid(uid);
            locationList.setOid(location.getOid());
            locationList.setAddress(location.getAddress());
            locationList.setName(location.getName());
            locationList.setWebsite(location.getWebsite());
            locationListRepo.addLocationList(locationList);
            //返回值设定
            li.setLocationimages(location.getImages());
            li.setLhlist(llist);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return li;
    }

    /**
     * 增加场地房间案例图片
     *
     * @param oid
     * @param houseOid
     * @param file
     * @return
     */
    public Info addLocationRoomCaseImage(String oid, String houseOid,
                                         MultipartFile file) {
        Info info = new Info();
        try {
            Location location = this.locationRepo.selLocationById(oid);
            List<Houses> hlist = location.getHouses();
            String imageOid = null;
            for (Houses h : hlist) {
                if (h.getOid().equals(houseOid)) {
                    imageOid = h.getCaseimages();
                }
            }

            String imagelocation = "会议室案例图片";
            this.imageServiceImpl.saveImageOneService(file, imageOid,
                    imagelocation);
            info.setStatus("成功");
        } catch (Exception e) {
            info.setStatus("失败");
        }

        return info;
    }

    /**
     * 批量操作场地实体
     *
     * @param llist
     * @return
     */
    @Override
    public List<Info> batchOperation(List<Location> llist) {
        List<Info> inlist = new ArrayList<Info>();
        // 力量导入操作
        // 遍历数组
        for (Location l : llist) {
            Info info = new Info();
            // 增加 :1 修改:2 删除:0
            if (l.getStatus() == 1) {
                LocationImageOidVO lVo = addLocation(l);
                if (lVo.getOid() != null) {
                    info.setOid(lVo.getOid());
                    info.setStatus("y");
                } else {
                    info.setStatus("n");
                }
            } else if (l.getStatus() == 2) {
                LocationImageOidVO lVo = updateLocation(l);
                info.setOid(l.getOid());
                if (lVo != null) {
                    info.setStatus("y");
                } else {
                    info.setStatus("n");
                }
            } else if (l.getStatus() == 0) {
                info.setOid(l.getOid());
                Info info2 = delLocationInfo(l.getOid());
                if (info2.getStatus().equals("成功")) {
                    info.setStatus("y");
                } else {
                    info.setStatus("n");
                }
            }
            // 加入集合
            inlist.add(info);
        }
        return inlist;
    }

    @Override
    public Info updateLocationMainInfo(Location location) {
        Info info = new Info();
        try {
            locationRepo.updateLocationMain(location);
            info.setStatus("y");
        } catch (Exception e) {
            info.setStatus("n");
        }
        return info;
    }

    @Override
    public List<Info> batchOperationDelete(List<String> stringList) {
        List<Info> infoList = new ArrayList<Info>();
        for (String s : stringList) {
            Info info = new Info();
            Info in = delLocationInfo(s);
            if (in.getStatus().equals("成功")) {
                info.setStatus("y");
            } else {
                info.setStatus("n");
            }
            infoList.add(info);
        }
        return infoList;
    }
}
