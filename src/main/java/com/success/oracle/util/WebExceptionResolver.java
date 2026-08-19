package com.success.oracle.util;

import com.alibaba.fastjson2.JSON;
import com.success.oracle.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@Component
public class WebExceptionResolver implements HandlerExceptionResolver {
//    @Nullable
//    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, Exception ex) {
//        Result result = new Result();
//        log.error("异常:，请求地址：" + request.getRequestURL());
//        Throwable deepestException = deepestException(ex);
//
//        if(deepestException instanceof CustomException){
//            CustomException customException = (CustomException) ex.getCause();
//            result.setMsg(customException.getOutMsg());
//            result.setCode(customException.getOutCode());
//
//            try {
//                response.setStatus(HttpServletResponse.SC_OK);
//                response.setContentType("application/json");
//                response.getWriter().write(JSON.toJSONString(result));
//            } catch (IOException e) {
//                log.error("写入响应失败", e);
//            }
//        }
        return null;
    }
//
//    private Throwable deepestException(Throwable e) {
//        Throwable tmp = e;
//        int breakPoint = 0;
//        while (tmp.getCause() != null) {
//            if (tmp.equals(tmp.getCause())) {
//                break;
//            }
//            tmp = tmp.getCause();
//            breakPoint++;
//            if (breakPoint > 1000) {
//                break;
//            }
//        }
//        return tmp;
//    }
//
}
