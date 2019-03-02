package org.pstcl.ea.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.pstcl.ea.dao.EAUserDao;
import org.pstcl.ea.model.EAFilter;
import org.pstcl.ea.model.entity.EAUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("eaUserDao")
@Transactional(value="sldcTxnManager")
public class EAUserDaoImpl extends AbstractDaoSLDC<String, EAUser> implements EAUserDao {

	static final Logger logger = LoggerFactory.getLogger(EAUserDaoImpl.class);

	@Override
	public EAUser findById(String id) {
		EAUser user = getByKey(id);
		return user;
	}
	


	@Override
	@SuppressWarnings("unchecked")
	public List<EAUser> findAllUsers() {
		Criteria criteria=createEntityCriteria();
		return criteria.list();
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<EAUser> listSubDivisions(EAUser user) {
		Criteria criteria=createEntityCriteria();
		return criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<EAUser> listDivisions(EAUser user) {
		Criteria criteria=createEntityCriteria();
		return criteria.list();
	}



	@Override
	@SuppressWarnings("unchecked")
	public List<EAUser> findUserHistory(EAFilter model) {
		Criteria criteria=createEntityCriteria();
		return criteria.list();
	}



	@Override
	public void save(EAUser eaUser) {
		update(eaUser);
	}

	
	



}
