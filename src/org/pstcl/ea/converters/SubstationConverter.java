package org.pstcl.ea.converters;


import org.pstcl.ea.dao.SubstationUtilityDao;
import org.pstcl.ea.model.entity.SubstationMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubstationConverter implements Converter<String, SubstationMaster> {
	static final Logger logger;
	@Autowired
	SubstationUtilityDao odtlService;

	static {
		logger = LoggerFactory.getLogger((Class) SubstationConverter.class);
	}

	public SubstationMaster convert(final String code) {
		if(code.equalsIgnoreCase(""))
		{
			return null;
		}
		SubstationConverter.logger.info("Profile : {}", (Object) code);
		return this.odtlService.findSubstationByID(Integer.parseInt(code));
	}
}