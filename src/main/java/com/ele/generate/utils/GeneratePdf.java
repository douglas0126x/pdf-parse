package com.ele.generate.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.ele.generate.entity.FPData;
import com.ele.generate.entity.FPDistributed;
import com.ele.generate.entity.FpKj;
import com.ele.generate.entity.FpKjmx;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * pdf生成
 * @author yaoxj
 * @time 2017年3月30日下午3:55:54
 */
@SuppressWarnings("unused")
public class GeneratePdf {
	private static BaseFont fontSimsun;
	private static BaseFont fontCour;
	// private static BaseFont fontSimsun1;
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 解析xml
	 * @param respXml
	 * @return
	 */
	private static FPDistributed parseResponseXML(String respXml) {

		FPDistributed fpDistributed = new FPDistributed();
		
		ArrayList<FPData> list = new ArrayList<FPData>();
		
		FPData data = new FPData();

		XMLInputFactory factory = XMLInputFactory.newInstance();
		factory.setProperty(XMLInputFactory.IS_COALESCING, Boolean.TRUE);
		XMLStreamReader reader = null;
		try {
			reader = factory.createXMLStreamReader(new ByteArrayInputStream(
					respXml.getBytes("GBK")), "GBK");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (XMLStreamException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			int event = reader.getEventType();
			ArrayList<FpKjmx> mxlist = new ArrayList<FpKjmx>();
			FpKjmx fpKjmx = new FpKjmx();
			String beforeReader = "";
			String currentReader = "";

			FpKj fpkj = new FpKj();
			while (true) {
				// 实现有几张发票 就有几个实体类

				switch (event) {
				case XMLStreamConstants.START_DOCUMENT:
					break;
				case XMLStreamConstants.START_ELEMENT:
					// 如果此节点是父节点 就next 否则 继续执行
					currentReader = reader.getLocalName();
					
					if (currentReader.equals("FPLX")) {
						fpkj.setfPZL_DM(reader.getElementText());
						break;
					}
					if (currentReader.equals("FPDM")) {
						fpkj.setfP_DM(reader.getElementText());
						break;
					}
					if (currentReader.equals("FPHM")) {
						fpkj.setfP_HM(reader.getElementText());
						break;
					}
					if (currentReader.equals("KPRQ")) {
						fpkj.setkPRQ(reader.getElementText());
						break;
					}
					if (currentReader.equals("FPJE")) {
						fpkj.setjSHJ(reader.getElementText());
						break;
					}
					if (currentReader.equals("JYM")) {
						fpkj.setjYM(reader.getElementText());
						break;
					}
					if (currentReader.equals("CYJG_CODE")) {
						fpDistributed.setReasonCode(reader.getElementText());
						break;
					}
					if (currentReader.equals("CYCS")) {
						fpkj.setcXCS(reader.getElementText());
						break;
					}
					if (currentReader.equals("KPRQ")) {
						fpkj.setkPRQ(reader.getElementText());
						break;
					}
					if (currentReader.equals("XFMC")) {
						fpkj.setxSF_MC(reader.getElementText());
						break;
					}
					if (currentReader.equals("XFSBH")) {
						fpkj.setxSF_NSRSBH(reader.getElementText());
						break;
					}
					if (currentReader.equals("XFSH")) {
						fpkj.setxSF_NSRSBH(reader.getElementText());
						break;
					}
					if (currentReader.equals("XFDZDH")) {
						fpkj.setxSF_DZDH(reader.getElementText());
						break;
					}
					if (currentReader.equals("XFYHZH")) {
						fpkj.setxSF_YHZH(reader.getElementText());
						break;
					}
					if (currentReader.equals("GFMC")) {
						fpkj.setgMF_MC(reader.getElementText());
						break;
					}
					if (currentReader.equals("GFSBH")) {
						fpkj.setgMF_NSRSBH(reader.getElementText());
						break;
					}
					if (currentReader.equals("GFDZDH")) {
						fpkj.setgMF_DZDH(reader.getElementText());
						break;
					}
					if (currentReader.equals("GFYHZH")) {
						fpkj.setgMF_YHZH(reader.getElementText());
						break;
					}
					if (currentReader.equals("SE") && beforeReader.equals("JE")) {
						fpkj.sethJSE(reader.getElementText());
						break;
					}
					if (currentReader.equals("JSHJ")) {
						fpkj.setjSHJ(reader.getElementText());
						break;
					}
					if (currentReader.equals("BZ")) {
						fpkj.setbZ(reader.getElementText());
						break;
					}
					if (currentReader.equals("JE")
							&& (beforeReader.equals("KPRQ")
									|| beforeReader.equals("JQBH")
									|| beforeReader.equals("SBBH") || beforeReader
										.equals("JYM"))) {
						fpkj.sethJJE(reader.getElementText());
						break;
					}
					if (currentReader.equals("FHR")) {
						fpkj.setfHR(reader.getElementText());
						break;
					}
					if (currentReader.equals("SKR")) {
						fpkj.setsKR(reader.getElementText());
						break;
					}
					if (currentReader.equals("KPR")) {
						fpkj.setkPR(reader.getElementText());
						break;
					}
					if (currentReader.equals("ZFBZ")) {
						fpkj.setZfbz(reader.getElementText());
						break;
					}
					if (currentReader.equals("MC")) {
						fpKjmx.setxMMC(reader.getElementText());
						break;
					}
					if (currentReader.equals("GGXH")) {
						fpKjmx.setgGXH(reader.getElementText());
						break;
					}
					if (currentReader.equals("JLDW")) {
						fpKjmx.setDw(reader.getElementText());
						mxlist.add(fpKjmx);
						fpKjmx = new FpKjmx();
						data.setfP_KJMX(mxlist);
						break;
					}
					if (currentReader.equals("SHUL")) {
						fpKjmx.setxMSL(reader.getElementText());
						break;
					}
					if (currentReader.equals("DJ")) {
						fpKjmx.setxMDJ(reader.getElementText());
						break;
					}
					if (currentReader.equals("JE") && beforeReader.equals("MC")) {
						fpKjmx.setxMJE(reader.getElementText());
						break;
					}
					if (currentReader.equals("SL")) {
						fpKjmx.setsL(reader.getElementText());
						break;
					}
					if (currentReader.equals("SE")
							&& (beforeReader.equals("SLV") || beforeReader
									.equals("SL"))) {
						fpKjmx.setsE(reader.getElementText());
						
						break;
					}

					break;
				case XMLStreamConstants.CHARACTERS:
					break;
				case XMLStreamConstants.END_ELEMENT:
					break;
				case XMLStreamConstants.END_DOCUMENT:
					break;
				}
				if (!reader.hasNext()) {
					break;
				}
				beforeReader = currentReader;
				event = reader.next();

			}
			data.setfP_KJ(fpkj);
		} catch (Exception e) {

		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (XMLStreamException e) {

			}

		}
		list.add(data);
		fpDistributed.setData(list);
		return fpDistributed;
	}

	
	/**
	 * 生成pdf文件核心方法
	 * @param responseEntity 发票Model
	 * @param filePath 生成路径文件名,如果为null,则默认保存在temp下
	 */
	public static void generatePDF(FPDistributed responseEntity,String filePath) {
		
		// pdf读取对象
		PdfReader reader = null;
		// 字节输出流，用于写入pdf文件
		ByteArrayOutputStream out = null;
		// pdf操作对象
		PdfStamper ps = null;
		
		try {
			//创建字体
//			fontSimsun = BaseFont.createFont("C:/WINDOWS/Fonts/SIMYOU.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			fontSimsun = BaseFont.createFont("font/SIMYOU.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			fontCour = BaseFont.createFont(BaseFont.COURIER, "", false);
			//默认字体大小
			int defaultFontSize = InvoiceGenUtil.DEFUALT_FONTSIZE;
			//发票明细集合
			List<FpKjmx> fpmx = responseEntity.getData().get(0).getfP_KJMX();
			//发票开具
			FpKj fp = responseEntity.getData().get(0).getfP_KJ();
			//发票类型
			String fplx = responseEntity.getData().get(0).getfP_KJ().getfPLX();
			//发票模板
			reader = getInvoiceTemplate(fplx);
			
			out = new ByteArrayOutputStream();
			ps = new PdfStamper(reader, out);

			// pdf版式文件内容操作对象
			PdfContentByte pcb = ps.getOverContent(1);
			
			/**
			 * 开始填充内容
			 */
			
			pcb.beginText();
			// 设置字体和大小	字段输出内容和坐标
			pcb.setFontAndSize(fontCour, 9);
			/**
			 * ================基本内容填充==================
			 */
			
			//基本四项设置：发票代码，发票号码，开票日期 ，校验码
			setBasicFours(fp, fplx, pcb);
			//其他基本内容：购买方，销售方，操作人系列，密码区，备注等
			setBasicOthers(defaultFontSize, fp, pcb);
			/**
			 * ================基本内容结束================	
			 */
			/**
			 * ==============发票货物清单填充 开始============	
			 */
			boolean isQd = fpmx.size() > 8? true : false;
			if (!isQd) {
				//发票货物清单填充
				bulidA5Mx(pcb, fpmx);
				pcb.endText();
			}
			
			//二维码
			setQrCodePic(fp, fplx, pcb);
			/**
			 * ===============发票货物清单接受===============
			 */
			// 关闭pdf操作对象，刷新字节输出流
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			/**
			 * pdf保存路径和名称设置，如果为空，默认在temp文件夹下
			 */
			if(filePath == null || "".equals(filePath)){
				long time = System.currentTimeMillis();
				filePath = "temp/"+time+".pdf";  
			}
			File file = new File(filePath);  
			/**
			 * 创建"文件"最好使用父级目录
			 */
			File fileParent = file.getParentFile();  
			if(!fileParent.exists()){  
			        fileParent.mkdirs();  
			    }
			/**
			 * 创建目录
			 */
		    /*if(!file.exists()){  
		            file.mkdirs();  
		        } 
			*/
			file.createNewFile();
			
			FileOutputStream fileOps = new FileOutputStream(file);
			fileOps.write(out.toByteArray());
			fileOps.flush();
			fileOps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader != null) {
				reader.close();
			}
		}
	}

	/**
	 * 其他基本内容：购买方，销售方，操作人系列，密码区，备注等
	 * @param defaultFontSize
	 * @param fp
	 * @param pcb
	 * @throws Exception
	 */
	private static void setBasicOthers(int defaultFontSize, FpKj fp,PdfContentByte pcb) throws Exception {
		// 设置字体和大小
		pcb.setFontAndSize(fontSimsun, FontSizeUtil.getFontSize(fp.getgMF_MC()));
		pcb.showTextAligned(3, fp.getgMF_MC() == null || "null".equals(fp.getgMF_MC()) ? "" : fp.getgMF_MC(), 108, 301, 0);// 购买方名称
		pcb.showTextAligned(3, fp.getgMF_NSRSBH() == null ? "" : fp.getgMF_NSRSBH(), 108, 286, 0);						// 购买方纳税人识别号
		pcb.showTextAligned(3, fp.getgMF_DZDH() == null || "null".equals(fp.getgMF_DZDH()) ? "" : fp.getgMF_DZDH(), 108, 270, 0);// 购买方地址电话
		pcb.showTextAligned(3, fp.getgMF_YHZH() == null || "null".equals(fp.getgMF_YHZH()) ? "" : fp.getgMF_YHZH(), 108, 255, 0);// 购买方银行账号
		
		pcb.setFontAndSize(fontCour, 12);
		pcb.showTextAligned(2, "¥" + fp.gethJJE() == null || "null".equals(fp.gethJJE()) ? "" : fp.gethJJE(), 478, 126, 0);// 合计金额
		pcb.showTextAligned(2, "¥" + fp.gethJSE() == null || "null".equals(fp.gethJSE()) ? "" : fp.gethJSE(), 590, 126, 0);// 合计税额

		// 设置字体和大小
		pcb.setFontAndSize(fontSimsun, FontSizeUtil.getFontSize(fp.getxSF_MC()));
		pcb.showTextAligned(3, fp.getxSF_MC() == null || "null".equals(fp.getxSF_MC()) ? "" : fp.getxSF_MC(), 108, 91, 0);// 销售方名称
		pcb.showTextAligned(3, fp.getxSF_NSRSBH() == null || "null".equals(fp.getxSF_NSRSBH()) ? "" : fp.getxSF_NSRSBH(), 108, 77, 0);// 销售方纳税人识别号
		pcb.showTextAligned(3, fp.getxSF_DZDH() == null || "null".equals(fp.getxSF_YHZH()) ? "" : fp.getxSF_DZDH(), 108, 63, 0);// 销售方地址电话
		pcb.showTextAligned(3, fp.getxSF_YHZH() == null || "null".equals(fp.getxSF_YHZH()) ? "" : fp.getxSF_YHZH(), 108, 50, 0);// 销售方开户行及电话
		pcb.showTextAligned(3, fp.getbZ() == null || "null".equals(fp.getbZ()) ? "" : fp.getbZ(), 370, 92, 0);				// 备注

		// 设置字体和大小
		pcb.setFontAndSize(fontCour, InvoiceGenUtil.NSRSBH_FONTSIZE);
		pcb.showTextAligned(3, "¥" + fp.getjSHJ(), 474, 110, 0);// 合计税额小写
		
		// 设置字体和大小
		pcb.setFontAndSize(fontSimsun, defaultFontSize);
		pcb.showTextAligned(3, Money2CNUtil.money2CN(new BigDecimal(fp.getjSHJ())), 195, 110, 0);// 合计税额大写
		// pcb.setFontAndSize(fontSimsun1, 9);
		pcb.setFontAndSize(fontSimsun, defaultFontSize);
		pcb.showTextAligned(3, fp.getsKR() == null || "null".equals(fp.getsKR()) ? "" : fp.getsKR(), 65, 30, 0);// 收款人
		pcb.showTextAligned(3, fp.getfHR() == null || "null".equals(fp.getfHR()) ? "" : fp.getfHR(), 215, 30, 0);// 复合人
		pcb.showTextAligned(3, fp.getkPR() == null || "null".equals(fp.getkPR()) ? "" : fp.getkPR(), 347, 30, 0);// 开票人
	}
	/**
	 * 根据发票类型填充发票基础4项
	 * @param fp
	 * @param fplx
	 * @param pcb
	 */
	private static void setBasicFours(FpKj fp, String fplx, PdfContentByte pcb) {
		if("01".equals(fplx)||"04".equals(fplx)){
			pcb.showTextAligned(3, fp.getfP_DM()==null?"":fp.getfP_DM(), 475, 357, 0);// 发票代码375
			pcb.showTextAligned(3, fp.getfP_HM()==null?"":fp.getfP_HM(), 475, 340, 0);// 发票号码357
			pcb.showTextAligned(3, fp.getkPRQ().substring(0, 4)
					+ "  " + fp.getkPRQ().substring(4, 6) + "  "
					+ fp.getkPRQ().substring(6, 8), 477, 322, 0);					  // 开票日期340
			pcb.showTextAligned(3, getCheckCode(fp.getjYM()), 128, 317, 0);			  // 校验码475  322
		}else{
			pcb.showTextAligned(3, fp.getfP_DM()==null?"":fp.getfP_DM(), 475, 375, 0);// 发票代码375
			pcb.showTextAligned(3, fp.getfP_HM()==null?"":fp.getfP_HM(), 475, 357, 0);// 发票号码357
			pcb.showTextAligned(3, fp.getkPRQ().substring(0, 4)
					+ "  " + fp.getkPRQ().substring(4, 6) + "  "
					+ fp.getkPRQ().substring(6, 8), 477, 340, 0);						// 开票日期340
			pcb.showTextAligned(3, getCheckCode(fp.getjYM()), 475, 322, 0);				// 校验码475  322
		}
	}
	
	private static void setQrCodePic(FpKj fp, String fplx, PdfContentByte pcb) throws Exception {
		Image image = Image.getInstance("D:/work/eclipse_workspace/eclipse2_workspace/pdf-parse/temp/tempQRCode.png");
//		Image image = Image.getInstance("F:/pic/id_pic/positive.jpg");
		
//		
//		image.setAlignment(Image.LEFT | Image.TEXTWRAP); 
//		image.setBorder(Image.BOX); 
//		image.setBorderWidth(10); 
//		image.setBorderColor(BaseColor.WHITE); 
//	    image.scaleToFit(1000, 72);//大小 
//	    image.setRotationDegrees(-30);//旋转 
		//TOOD  坐标
		image.setAbsolutePosition(20, 330.0f); // set the first background image of the absolute   
		image.scaleToFit(50.0f,50.0f);  
		pcb.addImage(image);  
		
		
	}
	
	
	
	/**
	 * 根据发票类型获取发票生成模板
	 * @param fplx
	 * @return reader
	 * @throws IOException
	 */
	private static PdfReader getInvoiceTemplate(String fplx) throws IOException {
		PdfReader reader;
		// 初始化 pdf读取对象  字节输出流  pdf操作对象
		if("04".equals(fplx)){
			//增值税普通发票——北京 
			reader = new PdfReader("/resourcesFile/template/zzhshptfp_bj.pdf");
		}else if("01".equals(fplx)){
			//增值税专用发票——北京
			reader = new PdfReader("/resourcesFile/template/zzhshzhyfp_bj.pdf");
		}else{
			//增值税普通发票——上海
//			reader = new PdfReader("template/zzhshptfp_dz_sh.pdf");
			reader = new PdfReader("template/fp_empty.pdf");
		}
		return reader;
	}
	/**
	 * 发票货物清单内容设置
	 * @param pcb
	 * @param fP_KJMX
	 */
	private static void bulidA5Mx(PdfContentByte pcb, List<FpKjmx> fP_KJMX) {
		int fontSize = InvoiceGenUtil.DEFUALT_FONTSIZE;
		for (int i = 0; i < fP_KJMX.size(); i++) {
			FpKjmx mx = fP_KJMX.get(i);
			int y = 225 - (12 * i);
			pcb.setFontAndSize(fontSimsun, fontSize);
			pcb.showTextAligned(3, mx.getxMMC()==null?"":mx.getxMMC(), 27, y, 0);// 货物名称
			pcb.setFontAndSize(fontCour, fontSize);
			pcb.showTextAligned(3, mx.getgGXH()==null?"":mx.getgGXH(), 178, y, 0);// 规格型号
			pcb.setFontAndSize(fontSimsun, fontSize);
			pcb.showTextAligned(1, mx.getDw()==null?"":mx.getDw(), 238, y, 0);// 单位
			pcb.setFontAndSize(fontCour, fontSize);
			pcb.showTextAligned(2, mx.getxMSL()==null?"":mx.getxMSL(), 317, y, 0);// 数量
			pcb.showTextAligned(2, (String) (mx.getxMDJ()==null?"":(mx.getxMDJ().length()>12?mx.getxMDJ().subSequence(0, 12):mx.getxMDJ())), 390, y, 0);// 单价
			pcb.showTextAligned(2, mx.getxMJE()==null?"":mx.getxMJE(), 478, y, 0);// 金额

			pcb.showTextAligned(2, mx.getsL()==null?"":mx.getsL(), 502, y, 0);// 税率
			pcb.showTextAligned(2, mx.getsE()==null?"":mx.getsE(), 590, y, 0);// 税额
		}
	}
	
	/**
	 * 获取校验码
	 */
	private static String getCheckCode(String source){
		int len = 0;
		String target = "";
		for (int i = 0; i < source.length(); i++) {
			target = target + source.substring(len, len + 1);
			len++;
			if (len % 5 == 0) {
				target = target + " ";
			}
		}
		return target;
	}

	/**
	 * 测试生成
	 * @param args
	 */
	public static void main(String[] args) {
		String responseXML = "<?xml version=\"1.0\" encoding=\"GBK\"?><FPXT>"
				+ "<RESPONSE_CODE>0000</RESPONSE_CODE>"
				+ "<RESPONSE_TIP>上传成功</RESPONSE_TIP>" 
				+ "<RESPONSE_CONTENT>"
				+ "<FP><PDF_MD5></PDF_MD5>" 
				+ "<CYJG_CODE>RC0000</CYJG_CODE>"
				+ "<CYJG_TIP>执行成功</CYJG_TIP>" 
				+ "<FPZL>1</FPZL>"
				+ "<FPDM>000099999991</FPDM>" 
				+ "<FPHM>10019349</FPHM>"
				+ "<CYCS>1</CYCS>" 
				+ "<XFSH>915001020005254741</XFSH>" 
				+ "<XFMC>中国移动通信集团浙江有限公司宁波分公司</XFMC>"
				+ "<XFDZDH>浙江省宁波市国家高新技术开发区光华路2号10086</XFDZDH>"
				+ "<XFYHZH>null</XFYHZH>" 
				+ "<GFSH>null</GFSH>"
				+ "<GFMC>个人</GFMC>" 
				+ "<GFDZDH>null</GFDZDH>"
				+ "<GFYHZH>null</GFYHZH>" 
				+ "<KPRQ>20160512</KPRQ>"
				+ "<JE>1.75</JE>" 
				+ "<SE>1.75</SE>" 
				+ "<JSHJ>2</JSHJ>"
				+ "<BZ>内部测试发票</BZ>" 
				+ "<KPR>移动测试</KPR>" 
				+ "<SKR>收款员</SKR>"
				+ "<FHR>复合人</FHR>" 
				+ "<JYM>61249278253316108189</JYM>"
				+ "<LZFPDM></LZFPDM>" 
				+ "<LZFPHM></LZFPHM>" 
				+ "<ZFBZ></ZFBZ>"
				+ "<XM><MXXX>" 
				+ "<MXXH>1</MXXH>" 
				+ "<MC>套餐及固定费</MC>"
				+ "<JE>0.85</JE>" 
				+ "<SL>17%</SL>" 
				+ "<SE>0.15</SE>"
				+ "<HSDJ></HSDJ>" 
				+ "<HSJE></HSJE>" 
				+ "<SHUL>1</SHUL>"
				+ "<DJ>0.85470085</DJ>" 
				+ "<GGXH></GGXH>" 
				+ "<JLDW></JLDW>"
				+ "</MXXX><MXXX>" 
				+ "<MXXH>2</MXXH>" 
				+ "<MC>短彩信费</MC>"
				+ "<JE>0.90</JE>" 
				+ "<SL>11%</SL>" 
				+ "<SE>0.10</SE>"
				+ "<HSDJ></HSDJ>" 
				+ "<HSJE></HSJE>" 
				+ "<SHUL>1</SHUL>"
				+ "<DJ>0.90090090</DJ>" 
				+ "<GGXH></GGXH>" 
				+ "<JLDW></JLDW>"
				+ "</MXXX></XM></FP>" 
				+ "</RESPONSE_CONTENT></FPXT>";
		// System.out.println(generatePDF());
		
		String anotherResponseXML = "<?xml version=\"1.0\" encoding=\"GBK\"?><FPXT>"
				+ "<RESPONSE_CODE>0000</RESPONSE_CODE>"
				+ "<RESPONSE_TIP>上传成功</RESPONSE_TIP>" 
				+ "<RESPONSE_CONTENT>"
				+ "<FP><PDF_MD5></PDF_MD5>" 
				+ "<CYJG_CODE>RC0000</CYJG_CODE>"
				+ "<CYJG_TIP>执行成功</CYJG_TIP>" 
				+ "<FPZL>1</FPZL>"
				+ "<FPDM>000099999991</FPDM>" 
				+ "<FPHM>10019349</FPHM>"
				+ "<CYCS>1</CYCS>" 
				+ "<XFSH>915001020005254741</XFSH>" 
				+ "<XFMC>xfmc</XFMC>"
				+ "<XFDZDH>xfdzdh</XFDZDH>"
				+ "<XFYHZH>null</XFYHZH>" 
				+ "<GFSH>null</GFSH>"
				+ "<GFMC>gr</GFMC>" 
				+ "<GFDZDH>null</GFDZDH>"
				+ "<GFYHZH>null</GFYHZH>" 
				+ "<KPRQ>20160512</KPRQ>"
				+ "<JE>1.75</JE>" 
				+ "<SE>0.25</SE>" 
				+ "<JSHJ>2</JSHJ>"
				+ "<BZ>nc</BZ>" 
				+ "<KPR>yc</KPR>" 
				+ "<SKR>sky</SKR>"
				+ "<FHR>fhr</FHR>" 
				+ "<JYM>61249278253316108189</JYM>"
				+ "<LZFPDM></LZFPDM>" 
				+ "<LZFPHM></LZFPHM>" 
				+ "<ZFBZ></ZFBZ>"
				+ "<XM><MXXX>" 
				+ "<MXXH>1</MXXH>" 
				+ "<MC>cf</MC>"
				+ "<JE>0.85</JE>" 
				+ "<SL>17%</SL>" 
				+ "<SE>0.15</SE>"
				+ "<HSDJ></HSDJ>" 
				+ "<HSJE></HSJE>" 
				+ "<SHUL>1</SHUL>"
				+ "<DJ>0.85470085</DJ>" 
				+ "<GGXH></GGXH>" 
				+ "<JLDW></JLDW>"
				+ "</MXXX><MXXX>" 
				+ "<MXXH>2</MXXH>" 
				+ "<MC>dxf</MC>"
				+ "<JE>0.90</JE>" 
				+ "<SL>11%</SL>" 
				+ "<SE>0.10</SE>"
				+ "<HSDJ></HSDJ>" 
				+ "<HSJE></HSJE>" 
				+ "<SHUL>1</SHUL>"
				+ "<DJ>0.90090090</DJ>" 
				+ "<GGXH></GGXH>" 
				+ "<JLDW></JLDW>"
				+ "</MXXX></XM></FP>" 
				+ "</RESPONSE_CONTENT></FPXT>";
//		generatePDF(responseXML,null);
	}

}
