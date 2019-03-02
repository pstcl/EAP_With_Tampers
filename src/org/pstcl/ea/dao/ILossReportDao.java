package org.pstcl.ea.dao;

import java.util.Date;
import java.util.List;

import org.pstcl.ea.model.EAFilter;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.LossReportEntity;

public interface ILossReportDao {

	
	
	List<LossReportEntity> getDailyTransactionsProjection(String reportCriteria, Date startDate, Date endDate);

	List<LossReportEntity> getLossReportEntries(String reportCriteria, Date startDate, Date endDate);

	List<LocationMaster> manualDailyEntryLocations(String reportCriteria, Date startDate, Date endDate);

	List<LocationMaster> lossReportLocations(String reportCriteria, Date startDate, Date endDate);

	List<LocationMaster> getIncompleteDailyEntryLocations(String reportCriteria, Date startDate, Date endDate);
	
	List<LocationMaster> findPendingLossReportLocations(Date startDate,Date endDate);
	List<LocationMaster> findPendingLocations(EAFilter entity, Integer month, Integer year);


}
