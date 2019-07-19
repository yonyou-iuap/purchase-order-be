package com.yonyou.iuap.pap.controller;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yonyou.iuap.base.web.BaseController;
import com.yonyou.iuap.mvc.constants.RequestStatusEnum;
import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.iuap.mvc.constants.RequestStatusEnum;
import com.yonyou.iuap.mvc.annotation.FrontModelExchange;
import com.yonyou.iuap.baseservice.persistence.support.CustomSelectListable;
import com.yonyou.iuap.baseservice.persistence.support.SimpleCustomSelectList;
import com.yonyou.iuap.baseservice.statistics.service.StatCommonService;
import com.yonyou.iuap.baseservice.intg.ext.EnumValueUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import com.yonyou.iuap.pap.service.PurchaseOrderService;
import com.yonyou.iuap.pap.entity.PurchaseOrder;
/**
* 说明：请购单主表PurchaseOrder 基础Controller——提供数据增(CREATE)、删(DELETE、改(UPDATE)、查(READ)等rest接口
* 
* @date 2019-7-19 16:18:52
*/
@Api(value="请购单主表PurchaseOrder接口",description = "请购单主表PurchaseOrder基础Controller——提供数据增(CREATE)、删(DELETE、改(UPDATE)、查(READ)等rest接口")
@Controller("com.yonyou.iuap.pap.controller.PurchaseOrderController")
@RequestMapping(value="/purchase-order/purchaseorder")
public class PurchaseOrderController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);

    private PurchaseOrderService purchaseOrderService;

    @Autowired
    public void setPurchaseOrderService(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @Autowired
    private StatCommonService statCommonService;

    /**
    * 简单分页查询
    * @param pageRequest
    * @param searchParams
    * @return 实体分页结果集
    */
    @ApiOperation(value = "分页查询",tags = "READ", response = PurchaseOrder.class)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(PageRequest pageRequest, SearchParams searchParams) {
        if (pageRequest.getPageSize() == 1) {
            Integer allCount = Integer.MAX_VALUE-1;
            pageRequest = new PageRequest(pageRequest.getPageNumber(), allCount, pageRequest.getSort());
        }
        Page<PurchaseOrder> page = this.purchaseOrderService.selectAllByPage(pageRequest, searchParams);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", page);
        return this.buildMapSuccess(map);
    }

    /**
    * 分页动态查询
    * @param pageRequest 分页及排序参数
    * @param searchMap  查询条件
    * @return 分页结果集
    */
    @ApiOperation(value = "分页动态查询",httpMethod = "POST" ,tags = "READ", response = PurchaseOrder.class)
    @ApiResponse(response = Page.class, code = 200, message = "接口返回对象参数")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(PageRequest pageRequest, @RequestBody Map<String, Object> searchMap) {
        SearchParams searchParams = new SearchParams();
        searchParams.setSearchMap(searchMap);

        if (pageRequest.getPageSize() == 1) {
        Integer allCount = Integer.MAX_VALUE-1;
        pageRequest = new PageRequest(pageRequest.getPageNumber(), allCount, pageRequest.getSort());
        }

        Page<Map> page =  this.statCommonService.selectFieldsByPage(pageRequest, searchParams, "com.yonyou.iuap.pap.entity.PurchaseOrder");
        EnumValueUtils.i18nEnumMapKeyToValue(page.getContent(),PurchaseOrder.class);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", page);
        return this.buildMapSuccess(map);
    }

    /**
    * 分组动态查询
    * @param pageRequest 分页及排序参数
    * @param searchMap  查询条件
    * @return 分页结果集
    */
    @ApiOperation(value = "分组动态查询",httpMethod = "POST",tags = "READ", response = PurchaseOrder.class)
    @ApiResponse(response = Page.class, code = 200, message = "接口返回对象参数")
    @RequestMapping(value = "/listByGroup", method = RequestMethod.POST)
    @ResponseBody
    public Object listByGroup(PageRequest pageRequest, @RequestBody Map<String, Object> searchMap) {
        SearchParams searchParams = new SearchParams();
        searchParams.setSearchMap(searchMap);
        if (pageRequest.getPageSize() == 1) {
            Integer allCount = Integer.MAX_VALUE-1;
            pageRequest = new PageRequest(pageRequest.getPageNumber(), allCount, pageRequest.getSort());
        }
        Page<Map> page = this.statCommonService.selectAllByPage(pageRequest, searchParams, "com.yonyou.iuap.pap.entity.PurchaseOrder");
        EnumValueUtils.i18nEnumMapKeyToValue(page.getContent(),PurchaseOrder.class);
        return buildSuccess(page);
    }


    /**
    * 动态枚举查询
    * @param searchMap 查询条件
    * @return 动态枚举结果集
    */
    @ApiOperation(value = "动态枚举查询",httpMethod = "POST",tags = "READ", response = PurchaseOrder.class)
    @RequestMapping(value = "/distinct", method = RequestMethod.POST)
    @ResponseBody
    public Object distinct(@RequestBody Map<String, Object> searchMap) {
        SearchParams searchParams = new SearchParams();
        Map<String, Object> resultMap = new HashMap<>();
        searchParams.setSearchMap(searchMap);
        List<Map> list = statCommonService.findDistinct(searchParams, "com.yonyou.iuap.pap.entity.PurchaseOrder");
        EnumValueUtils.i18nEnumMapKeyToValue(list,PurchaseOrder.class);
        resultMap.put("content", list);
        return buildSuccess(list);
    }

    /**
    * 单条添加
    * @param entity 业务实体
    * @return 标准JsonResponse结构
    */
    @ApiOperation( value = "单条添加",tags = "CREATE", response = PurchaseOrder.class)
    @RequestMapping(value = "/insertSelective", method = {RequestMethod.POST,RequestMethod.PATCH})
    @ResponseBody
    public Object insertSelective(@RequestBody PurchaseOrder entity) {
        try {
            this.purchaseOrderService.insertSelective(entity);
            return this.buildSuccess(entity);
        }catch(Exception exp) {
            return this.buildError("msg", exp.getMessage(), RequestStatusEnum.FAIL_FIELD);
        }
    }


    /**
    * 单条修改
    * @param entity 业务实体
    * @return 标准JsonResponse结构
    */
    @ApiOperation( value = "单条更新" ,tags = "UPDATE", response = PurchaseOrder.class)
    @RequestMapping(value = "/updateSelective", method = {RequestMethod.POST,RequestMethod.PATCH})
    @ResponseBody
    public Object updateSelective(@RequestBody PurchaseOrder entity) {
        try {
            this.purchaseOrderService.updateSelective(entity);
            return this.buildSuccess(entity);
        }catch(Exception exp) {
            return this.buildError("msg", exp.getMessage(), RequestStatusEnum.FAIL_FIELD);
        }
    }





    /**
    * 批量添加
    * @param listData 业务实体列表
    * @return 标准JsonResponse结构
    */
    @ApiOperation( value = "批量添加",httpMethod = "POST",tags = "CREATE", response = PurchaseOrder.class)
    @RequestMapping(value = "/saveMultiple")
    @ResponseBody
    public Object saveMultiple(@RequestBody List<PurchaseOrder> listData) {
        this.purchaseOrderService.saveBatch(listData);
        return this.buildSuccess();
    }

/**
    * 批量修改
    * @param listData 业务实体列表
    * @return
    */
    @ApiOperation(value = "批量标准JsonResponse结构修改",httpMethod = "POST",tags = "UPDATE", response = PurchaseOrder.class)
    @RequestMapping(value = "/updateMultiple")
    @ResponseBody
    public Object updateMultiple(@RequestBody List<PurchaseOrder> listData) throws Exception {
        this.purchaseOrderService.saveBatch(listData);
        return super.buildSuccess();
    }


    /**
    * 批量删除
    * @param listData 业务实体列表
    * @return 标准JsonResponse结构
    * @throws Exception
    */
    @ApiOperation(value = "批量删除",httpMethod = "POST",tags = "DELETE", response = PurchaseOrder.class)
    @RequestMapping(value = "/deleteBatch")
    @ResponseBody
    public Object deleteBatch(@RequestBody List<PurchaseOrder> listData) throws Exception {
        this.purchaseOrderService.deleteBatch(listData);
        return super.buildSuccess();
    }


        }
