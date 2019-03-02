package org.pstcl.ea.model;

import java.util.Date;
import java.util.List;

import org.pstcl.ea.model.entity.DailyTransaction;
import org.springframework.format.annotation.DateTimeFormat;

public class ImportExportModel extends EAFilter {
	private List<DailyTransaction> dailyTransactions ;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date transactionDate;

	

	public List<DailyTransaction> getDailyTransactions() {
		return dailyTransactions;
	}

	public void setDailyTransactions(List<DailyTransaction> dailyTransactions) {
		this.dailyTransactions = dailyTransactions;
		
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
}
