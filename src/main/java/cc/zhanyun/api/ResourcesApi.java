package cc.zhanyun.api;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.resources.*;
import cc.zhanyun.repository.impl.ResourcesRepoImpl;
import cc.zhanyun.service.ResourceListService;
import cc.zhanyun.service.ResourceService;
import cc.zhanyun.service.ResourceTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = {"/resources"}, produces = {"application/json"})
@Api(value = "/resources", description = "the resources API")
public class ResourcesApi {
    @Autowired
    private ResourceTypeService typeservice;
    @Autowired
    private ResourceService service;
    @Autowired
    private ResourcesRepoImpl repo;
    @Autowired
    private ResourceListService resourceListService;

    /**
     * 查询设备列表
     *
     * @param num
     * @param size
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(资源操作)查询设备列表", notes = "查询设备列表", response = Resources.class, responseContainer = "List")
    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Resources.class), @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = Error.class)})
    @RequestMapping(value = {"/{num}/{size}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<Resources> resourcesGet(@PathVariable("num") Integer num,
                                        @PathVariable("size") Integer size)
            throws NotFoundException {
        return this.service.selResourceList(num, size);
    }


    /**
     * 增加单条设备信息
     *
     * @param resources
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(资源操作)增加单条设备信息", notes = "增加单设备地信息", response = Void.class)
    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "添加成功", response = Void.class), @io.swagger.annotations.ApiResponse(code = 500, message = "添加失败", response = Void.class)})
    @RequestMapping(value = {""}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<ResourceStatusVO> resourcePost(@ApiParam("场地详细信息") @RequestBody Resources resources)
            throws NotFoundException {
        ResourceStatusVO in = this.service.addResource(resources);
        return new ResponseEntity(in, HttpStatus.OK);
    }


    /**
     * 单条删除设备
     *
     * @param oid
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(资源操作)单条删除设备信息", notes = "单条删除设备信息", response = Info.class)
    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "删除成功", response = Info.class), @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应错误", response = Info.class)})
    @RequestMapping(value = {"/{oid}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    public ResponseEntity<Info> typelistDelete(@ApiParam(value = "ID", required = true) @PathVariable("oid") String oid)
            throws NotFoundException {
        Info info = this.service.delResourceOne(oid);
        return new ResponseEntity(info, HttpStatus.OK);
    }


    /**
     * 单条查询设备详情
     *
     * @param oid
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(资源操作)查询单条设备详情", notes = "查询单条设备详情  ", response = Resources.class)
    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Resources.class), @io.swagger.annotations.ApiResponse(code = 404, message = "未找到查找内容", response = Void.class)})
    @RequestMapping(value = {"/{oid}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public Resources typelistGet(@ApiParam(value = "ID", required = true) @PathVariable("oid") String oid)
            throws NotFoundException {
        return this.service.selResourceOne(oid);
    }


    /**
     * 修改单条设备信息
     *
     * @param oid
     * @param resources
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(资源操作)修改单条设备", notes = "修改单条设备", response = Void.class)
    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "修改成功", response = Void.class), @io.swagger.annotations.ApiResponse(code = 500, message = "响应失败", response = Void.class)})
    @RequestMapping(value = {"/{oid}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public ResponseEntity<Void> typelistPut(@ApiParam(value = "ID", required = true) @PathVariable("oid") String oid, @ApiParam("项目属性") @RequestBody Resources resources)
            throws NotFoundException {
        this.service.updateResource(resources);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 查询用户资源分类库
     *
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(分类操作)查询用户资源分类库", notes = "查询用户资源分类库", response = ResourcesTypeOne.class, responseContainer = "List")
    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = ResourcesTypeOne.class), @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = ResourcesTypeOne.class)})
    @RequestMapping(value = {"/types"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<ResourcesTypeOne> resourcesTypeGet()
            throws NotFoundException {
        return this.typeservice.selTypeOfOneUser();
    }


    /**
     * 增加单条设备的分类
     *
     * @param resourcesTypeOne
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(分类操作)增加单条设备的分类", notes = "增加单条设备的分类", response = Info.class)
    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "添加成功", response = Info.class), @io.swagger.annotations.ApiResponse(code = 500, message = "添加失败", response = Void.class)})
    @RequestMapping(value = {"/type"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<Info> resourcesTypePost(@ApiParam("分类信息") @RequestBody ResourcesTypeOne resourcesTypeOne)
            throws NotFoundException {
        Info info = this.typeservice.addTypeOne(resourcesTypeOne);
        return new ResponseEntity(info, HttpStatus.OK);
    }

    /**
     * 初始化分类
     *
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(分类操作)初始化分类", notes = "初始化分类", response = Void.class)
    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "添加成功", response = Void.class), @io.swagger.annotations.ApiResponse(code = 500, message = "添加失败", response = Void.class)})
    @RequestMapping(value = {"/type/default"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<Void> resourcesTypesPost()
            throws NotFoundException {
        this.typeservice.saveTypeOfOneUser();
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 单条删除设备的分类
     *
     * @param oid
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(分类操作)单条删除设备的分类", notes = "单条删除设备的分类", response = Info.class)
    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "删除成功", response = Info.class), @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应错误", response = Info.class)})
    @RequestMapping(value = {"/type/{oid}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    public ResponseEntity<Info> resourcesTypeOidDelete(@ApiParam(value = "资源分类ID", required = true) @PathVariable("oid") String oid)
            throws NotFoundException {
        Info info = this.typeservice.delTypeOne(oid);
        return new ResponseEntity(info, HttpStatus.OK);
    }


    /**
     * 修改单条设备的分类
     *
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(分类操作)修改单条设备分类", notes = "修改单条设备分类", response = Info.class)
    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "修改成功", response = Info.class), @io.swagger.annotations.ApiResponse(code = 500, message = "响应失败", response = Void.class)})
    @RequestMapping(value = {"/type"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public ResponseEntity<Info> resourcesTypeoidPut(
            @ApiParam(value = "项目属性", required = true) @RequestBody ResourceTypeVO resourceTypeVO)
            throws NotFoundException {
        Info info = this.typeservice.updateTypeOne(resourceTypeVO);
        return new ResponseEntity(info, HttpStatus.OK);
    }


    /**
     * 单条上传资源图片
     *
     * @param oid
     * @param file
     * @return
     */

    @ApiOperation(value = "(图片操作)上传资源图片", notes = "上传资源图片")
    @RequestMapping(value = {"/image/{oid}"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public Info handleResourceImageUpload(@ApiParam(value = "资源ID", required = true) @PathVariable("oid") String oid, MultipartFile file) {
        return this.service.addResourceImage(file, oid);
    }


    /**
     * 批量操作资源(独立列表)
     *
     * @param resourceListList
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(独立列表)批量操作资源(独立列表)", notes = "批量操作资源(独立列表)", response = Info.class)
    @RequestMapping(value = {"/batchlist/{type}"}, produces = {"application/json"}, method = {RequestMethod.POST})
    public ResponseEntity<List<Info>> resourcesListBatchPost(
            @ApiParam(value = "资源分类", required = true) @PathVariable("type") String type,
            @ApiParam("详细列表") @RequestBody List<ResourceList> resourceListList)
            throws NotFoundException {
        List<Info> ilist = this.resourceListService.batchOperation(resourceListList, type);
        return new ResponseEntity(ilist, HttpStatus.OK);
    }

    /**
     * 查询设备独立列表
     *
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(独立列表)查询设备独立列表", notes = "查询设备独立列表  ", response = ResourceList.class)
    @RequestMapping(value = {"/list/{type}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<ResourceList> resourcesListGet(
            @ApiParam(value = "资源分类", required = true) @PathVariable("type") String type
    )
            throws NotFoundException {
        return this.resourceListService.selResourceListService(type, 0, 10000);
    }

    /**
     * 删除设备独立列表
     *
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(独立列表)删除设备独立列表", notes = "删除设备独立列表", response = Info.class)

    @RequestMapping(value = {"/list/{type}"}, produces = {"application/json"},
            method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    public ResponseEntity<Info> resourcesListDelete(
            @ApiParam(value = "资源分类", required = true) @PathVariable("type") String type)
            throws NotFoundException {
        Info info = resourceListService.delResourceListService(type);
        return new ResponseEntity(info, HttpStatus.OK);
    }

    /**
     * 增加设备独立列表
     *
     * @param resourceListList
     * @param type
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(独立列表)增加设备独立列表", notes = "增加设备独立列表", response = Info.class)
    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "添加成功", response = Info.class), @io.swagger.annotations.ApiResponse(code = 500, message = "添加失败", response = Void.class)})
    @RequestMapping(value = {"/list/{type}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<Info> resourcesListPost(
            @ApiParam(value = "资源分类", required = true) @PathVariable("type") String type,
            @ApiParam("详细列表") @RequestBody List<ResourceList> resourceListList)
            throws NotFoundException {
        Info info = resourceListService.addResourceListService(resourceListList, type);
        return new ResponseEntity(info, HttpStatus.OK);
    }

    /**
     * 修改设备的分类归属
     *
     * @param resourceTypeVO
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(资源分类操作)修改设备的分类归属", notes = "修改设备的分类归属", response = Info.class)
    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "添加成功", response = Info.class), @io.swagger.annotations.ApiResponse(code = 500, message = "添加失败", response = Void.class)})
    @RequestMapping(value = {"/classification/{type}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public ResponseEntity<Info> resourcesListPut(
            @ApiParam("属性") @RequestBody ResourceTypeVO resourceTypeVO)
            throws NotFoundException {
        Info info = resourceListService.updateType(resourceTypeVO);
        return new ResponseEntity(info, HttpStatus.OK);
    }

    /**
     * 按分类 查询 设备列表
     *
     * @param type
     * @param num
     * @param size
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(资源分类操作)按分类查询设备分类列表(手机端)", notes = "按分类查询设备分类列表(手机端)", response = Resources.class, responseContainer = "List")
    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Resources.class), @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = Resources.class)})
    @RequestMapping(value = {"/classification/{type}/{num}/{size}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<Resources> resourcesTypeGetList(
            @ApiParam(value = "分类", required = true) @PathVariable("type") String type,
            @ApiParam(value = "页数", required = true) @PathVariable("num") Integer num,
            @ApiParam(value = "条数", required = true) @PathVariable("size") Integer size)
            throws NotFoundException {
        return this.service.selResourceOneByType(type, num, size);
    }

}


