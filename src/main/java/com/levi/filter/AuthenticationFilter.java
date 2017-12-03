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

public class AuthenticationFilter implements Filter {

	public static final String X_CLACKS_OVERHEAD = "X-Clacks-Overhead";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		handleAuth(request, response, chain);
		
		handleToken(request, response, chain);

	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	private void handleAuth(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest sreq = (HttpServletRequest) request;

		String uri = sreq.getRequestURI();
		
		if(uri.contains("login"))
			filterChain.doFilter(request, response);
	}
	
	private void handleToken(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		if(response.isCommitted())
			return;
		
		HttpServletResponse sresp =(HttpServletResponse) response;
		HttpServletRequest sreq = (HttpServletRequest) request;
		String header = sreq.getHeader("Authorization");
		if(header == null || !header.startsWith("Basic ")){
			sresp.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Nao autorizado");
			return;
		}
		
		String token = header.substring(7);
		
		if(token.equals("cbd942d3-e33d-4163-84ab-d37f26e91d9c")){
			filterChain.doFilter(request, response);
		} else {
			sresp.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Nao autorizado");
			return;
		}
		
	}



}
