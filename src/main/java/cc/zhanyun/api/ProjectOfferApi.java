package cc.zhanyun.api;

import cc.zhanyun.model.*;
import cc.zhanyun.model.vo.OfferVO;
import cc.zhanyun.model.vo.ProjectOfferVO;
import cc.zhanyun.service.EmailService;
import cc.zhanyun.service.ProjectOfferFileModelService;
import cc.zhanyun.service.ProjectOfferModelService;
import cc.zhanyun.service.impl.ProjectOfferServiceImpl;
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
@RequestMapping(value = {"/projectoffer"}, produces = {"application/json"})
@Api(value = "/projectoffer", description = "the projectoffer API")
public class ProjectOfferApi {
    @Autowired
    private ProjectOfferServiceImpl service;
    @Autowired
    private EmailService emailservice;
    @Autowired
    private ProjectOfferModelService projectOfferModelService;
    @Autowired
    private ProjectOfferFileModelService projectOfferFileModelService;

    /**
     * 单条增加项目报价
     *
     * @param po
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(项目报价)增加项目报价", notes = "单独增加项目报价 ", response = Void.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "增加成功", response = ProOffInfo.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器无响应", response = Info.class)})
    @RequestMapping(value = {""}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<ProOffInfo> projectPost(
            @ApiParam("项目报价属性") @RequestBody ProjectOffer po)
            throws NotFoundException {
        ProOffInfo info = this.service.addProjectOfferOne(po);
        return new ResponseEntity(info, HttpStatus.OK);
    }

    /**
     * 单条删除项目报价
     *
     * @param oid
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(项目报价)删除项目报价", notes = "删除项目报价 ", response = Void.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "删除成功", response = Void.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "获取失败", response = Void.class)})
    @RequestMapping(value = {"/{oid}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    public ResponseEntity<Void> projectProjectIdDelete(
            @ApiParam(value = "项目ID", required = true) @PathVariable("oid") String oid)
            throws NotFoundException {
        this.service.delProjectOfferOne(oid);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 单条查询项目报价
     *
     * @param oid
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(项目报价)查询项目报价详情", notes = "项目报价详情 ", response = ProjectOffer.class, responseContainer = "List")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = ProjectOffer.class),
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取失败", response = ProjectOffer.class)})
    @RequestMapping(value = {"/{oid}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ProjectOffer projectProjectIdGet(
            @ApiParam(value = "项目ID", required = true) @PathVariable("oid") String oid)
            throws NotFoundException {
        return this.service.selProjectOfferOne(oid);
    }

    /**
     * 查询全部项目列表
     *
     * @param num
     * @param size
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(项目报价)获取全部项目报价列表", notes = "项目报价列表（该权限下的全部项目） ", response = ProjectOfferVO.class, responseContainer = "List")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = ProjectOfferVO.class),
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取失败", response = ProjectOfferVO.class)})
    @RequestMapping(value = {"/{num}/{size}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<ProjectOfferVO> projectsGet(
            @PathVariable("num") Integer num,
            @PathVariable("size") Integer size
    ) throws NotFoundException {
        return this.service.selProjectOfferList(num, size);
    }

    /**
     * 查询 不同状态 报价单列表
     *
     * @param status
     * @param num
     * @param size
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(项目报价)获取不同状态报价列表", notes = "获取不同状态报价列表 ", response = ProjectOfferVO.class, responseContainer = "List")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = ProjectOfferVO.class),
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取失败", response = ProjectOfferVO.class)})
    @RequestMapping(value = {"/status/{status}/{num}/{size}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<ProjectOfferVO> projectsOfStatusGet(
            @ApiParam(value = "项目状态", required = true) @PathVariable("status") Integer status,
            @ApiParam(value = "页数", required = true) @PathVariable Integer num,
            @ApiParam(value = "条数", required = true) @PathVariable Integer size)
            throws NotFoundException {
        return this.service.selProjectOfferOfStatus(status, num, size);
    }

    /**
     * 修改项目报价单状态
     *
     * @param oid
     * @param ovo
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(项目报价)修改项目报价状态", notes = "修改项目报价状态", response = Void.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "修改成功", response = Void.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "响应失败", response = Void.class)})
    @RequestMapping(value = {"/{oid}/status"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public ResponseEntity<Void> prijectOfferStatusOidPut(
            @ApiParam(value = "报价单ID", required = true) @PathVariable("oid") String oid,
            @ApiParam("项目属性") @RequestBody OfferVO ovo)
            throws NotFoundException {
        this.service.updateProjectOfferStatus(ovo);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 修改项目报价
     *
     * @param oid
     * @param po
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(项目报价)修改项目报价", notes = "修改项目报价", response = Void.class)
    @RequestMapping(value = {"/{oid}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public ResponseEntity<Info> projectOfferOidPut(
            @ApiParam(value = "报价单ID", required = true) @PathVariable("oid") String oid,
            @ApiParam("项目属性") @RequestBody ProjectOffer po)
            throws NotFoundException {
        Info in = this.service.updateProjectOfferOne(po);
        return new ResponseEntity(in, HttpStatus.OK);
    }

    /**
     * 上传效果图
     *
     * @param offeroid
     * @param file
     * @return
     */
    @ApiOperation(value = "(效果图)上传项目效果图", notes = "上传项目效果图")
    @RequestMapping(value = {"/image/{oid}"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public Info handleProjectOfferImageUpload(
            @ApiParam(value = "报价单ID", required = true) @PathVariable("oid") String offeroid,
            MultipartFile file) {
        return this.service.updateProjectImage(file, offeroid);
    }

    /**
     * 批量上传效果图
     *
     * @param multipartFileList
     * @param oid
     * @return List<Info>
     */
    @ApiOperation(value = "(批量效果图)批量上传项目效果图", notes = "批量上传项目效果图")
    @RequestMapping(value = {"/image/batch/{oid}"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public List<Info> batchProjectOfferImageUpload(
            @ApiParam(value = "报价单ID", required = true) @PathVariable("oid") String oid,
            List<MultipartFile> multipartFileList) {
        return this.service.batchUpdateProjectOfferImage(multipartFileList, oid);
    }

    /**
     * 邮件发送
     *
     * @param offerSend
     * @return
     */
    @ApiOperation(value = "(邮件发送)邮件发送", notes = "邮件发送", response = Info.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "修改成功", response = Info.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "响应失败", response = Info.class)})
    @RequestMapping(value = {"/email"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public Info handleSendEmail(
            @ApiParam("邮件属性") @RequestBody OfferSend offerSend) {
        return this.emailservice.sendOfferFileByMail(offerSend);
    }

    /**
     * 在线预览
     *
     * @param offeroid
     * @return
     */
    @ApiOperation(value = "(在线预览)在线预览报价单", notes = "在线预览报价单", response = Info.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "修改成功", response = Info.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "响应失败", response = Info.class)})
    @RequestMapping(value = {"/online/{oid}"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public Info lookOnline(
            @ApiParam(value = "报价单ID", required = true) @PathVariable("oid") String offeroid) {
        Info info = new Info();
        info.setStatus(this.service.selOfferOnline(offeroid));
        return info;
    }

    /**
     * 在线预览(不持久化)
     *
     * @param projectOffer
     * @return
     */
    @ApiOperation(value = "(不持久化)在线预览", notes = "在线预览(不持久化)", response = Info.class)
    @RequestMapping(value = {"/online"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public Info lookOnlineAndDelete(
            @ApiParam(value = "报价单json", required = true) @RequestBody ProjectOffer projectOffer) {
        Info info = new Info();
        info.setStatus(this.service.selOfferOnlineAndDelete(projectOffer));
        return info;
    }

    /**
     * (模板)新增报价模板
     *
     * @param projectOfferModel
     * @return
     */
    @ApiOperation(value = "(报价模板)新增报价模板", notes = "(模板)新增报价模板", response = Info.class)
    @RequestMapping(value = {"/model/"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public Info projectOfferModelPost(
            @ApiParam(value = "模板详情", required = true) @RequestBody ProjectOfferModel projectOfferModel) {

        return this.projectOfferModelService.addProjectOfferModel(projectOfferModel);
    }

    /**
     * (模板)单条查询报价模板
     *
     * @param oid
     * @return
     */
    @ApiOperation(value = "(报价模板)单条查询报价模板", notes = "(模板)单条查询报价模板", response = ProjectOfferModel.class)
    @RequestMapping(value = {"/model/{oid}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public ProjectOfferModel projectOfferModelGet(
            @ApiParam(value = "模板详情", required = true) @PathVariable("oid") String oid) {
        ProjectOfferModel projectOfferModel = this.projectOfferModelService.selProjectOfferModel(oid);
        return projectOfferModel;
    }

    /**
     * 多条查询
     * 删除模板
     *
     * @param oid
     * @return
     */
    @ApiOperation(value = "(报价模板)单条删除报价单模板", notes = "(模板)单条删除报价单模板", response = Info.class)
    @RequestMapping(value = {"/model/{oid}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    public Info projectOfferModelDelete(
            @ApiParam(value = "报价单模板ID", required = true) @PathVariable("oid") String oid) {
        return this.projectOfferModelService.delProjectOfferModel(oid);
    }

    /**
     * 按照状态 多条查询
     *
     * @return
     */
    @ApiOperation(value = "(报价模板)多条查询报价单模板列表", notes = "多条查询报价单模板列表", response = ProjectOfferModel.class, responseContainer = "list")
    @RequestMapping(value = {"/model/{num}/{size}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<ProjectOfferModel> projectOfferModelListGet(
            @ApiParam(value = "页数", required = true) @PathVariable("num") Integer num,
            @ApiParam(value = "条数", required = true) @PathVariable("size") Integer size
    ) {
        return this.projectOfferModelService.selProjectOfferModelList(num, size);
    }

    /**
     * 按照状态 多条查询
     *
     * @return
     */
    @ApiOperation(value = "(报价模板)按不同状态多条查询报价模板列表", notes = "(模板)按不同状态多条查询报价模板列表", response = ProjectOfferModel.class, responseContainer = "list")
    @RequestMapping(value = {"/model/status/{status}/{num}/{size}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<ProjectOfferModel> projectOfferModelListByStatusGet(
            @ApiParam(value = "状态值", required = true) @PathVariable("status") String status,
            @ApiParam(value = "页数", required = true) @PathVariable("num") Integer num,
            @ApiParam(value = "条数", required = true) @PathVariable("size") Integer size
    ) {
        return this.projectOfferModelService.selProjectOfferModelListByStatus(status, num, size);
    }

    /**
     * 修改状态
     *
     * @return
     */
    @ApiOperation(value = "(报价模板)修改报价模板状态", notes = "(模板)修改报价模板状态", response = Info.class)
    @RequestMapping(value = {"/model/status/{oid}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public Info projectOfferModelListByStatuPut(
            @ApiParam(value = "报价单模板ID", required = true) @PathVariable("oid") String oid,
            @ApiParam("属性") @RequestBody Info info) {
        Info info1 = this.projectOfferModelService.updateProjectOfferModelStatus(info);

        return info1;
    }


    /**
     * 默认下载模板设定
     *
     * @return
     */
    @ApiOperation(value = "(下载模板)默认下载模板设定", notes = "默认下载模板设定", response = Info.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "修改成功", response = Info.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "响应失败", response = Info.class)})
    @RequestMapping(value = {"/filemodel/default"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public Info projectOfferModelDefault(
            @ApiParam(value = "报价单ID", required = true) @RequestBody ProjectOfferFileModel projectOfferFileModel) {

        return this.projectOfferFileModelService.setDefaultModel(projectOfferFileModel);
    }

    /**
     * 查询默认下载模板
     *
     * @return
     */

    @ApiOperation(value = "(下载模板)查询默认下载模板", notes = "查询默认下载模板", response = ProjectOfferDefaultFileModel.class)
    @RequestMapping(value = {"/filemodel"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public ProjectOfferDefaultFileModel projectOfferModelListByStatuGet() {
        return this.projectOfferFileModelService.selDefaultModel();
    }


    /**
     * 查询模板库
     */
    @ApiOperation(value = "(下载模板)查询模板库", notes = "查询模板库", response = ProjectOfferFileModel.class, responseContainer = "list")
    @RequestMapping(value = {"/filemodel/{num}/{size}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<ProjectOfferFileModel> projectOfferModelListAllGet(
            @PathVariable("num") Integer num,
            @PathVariable("size") Integer size) {
        return this.projectOfferFileModelService.selProjectOfferFileModelAllService(num, size);
    }

    /**
     * 增加模板库模板
     */
    @ApiOperation(value = "(下载模板)增加模板库模板", notes = "增加模板库模板", response = ProjectOfferFileModel.class)
    @RequestMapping(value = {"/addfilemodel"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public Info projectOfferModelListAllPOST(@RequestBody ProjectOfferFileModel projectOfferFileModel) {
        return this.projectOfferFileModelService.addProjectOfferFileModel(projectOfferFileModel);
    }


}
