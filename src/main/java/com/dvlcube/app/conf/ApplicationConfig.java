package com.dvlcube.app.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dvlcube.app.repository.DvlJpaRepository;
import com.dvlcube.app.repository.UserRepository;

/**
 * Simple general configs.
 * 
 * @see ServletInitializer base packages for scanning
 * @since 28 de fev de 2019
 * @author Ulisses Lima
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = {
		UserRepository.class }, repositoryBaseClass = DvlJpaRepository.class)
public class ApplicationConfig {
}
