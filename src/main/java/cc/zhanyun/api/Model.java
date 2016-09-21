package cc.zhanyun.api;

import cc.zhanyun.model.Info;
import cc.zhanyun.model.ProjectOfferFileModel;
import cc.zhanyun.service.ProjectOfferFileModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/model"}, produces = {"application/json"})
@Api(value = "/model", description = "the projectoffer API")
/**
 * Created by hyh on 16-9-14.
 */
public class Model {
    @Autowired
    private ProjectOfferFileModelService projectOfferFileModelService;


    /**
     * 增加模板库模板
     */
    @ApiOperation(value = "查询模板库", notes = "查询模板库", response = Info.class)
    @RequestMapping(value = {"/addfilemodel"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public Info modelPOST(@RequestBody ProjectOfferFileModel projectOfferFileModel) {
        return this.projectOfferFileModelService.addProjectOfferFileModel(projectOfferFileModel);
    }
}
