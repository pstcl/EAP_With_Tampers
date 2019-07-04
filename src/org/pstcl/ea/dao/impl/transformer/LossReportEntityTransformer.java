package org.pstcl.ea.dao.impl.transformer;

import java.util.List;

import org.hibernate.transform.AliasToBeanResultTransformer;
import org.pstcl.ea.model.entity.LossReportEntity;

public class LossReportEntityTransformer extends AliasToBeanResultTransformer {
	private 
	Long noOfDaysInMonth;
	public LossReportEntityTransformer(Class resultClass,Long noOfDaysInMonth) {
		super(resultClass);
		this.noOfDaysInMonth=noOfDaysInMonth;
		}

	@Override
	public List transformList(List collection) {
		return super.transformList(collection);
	}

	@Override
	public Object transformTuple(Object[] arg0, String[] arg1) {
		LossReportEntity lossReportEntity=(LossReportEntity) super.transformTuple(arg0, arg1);
		lossReportEntity.setDaysInMonthCount(noOfDaysInMonth);
		return lossReportEntity;
	}

}
