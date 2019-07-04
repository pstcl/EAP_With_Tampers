package org.pstcl.ea.converters;

import org.pstcl.ea.dao.IAddReportLocationsDao;
import org.pstcl.ea.model.entity.AddReportLocations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddReportLocationsConverter implements Converter<String,AddReportLocations>{

	
	static final Logger logger;
	@Autowired 
	IAddReportLocationsDao service; 
	
	static {
		logger = LoggerFactory.getLogger((Class) AddReportLocationsConverter.class);
	}
	
	public AddReportLocations convert(final String code) {
		BoundaryTypeConverter.logger.info("Profile : {}", (Object) code);
		if(code.equalsIgnoreCase(""))
		{
			return null;
		}
		return this.service.findById(Integer.parseInt(code));
	}
}
