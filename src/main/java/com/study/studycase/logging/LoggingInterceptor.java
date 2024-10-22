package com.study.studycase.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LogManager.getLogger(LoggingInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Request: Method={}, URI={}, Headers={}",
                request.getMethod(),
                request.getRequestURI(),
                getHeadersAsString(request));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("Response: Status={}, URI={}, Headers={}",
                response.getStatus(),
                request.getRequestURI(),
                getHeadersAsString(response));
    }

    private String getHeadersAsString(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.append(headerName).append("=").append(request.getHeader(headerName)).append(", ");
        }
        return headers.toString();
    }

    private String getHeadersAsString(HttpServletResponse response) {
        StringBuilder headers = new StringBuilder();
        for (String headerName : response.getHeaderNames()) {
            headers.append(headerName).append("=").append(response.getHeader(headerName)).append(", ");
        }
        return headers.toString();
    }
}
