package com.zhoushijie.demo.controller;

import com.zhoushijie.demo.entity.Bill;
import com.zhoushijie.demo.mapper.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    BillMapper billMapper;

    @GetMapping("/0")
    public List<Bill> findbill(Bill bill) {
        bill = new Bill();
        bill.setGoodsName("手机");
        bill.setSupplier("华为");
        bill.setIsPayment(1);
        List<Bill> bills = billMapper.findBillByWhere(bill);
        System.out.println(bills);
        return bills;
    }


    @GetMapping("/1")
    public List<Bill> findbill1(Bill bill) {
        bill = new Bill();
        bill.setGoodsName("笔记本");
        bill.setSupplier("苹果");
        List<Bill> bills = billMapper.f1(bill);
        System.out.println(bills);
        return bills;
    }

    @GetMapping("/2")
    public List<Bill> findbill2(Bill bill) {
        bill = new Bill();
        bill.setGoodsName("鼠标");
        List<Bill> bills = billMapper.f2(bill);
        System.out.println(bills);
        return bills;
    }
}
