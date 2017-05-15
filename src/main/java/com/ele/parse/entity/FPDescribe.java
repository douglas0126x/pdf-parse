package com.ele.parse.entity;

import java.util.HashMap;
import java.util.Map;

public class FPDescribe {
	private Map<String, String> fpDescribeMap;
	/**
	 * 获得当前系统的换行符
	 */
	private String newLineFlag=System.getProperty("line.separator"); 

	public FPDescribe() {
		setFpDescribeMap(createDescribeMap());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, String> createDescribeMap() {
		
		/**
		 * 注意：添加多个剔除内容时，换行需要放在最前边
		 */
		Map map = new HashMap();
		
		//基本信息 
		map.put("title", newLineFlag);
		map.put("fpdm", "发票代码;:;：");
		map.put("fphm", "发票号码;:;：");
		map.put("kprq", "开票日期;:;：");
		map.put("jqbh", "机器编号;:;：;"+newLineFlag);
		map.put("jym", "校验码;:;：");
		map.put("passwordArea", newLineFlag);
		map.put("jxqz", newLineFlag);
		
		//购买方
		map.put("buyer_name", "名称;:;：");
		map.put("buyer_nsrsbh", "纳税人识别号;:;：");
		map.put("buyer_addressPhoneNum", "地址、电话;:;：");
		map.put("buyer_khhandzh", "开户行及账号;:;：");
		//销售方
		map.put("seller_name", "名称;:;：");
		map.put("seller_nsrsbh", "纳税人识别号;:;：");
		map.put("seller_addressPhoneNum", "地址、电话;:;：");
		map.put("seller_khhandzh", "开户行及账号;:;：");
		//合计
		map.put("hjje", newLineFlag+";¥;￥");
		map.put("hjse", newLineFlag+";¥;￥");
		map.put("jshj", "¥;(小写);￥;价税合计（大写）;（小写）;壹;贰;叁;肆;伍;陆;柒;捌;玖;零;万;仟;佰;拾;元;角;分;圆;整;亿;"+newLineFlag);
		//备注
		map.put("bz", newLineFlag+";备注");
//		map.put("bz", newLineFlag);
		//商品基本信息
		map.put("goodsList", newLineFlag);
		map.put("name", newLineFlag);
		map.put("type", newLineFlag);
		map.put("unit", newLineFlag);
		map.put("number", newLineFlag);
		map.put("price", newLineFlag);
		map.put("totalprice", newLineFlag);
		map.put("se", newLineFlag);
		map.put("sl", newLineFlag);
		//操作人信息
		map.put("receivers", newLineFlag);
		map.put("receiver", "复核");
		map.put("check","开票人");
		map.put("drawer", "销售方");
		return map;
	}

	public Map<String, String> getFpDescribeMap() {
		return fpDescribeMap;
	}

	public void setFpDescribeMap(Map<String, String> fpDescribeMap) {
		this.fpDescribeMap = fpDescribeMap;
	}

}
