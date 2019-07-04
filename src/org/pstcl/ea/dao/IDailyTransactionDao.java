package org.pstcl.ea.dao;

import java.util.Date;
import java.util.List;

import org.pstcl.ea.model.entity.DailyTransaction;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.LocationMaster;


public interface IDailyTransactionDao  {
	DailyTransaction findById(int id);
	DailyTransaction findByLocationDateCombo(LocationMaster location, Date txnDate);
	void deleteById(String id);
	void save(DailyTransaction meter, EAUser user);
	void update(DailyTransaction txn, EAUser user);
	void save(List<DailyTransaction> dailyTransactions, EAUser loggedInUser);
	List<DailyTransaction> getDailyTransactionsByMonth(LocationMaster locationMaster, Integer month, Integer year
			);
	DailyTransaction findByLocationDateFilter(DailyTransaction dailyTransaction);
}
