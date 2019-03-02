package org.pstcl.ea.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pstcl.ea.dao.IFileMasterDao;
import org.pstcl.ea.model.FileFilter;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.FileMaster;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.util.DateUtil;
import org.pstcl.ea.util.EAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;





@Repository("fileMasterDao")
@org.springframework.transaction.annotation.Transactional(value="sldcTxnManager")
public class FileMasterDaoImpl extends AbstractDaoSLDC<Integer, FileMaster> implements IFileMasterDao {

	static final Logger logger = LoggerFactory.getLogger(FileMasterDaoImpl.class);

	@Override
	public FileMaster findById(int id) {
		FileMaster txn = getByKey(id);
		return txn;
	}







	@Override
	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		FileMaster txn = (FileMaster)crit.uniqueResult();
		delete(txn);
	}

	@Override
	public void save(FileMaster txn,EAUser user) {
		saveOrUpdate(txn);
	}

	@Override
	public void update(FileMaster txn,EAUser user) {
		update(txn);
	}






	@Override
	public List<FileMaster> findFileDetailsByLocation(LocationMaster location) {
		Criteria crit = createEntityCriteria();
		crit.add((Restrictions.in("location.locationId", location.getLocationId())));
		return (List<FileMaster>) crit.list();
	}



	@Override
	public FileMaster findByLocationDateCombo(LocationMaster meter, Date txnDate) {

		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("location.locationId", meter.getLocationId()));

		//crit.add(Restrictions.between("transactionDate",StringUtil.getDayBeforeDate(StringUtil.getDayBeforeDate(StringUtil.getToday())),StringUtil.getToday()));

		//crit.add(Expression.le("transactionDate",StringUtil.getToday()));

		crit.add(Restrictions.eq("transactionDate",txnDate));

		return (FileMaster) crit.uniqueResult();
	}


	@Override
	public List<FileMaster> findByMonth(Integer monthOfYear, Integer year) {
		Criteria crit = createEntityCriteria();


		if(null!=monthOfYear && null!= year)
		{
			crit.add(Restrictions.ge("transactionDate",DateUtil.startDateTimeForDailySurveyRecs(monthOfYear, year)));
			crit.add(Restrictions.le("transactionDate",DateUtil.endDateTimeForDailySurveyRecs(monthOfYear, year) ));
		}
		return (List<FileMaster>) crit.list();
	}

	@Override
	public List<FileMaster> filterFiles(FileFilter filter) {


		Criteria crit = createEntityCriteria();

		if(null!=filter.getProcessingStatus())
		{
			crit.add(Restrictions.eq("processingStatus",filter.getProcessingStatus()));
		}

		if(null!=filter.getFileActionStatus())
		{
			crit.add(Restrictions.eq("fileActionStatus",filter.getFileActionStatus()));
		}
	

		if(null!=filter.getLocation())
		{
			crit.add(Restrictions.eq("location.locationId", filter.getLocation().getLocationId()));

		}
		else if(null!=filter.getSubstation()||null!=filter.getDivision()||null!=filter.getCircle())
		{
			Criteria critLocation = getSession().createCriteria(LocationMaster.class);
			
			if(null!=filter)
			{
				if(null!=filter.getSubstation())
				{
					critLocation.add(Restrictions.eq("substationMaster.ssCode", filter.getSubstation().getSsCode()));
				}
				if(null!=filter.getDivision())
				{
					critLocation.add(Restrictions.eq("divisionMaster.divCode", filter.getDivision().getDivCode()));
				}
				if(null!=filter.getCircle())
				{
					critLocation.add(Restrictions.eq("circleMaster.crCode", filter.getCircle().getCrCode()));
				}
			}
			critLocation.setProjection(Projections.property("locationId"));
			List <String> locationIdList=critLocation.list();
			if(locationIdList!=null){
				if(locationIdList.size()>0)
				{
					crit.add((Restrictions.in("location.locationId", locationIdList)));
				}
			}
			
		}
		if(null!=filter.getTransactionDateFrom())
		{
		
			crit.add(Restrictions.ge("transactionDate",filter.getTransactionDateFrom()));
		}
		if(null!=filter.getTransactionDateTo())
		{
			crit.add(Restrictions.le("transactionDate",filter.getTransactionDateTo() ));
		}
		return (List<FileMaster>) crit.list();
	}

	@Override
	public List<FileMaster> findCorruptFiles(FileFilter filter) {
		Criteria crit = createEntityCriteria();
		
		if(null!=filter.getTransactionDateFrom())
		{
		
			crit.add(Restrictions.ge("transactionDate",filter.getTransactionDateFrom()));
		}
		if(null!=filter.getTransactionDateTo())
		{
			crit.add(Restrictions.le("transactionDate",filter.getTransactionDateTo() ));
		}
		if(null==filter.getTransactionDateFrom()&&null==filter.getTransactionDateTo())
		{
			crit.add(Restrictions.isNull("transactionDate"));
		}
		crit.add(Restrictions.ne("processingStatus",EAUtil.FILE_ZIP_EXTRACTED));
		Criterion rest4= Restrictions.lt("surveyRecordCount",EAUtil.FILE_LOAD_SURVEY_RECORD_COUNT_HEALTHY);
		Criterion rest5= Restrictions.lt("dailyRecordCount",EAUtil.FILE_DAILY_RECORD_COUNT_HEALTHY);
		crit.add(Restrictions.or( rest4,rest5));
		return (List<FileMaster>) crit.list();
	}




}
