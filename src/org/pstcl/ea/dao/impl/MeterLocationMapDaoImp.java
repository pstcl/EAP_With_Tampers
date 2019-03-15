package org.pstcl.ea.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.pstcl.ea.dao.MeterLocationMapDao;

import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.InstantRegisters;
import org.pstcl.ea.model.entity.MeterLocationMap;
import org.pstcl.ea.model.entity.MeterMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;


public class MeterLocationMapDaoImp implements MeterLocationMapDao {

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
	public void save(List<MeterLocationMap> MeterLocationMaps, EAUser loggedInUser) {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		for (MeterLocationMap MeterLocationMap : MeterLocationMaps) {
			try
			{
				if (null != MeterLocationMap) {
					session.save(MeterLocationMap);
				}

			}
			catch(ConstraintViolationException dupExp)
			{
				transaction.rollback();
				session.close();
				saveOrUpdateInCaseDuplicateException(MeterLocationMaps, loggedInUser);
				return;
			}
			catch (Exception e) {
				transaction.rollback();
				session.close();
				saveOrUpdateInCaseDuplicateException(MeterLocationMaps, loggedInUser);
				return;
			}
		}
		transaction.commit();
		session.close();
	}

	
	private void saveOrUpdateInCaseDuplicateException(List<MeterLocationMap> MeterLocationMaps, EAUser loggedInUser) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		for (MeterLocationMap MeterLocationMap : MeterLocationMaps) {
			try
			{
				Criteria crit = session.createCriteria(InstantRegisters.class);
				crit.add(Restrictions.eq("location.locationId", MeterLocationMap.getLocationMaster().getLocationId()));
				//crit.add(Restrictions.eq("transactionDate",instantRegister.getTransactionDate()));
				MeterLocationMap entity =(MeterLocationMap) crit.uniqueResult();
				if (null != entity) {
	//				entity.updateValues(MeterLocationMap);
					session.update(entity);
				}
				else
				{
					session.save(MeterLocationMap);
				}

			}
			catch(ConstraintViolationException dupExp)
			{
				System.out.println(dupExp.getClass());
				transaction.rollback();
				session.close();
				return;
			}
			catch (Exception e) {
				e.printStackTrace();
			}

		}
		transaction.commit();
		session.close();

	}


	@Override
	@Transactional(value="sldcTxnManager")
	public MeterLocationMap findMeterLocationMapByDate(String locationId,Date current) {
Criteria crit = createEntityCriteria();
		
		if(locationId!=null) {
		crit.add((Restrictions.eq("location.locationId", locationId)));
		}
		
		crit.add(Restrictions.le("startDate",current));
		crit.add(Restrictions.ge("endDate",current));
		return (MeterLocationMap) crit.uniqueResult();
		
	}
	
	@Override
	@Transactional(value="sldcTxnManager")
	public MeterLocationMap getLocationByMeterAndDate(MeterMaster meterMaster, Date current) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("meterMaster.meterSrNo", meterMaster.getMeterSrNo()));
		
		crit.add(Restrictions.le("startDate",current));
		crit.add(Restrictions.ge("endDate",current));
		return (MeterLocationMap) crit.uniqueResult();
	}


}
