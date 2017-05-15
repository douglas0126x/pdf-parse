package com.ele.generate.utils;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.ele.generate.entity.FPData;
import com.ele.generate.entity.FPDistributed;
import com.ele.generate.entity.FpKj;
import com.ele.generate.entity.FpKjmx;


public class ParseUtil {
	/**
	 * xml字符串——>Object
	 * @param respXml
	 * @return
	 */
	@SuppressWarnings("unused")
	public static FPDistributed parseResponseXML(String respXml) {

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
					
					if (currentReader.equals("FPZL")) {
//						fpkj.setfPLX(reader.getElementText());
						fpkj.setfPLX("10");
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
}
