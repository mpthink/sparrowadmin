/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.sparrowadmin.springConfig;

import com.think.sparrowadmin.common.interceptor.GlobalInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import static com.think.sparrowadmin.common.config.Config.UPLOAD_FOLDER;

/**
 * MVC springConfig resource mapping and freemarker view resolver
 * @author map6
 */
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        // https://blog.csdn.net/qq_39025156/article/details/90055132
        //String uploadFilePath = OSinfoUtil.isWindows() ? Config.WIN_UPLOAD_FOLDER : Config.LINUX_UPLOAD_FOLDER;
        //资源映射
        registry.addResourceHandler("/plugins/**").addResourceLocations("classpath:/static/plugins/");
        registry.addResourceHandler("/app/**").addResourceLocations("classpath:/static/app/");
        registry.addResourceHandler("/remexplus/**").addResourceLocations("classpath:/static/remexplus/");
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + UPLOAD_FOLDER);
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GlobalInterceptor())
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/favicon.ico")
                .excludePathPatterns("/upload/**")
                .addPathPatterns("/**");
    }

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("");
        resolver.setSuffix(".html");
        resolver.setContentType("text/html; charset=UTF-8");
        resolver.setRequestContextAttribute("rc");
        return resolver;
    }

}

