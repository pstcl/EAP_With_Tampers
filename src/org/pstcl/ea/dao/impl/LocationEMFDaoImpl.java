package org.pstcl.ea.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.pstcl.ea.dao.ILocationEMFDao;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.InstantRegisters;
import org.pstcl.ea.model.entity.LocationEMF;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.MeterLocationMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("locationEMFRepository")
@org.springframework.transaction.annotation.Transactional(value="sldcTxnManager")
public class LocationEMFDaoImpl implements ILocationEMFDao {
	//emf

	@Transactional(value="sldcTxnManager")
	protected Session getSession(){

		return sessionFactory.getCurrentSession();
	}

	@Autowired 
	@Qualifier("sldcSessionFactory")
	private SessionFactory sessionFactory;


	static final Logger logger = LoggerFactory.getLogger(LocationEMFDaoImpl.class);


	@Transactional(value="sldcTxnManager")
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(LocationEMF.class);
	}

	@Override
	@Transactional(value="sldcTxnManager")
	public LocationEMF findById(int id) {


		LocationEMF txn = getSession().get(LocationEMF.class, id);
		return txn;
	}


	@Override
	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		LocationEMF txn = (LocationEMF)crit.uniqueResult();
		getSession().delete(txn);
	}


	@Override
	public void save(LocationEMF txn,EAUser user) {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		getSession().persist(txn);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(LocationEMF txn,EAUser user) {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		getSession().update(txn);
		transaction.commit();
		session.close();
	}

	@Override
	public void save(List<LocationEMF> locationEMFs, EAUser loggedInUser) {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		for (LocationEMF locationEMF : locationEMFs) {
			try
			{
				if (null != locationEMF) {
					session.save(locationEMF);
				}

			}
			catch(ConstraintViolationException dupExp)
			{
				transaction.rollback();
				session.close();
				saveOrUpdateInCaseDuplicateException(locationEMFs, loggedInUser);
				return;
			}
			catch (Exception e) {
				transaction.rollback();
				session.close();
				saveOrUpdateInCaseDuplicateException(locationEMFs, loggedInUser);
				return;
			}
		}
		transaction.commit();
		session.close();
	}


	private void saveOrUpdateInCaseDuplicateException(List<LocationEMF> locationEMFs, EAUser loggedInUser) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		for (LocationEMF locationEMF : locationEMFs) {
			try
			{
				Criteria crit = session.createCriteria(InstantRegisters.class);
				crit.add(Restrictions.eq("location.locationId", locationEMF.getLocationMaster().getLocationId()));
				//crit.add(Restrictions.eq("transactionDate",instantRegister.getTransactionDate()));
				LocationEMF entity =(LocationEMF) crit.uniqueResult();
				if (null != entity) {
					//	entity.updateValues(locationEMF);
					session.update(entity);
				}
				else
				{
					session.save(locationEMF);
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
	public List<LocationEMF> findLocationEmfByDate(String locationId,Date startDateOftheMonth) {




		Criteria crit = createEntityCriteria();

		if(locationId!=null) {
			crit.add((Restrictions.eq("locationMaster.locationId", locationId)));
		}
		Criterion rest4= Restrictions.ge("endDate",startDateOftheMonth);
		Criterion rest5= Restrictions.isNull("endDate");
		crit.add(Restrictions.or( rest4,rest5));
		return (List<LocationEMF>) crit.list();

	}

	@Override
	@Transactional(value="sldcTxnManager")
	public List<LocationEMF> findLocationEmfByLocAndDate(List<MeterLocationMap> meterLocationMapList,Date startDateOftheMonth) {




		Criteria crit = createEntityCriteria();
		List<String> locationIds=new ArrayList<>();
		for (MeterLocationMap meterLocationMap : meterLocationMapList) {
			locationIds.add(meterLocationMap.getLocationMaster().getLocationId());
		}

		if(locationIds.size()>0) {
			crit.add((Restrictions.in("locationMaster.locationId", locationIds)));
		}
		Criterion rest4= Restrictions.ge("endDate",startDateOftheMonth);
		Criterion rest5= Restrictions.isNull("endDate");
		crit.add(Restrictions.or( rest4,rest5));
		return (List<LocationEMF>) crit.list();

	}
}


