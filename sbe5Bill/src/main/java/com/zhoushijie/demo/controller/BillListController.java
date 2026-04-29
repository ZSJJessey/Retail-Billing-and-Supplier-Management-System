package com.zhoushijie.demo.controller;

import com.zhoushijie.demo.entity.Bill;
import com.zhoushijie.demo.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class BillListController {
    @Autowired
    BillService billService;

    @GetMapping("/tobillList")
    public String tobillList(Model model) {
        List<Bill> billByWhere = billService.findBillByWhere(null);
        model.addAttribute("billList", billByWhere);
        return "page/billList";
    }

    //多条件查询
    @GetMapping("/searchByWhere")
    public String searchByWhere(Bill bill, Model model) {
        List<Bill> billByWhere = billService.findBillByWhere(bill);
        model.addAttribute("billList", billByWhere);
        model.addAttribute("billListGoodsName", bill.getGoodsName());
        model.addAttribute("billListSupplierName", bill.getSupplier());
        model.addAttribute("billListIsPaymentName", bill.getIsPayment());
        return "page/billList";
    }

    //billList添加按钮
    @GetMapping("/tobillAdd")
    public String tobillAdd() {
        return "page/billAdd";
    }

    @PostMapping("/toAddBill")
    public String toAddBill(Bill bill, Model model) {
        bill.setCreateTime(new Date());
        int i = billService.insertBill(bill);
        if (i > 0) {
            model.addAttribute("addright", "添加成功");
            return "redirect:/tobillList";//请求之间的调用
        } else {
            model.addAttribute("adderror", "添加失败请检查数据");
            return "page/billAdd";
        }
        //return   "page/billList";//去页面 没有数据
        // return "page/billAdd";
    }

    //bilList查看按钮
    @GetMapping("/tobillView")
    public String tobillView(Integer id, Model model) {
        Bill bill = billService.selectBill(id);
        model.addAttribute("bill", bill);

        return "page/billView";
    }


    //billList修改按钮
    @GetMapping("/tobillUpdate")
    public String tobillUpdate(Integer id, Model model) {
        //从后台拿数据到前台,用selectBill方法先查看信息再用updateBill方法更新数据
        Bill bill = billService.selectBill(id);
        model.addAttribute("bill", bill);
        return "page/billUpdate";
    }

    @PostMapping("/toUpdate")
    public String toUpdate(Bill bill, Model model) {
        int i = billService.updateBill(bill);
        model.addAttribute("billUpdate", i);
        if (i > 0) {
            model.addAttribute("updateright", "修改成功");
            return "redirect:/tobillList";//请求之间的调用
        } else {
            model.addAttribute("updateerror", "修改失败");
            return "page/billUpdate";
        }
    }

    //删除按钮
    @GetMapping("/deleteBillById")
    public String deleteBillById(Integer id, Model model) {

        int result = billService.deleteBillById(id);
        if (result > 0) {
            model.addAttribute("deleteright", "删除成功");
            return "redirect:/tobillList";//请求之间的调用
        } else {
            return "page/billList";
        }

    }
}
