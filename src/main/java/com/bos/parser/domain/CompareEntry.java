package com.bos.parser.domain;

public class CompareEntry {
	
	String TransactionName;
	double duration;
	
	
	public CompareEntry(String name, double dur) {
		// TODO Auto-generated constructor stub
		
		this.TransactionName = name;
		this.duration = dur;
	}
	public String getTransactionName() {
		return TransactionName;
	}
	public void setTransactionName(String transactionName) {
		TransactionName = transactionName;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	

}
