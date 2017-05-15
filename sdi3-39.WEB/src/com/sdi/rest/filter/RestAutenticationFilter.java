package com.sdi.rest.filter;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;
import org.jboss.resteasy.util.Base64;

import com.sdi.business.UsersService;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.factories.Factories;
import com.sdi.infrastructure.model.User;

/**
 * Servlet Filter implementation class RestAutenticationFilter
 */
@WebFilter(dispatcherTypes = DispatcherType.REQUEST)
public class RestAutenticationFilter implements Filter {
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(RestAutenticationFilter.class);
	}

	public static final String AUTHENTICATION_HEADER = "Authorization";

	public RestAutenticationFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		chain.doFilter(request, response);
		
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;

			String authCredentials = httpServletRequest
					.getHeader(AUTHENTICATION_HEADER);

			boolean authenticationStatus = authenticate(authCredentials);

			if (authenticationStatus) {
				log.info("REST_AUTHENTICATION_FILTER: valid credentials.");
				chain.doFilter(request, response);
			} else {
				log.info("REST_AUTHENTICATION_FILTER: Invalid credentials.");
				httpServletResponse
						.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
	}

	private boolean authenticate(String authCredentials) {
		if (null == authCredentials)
			return false;

		final String encodedCredentials = authCredentials.replaceFirst(
				"Basic ", "");

		String credentials = null;
		try {
			byte[] decodedBytes = Base64.decode(encodedCredentials);
			credentials = new String(decodedBytes, "UTF-8");
			log.info("REST user credentials: decrypted.");
		} catch (IOException e) {
			log.info("Failure: couldn't decryps REST user credentials.");
			log.error(e.getStackTrace().toString());
			return false;
		}

		final StringTokenizer tokenizer = new StringTokenizer(credentials, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		boolean authenticationStatus = doAuthenticate(username, password);

		return authenticationStatus;

	}

	private boolean doAuthenticate(String login, String password) {
		UsersService us = Factories.services.getUsersService();
		boolean authenticationStatus = false;
		try {
			User user = us.loginUser(login, password);
			if (null != user) {
				authenticationStatus = true;
				log.info("Authenticated REST user [%s]", user.getPassword());
			}
		} catch (BusinessException be) {
			log.info("Unauthenticated REST user: attemted login [%s]", login);
			authenticationStatus = false;
		}
		return authenticationStatus;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
