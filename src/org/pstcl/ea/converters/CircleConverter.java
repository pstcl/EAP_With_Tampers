package org.pstcl.ea.converters;


import org.pstcl.ea.dao.SubstationUtilityDao;
import org.pstcl.ea.model.entity.CircleMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CircleConverter implements Converter<String, CircleMaster> {
	static final Logger logger;
	@Autowired
	SubstationUtilityDao odtlService;

	static {
		logger = LoggerFactory.getLogger((Class) CircleConverter.class);
	}

	public CircleMaster convert(final String code) {
		CircleConverter.logger.info("Profile : {}", (Object) code);
		if(code.equalsIgnoreCase(""))
		{
			return null;
		}
		return this.odtlService.findCircleByID(Integer.parseInt(code));
	}
}