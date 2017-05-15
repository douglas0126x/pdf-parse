package com.ele.generate.entity;

import java.util.List;


/**
 * 
 * @author songzhixin
 * 分布式数据库数据获取
 */
public class FPDistributed {

//	{\"CompleteCode\":0,\"ReasonCode\":\"RC0000\",\"ReasonMessage\":\"\"}
	private String completeCode;
	
	private String reasonCode;
	
	private String reasonMessage;
	
	/**
	 * 发票数据
	 */
	private List<FPData> data;
	
	/**
	 * 分页内容
	 */
	private Page page;
	

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<FPData> getData() {
		return data;
	}

	public void setData(List<FPData> data) {
		this.data = data;
	}

	public String getCompleteCode() {
		return completeCode;
	}

	public void setCompleteCode(String completeCode) {
		this.completeCode = completeCode;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getReasonMessage() {
		return reasonMessage;
	}

	public void setReasonMessage(String reasonMessage) {
		this.reasonMessage = reasonMessage;
	}

	@Override
	public String toString() {
		return "FPDB [completeCode=" + completeCode + ",reasonCode="+reasonCode+", data=" + data
				+ ", reasonCode=" + reasonCode + ", reasonMessage="
				+ reasonMessage + "]";
	}


	
	



	



	
	
}
