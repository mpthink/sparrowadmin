package com.think;

import com.think.sparrowadmin.common.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableCaching
@SpringBootApplication
public class SparrowadminApplication {

	@Bean
	public ServletListenerRegistrationBean<SpringUtil> servletListenerRegistrationBean(){
		ServletListenerRegistrationBean<SpringUtil> servletListenerRegistrationBean = new ServletListenerRegistrationBean<SpringUtil>();
		servletListenerRegistrationBean.setListener(new SpringUtil());
		return servletListenerRegistrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(SparrowadminApplication.class, args);
	}

}
