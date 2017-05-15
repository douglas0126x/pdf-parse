package com.ele.parse.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.json.JSONObject;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * 二维码服务
 * 
 * @author yaoxj
 *
 */
public class ErWeiMaPicture {

	/**
	 * 二维码内容识别，并以字符串方式返回
	 * 
	 * @param pdDocument
	 * @return
	 */
	public static String getErWeiMaData(PDDocument pdDocument) {
		MultiFormatReader formatReader = new MultiFormatReader();
		String result ="";
		try {
//			MultiFormatReader formatReader = new MultiFormatReader();
			BufferedImage image = getErWeiMaImageMiddle(pdDocument);
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			result = getResult(formatReader, source);
//			return result; 
		} catch (Exception e) {
			BufferedImage imageLarge = getErWeiMaImageLarge(pdDocument);
			LuminanceSource sourceLarge = new BufferedImageLuminanceSource(imageLarge);
			try {
				result = getResult(formatReader, sourceLarge);
			} catch (NotFoundException e1) {
				BufferedImage imageSmall= getErWeiMaImageSmall(pdDocument);
				LuminanceSource sourceSmall = new BufferedImageLuminanceSource(imageSmall);
				try {
					result = getResult(formatReader, sourceSmall);
				} catch (NotFoundException e2) {
					BufferedImage imageMini= getErWeiMaImageMini(pdDocument);
					LuminanceSource sourceMini = new BufferedImageLuminanceSource(imageMini);
					try {
						result = getResult(formatReader, sourceMini);
					} catch (NotFoundException e3) {
						System.out.println("二维码解析异常！");
					}
					
				}
			}
		}

		return result;
	}
	
	
	/**
	 * 
	 * @param filePath 二维码保存路径
	 * @param fileName 二维码保存名称，请以".png"结尾
	 * @param width 二维码图片 宽度，如：200
	 * @param height 二维码高度 ，如：200
	 * @param content 二维码内容
	 * @throws Exception
	 */
	public static void generateQRCodePic(String filePath,String fileName,Integer width,Integer height,String content) throws Exception{
		
		String format = "png";
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		Path path = FileSystems.getDefault().getPath(filePath,	new String[] { fileName });
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE, width, height, hints);
		MatrixToImageWriter.writeToPath(bitMatrix, format, path);
		MatrixToImageWriter.toBufferedImage(bitMatrix);
		
		System.out.println("二维码成功生成！");
	}
	
	
	
	
	/**
	 * 二维码解析
	 * @param image
	 * @return
	 * @throws NotFoundException
	 */
	public static Result decode(BufferedImage image) throws NotFoundException {
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		MultiFormatReader formatReader = new MultiFormatReader();
		Binarizer binarizer = new HybridBinarizer(source);
		BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		Result result = formatReader.decode(binaryBitmap, hints);
		return result;
	}

	/**
	 * 以字符串返回二维码内容
	 * @param formatReader
	 * @param source
	 * @return
	 * @throws NotFoundException
	 */
	private static String getResult(MultiFormatReader formatReader,	LuminanceSource source) throws NotFoundException {
		Binarizer binarizer = new HybridBinarizer(source);
		BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		Result result = formatReader.decode(binaryBitmap, hints);

		return result.toString();
	}

	/**
	 * 二维码内容识别，并以字符串方式返回
	 * 
	 * @param pdfFile
	 * @return
	 */
	public static String getErWeiMaData(String pdfFile) {

		try {
			MultiFormatReader formatReader = new MultiFormatReader();
			BufferedImage image = getErWeiMaImageMiddle(pdfFile);
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			return getResult(formatReader, source);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * 生成二维码图片，并返回二维码图片绝对路径
	 * 
	 * @param pdfFile
	 * @return
	 */
	public static String getErWeiMaPicturePath(String pdfFile) {

		try {
			PDDocument doc = PDDocument.load(new File(pdfFile));
			PDFRenderer renderer = new PDFRenderer(doc);
			int pageCount = doc.getNumberOfPages();
			for (int i = 0; i < pageCount; i++) {
				// BufferedImage image = renderer.renderImageWithDPI(i,
				// 396).getSubimage(50, 20, 750, 450);
				// BufferedImage image = renderer.renderImage(i, 2.5f);
				BufferedImage image = renderer.renderImageWithDPI(i, 396)
						.getSubimage(50, 20, 1500, 1300);
				File file2 = new File("tempPdfParsePicture.png");
				ImageIO.write(image, "PNG", file2);
				System.out.println("----------" + file2.getAbsolutePath());
				return file2.getAbsolutePath();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 生成二维码图片，并返回图片绝对路径
	 * @param data 二维码数据
	 * @param filePath 生成二维码图片路径及名称（path+name）,</br>
	 * 如果为Null,则默认在项目路径temp文件夹下
	 * @return
	 */
	public static String getPicByErWeiMaData(String data,String filePath) {

		if (data == null || "".equals(data)) {
			data = "无二维码或该二维码无法识别";
		}

		try {
			int width = 100;
			int height = 100;
			String format = "png";
			Hashtable hints = new Hashtable();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix;
			bitMatrix = new MultiFormatWriter().encode(data,
					BarcodeFormat.QR_CODE, width, height, hints);
			
			if(filePath == null || "".equals(filePath)){
				filePath = "temp/tempQRCode.png";  
			}
			File file = new File(filePath);  
			if(!file.exists()){  
				file.mkdirs();  
			}
		    
		    MatrixToImageWriter.writeToFile(bitMatrix, format, file);
			return file.getAbsolutePath();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";

	}
	
	
	/**
	 * 返回二维码图片<br/>
	 * 分辨率：396
	 * @param pdfFile
	 * @return image
	 */
	public static BufferedImage getErWeiMaImageSmall(Object pdfFile) {

		try {

			PDDocument doc;

			if (pdfFile instanceof String) {
				doc = PDDocument.load(new File((String) pdfFile));
			}else{
				doc = (PDDocument) pdfFile;
			}

			PDFRenderer renderer = new PDFRenderer(doc);
			BufferedImage image = renderer.renderImageWithDPI(0, 396).getSubimage(50, 20, 1500, 1300);

			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 返回二维码图片<br/>
	 * 分辨率：300
	 * @param pdfFile
	 * @return image
	 */
	public static BufferedImage getErWeiMaImageMini(Object pdfFile) {

		try {

			PDDocument doc;

			if (pdfFile instanceof String) {
				doc = PDDocument.load(new File((String) pdfFile));
			}else{
				doc = (PDDocument) pdfFile;
			}

			PDFRenderer renderer = new PDFRenderer(doc);
			BufferedImage image = renderer.renderImageWithDPI(0, 300).getSubimage(50, 20, 1500, 1300);

			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 返回二维码图片<br/>
	 * 分辨率：466
	 * @param pdfFile
	 * @return image
	 */
	public static BufferedImage getErWeiMaImageLarge(Object pdfFile) {

		try {

			PDDocument doc;

			if (pdfFile instanceof String) {
				doc = PDDocument.load(new File((String) pdfFile));
			}else{
				doc = (PDDocument) pdfFile;
			}

			PDFRenderer renderer = new PDFRenderer(doc);
			BufferedImage image = renderer.renderImageWithDPI(0, 466).getSubimage(50, 20, 1500, 1300);

			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回二维码图片<br/>
	 * 分辨率:426
	 * @param pdfFile
	 * @return image
	 */
	public static BufferedImage getErWeiMaImageMiddle(Object pdfFile) {

		try {

			PDDocument doc;

			if (pdfFile instanceof String) {
				doc = PDDocument.load(new File((String) pdfFile));
			}else{
				doc = (PDDocument) pdfFile;
			}

			PDFRenderer renderer = new PDFRenderer(doc);
			BufferedImage image = renderer.renderImageWithDPI(0, 426).getSubimage(50, 20, 1500, 1300);

			return image;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public static void main(String[] args) {

		String file13 = "E:\\resources\\发票\\电子票3.pdf";// 1500

		System.out.println("-----" + getErWeiMaData(file13));
		System.out.println(getErWeiMaPicturePath(file13));

		// System.out.println(getPicByErWeiMaData(getErWeiMaData(file10)));

	}
}