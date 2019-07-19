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
 * 请购单主表PurchaseOrder
 * @date 2019年07月19日 下午04点18分51秒
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "PURCHASE_ORDER")
@Associative(fkName = "orderId")
@CodingEntity(codingField="orderCode")
@Entity(name = "com.yonyou.iuap.pap.entity.PurchaseOrder")
public class PurchaseOrder extends AbsBpmModel  implements Serializable
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
    


    @Condition(match=Match.LIKE)
    @Column(name="order_name")
    private String orderName;        //请购单名称

    public void setOrderName(String orderName){
        this.orderName = orderName;
    }
    public String getOrderName(){
        return this.orderName;
    }
    

    @Condition(match=Match.LIKE)
    @Column(name="order_code")
    @CodingField(code="masterdetail-one")
    private String orderCode;        //请购单编号

    public void setOrderCode(String orderCode){
        this.orderCode = orderCode;
    }
    public String getOrderCode(){
        return this.orderCode;
    }
    

    @Condition
    @Column(name="order_price")
    private BigDecimal orderPrice;        //请购单价格

    public void setOrderPrice(BigDecimal orderPrice){
        this.orderPrice = orderPrice;
    }
    public BigDecimal getOrderPrice(){
        return this.orderPrice;
    }
    

    @Condition
    @Column(name="order_date")
    private String orderDate;        //请购单申请日期

    public void setOrderDate(String orderDate){
        this.orderDate = orderDate;
    }
    public String getOrderDate(){
        return this.orderDate;
    }
    

    @Condition(match=Match.EQ)
    @Transient
    private String orderDeptName;        //申请部门

    public void setOrderDeptName(String orderDeptName){
        this.orderDeptName = orderDeptName;
    }
    public String getOrderDeptName(){
        return this.orderDeptName;
    }
    

    @Condition
    @Column(name="order_dept")
    @Reference(code="newdept",srcProperties={ "refname"}, desProperties={ "orderDeptName"})
    private String orderDept;        //申请部门

    public void setOrderDept(String orderDept){
        this.orderDept = orderDept;
    }
    public String getOrderDept(){
        return this.orderDept;
    }
    

    @Condition
    @Column(name="order_type")
    @I18nEnumCode(clazz = PurchaseOrderEnum.class,target ="orderTypeEnumValue" )
    private String orderType;        //类型

    public void setOrderType(String orderType){
        this.orderType = orderType;
    }
    public String getOrderType(){
        return this.orderType;
    }
    
    @Transient
    private String orderTypeEnumValue;   //类型

    public void setOrderTypeEnumValue(String orderTypeEnumValue){
        this.orderTypeEnumValue = orderTypeEnumValue;
    }
    public String getOrderTypeEnumValue(){
        return this.orderTypeEnumValue;
    }

        @Override
        public String getBpmBillCode() {
        return getOrderCode();
        }



}




