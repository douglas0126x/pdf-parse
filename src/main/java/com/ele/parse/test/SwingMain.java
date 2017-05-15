package com.ele.parse.test;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class SwingMain {

	//换行符,支持window和linux两种环境
	private static String hunHang=System.getProperty("line.separator"); 
	private JFrame frmpdf;
	private JTextField filePathTextField;
	private static JButton startParseButton;
	private static JLabel jqbhLabel;
	private static JLabel fpdmLabel; 
	private static JLabel fphmLabel ;
	private static JLabel kprqLabel;
	private static JLabel jymLabel;
	private static JLabel buyerNameLabel;
	private static JLabel buyerNsrsbhLabel;
	private static JLabel buyerAddressAndPhoneLabel;
	private static JLabel buyerKhhAndZhLabel;
	private static JLabel hjLabel;
	private static JLabel jshjLabel;
	private static JLabel sellerNameLabel;
	private static JLabel sellerNsrsbhLabel;
	private static JLabel sellerAddressAndPhoneLabel;
	private static JLabel sellerKhhAndZhLabel;
	private static JLabel operatorInfoLabel;
	private JLabel erWeiMaPicLabel;
	private JLabel titleLabel;
	private JLabel passwordAreaLabel1;
	private JLabel passwordAreaLabel2;
	private JLabel passwordAreaLabel3;
	private JLabel passwordAreaLabel4;
	private JLabel bzLabel;
	private JLabel goodsLabel;
	private JLabel erWeiMaDataLabel;
	private JCheckBox jxqzCheckBox;
	private JLabel jxqzCaLabel;
	private JLabel jxqzSubjectLabel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingMain window = new SwingMain();
					window.frmpdf.setVisible(true);
					window.frmpdf.setLocation(new Point(350,50));
													
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SwingMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmpdf = new JFrame();
		frmpdf.setTitle("\u89E3\u6790PDF\u53D1\u7968");
		frmpdf.setBounds(0, -38, 743, 605);
		frmpdf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmpdf.getContentPane().setLayout(null);
		
		JLabel lblpdf = new JLabel("\u9009\u62E9PDF\u53D1\u7968\u6587\u4EF6\u7684\u8DEF\u5F84");
		lblpdf.setBounds(20, 0, 149, 24);
		frmpdf.getContentPane().add(lblpdf);
		startParseButton = new JButton("\u5F00\u59CB\u5206\u6790");
		startParseButton.setBounds(621, 27, 93, 27);
		frmpdf.getContentPane().add(startParseButton);
		
		JButton button = new JButton("\u9009\u62E9\u6587\u4EF6");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser jfc=new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
				jfc.showDialog(new JLabel(), "打开");
				File file=jfc.getSelectedFile();
				if(file==null){
					return ;
				}
				if(file.isDirectory()){

					JOptionPane.showMessageDialog(null, "请输入正确的pdf文件", "文件读取错误", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(file.isFile()){
										
					
					String fileName=jfc.getSelectedFile().getName();
					if(fileName.length()>3){
						String suffixFileName=fileName.substring(fileName.length()-3);
						if("pdf".equalsIgnoreCase(suffixFileName)){
							
						}else{
							JOptionPane.showMessageDialog(null, "请输入正确的pdf文件", "文件读取错误", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
					}
					
				}
				
				filePathTextField.setText(file.getAbsolutePath());
				
			}
		});
		button.setBounds(512, 27, 93, 27);
		frmpdf.getContentPane().add(button);
		
		filePathTextField = new JTextField();
		filePathTextField.setBounds(20, 30, 482, 24);
		frmpdf.getContentPane().add(filePathTextField);
		filePathTextField.setColumns(10);
		
		JLabel label = new JLabel("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label.setBounds(20, 51, 694, 15);
		frmpdf.getContentPane().add(label);
		
		jqbhLabel = new JLabel("机器编号：");
		jqbhLabel.setBounds(33, 145, 334, 15);
		frmpdf.getContentPane().add(jqbhLabel);
		
		fpdmLabel = new JLabel("\u53D1\u7968\u4EE3\u7801\uFF1A");
		fpdmLabel.setBounds(480, 119, 187, 15);
		frmpdf.getContentPane().add(fpdmLabel);
		
		fphmLabel = new JLabel("\u53D1\u7968\u53F7\u7801\uFF1A");
		fphmLabel.setBounds(480, 132, 187, 15);
		frmpdf.getContentPane().add(fphmLabel);
		
		kprqLabel = new JLabel("\u5F00\u7968\u65E5\u671F\uFF1A");
		kprqLabel.setBounds(480, 144, 187, 15);
		frmpdf.getContentPane().add(kprqLabel);
		
		jymLabel = new JLabel("\u6821\u9A8C\u7801\uFF1A");
		jymLabel.setBounds(480, 157, 237, 15);
		frmpdf.getContentPane().add(jymLabel);
		
		JLabel label_3 = new JLabel("\u8D27\u7269\u6216\u5E94\u7A0E\u52B3\u52A1\u3001\u670D\u52A1\u540D\u79F0 \u89C4\u683C\u578B\u53F7 \u5355\u4F4D \u6570\u91CF \u5355\u4EF7 \u91D1\u989D \u7A0E\u7387 \u7A0E\u989D");
		label_3.setBounds(23, 234, 447, 15);
		frmpdf.getContentPane().add(label_3);
		
		buyerNameLabel = new JLabel("名称：");
		buyerNameLabel.setBounds(33, 167, 334, 15);
		frmpdf.getContentPane().add(buyerNameLabel);
		
		buyerNsrsbhLabel = new JLabel("纳税人识别号：");
		buyerNsrsbhLabel.setBounds(33, 183, 334, 16);
		frmpdf.getContentPane().add(buyerNsrsbhLabel);
		
		buyerAddressAndPhoneLabel = new JLabel("地址、电话：");
		buyerAddressAndPhoneLabel.setBounds(33, 200, 357, 15);
		frmpdf.getContentPane().add(buyerAddressAndPhoneLabel);
		
		buyerKhhAndZhLabel = new JLabel("开户行及账号：");
		buyerKhhAndZhLabel.setBounds(33, 214, 385, 17);
		frmpdf.getContentPane().add(buyerKhhAndZhLabel);
		
		JLabel lbln = new JLabel("<html>\u5BC6<br/>\u7801<br/>\u533A</html>");
		lbln.setBounds(480, 182, 12, 67);
		frmpdf.getContentPane().add(lbln);
		
		JLabel lblNewLabel_3 = new JLabel("<html>\u8D2D<br>\u4E70<br>\u65B9</html>");
		lblNewLabel_3.setBounds(10, 145, 12, 101);
		frmpdf.getContentPane().add(lblNewLabel_3);
		
		hjLabel = new JLabel("\u5408\u8BA1\uFF1A");
		hjLabel.setBounds(23, 392, 447, 15);
		frmpdf.getContentPane().add(hjLabel);
		
		jshjLabel = new JLabel("\u4EF7\u7A0E\u5408\u8BA1\uFF1A");
		jshjLabel.setBounds(23, 413, 414, 15);
		frmpdf.getContentPane().add(jshjLabel);
		
		JLabel lblNewLabel_7 = new JLabel("<html>\u9500<br>\u552E<br>\u65B9</html>");
		lblNewLabel_7.setBounds(10, 438, 17, 60);
		frmpdf.getContentPane().add(lblNewLabel_7);
		
		sellerNameLabel = new JLabel("\u540D\u79F0\uFF1A");
		sellerNameLabel.setBounds(33, 448, 324, 15);
		frmpdf.getContentPane().add(sellerNameLabel);
		
		sellerNsrsbhLabel = new JLabel("\u7EB3\u7A0E\u4EBA\u8BC6\u522B\u53F7\uFF1A");
		sellerNsrsbhLabel.setBounds(33, 463, 404, 15);
		frmpdf.getContentPane().add(sellerNsrsbhLabel);
		
		sellerAddressAndPhoneLabel = new JLabel("\u5730\u5740\u3001\u7535\u8BDD\uFF1A");
		sellerAddressAndPhoneLabel.setBounds(33, 483, 684, 15);
		frmpdf.getContentPane().add(sellerAddressAndPhoneLabel);
		
		sellerKhhAndZhLabel = new JLabel("\u5F00\u6237\u884C\u53CA\u8D26\u53F7\uFF1A");
		sellerKhhAndZhLabel.setBounds(33, 500, 684, 15);
		frmpdf.getContentPane().add(sellerKhhAndZhLabel);
		
		JLabel lblNewLabel_12 = new JLabel("<html>\u5907<br>\u6CE8</html>");
		lblNewLabel_12.setBounds(447, 417, 23, 41);
		frmpdf.getContentPane().add(lblNewLabel_12);
		
		operatorInfoLabel = new JLabel("\u64CD\u4F5C\u5458\u4FE1\u606F\uFF1A");
		operatorInfoLabel.setBounds(33, 525, 551, 15);
		frmpdf.getContentPane().add(operatorInfoLabel);
		
		titleLabel = new JLabel("发票标题：");
		titleLabel.setBounds(126, 115, 288, 20);
		frmpdf.getContentPane().add(titleLabel);
		
		passwordAreaLabel1 = new JLabel("");
		passwordAreaLabel1.setBounds(505, 190, 212, 15);
		frmpdf.getContentPane().add(passwordAreaLabel1);
		
		passwordAreaLabel2 = new JLabel("");
		passwordAreaLabel2.setBounds(502, 204, 212, 15);
		frmpdf.getContentPane().add(passwordAreaLabel2);
		
		passwordAreaLabel3 = new JLabel("");
		passwordAreaLabel3.setBounds(505, 217, 212, 15);
		frmpdf.getContentPane().add(passwordAreaLabel3);
		
		passwordAreaLabel4 = new JLabel("");
		passwordAreaLabel4.setBounds(505, 230, 212, 15);
		frmpdf.getContentPane().add(passwordAreaLabel4);
		
		bzLabel = new JLabel("");
		bzLabel.setVerticalAlignment(SwingConstants.TOP);
		bzLabel.setBounds(480, 429, 237, 49);
		frmpdf.getContentPane().add(bzLabel);
		
		goodsLabel = new JLabel("");
		goodsLabel.setVerticalAlignment(SwingConstants.TOP);
		goodsLabel.setBounds(23, 253, 382, 129);
		frmpdf.getContentPane().add(goodsLabel);
		
		erWeiMaPicLabel = new JLabel("二维码图片：");
		erWeiMaPicLabel.setVerticalAlignment(SwingConstants.TOP);
		erWeiMaPicLabel.setBounds(502, 251, 100, 100);
		frmpdf.getContentPane().add(erWeiMaPicLabel);
		
		erWeiMaDataLabel = new JLabel("二维码内容：");
		erWeiMaDataLabel.setVerticalAlignment(SwingConstants.TOP);
		erWeiMaDataLabel.setBounds(480, 353, 221, 75);
		frmpdf.getContentPane().add(erWeiMaDataLabel);
		
		jxqzCaLabel = new JLabel("");
		jxqzCaLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		jxqzCaLabel.setForeground(Color.RED);
		jxqzCaLabel.setBounds(150, 90, 551, 19);
		frmpdf.getContentPane().add(jxqzCaLabel);
		
		jxqzCheckBox = new JCheckBox("解析签章");
		jxqzCheckBox.setSelected(true);
		jxqzCheckBox.setBounds(45, 65, 86, 23);
		frmpdf.getContentPane().add(jxqzCheckBox);
		
		jxqzSubjectLabel = new JLabel("");
		jxqzSubjectLabel.setFont(new Font("SimSun", Font.PLAIN, 12));
		jxqzSubjectLabel.setForeground(Color.RED);
		jxqzSubjectLabel.setBounds(149, 65, 552, 23);
		frmpdf.getContentPane().add(jxqzSubjectLabel);
		
		
		startParseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pdfFilePath = filePathTextField.getText();
				com.ele.parse.entity.FPEntity fp = new com.ele.parse.entity.FPEntity();
				com.ele.parse.utils.FPUtils fpUtiles = new com.ele.parse.utils.FPUtils();
				//已经勾选解析签章（默认是解析全部）
				if(jxqzCheckBox.isSelected()){
					
					fpUtiles.setFPAttri(pdfFilePath,null,false,true,false);
				}else{
					fpUtiles.setFPAttri(pdfFilePath,null,false,false,false);
				}
				
						    	
		    	jqbhLabel.setText(fp.getJqbh());
		    	buyerNameLabel.setText(fp.getBuyer_name());
		    	buyerNsrsbhLabel.setText(fp.getBuyer_nsrsbh());
		    	buyerAddressAndPhoneLabel.setText(fp.getBuyer_addressPhoneNum());
		    	buyerKhhAndZhLabel.setText(fp.getBuyer_khhandzh());
		    	
		    	fpdmLabel.setText(fp.getFpdm());
		    	fphmLabel.setText(fp.getFphm());
		    	kprqLabel.setText(fp.getKprq());
		    	jymLabel.setText(fp.getJym());
		    	
		    	hjLabel.setText("合计：合计金额："+fp.getHjje()+"合计税额："+fp.getHjse());
		    	jshjLabel.setText("价税合计："+fp.getJshj());
		    
		    	sellerNameLabel.setText(fp.getSeller_name());
		    	sellerNsrsbhLabel.setText(fp.getSeller_nsrsbh());
		    	sellerAddressAndPhoneLabel.setText(fp.getSeller_addressPhoneNum());
		    	sellerKhhAndZhLabel.setText(fp.getSeller_khhandzh());
		    	
		    	operatorInfoLabel.setText(fp.getPersonsInfo());
		    	
		    	jxqzCaLabel.setText(fp.parseJxqz()[0]);
		    	jxqzSubjectLabel.setText(fp.parseJxqz()[1]);
		    	
		    	titleLabel.setText("发票标题："+fp.getTitle());
		    	
		    	
		    	String[] passwordAreas = fp.getPasswordArea().split(hunHang);
		    	passwordAreaLabel1.setText(passwordAreas[0]);
		    	passwordAreaLabel2.setText(passwordAreas[1]);
		    	passwordAreaLabel3.setText(passwordAreas[2]);
		    	passwordAreaLabel4.setText(passwordAreas[3]);
		    	bzLabel.setText(fp.getBz());
		    	
		    	erWeiMaPicLabel.removeAll();
		    	
		    	try {
					erWeiMaPicLabel.setIcon(new ImageIcon(ImageIO.read(new File(fp.getErWeiMaPicturePath()))));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	System.out.println("sdfaa:"+fp.getErWeiMaPicturePath());
		    	//erWeiMaPicLabel.updateUI();
		    	//erWeiMaPicLabel.repaint();
		    	
		    	StringBuilder erWeiMaData=new StringBuilder("<html>二维码内容：");
		    	String erWeiMaData_all=fp.getErWeiMaData();
		    	String erWeiMaData_part1= fp.getErWeiMaData().substring(0, erWeiMaData_all.length()/2)+"<br>";
		    	String erWeiMaData_part2= fp.getErWeiMaData().substring(erWeiMaData_all.length()/2, erWeiMaData_all.length())+"<br>";
		    	erWeiMaData.append(erWeiMaData_part1);
		    	erWeiMaData.append(erWeiMaData_part2);
		    	erWeiMaData.append("</html>");
		    	erWeiMaDataLabel.setText(erWeiMaData.toString());
		    	
		    	ArrayList<com.ele.parse.entity.Goods> goodsList = fp.getGoodsList();
		    	StringBuilder sb=new StringBuilder("<html>");
		    	for(int i=0;i<goodsList.size();i++){
		    		
//		    		if(goodsList.get(i).getName().length()<12){
//		    			sb.append(goodsList.get(i).getName()+",");
//		    			for(int k=0;k<12-goodsList.get(i).getName().length();k++){
//		    				sb.append("&nbsp;&nbsp;&nbsp;");
//		    			}
//		    		}
//		    		else
//		    			sb.append(goodsList.get(i).getName().substring(0, 12)+"...,");
		    		
		    		sb.append(goodsList.get(i).getName()+",");
		    		
		    		if(!goodsList.get(i).getType().equals("")
		    				&&!goodsList.get(i).getType().contains("无")
		    				&&!goodsList.get(i).getType().equals(hunHang))
		    			sb.append(goodsList.get(i).getType()+",");
		    		else
		    			sb.append("无,");
		    		
		    		if(!goodsList.get(i).getUnit().equals("")
		    				&&!goodsList.get(i).getUnit().contains("无")
		    				&&!goodsList.get(i).getUnit().equals(hunHang)
		    				)
		    			sb.append(goodsList.get(i).getUnit()+",");
		    		else
		    			sb.append("无,");
		    		
		    		if(!goodsList.get(i).getNumber().equals("")
		    				&&!goodsList.get(i).getNumber().contains("无")
		    				&&!goodsList.get(i).getNumber().equals(hunHang))
		    			sb.append(goodsList.get(i).getNumber()+",");
		    		else
		    			sb.append("无,");
		    		
		    		
		    		
		    			    		
		    		if(!goodsList.get(i).getPrice().equals("")
		    				&&!goodsList.get(i).getPrice().contains("无")
		    				&&!goodsList.get(i).getPrice().equals(hunHang))
		    			sb.append(goodsList.get(i).getPrice()+",");
		    		else
		    			sb.append("无,");
		    		
		    		
		    		if(!goodsList.get(i).getTotalprice().equals("")
		    				&&!goodsList.get(i).getTotalprice().contains("无")
		    				&&!goodsList.get(i).getTotalprice().equals(hunHang))
		    			sb.append(goodsList.get(i).getTotalprice()+",");
		    		else
		    			sb.append("无,");
		    		
		    		
		    		if(!goodsList.get(i).getSl().equals("")
		    				&&!goodsList.get(i).getSl().contains("无")
		    				&&!goodsList.get(i).getSl().equals(hunHang))
		    			sb.append(goodsList.get(i).getSl()+",");
		    		else
		    			sb.append("无,");
		    		
		    		
		    		if(!goodsList.get(i).getSe().equals("")
		    				&&!goodsList.get(i).getSe().contains("无")
		    				&&!goodsList.get(i).getSe().equals(hunHang))
		    			sb.append(goodsList.get(i).getSe()+"");
		    		else
		    			sb.append("无");
		    				    		
		    		
		    		sb.append("<br/>");
		    	}
		    	
		    	sb.append("</html>");
		    	goodsLabel.setText(sb.toString());
		    	
		    	
		    	
			}
		});
		
		
		
		
	}
}
