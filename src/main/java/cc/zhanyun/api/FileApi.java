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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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

    @ApiOperation(value = "上传文件(模板)", notes = "上传文件(模板)", response = Void.class, responseContainer = "List")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Void.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = cc.zhanyun.model.Error.class)})
    @RequestMapping(value = {"/upload"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public String handleFileUpload(
            @ApiParam(value = "Token", required = true) @RequestHeader("token") String token,
            MultipartFile file) {
        String info = this.service.uploadFile(file);

        return info;
    }

    /**
     * 下载报价单模板
     *
     * @param oid
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "下载文件（报价单）", notes = "下载文件（报价单）", response = Void.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Void.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = cc.zhanyun.model.Error.class)})
    @RequestMapping(value = {"/download/{oid}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public ResponseEntity<InputStreamResource> downloadFile(
            @ApiParam(value = "报价单ID", required = true) @PathVariable("oid") @RequestBody String oid)
            throws IOException {
        FileSystemResource file = this.service.downloadFile(oid);
        HttpHeaders headers = new HttpHeaders();

        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format(
                "attachment; filename=\"%s\"",
                new Object[]{file.getFilename()}));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ((ResponseEntity.BodyBuilder) ResponseEntity.ok().headers(
                headers))
                .contentLength(file.contentLength())
                .contentType(
                        MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }

    @ApiOperation(value = "批量上传文件", notes = "批量上传文件", response = Void.class, responseContainer = "List")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Void.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = cc.zhanyun.model.Error.class)})
    @RequestMapping(value = {"/batch/upload"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String handleFileUpload(
            @ApiParam(value = "Token", required = true) @RequestHeader("token") String token,
            HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");

        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = (MultipartFile) files.get(i);
            String name = file.getOriginalFilename();
            String url = "F:/";
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(new File(url + name + i)));

                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    return "You failed to upload " + name + " => "
                            + e.getMessage();
                }
            } else {
                return "You failed to upload " + name
                        + " because the file was empty.";
            }
        }

        return "upload successful";
    }

    @ApiOperation(value = "app更新", notes = "app更新", response = Void.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Void.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = cc.zhanyun.model.Error.class)})
    @RequestMapping(value = {"/download/app"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public ResponseEntity<InputStreamResource> downloadAppFile()
            throws IOException {
        FileSystemResource file = this.service.downloadFile("dddd");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format(
                "attachment; filename=\"%s\"",
                new Object[]{file.getFilename()}));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ((ResponseEntity.BodyBuilder) ResponseEntity.ok().headers(
                headers))
                .contentLength(file.contentLength())
                .contentType(
                        MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }

    @ApiOperation(value = "查询照片库", notes = "查询照片库", response = Image.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Image.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = cc.zhanyun.model.Error.class)})
    @RequestMapping(value = {"/image/{oid}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<ImageProVO> downloadImage(
            @ApiParam(value = "ID", required = true) @PathVariable("oid") @RequestBody String oid)
            throws IOException {
        return this.imageService.selImagesByOid(oid);
    }

    @ApiOperation(value = "删除图片", notes = "删除图片", response = Void.class)
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
