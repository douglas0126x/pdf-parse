package com.ele.generate.entity;

/**
 * 发票开具实体内容
 * @author songzhixin
 *
 */
public class FpKj {

	/**
	 * 发票请求流水号
	 */
	private String fPQQLSH;
	
	/**
	 * 发票代码
	 */
	private String fP_DM;
    
	/**
	 * 发票号码
	 */
	private String fP_HM;
	
	/**
	 * 开票日期
	 */
	private String kPRQ;
	
	/**
	 * 发票密文
	 */
	private String fWMW;
	/**
	 * 校验码
	 */
	private String jYM;

	/**
	 * 二维码
	 */
	private String eWM;
	
	/**
	 * 开票类型
	 */
	private String kPLX;

	/**
	 * 订单号
	 */
	private String dDH;
	
	/**
	 * 订单时间
	 */
	private String dDSJ;
	
	/**
	 * 查询次数
	 */
	private String cXCS;

	/**
	 * 销售方纳税人识别号
	 */
	private String xSF_NSRSBH;
    /**
	 * 销售方名称
	 */
	private String xSF_MC;
	/**
	 * 销售方地址、电话
	 */
	private String xSF_DZDH;
	/**
	 * 销售方银行账号
	 */
	private String xSF_YHZH;
	/**
	 * 购买方名称
	 */
	private String gMF_MC;
	/**
	 * 购买方纳税人识别号
	 */
	private String gMF_NSRSBH;
	/**
	 * 购买方地址、电话
	 */
	private String gMF_DZDH;
	/**
	 * 购买方银行账号
	 */
	private String gMF_YHZH;
	/**
	 * 购买方手机
	 */
	private String gMF_SJ;
	/**
	 * 购买方微信
	 */
	private String gMF_WX;
	/**
	 * 开票人
	 */
	private String kPR;
	/**
	 * 收款人
	 */
	private String sKR;
	/**
	 * 复核人
	 */
	private String fHR;
	/**
	 * 原发票代码
	 */
	private String yFP_DM;
	/**
	 * 原发票号码
	 */
	private String yFP_HM;
	/**
	 * 价税合计
	 */
	private String jSHJ;
	/**
	 * 合计金额
	 */
	private String hJJE;
	/**
	 * 合计税额
	 */
	private String hJSE;
	/**
	 * 备注
	 */
	private String bZ;
	/**
	 * 发票状态
	 */
	private String fPZT;
	/**
	 * 税控设备编号
	 */
	private String jQBH;
	/**
	 * 开票机号
	 */
	private String kPJH;
	/**
	 * 税务机构代码
	 */
	private String sWJG_DM;
	/**
	 * 开票项目数量
	 */
	private String  kPXMSL;
	/**
	 * 主要开票项目
	 */
	private String  kPXM;
	/**
	 * 发票种类代码
	 */
	private String  fPZL_DM;
	/**
	 * 发票类别
	 */
	private String  fPLB;
	/**
	 * 行业名称
	 */
	private String  hY_MC;
	/**
	 * 行业代码
	 */
	private String hY_DM;
	/**
	 * 冲红标志
	 */
	private String cH_BZ;
	/**
	 * 开票状态
	 */
	private String  kPZT;
	/**
	 * 开票电商编码
	 */
	private String  dSPTBM;
	/**
	 * 购买方邮箱
	 */
	private String  gMF_EMAIL;
	/**
	 * 查验入账状态
	 */
	private String   cYRZZT; 
	/**
	 * 版式文件路径
	 */
	private String fILE_PATH;
	/**
	 * 版式文件内容[VARCHAR2(不定长，大概100-200kb左右，可为空)]
	 */
	private String fILE_CONTENT;
	/**
	 * 购买方企业类型
	 */
	private String   gMFQYLX;
	/**
	 * 特殊冲红标志
	 */
	private String   tSCHBZ;
	/**
	 * 冲红原因
	 */
	private String   cHYY ;
	/**
	 * 销货清单
	 */
	private String   xHQD;
	/**
	 * 所属月份
	 */
	private String    sSYF;
	/**
	 * 销货清单标志
	 */
	private String   xHQDBZ;
	/**
	 * 数字签名
	 */
	private String sZQM;
	/**
	 * 编码表版本号
	 */
	private String bMB_BBH;
	/**
	 * 清单标志
	 */
	private String qD_BZ;
	/**
	 * 清单发票项目名称[VARCHAR2(200)]
	 */
	private String qDXMMC;
	/**
	 * 发票类型
	 */
	private String fPLX;
	/**
	 * 插入时间
	 * @return
	 */
	private String __insertTime;
	
	//作废标志
	private String zfbz;
	
	public String getkPR() {
		return kPR;
	}

	public void setkPR(String kPR) {
		this.kPR = kPR;
	}

	public String getkPLX() {
		return kPLX;
	}

	public void setkPLX(String kPLX) {
		this.kPLX = kPLX;
	}

	public String getgMF_MC() {
		return gMF_MC;
	}

	public void setgMF_MC(String gMFMC) {
		gMF_MC = gMFMC;
	}
	
	public String getfPQQLSH() {
		return fPQQLSH;
	}

	public void setfPQQLSH(String fPQQLSH) {
		this.fPQQLSH = fPQQLSH;
	}

	public String getfP_DM() {
		return fP_DM;
	}

	public void setfP_DM(String fPDM) {
		fP_DM = fPDM;
	}

	public String getfP_HM() {
		return fP_HM;
	}

	public void setfP_HM(String fPHM) {
		fP_HM = fPHM;
	}

	public String getkPRQ() {
		return kPRQ;
	}

	public void setkPRQ(String kPRQ) {
		this.kPRQ = kPRQ;
	}

	public String getfWMW() {
		return fWMW;
	}

	public void setfWMW(String fWMW) {
		this.fWMW = fWMW;
	}

	public String getjYM() {
		return jYM;
	}

	public void setjYM(String jYM) {
		this.jYM = jYM;
	}

	public String geteWM() {
		return eWM;
	}

	public void seteWM(String eWM) {
		this.eWM = eWM;
	}

	public String getdDH() {
		return dDH;
	}

	public void setdDH(String dDH) {
		this.dDH = dDH;
	}

	public String getdDSJ() {
		return dDSJ;
	}

	public void setdDSJ(String dDSJ) {
		this.dDSJ = dDSJ;
	}

	public String getcXCS() {
		return cXCS;
	}

	public void setcXCS(String cXCS) {
		this.cXCS = cXCS;
	}

	public String getxSF_NSRSBH() {
		return xSF_NSRSBH;
	}

	public void setxSF_NSRSBH(String xSFNSRSBH) {
		xSF_NSRSBH = xSFNSRSBH;
	}

	public String getxSF_MC() {
		return xSF_MC;
	}

	public void setxSF_MC(String xSFMC) {
		xSF_MC = xSFMC;
	}

	public String getxSF_DZDH() {
		return xSF_DZDH;
	}

	public void setxSF_DZDH(String xSFDZDH) {
		xSF_DZDH = xSFDZDH;
	}

	public String getxSF_YHZH() {
		return xSF_YHZH;
	}

	public void setxSF_YHZH(String xSFYHZH) {
		xSF_YHZH = xSFYHZH;
	}

	public String getgMF_NSRSBH() {
		return gMF_NSRSBH;
	}

	public void setgMF_NSRSBH(String gMFNSRSBH) {
		gMF_NSRSBH = gMFNSRSBH;
	}

	public String getgMF_DZDH() {
		return gMF_DZDH;
	}

	public void setgMF_DZDH(String gMFDZDH) {
		gMF_DZDH = gMFDZDH;
	}

	public String getgMF_YHZH() {
		return gMF_YHZH;
	}

	public void setgMF_YHZH(String gMFYHZH) {
		gMF_YHZH = gMFYHZH;
	}

	public String getgMF_SJ() {
		return gMF_SJ;
	}

	public void setgMF_SJ(String gMFSJ) {
		gMF_SJ = gMFSJ;
	}

	public String getgMF_WX() {
		return gMF_WX;
	}

	public void setgMF_WX(String gMFWX) {
		gMF_WX = gMFWX;
	}

	public String getsKR() {
		return sKR;
	}

	public void setsKR(String sKR) {
		this.sKR = sKR;
	}

	public String getfHR() {
		return fHR;
	}

	public void setfHR(String fHR) {
		this.fHR = fHR;
	}

	public String getyFP_DM() {
		return yFP_DM;
	}

	public void setyFP_DM(String yFPDM) {
		yFP_DM = yFPDM;
	}

	public String getyFP_HM() {
		return yFP_HM;
	}

	public void setyFP_HM(String yFPHM) {
		yFP_HM = yFPHM;
	}

	public String getjSHJ() {
		return jSHJ;
	}

	public void setjSHJ(String jSHJ) {
		this.jSHJ = jSHJ;
	}

	public String gethJJE() {
		return hJJE;
	}

	public void sethJJE(String hJJE) {
		this.hJJE = hJJE;
	}

	public String gethJSE() {
		return hJSE;
	}

	public void sethJSE(String hJSE) {
		this.hJSE = hJSE;
	}

	public String getbZ() {
		return bZ;
	}

	public void setbZ(String bZ) {
		this.bZ = bZ;
	}

	public String getfPZT() {
		return fPZT;
	}

	public void setfPZT(String fPZT) {
		this.fPZT = fPZT;
	}

	public String getjQBH() {
		return jQBH;
	}

	public void setjQBH(String jQBH) {
		this.jQBH = jQBH;
	}

	public String getkPJH() {
		return kPJH;
	}

	public void setkPJH(String kPJH) {
		this.kPJH = kPJH;
	}

	public String getsWJG_DM() {
		return sWJG_DM;
	}

	public void setsWJG_DM(String sWJGDM) {
		sWJG_DM = sWJGDM;
	}

	public String getkPXMSL() {
		return kPXMSL;
	}

	public void setkPXMSL(String kPXMSL) {
		this.kPXMSL = kPXMSL;
	}

	public String getkPXM() {
		return kPXM;
	}

	public void setkPXM(String kPXM) {
		this.kPXM = kPXM;
	}

	public String getfPZL_DM() {
		return fPZL_DM;
	}

	public void setfPZL_DM(String fPZLDM) {
		fPZL_DM = fPZLDM;
	}

	public String getfPLB() {
		return fPLB;
	}

	public void setfPLB(String fPLB) {
		this.fPLB = fPLB;
	}

	public String gethY_MC() {
		return hY_MC;
	}

	public void sethY_MC(String hYMC) {
		hY_MC = hYMC;
	}

	public String gethY_DM() {
		return hY_DM;
	}

	public void sethY_DM(String hYDM) {
		hY_DM = hYDM;
	}

	public String getcH_BZ() {
		return cH_BZ;
	}

	public void setcH_BZ(String cHBZ) {
		cH_BZ = cHBZ;
	}

	public String getkPZT() {
		return kPZT;
	}

	public void setkPZT(String kPZT) {
		this.kPZT = kPZT;
	}

	public String getdSPTBM() {
		return dSPTBM;
	}

	public void setdSPTBM(String dSPTBM) {
		this.dSPTBM = dSPTBM;
	}

	public String getgMF_EMAIL() {
		return gMF_EMAIL;
	}

	public void setgMF_EMAIL(String gMFEMAIL) {
		gMF_EMAIL = gMFEMAIL;
	}

	public String getcYRZZT() {
		return cYRZZT;
	}

	public void setcYRZZT(String cYRZZT) {
		this.cYRZZT = cYRZZT;
	}

	public String getfILE_PATH() {
		return fILE_PATH;
	}

	public void setfILE_PATH(String fILEPATH) {
		fILE_PATH = fILEPATH;
	}

	public String getfILE_CONTENT() {
		return fILE_CONTENT;
	}

	public void setfILE_CONTENT(String fILECONTENT) {
		fILE_CONTENT = fILECONTENT;
	}

	public String getgMFQYLX() {
		return gMFQYLX;
	}

	public void setgMFQYLX(String gMFQYLX) {
		this.gMFQYLX = gMFQYLX;
	}

	public String gettSCHBZ() {
		return tSCHBZ;
	}

	public void settSCHBZ(String tSCHBZ) {
		this.tSCHBZ = tSCHBZ;
	}

	public String getcHYY() {
		return cHYY;
	}

	public void setcHYY(String cHYY) {
		this.cHYY = cHYY;
	}

	public String getxHQD() {
		return xHQD;
	}

	public void setxHQD(String xHQD) {
		this.xHQD = xHQD;
	}

	public String getsSYF() {
		return sSYF;
	}

	public void setsSYF(String sSYF) {
		this.sSYF = sSYF;
	}

	public String getxHQDBZ() {
		return xHQDBZ;
	}

	public void setxHQDBZ(String xHQDBZ) {
		this.xHQDBZ = xHQDBZ;
	}

	public String getsZQM() {
		return sZQM;
	}

	public void setsZQM(String sZQM) {
		this.sZQM = sZQM;
	}

	public String getbMB_BBH() {
		return bMB_BBH;
	}

	public void setbMB_BBH(String bMBBBH) {
		bMB_BBH = bMBBBH;
	}

	public String getqD_BZ() {
		return qD_BZ;
	}

	public void setqD_BZ(String qDBZ) {
		qD_BZ = qDBZ;
	}

	public String getqDXMMC() {
		return qDXMMC;
	}

	public void setqDXMMC(String qDXMMC) {
		this.qDXMMC = qDXMMC;
	}

	public String getfPLX() {
		return fPLX;
	}

	public void setfPLX(String fPLX) {
		this.fPLX = fPLX;
	}

	public String get__insertTime() {
		return __insertTime;
	}

	public void set__insertTime(String __insertTime) {
		this.__insertTime = __insertTime;
	}

	public String getZfbz() {
		return zfbz;
	}

	public void setZfbz(String zfbz) {
		this.zfbz = zfbz;
	}

	@Override
	public String toString() {
		return "FpKj [fPQQLSH=" + fPQQLSH + ", fP_DM=" + fP_DM + ", fP_HM="
				+ fP_HM + ", kPRQ=" + kPRQ + ", fWMW=" + fWMW + ", jYM=" + jYM
				+ ", eWM=" + eWM + ", kPLX=" + kPLX + ", dDH=" + dDH
				+ ", dDSJ=" + dDSJ + ", cXCS=" + cXCS + ", xSF_NSRSBH="
				+ xSF_NSRSBH + ", xSF_MC=" + xSF_MC + ", xSF_DZDH=" + xSF_DZDH
				+ ", xSF_YHZH=" + xSF_YHZH + ", gMF_MC=" + gMF_MC
				+ ", gMF_NSRSBH=" + gMF_NSRSBH + ", gMF_DZDH=" + gMF_DZDH
				+ ", gMF_YHZH=" + gMF_YHZH + ", gMF_SJ=" + gMF_SJ + ", gMF_WX="
				+ gMF_WX + ", kPR=" + kPR + ", sKR=" + sKR + ", fHR=" + fHR
				+ ", yFP_DM=" + yFP_DM + ", yFP_HM=" + yFP_HM + ", jSHJ="
				+ jSHJ + ", hJJE=" + hJJE + ", hJSE=" + hJSE + ", bZ=" + bZ
				+ ", fPZT=" + fPZT + ", jQBH=" + jQBH + ", kPJH=" + kPJH
				+ ", sWJG_DM=" + sWJG_DM + ", kPXMSL=" + kPXMSL + ", kPXM="
				+ kPXM + ", fPZL_DM=" + fPZL_DM + ", fPLB=" + fPLB + ", hY_MC="
				+ hY_MC + ", hY_DM=" + hY_DM + ", cH_BZ=" + cH_BZ + ", kPZT="
				+ kPZT + ", dSPTBM=" + dSPTBM + ", gMF_EMAIL=" + gMF_EMAIL
				+ ", cYRZZT=" + cYRZZT + ", fILE_PATH=" + fILE_PATH
				+ ", fILE_CONTENT=" + fILE_CONTENT + ", gMFQYLX=" + gMFQYLX
				+ ", tSCHBZ=" + tSCHBZ + ", cHYY=" + cHYY + ", xHQD=" + xHQD
				+ ", sSYF=" + sSYF + ", xHQDBZ=" + xHQDBZ + ", sZQM=" + sZQM
				+ ", bMB_BBH=" + bMB_BBH + ", qD_BZ=" + qD_BZ + ", qDXMMC="
				+ qDXMMC + ", fPLX=" + fPLX + ", __insertTime=" + __insertTime
				+ ", zfbz=" + zfbz + "]";
	}

	
	
	
}
