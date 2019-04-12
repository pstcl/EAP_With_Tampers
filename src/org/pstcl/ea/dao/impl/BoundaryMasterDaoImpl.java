package org.pstcl.ea.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.pstcl.ea.dao.IBoundaryTypeMasterDao;
import org.pstcl.ea.model.EAFilter;
import org.pstcl.ea.model.entity.BoundaryTypeMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("boundaryMasterDao")
@Transactional(value="sldcTxnManager")
public class BoundaryMasterDaoImpl extends AbstractDaoSLDC<String, BoundaryTypeMaster> implements IBoundaryTypeMasterDao {

	static final Logger logger = LoggerFactory.getLogger(BoundaryMasterDaoImpl.class);

	@Override
	public BoundaryTypeMaster findById(String id) {
		BoundaryTypeMaster user = getByKey(id);
		return user;
	}
	
	@Override
	public BoundaryTypeMaster findBoundaryById(Integer id) {
		return getSession().get(BoundaryTypeMaster.class,id);
	}



	@Override
	@SuppressWarnings("unchecked")
	public List<BoundaryTypeMaster> findAllUsers() {
		Criteria criteria=createEntityCriteria();
		return criteria.list();
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<BoundaryTypeMaster> listSubDivisions(BoundaryTypeMaster user) {
		Criteria criteria=createEntityCriteria();
		return criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<BoundaryTypeMaster> listDivisions(BoundaryTypeMaster user) {
		Criteria criteria=createEntityCriteria();
		return criteria.list();
	}



	@Override
	@SuppressWarnings("unchecked")
	public List<BoundaryTypeMaster> findUserHistory(EAFilter model) {
		Criteria criteria=createEntityCriteria();
		return criteria.list();
	}



	@Override
	public void save(BoundaryTypeMaster eaUser) {
		update(eaUser);
	}

	
	



}
