package com.yonyou.iuap.pap.dao;
import com.yonyou.iuap.pap.entity.PurchaseOrderDetail;
import com.yonyou.iuap.baseservice.persistence.mybatis.mapper.GenericExMapper;
import com.yonyou.iuap.baseservice.persistence.mybatis.mapper.GenericMapper;
import com.yonyou.iuap.mybatis.anotation.MyBatisRepository;
import org.springframework.stereotype.Component;


@MyBatisRepository
   @Component("com.yonyou.iuap.pap.dao.PurchaseOrderDetailMapper")
public interface PurchaseOrderDetailMapper extends GenericExMapper<PurchaseOrderDetail> {

}

