package com.nineone.nocm.xss;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeFilter;

public class XssEscapeServletFilter implements Filter{

	private XssEscapeFilter xssEscapeFilter = XssEscapeFilter.getInstance();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			String uri = ((HttpServletRequest)request).getRequestURI().toString();
			System.out.println(uri);
			if(!uri.equals("/api/task/update/content")) {
				chain.doFilter(new XssEscapeServletFilterWrapper(request, xssEscapeFilter), response);
			}else {
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void destroy() {
	}
}
