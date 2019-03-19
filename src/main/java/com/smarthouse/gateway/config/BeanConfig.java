package com.smarthouse.gateway.config;

import com.smarthouse.commonutil.contexts.UserContext;
import com.smarthouse.commonutil.contexts.UserContextFilter;
import com.smarthouse.commonutil.contexts.UserContextInterceptor;
import com.smarthouse.commonutil.filters.FilterUtils;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Configuration
public class BeanConfig {

    @Bean
    public FilterUtils getFilterUtils() {
        return new FilterUtils();
    }

    @Bean
    public UserContext getUserContext() {
        return new UserContext();
    }

    @Bean
    public UserContextFilter getUserContextFilter() {
        return new UserContextFilter();
    }

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate template = new RestTemplate();
        List interceptors = template.getInterceptors();
        if (interceptors == null) {
            template.setInterceptors(
                    Collections.<ClientHttpRequestInterceptor>singletonList(new UserContextInterceptor())
            );
        } else {
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }

        return template;
    }
}
