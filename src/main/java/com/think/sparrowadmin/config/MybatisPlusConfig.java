/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.sparrowadmin.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author map6
 */
@Configuration
@MapperScan("com.think.sparrowadmin.system.mapper")
public class MybatisPlusConfig {
    /**
     * mybatis-plus 分页插件
     * 可参考：https://gitee.com/baomidou/mybatisplus-spring-boot/blob/master/src/main/java/com/baomidou/springboot/config/MybatisPlusConfig.java
     * https://blog.csdn.net/xusheng_Mr/article/details/78740887
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }

}
