package org.pstcl.ea.converters;

import org.pstcl.ea.dao.IMeterMasterDao;
import org.pstcl.ea.model.entity.MeterMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MeterConverter implements Converter<String, MeterMaster>{

	
	static final Logger logger;
	@Autowired
	IMeterMasterDao service;

	static {
		logger = LoggerFactory.getLogger((Class) MeterConverter.class);
	}

	public MeterMaster convert(final String code) {
		if(code.equalsIgnoreCase(""))
		{
			return null;
		}
		SubstationConverter.logger.info("Profile : {}", (Object) code);
		MeterMaster a = this.service.findByMeterSrNo(code);
		System.out.println(code);
		return a;
	}

}
