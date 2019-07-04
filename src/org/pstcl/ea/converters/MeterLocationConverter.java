package org.pstcl.ea.converters;

import org.pstcl.ea.dao.MeterLocationMapDao;
import org.pstcl.ea.model.mapping.MeterLocationMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MeterLocationConverter implements Converter<String, MeterLocationMap>{

	static final Logger logger;

	@Autowired
	MeterLocationMapDao service;
	
	static {
		logger = LoggerFactory.getLogger((Class) MeterLocationConverter.class);
	}

	public MeterLocationMap convert(final String code) {
		if(code.equalsIgnoreCase(""))
		{
			return null;
		}
		DivisionConverter.logger.info("Profile : {}", (Object) code);
		return this.service.findById(Integer.parseInt(code));
	}
	

}
