package com.ele.generate.utils;

public enum FontSize {
	NOtwelve(12,0),NOnine(9,52),NOeight(8,60),NOseven(7,68),NOsiX(6,80),NOfive(5,96),NOfour(4,120),NOthree(3,160),NOtwo(2,250),
	GGXHnine(9,8),GGXHeight(8,8),GGXHseven(7,10),GGXHsix(6,12),GGXHfive(5,15),GGXHfour(4,20),
	DWnine(9,6),DWeight(8,6),DWseven(7,8),DWsix(6,11),DWfive(5,13),DWfour(4,14),
	QDnine(9,110),QDeight(8,120),
	QDBZnine(9,354,118),QDBZeight(8,396,132),QDBZseven(7,456,152),QDBZsix(6,460,176),
	BZnine(9,240,48),BZeight(8,324,54),BZseven(7,372,62),BZsix(6,460,72);
	private int fontSize;
	private int length;
	private int fontWidth;
	private FontSize(int fontSize,int length){
		this.fontSize = fontSize;
		this.length = length;
	}
	private FontSize(int fontSize,int length,int fontWidth){
		this.fontSize = fontSize;
		this.length = length;
		this.fontWidth = fontWidth;
	}
	public int size(){
		return fontSize;
	}
	public int length(){
		return length;
	}
	public int width(){
		return fontWidth;
	}
}
