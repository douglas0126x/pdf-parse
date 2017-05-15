/** 
 * Project Name:workbench-web 
 * File Name:InvodeUtil.java 
 * Package Name:tools.numUtils 
 * Date:2016年11月10日上午10:34:50 
 * Copyright (c) 2016,douglas0126x@163.com All Rights Reserved. 
 * 
 */

package com.ele.parse.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ele.parse.entity.FPDescribe;
import com.ele.parse.entity.Receivers;

/**
 * ClassName:InvodeUtil <br/>
 * Function: 反射工具类. <br/>
 * Date: 2016年11月10日 上午10:34:50 <br/>
 * 
 * @author Administrator
 * @version 1.0
 * @since JDK 1.7
 */
public class InvokeUtil {
	
	
	private static FPDescribe fpd;
	/**
	 * 
	     * loopModel:反射方式，Float，double类型值保留小数. <br/> 
	     * @author Administrator 
	     * @param model 
	     * @since JDK 1.7
	 */
	public static final Object floatDecimalNum(Object model) {
		if(model == null){
			return null;
		};
		Field[] field = model.getClass().getDeclaredFields(); 									// 获取实体类的所有属性，返回Field数组
		try {
			for (int j = 0; j < field.length; j++) { 											// 遍历所有属性
				String name = field[j].getName();												// 获取属性的名字
				
				name = name.substring(0, 1).toUpperCase() + name.substring(1); 					// 将属性的首字符大写，方便构造get，set方法
				String type = field[j].getGenericType().toString(); 							// 获取属性的类型
				
				
				if (type.equals("class java.lang.Float") ) { 									// 如果type是类类型，则前面包含"class "，后面跟类名
					Method m = model.getClass().getMethod("get" + name);
					Float value = (Float) m.invoke(model); 										// 调用getter方法获取属性值
					if (null != value && value > 0) {
						m = model.getClass().getMethod("set" + name,							
								Float.class);
						m.invoke(model, DecimalUtil.decimal(value));							//调用setter方法设值
					}
				}else if (type.equals("class java.lang.Double") ) { 							// 如果type是类类型，则前面包含"class "，后面跟类名
					Method m = model.getClass().getMethod("get" + name);
					Double value = (Double) m.invoke(model); 									// 调用getter方法获取属性值
					if (null != value && value > 0) {
						m = model.getClass().getMethod("set" + name,							
								Double.class);
						m.invoke(model, DecimalUtil.decimal(value));							//调用setter方法设值
					}
				}
			}
			
			return model;
		} catch (Exception e) {
			System.out.println("\nInvokeUtil  error!\n");
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 去掉pdf电子发票识别后的无效数据
	 * @param model
	 * @return
	 */
	public static final Object trimParse(Object model) {
		if(model == null){
			return null;
		};
		Field[] field = model.getClass().getDeclaredFields(); 									// 获取实体类的所有属性，返回Field数组
		try {
			for (int j = 0; j < field.length; j++) { 											// 遍历所有属性
				String name = field[j].getName();												// 获取属性的名字
				fpd = new FPDescribe();
				java.util.Map<String, String> map = fpd.getFpDescribeMap();
				
				detailTrim(model, name, map);
				
			}
			return model;
		} catch (Exception e) {
			System.out.println("\nInvokeUtil --- trimParse--- error!\n");
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 无效数据排除
	 * @param model
	 * @param name
	 * @param map
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("rawtypes")
	private static void detailTrim(Object model, String name,java.util.Map<String, String> map) throws Exception {
		if(map.keySet().contains(name)){
			String rep = new String();
			String mapValue = map.get(name);
			name = name.substring(0, 1).toUpperCase() + name.substring(1); 					// 将属性的首字符大写，方便构造get，set方法
			Method m = model.getClass().getMethod("get" + name);
			
			//TODO 先假设获取得到值是String ,如：发票代码:011001605111————稍后进行判断修改
			Object o =  m.invoke(model); 													// 调用getter方法获取属性值
			
			if(o instanceof String){
				rep = (String) o;
				String[] replaceArr = mapValue.split(";");
				for(String arr:replaceArr){
					rep = rep.replace(" ", "").replace(" ","").replace(arr, "");
				}
				m=model.getClass().getMethod("set"+name,String.class);										//调用setter方法设值
				m.invoke(model, rep);																//
			}else if(o instanceof List){
				for(Object z:(List)o){
					InvokeUtil.trimParse(z);
				}
			}else if(o instanceof Receivers){
				InvokeUtil.trimParse(o);
			}
			
		}else{
			String rep = new String();
			name = name.substring(0, 1).toUpperCase() + name.substring(1); 					// 将属性的首字符大写，方便构造get，set方法
			Method m = model.getClass().getMethod("get" + name);
			Object o =  m.invoke(model); 													// 调用getter方法获取属性值
			if(o instanceof String){
				rep = (String) o;
				rep = rep.replace(" ", "");
				m=model.getClass().getMethod("set"+name,String.class);										//调用setter方法设值
				m.invoke(model, rep);																//
			}
		}
	}
	/**
	 * 
	     * loopModel:反射方式，Float类型保留小数. <br/> 
	     * @author Administrator 
	     * @param model 
	     * @since JDK 1.7
	 */
	
	public static final <T> List<T> floatDecimalNum (List<T> list){
		List<T> rt =  new ArrayList<T>();
		if(null == list || list.size() == 0){
			return null;
		}
		for(T o:list){
			floatDecimalNum(o);
			rt.add(o);
		}
		return rt;
	}
	/**
	 * 
	     * nullSettring:    空值(null)设定：<br/>①.String： "";<br/>②.Integer: 0;<br/>③.Boolean: false;<br/>④.Date: new Date(); <br/> 
	     * @author Administrator 
	     * @param model 
	     * @since JDK 1.7
	 */
	public static final Object nullSettring(Object model){
		if(model == null){
			return null;
		};
		Field[] field = model.getClass().getDeclaredFields(); 									// 获取实体类的所有属性，返回Field数组
		try {
			for (int j = 0; j < field.length; j++) { 											// 遍历所有属性
				String name = field[j].getName();												// 获取属性的名字
				name = name.substring(0, 1).toUpperCase() + name.substring(1); 					// 将属性的首字符大写，方便构造get，set方法
				String type = field[j].getGenericType().toString(); 							// 获取属性的类型
				
				if (type.equals("class java.lang.String")) { 									// 如果type是类类型，则前面包含"class "，后面跟类名
					Method m = model.getClass().getMethod("get" + name);
					String value = (String) m.invoke(model); 									// 调用getter方法获取属性值
					if (value == null) {
						m = model.getClass().getMethod("set" + name,
								String.class);
						m.invoke(model, "");
					}
				}
				if (type.equals("class java.lang.Integer")) {
					Method m = model.getClass().getMethod("get" + name);
					Integer value = (Integer) m.invoke(model);
					if (value == null) {
						m = model.getClass().getMethod("set" + name,
								Integer.class);
						m.invoke(model, 0);
					}
				}
				if (type.equals("class java.lang.Boolean")) {
					Method m = model.getClass().getMethod("get" + name);
					Boolean value = (Boolean) m.invoke(model);
					if (value == null) {
						m = model.getClass().getMethod("set" + name,
								Boolean.class);
						m.invoke(model, false);
					}
				}
				if (type.equals("class java.util.Date")) {
					Method m = model.getClass().getMethod("get" + name);
					Date value = (Date) m.invoke(model);
					if (value == null) {
						m = model.getClass()
								.getMethod("set" + name, Date.class);
						m.invoke(model, new Date());
					}
				}
			}
			return model;
		} catch (Exception e) {
			System.out.println("\nInvokeUtil  error!\n");
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	

}
