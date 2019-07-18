package com.dvlcube.app.rest.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @since May 17, 2018
 * @author Ulisses Lima
 */
@Order(1)
@Component
public class CorsFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		String accessControl = "Access-Control-Allow-Origin";
		String allowCredentials = "Access-Control-Allow-Credentials";

		String origin = System.getProperty(accessControl, "*");
		String allow = System.getProperty(allowCredentials, "true");

		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setHeader(accessControl, origin);
		response.setHeader(allowCredentials, allow);
		response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT");
		response.setHeader("Access-Control-Allow-Headers",
				"Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method,Access-Control-Request-Headers");

		if (!"OPTIONS".equals(((HttpServletRequest) servletRequest).getMethod()))
			filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
	}
}
