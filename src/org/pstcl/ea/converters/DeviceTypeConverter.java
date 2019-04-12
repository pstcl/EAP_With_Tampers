package org.pstcl.ea.converters;

import org.pstcl.ea.dao.IDeviceTypeMasterDao;
import org.pstcl.ea.model.entity.DeviceTypeMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DeviceTypeConverter  implements Converter<String, DeviceTypeMaster> {

	static final Logger logger;
	@Autowired
	IDeviceTypeMasterDao service;

	static {
		logger = LoggerFactory.getLogger((Class)  DeviceTypeConverter.class);
	}

	public DeviceTypeMaster convert(final String code) {
		if(code.equalsIgnoreCase(""))
		{
			return null;
		}
		 DeviceTypeConverter.logger.info("Profile : {}", (Object) code);
		return service.findById(code);
	}
}

