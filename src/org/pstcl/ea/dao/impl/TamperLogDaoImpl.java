package org.pstcl.ea.dao.impl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.pstcl.ea.dao.ITamperLogDao;
import org.pstcl.ea.dao.ITamperLogDao;
import org.pstcl.ea.model.ImportExportModel;
import org.pstcl.ea.model.entity.DailyTransaction;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.TamperLogTransaction;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.SubstationMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




@Repository("tamperLogDao")
//@Transactional(value="sldcTxnManager")
public class TamperLogDaoImpl  implements ITamperLogDao {


	@Transactional(value="sldcTxnManager")
	protected Session getSession(){

		return sessionFactory.getCurrentSession();
	}

	//	@Autowired
	//	public SubstationUtiltiyDaoImpl(EntityManagerFactory factory) {
	//		if(factory.unwrap(SessionFactory.class) == null){
	//			throw new NullPointerException("factory is not a hibernate factory");
	//		}
	//		this.sessionFactory = factory.unwrap(SessionFactory.class);
	//	}

	@Autowired 
	@Qualifier("sldcSessionFactory")
	private SessionFactory sessionFactory;


	static final Logger logger = LoggerFactory.getLogger(TamperLogDaoImpl.class);

	@Override
	@Transactional(value="sldcTxnManager")
	public TamperLogTransaction findById(int id) {


		TamperLogTransaction txn = getSession().get(TamperLogTransaction.class, id);
		return txn;
	}



	@Transactional(value="sldcTxnManager")
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(TamperLogTransaction.class);
	}



	@Override
	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		TamperLogTransaction txn = (TamperLogTransaction)crit.uniqueResult();
		getSession().delete(txn);
	}

	@Override
	public void save(TamperLogTransaction txn,EAUser user) {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		getSession().persist(txn);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(TamperLogTransaction txn,EAUser user) {
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
	//	public TamperLog findByMeter(MeterMaster meter, Date txnDate) {
	//
	//
	//		Criteria crit = createEntityCriteria();
	//		crit.add(Restrictions.eq("meterMaster.meterSrNo", meter.getMeterSrNo()));
	//
	//		crit.add(Restrictions.eq("transactionDate",txnDate));
	//
	//		return (TamperLog) crit.uniqueResult();
	//	}

	@Override
	@Transactional(value="sldcTxnManager")
	public List<TamperLogTransaction> findTamperLogByDayAndLocation(LocationMaster location,Integer dayOfMonth,int monthOfYear,int year) {
		Criteria crit = createEntityCriteria();
		crit.add((Restrictions.in("location.locationId", location.getLocationId())));
		crit.add(Restrictions.eq("monthOfYear",monthOfYear));
		if(dayOfMonth!=null)
		{
		crit.add(Restrictions.eq("dayOfMonth",dayOfMonth));
		}
		crit.add(Restrictions.eq("year",year));
		return (List<TamperLogTransaction>) crit.list();
	}

	@Override
	@Transactional(value="sldcTxnManager")
	public List<TamperLogTransaction> findTamperLogBySubstation(SubstationMaster location,int dayOfMonth,int monthOfYear,int year) {

		Criteria critLocationList = getSession().createCriteria(LocationMaster.class);

		if(null!=location)
		{
			critLocationList.add(Restrictions.eq("substationMaster.ssCode", location.getSsCode()));
		}

		critLocationList.setProjection(Projections.property("locationId"));
		List <String> locationIdList=critLocationList.list();

		Criteria crit = createEntityCriteria();

		if(locationIdList!=null){
			if(locationIdList.size()>0)
			{
				crit.add((Restrictions.in("location.locationId", locationIdList)));
			}
		}
		crit.add(Restrictions.eq("dayOfMonth",dayOfMonth));
		crit.add(Restrictions.eq("monthOfYear",monthOfYear));
		crit.add(Restrictions.eq("year",year));

		return (List<TamperLogTransaction>) crit.list();
	}






	@Override
	@Transactional(value="sldcTxnManager")
	public List<TamperLogTransaction> findTamperLogByLocationList(ImportExportModel filter) {


		Criteria critLocationList = getSession().createCriteria(LocationMaster.class);
		if(null!=filter)
		{
			if(null!=filter.getSubstation())
			{
				critLocationList.add(Restrictions.eq("substationMaster.ssCode", filter.getSubstation().getSsCode()));
			}
			if(null!=filter.getDivision())
			{
				critLocationList.add(Restrictions.eq("divisionMaster.divCode", filter.getDivision().getDivCode()));
			}
			if(null!=filter.getCircle())
			{
				critLocationList.add(Restrictions.eq("circleMaster.crCode", filter.getCircle().getCrCode()));
			}
		}

		critLocationList.setProjection(Projections.property("locationId"));


		List <String> locationIdList=critLocationList.list();

		Criteria crit = createEntityCriteria();

		if(locationIdList!=null){
			if(locationIdList.size()>0)
			{
				crit.add((Restrictions.in("location.locationId", locationIdList)));
			}
		}
		crit.add(Restrictions.eq("transactionDate",filter.getTransactionDate()));

		return (List<TamperLogTransaction>) crit.list();
	}



	@Override
	@Transactional(value="sldcTxnManager")
	public TamperLogTransaction findByLocationDateCombo(LocationMaster meter, Date txnDate) {

		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("location.locationId", meter.getLocationId()));




		//crit.add(Restrictions.between("transactionDate",StringUtil.getDayBeforeDate(StringUtil.getDayBeforeDate(StringUtil.getToday())),StringUtil.getToday()));

		//crit.add(Expression.le("transactionDate",StringUtil.getToday()));

		crit.add(Restrictions.eq("transactionDate",txnDate));

		return (TamperLogTransaction) crit.uniqueResult();
	}







	@Override
	public void save(List<TamperLogTransaction> loadSurveyList, EAUser loggedInUser) {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		for (TamperLogTransaction tamperLog : loadSurveyList) {
			try

			{
				if (null != tamperLog) {

					session.save(tamperLog);
				}	
			}
			catch(ConstraintViolationException dupExp)
			{
				
				transaction.rollback();
				session.close();
				saveOrUpdateInException(loadSurveyList, loggedInUser);;
				return;
			}
			catch (Exception e) {
				transaction.rollback();
				session.close();
				saveOrUpdateInException(loadSurveyList, loggedInUser);;
				return;

			}

		}
		transaction.commit();
		session.close();
	}
	
	private void saveOrUpdateInException(List<TamperLogTransaction> loadSurveyList, EAUser loggedInUser) {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		for (TamperLogTransaction tamperLog : loadSurveyList) {
			try

			{
				Criteria crit = session.createCriteria(TamperLogTransaction.class);
				crit.add(Restrictions.eq("location.locationId", tamperLog.getLocation().getLocationId()));
				crit.add(Restrictions.eq("transactionDate",tamperLog.getTransactionDate()));
				crit.add(Restrictions.eq("recordNo",tamperLog.getRecordNo()));
				
				
				TamperLogTransaction entity =(TamperLogTransaction) crit.uniqueResult();

				if (null != entity) {
					entity.updateValues(tamperLog);
					session.update(entity);
				}
				else
				{
					session.save(tamperLog);
				}

			}
			catch(ConstraintViolationException dupExp)
			{

				System.out.println(tamperLog);
				System.out.println(dupExp.getClass());
				transaction.rollback();
				session.close();
				return;
			}
			catch (Exception e) {
				System.out.println(tamperLog);
				e.printStackTrace();
				transaction.rollback();
				session.close();

			}

		}
		transaction.commit();
		session.close();
	}
}
