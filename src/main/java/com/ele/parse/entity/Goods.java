package com.ele.parse.entity;

public class Goods {
	
	/**
	 * 应税劳务清单序号（最后为小计、合计等）
	 */
	private String sortNum;
	
	/**
	 * 货物或应税劳务、服务名称
	 */
	private String name;
	/**
	 * 规格型号
	 */
	private String type;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 数量
	 */
	private String number;
	/**
	 * 单价
	 */
	private String price;
	/**
	 * 金额
	 */
	private String totalprice;
	/**
	 * 税额
	 */
	private String se;
	/**
	 * 税率
	 */
	private String sl;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSortNum() {
		return sortNum;
	}
	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
	public String getSe() {
		return se;
	}
	public void setSe(String se) {
		this.se =se;			
	}
	public String getSl() {
		return sl;
	}
	public void setSl(String sl) {
		this.sl =sl;
	}
	@Override
	public String toString() {
		return "Goods [name=" + name + ", number=" + number + ", price="
				+ price + ", se=" + se + ", sl=" + sl + ", totalprice="
				+ totalprice + ", type=" + type + ", unit=" + unit + "]";
	}
	
	
}
