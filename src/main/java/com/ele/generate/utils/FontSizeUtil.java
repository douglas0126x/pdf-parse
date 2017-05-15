package com.ele.generate.utils;


//import com.aisino.fpqz.exception.Exception;

//璁剧疆瀛椾綋澶у皬
public class FontSizeUtil {
	public static int getFontSize(String org) throws Exception{
		FontSize font = null;
		double orgLength = 0;
		try{
			orgLength = StringUtil.getLength(org);
		}catch (Exception e) {
//			throw new Exception(1005,"鑾峰彇瀛椾綋澶у皬澶辫触(" + org + ")",e);
		}
		font = compare(orgLength,org);
		return font.size();
	}
	
	public static FontSize compare(double length, String org) throws Exception{
		if(length <= FontSize.NOnine.length()){
			return FontSize.NOnine;
		}else if(length > FontSize.NOnine.length() && length <= FontSize.NOeight.length()){
			return FontSize.NOeight;
		}else if(length > FontSize.NOeight.length() && length <= FontSize.NOseven.length()){
			return FontSize.NOseven;
		}else if(length > FontSize.NOseven.length() && length <= FontSize.NOsiX.length()){
			return FontSize.NOsiX;
		}else if(length > FontSize.NOsiX.length() && length <= FontSize.NOfive.length()){
			return FontSize.NOfive;
		}else if(length > FontSize.NOfive.length() && length <= FontSize.NOfour.length()){
			return FontSize.NOfour;
		}else if(length > FontSize.NOfour.length() && length <= FontSize.NOthree.length()){
			return FontSize.NOthree;
		}else if(length > FontSize.NOthree.length() && length <= FontSize.NOtwo.length()){
			return FontSize.NOtwo;
		}else{
//			throw new Exception(1006,"鏂囧瓧闀垮害杩囬暱锛岃幏鍙栧瓧浣撳ぇ灏忓け璐�(" + length + "锛�" + org + ")");
			return null;
		}
	}
	
	public static int getFontSizeForQd(String org) throws Exception{
		FontSize font = null;
		double orgLength = 0;
		try{
			orgLength = StringUtil.getLength(org);
		}catch (Exception e) {
//			throw new Exception(1005,"鑾峰彇瀛椾綋澶у皬澶辫触(" + org + ")",e);
			return 0;
		}
		font = qdcompare(orgLength,org);
		return font.size();
	}
	
	public static FontSize qdcompare(double length, String org) throws Exception{
		if(length <= FontSize.QDnine.length()){
			return FontSize.QDnine;
		}else if(length > FontSize.QDnine.length() && length <= FontSize.QDeight.length()){
			return FontSize.QDeight;
		}else{
//			throw new Exception(1006,"鏂囧瓧闀垮害杩囬暱锛岃幏鍙栧瓧浣撳ぇ灏忓け璐�(" + length + "锛�" + org + ")");
			return null;
		}
	}
	
	//鎶樿骞剁缉灏忓瓧浣�
	public static int getFontSize(String[] src,int srcFontSzie,int length) {
		int size = src.length/length;
		int ys = src.length%length;
		if(size != 0){
			if(ys>=1){
				size ++;
			}
		}
		if(size == 1 && ys == 0){
			size = 0;
		}
		return srcFontSzie-size;
	}
	
	public static FontSize getFontSizeForGgxh(String src) throws Exception{
		try{
			double srcLength = StringUtil.getLength(src);
			if(srcLength <= FontSize.GGXHnine.length()){
				return FontSize.GGXHnine;
			}else if(srcLength > FontSize.GGXHnine.length() && srcLength <= FontSize.GGXHeight.length()){
				return FontSize.GGXHeight;
			}else if(srcLength > FontSize.GGXHeight.length() && srcLength <= FontSize.GGXHseven.length()){
				return FontSize.GGXHseven;
			}else if(srcLength > FontSize.GGXHseven.length() && srcLength <= FontSize.GGXHsix.length()){
				return FontSize.GGXHsix;
			}else if(srcLength > FontSize.GGXHsix.length() && srcLength <= FontSize.GGXHfive.length()){
				return FontSize.GGXHfive;
			}else if(srcLength > FontSize.GGXHfive.length() && srcLength <= FontSize.GGXHfour.length()){
				return FontSize.GGXHfour;
			}else{
				return FontSize.GGXHfour;
			}
		}catch (Exception e) {
//			throw new Exception(1007, "瑙勬牸鍨嬪彿瀛椾綋璁剧疆澶辫触(" + src + ")", e);
			return null;
		}
	}
	
	public static FontSize getFontSizeForDw(String src) throws Exception{
		try{
			double srcLength = StringUtil.getLength(src);
			if(srcLength <= FontSize.DWnine.length()){
				return FontSize.DWnine;
			}else if(srcLength > FontSize.DWnine.length() && srcLength <= FontSize.DWeight.length()){
				return FontSize.DWeight;
			}else if(srcLength > FontSize.DWeight.length() && srcLength <= FontSize.DWseven.length()){
				return FontSize.DWseven;
			}else if(srcLength > FontSize.DWseven.length() && srcLength <= FontSize.DWsix.length()){
				return FontSize.DWsix;
			}else if(srcLength > FontSize.DWsix.length() && srcLength <= FontSize.DWfive.length()){
				return FontSize.DWfive;
			}else if(srcLength > FontSize.DWfive.length() && srcLength <= FontSize.DWfour.length()){
				return FontSize.DWfour;
			}else{
				return FontSize.DWfour;
			}
		}catch (Exception e) {
//			throw new Exception(1007, "鍗曚綅瀛椾綋璁剧疆澶辫触(" + src + ")", e);
			return null;
		}
	}
	
	public static FontSize getFontSizeForQDBZ(String src) throws Exception{
		try{
			double srcLength = StringUtil.getLength(src);
			if(srcLength <= FontSize.QDBZnine.length()){
				return FontSize.QDBZnine;
			}else if(srcLength > FontSize.QDBZnine.length() && srcLength <= FontSize.QDBZeight.length()){
				return FontSize.QDBZeight;
			}else if(srcLength > FontSize.QDBZeight.length() && srcLength <= FontSize.QDBZseven.length()){
				return FontSize.QDBZseven;
			}else if(srcLength > FontSize.QDBZseven.length() && srcLength <= FontSize.QDBZsix.length()){
				return FontSize.QDBZsix;
			}else{
				return FontSize.QDBZsix;
			}
		}catch (Exception e) {
//			throw new Exception(1007, "娓呭崟澶囨敞瀛椾綋璁剧疆澶辫触(" + src + ")", e);
			return null;
		}
	}

	public static FontSize getFontSizeForBZ(String src) throws Exception{
		try{
			double srcLength = StringUtil.getLength(src);
			if(srcLength <= FontSize.BZnine.length()){
				return FontSize.BZnine;
			}else if(srcLength > FontSize.BZnine.length() && srcLength <= FontSize.BZeight.length()){
				return FontSize.BZeight;
			}else if(srcLength > FontSize.BZeight.length() && srcLength <= FontSize.BZseven.length()){
				return FontSize.BZseven;
			}else if(srcLength > FontSize.BZseven.length() && srcLength <= FontSize.BZsix.length()){
				return FontSize.BZsix;
			}else{
				return FontSize.BZsix;
			}
		}catch (Exception e) {
//			throw new Exception(1007, "澶囨敞瀛椾綋璁剧疆澶辫触(" + src + ")", e);
			return null;
		}
	}
}
