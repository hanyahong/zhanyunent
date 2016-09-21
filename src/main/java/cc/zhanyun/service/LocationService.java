package cc.zhanyun.service;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.location.Location;
import cc.zhanyun.model.vo.LocationImageOidVO;
import cc.zhanyun.model.vo.LocationVO;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public abstract interface LocationService {
    /**
     * 增加场地
     *
     * @param paramLocation
     * @return
     */
    public abstract LocationImageOidVO addLocation(Location paramLocation);

    /**
     * 更新场地信息
     *
     * @param paramLocation
     * @return
     */
    public abstract LocationImageOidVO updateLocation(Location paramLocation);

    /**
     * 查询场地信息
     *
     * @param paramString
     * @return
     */
    public abstract Location selLocationInfo(String paramString);

    /**
     * 删除场地
     *
     * @param paramString
     * @return
     */
    public abstract Info delLocationInfo(String paramString);

    /**
     * 查询列表
     *
     * @return
     */
    public abstract List<LocationVO> selLocationList(Integer num ,Integer size);

    /**
     * 增加场地效果图库
     *
     * @param paramString
     * @param paramMultipartFile
     * @return
     */
    public abstract Info addLocationImage(String paramString,
                                          MultipartFile paramMultipartFile);

    /**
     * 增加房间图库
     *
     * @param paramString1
     * @param paramString2
     * @param paramMultipartFile
     * @return
     */
    public abstract Info addLocationRoomImage(String paramString1,
                                              String paramString2, MultipartFile paramMultipartFile);

    /**
     * 添加房间照片
     *
     * @param paramString1
     * @param paramString2
     * @param paramMultipartFile
     * @return
     */
    public abstract Info addLocationRoomCaseImage(String paramString1,
                                                  String paramString2, MultipartFile paramMultipartFile);

    /**
     * 批量操作
     *
     * @param llist
     * @return info
     */
    public abstract List<Info> batchOperation(List<Location> llist);

    /**
     * 更新信息
     *
     * @param location
     * @return
     */
    public abstract Info updateLocationMainInfo(Location location);

    /**
     * 批量删除
     *
     * @param stringList
     * @return
     */
    public List<Info> batchOperationDelete(List<String> stringList);
}
