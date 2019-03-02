package org.pstcl.ea.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.transform.Transformers;
import org.pstcl.ea.dao.IDailyTransactionDao;
import org.pstcl.ea.model.entity.DailyTransaction;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;





@Repository("dailyTransactionDao")
//@org.springframework.transaction.annotation.Transactional(value="sldcTxnManager")
public class DailyTransactionDaoImpl  implements IDailyTransactionDao {



	@Autowired 
	@Qualifier("sldcSessionFactory")
	private SessionFactory sessionFactory;


	@Transactional(value="sldcTxnManager")
	protected Session getSession(){

		return sessionFactory.getCurrentSession();
	}

	static final Logger logger = LoggerFactory.getLogger(DailyTransactionDaoImpl.class);

	@Override
	public DailyTransaction findById(int id) {
		DailyTransaction txn = getSession().get(DailyTransaction.class,id);
		return txn;
	}



	@Transactional(value="sldcTxnManager")

	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(DailyTransaction.class);
	}




	@Override
	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		DailyTransaction txn = (DailyTransaction)crit.uniqueResult();
		getSession().delete(txn);
	}

	@Override
	public void save(DailyTransaction txn,EAUser user) {

		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();

		getSession().persist(txn);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(DailyTransaction txn,EAUser user) {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();

		getSession().update(txn);
		transaction.commit();
		session.close();
	}

	//	private List <String> getLocationMeters(FilterModel entity)
	//
	//	{
	//		Criteria critLocationList = getSession().createCriteria(LocationMaster.class);
	//
	//		if(null!=entity)
	//		{
	//			if(null!=entity.getSubstation())
	//			{
	//				critLocationList.add(Restrictions.eq("substationMaster.ssCode", entity.getSubstation().getSsCode()));
	//			}
	//		}
	//		critLocationList.setProjection(Projections.property("meterSrNo"));
	//		return critLocationList.list();
	//	}





	//	@Override
	//	public DailyTransaction findByMeter(MeterMaster meter, Date txnDate) {
	//
	//
	//		Criteria crit = createEntityCriteria();
	//		crit.add(Restrictions.eq("meterMaster.meterSrNo", meter.getMeterSrNo()));
	//
	//		crit.add(Restrictions.eq("transactionDate",txnDate));
	//
	//		return (DailyTransaction) crit.uniqueResult();
	//	}


	

	@Override
	@Transactional(value="sldcTxnManager")
	public DailyTransaction findByLocationDateFilter(DailyTransaction dailyTransaction) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("location.locationId", dailyTransaction.getLocation().getLocationId()));
		crit.add(Restrictions.eq("transactionDate",dailyTransaction.getTransactionDate()));
		return (DailyTransaction) crit.uniqueResult();
	}






	@Override
	@Transactional(value="sldcTxnManager")
	public DailyTransaction findByLocationDateCombo(LocationMaster meter, Date txnDate) {
		Criteria crit = createEntityCriteria();
		java.sql.Date date=new java.sql.Date(txnDate.getTime());
		crit.add(Restrictions.eq("location.locationId", meter.getLocationId()));
		crit.add(Restrictions.eq("transactionDate",date));
		return (DailyTransaction) crit.uniqueResult();
	}







	@Override
	public void save(List<DailyTransaction> dailyTransactions, EAUser loggedInUser) {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		for (DailyTransaction dailyTransaction : dailyTransactions) {
			try
			{
				if (null != dailyTransaction) {
					session.save(dailyTransaction);
				}

			}
			catch(ConstraintViolationException dupExp)
			{
				transaction.rollback();
				session.close();
				saveOrUpdateInCaseDuplicateException(dailyTransactions, loggedInUser);
				return;
			}
			catch (Exception e) {
				transaction.rollback();
				session.close();
				saveOrUpdateInCaseDuplicateException(dailyTransactions, loggedInUser);
				return;
			}
		}
		transaction.commit();
		session.close();
	}

	//Called only in case of exception on duplicate entries for date,location combo. Saves the records only if not found
	private void saveOrUpdateInCaseDuplicateException(List<DailyTransaction> dailyTransactions, EAUser loggedInUser) {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		for (DailyTransaction dailyTransaction : dailyTransactions) {
			try
			{
				Criteria crit = session.createCriteria(DailyTransaction.class);
				crit.add(Restrictions.eq("location.locationId", dailyTransaction.getLocation().getLocationId()));
				crit.add(Restrictions.eq("transactionDate",dailyTransaction.getTransactionDate()));
				DailyTransaction entity =(DailyTransaction) crit.uniqueResult();
				if (null != entity) {
					entity.updateValues(dailyTransaction);
					session.update(entity);
				}
				else
				{
					session.save(dailyTransaction);
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

	@Transactional(value="sldcTxnManager")
	@Override
	public List<DailyTransaction> getDailyTransactionsByMonth(LocationMaster locationMaster,Integer month,Integer year) {


		Criteria criteria = createEntityCriteria();
		if(null!=locationMaster)
		{
		criteria.add(Restrictions.eq("location.locationId", locationMaster.getLocationId()));
		}
		else
		{
			return null;
		}
		Date startDate=DateUtil.startDateTimeForDailySurveyRecs(month, year);
		Date endDate=DateUtil.endDateTimeForDailySurveyRecs(month, year);



		criteria.add(Restrictions.ge("transactionDate",startDate));
		criteria.add(Restrictions.le("transactionDate",endDate));


		List<DailyTransaction> results=criteria.list();
		
		
		@SuppressWarnings("unchecked")
		List<DailyTransaction> totalAll = criteria
		.setProjection(Projections.projectionList()
				.add(Projections.sum("exportWHF"), "exportWHF")
				.add(Projections.sum("importWHF"), "importWHF"))
		.setResultTransformer(Transformers.aliasToBean(DailyTransaction.class)).list();
		results.addAll(totalAll);

		return results;
	}

	

	

}
