package com.yonyou.iuap.pap.entity;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yonyou.iuap.baseservice.bpm.entity.AbsBpmModel;
import com.yonyou.iuap.baseservice.intg.ext.I18nEnumCode;

import com.yonyou.iuap.baseservice.entity.annotation.Reference;
import com.yonyou.iuap.baseservice.entity.annotation.Associative;
import com.yonyou.iuap.baseservice.support.condition.Condition;
import com.yonyou.iuap.baseservice.support.condition.Match;
import com.yonyou.iuap.baseservice.support.generator.GeneratedValue;
import com.yonyou.iuap.baseservice.support.generator.Strategy;
import com.yonyou.iuap.baseservice.entity.annotation.CodingEntity;
import com.yonyou.iuap.baseservice.entity.annotation.CodingField;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.math.BigDecimal;

  
/**
 * 请购单详情表_物料表
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "PURCHASE_ORDER_DETAIL")
@Entity(name = "com.yonyou.iuap.pap.entity.PurchaseOrderDetail")
public class PurchaseOrderDetail extends AbsBpmModel  implements Serializable
{

    @Id
    @GeneratedValue
    @Condition
    protected String id;//ID
    @Override
    public String getId() {
        return id;
    }
    @Override
    public void setId(Serializable id){
        this.id= id.toString();
        super.id = id;
    }
    public void setId(String id) {
        this.id = id;
    }
        


    @Condition
    @Column(name="detail_name")
    private String detailName;        //物料名称

    public void setDetailName(String detailName){
        this.detailName = detailName;
    }
    public String getDetailName(){
        return this.detailName;
    }

    @Condition
    @Column(name="detail_count")
    private Integer detailCount;        //物料数量

    public void setDetailCount(Integer detailCount){
        this.detailCount = detailCount;
    }
    public Integer getDetailCount(){
        return this.detailCount;
    }

    @Condition
    @Column(name="order_id")
    private String orderId;        //请购单ID

    public void setOrderId(String orderId){
        this.orderId = orderId;
    }
    public String getOrderId(){
        return this.orderId;
    }

    @Condition
    @Column(name="detail_date")
    private String detailDate;        //需求日期

    public void setDetailDate(String detailDate){
        this.detailDate = detailDate;
    }
    public String getDetailDate(){
        return this.detailDate;
    }

        @Override
        public String getBpmBillCode() {
        return  DateUtil.format(new Date(), "yyyyMMddHHmmss"+new Random().nextInt(10))   ;
        }



}




