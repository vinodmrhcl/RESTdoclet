package com.iggroup.oss.sample.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;

/**
 * Basic Jamon filter for gathering per IP address statistics.
 */
public class MonitorFilter extends HttpServlet implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		Monitor monitor = MonitorFactory.start(request.getRemoteAddr());

		try {
			filterChain.doFilter(request, response);
		} finally {
			monitor.stop();
		}
	}

	protected String getURI(ServletRequest request) {
		if (request instanceof HttpServletRequest) {
			return ((HttpServletRequest) request).getRequestURI();
		} else {
			return "Not an HttpServletRequest";
		}
	}

	@Override
	public void destroy() {
	}

}