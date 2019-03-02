package org.pstcl.ea.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.pstcl.ea.dao.ILossReportDao;
import org.pstcl.ea.dao.impl.transformer.LossReportEntityTransformer;
import org.pstcl.ea.model.EAFilter;
import org.pstcl.ea.model.entity.DailyTransaction;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.LossReportEntity;
import org.pstcl.ea.util.DateUtil;
import org.pstcl.ea.util.EAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("lossReportDao")
public class LossReportDaoImpl implements ILossReportDao {

	@Autowired 
	@Qualifier("sldcSessionFactory")
	private SessionFactory sessionFactory;


	@Transactional(value="sldcTxnManager")
	protected Session getSession(){

		return sessionFactory.getCurrentSession();
	}
	static final Logger logger = LoggerFactory.getLogger(LossReportDaoImpl.class);

	@Override
	@Transactional(value="sldcTxnManager")
	public List<LocationMaster> lossReportLocations(String reportCriteria,Date startDate,Date endDate) {
		Criteria critLocationList = getSession().createCriteria(LocationMaster.class);
		if(null!=reportCriteria)
		{
			critLocationList.add(Restrictions.eq("lossReportCriteria", reportCriteria));
		}
		return critLocationList.list();
	}
	
	
	@Override
	@Transactional(value="sldcTxnManager")
	public List<LocationMaster> manualDailyEntryLocations(String reportCriteria,Date startDate,Date endDate) {
		Criteria critLocationList = getSession().createCriteria(LocationMaster.class);
		if(null!=reportCriteria)
		{
			critLocationList.add(Restrictions.eq("lossReportCriteria", reportCriteria));
		}
		critLocationList.setProjection(Projections.property("locationId"));
		List <String> locationIdList=critLocationList.list();
		Session session = getSession();
		Criteria criteria = session.createCriteria(DailyTransaction.class);
		if(locationIdList!=null){
			if(locationIdList.size()>0)
			{
				criteria.add((Restrictions.in("location.locationId", locationIdList)));
			}
		}

		criteria.add(Restrictions.ge("transactionDate",startDate));
		criteria.add(Restrictions.le("transactionDate",endDate));
		criteria.add(Restrictions.eq("transactionStatus", EAUtil.DAILY_TRANSACTION_ADDED_MANUALLY));

		@SuppressWarnings("unchecked")
		List<LocationMaster> results = criteria
		.setProjection(Projections.projectionList().add(Projections.groupProperty("location"), "location")).list();
		//.setResultTransformer(Transformers.aliasToBean(LocationMaster.class)).list();
		return results;
	}



	@Override
	@Transactional(value="sldcTxnManager")
	public List<LossReportEntity> getDailyTransactionsProjection(String reportCriteria,Date startDate,Date endDate) {
		
		Long daysCount=DateUtil.daysBetweenDates(startDate,endDate);
		Criteria critLocationList = getSession().createCriteria(LocationMaster.class);
		if(null!=reportCriteria)
		{
			critLocationList.add(Restrictions.eq("lossReportCriteria", reportCriteria));
		}
		critLocationList.setProjection(Projections.property("locationId"));
		List <String> locationIdList=critLocationList.list();
		Session session = getSession();
		Criteria criteria = session.createCriteria(DailyTransaction.class);
		if(locationIdList!=null){
			if(locationIdList.size()>0)
			{
				criteria.add((Restrictions.in("location.locationId", locationIdList)));
			}
		}

		criteria.add(Restrictions.ge("transactionDate",startDate));
		criteria.add(Restrictions.le("transactionDate",endDate));
		@SuppressWarnings("unchecked")
		List<LossReportEntity> results = criteria
		.setProjection(Projections.projectionList().add(Projections.groupProperty("location"), "location")
				.add(Projections.sum("exportWHF"), "exportWHFSum")
				.add(Projections.sum("importWHF"), "importWHFSum")
				.add(Projections.count("exportWHF"), "exportWHFCount")
				.add(Projections.count("importWHF"), "importWHFCount")
				)
		.setResultTransformer(new LossReportEntityTransformer(LossReportEntity.class,daysCount)).list();
		return results;
	}


	@Override
	@Transactional(value="sldcTxnManager")
	public List<LossReportEntity> getLossReportEntries(String reportCriteria,Date startDate,Date endDate) {
		Criteria critLocationList = getSession().createCriteria(LocationMaster.class);
		if(null!=reportCriteria)
		{
			critLocationList.add(Restrictions.eq("lossReportCriteria", reportCriteria));
		}
		critLocationList.setProjection(Projections.property("locationId"));
		List <String> locationIdList=critLocationList.list();
		Session session = getSession();
		Criteria criteria = session.createCriteria(DailyTransaction.class);
		if(locationIdList!=null){
			if(locationIdList.size()>0)
			{
				criteria.add((Restrictions.in("location.locationId", locationIdList)));
			}
		}
		criteria.add(Restrictions.ge("transactionDate",startDate));
		criteria.add(Restrictions.le("transactionDate",endDate));
		@SuppressWarnings("unchecked")
		List<LossReportEntity> results = criteria
		.setProjection(Projections.projectionList().add(Projections.groupProperty("location"), "location")
				.add(Projections.sum("exportWHF"), "exportWHFSum")
				.add(Projections.sum("importWHF"), "importWHFSum")
				.add(Projections.count("exportWHF"), "exportWHFCount")
				.add(Projections.count("importWHF"), "importWHFCount"))

		.setResultTransformer(Transformers.aliasToBean(LossReportEntity.class)).list();


		//		@SuppressWarnings("unchecked")
		//		List<LossReportEntity> totalAll = criteria
		//		.setProjection(Projections.projectionList()
		//				.add(Projections.sum("exportWHF"), "exportWHFSum")
		//				.add(Projections.sum("importWHF"), "importWHFSum"))
		//		.setResultTransformer(Transformers.aliasToBean(LossReportEntity.class)).list();
		//		results.addAll(totalAll);
		return results;
	}
	
	
	@Override
	@Transactional(value="sldcTxnManager")
	public List<LocationMaster> getIncompleteDailyEntryLocations(String reportCriteria,Date startDate,Date endDate) {
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		
		CriteriaQuery<LocationMaster> criteriaQuery = criteriaBuilder.createQuery(LocationMaster.class);
        Root<LocationMaster> root = criteriaQuery.from(LocationMaster.class);
        Root<DailyTransaction> rootDailyTransaction = criteriaQuery.from(DailyTransaction.class);
        
        List<javax.persistence.criteria.Predicate> predicates = new ArrayList<javax.persistence.criteria.Predicate>();

        
        criteriaQuery.multiselect( root);
        
        
        Join<DailyTransaction,LocationMaster> calendarEntityJoin= rootDailyTransaction.joinSet("location", JoinType.LEFT);
        calendarEntityJoin.on(
        		criteriaBuilder.equal(calendarEntityJoin.join("location").<String>get("locationId"),  root.<String>get("locationId")));
        
        predicates.add(criteriaBuilder.and(
                criteriaBuilder.equal(calendarEntityJoin.<String>get("available"), true),
                criteriaBuilder.between(calendarEntityJoin.<Date>get("transactionDate"),startDate, endDate)
        ));
        
        criteriaQuery.groupBy(calendarEntityJoin);
        criteriaQuery.having(criteriaBuilder.le(criteriaBuilder.count(calendarEntityJoin.get("importWHF")), 30));

        Query<LocationMaster> query = getSession().createQuery(criteriaQuery);
        List<LocationMaster> list = query.getResultList();
        List results=new ArrayList<LocationMaster>();
        
        for (Object objects : list) {
		}
		
		
		return results;
	}

	
	@Override
	@Transactional(value="sldcTxnManager")
	public List<LocationMaster> findPendingLossReportLocations(Date startDate,Date endDate) {

		Criteria allLocationsWithCriteria = getSession().createCriteria(LocationMaster.class);
		allLocationsWithCriteria.add(Restrictions.isNotNull("lossReportCriteria"));
		allLocationsWithCriteria.setProjection(Projections.property("locationId"));
		List <String> locationIdList=allLocationsWithCriteria.list();



		Session session = getSession();
		Criteria criteria = session.createCriteria(DailyTransaction.class);
		criteria.add(Restrictions.ge("transactionDate",startDate));
		criteria.add(Restrictions.le("transactionDate",endDate));

		//		if(locationIdList!=null){
		//			if(locationIdList.size()>0)
		//			{
		//				criteria.add((Restrictions.in("location.locationId", locationIdList)));
		//			}
		//		}
		criteria.setProjection(Projections.projectionList().add(Projections.distinct(Projections.property("location.locationId"))));
	
		List <String> locationInDailySurveyTable=criteria.list();


		Criteria critLocationList = getSession().createCriteria(LocationMaster.class);
		if(locationInDailySurveyTable!=null){
			if(locationInDailySurveyTable.size()>0)
			{
				critLocationList.add(Restrictions.not(Restrictions.in("locationId", locationInDailySurveyTable)));
			}
			else
			{
				critLocationList.add(Restrictions.isNotNull("lossReportCriteria"));

			}
		}
		critLocationList.add(Restrictions.isNotNull("lossReportCriteria"));
		return critLocationList.list();
	}


	@Override
	@Transactional(value="sldcTxnManager")
	public List<LocationMaster> findPendingLocations(EAFilter entity,Integer month, Integer year) {

		Criteria crit =  getSession().createCriteria(LocationMaster.class);


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
		crit.setProjection(Projections.property("locationId"));
		List <String> locationIdList=crit.list();


		Session session = getSession();
		Criteria criteria = session.createCriteria(DailyTransaction.class);
		Date startDate=DateUtil.startDateTimeForDailySurveyRecs(month, year);
		Date endDate=DateUtil.endDateTimeForDailySurveyRecs(month, year);
		criteria.add(Restrictions.ge("transactionDate",startDate));
		criteria.add(Restrictions.le("transactionDate",endDate));

		if(locationIdList!=null){
			if(locationIdList.size()>0)
			{
				criteria.add((Restrictions.in("location.locationId", locationIdList)));
			}
		}

		criteria.setProjection(Projections.projectionList().add(Projections.distinct(Projections.property("location.locationId"))) );

		List <String> locationInDailySurveyTable=criteria.list();




		Criteria critLocationList = getSession().createCriteria(LocationMaster.class);

		if(locationIdList!=null){
			if(locationIdList.size()>0)
			{
				critLocationList.add((Restrictions.in("locationId", locationIdList)));
			}
			else
			{
				critLocationList.add(Restrictions.sqlRestriction("(1=0)"));
			}
		}

		if(locationInDailySurveyTable!=null){
			if(locationInDailySurveyTable.size()>0)
			{
				critLocationList.add(Restrictions.not(Restrictions.in("locationId", locationInDailySurveyTable)));
			}

		}

		return critLocationList.list();



	}


}
