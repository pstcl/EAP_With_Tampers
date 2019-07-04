package org.pstcl.ea.converters;

import org.pstcl.ea.dao.IFeederMasterDao;
import org.pstcl.ea.model.entity.FeederMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class FeederMasterConverter implements Converter<String, FeederMaster> {
	static final Logger logger;
	@Autowired
     IFeederMasterDao service;

	static {
		logger = LoggerFactory.getLogger((Class) FeederMasterConverter.class);
	}

	public FeederMaster convert(final String code) {
		FeederMasterConverter.logger.info("Profile : {}", (Object) code);
		if(code.equalsIgnoreCase(""))
		{
			return null;
		}
		return this.service.findById(Integer.parseInt(code));
	}
}
