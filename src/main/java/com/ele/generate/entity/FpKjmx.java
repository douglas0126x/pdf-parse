package com.ele.generate.entity;

/**
 * 发票开具明细
 * @author songzhixin
 *
 */
public class FpKjmx {

  /**
   * 开票项目序号
   */
  private String kPXMXH;
  /**
   * 发票代码
   */
  private String fP_DM; 
  /**
   * 发票号码
   */
  private String fP_HM;
  /**
   * 发票行 
   */
  private String fPHXZ;
  /**
   * 项目名称
   */
  private String  xMMC;
  /**
   * 规格型号
   */
  private String gGXH;
  /**
   * 计量单位
   */
  private String dw; 
  /**
   * 计量单位代码
   */
  private String jLDW_DM;
  /**
   * 项目数量
   */
  private String xMSL;
  /**
   * 项目单价
   */
  private String xMDJ;
  /**
   * 项目金额
   */
  private String xMJE;
  /**
   * 项目编码
   */
  private String xMBM;
  /**
   * 税率
   */
  private String sL;
  /**
   * 税额
   */
  private String sE;
  /**
   * 税目代码
   */
  private String sM_DM;
  /**
   * 备注
   */
  private String bZ;
  /**
   * 折扣
   */
  private String zK;
  /**
   * 税目
   */
  private String sM;
  /**
   * 商品编码
   */
  private String sPBM;
  /**
   * 自行编码
   */
  private String zXBM;
  /**
   * 优惠政策标识
   */
  private String yHZCBS;
  /**
   * 零税率标识
   */
  private String lSLBS;
  /**
   * 增值税特殊管理
   */
  private String zZSTSGL;
  /**
   * 扣除额
   */
  private String kCE;
  
  /**
   * 含税金额
   */
  private String hsje;
  
  /**
   * 含税金额
   */
  private String hsdj;
  
	public String getkPXMXH() {
		return kPXMXH;
	}
	public void setkPXMXH(String kPXMXH) {
		this.kPXMXH = kPXMXH;
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
	public String getfPHXZ() {
		return fPHXZ;
	}
	public void setfPHXZ(String fPHXZ) {
		this.fPHXZ = fPHXZ;
	}
	public String getxMMC() {
		return xMMC;
	}
	public void setxMMC(String xMMC) {
		this.xMMC = xMMC;
	}
	public String getgGXH() {
		return gGXH;
	}
	public void setgGXH(String gGXH) {
		this.gGXH = gGXH;
	}
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	public String getjLDW_DM() {
		return jLDW_DM;
	}
	public void setjLDW_DM(String jLDWDM) {
		jLDW_DM = jLDWDM;
	}
	public String getxMSL() {
		return xMSL;
	}
	public void setxMSL(String xMSL) {
		this.xMSL = xMSL;
	}
	public String getxMDJ() {
		return xMDJ;
	}
	public void setxMDJ(String xMDJ) {
		this.xMDJ = xMDJ;
	}
	public String getxMJE() {
		return xMJE;
	}
	public void setxMJE(String xMJE) {
		this.xMJE = xMJE;
	}
	public String getxMBM() {
		return xMBM;
	}
	public void setxMBM(String xMBM) {
		this.xMBM = xMBM;
	}
	public String getsL() {
		return sL;
	}
	public void setsL(String sL) {
		this.sL = sL;
	}
	public String getsE() {
		return sE;
	}
	public void setsE(String sE) {
		this.sE = sE;
	}
	public String getsM_DM() {
		return sM_DM;
	}
	public void setsM_DM(String sMDM) {
		sM_DM = sMDM;
	}
	public String getbZ() {
		return bZ;
	}
	public void setbZ(String bZ) {
		this.bZ = bZ;
	}
	public String getzK() {
		return zK;
	}
	public void setzK(String zK) {
		this.zK = zK;
	}
	public String getsM() {
		return sM;
	}
	public void setsM(String sM) {
		this.sM = sM;
	}
	public String getsPBM() {
		return sPBM;
	}
	public void setsPBM(String sPBM) {
		this.sPBM = sPBM;
	}
	public String getzXBM() {
		return zXBM;
	}
	public void setzXBM(String zXBM) {
		this.zXBM = zXBM;
	}
	public String getyHZCBS() {
		return yHZCBS;
	}
	public void setyHZCBS(String yHZCBS) {
		this.yHZCBS = yHZCBS;
	}
	public String getlSLBS() {
		return lSLBS;
	}
	public void setlSLBS(String lSLBS) {
		this.lSLBS = lSLBS;
	}
	public String getzZSTSGL() {
		return zZSTSGL;
	}
	public void setzZSTSGL(String zZSTSGL) {
		this.zZSTSGL = zZSTSGL;
	}
	public String getkCE() {
		return kCE;
	}
	public void setkCE(String kCE) {
		this.kCE = kCE;
	}
	@Override
	public String toString() {
		return "FpKjmx [bZ=" + bZ + ", dw=" + dw + ", fPHXZ=" + fPHXZ
				+ ", fP_DM=" + fP_DM + ", fP_HM=" + fP_HM + ", gGXH=" + gGXH
				+ ", jLDW_DM=" + jLDW_DM + ", kCE=" + kCE + ", kPXMXH="
				+ kPXMXH + ", lSLBS=" + lSLBS + ", sE=" + sE + ", sL=" + sL
				+ ", sM=" + sM + ", sM_DM=" + sM_DM + ", sPBM=" + sPBM
				+ ", xMBM=" + xMBM + ", xMDJ=" + xMDJ + ", xMJE=" + xMJE
				+ ", xMMC=" + xMMC + ", xMSL=" + xMSL + ", yHZCBS=" + yHZCBS
				+ ", zK=" + zK + ", zXBM=" + zXBM + ", zZSTSGL=" + zZSTSGL
				+ "]";
	}
	public String getHsje() {
		return hsje;
	}
	public void setHsje(String hsje) {
		this.hsje = hsje;
	}
	public String getHsdj() {
		return hsdj;
	}
	public void setHsdj(String hsdj) {
		this.hsdj = hsdj;
	}

	
}
