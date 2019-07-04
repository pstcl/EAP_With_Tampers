package org.pstcl.ea.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pstcl.ea.dao.IMonthlyTransactionDao;
import org.pstcl.ea.model.ImportExportModel;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.MonthlyTransaction;
import org.pstcl.ea.model.entity.SubstationMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;





@Repository("MonthlyTransactionDao")
@org.springframework.transaction.annotation.Transactional(value="sldcTxnManager")
public class MontlyTransactionDaoImpl extends AbstractDaoSLDC<Integer, MonthlyTransaction> implements IMonthlyTransactionDao {

	static final Logger logger = LoggerFactory.getLogger(MontlyTransactionDaoImpl.class);

	@Override
	public MonthlyTransaction findById(int id) {
		MonthlyTransaction txn = getByKey(id);
		return txn;
	}







	@Override
	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		MonthlyTransaction txn = (MonthlyTransaction)crit.uniqueResult();
		delete(txn);
	}

	@Override
	public void save(MonthlyTransaction txn,EAUser user) {
		persist(txn);
	}

	@Override
	public void update(MonthlyTransaction txn,EAUser user) {
		update(txn);
	}

	

	@Override
	public MonthlyTransaction findMonthlyByLocationMonth(LocationMaster location,int monthOfYear,int year) {
		Criteria crit = createEntityCriteria();
		crit.add((Restrictions.in("location.locationId", location.getLocationId())));
		crit.add(Restrictions.eq("monthOfYear",monthOfYear));
		crit.add(Restrictions.eq("year",year));
		return (MonthlyTransaction) crit.uniqueResult();
	}

	@Override
	public List<MonthlyTransaction> findMonthlyTxnsBySubstation(SubstationMaster location,int monthOfYear,int year) {

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
		crit.add(Restrictions.eq("monthOfYear",monthOfYear));
		crit.add(Restrictions.eq("year",year));

		return (List<MonthlyTransaction>) crit.list();
	}






	@Override
	public List<MonthlyTransaction> findDailyTxnByLocationList(ImportExportModel filter) {


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

		return (List<MonthlyTransaction>) crit.list();
	}










	




}
