package com.ele.generate.test;

import com.ele.generate.entity.FPDistributed;
import com.ele.generate.utils.GeneratePdf;
import com.ele.generate.utils.ParseUtil;


public class TestGenerate {

	public static void main(String[] args) {
		
		String responseText = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><FPXT><RESPONSE_CODE>0000</RESPONSE_CODE><RESPONSE_TIP>上传成功</RESPONSE_TIP><RESPONSE_CONTENT><FP><PDF_MD5></PDF_MD5><CYJG_CODE>001</CYJG_CODE><CYJG_TIP>查验成功发票一致</CYJG_TIP><FPZL>04</FPZL><FPDM>1100162320</FPDM><FPHM>61355879</FPHM><CYCS>0</CYCS><XFSH>110112599688066</XFSH><XFMC>北京宏源永辉酒店管理有限公司</XFMC><XFDZDH></XFDZDH><XFYHZH>北京银行光机电园区支行01091669200120108001148</XFYHZH><GFSH> </GFSH><GFMC>北京东港嘉华安全信息技术有限公司</GFMC><GFDZDH> </GFDZDH><GFYHZH> </GFYHZH><KPRQ>20170122</KPRQ><JE>168.93</JE><SE>5.07</SE><JSHJ>174.00</JSHJ><BZ></BZ><KPR></KPR><SKR></SKR><FHR></FHR><JYM>16406981958494983504</JYM><LZFPDM></LZFPDM><LZFPHM></LZFPHM><ZFBZ>N</ZFBZ><XM><MXXX><MXXH></MXXH><MC>住宿服务</MC><JE>168.93</JE><SL>3</SL><SE>5.07</SE><HSDJ></HSDJ><HSJE></HSJE><SHUL>1</SHUL><DJ>168.93203883495147</DJ><GGXH> </GGXH><JLDW></JLDW></MXXX></XM></FP></RESPONSE_CONTENT></FPXT>";
		FPDistributed  responseEntity  = ParseUtil.parseResponseXML(responseText);
		String cyjg = responseEntity.getReasonCode();
		if("RC0000".equals(cyjg)||"001".equals(cyjg)){
			long start = System.currentTimeMillis();
			GeneratePdf.generatePDF(responseEntity,null);
			long end = System.currentTimeMillis();
			System.out.println("#######    pdf--generate--sucess!!   #########\n\n"+
			"--------------Time-consuming--------------\n"+(end-start) + "mm");
		}
		
		/*
		else if("002".equals(cyjg)){
			JOptionPane.showMessageDialog(null,"超过当天查验次数！","提示信息",JOptionPane.WARNING_MESSAGE);
		}else if("003".equals(cyjg)){
			JOptionPane.showMessageDialog(null,"超过该账户查验次数！","提示信息",JOptionPane.WARNING_MESSAGE);
		}else if("004".equals(cyjg)){
			JOptionPane.showMessageDialog(null,"超过服务器最大请求数！","提示信息",JOptionPane.WARNING_MESSAGE);
		}else if("005".equals(cyjg)){
			JOptionPane.showMessageDialog(null,"请求不合法！","提示信息",JOptionPane.WARNING_MESSAGE);
		}else if("006".equals(cyjg)){
			JOptionPane.showMessageDialog(null,"查验成功发票不一致！","提示信息",JOptionPane.WARNING_MESSAGE);
		}else if("009".equals(cyjg)){
			JOptionPane.showMessageDialog(null,"所查发票不存在！","提示信息",JOptionPane.WARNING_MESSAGE);
		}else if("100".equals(cyjg)){
			JOptionPane.showMessageDialog(null,"用户不存在！","提示信息",JOptionPane.WARNING_MESSAGE);
		}else if("101".equals(cyjg)){
			JOptionPane.showMessageDialog(null,"密码不正确！","提示信息",JOptionPane.WARNING_MESSAGE);
		}else if("102".equals(cyjg)){
			JOptionPane.showMessageDialog(null,"该用户无此权限！","提示信息",JOptionPane.WARNING_MESSAGE);
		}else if("103".equals(cyjg)){
			JOptionPane.showMessageDialog(null,"不在查询IP地址范围内！","提示信息",JOptionPane.WARNING_MESSAGE);
		}else if("104".equals(cyjg)){
			JOptionPane.showMessageDialog(null,"已超过最大查验量！","提示信息",JOptionPane.WARNING_MESSAGE);
		}else if("105".equals(cyjg)){
			JOptionPane.showMessageDialog(null,"查询发票不规范！","提示信息",JOptionPane.WARNING_MESSAGE);
		}else if("106".equals(cyjg)){
			JOptionPane.showMessageDialog(null,"查验异常！","提示信息",JOptionPane.WARNING_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null,"查验失败！","提示信息",JOptionPane.WARNING_MESSAGE);
		}
		*/
		
	}
	
}
