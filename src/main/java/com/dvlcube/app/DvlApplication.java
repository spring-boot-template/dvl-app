package com.dvlcube.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.dvlcube.app.manager.data.UserBean;
import com.dvlcube.app.conf.ApplicationConfig;

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
public class DvlApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DvlApplication.class, args);
		int i = 1;
		for (String name : context.getBeanDefinitionNames()) {
			System.out.println(i++ + " scanned. " + name);
		}
	}
}
