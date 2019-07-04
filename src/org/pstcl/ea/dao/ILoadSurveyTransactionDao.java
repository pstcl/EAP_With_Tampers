package org.pstcl.ea.dao;

import java.util.Date;
import java.util.List;

import org.pstcl.ea.model.ImportExportModel;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.LoadSurveyTransaction;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.SubstationMaster;


public interface ILoadSurveyTransactionDao  {
	LoadSurveyTransaction findById(int id);
	LoadSurveyTransaction findByLocationDateCombo(LocationMaster location, Date txnDate);
	void deleteById(String id);
	void save(LoadSurveyTransaction meter, EAUser user);
	void update(LoadSurveyTransaction txn, EAUser user);
	List<LoadSurveyTransaction> findLoadSurveyTxnByLocationList(ImportExportModel importExportModel);
	
	List<LoadSurveyTransaction> findLoadSurveyByDayAndLocation(LocationMaster location, int dayOfMonth, int monthOfYear, int year);
	
	List<LoadSurveyTransaction> findLoadSurveyTxnsBySubstation(SubstationMaster location, int dayOfMonth, int monthOfYear,
			int year);
	void save(List<LoadSurveyTransaction> loadSurveyList, EAUser loggedInUser);
}
