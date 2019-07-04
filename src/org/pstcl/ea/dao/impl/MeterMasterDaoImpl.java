package org.pstcl.ea.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pstcl.ea.dao.IMeterMasterDao;
import org.pstcl.ea.model.EAFilter;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.MeterMaster;
import org.pstcl.ea.model.mapping.MeterLocationMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository("meterMasterDao")
@org.springframework.transaction.annotation.Transactional(value="sldcTxnManager")
public class MeterMasterDaoImpl extends AbstractDaoSLDC<String, MeterMaster> implements IMeterMasterDao {

	@Transactional(value="sldcTxnManager")
	protected Session getSession(){

		return sessionFactory.getCurrentSession();
	}

	@Autowired 
	@Qualifier("sldcSessionFactory")
	private SessionFactory sessionFactory;


	static final Logger logger = LoggerFactory.getLogger(MeterMasterDaoImpl.class);

	@Override
	public MeterMaster findByMeterSrNo(String meterNo) {
		MeterMaster meter = getByKey(meterNo);
		return meter;
	}



	@Override
	public List<MeterMaster> findAllMeterMasters() {
		Criteria crit = createEntityCriteria();
		//crit.addOrder(Order.desc("Dia_MM_G6"));
		return (List<MeterMaster>)crit.list();

	}



	@Override
	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		MeterMaster meter = (MeterMaster)crit.uniqueResult();
		delete(meter);
	}

	@Override
	public void save(MeterMaster meter,EAUser user) {
		//persist(meter);
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		session.persist(meter);
		transaction.commit();
		session.close();
	}

	@Override
	public void persist(MeterMaster meter) {
		//persist(meter);
		//To avoid accidental update In meters
	
	}


	@Override
	public void update(MeterMaster meter) {
		//persist(meter);
		//To avoid accidental update In meters
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		session.update(meter);
		transaction.commit();
		session.close();
	}



	@Override
	public List<MeterMaster> findAllMeterMasters(EAFilter entity) {
		Criteria critLocationList = getSession().createCriteria(LocationMaster.class);
		if(null!=entity)
		{
			if(null!=entity.getSubstation())
			{
				critLocationList.add(Restrictions.eq("substationMaster.ssCode", entity.getSubstation().getSsCode()));
			}
			if(null!=entity.getDivision())
			{
				critLocationList.add(Restrictions.eq("divisionMaster.divCode", entity.getDivision().getDivCode()));
			}
			if(null!=entity.getCircle())
			{
				critLocationList.add(Restrictions.eq("circleMaster.crCode", entity.getCircle().getCrCode()));
			}
		}

		critLocationList.setProjection(Projections.property("locationId"));
		
		
		List <String> locationIdList=critLocationList.list();

		Criteria crit = createEntityCriteria();

		if(locationIdList!=null){
			if(locationIdList.size()>0)
			{
				crit.add((Restrictions.in("locationMaster.locationId", locationIdList)));
			}
			else
			{
				crit.add(Restrictions.sqlRestriction("(1=0)"));
				
			}
		}
		crit.addOrder(Order.asc("meterSrNo"));
		return (List<MeterMaster>) crit.list();
	}



	@Override
	public MeterMaster findMeterForMonth(String locationid, int month, int year) {
		Criteria critLocation = createEntityCriteria();
		critLocation.add(Restrictions.eq("locationMaster.locationId", locationid));
		
		MeterMaster meter = (MeterMaster) critLocation.uniqueResult();
		return meter;
	}


@Override
public List<MeterMaster> findMeterWithNoMapping(){
	Criteria critLocationList = getSession().createCriteria(MeterLocationMap.class);
	critLocationList.add(Restrictions.isNull("endDate"));
	critLocationList.setProjection(Projections.projectionList().add(Projections.distinct(Projections.property("meterMaster.meterSrNo"))) );
    List <String> meterSrNo = critLocationList.list();
	Criteria crit = createEntityCriteria();
	crit.add(Restrictions.not(Restrictions.in("meterSrNo", meterSrNo)));
	return (List<MeterMaster>) crit.list();
}

@Override
public List<String> findDistinctMeterMake(){
	Criteria criteria =  createEntityCriteria();
	criteria.setProjection(Projections.distinct(Projections.property("meterMake")));
	return (List<String>) criteria.list();
}

@Override
public List<String> findDistinctMeterType(){
	Criteria criteria =  createEntityCriteria();
	criteria.setProjection(Projections.distinct(Projections.property("meterType")));
	return (List<String>) criteria.list();
}

@Override
public List<String> findDistinctMeterCategory(){
	Criteria criteria =  createEntityCriteria();
	criteria.setProjection(Projections.distinct(Projections.property("meterCategory")));
	return (List<String>) criteria.list();
}
}
