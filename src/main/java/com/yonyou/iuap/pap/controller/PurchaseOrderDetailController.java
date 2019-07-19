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
            import com.yonyou.iuap.pap.service.PurchaseOrderDetailService;
            import com.yonyou.iuap.pap.entity.PurchaseOrderDetail;
        /**
        * 说明：请购单详情表_物料表 基础Controller——提供数据增、删、改、查等rest接口
        * 
        * @date 2019-7-19 16:18:52
        */
        @Api(value="请购单详情表_物料表接口",description = "请购单详情表_物料表基础Controller——提供数据增(CREATE)、删(DELETE、改(UPDATE)、查(READ)等rest接口")
        @Controller("com.yonyou.iuap.pap.controller.PurchaseOrderDetailController")
        @RequestMapping(value="/purchase-order/purchaseorderdetail")
        public class PurchaseOrderDetailController extends BaseController{

        private Logger logger = LoggerFactory.getLogger(PurchaseOrderDetailController.class);

        private PurchaseOrderDetailService purchaseOrderDetailService;

        @Autowired
        private StatCommonService statCommonService;

        @Autowired
        public void setPurchaseOrderDetailService(PurchaseOrderDetailService purchaseOrderDetailService) {
            this.purchaseOrderDetailService = purchaseOrderDetailService;
        }

        /**
        * 分页动态查询
        * @param pageRequest 分页及排序参数
        * @param searchMap  查询条件
        * @return 分页结果集
        */
        @ApiOperation(value = "分页动态查询",httpMethod = "POST" ,tags = "READ", response = PurchaseOrderDetail.class)
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

            Page<Map> page =  this.statCommonService.selectFieldsByPage(pageRequest, searchParams, "com.yonyou.iuap.pap.entity.PurchaseOrderDetail");
            EnumValueUtils.i18nEnumMapKeyToValue(page.getContent(),PurchaseOrderDetail.class);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data", page);
            return this.buildMapSuccess(map);
        }

        /**
        * 单条添加
        * @param entity 业务实体
        * @return 标准JsonResponse结构
        */
        @ApiOperation( value = "单条添加",tags = "CREATE", response = PurchaseOrderDetail.class)
        @RequestMapping(value = "/insertSelective"  ,method = {RequestMethod.POST,RequestMethod.PATCH})
        @ResponseBody
        public Object insertSelective(@RequestBody PurchaseOrderDetail entity) {
            try {
                entity = this.purchaseOrderDetailService.insertSelective(entity);
                PurchaseOrderDetail purchaseOrderDetail = this.purchaseOrderDetailService.findById(entity.getId());
                return this.buildSuccess(purchaseOrderDetail);
            } catch (Exception exp) {
                return this.buildError("msg", exp.getMessage(), RequestStatusEnum.FAIL_FIELD);
            }
        }

        /**
        * 单条更新
        * @param entity 业务实体
        * @return 标准JsonResponse结构
        */
        @ApiOperation( value = "单条更新" ,tags = "UPDATE", response = PurchaseOrderDetail.class)
        @RequestMapping(value = "/updateSelective" ,method = {RequestMethod.POST,RequestMethod.PATCH})
        @ResponseBody
        public Object updateSelective(@RequestBody PurchaseOrderDetail entity) {
            try {
                entity = this.purchaseOrderDetailService.updateSelective(entity);
                PurchaseOrderDetail purchaseOrderDetail = this.purchaseOrderDetailService.findById(entity.getId());
                return this.buildSuccess(purchaseOrderDetail);
            } catch (Exception exp) {
                return this.buildError("msg", exp.getMessage(), RequestStatusEnum.FAIL_FIELD);
            }
        }

        /**
        * 删除数据
        * @param listData
        * @param request
        * @param response
        * @return
        * @throws Exception
        */
        @ApiOperation(value = "删除数据",httpMethod = "POST",tags = "DELETE", response = PurchaseOrderDetail.class)
        @RequestMapping(value = "/deleteBatch")
        @ResponseBody
        public Object deleteBatch(@RequestBody List<PurchaseOrderDetail> listData, HttpServletRequest request, HttpServletResponse response) throws Exception {
            this.purchaseOrderDetailService.deleteBatch(listData);
            return super.buildSuccess();
        }


        }
