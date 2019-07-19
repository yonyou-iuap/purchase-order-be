package com.yonyou.iuap.pap.service;

import com.yonyou.iuap.baseservice.bpm.service.GenericBpmService;
import com.yonyou.iuap.bpm.pojo.BPMFormJSON;
import com.yonyou.iuap.context.InvocationInfoProxy;
import com.yonyou.uap.wb.utils.JsonConverter;
import org.apache.commons.beanutils.PropertyUtils;
import com.yonyou.iuap.pap.dao.PurchaseOrderMapper;
import com.yonyou.iuap.pap.entity.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

@Service("com.yonyou.iuap.pap.service.PurchaseOrderBpmService")
public class PurchaseOrderBpmService extends GenericBpmService<PurchaseOrder>{

    private PurchaseOrderMapper purchaseOrderMapper;

    @Autowired
    public void setPurchaseOrderMapper(PurchaseOrderMapper purchaseOrderMapper) {
        this.purchaseOrderMapper = purchaseOrderMapper;
        super.setGenericMapper(purchaseOrderMapper);
    }

    @Override
    public BPMFormJSON buildBPMFormJSON(String processDefineCode, PurchaseOrder entity) {
        try {
            BPMFormJSON bpmform = new BPMFormJSON();
            bpmform.setProcessDefinitionKey(processDefineCode);
            String userName = InvocationInfoProxy.getUsername()== null ? "default user" : InvocationInfoProxy.getUsername();
            try {
                userName = URLDecoder.decode(userName,"utf-8");
                userName = URLDecoder.decode(userName,"utf-8");
            } catch (UnsupportedEncodingException e) {
                userName =InvocationInfoProxy.getUsername();
            }
            //title
            String title = userName + "提交的【请购单PurchaseOrder一主一子】单据编号是" + entity.getBpmBillCode() + ", 请审批";
            bpmform.setTitle(title);
            // 实例名
            bpmform.setFunCode("purchase-order");
            bpmform.setFunCodeName("请购单PurchaseOrder一主一子");
            // 单据id
            bpmform.setFormId(entity.getId().toString());
            // 单据号
            bpmform.setBillNo(entity.getBpmBillCode());
            // 制单人
            bpmform.setBillMarker(InvocationInfoProxy.getUserid());
            // 提交日期
            bpmform.setSubmitTime(new Date());
            // 其他变量
            bpmform.setOtherVariables(buildEntityVars(entity));
            // 单据url
            bpmform.setFormUrl("/purchase-order-fe/purchase-order-purchaseorder#/purchaseOrder?btnFlag=2&search_id="+ entity.getId());   // 单据url
// 流程实例名称
            bpmform.setProcessInstanceName(title);                                                                              // 流程实例名称
            // 流程审批后，执行的业务处理类(controller对应URI前缀)
            bpmform.setServiceClass("/purchase-order-be/purchase-order/purchaseorder");// 流程审批后，执行的业务处理类(controller对应URI前缀)
            //设置单据打开类型 uui/react
            bpmform.setFormType(BPMFormJSON.FORMTYPE_REACT);
            // 过滤其他表单变量,流程实例名称只存储关键信息
            BPMFormJSON tempFormJson = new BPMFormJSON();
            PropertyUtils.copyProperties(tempFormJson, bpmform);
            tempFormJson.setOtherVariables(null);
            bpmform.setProcessInstanceName(JsonConverter.toJson(tempFormJson));
            return bpmform;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}

