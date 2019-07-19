package com.yonyou.iuap.pap.dao;
import com.yonyou.iuap.pap.entity.PurchaseOrder;
import com.yonyou.iuap.baseservice.persistence.mybatis.mapper.GenericExMapper;
import com.yonyou.iuap.baseservice.persistence.mybatis.mapper.GenericMapper;
import com.yonyou.iuap.mybatis.anotation.MyBatisRepository;
import org.springframework.stereotype.Component;

        import org.apache.ibatis.annotations.Param;
        import com.yonyou.iuap.mybatis.type.PageResult;
        import com.yonyou.iuap.mvc.type.SearchParams;
        import org.springframework.data.domain.PageRequest;

@MyBatisRepository
@Component("com.yonyou.iuap.pap.dao.PurchaseOrderMapper")
public interface PurchaseOrderMapper extends GenericExMapper<PurchaseOrder> {

    PageResult<PurchaseOrder> selectAllBySonCode(@Param("page") PageRequest paramPageRequest, @Param("condition") SearchParams paramSearchParams);


}

