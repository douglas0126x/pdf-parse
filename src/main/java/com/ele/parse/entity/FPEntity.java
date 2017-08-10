package com.ele.parse.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 发票模型
 * @author yaoxj
 *
 */
public class FPEntity {

	/*************start 发票基本信息**************/
	//发票代码
	private String fpdm;
	//标题
	private String title;
	//发票号码
	private String fphm;
	//开票日期
	private String kprq;
	//机器编号
	private String jqbh;
	//校验码
	private String jym;
	
	//密码区
	private String passwordArea;
	
	//二维码图片路径
	private String erWeiMaPicturePath;
	
	//二维码数据
	private String erWeiMaData;
	
	/*************end 发票基本信息****************/
	
	
	/*************start 商品基本信息**************/
	private ArrayList<Goods>goodsList=new ArrayList<Goods>();
	/*************end 商品基本信息**************/
	/********* 货物清单列表 **************/
	private List<Goods> detailGoodsList ;
	
	
	/*************start 购买方**************/
	//购买方名称
	private String buyer_name;
	//购买方纳税人识别号
	private String buyer_nsrsbh;
	//购买方地址、电话
	private String buyer_addressPhoneNum;
	//购买方开户行及账号
	private String buyer_khhandzh;
	/*************end 购买方****************/


	/*************start 销售方**************/
	//销售方名称
	private String seller_name;
	//销售方纳税人识别号
	private String seller_nsrsbh;
	//销售方地址、电话
	private String seller_addressPhoneNum;
	//销售方开户行及账号
	private String seller_khhandzh;
	/*************end 销售方****************/
	
	//合计金额
	private String hjje;
	
	//合计税额
	private String hjse;
	
	//价税合计
	private String jshj;
	
	//备注
	private String bz;
	
	//操作员信息
	private String personsInfo;
	
	//解析签章
	private String jxqz;
	
	private Receivers receivers = new Receivers();
	
	
	//--TODO something is wrong!
	
	public String getJxqz() {
		return jxqz;
	}
	public String[] parseJxqz() {
		
		if(jxqz==null||"".equals(jxqz)||jxqz.length()<3){
			return null;
		}
		
		int index1 = jxqz.indexOf("CN=");
		int index2_part1 = jxqz.indexOf("\"", index1);
		int index2_part2 = jxqz.indexOf(",", index1);
		int index2 = Math.min(index2_part1, index2_part2);
		String cn1=jxqz.substring(index1, index2);//英文
		
		
		
		int index3 = jxqz.indexOf("CN=",index2);
		int index4_part1 = jxqz.indexOf("\"",index3);
		int index4_part2 = jxqz.indexOf(",",index3);
		
		if(index4_part1==-1)
			index4_part1=index4_part2;
		
		if(index4_part2==-1)
			index4_part2=index4_part1;
		
		int index4=Math.min(index4_part1, index4_part2);
		
		
		String cn2="";
		if(index3!=-1)
			cn2=jxqz.substring(index3, index4);//中文
		
		if(cn2.length()>3){
			cn2=cn2.substring(3);
		}
		
		if(cn1.length()>3){
			cn1=cn1.substring(3);
		}
		
		String []jxqzArr=new String[2];
		jxqzArr[0]="Ca:"+cn1;
		jxqzArr[1]="Subject:"+cn2;
		
		return jxqzArr;
	}
	public void setJxqz(String jxqz) {
		this.jxqz = jxqz;
	}
	public String getFpdm() {
		return fpdm;
	}
	public void setFpdm(String fpdm) {
		this.fpdm = fpdm;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFphm() {
		return fphm;
	}
	public void setFphm(String fphm) {
		this.fphm = fphm;
	}
	public String getKprq() {
		return kprq;
	}
	public void setKprq(String kprq) {
		this.kprq = kprq;
	}
	public String getJqbh() {
		return jqbh;
	}
	public void setJqbh(String jqbh) {
		this.jqbh = jqbh;
	}
	public String getJym() {
		return jym;
	}
	public void setJym(String jym) {
		this.jym = jym;
	}
	public ArrayList<Goods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(ArrayList<Goods> goodsList) {
		this.goodsList = goodsList;
	}
	public String getBuyer_name() {
		return buyer_name;
	}
	public List<Goods> getDetailGoodsList() {
		return detailGoodsList;
	}
	public void setDetailGoodsList(List<Goods> detailGoodsList) {
		this.detailGoodsList = detailGoodsList;
	}
	public void setBuyer_name(String buyerName) {
		buyer_name = buyerName;
	}
	public String getBuyer_nsrsbh() {
		return buyer_nsrsbh;
	}
	public void setBuyer_nsrsbh(String buyerNsrsbh) {
		buyer_nsrsbh = buyerNsrsbh;
	}
	public String getBuyer_addressPhoneNum() {
		return buyer_addressPhoneNum;
	}
	public void setBuyer_addressPhoneNum(String buyerAddressPhoneNum) {
		buyer_addressPhoneNum = buyerAddressPhoneNum;
	}
	public String getBuyer_khhandzh() {
		return buyer_khhandzh;
	}
	public void setBuyer_khhandzh(String buyerKhhandzh) {
		buyer_khhandzh = buyerKhhandzh;
	}
	public String getSeller_name() {
		return seller_name;
	}
	public void setSeller_name(String sellerName) {
		seller_name = sellerName;
	}
	public String getSeller_nsrsbh() {
		return seller_nsrsbh;
	}
	public void setSeller_nsrsbh(String sellerNsrsbh) {
		seller_nsrsbh = sellerNsrsbh;
	}
	public String getSeller_addressPhoneNum() {
		return seller_addressPhoneNum;
	}
	public void setSeller_addressPhoneNum(String sellerAddressPhoneNum) {
		seller_addressPhoneNum = sellerAddressPhoneNum;
	}
	public String getSeller_khhandzh() {
		return seller_khhandzh;
	}
	public void setSeller_khhandzh(String sellerKhhandzh) {
		seller_khhandzh = sellerKhhandzh;
	}
	public String getPasswordArea() {
		return passwordArea;
	}
	public void setPasswordArea(String passwordArea) {
		this.passwordArea = passwordArea;
	}
	public String getHjje() {
		return hjje;
	}
	public void setHjje(String hjje) {
		this.hjje = hjje;
	}
	public String getHjse() {
		return hjse;
	}
	public void setHjse(String hjse) {
		this.hjse = hjse;
	}
	public String getJshj() {
		return jshj;
	}
	public void setJshj(String jshj) {
		this.jshj = jshj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getPersonsInfo() {
		return personsInfo;
	}
	public void setPersonsInfo(String personsInfo) {
		this.personsInfo = personsInfo;
	}
	
		
	public String getErWeiMaPicturePath() {
		return erWeiMaPicturePath;
	}
	public void setErWeiMaPicturePath(String erWeiMaPicturePath) {
		this.erWeiMaPicturePath = erWeiMaPicturePath;
	}
	
	
	
	public String getErWeiMaData() {
		return erWeiMaData;
	}
	public void setErWeiMaData(String erWeiMaData) {
		this.erWeiMaData = erWeiMaData;
	}
	public Receivers getReceivers() {
		return receivers;
	}
	public void setReceivers(Receivers receivers) {
		this.receivers = receivers;
	}
	@Override
	public String toString() {
		return "FPEntity [fpdm=" + fpdm + ", title=" + title + ", fphm=" + fphm
				+ ", kprq=" + kprq + ", jqbh=" + jqbh + ", jym=" + jym
				+ ", passwordArea=" + passwordArea + ", erWeiMaPicturePath="
				+ erWeiMaPicturePath + ", erWeiMaData=" + erWeiMaData
				+ ", goodsList=" + goodsList + ", detailGoodsList="
				+ detailGoodsList + ", buyer_name=" + buyer_name
				+ ", buyer_nsrsbh=" + buyer_nsrsbh + ", buyer_addressPhoneNum="
				+ buyer_addressPhoneNum + ", buyer_khhandzh=" + buyer_khhandzh
				+ ", seller_name=" + seller_name + ", seller_nsrsbh="
				+ seller_nsrsbh + ", seller_addressPhoneNum="
				+ seller_addressPhoneNum + ", seller_khhandzh="
				+ seller_khhandzh + ", hjje=" + hjje + ", hjse=" + hjse
				+ ", jshj=" + jshj + ", bz=" + bz + ", personsInfo="
				+ personsInfo + ", jxqz=" + jxqz + ", receivers=" + receivers
				+ "]";
	}
	
	
	
	
	
}
