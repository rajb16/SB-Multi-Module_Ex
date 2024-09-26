package com.example.common;

import com.example.common.FaviconFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;



@Configuration

public class FaviconConfiguration implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<FaviconFilter> faviconFilterRegistration() {
        FilterRegistrationBean<FaviconFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new FaviconFilter());
        registration.addUrlPatterns("/*");
        registration.setName("faviconFilter");
        registration.setOrder(Integer.MAX_VALUE - 1);  // Set to run late, but before error page filter
        return registration;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(3600)
                .resourceChain(true);
    }

    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //     registry.addResourceHandler(
    //             "/favicon.ico",
    //             "/*/favicon.ico",
    //             "/**/favicon.ico"
    //         )
    //         .addResourceLocations("classpath:/static/")
    //         .setCachePeriod(3600)
    //         .resourceChain(true);
    // }
}

// public class FaviconConfiguration {

//     @Bean
//     public FilterRegistrationBean<FaviconFilter> faviconFilterRegistration() {
//         FilterRegistrationBean<FaviconFilter> registration = new FilterRegistrationBean<>();
//         registration.setFilter(new FaviconFilter());
//         registration.addUrlPatterns("/*");
//         registration.setName("faviconFilter");
//         registration.setOrder(1);
//         return registration;
//     }
// }
// @Configuration
// public class FaviconConfiguration {

//     @Bean
//     public FilterRegistrationBean<FaviconFilter> faviconFilterRegistration() {
//         FilterRegistrationBean<FaviconFilter> registration = new FilterRegistrationBean<>();
//         registration.setFilter(new FaviconFilter());
//         registration.addUrlPatterns("/**/favicon.ico");
//         registration.setOrder(Integer.MIN_VALUE);
//         return registration;
//     }
// }


/* package com.example.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class FaviconConfiguration
{
    @Bean
    public SimpleUrlHandlerMapping myFaviconHandlerMapping()
    {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Integer.MIN_VALUE);
        mapping.setUrlMap(Collections.singletonMap("/favicon.ico",
            myFaviconRequestHandler()));
        return mapping;
    }

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    protected ResourceHttpRequestHandler myFaviconRequestHandler()
    {
        ResourceHttpRequestHandler requestHandler =
            new ResourceHttpRequestHandler();
        // requestHandler.setLocations(Arrays.<Resource> asList(applicationContext.getResource("classpath:/static/images/")));
        // requestHandler.setCacheSeconds(0);

         ClassPathResource faviconResource = new ClassPathResource("static/favicon.ico");
        requestHandler.setLocations(List.of(faviconResource));
        requestHandler.setMediaTypes(Collections.singletonMap("ico", MediaType.IMAGE_X_ICON));
        return requestHandler;
    }
} */

/* @Configuration
public class FaviconConfiguration {

    @Bean
    public SimpleUrlHandlerMapping faviconHandlerMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Integer.MIN_VALUE);
        mapping.setUrlMap(Collections.singletonMap("/favicon.ico", faviconRequestHandler()));
        return mapping;
    }

    @Bean
    protected ResourceHttpRequestHandler faviconRequestHandler() {
        ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
        requestHandler.setLocations(Collections.singletonList(new ClassPathResource("static/images/")));
        return requestHandler;
    }
} */