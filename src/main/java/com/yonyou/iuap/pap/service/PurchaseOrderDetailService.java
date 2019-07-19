package com.yonyou.iuap.pap.service;
import com.yonyou.iuap.baseservice.intg.service.GenericIntegrateService;
import com.yonyou.iuap.baseservice.intg.support.ServiceFeature;
       
import com.yonyou.iuap.pap.dao.PurchaseOrderDetailMapper;
import com.yonyou.iuap.pap.entity.PurchaseOrderDetail;
          

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonyou.uap.busilog.annotation.LogConfig;

import java.util.List;
import static com.yonyou.iuap.baseservice.intg.support.ServiceFeature.*;

    
@Service("com.yonyou.iuap.pap.service.PurchaseOrderDetailService")
/**
 * PurchaseOrderDetail CRUD 核心服务,提供逻辑删除/乐观锁
 */
public class PurchaseOrderDetailService extends  GenericIntegrateService<PurchaseOrderDetail>{

    private PurchaseOrderDetailMapper purchaseOrderDetailMapper;
    @Autowired
    public void setPurchaseOrderDetailMapper(PurchaseOrderDetailMapper purchaseOrderDetailMapper) {

        this.purchaseOrderDetailMapper = purchaseOrderDetailMapper;
        super.setGenericMapper(purchaseOrderDetailMapper);
    }

    @Override
    @LogConfig(busiCode = "purchaseOrderDetail_deleteBatch", busiName = "请购单详情表_物料表", operation = "请购单详情表_物料表删除", templateId = "purchaseOrderDetail_deleteBatch")
    public int deleteBatch(List< PurchaseOrderDetail> list) {
    return super.deleteBatch(list);
    }

    @Override
    @LogConfig(busiCode = "purchaseOrderDetail_insertSelective", busiName = "请购单详情表_物料表", operation = "请购单详情表_物料表添加", templateId = "purchaseOrderDetail_insertSelective")
    public  PurchaseOrderDetail insertSelective( PurchaseOrderDetail entity) {
    return super.insertSelective(entity);
    }

    @Override
    @LogConfig(busiCode = "purchaseOrderDetail_updateSelective", busiName = "请购单详情表_物料表", operation = "请购单详情表_物料表修改", templateId = "purchaseOrderDetail_updateSelective")
    public  PurchaseOrderDetail updateSelective( PurchaseOrderDetail entity) {
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