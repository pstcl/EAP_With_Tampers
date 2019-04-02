package org.pstcl.ea.dao;

import java.util.List;

import org.pstcl.ea.model.entity.FeederMaster;

public interface IFeederMasterDao {

	FeederMaster findById(int id);

	List<FeederMaster> findAllFeeders();

}
