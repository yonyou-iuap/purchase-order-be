package com.yonyou.iuap.pap.entity;

import com.yonyou.iuap.baseservice.intg.ext.I18nEnum;
import com.yonyou.iuap.pap.base.i18n.MessageSourceUtil;

import java.util.HashMap;
import java.util.Map;


  
/**
 * 请购单详情表_物料表枚举定义,约定为po的fieldName_key为匹配原则
 * @date 2019-7-19 16:18:52
 */
public enum PurchaseOrderDetailEnum implements I18nEnum {
                ;
    private String code;
    private String value;
    private String i18nKey;

    PurchaseOrderDetailEnum(String code, String value, String i18nKey) {
        this.code = code;
        this.value = value;
        this.i18nKey = i18nKey;
    }

   @Override
    public Map getMappings() {
        Map result = new HashMap();
        for(PurchaseOrderDetailEnum item: PurchaseOrderDetailEnum.values()){
            result.put( item.name(), MessageSourceUtil.getMessage(item.i18nKey, item.value));
        }

        return result;
    }
}
