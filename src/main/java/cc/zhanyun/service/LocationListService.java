package cc.zhanyun.service;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.location.LocationList;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hyh on 16-9-5.
 */
@Service
public interface LocationListService {
    /**
     * 添加场地列表(批量)
     *
     * @param locationListList
     * @return
     */
    public Info addLocationListService(List<LocationList> locationListList);

    /**
     * 按分类查询场地列表(批量)
     *
     * @return
     */

    public List<LocationList> selLocationListService(Integer num ,Integer size);

    /**
     * 删除场地列表(批量)
     *
     * @return
     */
    public Info delLocationListService();

    /**
     * 单条增加
     *
     * @param locationList
     * @return
     */
    public Info addLocationListOne(LocationList locationList);

    /**
     * 单条删除
     *
     * @param oid
     * @return
     */
    public Info delLocationListOne(String oid);

    /**
     * 批量操作
     *
     * @param locationListList
     * @return
     */
    public List<Info> batchOperation(List<LocationList> locationListList);


}
