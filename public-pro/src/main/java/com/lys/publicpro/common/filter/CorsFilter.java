package com.lys.publicpro.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * @author 李无敌
 * @date 2021年02月04日 16:46
 */
//@Component
//public class CorsFilter implements Filter {
//    static final Logger logger = LoggerFactory.getLogger(CorsFilter.class);
//
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse)res;
//        HttpServletRequest request = (HttpServletRequest) req;
//        System.out.println(request.getMethod());
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Cache-Control","no-cache");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Encrypt,Access-Token");
//        if (request.getMethod().equals("OPTIONS")) {
//            PrintWriter out = response.getWriter();
//            out.print("200");
//            out.close();
//        } else {
//            chain.doFilter(req, res);
//        }
//    }
//
//    public void init(FilterConfig filterConfig) {}
//
//    public void destroy() {}
//
//    @Override
//    public boolean isLoggable(LogRecord record) {
//        return false;
//    }
//}
