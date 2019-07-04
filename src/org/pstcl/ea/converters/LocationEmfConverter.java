package org.pstcl.ea.converters;


import org.pstcl.ea.dao.ILocationEMFDao;
import org.pstcl.ea.model.mapping.LocationMFMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocationEmfConverter implements Converter<String, LocationMFMap> {
	static final Logger logger;
	@Autowired
	 ILocationEMFDao service;

	static {
		logger = LoggerFactory.getLogger((Class) LocationEmfConverter.class);
	}

	public LocationMFMap convert(final String code) {
		if(code.equalsIgnoreCase(""))
		{
			return null;
		}
		DivisionConverter.logger.info("Profile : {}", (Object) code);
		return this.service.findById(Integer.parseInt(code));
		
	}
}