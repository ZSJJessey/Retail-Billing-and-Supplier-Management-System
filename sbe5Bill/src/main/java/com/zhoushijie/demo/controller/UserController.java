package com.zhoushijie.demo.controller;


import com.zhoushijie.demo.entity.User;
import com.zhoushijie.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/touserList")
    public String touserList(Model model) {
        List<User> userByWhere = userService.findUserByWhere(null);
        model.addAttribute("userList", userByWhere);
        return "page/userList";
    }
    //多条件查询
    @GetMapping("/searchByWhere3")
    public String searchByWhere3(User user,Model model) {
        List<User> userByWhere = userService.findUserByWhere(user);
        model.addAttribute("userList", userByWhere);
        model.addAttribute("userListUserName", user.getUsername());
        return "page/userList";
    }
//查看
    @GetMapping("/touserView")
    public String touserView(Integer id,Model model) {
        User user= userService.selectUser(id);
        model.addAttribute("user", user);
        return "page/userView";
    }
//更新
    @GetMapping("/touserUpdate")
    public String touserUpdate(Integer id,Model model) {
        User user= userService.selectUser(id);
        model.addAttribute("user", user);
        return "page/userUpdate";
    }

    @PostMapping("/toUpdateUser")
    public String toUpdateUser(User user, Model model) {
        int i = userService.updateUser(user);
        model.addAttribute("userUpdate", i);
        if (i > 0) {
            model.addAttribute("updateright3", "修改成功");
            return "redirect:/touserList";//请求之间的调用
        } else {
            model.addAttribute("updateerror3", "修改失败");
            return "page/userUpdate";
        }
    }
//添加
    @GetMapping("/touserAdd")
    public String touserAdd() {

        return "page/userAdd";
    }
    @PostMapping("/toAddUser")
    public String toAddUser(User user, Model model) {
        int i = userService.insertUser(user);
        if (i > 0) {
            model.addAttribute("addright3", "添加成功");
            return "redirect:/touserList";//请求之间的调用
        } else {
            model.addAttribute("adderror3", "添加失败请检查数据");
            return "page/userAdd";
        }
    }

    //删除
    @GetMapping("/deleteUserById")
    public String deleteUserById(Integer id, Model model) {

        int result = userService.deleteUserById(id);
        if (result > 0) {
            model.addAttribute("deleteright3", "删除成功");
            return "redirect:/touserList";//请求之间的调用
        } else {
            return "page/userList";
        }

    }
}
