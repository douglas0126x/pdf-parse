package com.ele.parse.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ele.parse.entity.FPEntity;
import com.ele.parse.utils.FPUtils;
import com.ele.parse.utils.InvokeUtil;
import com.google.zxing.Result;

/**
 * Hello world!
 *
 */
public class AppTest 
{
	
	/**
	 * 二维码图片生成与解析
	 * @param args
	 * @throws Exception 
	 */
	/*
	public static void main( String[] args ) throws Exception{
			TestPrepare prepare = new TestPrepare();
			List<String> list = new ArrayList<String>();
    	
			FPUtils fpUtils = new FPUtils();
			String filePath = "F://generate";
			prepare.ergodic(new File(filePath),  list);
			String fileName = System.currentTimeMillis()+".png";
			Integer width=200,height=200;
			String content = "二维码生成测试,token="+System.currentTimeMillis();
			
			fpUtils.QRCodeGenate(filePath, fileName, width, height, content);
			for(String path:list){
				System.out.println("二维码路径="+path+"二维码内容如下：");
				Result  rs = fpUtils.QRCodeDecode(path);
				System.out.println(rs.toString());
			}
	}
	*/
	
	
	
	
    public static void main( String[] args ) throws IOException
    {	
    	System.out.println("==========application running===========");
    	TestPrepare prepare = new TestPrepare();
//    	File file = new File("E:\\resources\\发票\\catagely\\testFiles\\error\\2017-5-3");
//    	File file = new File("E:\\resources\\发票\\catagely\\testFiles\\all");
    	File file = new File("D:\\work\\eclipse_workspace\\eclipse2_workspace\\pdf-produce\\tmp\\generatePdf");
    	
//    	File file = new File("E:\\resources\\发票\\catagely\\testFiles\\百旺");
    	List<String> list = new ArrayList<String>();
    	prepare.ergodic(file, list);
    	
    	for(String path:list){
    		
    		boolean isBase64Data=false;
    		boolean isJxqzSelected=false;//是否解析签章
    		boolean onlyErWeiMa=false;//true代表只解析二维码（此时无论isJxqzSelected输入任何值都不解析）
    		long startTime = System.currentTimeMillis();
    		FPUtils fpUtiles = new FPUtils();
    		FPEntity fp = fpUtiles.setFPAttri(path,null,isBase64Data,isJxqzSelected,onlyErWeiMa);
//    		System.out.println(fp);
    		InvokeUtil.trimParse(fp);
    		long endTime = System.currentTimeMillis();
    		System.out.println("=========time======"+(endTime-startTime)+"===============");
    		System.out.println(fp);
    	}
    	
/*    	
    	for(String path:list){
    		System.out.println(path);
    		FPUtils fpUtiles = new FPUtils();
    		try {
				Result result = fpUtiles.QRCodeDecode(path);
				System.out.println("二维码内容\n"+result.getText());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	*/
    }
    	/********************************start1 直接读pdf文件路径*********************************/
    	
    	/*
    	 * 
    	for(int i=0;i<7;i++){
    		Thread t=new Thread(new Runnable(){
    			String filePath = i;
				@Override
				public void run() {
					
					System.out.println("------------------------------------");
					
					// TODO Auto-generated method stub
					boolean isBase64Data=false;
			    	boolean isJxqzSelected=true;//是否解析签章
			    	boolean onlyErWeiMa=false;//true代表只解析二维码（此时无论isJxqzSelected输入任何值都不解析）
			    	FPEntity fp = FPUtils.setFPAttri(i,null,isBase64Data,isJxqzSelected,onlyErWeiMa);
			    	InvokeUtil.trimParse(fp);
			    	
			    	System.out.println(fp);
//			    	System.out.println("---"+fp.parseJxqz()[0]);
//			    	System.out.println("---"+fp.parseJxqz()[1]);
				}
    			
    		});
    		t.start();
    	}
    	
    	*/
    	
    	
    	/********************************end1 直接读pdf文件路径*********************************/
    	
    	
    	

  

	

	
}
