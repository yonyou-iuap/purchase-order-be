package com.yonyou.iuap.pap.service;
import com.yonyou.iuap.baseservice.intg.service.GenericIntegrateService;
import com.yonyou.iuap.baseservice.intg.support.ServiceFeature;
import com.yonyou.iuap.pap.dao.PurchaseOrderMapper;
import com.yonyou.iuap.pap.entity.PurchaseOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonyou.uap.busilog.annotation.LogConfig;

import java.util.List;
import static com.yonyou.iuap.baseservice.intg.support.ServiceFeature.*;

@Service("com.yonyou.iuap.pap.service.PurchaseOrderService")
/**
 * PurchaseOrder CRUD 核心服务,提供逻辑删除/乐观锁
 */
public class PurchaseOrderService extends GenericIntegrateService<PurchaseOrder>{


    private PurchaseOrderMapper purchaseOrderMapper;

    @Autowired
    public void setPurchaseOrderMapper(PurchaseOrderMapper purchaseOrderMapper) {
        this.purchaseOrderMapper = purchaseOrderMapper;
        super.setGenericMapper(purchaseOrderMapper);
    }

    @Override
    @LogConfig(busiCode = "purchaseOrder_deleteBatch", busiName = "请购单主表PurchaseOrder", operation = "请购单主表PurchaseOrder删除", templateId = "purchaseOrder_deleteBatch")
    public int deleteBatch(List< PurchaseOrder> list) {
    return super.deleteBatch(list);
    }

    @Override
    @LogConfig(busiCode = "purchaseOrder_insertSelective", busiName = "请购单主表PurchaseOrder", operation = "请购单主表PurchaseOrder添加", templateId = "purchaseOrder_insertSelective")
    public  PurchaseOrder insertSelective( PurchaseOrder entity) {
    return super.insertSelective(entity);
    }

    @Override
    @LogConfig(busiCode = "purchaseOrder_updateSelective", busiName = "请购单主表PurchaseOrder", operation = "请购单主表PurchaseOrder修改", templateId = "purchaseOrder_updateSelective")
    public  PurchaseOrder updateSelective( PurchaseOrder entity) {
    return super.updateSelective(entity);
    }





    /**
     * @CAU 可插拔设计
     * @return 向父类 GenericIntegrateService 提供可插拔的特性声明
     */
    @Override
    protected ServiceFeature[] getFeats() {
        return new ServiceFeature[]{ I18N_ENUM,UNION_REFERENCE,LOGICAL_DEL,BPM };
    }
}