package org.pstcl.ea.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pstcl.ea.dao.IFeederMasterDao;
import org.pstcl.ea.model.entity.FeederMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("feederMasterDao")
public class FeederMasterDaoImpl implements IFeederMasterDao{

	@Autowired 
	@Qualifier("sldcSessionFactory")
	private SessionFactory sessionFactory;


	@Transactional(value="sldcTxnManager")
	protected Session getSession(){

		return sessionFactory.getCurrentSession();
	}



	static final Logger logger = LoggerFactory.getLogger(FeederMasterDaoImpl.class);


	@Override
	@Transactional(value="sldcTxnManager")
	public FeederMaster findById(int id) {
		FeederMaster txn = getSession().get(FeederMaster.class,id);
		System.out.println(txn);
		return txn;
	}
	
	@Transactional(value="sldcTxnManager")
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(FeederMaster.class);
		
		
	}
	
		@Override
		@Transactional(value="sldcTxnManager")
		public List<FeederMaster> findAllFeeders(){
			Criteria crit = createEntityCriteria();
			return  (List<FeederMaster>)crit.list();
		}
	


}
