package com.dvlcube.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.dvlcube.app.manager.data.UserBean;
import com.dvlcube.app.conf.ApplicationConfig;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @see ApplicationConfig
 * @see ServletInitializer
 * @since 13 de fev de 2019
 * @author Ulisses Lima
 */
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = "com.dvlcube")
@EntityScan(basePackageClasses = UserBean.class)
@EnableSwagger2
public class DvlApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DvlApplication.class, args);
		int i = 1;
		for (String name : context.getBeanDefinitionNames()) {
			System.out.println(i++ + " scanned. " + name);
		}
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(DvlApplication.class);
	}
}
