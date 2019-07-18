package com.dvlcube.app.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * https://www.baeldung.com/spring-http-logging
 * 
 * @since 28 de mai de 2019
 * @author Ulisses Lima
 */
@Configuration
public class RequestLoggingFilterConfig {
	@Value("${dvl.rest.debug.payLoad}")
	private boolean payloadDebug;

	@Bean
	public CommonsRequestLoggingFilter logFilter() {
		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
		filter.setIncludeQueryString(true);
		filter.setIncludePayload(payloadDebug);
		filter.setMaxPayloadLength(10000);
		filter.setIncludeHeaders(true);
		filter.setAfterMessagePrefix("REQUEST DATA : ");
		return filter;
	}
}