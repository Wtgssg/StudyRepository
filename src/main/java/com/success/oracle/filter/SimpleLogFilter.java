package com.success.oracle.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "CharacterEncodingFilter",urlPatterns = "/*",
            initParams = {
                @WebInitParam(name = "encoding",value = "UTF-8"),
                @WebInitParam(name = "forceEncoding",value = "true")
            },
            dispatcherTypes = DispatcherType.REQUEST)
public class SimpleLogFilter implements Filter {
    private String encoding;
    @Override
    public void init(FilterConfig filterConfig) {
        this.encoding = filterConfig.getInitParameter("encoding");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        log.info(">>> 请求原始编码: " + request.getCharacterEncoding());
        log.info(">>>注解中配置的编码"+encoding);
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);

        chain.doFilter(request, response);
        log.info(">>> 设置后请求编码: " + request.getCharacterEncoding());
    }
}