package com.zhoushijie.demo.controller;


import com.zhoushijie.demo.entity.User;
import com.zhoushijie.demo.mapper.PasswordMapper;
import com.zhoushijie.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller //RestController 显示数据
public class LoginController {

    @GetMapping("/toLoginPage")
    public String toLoginPage() {
        return "page/login";
    }

    //登陆状态是保存在浏览器的cookie  和服务器的session中
    @Autowired
    HttpServletRequest request;
    //对外暴漏的永远是接口
    @Autowired
    UserService userService;

    @GetMapping("/toIndex")
    public String toIndex() {
        return "page/index";
    }

    @PostMapping("/toLogin")
    public String toindex(User user, Model model) {
        //User 为了接受页面的数据（页面上的参数名和对象的属性名一致）
        //model 为了后台向前台传数据 注意提交表单用PostMapping

        HttpSession session = request.getSession();
        String path = "";

        User loginUser = userService.login(user);

        if (loginUser != null) {
            session.setAttribute("user", user);
            model.addAttribute("username", loginUser);
            path = "redirect:toIndex";
        } else {
            path = "page/login";

        }
        return path;
    }       model.addAttribute("error", "用户名密码错误");


    /**
     * 密码修改模块
     *
     * @param password
     * @param request
     * @return
     */
    //旧密码的控制
    @GetMapping("/checkPassword")
    @ResponseBody
    public String checkPassword(String password, HttpServletRequest request) {
        String msg = "";
        //session拿到登录密码创建新对象与输入的密码比较
        User loginUser = (User) request.getSession().getAttribute("user");
        if (!loginUser.getPassword().equals(password)) {
            msg = "密码输入错误";
        }
        return msg;
    }

    @GetMapping("/topassword")
    public String topassword() {
        return "page/password";
    }

    @Autowired
    PasswordMapper passwordMapper;

    @PostMapping("/Updatepassword")
    public String UpdatePassword(String rePassword, HttpServletRequest request, User user) {
        String path = "";
        String password = user.getPassword();
        System.out.println(password);
        if (user.getPassword().equals(rePassword)) {
            HttpSession session = request.getSession();
            User user1 = (User) session.getAttribute("user");
            user.setUsername(user1.getUsername());

            passwordMapper.UpdatePassword(user);
            
            path = "page/login";
        } else {
            path= "redirect:topassword";
//            path = "page/password";
        }
        return path;
    }


    /**
     * 退出系统删除登录用户会话
     *
     * @return
     */
    @GetMapping("/toLogout")
    public String toLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        return "page/login";
    }
}
