package org.pstcl.ea.dao;

import java.util.Date;
import java.util.List;

import org.pstcl.ea.model.FileFilter;
import org.pstcl.ea.model.ImportExportModel;
import org.pstcl.ea.model.entity.FileMaster;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.SubstationMaster;


public interface IFileMasterDao  {
	FileMaster findById(int id);
	FileMaster findByLocationDateCombo(LocationMaster location, Date txnDate);
	void deleteById(String id);
	void save(FileMaster meter, EAUser user);
	void update(FileMaster txn, EAUser user);
	
	List<FileMaster> findFileDetailsByLocation(LocationMaster location);
	List<FileMaster> findByMonth(Integer monthOfYear, Integer year);
	List<FileMaster> filterFiles(FileFilter filter);
	List<FileMaster> findCorruptFiles(FileFilter filter);
	
	
}
