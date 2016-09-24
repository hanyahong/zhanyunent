package cc.zhanyun.api;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.location.Location;
import cc.zhanyun.model.location.LocationList;
import cc.zhanyun.model.vo.LocationImageOidVO;
import cc.zhanyun.model.vo.LocationVO;
import cc.zhanyun.service.LocationListService;
import cc.zhanyun.service.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = {"/location"}, produces = {"application/json"})
@Api(value = "/location", description = "the location API")
public class LocationApi {
    @Autowired
    private LocationService service;
    @Autowired
    private LocationListService locationListService;

    /**
     * 查询场地列表
     *
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "查询场地列表", notes = "查询场地列表", response = LocationVO.class, responseContainer = "List")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = LocationVO.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = LocationVO.class)})
    @RequestMapping(value = {"/{num}/{size}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<LocationVO> locationGet(
            @PathVariable("num") Integer num,
            @PathVariable("size") Integer size) throws NotFoundException {
        List<LocationVO> llist = this.service.selLocationList(num, size);
        return llist;
    }

    /**
     * 增加单条场地信息
     *
     * @param location
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "增加单条场地信息", notes = "增加单条场地信息", response = Void.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "添加成功", response = Void.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "添加失败", response = Void.class)})
    @RequestMapping(value = {""}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<LocationImageOidVO> locationPost(
            @ApiParam("场地详细信息") @RequestBody Location location)
            throws NotFoundException {
        LocationImageOidVO in = this.service.addLocation(location);

        return new ResponseEntity(in, HttpStatus.OK);
    }

    /**
     * 单条删除场地信息
     *
     * @param oid
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "单条删除场地信息", notes = "单条删除场地信息", response = Void.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "删除成功", response = Void.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应错误", response = Void.class)})
    @RequestMapping(value = {"/{oid}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    public ResponseEntity<Info> locaitonLocationIdDelete(
            @ApiParam(value = "客户ID", required = true) @PathVariable("oid") String oid)
            throws NotFoundException {
        Info in = this.service.delLocationInfo(oid);
        return new ResponseEntity(in, HttpStatus.OK);
    }

    /**
     * 查询单条场地详情
     *
     * @param oid
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "查询单条场地详情", notes = "查询单条场地详情  ", response = Location.class)
    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Location.class)})
    @RequestMapping(value = {"/{oid}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public Location locaitonLocationIdGet(
            @ApiParam(value = "客户ID", required = true) @PathVariable("oid") String oid)
            throws NotFoundException {
        return this.service.selLocationInfo(oid);
    }

    /**
     * 修改单条场地
     *
     * @param oid
     * @param location
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "修改单条场地", notes = "修改单条场地", response = Void.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "修改成功", response = Void.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "响应失败", response = Void.class)})
    @RequestMapping(value = {"/{oid}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public ResponseEntity<LocationImageOidVO> locaitonLocationIdPut(
            @ApiParam(value = "客户ID", required = true) @PathVariable("oid") String oid,
            @ApiParam("项目属性") @RequestBody Location location)
            throws NotFoundException {
        LocationImageOidVO in = this.service.updateLocation(location);
        return new ResponseEntity(in, HttpStatus.OK);
    }

    /**
     * 上传会议室图片
     *
     * @param loid
     * @param hoid
     * @param file
     * @return
     */
    @ApiOperation(value = "上传会议室图片", notes = "上传会议室图片")
    @RequestMapping(value = {"/house/{loid}/image/{roid}"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public Info handleLocationHouseImageUpload(
            @ApiParam(value = "场地ID", required = true) @PathVariable("loid") String loid,
            @ApiParam(value = "房间ID", required = true) @PathVariable("roid") String hoid,
            MultipartFile file) {
        return this.service.addLocationRoomImage(loid, hoid, file);
    }

    /**
     * 上传会议室案例图片
     *
     * @param loid
     * @param roid
     * @param file
     * @return
     */
    @ApiOperation(value = "上传会议室案例图片", notes = "上传会议室案例图片")
    @RequestMapping(value = {"/house/{loid}/{roid}"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public Info handleLocationHouseCaseImageUpload(
            @ApiParam(value = "场地ID", required = true) @PathVariable("loid") String loid,
            @ApiParam(value = "房间ID", required = true) @PathVariable("roid") String roid,
            MultipartFile file) {
        return this.service.addLocationRoomCaseImage(loid, roid, file);
    }

    /**
     * 上传场地效果图
     *
     * @param oid
     * @param file
     * @return
     */
    @ApiOperation(value = "上传场地效果图", notes = "上传场地效果图")
    @RequestMapping(value = {"/{oid}/image"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public Info handleFileUpload(
            @ApiParam(value = "场地ID", required = true) @PathVariable("oid") String oid,
            MultipartFile file) {
        return this.service.addLocationImage(oid, file);
    }


    /**
     * 批量操作场地信息
     *
     * @param llist
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "批量操作场地信息", notes = "批量操作场地信息", response = Void.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "添加成功", response = Void.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "添加失败", response = Void.class)})
    @RequestMapping(value = {"/batch"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<List<Info>> locationBatchPost(
            @ApiParam("场地详细信息") @RequestBody List<Location> llist)
            throws NotFoundException {
        List<Info> ilist = this.service.batchOperation(llist);

        return new ResponseEntity(ilist, HttpStatus.OK);
    }

    /**
     * 查询场地独立列表
     *
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "查询场地独立列表(批量)", notes = "查询场地独立列表(批量) ", response = LocationList.class, responseContainer = "List")
    @RequestMapping(value = {"/list"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<LocationList> locationListGet()
            throws NotFoundException {
        return locationListService.selLocationListService();
    }

    /**
     * 增加场地独立列表(批量)
     *
     * @param locationListList
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "增加场地独立列表(批量)", notes = "增加场地独立列表(批量)", response = Void.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "添加成功", response = Info.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "添加失败", response = Info.class)})
    @RequestMapping(value = {"/list"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<Info> locationListPost(
            @ApiParam("场地详细信息") @RequestBody List<LocationList> locationListList)
            throws NotFoundException {
        Info in = this.locationListService.addLocationListService(locationListList);

        return new ResponseEntity(in, HttpStatus.OK);
    }

    /**
     * 删除场地独立列表(独立)
     *
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "删除场地独立列表(批量)", notes = "删除场地独立列表(批量)", response = Info.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "删除成功", response = Info.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应错误", response = Info.class)})
    @RequestMapping(value = {"/list"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    public ResponseEntity<Info> locaitonListDelete()
            throws NotFoundException {
        Info in = this.locationListService.delLocationListService();
        return new ResponseEntity(in, HttpStatus.OK);
    }

    /**
     * 批量操作资源(独立)
     *
     * @param locationListList
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "批量操作资源(独立列表)", notes = "批量操作资源(独立列表)", response = Info.class, responseContainer = "List")
    @RequestMapping(value = {"/batchlist"}, produces = {"application/json"}, method = {RequestMethod.POST})
    public ResponseEntity<List<Info>> resourcesListBatchPost(@RequestBody List<LocationList> locationListList)
            throws NotFoundException {
        List<Info> ilist = this.locationListService.batchOperation(locationListList);
        return new ResponseEntity(ilist, HttpStatus.OK);
    }

    /**
     * 批量删除场地(详情)
     *
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "批量删除场地(详情)", notes = "批量删除场地(详情)", response = Info.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "删除成功", response = Info.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应错误", response = Info.class)})
    @RequestMapping(value = {"/batch"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    public ResponseEntity<Info> locaitonListDelete(List<String> stringList)
            throws NotFoundException {
        List<Info> infoList = this.service.batchOperationDelete(stringList);
        return new ResponseEntity(infoList, HttpStatus.OK);
    }


}
