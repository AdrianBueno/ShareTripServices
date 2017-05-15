package com.sdi.presentation.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.infrastructure.services.io.Msg;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = { "/html/restricted/*" }, initParams = { @WebInitParam(name = "LoginParam", value = "/html/index.xhtml", description = "Mi paginita wapa") })
public class LoginFilter implements Filter {
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(LoginFilter.class);
	}
	FilterConfig config = null; 
	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		log.info(Msg.getStr("info.instanced"));
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		log.info("Ejecutando doFilter");
		if (!(request instanceof HttpServletRequest)) {
			chain.doFilter(request, response);
			return;
		}
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		if (session.getAttribute("usuario") == null) {
			//String loginForm = config.getInitParameter("LoginParam");
			//log.info(loginForm);
			res.sendRedirect(req.getContextPath() + "/html/index.xhtml");
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
		config = fConfig;
	}

}
