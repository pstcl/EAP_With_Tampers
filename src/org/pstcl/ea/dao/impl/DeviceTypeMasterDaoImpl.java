package org.pstcl.ea.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pstcl.ea.dao.IDeviceTypeMasterDao;
import org.pstcl.ea.model.entity.DeviceTypeMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("deviceTypeMasterDao")
public class DeviceTypeMasterDaoImpl implements IDeviceTypeMasterDao{

	
	@Autowired 
	@Qualifier("sldcSessionFactory")
	private SessionFactory sessionFactory;


	@Transactional(value="sldcTxnManager")
	protected Session getSession(){

		return sessionFactory.getCurrentSession();
	}



	static final Logger logger = LoggerFactory.getLogger(DeviceTypeMasterDaoImpl.class);


	@Override
	@Transactional(value="sldcTxnManager")
	public DeviceTypeMaster findById(String deviceId) {
		DeviceTypeMaster txn = getSession().get(DeviceTypeMaster.class,deviceId);
		System.out.println(txn);
		return txn;
	}
	
	@Transactional(value="sldcTxnManager")
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(DeviceTypeMaster.class);
		
		
	}
	
		@Override
		@Transactional(value="sldcTxnManager")
		public List<DeviceTypeMaster> findAllDeviceTypes(){
			Criteria crit = createEntityCriteria();
			return  (List<DeviceTypeMaster>)crit.list();
		}

}
