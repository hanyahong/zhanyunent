package cc.zhanyun.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.image.Image;
import cc.zhanyun.model.vo.ImageProVO;
import cc.zhanyun.repository.impl.ImageRepoImpl;
import cc.zhanyun.service.ImageService;
import cc.zhanyun.service.impl.FileServiceImpl;

@RestController
@RequestMapping({"/file"})
@Api(value = "/file", description = "the file API")
public class FileApi {
    @Autowired
    private FileServiceImpl service;
    @Autowired
    private ImageRepoImpl image;
    @Autowired
    private ImageService imageService;

    /**
     * 上传上传
     *
     * @param oid
     * @param file
     * @return
     */
    @ApiOperation(value = "(文件操作)上传文件", notes = "上传文件(oid,文件库oid)", response = Info.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Info.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = Error.class)})
    @RequestMapping(value = {"/filelab/{oid}"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<Info> handleFileLibUpload(
            @ApiParam(value = "文件库oid", required = true) @PathVariable("oid") String oid,
            MultipartFile file) {

        Info info = this.service.uploadFile(file, oid);
        return new ResponseEntity(info, HttpStatus.OK);
    }

    /**
     * 批量上传文件
     *
     * @param oid
     * @param files
     * @return
     */
    @ApiOperation(value = "(文件操作)批量上传文件", notes = "批量上传文件(文件库oid)", response = Info.class, responseContainer = "list")
    @RequestMapping(value = {"/filelab/batch/{oid}"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<List<Info>> handleBatchFileUpload(
            @ApiParam(value = "文件库oid", required = true) @PathVariable("oid") String oid
            ,@RequestParam("file") List<MultipartFile> files) {
        List<Info> infoList = this.service.batchUploadFiles(files, oid);
        return new ResponseEntity(infoList, HttpStatus.OK);
    }

    /**
     * 下载文件库文件
     *
     * @param libOid
     * @param fileOid
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "(文件操作)文件下载", notes = "(文件库)文件下载", response = Void.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Void.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = cc.zhanyun.model.Error.class)})
    @RequestMapping(value = {"/filelab/{libOid}/{fileOid}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public ResponseEntity<InputStreamResource> downloadFileOfFileLib(
            @ApiParam(value = "文件库ID", required = true) @PathVariable("libOid") String libOid,
            @ApiParam(value = "文件ID", required = true) @PathVariable("fileOid") String fileOid)
            throws IOException {

        FileSystemResource file = new FileSystemResource(this.service.downloadFileOfFileLib(libOid, fileOid));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new Object[]{file.getFilename()}));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return (ResponseEntity
                .ok()
                .headers(headers))
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }


    /**
     * 下载报价单模板
     *
     * @param oid
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "(报价单库)下载报价单", notes = "下载文件报价单", response = Void.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Void.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = cc.zhanyun.model.Error.class)})
    @RequestMapping(value = {"/download/{oid}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public ResponseEntity<InputStreamResource> downloadFile(
            @ApiParam(value = "报价单ID", required = true) @PathVariable("oid") @RequestBody String oid)
            throws IOException {

        FileSystemResource file = new FileSystemResource(this.service.downloadFile(oid));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new Object[]{file.getFilename()}));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return (ResponseEntity
                .ok()
                .headers(headers))
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }

    /**
     * 文件库删除
     *
     * @param libOid
     * @param fileOid
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(文件操作)删除文件", notes = "删除文件", response = Void.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "删除成功", response = Void.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应错误", response = Void.class)})
    @RequestMapping(value = {"/filelab/{libOid}/{fileOid}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    public ResponseEntity<Info> oneOfFileLibDelete(
            @ApiParam(value = "文件库ID", required = true) @PathVariable("libOid") String libOid,
            @ApiParam(value = "文件ID", required = true) @PathVariable("fileOid") String fileOid)
            throws NotFoundException {
        Info info = this.service.delFileOfFileLib(libOid, fileOid);
        return new ResponseEntity(info, HttpStatus.OK);
    }

    /**
     * app更新
     *
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "(app库)app更新", notes = "app更新", response = Void.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Void.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = cc.zhanyun.model.Error.class)})
    @RequestMapping(value = {"/download/app"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public ResponseEntity<InputStreamResource> downloadAppFile()
            throws IOException {
        return null;
    }

    /**
     * 查询图片库
     *
     * @param oid
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "(图片库)查询照片库", notes = "查询照片库", response = Image.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Image.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = cc.zhanyun.model.Error.class)})
    @RequestMapping(value = {"/image/{oid}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<ImageProVO> downloadImage(
            @ApiParam(value = "ID", required = true) @PathVariable("oid") @RequestBody String oid)
            throws IOException {
        return this.imageService.selImagesByOid(oid);
    }

    /**
     * 图片库删除
     *
     * @param oid
     * @param ioid
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "(图片库)删除图片", notes = "删除图片", response = Void.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "删除成功", response = Void.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应错误", response = Void.class)})
    @RequestMapping(value = {"/image/{oid}/{ioid}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    public ResponseEntity<Info> clientoidDelete(
            @ApiParam(value = "图片库ID", required = true) @PathVariable("oid") String oid,
            @ApiParam(value = "图片ID", required = true) @PathVariable("ioid") String ioid)
            throws NotFoundException {
        Info info = this.imageService.delImageService(oid, ioid);
        return new ResponseEntity(info, HttpStatus.OK);
    }
}
