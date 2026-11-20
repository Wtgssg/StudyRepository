package com.success.oracle.controller;

import com.success.oracle.entity.User;
import com.success.oracle.repository.UserRepository;
import com.success.oracle.service.SsoService;
import com.success.oracle.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final SsoService ssoService;
    private final UserService userService;
    private final UserRepository userRepository;
    /**
     * 首页（需要登录）
     * 访问：http://localhost:8080/home
     */
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        String user = (String) session.getAttribute("user");
        if (user == null) {
            String returnUrl = "http://localhost:8080/home";
            return "redirect:http://localhost:8080/login?returnUrl=" + returnUrl;

        }
        model.addAttribute("username", user);
        return "home";
    }

    /**
     * 回调接口（认证中心登录成功后跳转到这里）
     * 访问：http://localhost:8080/callback?ticket=xxx
     */
    @GetMapping("/callback")
    public String callback(HttpServletRequest request,
                           HttpServletResponse response,
                           @RequestParam(required = false) String ticket,
                           @RequestParam(required = false) String redirectUrl,
                           HttpSession session,
                           Model model) {
        log.info("从 url 获取 Ticket: {}", ticket);
        if (ticket == null) {
            model.addAttribute("error", "未找到 Ticket，请重新登录");
            return "error";
        }

        String username = ssoService.verifyTicket(ticket);
        log.info("Ticket 验证结果: {}", username);

        if (username == null) {
            model.addAttribute("error", "Ticket 无效，请重新登录");
            return "error";
        }

        User user = userRepository.findByUsername(username);
        session.setAttribute("userId", user.getUserId());

        // 6. 跳转
        if (redirectUrl != null && !redirectUrl.isEmpty()) {
            return "redirect:" + redirectUrl;
        }
        return "success";
    }


    private void clearTicketCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("TICKET".equals(cookie.getName())) {
                    Cookie deleteCookie = new Cookie("TICKET", null);
                    deleteCookie.setPath("/");
                    deleteCookie.setMaxAge(0);  // 立即过期
                    response.addCookie(deleteCookie);
                    log.info("已清除 TICKET Cookie");
                    break;
                }
            }
        }
    }
    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout(HttpSession session,HttpServletResponse response) {
        session.invalidate();

        // 清除 Ticket Cookie（如果有）
        Cookie cookie = new Cookie("TICKET", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/login";
    }
}