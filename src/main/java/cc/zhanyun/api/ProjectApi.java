package cc.zhanyun.api;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.project.Project;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = {"/project"}, produces = {"application/json"})
@Api(value = "/project", description = "the projects API")
public class ProjectApi {
    @ApiOperation(value = "增加项目", notes = "单独增加项目（暂未开放） ", response = Info.class)
    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "增加成功", response = Info.class), @io.swagger.annotations.ApiResponse(code = 500, message = "服务器无响应", response = Info.class)})
    @RequestMapping(value = {""}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<Info> projectPost(@ApiParam("项目属性") @RequestBody Project p)
            throws NotFoundException {
        return new ResponseEntity(HttpStatus.OK);
    }


    @ApiOperation(value = "删除项目", notes = "删除项目（暂未开放） ", response = Void.class)
    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "删除成功", response = Void.class), @io.swagger.annotations.ApiResponse(code = 500, message = "获取失败", response = Void.class)})
    @RequestMapping(value = {"/{project-id}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    public ResponseEntity<Void> projectProjectIdDelete(@ApiParam(value = "项目ID", required = true) @PathVariable("projectId") String projectId)
            throws NotFoundException {
        return new ResponseEntity(HttpStatus.OK);
    }


    @ApiOperation(value = "查询项目详情", notes = "项目详情 ", response = Project.class, responseContainer = "List")
    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Project.class), @io.swagger.annotations.ApiResponse(code = 200, message = "获取失败", response = Project.class)})
    @RequestMapping(value = {"/{project-id}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public ResponseEntity<List<Project>> projectProjectIdGet(@ApiParam(value = "项目ID", required = true) @PathVariable("projectId") String projectId)
            throws NotFoundException {
        return new ResponseEntity(HttpStatus.OK);
    }
}


