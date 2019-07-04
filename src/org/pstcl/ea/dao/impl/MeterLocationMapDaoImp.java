package org.pstcl.ea.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.pstcl.ea.dao.MeterLocationMapDao;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.MeterMaster;
import org.pstcl.ea.model.mapping.MeterLocationMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository("meterLocationMapRepository")
@Transactional(value="sldcTxnManager")
public class MeterLocationMapDaoImp implements MeterLocationMapDao {
	//map
	@Transactional(value="sldcTxnManager")
	protected Session getSession(){

		return sessionFactory.getCurrentSession();
	}

	@Autowired 
	@Qualifier("sldcSessionFactory")
	private SessionFactory sessionFactory;


	static final Logger logger = LoggerFactory.getLogger(MeterLocationMapDaoImp.class);


	@Transactional(value="sldcTxnManager")
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(MeterLocationMap.class);
	}



	@Override
	@Transactional(value="sldcTxnManager")
	public MeterLocationMap findById(int id) {


		MeterLocationMap txn = getSession().get(MeterLocationMap.class, id);
		return txn;
	}


	@Override
	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		MeterLocationMap txn = (MeterLocationMap)crit.uniqueResult();
		getSession().delete(txn);
	}
	
	@Override
	@Transactional(value="sldcTxnManager")
	public MeterLocationMap findById(String id) {
		Criteria crit = createEntityCriteria();

		if(id!=null) {
			crit.add((Restrictions.eq("id", id)));
		}

		return (MeterLocationMap) crit.uniqueResult();

	}

	@Override
	@Transactional(value="sldcTxnManager")
	public
	boolean find(MeterLocationMap newMtrLocMap) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("startDate", newMtrLocMap.getStartDate()));
		crit.add((Restrictions.eq("locationMaster.locationId",newMtrLocMap.getLocationMaster().getLocationId())));
		crit.add(Restrictions.eq("meterMaster.meterSrNo", newMtrLocMap.getMeterMaster().getMeterSrNo()));
		List<MeterLocationMap> a = (List<MeterLocationMap>) crit.list();
		if(a!=null && a.size()>0)
			return true;
		return false;
	}

	@Override
	public void save(MeterLocationMap txn,EAUser user) {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		getSession().persist(txn);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(MeterLocationMap txn,EAUser user) {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		getSession().update(txn);
		transaction.commit();
		session.close();
	}



	@Override
	@Transactional(value="sldcTxnManager")
	public MeterLocationMap findMeterLocationMapByDate(String locationId,Date current) {
		Criteria crit = createEntityCriteria();

		if(locationId!=null) {
			crit.add((Restrictions.eq("locationMaster.locationId", locationId)));
		}

		crit.add(Restrictions.le("startDate",current));
		crit.add(Restrictions.ge("endDate",current));
		return (MeterLocationMap) crit.uniqueResult();

	}

	@Override
	@Transactional(value="sldcTxnManager")
	public List<MeterLocationMap> getLocationByMeterAndDate(MeterMaster meterMaster, Date startDateOftheMonth) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("meterMaster.meterSrNo", meterMaster.getMeterSrNo()));

		//crit.add(Restrictions.le("startDate",current));


		Criterion rest4= Restrictions.ge("endDate",startDateOftheMonth);
		Criterion rest5= Restrictions.isNull("endDate");
		crit.add(Restrictions.or( rest4,rest5));

		return (List<MeterLocationMap>) crit.list();
	}


	@Override
	public
	List<MeterLocationMap> findMeterLocationMapByLoc(String locationId){
		Criteria crit = createEntityCriteria();

		if(locationId!=null) {
			crit.add((Restrictions.eq("locationMaster.locationId", locationId)));
		}

		
		return (List<MeterLocationMap>) crit.list();
	}
	
	@Override
	public List<MeterLocationMap> findLocations(MeterMaster meterMaster){
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("meterMaster.meterSrNo", meterMaster.getMeterSrNo()));
		return (List<MeterLocationMap>) crit.list();
	}
}
