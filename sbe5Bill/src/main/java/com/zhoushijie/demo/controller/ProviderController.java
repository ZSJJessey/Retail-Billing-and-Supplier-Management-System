package com.zhoushijie.demo.controller;

import com.zhoushijie.demo.entity.Provider;
import com.zhoushijie.demo.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class ProviderController {
    @Autowired
    ProviderService providerService;

    @GetMapping("/toproviderList")
    public String toproviderList(Model model) {
        List<Provider> providerByWhere = providerService.findProviderByWhere(null);
        model.addAttribute("providerList", providerByWhere);
        return "page/providerList";
    }

    //多条件查询
    @GetMapping("/searchByWhere2")
    public String searchByWhere2(Provider provider, Model model) {
        List<Provider> providerByWhere = providerService.findProviderByWhere(provider);
        model.addAttribute("providerList", providerByWhere);
        model.addAttribute("providerListSupplierName", provider.getSupplier());
        return "page/providerList";
    }


    //providerList添加按钮
    @GetMapping("/toproviderAdd")
    public String toproviderAdd() {
        return "page/providerAdd";
    }

    @PostMapping("/toAddProvider")
    public String toAddProvider(Provider provider, Model model) {
        provider.setCreateDate(new Date());
        int i = providerService.insertProvider(provider);
        if (i > 0) {
            model.addAttribute("addright2", "添加成功");
            return "redirect:/toproviderList";//请求之间的调用
        } else {
            model.addAttribute("adderror2", "添加失败请检查数据");
            return "page/providerAdd";
        }
    }
//查看
    @GetMapping("/toproviderView")
    public String toproviderView(Integer id, Model model) {
        Provider provider = providerService.selectProvider(id);
        model.addAttribute("provider", provider);
        return "page/providerView";
    }
//修改
    @GetMapping("/toproviderUpdate")
    public String toproviderUpdate(Integer id, Model model) {
        Provider provider = providerService.selectProvider(id);
        model.addAttribute("provider", provider);
        return "page/providerUpdate";
    }

    @PostMapping("/toUpdateProvider")
    public String toUpdateProvider(Provider provider, Model model) {
        int i = providerService.updateProvider(provider);
        model.addAttribute("providerUpdate", i);
        if (i > 0) {
            model.addAttribute("updateright2", "修改成功");
            return "redirect:/toproviderList";//请求之间的调用
        } else {
            model.addAttribute("updateerror2", "修改失败");
            return "page/providerUpdate";
        }
    }
    //删除按钮
    @GetMapping("/deleteProviderById")
    public String deleteProviderById(Integer id, Model model) {

        int result = providerService.deleteProviderById(id);
        if (result > 0) {
            model.addAttribute("deleteright2", "删除成功");
            return "redirect:/toproviderList";//请求之间的调用
        } else {
            return "page/providerList";
        }

    }
}
