package org.pstcl.ea.dao;

import java.util.List;

import org.pstcl.ea.model.ImportExportModel;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.MonthlyTransaction;
import org.pstcl.ea.model.entity.SubstationMaster;


public interface IMonthlyTransactionDao {
	MonthlyTransaction findById(int id);
	MonthlyTransaction findMonthlyByLocationMonth(LocationMaster location, int monthOfYear, int year);

	void deleteById(String id);
	void save(MonthlyTransaction meter, EAUser user);
	void update(MonthlyTransaction txn, EAUser user);
	List<MonthlyTransaction> findDailyTxnByLocationList(ImportExportModel importExportModel);
	
	
	
	List<MonthlyTransaction> findMonthlyTxnsBySubstation(SubstationMaster location, int monthOfYear, int year);
}
