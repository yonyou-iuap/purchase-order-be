package com.yonyou.iuap.pap.controller;
import com.yonyou.iuap.base.web.BaseController;
import com.yonyou.iuap.baseservice.controller.GenericAssoController;
import com.yonyou.iuap.baseservice.entity.annotation.Associative;
import com.yonyou.iuap.baseservice.vo.GenericAssoVo;
import com.yonyou.iuap.mvc.constants.RequestStatusEnum;
import com.yonyou.iuap.mvc.type.JsonResponse;
import com.yonyou.iuap.mvc.type.SearchParams;
import io.swagger.annotations.*;
import com.yonyou.iuap.pap.entity.PurchaseOrder;
import com.yonyou.iuap.pap.service.PurchaseOrderAssoService;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
/**
 * 说明：请购单主表PurchaseOrder 主子表Controller——提供主子数据的查询、保存等rest接口
 * 
 * @date 2019-7-19 16:18:52
 */
@Api(value="请购单主表PurchaseOrder接口",description = "请购单主表PurchaseOrder主子表Controller——提供主子数据的查询、保存等rest接口")
@Controller("com.yonyou.iuap.pap.controller.PurchaseOrderAssoController")
@RequestMapping(value = "/purchase-order/purchaseorder")
public class PurchaseOrderAssoController extends BaseController {

    private Logger log = LoggerFactory.getLogger(PurchaseOrderAssoController.class);
    private PurchaseOrderAssoService purchaseOrderAssoService;
    /**
     * 注入主子表service
     */
    @Autowired
    public void setService(PurchaseOrderAssoService service) {
        this.purchaseOrderAssoService = service;
    }

    /**
     * 主子表联合查询
     * @param pageRequest 主表分页参数
     * @param searchParams 查询参数
     * @return GenericAssoVo ,entity中保存的是单条主表数据,sublist中保存的是子表数据,一次性全部加载
     */
    @ApiOperation(value = "主子表联合查询",httpMethod = "GET",tags = "READ", response = PurchaseOrder.class)
    @RequestMapping(value = "/getAssoVo")
    @ResponseBody
    public Object  getAssoVo(PageRequest pageRequest,
                             SearchParams searchParams){
        Serializable id = MapUtils.getString(searchParams.getSearchMap(), "id");
        if (null==id){ return buildSuccess();}
        GenericAssoVo vo = purchaseOrderAssoService.getAssoVo(pageRequest, searchParams);
        JsonResponse result = this.buildSuccess("entity",vo.getEntity());//保证入参出参结构一致
        result.getDetailMsg().putAll(vo.getSublist());
        return  result;
    }

    /**
     * 主子表合并处理--主表单条保存
     * @param vo GenericAssoVo ,entity中保存的是单条主表数据,sublist中保存的是字表数据
     * @return 主表的业务实体
     */
    @ApiOperation( value = "主子表联合保存",httpMethod = "POST",tags = "CREATE", response = PurchaseOrder.class)
    @RequestMapping(value = "/saveAssoVo")
    @ResponseBody
    public Object  saveAssoVo(@RequestBody GenericAssoVo<PurchaseOrder> vo){
        Associative annotation= vo.getEntity().getClass().getAnnotation(Associative.class);
        if (annotation==null|| StringUtils.isEmpty(annotation.fkName())){
            return buildError("","Nothing got @Associative or without fkName",RequestStatusEnum.FAIL_FIELD);
        }
        Object result =purchaseOrderAssoService.saveAssoVo(vo,annotation);
        return this.buildSuccess(result) ;
    }

    /**
     * 主子表合并处理--主表单条删除,若取消级联删除请在主实体上注解改为@Associative(fkName = "orderId",deleteCascade = false)
     * @see GenericAssoController
     * @param entities 待删除业务实体
     * @return 删除成功的实体
     */
    @ApiOperation( value = "主子表级联删除",httpMethod = "POST",tags = "DELETE", response = PurchaseOrder.class)
    @RequestMapping(value = "/deleAssoVo")
    @ResponseBody
    public Object  deleAssoVo(@RequestBody PurchaseOrder... entities){
        if (entities.length==0){
            return this.buildGlobalError("requst entity must not be empty");
        }
        Associative annotation = entities[0].getClass().getAnnotation(Associative.class);
        if (annotation != null && !StringUtils.isEmpty(annotation.fkName())) {
            int result =0;
            for (PurchaseOrder entity:entities){
                if (StringUtils.isEmpty(entity.getId())) {
                    return this.buildError("ID", "ID field is empty:"+entity.toString(), RequestStatusEnum.FAIL_FIELD);
                } else if (StringUtils.isEmpty(entity.getTs())) {
                    return this.buildError("TS", "TS field is empty:"+entity.toString(), RequestStatusEnum.FAIL_FIELD);
                } else {
                    result += this.purchaseOrderAssoService.deleAssoVo(entity, annotation);
                }
            }
            return this.buildSuccess(result + " rows(both entity and its sub-entities) has been deleted!");
        } else {
            return this.buildError("", "Nothing got @Associative or without fkName", RequestStatusEnum.FAIL_FIELD);
        }
    }



}
