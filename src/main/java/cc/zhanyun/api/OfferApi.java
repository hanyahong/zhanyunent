
package cc.zhanyun.api;


import cc.zhanyun.model.offer.Offer;
import cc.zhanyun.model.vo.OfferStatusVO;
import cc.zhanyun.model.vo.OfferVO;
import cc.zhanyun.repository.impl.OfferRepoImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller

@RequestMapping(value = {"/offer"}, produces = {"application/json"})

@Api(value = "/offer", description = "the offer API")
public class OfferApi {

    @Autowired
    private OfferRepoImpl service;

    @ApiOperation(value = "增加单条报价单", notes = "增加单条报价单", response = Void.class)

    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = Void.class), @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = Void.class)})

    @RequestMapping(value = {""}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseEntity<Void> offerPost(@ApiParam("项目属性") @RequestBody Offer offer)
            throws NotFoundException {

        this.service.addOffer(offer);

        return new ResponseEntity(HttpStatus.OK);

    }


    @ApiOperation(value = "删除报价单（未开启）", notes = "删除当前项目下的报价单", response = Void.class)

    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "删除成功", response = Void.class), @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = Void.class)})

    @RequestMapping(value = {"/{oid}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    public ResponseEntity<Void> offerOfferIdDelete(@ApiParam(value = "报价单ID", required = true) @PathVariable("oid") String oid)
            throws NotFoundException {

        this.service.delOffer(oid);

        return new ResponseEntity(HttpStatus.OK);

    }


    @ApiOperation(value = "查询单条项目报价单", notes = "报价单单条  ", response = Offer.class)

    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = Offer.class)})

    @RequestMapping(value = {"/{oid}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})

    @ResponseBody
    public Offer offerOidGet(@ApiParam(value = "报价单ID", required = true) @PathVariable("oid") String oid)
            throws NotFoundException {

        return this.service.selOfferById(oid);

    }


    @ApiOperation(value = "修改单条报价", notes = "修改单条报价", response = Void.class)

    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "修改成功", response = Void.class), @io.swagger.annotations.ApiResponse(code = 500, message = "响应失败", response = Void.class)})

    @RequestMapping(value = {"/{OfferId}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public ResponseEntity<Void> offerOfferIdPut(@ApiParam(value = "报价单ID", required = true) @PathVariable("oid") String oid, @ApiParam("项目属性") @RequestBody Offer offer)
            throws NotFoundException {

        this.service.addOffer(offer);

        return new ResponseEntity(HttpStatus.OK);

    }


    @ApiOperation(value = "修改单条报价状态", notes = "修改单条报价状态", response = Void.class)

    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "修改成功", response = Void.class), @io.swagger.annotations.ApiResponse(code = 500, message = "响应失败", response = Void.class)})

    @RequestMapping(value = {"status/{offer-id}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public ResponseEntity<Void> offerOidPut(@ApiParam(value = "报价单ID", required = true) @PathVariable("offer-id") String oid, @ApiParam("项目属性") @RequestBody OfferStatusVO osvo)
            throws NotFoundException {

        this.service.updateOfferStatus(osvo);

        return new ResponseEntity(HttpStatus.OK);

    }


    @ApiOperation(value = "查询报价单列表", notes = "查询全部已经创建的报价单列表", response = OfferVO.class, responseContainer = "List")

    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = OfferVO.class), @io.swagger.annotations.ApiResponse(code = 500, message = "服务器响应失败", response = OfferVO.class)})

    @RequestMapping(value = {""}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})

    @ResponseBody
    public List<OfferVO> offerGet()
            throws NotFoundException {

        return this.service.selOffer();

    }


    @ApiOperation(value = "查询不同状态下的单列表", notes = "查询不同状态下的报价单列表", response = OfferVO.class, responseContainer = "List")

    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 200, message = "获取成功", response = OfferVO.class), @io.swagger.annotations.ApiResponse(code = 500, message = "获取失败", response = Error.class)})

    @RequestMapping(value = {"/status/{status}"}, produces = {"application/json"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})

    @ResponseBody
    public List<OfferVO> offerStatusGet(@ApiParam(value = "报价状态", required = true) @PathVariable("status") Integer status)
            throws NotFoundException {

        return this.service.selOfferByStatus(status);

    }

}


