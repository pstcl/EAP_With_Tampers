package org.pstcl.ea.dao.impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.pstcl.ea.dao.SubstationUtilityDao;
import org.pstcl.ea.model.EAModel;
import org.pstcl.ea.model.entity.CircleMaster;
import org.pstcl.ea.model.entity.DivisionMaster;
import org.pstcl.ea.model.entity.SubstationMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("substationUtilityDao")
@org.springframework.transaction.annotation.Transactional(value="sldcTxnManager")
public class SubstationUtiltiyDaoImpl implements SubstationUtilityDao {
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
//	@Autowired
//	public SubstationUtiltiyDaoImpl(EntityManagerFactory factory) {
//		if(factory.unwrap(SessionFactory.class) == null){
//			throw new NullPointerException("factory is not a hibernate factory");
//		}
//		this.sessionFactory = factory.unwrap(SessionFactory.class);
//	}

	@Autowired 
	@Qualifier("sldcSessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<CircleMaster> listCircles(EAModel filter) {
		Criteria crit = getSession().createCriteria(CircleMaster.class);
		if(null!=filter)
		{
			if(null!=filter.getCircle())
			{
				crit.add(Restrictions.eq("crCode", filter.getCircle().getCrCode()));
			}
		}
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<CircleMaster>)crit.list();
	}
	


	@Override
	public List<DivisionMaster> listDivisions(EAModel filter) {
		Criteria crit = getSession().createCriteria(DivisionMaster.class);
		if(null!=filter)
		{
			if(null!=filter.getDivision())
			{
				crit.add(Restrictions.eq("divCode", filter.getDivision().getDivCode()));
			}
			if(null!=filter.getCircle())
			{
				crit.add(Restrictions.eq("circleMaster.crCode", filter.getCircle().getCrCode()));
			}
		}
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<DivisionMaster>)crit.list();
	}



	@Override
	public List<SubstationMaster> listSubstations(EAModel entity) {
		Criteria crit = getSession().createCriteria(SubstationMaster.class);

		if(null!=entity)
		{
			if(null!=entity.getCircle())
			{
				crit.add(Restrictions.eq("circleMaster.crCode", entity.getCircle().getCrCode()));
			}
			if(null!=entity.getDivision())
			{
				crit.add(Restrictions.eq("divisionMaster.divCode", entity.getDivision().getDivCode()));
			}
			if(null!=entity.getSubstation())
			{
				crit.add(Restrictions.eq("ssCode", entity.getSubstation().getSsCode()));
			}
		}
		
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.addOrder(Order.asc("circleMaster"));
		return (List<SubstationMaster>)crit.list();
	}



	@Override
	public CircleMaster findCircleByID(Integer code) {
		return (CircleMaster) getSession().get(CircleMaster.class , code);
	}

	@Override
	public DivisionMaster findDivisionByID(Integer code) {
		return (DivisionMaster) getSession().get(DivisionMaster.class, code);
	}

	@Override
	public SubstationMaster findSubstationByID(Integer code) {
		return (SubstationMaster) getSession().get(SubstationMaster.class, code);
	}



}
