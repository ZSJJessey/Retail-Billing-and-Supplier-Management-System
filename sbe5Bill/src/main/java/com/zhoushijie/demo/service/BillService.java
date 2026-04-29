package com.zhoushijie.demo.service;

import com.zhoushijie.demo.entity.Bill;

import java.util.List;

public interface BillService {
    public List<Bill> findBillByWhere(Bill bill);

    public int insertBill(Bill bill);

    public Bill selectBill(Integer id);

    public int updateBill(Bill bill);

    public int deleteBillById(Integer id);
}
