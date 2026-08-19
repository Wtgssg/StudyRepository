package com.success.oracle.controller;//package com.success.oracle.controller;

import com.success.oracle.service.TicketService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;


import com.success.oracle.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/sso")  // ← 统一前缀，和业务接口区分
public class SsoController {

    private final TicketService ticketService;

    /**
     * 登录页面：http://localhost:8080/sso/login?returnUrl=http://localhost:8080/home
     */
    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String returnUrl, Model model) {
        model.addAttribute("returnUrl", returnUrl);
        return "sso/login";  // ← templates/sso/login.html
    }

    /**
     * 登录接口：POST /sso/doLogin
     */
    @PostMapping("/doLogin")
    public void doLogin(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam(required = false) String returnUrl,
                        HttpServletResponse response) throws IOException {

        // 模拟账号校验
        if (!"admin".equals(username) || !"123".equals(password)) {
            response.getWriter().write("用户名或密码错误");
            return;
        }

        String ticket = ticketService.generateTicket(username);

        if (returnUrl != null && !returnUrl.isEmpty()) {
            // 重定向回业务系统，带上 ticket
            response.sendRedirect(returnUrl + "?ticket=" + ticket);
        } else {
            response.getWriter().write("登录成功，Ticket: " + ticket);
        }
    }

    /**
     * 验证 Ticket：GET /sso/verifyTicket?ticket=xxx
     */
    @GetMapping("/verifyTicket")
    @ResponseBody
    public String verifyTicket(@RequestParam String ticket) {
        return ticketService.verifyTicket(ticket);
    }
}