package org.pstcl.ea.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pstcl.ea.dao.ILocationMasterDao;
import org.pstcl.ea.model.EAFilter;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.LocationMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository("locationMasterDao")
@org.springframework.transaction.annotation.Transactional(value="sldcTxnManager")
public class LocationMasterDaoImpl extends AbstractDaoSLDC<String, LocationMaster> implements ILocationMasterDao {

	static final Logger logger = LoggerFactory.getLogger(LocationMasterDaoImpl.class);

	@Override
	@Transactional(value="sldcTxnManager")
	public LocationMaster findById(String id) {
		LocationMaster meter = getByKey(id);
		return meter;
	}



	@Override
	@Transactional(value="sldcTxnManager")
	public List<LocationMaster> findAllLocationMasters() {
		Criteria crit = createEntityCriteria();
		//crit.addOrder(Order.desc("Dia_MM_G6"));
		List <LocationMaster> list= crit.list();
		System.out.println("list.size() - "+list.size());
		
		return list;

	}



	@Override
	@Transactional(value="sldcTxnManager")
	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		LocationMaster meter = (LocationMaster)crit.uniqueResult();
		delete(meter);
	}

	@Override
	@Transactional(value="sldcTxnManager")
	public void save(LocationMaster meter,EAUser user) {
		persist(meter);
	}



	@Override
	public List<LocationMaster> findAllLocationMasters(EAFilter entity) {
		Criteria crit =  createEntityCriteria();


		if(null!=entity)
		{
			if(null!=entity.getSubstation())
			{
				crit.add(Restrictions.eq("substationMaster.ssCode", entity.getSubstation().getSsCode()));
			}
			if(null!=entity.getDivision())
			{
				crit.add(Restrictions.eq("divisionMaster.divCode", entity.getDivision().getDivCode()));
			}
			if(null!=entity.getCircle())
			{
				crit.add(Restrictions.eq("circleMaster.crCode", entity.getCircle().getCrCode()));
			}
		}

		//crit.addOrder(Order.asc("meterMaster.meterSrNo"));
		return (List<LocationMaster>) crit.list();
	}

	
@Override
public List<String> findDistinctUtiltiyName(){
	Criteria criteria =  createEntityCriteria();
	criteria.setProjection(Projections.distinct(Projections.property("utiltiyName")));
	return (List<String>) criteria.list();
}


@Override
public List<String> findDistinctVoltageLevel(){
	Criteria criteria =  createEntityCriteria();
	criteria.setProjection(Projections.distinct(Projections.property("voltageLevel")));
	return (List<String>) criteria.list();
}
	



}
