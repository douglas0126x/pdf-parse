package com.ele.generate.entity;

import java.util.List;

/**
 * 发票数据
 * @author songzhixin
 *
 */
public class FPData {

	/**
	 * 发票开具实体内容
	 */
	private FpKj fP_KJ;

	/**
	 * 发票开具明细
	 */
	private List<FpKjmx> fP_KJMX;

	public FpKj getfP_KJ() {
		return fP_KJ;
	}

	public void setfP_KJ(FpKj fPKJ) {
		fP_KJ = fPKJ;
	}

	public List<FpKjmx> getfP_KJMX() {
		return fP_KJMX;
	}

	public void setfP_KJMX(List<FpKjmx> fPKJMX) {
		fP_KJMX = fPKJMX;
	}
	@Override
	public String toString() {
		return "FPData [fP_KJ=" + fP_KJ + ", fP_KJMX=" + fP_KJMX + "]";
	}

}
