package cc.zhanyun.api;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.client.Clientmanager;
import cc.zhanyun.model.client.ClientmanagerList;
import cc.zhanyun.model.vo.ClientVO;
import cc.zhanyun.service.ClientListService;
import cc.zhanyun.service.impl.ClientServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = {"/client"}, produces = {"application/json"})
@Api(value = "/client", description = "the client API")

public class ClientApi {
    @Autowired
    private ClientServiceImpl service;
    @Autowired
    private ClientListService clientListService;

    /**
     * 单条删除客户
     *
     * @param oid
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "单条删除客户信息", notes = "单条删除客户信息", response = Void.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "删除成功", response = Void.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应错误", response = Void.class)})
    @RequestMapping(value = {"/{oid}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    public ResponseEntity<Void> clientoidDelete(
            @ApiParam(value = "客户ID", required = true) @PathVariable("oid") String oid)
            throws NotFoundException {
        this.service.delClientInfo(oid);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 查询单条客户信息
     *
     * @param oid
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "查询单条客户详情", notes = "查询单条客户详情  ", response = Clientmanager.class)
    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Clientmanager.class)})
    @RequestMapping(value = {"/{oid}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public Clientmanager clientoidGet(
            @ApiParam(value = "客户ID", required = true) @PathVariable("oid") String oid)
            throws NotFoundException {
        Clientmanager client = this.service.selClientInfo(oid);
        return client;
    }

    /**
     * 修改单条客户
     *
     * @param oid
     * @param client
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "修改单条客户", notes = "修改单条客户", response = Void.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "修改成功", response = Void.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "响应失败", response = Void.class)})
    @RequestMapping(value = {"/{oid}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public ResponseEntity<Void> clientoidPut(
            @ApiParam(value = "客户ID", required = true) @PathVariable("oid") String oid,
            @ApiParam("项目属性") @RequestBody Clientmanager client)
            throws NotFoundException {
        this.service.updateClientOne(client);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 查询客户列表
     *
     * @param num
     * @param size
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "查询客户列表", notes = "查询客户列表", response = ClientVO.class, responseContainer = "List")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = ClientVO.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = Void.class)})
    @RequestMapping(value = {"/{num}/{size}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<ClientVO> clientGet(@PathVariable("num") Integer num,
                                    @PathVariable("num") Integer size) throws NotFoundException {

        return this.service.selClientList(num, size);
    }

    /**
     * 单条增加客户信息
     *
     * @param clientmanager
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "增加单条客户信息", notes = "增加单条客户信息", response = Void.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "添加成功", response = Void.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "添加失败", response = Void.class)})
    @RequestMapping(value = {""}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<Info> clientPost(
            @ApiParam("客户详细信息") @RequestBody Clientmanager clientmanager)
            throws NotFoundException {
        Info in = this.service.addClientOne(clientmanager);
        return new ResponseEntity(in, HttpStatus.OK);
    }

    /**
     * 客户上传头像
     *
     * @param oid
     * @param file
     * @return
     */
    @ApiOperation(value = "上传头像", notes = "上传头像", response = Info.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Info.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = cc.zhanyun.model.Error.class)})
    @RequestMapping(value = {"/upload/{oid}"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<Info> handleFileUpload(
            @ApiParam(value = "客户ID", required = true) @PathVariable("oid") String oid,
            MultipartFile file) {
        Info in = this.service.addClientImage(file, oid);
        return new ResponseEntity(in, HttpStatus.OK);
    }

    /**
     * 客户批量操作
     *
     * @param clientmanagerList
     * @return
     * @throws NotFoundException
     */

    @ApiOperation(value = "客户批量操作(原始)", notes = "客户批量操作(原始)", response = Info.class, responseContainer = "List")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Info.class, responseContainer = "List"),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = Info.class, responseContainer = "List")})
    @RequestMapping(value = {"/batch"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public List<Info> clientBatchGet(
            @ApiParam("客户详细信息") @RequestBody List<Clientmanager> clientmanagerList
    ) throws NotFoundException {
        return this.service.batchClient(clientmanagerList);
    }

    /**
     * 客户批量操作
     *
     * @param clientmanagerListList
     * @return
     * @throws NotFoundException
     */

    @ApiOperation(value = "客户批量操作(独立列表)", notes = "客户批量操作(独立列表)", response = Info.class, responseContainer = "List")
    @RequestMapping(value = {"/list/batch"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public List<Info> clientListBatchPost(
            @ApiParam("客户详细信息") @RequestBody List<ClientmanagerList> clientmanagerListList
    ) throws NotFoundException {
        return this.clientListService.batchOperation(clientmanagerListList);
    }

    /**
     * 查询客户独立列表
     *
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "查询客户独立列表", notes = "查询客户独立列表", response = ClientmanagerList.class, responseContainer = "List")
    @RequestMapping(value = {"/list"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<ClientmanagerList> clientListGet() throws NotFoundException {
        return this.clientListService.selClientListByUid(0, 1000);
    }
}
