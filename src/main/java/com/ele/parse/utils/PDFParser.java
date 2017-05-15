package com.ele.parse.utils;

import java.awt.Rectangle;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.aisino.certreq.InfoPdf;
import com.aisino.common.Base64;
import com.aisino.common.FileUtil;
import com.ele.parse.entity.FPEntity;

/**
 * PDF——>PDDocument</br>
 * 将pdf转为待处理文件格式，同时(不)解析签章
 * @author yaoxj
 *
 */
public class PDFParser {

	
	/**
	 * 将各类型pdf文件转为PDDocument；并（不）解析签章
	 * @param filePathOrbase64Data 传递数据：文件路径或者字符串类型加密数据
	 * @param base64Data 传递数据：数组类型加密数据
	 * @param isBase64Data 传递数据判断
	 * @param fp 发票模型
	 * @param isJxqzSelected 签章解析与否
	 * @return
	 * @throws Exception
	 */
	public static PDDocument readData(String filePathOrbase64Data,byte[]base64Data,boolean isBase64Data,FPEntity fp,boolean isJxqzSelected) throws Exception{
	
		PDDocument document=null;
		/**
		 * ①、数据是文件路径，不是加密数据(base64的)
		 */
		if(isBase64Data==false && null != filePathOrbase64Data && !"".equals(filePathOrbase64Data.trim())){
			FileInputStream fis = new FileInputStream(filePathOrbase64Data);
			//解析签章
			if(isJxqzSelected){
				byte[] bs = FileUtil.read(filePathOrbase64Data);
				fp.setJxqz(InfoPdf.getInfo(bs));
			}
			document = PDDocument.load(fis);
		}else{
			
			/**
			 * ②、数据是加密数据（base64）
			 */
			
			//接收的数据是字符串类型
			if(filePathOrbase64Data!=null){
				byte[] decode = Base64.decode(filePathOrbase64Data);
			    InputStream input = new ByteArrayInputStream(decode);
			    document = PDDocument.load(input);
			    
			    //解析签章
				if(isJxqzSelected){
					fp.setJxqz(InfoPdf.getInfo(decode));
				}
			    
			    
			}else{
				//接收的数据是字节数组类型
				byte[] decode = Base64.decode(base64Data);
			    InputStream input = new ByteArrayInputStream(decode);
			    document = PDDocument.load(input);
			    
			    //解析签章
				if(isJxqzSelected){
					fp.setJxqz(InfoPdf.getInfo(decode));
				}
			    
			    
			}
			
		    
		    
		}
		return document;
	}
	
	/**
	 * 根据区域识别并返回字符串
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param document
	 * @return
	 * @throws IOException
	 */
	static public String execute(int x,int y,int width,int height,PDDocument document) throws IOException{
		
		
	    PDFTextStripperByArea stripper = new PDFTextStripperByArea(); 
		stripper.setSortByPosition( true ); 
		
/*		PDPageTree pages = document.getPages();
		Iterator<PDPage> iterator = pages.iterator();
		document.removePage(0);
*/
		Rectangle rect = new Rectangle( x, y, width, height ); 
		stripper.addRegion( "class1", rect ); 
		PDPage firstPage = document.getPage(0); 
		stripper.extractRegions( firstPage ); 
		String str=stripper.getTextForRegion( "class1" );
		
		return str; 

	}
}
