package com.zhoushijie.demo.mapper;


import com.zhoushijie.demo.entity.Bill;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BillMapper {
    //三种条件查询限制
    public List<Bill> findBillByWhere(Bill bill);

    public int insertBill(Bill bill);

    public Bill selectBill(Integer id);

    public int updateBill(Bill bill);

    @Delete("delete from billtable where bill_id=#{id}")
    public int deleteBillById(Integer id);

    //俩种条件查询限制
    public List<Bill> f1(Bill bill);

    //一种查询条件限制
    public List<Bill> f2(Bill bill);
}
