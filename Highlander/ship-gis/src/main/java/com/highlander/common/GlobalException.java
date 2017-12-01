package com.highlander.common;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GlobalException implements HandlerExceptionResolver {
    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView();
        /* 使用response返回 */
        response.setStatus(HttpStatus.OK.value()); // 设置状态码
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 设置ContentType
        response.setCharacterEncoding("UTF-8"); // 避免乱码
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
            PrintWriter writer = response.getWriter();
            //
            if (ex instanceof UnauthorizedException) {
                writer.write("{\"success\":false,\"message\":\"权限不足\"}");
            } else if (ex instanceof UnauthenticatedException) {
                writer.write("{\"success\":false,\"message\":\"请先登录\"}");
            } else {
                log.error(ex.getMessage(), ex);
                writer.write("{\"success\":false,\"message\":\"" + ex.getMessage() + "\"}");
            }
        } catch (IOException e) {
            log.error("与客户端通讯异常:" + e.getMessage(), e);
        }

        return mv;
    }

}
