package org.pstcl.ea.config;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.pstcl.ea.birt.spring.BirtViewReport;
import org.pstcl.ea.birt.spring.core.BirtEngineFactory;
import org.pstcl.ea.birt.spring.core.BirtView;
import org.pstcl.ea.converters.CircleConverter;
import org.pstcl.ea.converters.DivisionConverter;
import org.pstcl.ea.converters.SubstationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@EnableAsync
@ComponentScan(basePackages = {"org.pstcl"})
public class AppConfig extends WebMvcConfigurerAdapter {
	@Autowired
	CircleConverter circleConverter;
	@Autowired
	DivisionConverter divisionConverter;
	@Autowired
	SubstationConverter substationConverter;


	public void configureViewResolvers(final ViewResolverRegistry registry) {
		final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass((Class) JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setContentType("text/html;charset=UTF-8");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver((ViewResolver) viewResolver);
	}

	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler(new String[]{"/static/**"}).addResourceLocations(new String[]{"/static/"})
		.setCacheControl(CacheControl.maxAge(31536000L, TimeUnit.SECONDS));
	}

	public void addFormatters(final FormatterRegistry registry) {
		registry.addConverter(circleConverter);
		registry.addConverter(divisionConverter);
		registry.addConverter(substationConverter);
	}

	@Bean
	public MessageSource messageSource() {
		final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return (MessageSource) messageSource;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public void configurePathMatch(final PathMatchConfigurer matcher) {
		matcher.setUseRegisteredSuffixPatternMatch(true);
	}

	@Bean(name = {"multipartResolver"})
	public CommonsMultipartResolver getResolver() throws IOException {
		final CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		resolver.setMaxUploadSizePerFile(5242880L);
		return resolver;
	}

	//@Autowired private CarService carService ;


	@Bean
	public BirtView birtView(){ 
		BirtView bv = new BirtView();
		//bv.setReportFormatRequestParameter("ReportFormat");
		//bv.setReportNameRequestParameter("ReportName");
		bv.setBirtEngine( this.engine().getObject() );
		return bv; 
	}


	@Bean
	public BirtViewReport birtViewOilReport(){ 
		BirtViewReport bv = new BirtViewReport();
		//bv.setReportFormatRequestParameter("ReportFormat");
		//bv.setReportNameRequestParameter("ReportName");
		bv.setBirtEngine( this.engine().getObject() );
		return bv; 
	}



	@Bean
	protected BirtEngineFactory engine(){ 
		BirtEngineFactory factory = new BirtEngineFactory() ;  
		//factory.setLogLevel( Level.FINEST);
		//factory.setLogDirectory ( new File ("c:/logs"));
		//factory.setLogDirectory( new FileSystemResource("/logs"));
		return factory ; 
	}



	@Bean public BeanNameViewResolver beanNameResolver(){ 
		BeanNameViewResolver br = new BeanNameViewResolver() ;
		return br; 
	} 

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(100000);
	    return multipartResolver;
	}
	








}