package org.pstcl.ea.converters;


import org.pstcl.ea.dao.SubstationUtilityDao;
import org.pstcl.ea.model.entity.DivisionMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DivisionConverter implements Converter<String, DivisionMaster> {
	static final Logger logger;
	@Autowired
	SubstationUtilityDao odtlService;

	static {
		logger = LoggerFactory.getLogger((Class) DivisionConverter.class);
	}

	public DivisionMaster convert(final String code) {
		if(code.equalsIgnoreCase(""))
		{
			return null;
		}
		DivisionConverter.logger.info("Profile : {}", (Object) code);
		return this.odtlService.findDivisionByID(Integer.parseInt(code));
	}
}