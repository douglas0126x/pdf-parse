/** 
 * Project Name:workbench-web 
 * File Name:DecimalUtil.java 
 * Package Name:tools.numUtils 
 * Date:2016年11月8日下午5:32:47 
 * Copyright (c) 2016,douglas0126x@163.com All Rights Reserved. 
 * 
*/  
  
package com.ele.parse.utils;  

import java.math.BigDecimal;
import java.math.RoundingMode;

/** 
 * ClassName:DecimalUtil <br/> 
 * Function: 小数处理工具. <br/> 
 * Date:     2016年11月8日 下午5:32:47 <br/> 
 * @author   Administrator 
 * @version  1.0
 * @since    JDK 1.7   
 */
public final class DecimalUtil {
	
	/**
	 * 默认保留两位小数
	 */
	private static final Float DECIMAL_NUM = 100.f;
	/**
	 * 保留小数位数
	 */
	private static final Integer DECNUM = 2;
	
	/**
	 * 
	 * Creates a new instance of DecimalUtil. 
	 * 
	 * @param a
	 */
	public DecimalUtil(float num) {
		DecimalUtil.decimal(num);
	}
	public DecimalUtil() {
	}
	
	/**
	 * 
	     * decimal:float值保留小数. <br/> 
	     * @author Administrator 
	     * @param a
	     * @return 
	     * @since JDK 1.7
	 */
	public static final Float decimal(float a){
//		System.out.println("a="+a);
//		System.out.println(Math.round(a * DECIMAL_NUM)/DECIMAL_NUM);
//		保留两位小数，四舍五入的一个老土的方法
//		return (float) (Math.round(a * DECIMAL_NUM)/DECIMAL_NUM);
		
//		 新方法，四舍五入，可以使用RoundingMode.HALF_DOWN
        BigDecimal bg = new BigDecimal(a).setScale(DECNUM, RoundingMode.HALF_DOWN);

        
        return (float) bg.doubleValue();
	}
	/**
	 * 
	     * decimal:double类型值保留小数. <br/> 
	     * @author Administrator 
	     * @param a
	     * @return 
	     * @since JDK 1.7
	 */
	public static final Double decimal(double a){
		
	    // 旧方法，已经不再推荐使用
		// BigDecimal bg = new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP);

      
      // 新方法，如果不需要四舍五入，可以使用RoundingMode.HALF_DOWN
      BigDecimal bg = new BigDecimal(a).setScale(2, RoundingMode.HALF_DOWN);
      
      return bg.doubleValue();
		
	}
	
	
	
}
 