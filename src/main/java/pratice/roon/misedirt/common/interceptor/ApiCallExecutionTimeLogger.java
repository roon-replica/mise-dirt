package pratice.roon.misedirt.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class ApiCallExecutionTimeLogger implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("url : {}", URLDecoder.decode(request.getRequestURL().toString(), StandardCharsets.UTF_8));
        request.setAttribute("startTime", System.currentTimeMillis());

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long processTime = System.currentTimeMillis() - (long) request.getAttribute("startTime");
        log.info("processTime = " + processTime);
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
