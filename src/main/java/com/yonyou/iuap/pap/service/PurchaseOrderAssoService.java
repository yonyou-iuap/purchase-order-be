package com.yonyou.iuap.pap.service;
import com.yonyou.iuap.baseservice.intg.support.ServiceFeature;
import com.yonyou.iuap.baseservice.service.GenericAssoService;
import com.yonyou.iuap.baseservice.vo.GenericAssoVo;
import com.yonyou.iuap.pap.entity.PurchaseOrder;
import com.yonyou.iuap.pap.dao.PurchaseOrderMapper;
import com.yonyou.iuap.pap.entity.PurchaseOrderDetail;
import com.yonyou.iuap.pap.service.PurchaseOrderDetailService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static com.yonyou.iuap.baseservice.intg.support.ServiceFeature.*;


/**
 * 主子表联合查询,修改服务
 * @date 2019年07月19日 下午04点18分51秒
 */
@Service("com.yonyou.iuap.pap.service.PurchaseOrderAssoService")
public class PurchaseOrderAssoService  extends GenericAssoService<PurchaseOrder> {

    private PurchaseOrderMapper mapper;
    /**
     * 注入主表mapper
     */
    @Autowired
    public void setService(PurchaseOrderMapper mapper) {
        this.mapper = mapper;
        super.setGenericMapper(mapper);
    }

    /**
     * 注入子表PurchaseOrderDetailService
     */
    @Autowired
    public void setPurchaseOrderDetailService(PurchaseOrderDetailService subService) {
        super.setSubService(PurchaseOrderDetail.class,subService);
    }
    

    @Override
    protected ServiceFeature[] getFeats() {
        return new ServiceFeature[]{ I18N_ENUM,UNION_REFERENCE,LOGICAL_DEL,BPM };
    }



}
