package org.pstcl.ea.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.pstcl.ea.dao.IInstantRegistersDao;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.InstantRegisters;
import org.pstcl.ea.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("instantRegistersDao")
public class InstantRegistersDaoImpl implements IInstantRegistersDao{

	@Transactional(value="sldcTxnManager")
	protected Session getSession(){

		return sessionFactory.getCurrentSession();
	}

	@Autowired 
	@Qualifier("sldcSessionFactory")
	private SessionFactory sessionFactory;


	static final Logger logger = LoggerFactory.getLogger(InstantRegistersDaoImpl.class);

	

	@Transactional(value="sldcTxnManager")
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(InstantRegisters.class);
	}
	
	@Override
	@Transactional(value="sldcTxnManager")
	public InstantRegisters findById(int id) {


		InstantRegisters txn = getSession().get(InstantRegisters.class, id);
		return txn;
	}
	
	@Override
	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		InstantRegisters txn = (InstantRegisters)crit.uniqueResult();
		getSession().delete(txn);
	}

	
	@Override
	public void save(InstantRegisters txn,EAUser user) {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		getSession().persist(txn);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(InstantRegisters txn,EAUser user) {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		getSession().update(txn);
		transaction.commit();
		session.close();
	}
	
	@Override
	public void save(List<InstantRegisters> instantRegisters, EAUser loggedInUser) {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		for (InstantRegisters instantRegister : instantRegisters) {
			try
			{
				if (null != instantRegister) {
					session.save(instantRegister);
				}

			}
			catch(ConstraintViolationException dupExp)
			{
				transaction.rollback();
				session.close();
				saveOrUpdateInCaseDuplicateException(instantRegisters, loggedInUser);
				return;
			}
			catch (Exception e) {
				transaction.rollback();
				session.close();
				saveOrUpdateInCaseDuplicateException(instantRegisters, loggedInUser);
				return;
			}
		}
		transaction.commit();
		session.close();
	}

	
	private void saveOrUpdateInCaseDuplicateException(List<InstantRegisters> instantRegisters, EAUser loggedInUser) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		for (InstantRegisters instantRegister : instantRegisters) {
			try
			{
				Criteria crit = session.createCriteria(InstantRegisters.class);
				crit.add(Restrictions.eq("location.locationId", instantRegister.getLocation().getLocationId()));
				crit.add(Restrictions.eq("transactionDate",instantRegister.getTransactionDate()));
				InstantRegisters entity =(InstantRegisters) crit.uniqueResult();
				if (null != entity) {
					entity.updateValues(instantRegister);
					session.update(entity);
				}
				else
				{
					session.save(instantRegister);
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
	public List<InstantRegisters> findInstantRegistersByDayAndLocation(String locationid, Integer month,Integer year) {
		
		Date startDate=DateUtil.startDateTimeForDailySurveyRecs(month, year);
		Date endDate=DateUtil.endDateTimeForDailySurveyRecs(month, year);
		Criteria crit = createEntityCriteria();
		
		if(locationid!=null) {
		crit.add((Restrictions.eq("location.locationId", locationid)));
		}
		System.out.println(startDate);
		System.out.println(endDate);
		crit.add(Restrictions.ge("transactionDate",startDate));
		crit.add(Restrictions.le("transactionDate",endDate));
		List<InstantRegisters> ir = crit.list();
		return ir;
	}
}
