package com.success.oracle.controller;

import com.success.oracle.entity.User;
import com.success.oracle.service.UserService;
import com.success.oracle.service.impl.UserServiceImpl;
import com.success.oracle.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
//@RequestMapping("/sso")
@RequiredArgsConstructor
public class LoginController {

    private final UserServiceImpl userService;  // ← 复用你原有的 UserService
    private final TicketService ticketService;  // ← 新增 Ticket 管理

    /**
     * 登录页面（GET）
     */
    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String returnUrl,
                            org.springframework.ui.Model model) {
        model.addAttribute("returnUrl", returnUrl);
        return "login";  // ← templates/sso/login.html
    }

    /**
     * 登录接口（POST）
     */
    @PostMapping("/doLogin")
    public void doLogin(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam(required = false) String returnUrl,
                        HttpServletResponse response) throws IOException {

        // 1. 复用你原来的校验逻辑（查数据库）
        User user = userService.login(username, password);
        if (user == null) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("用户名或密码错误，<a href='javascript:history.back()'>返回重试</a>");
            return;
        }

        // 2. 登录成功 → 生成 Ticket（一次性凭证）
        String ticket = ticketService.generateTicket(user.getUsername());

        Cookie cookie = new Cookie("TICKET", ticket);
        cookie.setHttpOnly(true);   // JS 无法读取
    //    cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(60);       // 1分钟有效期
        response.addCookie(cookie);


        if (returnUrl != null && !returnUrl.isEmpty()) {
            // 把 returnUrl 作为参数传给 /callback
            response.sendRedirect("/callback?redirectUrl=" + returnUrl);
        } else {
            response.sendRedirect("/callback");
        }
    }

    /**
     * 验证 Ticket（供业务系统调用）
     */
    @GetMapping("/verifyTicket")
    public void verifyTicket(@RequestParam String ticket,
                             HttpServletResponse response) throws IOException {
        String username = ticketService.verifyTicket(ticket);
        if (username == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("invalid");
        } else {
            response.getWriter().write(username);  // ← 返回用户名
        }
    }
}