package pratice.roon.misedirt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pratice.roon.misedirt.interceptor.ApiCallExecutionTimeLogger;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiCallExecutionTimeLogger())
                .addPathPatterns("/openapi/**");
    }
}
