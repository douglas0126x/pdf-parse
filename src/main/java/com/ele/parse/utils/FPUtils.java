package com.ele.parse.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.ele.parse.entity.FPEntity;
import com.ele.parse.entity.FPType;
import com.ele.parse.entity.Goods;
import com.ele.parse.entity.ItemPoint;
import com.google.zxing.Result;

/**
 * 发票服务
 * @author yaoxj
 *
 */
public class FPUtils {

	/**
	 * 换行符,支持window和linux两种环境
	 */
	private static final String hunHang=System.getProperty("line.separator");
	/**
	 * 中文冒号
	 */
	private static final String chColon ="：";
	/**
	 * 英文冒号
	 */
	private static final String enColon = ":";
	/**
	 * 商品详情
	 */
	private ArrayList<ItemPoint> itemListFlag = new ArrayList<ItemPoint>();
	/**
	 * 特殊发票种类
	 */
	private int judgeSpecial;
	 
	/**
	 * pdf识别入口方法
	 * @param filePathOrbase64Data 传递数据：文件路径或者字符串类型加密数据
	 * @param base64Data 传递数据：数组类型加密数据
	 * @param isBase64Data 传递数据判断
	 * @param isJxqzSelected 签章解析与否
	 * @param onlyErWeiMa 是否只解析二维码（如果只解析二维码,签章将不再解析）
	 * @return
	 */
	public  FPEntity setFPAttri(String filePathOrbase64Data,byte[]base64Data, boolean isBase64Data,boolean isJxqzSelected,boolean onlyErWeiMa){
		FPEntity fp = new FPEntity();
		
		try {
			if(onlyErWeiMa==true){
				//只解析二维码,签章不再解析
				isJxqzSelected=false;
			}
			PDDocument pdDocument = PDFParser.readData(filePathOrbase64Data,base64Data,isBase64Data,fp,isJxqzSelected);
			qrcodeRelated(fp, pdDocument);
			if(onlyErWeiMa==true){
				//只解析二维码,其他不再解析
				return fp;
			}
			setFP(fp, pdDocument);
			pdDocument.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fp;
		
	}
	
	/**
	 * 二维码图片生成
	 * @param filePath 二维码保存路径
	 * @param fileName 二维码保存名称，请以".png"结尾
	 * @param width 二维码图片 宽度，如：200
	 * @param height 二维码高度 ，如：200
	 * @param content 二维码内容
	 * @throws Exception
	 */
	public void QRCodeGenate(String filePath,String fileName,Integer width,Integer height,String content) throws Exception{
		ErWeiMaPicture.generateQRCodePic(filePath, fileName, width, height, content);
	}
	
	/**
	 * 二维码图片解析
	 * @param picPath
	 * @return Result
	 * @throws Exception
	 */
	public Result  QRCodeDecode(String picPath) throws Exception{
		BufferedImage image = ImageIO.read(new File(picPath));
		return ErWeiMaPicture.decode(image);
	}
	
	/**
	 * 二维码相关解析
	 * @param fp
	 * @param pdDocument
	 */
	private static void qrcodeRelated(FPEntity fp, PDDocument pdDocument) {
		//12解析二维码数据
		String erWeiMaData = ErWeiMaPicture.getErWeiMaData(pdDocument);
		fp.setErWeiMaData(erWeiMaData);
		
		//13生成二维码图片
		String erWeiMaPicturePath = ErWeiMaPicture.getPicByErWeiMaData(erWeiMaData, null);
		fp.setErWeiMaPicturePath(erWeiMaPicturePath);
	}
	
	/**
	 * 发票其他元素
	 * @param fp
	 * @param pdDocument
	 * @throws IOException
	 */
	private  void setFP(FPEntity fp, PDDocument pdDocument)
			throws IOException {
		//首先判别是否是特殊票
		judgeSpecial = judgeSpecial(pdDocument);
		
		//0设置发票名称（标题）
		String title=getTitle(judgeSpecial,pdDocument);
		fp.setTitle(title);
		
		//1设置机器编码
		String jqbh = getJqbh(judgeSpecial,pdDocument);
		fp.setJqbh(jqbh);
		
		//2设置发票代码、发票号码、开票日期、校验码
		String fpInfo = getFpInfo(judgeSpecial,pdDocument);
		String[]fpInfos=fpInfo.split(hunHang);
		fp.setFpdm(fpInfos[0]);
		fp.setFphm(fpInfos[1]);
		fp.setKprq(fpInfos[2]);
		fp.setJym(fpInfos[3]);
		
		//3购买方的名称、纳税人识别号、地址及电话、开户行及账号
		String buyerInfo = getBuyerInfo(judgeSpecial,pdDocument);
		String[] buyerInfos = buyerInfo.split(hunHang);
		fp.setBuyer_name(buyerInfos[0]);
		fp.setBuyer_nsrsbh(buyerInfos[1]);
		fp.setBuyer_addressPhoneNum(buyerInfos[2]);
		fp.setBuyer_khhandzh(buyerInfos[3]);
		
		//4密码区
		String passwordArea = getPasswordArea(judgeSpecial,pdDocument);
		fp.setPasswordArea(passwordArea);
		
		//5商品信息
		//==============================================begin=============================================================
		
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		if(FPType.FPTYPE1==judgeSpecial){
    		itemListFlag = fpType1ItemPointList;
    		int y=230;
    		int height=20;
    		setGoodsList(pdDocument, goodsList,itemListFlag,y,height);
    		
    	}else if(FPType.FPTYPE2==judgeSpecial){
    		itemListFlag = fpType2ItemPointList;
    		int y=230;
    		int height=20;
    		setGoodsList(pdDocument, goodsList,itemListFlag,y,height);
    	}else{
    		itemListFlag = itemPointList;
        	int y = 160;
        	int height = 13;
        	setGoodsList(pdDocument, goodsList,itemListFlag,y,height);
    		if(goodsList.size()==0){
    			judgeSpecial = 3;
    			itemListFlag = itemPointList_jiyejia;
    			int y_jiyejia = 150;
            	int height_jiyejia = 13;
            	setGoodsList(pdDocument, goodsList,itemListFlag,y_jiyejia,height_jiyejia);
    		}
    	}
		//===============================================end==========================================================
		
//		ArrayList<Goods> goodsList = getGoodsList(judgeSpecial,pdDocument);
		fp.setGoodsList(goodsList);
		
		//6合计金额
		String hjje = getHjje(judgeSpecial,pdDocument);
		fp.setHjje(hjje);
		
		//7合计税额
		String hjse = getHjse(judgeSpecial,pdDocument);
		fp.setHjse(hjse.replace("*", "0"));
		
		//8价税合计
		String jshj = getJshj(judgeSpecial,pdDocument);
		fp.setJshj(jshj);
		
		//9销售方名称、纳税人识别号、地址及电话、开户行及账号
		String sellerInfo = getSellerInfo(judgeSpecial,pdDocument);
		String[] sellerInfos = sellerInfo.split(hunHang);
		fp.setSeller_name(sellerInfos[0]);
		fp.setSeller_nsrsbh(sellerInfos[1]);
		fp.setSeller_addressPhoneNum(sellerInfos[2]);
		fp.setSeller_khhandzh(sellerInfos[3]);
		
		//10 备注
		String bz= getBz(judgeSpecial,pdDocument);
		fp.setBz(bz);
		
		//11 收款人、复核、开票人、销售方
		
		String personsInfo=	getPersonsInfo(judgeSpecial,pdDocument);
		fp.setPersonsInfo(personsInfo);
		String[] persons = new String[5];
		if(personsInfo.contains(enColon)){
			persons= personsInfo.split(enColon);
		}else if(personsInfo.contains(chColon)){
			persons= personsInfo.split(chColon);
		}
		
//		System.out.println("=====persons ==== assemble====");
		if(persons.length > 0){
			
			fp.getReceivers().setReceiver(persons[1]);
			fp.getReceivers().setCheck(persons[2]);
			fp.getReceivers().setDrawer(persons[3]);
			
		}
//		收款人:
//		京东商城 复核: receiver
//		开票人: check
//		京东商城 销售方: drawer
//		(章)
	}
	
	/**
	 * 发票类型判断
	 * @param pdDocument
	 * @return
	 */
	private static int judgeSpecial(PDDocument pdDocument) {
		
		try {
			int x=0;
			int y=500;
			int width=230;
			int height=20;
			String str=PDFParser.execute(x,y,width,height,pdDocument);
			if(str!=null && "说明:电子发票可以在北京市国家税务".equals(str.trim()))
				return FPType.FPTYPE1;
			else if (str!=null && "说明:电子发票可以在北京市国家税务局网站".equals(str.trim()))
				return FPType.FPTYPE2;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
		
		return FPType.FPTYPE0;
	}


	/**
	 * 输出第0项
	 * 输出机器编号
	 * 发票名称
	 */
	private static String getTitle(int judgeSpecial,PDDocument pdDocument) throws IOException{
			
		if(judgeSpecial==FPType.FPTYPE1){
			
			int x=180;
			int y=0;
			int width=300;
			int height=145;
			String str=PDFParser.execute(x,y,width,height,pdDocument);       
	    	return str;
			
		}
		else  if(judgeSpecial==FPType.FPTYPE2){
			int x=180;
			int y=0;
			int width=300;
			int height=100;
			String str=PDFParser.execute(x,y,width,height,pdDocument);       
	    	return str;
		}else{
			
			int x=180;
			int y=0;
			int width=230;
			int height=45;
			String str=PDFParser.execute(x,y,width,height,pdDocument);       
	    	return str;
		
		}
		
		
	}
	
	
	
	
	/**
	 * 输出第1项
	 * 输出机器编号
	 * 机器编号:661505060904
	 */
	private static String getJqbh(int judgeSpecial,PDDocument pdDocument) throws IOException{
		
		 if(FPType.FPTYPE1==judgeSpecial)  {
			int x=0;
			int y=70;
			int width=180;
			int height=60;
			String str=PDFParser.execute(x,y,width,height,pdDocument);       
	    	return str;
		}else  if(FPType.FPTYPE2==judgeSpecial)  {
			int x=0;
			int y=70;
			int width=180;
			int height=50;
			String str=PDFParser.execute(x,y,width,height,pdDocument);       
	    	return str;
		}else{
			
			int x=0;
			int y=70;
			int width=125;
			int height=20;
			String str=PDFParser.execute(x,y,width,height,pdDocument);       
	    	return str;
			
		}
    	
	}
	
	/**
	 * 输出第2项</br>
	 * 输出发票代码、发票号码、开票日期、校验码</br>
	 * 发票代码: 114011015609</br>
	 * 发票号码: 00593997</br>
	 * 开票日期: 2016 年 09月 20日</br>
	 * 校 验 码: 65560 89127 1390</br>
	 * @throws IOException 
	 */
	private static String getFpInfo(int judgeSpecial,PDDocument pdDocument) throws IOException{
		
		if(FPType.FPTYPE1==judgeSpecial){
			int x=470;
			int y=70;
			int width=210;
			int height=67;
			String str= PDFParser.execute(x,y,width,height,pdDocument);
			return str;
		}else if(FPType.FPTYPE2==judgeSpecial){
			int x=470;
			int y=0;
			int width=230;
			int height=120;
			String str= PDFParser.execute(x,y,width,height,pdDocument);
			return str;
		}else{
			
			int x=410;
			int y=0;
			int width=190;
			int height=87;
			String str= PDFParser.execute(x,y,width,height,pdDocument);
			return str;
			
		}
		
	}
	
	
	/**
	 * 输出第3项</br>
	 * 输出购买方的名称、纳税人识别号、地址及电话、开户行及账号</br>
	 * 名        称: 宁波海曙润兰商贸有限公司</br>
	 * 纳税人识别号: 330203573673560</br>
	 * 地 址、电 话: 海曙区中山东路181号（18-27）</br>
	 * 开户行及账号: 中国工商银行宁波市鼓楼支行 3901110009200112246</br>
	 * @throws IOException 
	 */
	private static String getBuyerInfo(int judgeSpecial,PDDocument pdDocument) throws IOException{
		
		 if(FPType.FPTYPE1==judgeSpecial){
			int x=80;
			int y=140;
			int width=310;
			int height=70;
			String str=PDFParser.execute(x,y,width,height,pdDocument);       
			return str;
		}else if(FPType.FPTYPE2==judgeSpecial){
			int x=50;
			int y=110;
			int width=310;
			int height=80;
			String str=PDFParser.execute(x,y,width,height,pdDocument);       
			return str;
		}else{
			
			int x=40;
			int y=90;
			int width=310;
			int height=60;
			String str=PDFParser.execute(x,y,width,height,pdDocument);       
			return str;
		}
	}
	
	
	/**
	 * 输出第4项
	 * 输出密码区
	 * @throws IOException 
	 */
	private static String getPasswordArea(int judgeSpecial,PDDocument pdDocument) throws IOException{
    	
		if(FPType.FPTYPE1==judgeSpecial){
			int x=490;
			int y=130;
			int width=210;
			int height=80;
			String str=PDFParser.execute(x,y,width,height,pdDocument);       
	    	return str;
		}else if(FPType.FPTYPE2==judgeSpecial){
			int x=490;
			int y=110;
			int width=210;
			int height=80;
			String str=PDFParser.execute(x,y,width,height,pdDocument);       
	    	return str;
		}else{
			
			int x=370;
			int y=90;
			int width=210;
			int height=60;
			String str=PDFParser.execute(x,y,width,height,pdDocument);       
	    	return str;
			
		}
		
	}
	
	/**
	 * 输出第5项</br>
	 * 输出商品详情信息</br>
	 * 每个字高度为13</br>
	 * @throws IOException 
	 */
	private  ArrayList<Goods> getGoodsList(int judgeSpecial,PDDocument pdDocument) throws IOException{
		
	 	System.out.println("judgeSpecial====="+judgeSpecial);
    	if(FPType.FPTYPE1==judgeSpecial){
    		
    		itemListFlag = fpType1ItemPointList;
    		ArrayList<Goods> goodsList=new ArrayList<Goods>();
    		int y=230;
    		int height=20;
    		setGoodsList(pdDocument, goodsList,itemListFlag,y,height);
    		
    		return goodsList;
    		
    			
    		
    		
    	}else if(FPType.FPTYPE2==judgeSpecial){
    		
    		itemListFlag = fpType2ItemPointList;
    		ArrayList<Goods> goodsList=new ArrayList<Goods>();
    		int y=230;
    		int height=20;
    		setGoodsList(pdDocument, goodsList,itemListFlag,y,height);
    		return goodsList;
    		
    	}else{
    		itemListFlag = itemPointList;
    		ArrayList<Goods>goodsList=new ArrayList<Goods>();
        	int y = 160;
        	int height = 13;
        	setGoodsList(pdDocument, goodsList,itemListFlag,y,height);
    		if(goodsList.size()==0){
    			judgeSpecial = 3;
    			itemListFlag = itemPointList_jiyejia;
    			int y_jiyejia = 150;
            	int height_jiyejia = 13;
            	setGoodsList(pdDocument, goodsList,itemListFlag,y_jiyejia,height_jiyejia);
    		}
    		return goodsList;
        		
    	}
	}
	
	/**
	 * 明细填充
	 * @param pdDocument
	 * @param goodsList
	 * @throws IOException
	 */
	private  void setGoodsList(PDDocument pdDocument,ArrayList<Goods> goodsList,ArrayList<ItemPoint> itemList,int y,int height) throws IOException {
		String str="";
		do{
			
			Goods goods=new Goods();
			for(int i=0;i<itemList.size();i++){
				str=PDFParser.execute(itemList.get(i).getX(),y,itemList.get(i).getWidth(),height,pdDocument);
				setGoodsAttri(goods,i,str);
			}
			
			int over=judgeOver(goods);
			if(over==1){
				break;
			}else{
				mutiLineHandle(goodsList, goods);
			}
			y=y+13;
			//System.out.println(str);
			str=str.trim();
		}while(!str.matches("合.*计"));
	}
	
	
	/**
	 * 处理多行的名称和类型
	 * @param goodsList
	 * @param goods
	 */
	private static void mutiLineHandle(ArrayList<Goods> goodsList, Goods goods) {
		//判断是否是名称太长了，进行的换行。
		int isLastName=judgeLastName(goods);
		if(isLastName==1){
			//是上一个名字的后半部分
			if(goodsList.size()-1>=0){
				String preName=goodsList.get(goodsList.size()-1).getName();
				String suffName=goods.getName();
				goodsList.get(goodsList.size()-1).setName(preName+suffName);
			}
			
		} else 	if(1==judgeLastType(goods)){
			//是上一个类型的后半部分
			if(goodsList.size()-1>=0){
				String preType=goodsList.get(goodsList.size()-1).getType();
				String suffType=goods.getType();
				goodsList.get(goodsList.size()-1).setType(preType+suffType);
			}
		}else  if(goods.getUnit().contains("单位") || goods.getPrice().contains("金")|| goods.getSe().contains("额") || goods.getSl().contains("率")){
			System.out.println("good'name is getted!");
		}else{
			goodsList.add(goods);
		}
		
	}
	
	/**
	 * 输出第6项
	 * 合计金额
	 * @throws IOException 
	 */
	private static String getHjje(int judgeSpecial,PDDocument pdDocument) throws IOException{
    	
		if(FPType.FPTYPE0==judgeSpecial){
	    	String str="";
	    	int x=380;
			int y=260;
			int width=100;
			int height=20;
	    	str=PDFParser.execute(x,y,width,height,pdDocument);      
	    	return str;
		}else if(FPType.FPTYPE3==judgeSpecial){
	    	String str="";
	    	int x=380;
			int y=250;
			int width=100;
			int height=15;
	    	str=PDFParser.execute(x,y,width,height,pdDocument);      
	    	return str;
		}else{
			String str="";
	    	int x=480;
			int y=260;
			int width=140;
			int height=120;
	    	str=PDFParser.execute(x,y,width,height,pdDocument);      
	    	return str;
						
		}
    	
    	
    	
	}
	
	
	/**
	 * 输出第7项
	 * 合计税额
	 * @throws IOException 
	 */
	private static String getHjse(int judgeSpecial,PDDocument pdDocument) throws IOException{
	 	
		if(FPType.FPTYPE0==judgeSpecial){
			String str="";
			/**
			 * author:songzx
			 */
//	    	int x=520;
			/**
			 * author:yaoxj
			 */
	    	int x=510;
			int y=260;
			int width=80;
			int height=20;
	    	
	    	str=PDFParser.execute(x,y,width,height,pdDocument);      
	    	return str;
		}else if(FPType.FPTYPE3 == judgeSpecial){
			String str="";
	    	int x=500;
			int y=250;
			int width=150;
			int height=20;
	    	str=PDFParser.execute(x,y,width,height,pdDocument);      
	    	return str;
		}else{
			String str="";
	    	int x=600;
			int y=260;
			int width=150;
			int height=120;
	    	str=PDFParser.execute(x,y,width,height,pdDocument);      
	    	return str;
		}
    	
	}
	
	
	/**
	 * 输出第8项
	 * 价税合计
	 * @throws IOException 
	 */
	private static String getJshj(int judgeSpecial,PDDocument pdDocument) throws IOException{
		
		if(FPType.FPTYPE0==judgeSpecial){
			String str="";
		   	int x=135;
			int y=280;
			int width=450;
			int height=20;
		   	str=PDFParser.execute(x,y,width,height,pdDocument);      
		    return str;
		}else if(FPType.FPTYPE3 == judgeSpecial){
			String str="";
		   	int x=130;
			int y=260;
			int width=900;
			int height=20;
		   	str=PDFParser.execute(x,y,width,height,pdDocument);      
		    return str;
		}else{
			String str="";
		   	int x=30;
			int y=380;
			int width=900;
			int height=20;
		   	str=PDFParser.execute(x,y,width,height,pdDocument);      
		    return str;
		}
	   	
	} 
	
	/**
	 * 输出第9项
	 * 销售方名称、纳税人识别号、地址及电话、开户行及账号
	 * @throws IOException 
	 */
	private static String getSellerInfo(int judgeSpecial,PDDocument pdDocument) throws IOException{

		if(FPType.FPTYPE1==judgeSpecial){
			String str="";
		   	int x=80;
			int y=400;
			int width=400;
			int height=80;
		   	str=PDFParser.execute(x,y,width,height,pdDocument);      
		   	return str;
		}else if(FPType.FPTYPE2==judgeSpecial){
			String str="";
		   	int x=40;
			int y=400;
			int width=420;
			int height=80;
		   	str=PDFParser.execute(x,y,width,height,pdDocument);      
		   	return str;
		}else if(FPType.FPTYPE3==judgeSpecial){
			String str="";
		   	int x=40;
			int y=280;
			int width=310;
			int height=60;
		   	str=PDFParser.execute(x,y,width,height,pdDocument);      
		   	return str;
		}else{
			/**
			 * author:songzx
			 * int width=310;
			 */
		   	String str="";
		   	int x=40;
			int y=300;
			int width=310;
			int height=60;
		   	str=PDFParser.execute(x,y,width,height,pdDocument);      
		   	return str;
			
		}
	}
	
	/**
	 * 输出第10项
	 * 备注
	 * @throws IOException 
	 */
	private static String getBz(int judgeSpecial,PDDocument pdDocument) throws IOException{
		
		if(FPType.FPTYPE0==judgeSpecial){
			String str="";
//	    	int x=364;
	    	int x=355;
			int y=300;
			int width=500;
			int height=60;
	    	str=PDFParser.execute(x,y,width,height,pdDocument);      
	    	return str;
		}else if(FPType.FPTYPE3 == judgeSpecial){
			String str="";
	    	int x=350;
			int y=280;
			int width=500;
			int height=60;
	    	str=PDFParser.execute(x,y,width,height,pdDocument);      
	    	return str;
		}else{
			String str="";
	    	int x=490;
			int y=400;
			int width=500;
			int height=60;
	    	str=PDFParser.execute(x,y,width,height,pdDocument);      
	    	return str;
		}
    
	}
	
	
	/**
	 * 输出第11项
	 * 收款人、复核、开票人、销售方
	 */
	private static String getPersonsInfo(int judgeSpecial,PDDocument pdDocument) throws IOException{
    	
		if(FPType.FPTYPE0==judgeSpecial){
			String str="";
	    	int x=0;
			int y=355;
			int width=500;
			int height=40;
	    	str=PDFParser.execute(x,y,width,height,pdDocument);      
	    	return str;
		}else if(FPType.FPTYPE3 == judgeSpecial){
			String str="";
	    	int x=0;
			int y=340;
			int width=650;
			int height=30;
	    	str=PDFParser.execute(x,y,width,height,pdDocument);      
	    	return str;
		}else{
			String str="";
	    	int x=0;
			int y=480;
			int width=650;
			int height=20;
	    	str=PDFParser.execute(x,y,width,height,pdDocument);      
	    	return str;
		}
    	
	}
	
	
	/**
	 * 商品详情的坐标
	 */
	private static 	ArrayList<ItemPoint>itemPointList=new ArrayList<ItemPoint>(){{
		
/* 		author:songzx:
 * 		add(new ItemPoint(0,179));//货物或应税劳务、服务名称
 *		add(new ItemPoint(179,50));//规格型号
 *		add(new ItemPoint(229,60));//单位
		add(new ItemPoint(290,60));//数量
		add(new ItemPoint(350,60));//单价
		add(new ItemPoint(480,70));//税率
*/ 	
		
/*
 * 		author:yaoxj
 */
		/*
		add(new ItemPoint(0,175));//货物或应税劳务、服务名称
		add(new ItemPoint(175,50));//规格型号
		add(new ItemPoint(229,30));//单位
		add(new ItemPoint(260,60));//数量
		add(new ItemPoint(320,70));//单价
		add(new ItemPoint(410,70));//金额
		add(new ItemPoint(480,60));//税率
		add(new ItemPoint(540,70));//税额
		*/
		
		
		add(new ItemPoint(0,175));//货物或应税劳务、服务名称
		add(new ItemPoint(175,50));//规格型号
		add(new ItemPoint(229,40));//单位
		add(new ItemPoint(270,80));//数量
		add(new ItemPoint(350,60));//单价
		add(new ItemPoint(410,70));//金额
		add(new ItemPoint(480,60));//税率
		add(new ItemPoint(540,70));//税额
		
		
	}};
	
	//吉野家发票清单定位
	private static 	ArrayList<ItemPoint>itemPointList_jiyejia=new ArrayList<ItemPoint>(){{
		add(new ItemPoint(0,179));//货物或应税劳务、服务名称
		add(new ItemPoint(179,50));//规格型号
		add(new ItemPoint(229,60));//单位
		add(new ItemPoint(290,60));//数量
		add(new ItemPoint(350,60));//单价
		add(new ItemPoint(410,70));//金额
		add(new ItemPoint(480,70));//税率
		add(new ItemPoint(550,70));//税额
		
	}};
	
	private static 	ArrayList<ItemPoint>fpType1ItemPointList=new ArrayList<ItemPoint>(){{
		add(new ItemPoint(0,230));//货物或应税劳务、服务名称
		add(new ItemPoint(220,50));//规格型号
		add(new ItemPoint(229,60));//单位
		add(new ItemPoint(350,60));//数量
		add(new ItemPoint(410,70));//单价
		add(new ItemPoint(480,70));//金额
		add(new ItemPoint(540,70));//税率
		add(new ItemPoint(600,70));//税额
	}};
	
	private static 	ArrayList<ItemPoint>fpType2ItemPointList=new ArrayList<ItemPoint>(){{
		add(new ItemPoint(0,230));//货物或应税劳务、服务名称
		add(new ItemPoint(220,50));//规格型号
		add(new ItemPoint(229,60));//单位
		add(new ItemPoint(410,30));//数量
		add(new ItemPoint(440,70));//单价
		add(new ItemPoint(450,70));//金额
		add(new ItemPoint(600,70));//税率
		add(new ItemPoint(680,70));//税额
	}};
	
	
	
	
	
	
	/**
	 * 对Goods类的对象赋予属性
	 * @param goods
	 * @param i
	 * @param str
	 */
	private static void setGoodsAttri(Goods goods, int i, String str) {
		
		// TODO Auto-generated method stub
		switch(i){
			case 0:goods.setName(str);break;//货物或应税劳务、服务名称
			case 1:goods.setType(str);break;//规格型号
			case 2:goods.setUnit(str);break;//单位
			case 3:goods.setNumber(str);break;//数量
			case 4:goods.setPrice(str);break;//单价
			case 5:goods.setTotalprice(str);break;//金额
			case 6:goods.setSl(str.replace("*", "0"));break;//税率
			case 7:goods.setSe(str.replace("*", "0"));break;//税额
		}
	}
	
	//TODO NOTE: 类型，单位等不用判断吗？
	/**
	 * 判断是否是上一个名字的后半部分</br>
	 * 类型，单位等不用判断吗？
	 * @param goods
	 * @return
	 */
	private static int judgeLastName(Goods goods){
		
		String[]attr=new String[8];
		
		attr[0] = goods.getName().trim();
		attr[1] = goods.getType().trim();
		attr[2] = goods.getUnit().trim();
		attr[3] = goods.getNumber().trim();
		attr[4] = goods.getPrice().trim();
		attr[5] = goods.getTotalprice().trim();
		attr[6] = goods.getSl().trim();
		attr[7] = goods.getSe().trim();
		
		int judgeBlank=1;
		for(int i=1;i<=7;i++){
			
			if(attr[i]!=null&&!"".equals(attr[i])){
				judgeBlank=0;
				break;
			}
		}
		
		if(judgeBlank==1&&attr[0]!=null){
			return 1;
		}else{
			return 0;
		}
		
	}
	
	/**
	 * 判断是否是上一个规格型号的后半部分</br>
	 * @param goods
	 * @return
	 */
	private static int judgeLastType(Goods goods){
		
		String[]attr=new String[8];
		
		attr[0] = goods.getType().trim();
		attr[1] = goods.getName().trim();
		attr[2] = goods.getUnit().trim();
		attr[3] = goods.getNumber().trim();
		attr[4] = goods.getPrice().trim();
		attr[5] = goods.getTotalprice().trim();
		attr[6] = goods.getSl().trim();
		attr[7] = goods.getSe().trim();
		
		int judgeBlank=1;
		for(int i=1;i<=7;i++){
			
			if(attr[i]!=null&&!"".equals(attr[i])){
				judgeBlank=0;
				break;
			}
		}
		
		if(judgeBlank==1&&attr[0]!=null){
			return 1;
		}else{
			return 0;
		}
		
	}
	
	
	/**
	 * 判断商品记录是否结束
	 * @param goods
	 * @return
	 */
	private int judgeOver(Goods goods){
		
		String[]attr=new String[8];
		
		attr[0] = goods.getName().trim();
		attr[1] = goods.getType().trim();
		attr[2] = goods.getUnit().trim();
		attr[3] = goods.getNumber().trim();
		attr[4] = goods.getPrice().trim();
		attr[5] = goods.getTotalprice().trim();
		attr[6] = goods.getSl().trim();
		attr[7] = goods.getSe().trim();
		for(int i=0;i<attr.length;i++){
			
			if(i==0){
				if(true==attr[i].matches("合.*计")){
					return 1;
				}
			}
			
			if(attr[i]!=null&&!"".equals(attr[i])){
					return 0;
			}
		}
		return 1;
	}
}
