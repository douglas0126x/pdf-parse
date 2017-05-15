package com.ele.generate.utils;

import java.util.ArrayList;
import java.util.List;

//import com.aisino.fpqz.exception.CustomException;

public class StringUtil {

	//鐩爣瀛楃涓叉寜闀垮害杞崲array
	public static String[] substringToArry(String text, int length)throws Exception {
		if (text == null) {
			return null;
		}
		List<String> strList = new ArrayList<String>();
		try {
			StringBuilder sb = new StringBuilder();
			double currentLength = 0;
			double totalLength = 0;
			for (char c : text.toCharArray()) {
				totalLength += 1;
				currentLength += getLength(c);
				if (currentLength <= length) {
					sb.append(c);
					if(currentLength == getLength(text) || totalLength == text.length()){
						strList.add(sb.toString());
					}
				} else {
					strList.add(sb.toString());
					if(currentLength == length){
						currentLength = 0;
					}else{
						sb = new StringBuilder();
						sb.append(c);
						if(currentLength == getLength(text) || totalLength == text.length()){
							strList.add(sb.toString());
						}
						currentLength = getLength(c);
					}
				}
			}
		} catch (Exception e) {
//			throw new CustomException(1004, "鎴彇瀛楃涓插け璐�", e);
			return null;
		}
		String[] strs = new String[strList.size()];
		return strList.toArray(strs);
	}
	
	public static double getLength(String s) {
		double i = 0;
		for(char c : s.toCharArray())
			i += getLength(c);
		return i;
	}
	
	public static double getLength(char c) {
		double i = 0;
		String match = "^[\u0000-\u00FF]+$";
		if(String.valueOf(c).matches(match)) {
			if(String.valueOf(c).matches("^[0-9]+$")) {
				i += 1;
			} else {
				i += 1.11;
			}
		} else {
			i += 2;
		}
		return i;
	}
}
