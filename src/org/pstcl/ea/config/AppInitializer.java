package org.pstcl.ea.config;


import java.util.EnumSet;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	private static final String DISPATCHER_SERVLET_NAME = "sldc-ea-spring-mvc";
	private static final String DISPATCHER_SERVLET_MAPPING = "/";

	protected Class<?>[] getRootConfigClasses() {
		return (Class<?>[]) new Class[]{AppConfig.class};
	}

	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	public void onStartup(final ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		final FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("characterEncoding",
				(Filter) new CharacterEncodingFilter());
		encodingFilter.setInitParameter("encoding", "UTF-8");
		encodingFilter.setInitParameter("forceEncoding", "true");
		encodingFilter.addMappingForUrlPatterns((EnumSet) null, true, new String[]{"/*"});
	}

	protected Filter[] getServletFilters() {
		final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[]{characterEncodingFilter};
	}
}