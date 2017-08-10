package com.ele.parse.test;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.bouncycastle.util.Strings;

import com.ele.parse.entity.FPEntity;
import com.ele.parse.entity.Goods;
import com.ele.parse.entity.ItemPoint;
import com.ele.parse.utils.DetailList;
import com.ele.parse.utils.PDFParser;

public class ParseTest {
	
	//======================= ★★★★★★★★★★★★★★★★★★   test start  ★★★★★★★★★★★★★★★★★★ ==============================
	
	public static void main(String[] args) throws Exception {

		FPEntity fp = new FPEntity();
		PDDocument pdDocument = PDFParser.readData("E:/resources/发票/invoice_second/More-page/xiangxi_042001500111_28181600.pdf",null, false, fp, false);
//		PDDocument pdDocument = PDFParser.readData("E:/resources/发票/invoice_second/More-page/上海天翌电子商务有限公司发票.pdf",null, false, fp, false);
		

		List<Goods> detailList = DetailList.getDetailList(pdDocument, fp);
		fp.setDetailGoodsList(detailList);

		System.out.println("\n\n\n★★★★★★★★★★★   -----  size = " + detailList.size()+ " -------★★★★★★★★★★★\n\n");
		for (Goods good : detailList) {
			System.out.println(good.toString());
		}

	}
	
	
	
	
	
	//============================ ★★★★★★★★★★★★★★★★★★  test end   ★★★★★★★★★★★★★★★★★★==============================
	
	
	

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
	
	
 public static String execute(int x,int y,int width,int height,PDDocument document,int pageIndex) throws IOException{
		
		
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
		 * 对Goods类的对象赋予属性
		 * @param goods
		 * @param i
		 * @param str
		 */
		private static void setGoodsAttri(Goods goods, int i, String str) {
			
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
		private  static int judgeOver(Goods goods){
			
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
						return 1;
					}
				}
				
				if(null != goods && goods.getSortNum().matches("小计\r\n") ){
					return 1;
				}
				
				if(attr[i]!=null&&!"".equals(attr[i])){
						return 0;
				}
				
			}
			return 1;
		}
 
}
