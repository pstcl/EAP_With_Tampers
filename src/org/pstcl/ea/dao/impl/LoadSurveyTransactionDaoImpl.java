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
import org.pstcl.ea.dao.ILoadSurveyTransactionDao;
import org.pstcl.ea.model.ImportExportModel;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.LoadSurveyTransaction;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.SubstationMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




@Repository("loadSurveyTransactionDao")
//@Transactional(value="sldcTxnManager")
public class LoadSurveyTransactionDaoImpl  implements ILoadSurveyTransactionDao {


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


	static final Logger logger = LoggerFactory.getLogger(LoadSurveyTransactionDaoImpl.class);

	@Override
	@Transactional(value="sldcTxnManager")
	public LoadSurveyTransaction findById(int id) {


		LoadSurveyTransaction txn = getSession().get(LoadSurveyTransaction.class, id);
		return txn;
	}



	@Transactional(value="sldcTxnManager")
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(LoadSurveyTransaction.class);
	}



	@Override
	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		LoadSurveyTransaction txn = (LoadSurveyTransaction)crit.uniqueResult();
		getSession().delete(txn);
	}

	@Override
	public void save(LoadSurveyTransaction txn,EAUser user) {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		getSession().persist(txn);
		transaction.commit();
		session.close();
	}

	@Override
	public void update(LoadSurveyTransaction txn,EAUser user) {
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
	//	public LoadSurveyTransaction findByMeter(MeterMaster meter, Date txnDate) {
	//
	//
	//		Criteria crit = createEntityCriteria();
	//		crit.add(Restrictions.eq("meterMaster.meterSrNo", meter.getMeterSrNo()));
	//
	//		crit.add(Restrictions.eq("transactionDate",txnDate));
	//
	//		return (LoadSurveyTransaction) crit.uniqueResult();
	//	}

	@Override
	@Transactional(value="sldcTxnManager")
	public List<LoadSurveyTransaction> findLoadSurveyByDayAndLocation(LocationMaster location,int dayOfMonth,int monthOfYear,int year) {
		Criteria crit = createEntityCriteria();
		crit.add((Restrictions.in("location.locationId", location.getLocationId())));
		crit.add(Restrictions.eq("monthOfYear",monthOfYear));
		crit.add(Restrictions.eq("dayOfMonth",dayOfMonth));
		crit.add(Restrictions.eq("year",year));
		return (List<LoadSurveyTransaction>) crit.list();
	}

	@Override
	@Transactional(value="sldcTxnManager")
	public List<LoadSurveyTransaction> findLoadSurveyTxnsBySubstation(SubstationMaster location,int dayOfMonth,int monthOfYear,int year) {

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

		return (List<LoadSurveyTransaction>) crit.list();
	}






	@Override
	@Transactional(value="sldcTxnManager")
	public List<LoadSurveyTransaction> findLoadSurveyTxnByLocationList(ImportExportModel filter) {


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

		return (List<LoadSurveyTransaction>) crit.list();
	}



	@Override
	@Transactional(value="sldcTxnManager")
	public LoadSurveyTransaction findByLocationDateCombo(LocationMaster meter, Date txnDate) {

		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("location.locationId", meter.getLocationId()));




		//crit.add(Restrictions.between("transactionDate",StringUtil.getDayBeforeDate(StringUtil.getDayBeforeDate(StringUtil.getToday())),StringUtil.getToday()));

		//crit.add(Expression.le("transactionDate",StringUtil.getToday()));

		crit.add(Restrictions.eq("transactionDate",txnDate));

		return (LoadSurveyTransaction) crit.uniqueResult();
	}







	@Override
	public void save(List<LoadSurveyTransaction> loadSurveyList, EAUser loggedInUser) {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		for (LoadSurveyTransaction loadSurveyTransaction : loadSurveyList) {
			try

			{
				if (null != loadSurveyTransaction) {

					session.save(loadSurveyTransaction);
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
	
	private void saveOrUpdateInException(List<LoadSurveyTransaction> loadSurveyList, EAUser loggedInUser) {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		for (LoadSurveyTransaction loadSurveyTransaction : loadSurveyList) {
			try

			{
				Criteria crit = session.createCriteria(LoadSurveyTransaction.class);
				crit.add(Restrictions.eq("location.locationId", loadSurveyTransaction.getLocation().getLocationId()));
				crit.add(Restrictions.eq("transactionDate",loadSurveyTransaction.getTransactionDate()));
				LoadSurveyTransaction entity =(LoadSurveyTransaction) crit.uniqueResult();

				if (null != entity) {
					entity.updateValues(loadSurveyTransaction);
					session.update(entity);
				}
				else
				{
					session.save(loadSurveyTransaction);
				}

			}
			catch(ConstraintViolationException dupExp)
			{

				System.out.println(loadSurveyTransaction);
				System.out.println(dupExp.getClass());
				transaction.rollback();
				session.close();
				return;
			}
			catch (Exception e) {
				System.out.println(loadSurveyTransaction);
				e.printStackTrace();
				transaction.rollback();
				session.close();

			}

		}
		transaction.commit();
		session.close();
	}
}
