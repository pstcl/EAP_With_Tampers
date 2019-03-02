package org.pstcl.ea.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pstcl.ea.dao.IMeterMasterDao;
import org.pstcl.ea.model.EAFilter;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.MeterMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;



@Repository("meterMasterDao")
@org.springframework.transaction.annotation.Transactional(value="sldcTxnManager")
public class MeterMasterDaoImpl extends AbstractDaoSLDC<String, MeterMaster> implements IMeterMasterDao {

	static final Logger logger = LoggerFactory.getLogger(MeterMasterDaoImpl.class);

	@Override
	public MeterMaster findByMeterSrNo(String meterNo) {
		MeterMaster meter = getByKey(meterNo);
		return meter;
	}



	@Override
	public List<MeterMaster> findAllMeterMasters() {
		Criteria crit = createEntityCriteria();
		//crit.addOrder(Order.desc("Dia_MM_G6"));
		return (List<MeterMaster>)crit.list();

	}



	@Override
	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		MeterMaster meter = (MeterMaster)crit.uniqueResult();
		delete(meter);
	}

	@Override
	public void save(MeterMaster meter,EAUser user) {
		//persist(meter);
	}

	@Override
	public void persist(MeterMaster meter) {
		//persist(meter);
		//To avoid accidental update In meters
	}


	@Override
	public void update(MeterMaster meter) {
		//persist(meter);
		//To avoid accidental update In meters
	}



	@Override
	public List<MeterMaster> findAllMeterMasters(EAFilter entity) {
		Criteria critLocationList = getSession().createCriteria(LocationMaster.class);
		if(null!=entity)
		{
			if(null!=entity.getSubstation())
			{
				critLocationList.add(Restrictions.eq("substationMaster.ssCode", entity.getSubstation().getSsCode()));
			}
			if(null!=entity.getDivision())
			{
				critLocationList.add(Restrictions.eq("divisionMaster.divCode", entity.getDivision().getDivCode()));
			}
			if(null!=entity.getCircle())
			{
				critLocationList.add(Restrictions.eq("circleMaster.crCode", entity.getCircle().getCrCode()));
			}
		}

		critLocationList.setProjection(Projections.property("locationId"));
		
		
		List <String> locationIdList=critLocationList.list();

		Criteria crit = createEntityCriteria();

		if(locationIdList!=null){
			if(locationIdList.size()>0)
			{
				crit.add((Restrictions.in("locationMaster.locationId", locationIdList)));
			}
			else
			{
				crit.add(Restrictions.sqlRestriction("(1=0)"));
				
			}
		}
		crit.addOrder(Order.asc("meterSrNo"));
		return (List<MeterMaster>) crit.list();
	}



	@Override
	public MeterMaster findMeterForMonth(String locationid, int month, int year) {
		Criteria critLocation = createEntityCriteria();
		critLocation.add(Restrictions.eq("locationMaster.locationId", locationid));
		
		MeterMaster meter = (MeterMaster) critLocation.uniqueResult();
		return meter;
	}




}
