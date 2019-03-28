package org.pstcl.ea.converters;

import org.pstcl.ea.dao.SubstationUtilityDao;
import org.pstcl.ea.model.entity.LocationMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocationConverter implements Converter<String, LocationMaster> {

	static final Logger logger;
	@Autowired
	SubstationUtilityDao odtlService;

	static {
		logger = LoggerFactory.getLogger((Class) LocationConverter.class);
	}

	public LocationMaster convert(final String code) {
		if(code.equalsIgnoreCase(""))
		{
			return null;
		}
		SubstationConverter.logger.info("Profile : {}", (Object) code);
		return this.odtlService.findLocationByID(code);
	}
}
