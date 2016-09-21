package cc.zhanyun.api;

import cc.zhanyun.model.project.Project;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = {"/projects"}, produces = {"application/json"})
@Api(value = "/projects", description = "the projects API")
public class ProjectsApi {
    @ApiOperation(value = "获取全部项目", notes = "项目列表（该权限下的全部项目） ", response = Project.class, responseContainer = "List")
    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Project.class), @io.swagger.annotations.ApiResponse(code = 200, message = "获取失败", response = Project.class)})
    @RequestMapping(value = {""}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public ResponseEntity<List<Project>> projectsGet()
            throws NotFoundException {
        return new ResponseEntity(HttpStatus.OK);
    }
}


