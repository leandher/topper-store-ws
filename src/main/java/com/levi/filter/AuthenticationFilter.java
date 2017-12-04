package com.levi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(AuthenticationFilter.class);

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		LOGGER.info("Iniciando filtro");
	}

	@Override
	public void doFilter(final ServletRequest request,
			final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		
		handleAuth(request, response, chain);

		handleToken(request, response, chain);
	}

	@Override
	public void destroy() {
		LOGGER.warn("Destruindo o filtro");
	}

	@Bean
	public FilterRegistrationBean AuthenticationFilterBean() {
		final FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
		filterRegBean.setFilter(new AuthenticationFilter());
		filterRegBean.addUrlPatterns("/*");
		filterRegBean.setEnabled(Boolean.TRUE);
		filterRegBean.setName("Authentication Filter");
		filterRegBean.setAsyncSupported(Boolean.TRUE);
		return filterRegBean;
	}

	private void handleAuth(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest sreq = (HttpServletRequest) request;

		String uri = sreq.getRequestURI();

		if (uri.contains("login"))
			filterChain.doFilter(request, response);
	}

	private void handleToken(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		if (response.isCommitted())
			return;

		HttpServletResponse sresp = (HttpServletResponse) response;
		HttpServletRequest sreq = (HttpServletRequest) request;
		String header = sreq.getHeader("Authorization");
		if (header == null || !header.startsWith("Basic ")) {
			sresp.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					"Nao autorizado");
			return;
		}

		String token = header.substring(6);
		
		if (token.equals("cbd942d3-e33d-4163-84ab-d37f26e91d9c")) {
			filterChain.doFilter(request, response);
		} else {
			sresp.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					"Nao autorizado");
			return;
		}

	}

}