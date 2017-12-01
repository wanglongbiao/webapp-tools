package com.highlander.controller;

import com.highlander.common.EncodeUtils;
import com.highlander.common.Global;
import com.highlander.entity.User;
import com.highlander.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RequestMapping("user")
@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("index")
    public String index(@RequestParam Map<String, String> params, HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) {
        User loginedUser = (User) session.getAttribute(Global.USER_INFO);

        if (loginedUser == null) {
            return "redirect:login.do";
        }

        model.put("status", Global.STATUE_SUCCESS);
        model.put("message", "退出登录成功");
        return "index";
    }

    @RequestMapping("login")
    public String login(@RequestParam Map<String, String> params, User user, HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) {
        // User loginedUser = (User) session.getAttribute(Global.USER_INFO);
        Subject subject = SecurityUtils.getSubject();
        log.info(user.toString());
        if (subject.isAuthenticated()) {
//            return "redirect:/ship/shipManagement.do";
        }

        if (params.get("userName") == null) {
            session.removeAttribute(Global.USER_INFO);
            return "user/login";
        } else {
            log.info(params.toString());
        }

        User loginedUser = (User) session.getAttribute(Global.USER_INFO);

        user.setPassword(EncodeUtils.md5(user.getPassword()));

        log.info("password:" + EncodeUtils.md5(user.getPassword()));
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());

        try {
            subject.login(token);// 会跳到我们自定义的realm中
            loginedUser = userService.findByUserAccount(user.getUserName());
            session.setAttribute(Global.USER_INFO, loginedUser);

            // FullMark fullMark = new FullMark(loginedUser.getId(),
            // VtsConstants.MARK_LOGIN, "登录", VtsConstants.CLIENT_TYPE_BS);
            // 登录留痕
            // fullMarkService.save(fullMark);

            return "redirect:/ship/shipManagement.do";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "user/login";
        }

    }

    @RequestMapping("logout")
    public String logout() {
        log.info("log out...");
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();

        return "user/login";
    }

}
