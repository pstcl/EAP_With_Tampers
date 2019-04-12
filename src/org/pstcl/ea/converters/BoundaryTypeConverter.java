package org.pstcl.ea.converters;

import org.pstcl.ea.dao.IBoundaryTypeMasterDao;
import org.pstcl.ea.model.entity.BoundaryTypeMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BoundaryTypeConverter implements Converter<String, BoundaryTypeMaster> {
	static final Logger logger;
	@Autowired
     IBoundaryTypeMasterDao service;

	static {
		logger = LoggerFactory.getLogger((Class) BoundaryTypeConverter.class);
	}

	public BoundaryTypeMaster convert(final String code) {
		BoundaryTypeConverter.logger.info("Profile : {}", (Object) code);
		if(code.equalsIgnoreCase(""))
		{
			return null;
		}
		return this.service.findBoundaryById(Integer.parseInt(code));
	}
}
