package com.shakiroye.exac;

import com.shakiroye.exac.security.CORSFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

@SpringBootApplication
public class ExacApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExacApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<CORSFilter> corsFilter() {
		FilterRegistrationBean<CORSFilter> bean = new FilterRegistrationBean<>(new CORSFilter());
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
