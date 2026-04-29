package com.zhoushijie.demo.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Bill implements Serializable {

    private Integer billid;
    private String goodsName;
    private String supplier;
    private Float billAmount;
    private Integer isPayment;
    private Date createTime;


    public Integer getBillid() {
        return billid;
    }

    public void setBillid(Integer billid) {
        this.billid = billid;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Float getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Float billAmount) {
        this.billAmount = billAmount;
    }

    public Integer getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(Integer isPayment) {
        this.isPayment = isPayment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "Bill{" +
                "billid=" + billid +
                ", goodsName='" + goodsName + '\'' +
                ", supplier='" + supplier + '\'' +
                ", billAmount=" + billAmount +
                ", isPayment=" + isPayment +
                ", createTime=" + createTime +
                '}';
    }
}

