package com.ele.parse.entity;

/**
 * 电子发票收款人所在行--Model
 * @author yaoxj
 *
 */
public class Receivers {

	/**
	 * 收款人
	 */
	private String receiver;
	/**
	 * 复合
	 */
	private String check;
	/**
	 * 开票人
	 */
	private String drawer;
	
	
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public String getDrawer() {
		return drawer;
	}
	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}
	@Override
	public String toString() {
		return "Receiver [receiver=" + receiver + ", check=" + check
				+ ", drawer=" + drawer + "]";
	}
	
	
	
	

}
