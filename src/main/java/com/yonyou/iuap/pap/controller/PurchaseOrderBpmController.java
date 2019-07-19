package com.yonyou.iuap.pap.controller;

import com.yonyou.iuap.baseservice.bpm.controller.GenericBpmController;
import com.yonyou.iuap.pap.entity.PurchaseOrder;
import com.yonyou.iuap.pap.service.PurchaseOrderBpmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yonyou.iuap.mvc.annotation.FrontModelExchange;
import com.yonyou.iuap.mvc.type.SearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 说明：请购单主表PurchaseOrder 流程控制Controller——提供流程提交、收回、审批回调等rest接口
 * 
 * @date 2019-7-19 16:18:52
 */
@Controller("com.yonyou.iuap.pap.controller.PurchaseOrderBpmController")
@RequestMapping(value="/purchase-order/purchaseorder")
public class PurchaseOrderBpmController extends GenericBpmController<PurchaseOrder>{
    
    private Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);


    private PurchaseOrderBpmService service;
    @Autowired
    public void setService(PurchaseOrderBpmService service) {
        this.service = service;
        super.setService(service);
    }

}
