package com.ele.parse.utils;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.ele.parse.entity.FPEntity;
import com.ele.parse.entity.Goods;
import com.ele.parse.entity.ItemPoint;

/**
 * 识别货物应税劳务清单并返回list集合
 * @author yaoxj
 * @time 2017年8月1日上午10:43:49
 */
public class DetailList {
	
	/**
	 * pdf文本区域化获取 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param document
	 * @param pageIndex
	 * @return
	 * @throws IOException
	 */
	 private static String execute(int x,int y,int width,int height,PDDocument document,int pageIndex) throws IOException{
			
			
		    PDFTextStripperByArea stripper = new PDFTextStripperByArea(); 
			stripper.setSortByPosition( true ); 
			
	/*		PDPageTree pages = document.getPages();
			Iterator<PDPage> iterator = pages.iterator();
			document.removePage(0);
	*/		
			
			int numbers = document.getNumberOfPages();
			
			Rectangle rect = new Rectangle( x, y, width, height ); 
			stripper.addRegion( "class1", rect ); 
			PDPage firstPage = document.getPage(pageIndex); 
			stripper.extractRegions( firstPage ); 
			String str=stripper.getTextForRegion( "class1" );
			
			return str; 
			
		}
	 
	public static List<Goods> getDetailList(PDDocument pdDocument, FPEntity fp)	throws IOException {

		
		
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		int pages = pdDocument.getNumberOfPages();
		System.out.println("=============== page  "+pages+"===============");
		for(int index = 1; index<pages; index++){
			
			int height = coordinate_normal.get("index_width");
			int y = coordinate_normal.get("index_y");
			String str = "";
			
			
			//======================== useless code ====================
			if(index == 2){
				System.out.println("notice:page == 2!!");
			}
			int count = 0;
			//=========================== end ===========================
			
			
			do {
				Goods goods = new Goods();
				for (int i = 0; i < ItemSpecialList.size(); i++) {
					str = execute(ItemSpecialList.get(i).getX(), y,ItemSpecialList.get(i).getWidth(), height,pdDocument,index);
					System.out.println("\n第 "+index+"页  "+"loop =" + (count) + "   regnize==" + str);
					setGoodsListAttri(goods, i, str);
				}
	
				int over = judgeListOver(goods);
				if (over == 1) {
					break;
				} else {
					mutiLineHandle(goodsList, goods);
				}
				y = y + height;
				count++;
				str = str.trim();
			
			}while (!str.matches("小计"));
			
		}
		
		return goodsList;
	}
	/**
	 * 货物应税劳务清单
	 */
	private static 	ArrayList<ItemPoint>ItemDetailPointList=new ArrayList<ItemPoint>(){{
		add(new ItemPoint(0,50));//序号
		add(new ItemPoint(50,170));//货物或应税劳务、服务名称
		add(new ItemPoint(220,50));//规格型号
		add(new ItemPoint(270,40));//单位
		add(new ItemPoint(300,60));//数量
		add(new ItemPoint(370,70));//单价
		add(new ItemPoint(440,50));//金额
		add(new ItemPoint(490,50));//税率
		add(new ItemPoint(550,70));//税额
	}};
	
	/**
	 * 货物应税劳务清单
	 */
	private static 	ArrayList<ItemPoint> ItemSpecialList = new ArrayList<ItemPoint>(){{
		add(new ItemPoint(0,50));//序号
		add(new ItemPoint(50,140));//货物或应税劳务、服务名称
		add(new ItemPoint(200,50));//规格型号
		add(new ItemPoint(270,40));//单位
		add(new ItemPoint(300,60));//数量
		add(new ItemPoint(370,70));//单价
		add(new ItemPoint(440,50));//金额
		add(new ItemPoint(490,50));//税率
		add(new ItemPoint(550,70));//税额
	}};
	
	private static Map<String,Integer> coordinate_normal= new HashMap<String, Integer>(){{
		put("index_y",175);
		put("index_width",12);
	}};
	
	private static Map<String,Integer> coordinate_special= new HashMap<String, Integer>(){{
		put("index_y",180);
		put("index_width",17);
	}};
	
	/**
	 * 处理多行的名称和类型
	 * @param goodsList
	 * @param goods
	 */
	private static void mutiLineHandle(ArrayList<Goods> goodsList, Goods goods) {
		//判断是否是名称太长了，进行的换行。
		int isLastName=judgeListLastName(goods);
		if(isLastName==1){
			//是上一个名字的后半部分
			if(goodsList.size()-1>=0){
				String preName=goodsList.get(goodsList.size()-1).getName();
				String suffName=goods.getName();
				goodsList.get(goodsList.size()-1).setName(preName+suffName);
			}
			
		} else 	if(1==judgeListLastType(goods)){
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
	 * 判断是否是上一个名字的后半部分</br>
	 * 类型，单位等不用判断吗？
	 * @param goods
	 * @return
	 */
	private static int judgeListLastName(Goods goods){
		
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
	private static int judgeListLastType(Goods goods){
		
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
	 * 对Goods类的对象赋予属性
	 * @param goods
	 * @param i
	 * @param str
	 */
	private static void setGoodsListAttri(Goods goods, int i, String str) {
		
		// TODO Auto-generated method stub
		switch(i){
			case 0:goods.setSortNum(str);break;// 清单序号
			case 1:goods.setName(str);break;//货物或应税劳务、服务名称
			case 2:goods.setType(str);break;//规格型号
			case 3:goods.setUnit(str);break;//单位
			case 4:goods.setNumber(str);break;//数量
			case 5:goods.setPrice(str);break;//单价
			case 6:goods.setTotalprice(str);break;//金额
			case 7:goods.setSl(str.replace("*", "0"));break;//税率
			case 8:goods.setSe(str.replace("*", "0"));break;//税额
		}
	}
	
	/**
	 * 判断商品记录是否结束
	 * @param goods
	 * @return
	 */
	private  static int judgeListOver(Goods goods){
		
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
				if(true==attr[i].matches("小计")){
					System.out.println("good name = 小计");
					return 1;
				}
			}
			
			if(null != goods && goods.getSortNum().matches("小计\r\n") ){
				System.out.println("sortnum = 小计");
				return 1;
			}
			
			if(attr[i]!=null&&!"".equals(attr[i])){
					return 0;
			}
			
		}
		System.out.println("all is empity!");
		return 1;
	}
}
