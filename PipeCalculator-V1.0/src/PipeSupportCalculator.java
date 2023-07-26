import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PipeSupportCalculator extends JFrame implements ActionListener {

	private JTextField textField1, textField2, textField3, textField4,
			textField5, textField6, textField7, textField8, textField9,
			textField10, textField11, textField12, textField13, textField14,
			textField15, textField16, textField17, textField18, textField19,
			textField20,textField21,textField22,textField23,textField24;
	private JButton calculateButton, clearButton;

	public PipeSupportCalculator() {

		setTitle("直埋供热管道固定墩计算程序V1.0-屈夏霞");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 800);
		setLayout(new FlowLayout(1, 10, 20));
		setLocationRelativeTo(null);
		setResizable(false);
		
		Color color = new Color(143, 188, 143); // RGB 颜色值 输入字符背景设置为淡绿
		Color color2 = new Color(220, 220, 220); // 控制板背景颜色设置为淡灰色

		// 首先定义输入框的4个垂直BOX，还有一个主要的BOX
		Box box, box1, box2, box3, box4, box5;
		Box text1, text2, text3, text4, text5, text6, text7, text8, text9, text10;
		
		box = Box.createVerticalBox();// 主要的box,存放所有控件
		box1 = Box.createHorizontalBox();// 存放输入几何尺寸，水平
		box2 = Box.createHorizontalBox();// 存放计算参数
		box3 = Box.createHorizontalBox();// 存放图片
		box4 = Box.createHorizontalBox();// 存放计算按钮
		box5 = Box.createHorizontalBox();// 存放计算结果，水平
		box1.setAlignmentY(Component.CENTER_ALIGNMENT);
		box2.setAlignmentY(Component.CENTER_ALIGNMENT);
		box1.setBorder(BorderFactory.createTitledBorder("输入固定墩几何参数")); // 容器的标题
		box2.setBorder(BorderFactory.createTitledBorder("输入计算参数"));
		box3.setBorder(BorderFactory.createTitledBorder("计算图像"));
		box4.setBorder(BorderFactory.createTitledBorder("计算操作"));
		box5.setBorder(BorderFactory.createTitledBorder("结果输出"));

		// 存放输入几何尺寸
		text1 = Box.createVerticalBox();// 垂直
		text2 = Box.createVerticalBox();// 垂直
		text3 = Box.createVerticalBox();
		text4 = Box.createVerticalBox();
		text5 = Box.createVerticalBox();
		text6 = Box.createVerticalBox();
		text7 = Box.createVerticalBox();
		text8 = Box.createVerticalBox();
		text9 = Box.createVerticalBox();
		text10 = Box.createVerticalBox();

		text1.add(new JLabel("固定墩长度A(m):"));
		text1.add(new JLabel("固定墩厚度T(m):"));
		text1.add(new JLabel("固定墩高度H(m):"));
		text1.add(new JLabel("管道中心距C(m):"));
		textField1 = new JTextField();
		textField1.setBackground(color);
		textField2 = new JTextField();
		textField2.setBackground(color);
		textField3 = new JTextField();
		textField3.setBackground(color);
		textField4 = new JTextField();
		textField4.setBackground(color);
		text2.add(textField1);
		text2.add(textField2);
		text2.add(textField3);
		text2.add(textField4);
		text3.add(new JLabel("管顶覆土厚度h1(m):"));
		text3.add(new JLabel("保温管外径Φ(m):"));
		text3.add(new JLabel("钢管直径d(mm):"));			
		text3.add(new JLabel("环板宽度b(mm):"));
		textField5 = new JTextField();
		textField5.setBackground(color);
		textField6 = new JTextField();
		textField6.setBackground(color);
		textField7 = new JTextField();
		textField7.setBackground(color);
		textField8 = new JTextField();
		textField8.setBackground(color);
		textField8.setToolTipText("注：环板宽度一般取250mm");
		text4.add(textField5);
		text4.add(textField6);
		text4.add(textField7);
		text4.add(textField8);	
				
		box1.add(text1);
		box1.add(text2);
		box1.add(Box.createHorizontalStrut(180));// 设置中间距离
		box1.add(text3);
		box1.add(text4);
/*		box1.add(text5);
		box1.add(text6);*/

		// 存放计算参数
		text7.add(new JLabel("双管推力F(kN):"));
		text7.add(new JLabel("回填土内摩擦角φ º:"));
		text7.add(new JLabel("回填土容重γs(kN/m³):"));
		text7.add(new JLabel("被动土压力折减系数k:"));//label1a.setToolTipText("注：无位移取0.8-0.9，小位移取0.4-0.7");		
		text7.add(new JLabel("回填土与固定墩的摩擦系数μ:"));
		textField9= new JTextField();
		textField9.setBackground(color);
		textField13 = new JTextField();
		textField13.setBackground(color);
		textField13.setToolTipText("注：砂土类内摩擦角为25º，粘土类内摩擦角为30º");// 设置 JTextField 的提示信息
		textField14 = new JTextField();
		textField14.setBackground(color);
		textField15 = new JTextField();
		textField15.setBackground(color);
		textField15.setToolTipText("注：无位移取0.8-0.9，小位移取0.4-0.7");
		textField16 = new JTextField();
		textField16.setBackground(color);
		textField16.setToolTipText("注：按照《城镇供热直埋热水管道技术规程》第6.2.2条取用");
		text8.add(textField9);
		text8.add(textField13);
		text8.add(textField14);
		text8.add(textField15);
		text8.add(textField16);
		text9.add(new JLabel("混凝土容重γc(kN/m³):"));
		text9.add(new JLabel("钢筋保护层厚度a(mm):"));
		text9.add(new JLabel("混凝土轴心抗拉强度设计值ft(N/mm2):"));
		text9.add(new JLabel("截面高度影响系数βh:"));
		text9.add(new JLabel("输入地基承载力特征值 PK(kPa):"));
		textField17 = new JTextField();
		textField17.setBackground(color);
		textField18 = new JTextField();
		textField18.setBackground(color);
		textField19 = new JTextField();
		textField19.setBackground(color);
		textField19.setToolTipText("注：C30混凝土，轴心抗拉强度为1.43；C35为1.57；C40为1.71");
		textField20 = new JTextField();
		textField20.setBackground(color);
		textField20.setToolTipText("注：固定墩厚度≤800mm，取1.0；固定墩厚度≥2000mm，取0.9；其余线性内插");
		textField12 = new JTextField();
		textField12.setBackground(color);
		text10.add(textField17);
		text10.add(textField18);
		text10.add(textField19);
		text10.add(textField20);
		text10.add(textField12);

		box2.add(text7);
		box2.add(text8);
		box2.add(Box.createHorizontalStrut(10));// 设置中间距离
		box2.add(text9);
		box2.add(text10);
		// 图片的设置
		ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(
				"/images/计算模型图.png"));

		
//        Image image = imageIcon.getImage();
//        // Calculate aspect ratio
//        double imageAspectRatio = (double) image.getWidth(null) / image.getHeight(null);
//        
//        double boxAspectRatio = (double) box3.getWidth() / box3.getHeight();
//        double scale = 1;
//        if (imageAspectRatio > boxAspectRatio) {
//            scale = (double) box3.getWidth() / image.getWidth(null);
//        } else {
//            scale = (double) box3.getHeight() / image.getHeight(null);
//        }
//        
//        // Scale image
//        int scaledWidth = (int) (image.getWidth(null) * scale);
//        int scaledHeight = (int) (image.getHeight(null) * scale);
//        Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
//
//        // Create scaled image icon
//        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
//
//        // Create label and add to box
//        JLabel labelImage = new JLabel(scaledImageIcon);
//		
	
		JLabel labelImage = new JLabel(imageIcon);
		labelImage.setPreferredSize(new Dimension(600, 300)); // 设置 JLabel 的大小
		box3.add(labelImage);
		// Box.createVerticalStrut(80)；
		
		
		// 按钮的设置
		calculateButton = new JButton("计算");
		// calculateButton.setSize(50, 5);
		clearButton = new JButton("清空");

		box4.add(calculateButton);
		box4.add(Box.createHorizontalStrut(50));// 设置按钮中间距离
		box4.add(clearButton);
		calculateButton.addActionListener(this);
		clearButton.addActionListener(this);

		// 输出参数的设置
		Box boxa;
		Box texta;

		boxa = Box.createVerticalBox();// 垂直
		texta = Box.createVerticalBox();// 垂直

		boxa.add(new JLabel("抗滑移验算结果:"));
		boxa.add(new JLabel("抗倾覆验算结果:"));
		boxa.add(new JLabel("固定墩抗冲切验算结果:"));
		boxa.add(new JLabel("地基承载力验算结果:"));
		textField21 = new JTextField();
		texta.add(textField21);
		textField22 = new JTextField();
		texta.add(textField22);
		textField23 = new JTextField();
		texta.add(textField23);
		textField24 = new JTextField();
		texta.add(textField24);
		box5.add(boxa);
		box5.add(texta);

		box.add(box1);
		box.add(box2);
		box.add(box3);
		box.add(box4);
		box.add(box5);

		add(box);
		setVisible(true);
	}

	// 实现 ActionListener 接口中的方法
	public void actionPerformed(ActionEvent e) {
		Color color = new Color(143, 188, 143);
		double A = 0, T = 0,H = 0,C = 0,h1 = 0,Φ1 = 0,d = 0,b = 0;
		double F = 0, φ = 0,γs = 0,k = 0,μ = 0,γc = 0,a = 0,ft = 0,βh = 0,Pk = 0;
		
		if (e.getSource() == calculateButton) {
			// 创建一个Map对象
			Map<String, Object> myMap = new HashMap<>();
			// 将变量作为值存储在Map中
			String myVariable = "example";
			myMap.put("key", myVariable);
			
			
/*			String A = textField1.getText(); // A：固定墩长度A (m)，单位：m²
			String T = textField2.getText(); // 固定墩厚度T (m)；，单位：kN
			String H = textField3.getText(); // H：固定墩高度H (m)，单位：m
			String C = textField4.getText(); //管道中心距C (m)；
			String h1 =textField5.getText(); // h：管顶覆土厚度h (m)，单位：m
			String Φ1 = textField6.getText(); // 保温管外径Φ (m)；
			String d = textField7.getText(); // 回填土与固定墩的摩擦系数μ；，单位：无量纲
			String b=textField8.getText() ;// 环板宽度b(mm) (注：环板宽度一般取250mm)
    
			String F = textField9.getText(); // 双管推力F (kN)；
			String φ = textField13.getText(); // 回填土内摩擦角φ º；，单位：度
			String γs = textField14.getText();// γs：回填土容重γs (kN/m³)；，单位：kN/m³
			String k = textField15.getText(); // 被动土压力折减系数k (注：无位移取0.8-0.9，小位移取0.4-0.7)；
			String μ = textField16.getText(); 
			String γc = textField17.getText(); //混凝土容重γc (kN/m³)；
			String a = textField18.getText(); 
			String ft = textField19.getText(); 
			String βh = textField20.getText(); 
			String Pk = textField12.getText(); 
*/

/*			myMap.put("A", A);
			myMap.put("T", T);
			myMap.put("H", H);
			myMap.put("C", myVariable);
			myMap.put("h1", myVariable);
			myMap.put("Φ1", myVariable);
			myMap.put("d", myVariable);
			myMap.put("b", myVariable);
			myMap.put("F", myVariable);
			myMap.put("φ", myVariable);
			myMap.put("γs", myVariable);
			myMap.put("k", myVariable);
			myMap.put("μ", myVariable);
			myMap.put("γc", myVariable);
			myMap.put("a", myVariable);
			myMap.put("ft", myVariable);
			myMap.put("βh", myVariable);
			myMap.put("Pk", myVariable);

	        boolean valid = true;
	        // 1. 检查是否为空
	        for (Object value : myMap.values()) {
	            if (value == null) {
	                valid = false;
	                break;
	            }
	        }
	        if (!valid) {
	            System.out.println("校验失败：参数不能为空！");
				JOptionPane.showMessageDialog(this, "参数不能为空");
	            return;
	        }
	     // 将数据类型参数转换成数字类型
	        for (Map.Entry<String, Object> entry : myMap.entrySet()) {
	            String key = entry.getKey();
	            Object value = entry.getValue();

	            try {
	                if (value instanceof Number) {
	                    // 如果参数本来就是数字类型，就不需要转换
	                    System.out.println(key + ": " + value);
	                } else {
	                    // 尝试将参数转换成数字类型
	                    Number num = getNumber(value);
	                    System.out.println(key + ": " + num);
	                }
	            } catch (NumberFormatException ex) {
	                // 处理转换异常
	                System.err.println("Invalid value for " + key + ": " + value);
	                JOptionPane.showMessageDialog(this, "参数必须为数字类型");
	            }
	        }		        
	        // 2. 检查是否为数字类型
	        for (Object value : myMap.values()) {
	            if (!(value instanceof Number)) {
	                valid = false;
	                break;
	            }
	        }
	        if (!valid) {
	            System.out.println("校验失败：参数必须为数字类型！");
				JOptionPane.showMessageDialog(this, "参数必须为数字类型！");
	            return;
	        }				
			// 从Map中获取变量
			String retrievedVariable = (String) myMap.get("key");
			System.out.println(retrievedVariable); // 输出 "example"
*/
	
			double[] arr = new double[18]; // 创建长度为3的double数组
			arr[0] = A; 
			arr[1] = T; 
			arr[2] = H; 
			arr[3] = C;
			arr[4] = h1; 
			arr[5] = Φ1;
			arr[6] = d; 
			arr[7] = b; 
			
			arr[8] = F; 
			arr[9] = φ;			
			arr[10] = γs; 
			arr[11] = k;
			arr[12] = μ; 
			arr[13] = γc; 
			arr[14] = a;
			arr[15] = ft; 
			arr[16] = βh; 
			arr[17] = Pk;

			
	    try {
	    	 A = Double.parseDouble(textField1.getText()); // A：固定墩长度A (m)，单位：m²
	    	 T = Double.parseDouble(textField2.getText()); // 固定墩厚度T (m)；，单位：kN
	    	 H = Double.parseDouble(textField3.getText()); // H：固定墩高度H (m)，单位：m
		     C = Double.parseDouble(textField4.getText()); //管道中心距C (m)；
		     h1 = Double.parseDouble(textField5.getText()); // h：管顶覆土厚度h (m)，单位：m
		     Φ1 = Double.parseDouble(textField6.getText()); // 保温管外径Φ (m)；
		     d = Double.parseDouble(textField7.getText()); // 回填土与固定墩的摩擦系数μ；，单位：无量纲
		     b= Double.parseDouble(textField8.getText()); // 环板宽度b(mm) (注：环板宽度一般取250mm)
		
		     F = Double.parseDouble(textField9.getText()); // 双管推力F (kN)；
		     φ = Double.parseDouble(textField13.getText()); // 回填土内摩擦角φ º；，单位：度
		     γs = Double.parseDouble(textField14.getText()); // γs：回填土容重γs (kN/m³)；，单位：kN/m³
		     k = Double.parseDouble(textField15.getText()); // 被动土压力折减系数k (注：无位移取0.8-0.9，小位移取0.4-0.7)；
		     μ = Double.parseDouble(textField16.getText()); 
		     γc = Double.parseDouble(textField17.getText()); //混凝土容重γc (kN/m³)；
		     a = Double.parseDouble(textField18.getText()); 
		     ft = Double.parseDouble(textField19.getText()); 
		     βh = Double.parseDouble(textField20.getText()); 
		     Pk = Double.parseDouble(textField12.getText()); 
		         
/*			arr[0] = A; 
			arr[1] = T; 
			arr[2] = H; 
			arr[3] = C;
			arr[4] = h1; 
			arr[5] = Φ1;
			arr[6] = d; 
			arr[7] = b; 
			
			arr[8] = F; 
			arr[9] = φ;			
			arr[10] = γs; 
			arr[11] = k;
			arr[12] = μ; 
			arr[13] = γc; 
			arr[14] = a;
			arr[15] = ft; 
			arr[16] = βh; 
			arr[17] = Pk;*/
		     
           
        } catch (NumberFormatException ex) {
            // 处理转换异常
        	System.out.println("Caught exception: " + ex.getMessage());
        	 System.out.println("Exception stack trace:");
            for (int i=0; i<arr.length; i++) {
                if (Double.isNaN(arr[i])) {
                    System.out.println("第 " + (i+1) + " 个变量为空值。");
                } else {
                    System.out.println("第 " + (i+1) + " 个变量为 " + arr[i] + "。");
                }
           // JOptionPane.showMessageDialog(this, "参数"+arr[i+2]+"输入异常;"+"请输入全部参数，并且必须为数字类型,保留2位小数");
            JOptionPane.showMessageDialog(this, "请输入全部参数，并且必须为数字类型,保留2位小数");

            return;
            }
        }			
				
		 // 使用DecimalFormat将数字四舍五入为2位小数
		 DecimalFormat df = new DecimalFormat("#.00");
		 Font font = new Font("宋体", Font.BOLD, 14);
		 
		    //若：A>(C+Φ+1),则不输出提示语；
		    // 若不满足以上要求，则弹窗输出“固定墩宽度不满足构造要求”
		    if (A<=(C+Φ1+1)) {
				JOptionPane.showMessageDialog(this, "固定墩宽度不满足构造要求");
		    }
			else {
		    
		    //固定墩顶部覆土厚度h
		    double h=h1+Φ1/2-H/2;
	        // 计算土压力
	        double Ep = 0.5 * γs * A * H * (2*h + H) * Math.tan(Math.toRadians(45.0 + φ/2))* Math.tan(Math.toRadians(45.0 + φ/2));
	        double Ea = 0.5 * γs * A * H * (2*h + H) * Math.tan(Math.toRadians(45.0 - φ/2))* Math.tan(Math.toRadians(45.0 - φ/2));
	        // 计算摩擦力
	        double f1 = μ * (γc * A * H * T + γs * A * h * T);
	        double f2 = μ * γs * A * h * T;
	        double f3 = 2 * μ * Ea * T / A;
	        // 计算抗滑移系数
	        double result = (k*Ep + f1 + f2 + f3) / (F + Ea);
	     // 将double类型的数字格式化为字符串
	     // 将格式化后的字符串转换为double类型
	        String numStr = df.format(result);
	        double Rp = Double.parseDouble(numStr);
	        // 输出计算结果
	        if (Rp < 1.3) {
	            System.out.println("抗滑移系数是"+Rp+",若计算结果<1.3,抗滑移不满足规范要求，重新计算！");
				textField21.setText("抗滑移系数"+Rp+"<1.3,抗滑移不满足规范要求，重新计算!");
				textField21.setBackground(color);
				 textField24.setForeground(Color.RED);
	        } else {
	            System.out.println("抗滑移系数是"+Rp+",若计算结果>=1.3,抗滑移满足规范要求！");
				textField21.setText("抗滑移系数"+Rp+"≥1.3,抗滑移满足规范要求!");
				textField21.setBackground(color);
	        }
		
	        // 计算倾覆弯矩
	        double MS1 = F * H / 2;  // 管道推力产生的倾覆弯矩
	        double tg2 = Math.tan(Math.toRadians(45 - φ / 2));  // 计算tg(45 - φ/2)
	        double MS2 = 0.5 * γs * A * Math.pow(H, 2) * (h + H / 3) * tg2* tg2;  // 主动土压力产生的倾覆弯矩
	        // 计算抗倾覆弯矩
	        double T2 = Math.pow(T, 2);
	        double MR1 = γs * h * A * T2 / 2;  // 固定墩墩顶土重产生的抗倾覆弯矩
	        double MR2 = γc * H * A * T2 / 2;  // 固定墩自重产生的抗倾覆弯矩
	        double MR3 = μ * γs * T * Math.pow(H, 2) * (h + H / 3) * tg2* tg2;  // 固定墩侧壁摩擦力产生的抗倾覆弯矩
	        double tg3 = Math.tan(Math.toRadians(45 + φ / 2));  // 计算tg(45 + φ/2)
	        double MR4 = 0.5 * γs * A * Math.pow(H, 2) * (h + H / 3) * tg3* tg3;  // 被动土压力产生的抗倾覆弯矩
	        // 计算抗倾覆系数
	        double result2 = (MR1 + MR2 + MR3 + MR4*k) / (MS1 + MS2);
	        // 输出计算结果
	        String numStr2 = df.format(result2);
	        double SF = Double.parseDouble(numStr2);
	        if (SF < 1.5) {
	            System.out.println("抗倾覆系数是"+SF+",若计算结果<1.5,抗倾覆不满足规范要求，重新计算！");
	            textField22.setText("抗倾覆系数"+SF+"<1.5,抗倾覆不满足规范要求,重新计算!");
	            textField22.setBackground(color);
	            textField24.setForeground(Color.RED);
	        } else {
	            System.out.println("抗倾覆系数是"+SF+",若计算结果>=1.5,抗倾覆满足规范要求！！");
	            textField22.setText("抗倾覆系数"+SF+"≥1.5,抗倾覆满足规范要求!");
	            textField22.setBackground(color);
	        }
	       		        
	        //第三步：固定墩抗冲切验算
	        double mu = 3.14 * (d + 2*b + 1000 * T - 2 * a); // 冲切破坏面周长
	        double eta1 = 1.00;
	        double eta2 = 0.5 + (10000 * T - 20 * a) / mu; // 抗冲剪影响系数2
	        double eta = Math.min(eta1, eta2); // 抗冲剪影响系数
	        double result3 = 0.7 * βh * ft * eta * mu * (500 * T - 1.5 * a)/1000; // 墩体抗冲切力
	        String numStr3 = df.format(result3);
	        double Fe = Double.parseDouble(numStr3);
	        System.out.println("墩体抗冲切力是"+Fe+",若墩体抗冲切力<输入的双管推力F,墩体抗冲切不满足规范要求，需要重新计算！");
	        if (Fe < F) {
	            System.out.println("墩体抗冲切力是"+Fe+",若墩体抗冲切力<输入的双管推力F,墩体抗冲切不满足规范要求，需要重新计算！");
	            textField23.setText("墩体抗冲切力是"+Fe+"<双管推力F,墩体抗冲切不满足规范要求，重新计算!");
	            textField23.setBackground(color);
	            textField24.setForeground(Color.RED);
	        } else {
	            System.out.println("墩体抗冲切力是"+Fe+",若墩体抗冲切力>=输入的双管推力F,墩体抗冲切满足规范要求！");
	            textField23.setText("墩体抗冲切力是"+Fe+"≥双管推力F,墩体抗冲切满足规范要求!");
	            textField23.setBackground(color);
	        }
	        
	        //第四步：地基承载力验算		        
	        // 计算最大压应力
	        double result4 = γs * h + γc * H + (MR3 + MR4 - MS1 - MS2)/ (A * T * T)/6;
	        // 计算最小压应力
	        double result5 = γs * h + γc * H - (MR3 + MR4 - MS1 - MS2)/ (A * T * T)/6;
	        System.out.println("γs是"+γs+"--h是"+h+"--γc是"+γc+"--H是"+H+"--MR3是"+MR3+"--MR4是"+MR4+"--MS1是"+MS1+"--MS2是"+MS2+"--A是"+A+"--T是"+T);
	        String numStr4 = df.format(result4);
	        double Px = Double.parseDouble(numStr4);
	        String numStr5 = df.format(result5);
	        double Pmin = Double.parseDouble(numStr5);
	        // 比较最大压应力和规范要求的极限荷载
	        if (Px <= 1.2 * Pk && Pmin >= 0) {
	            System.out.println("PKmax="+Px+"≤1.2*PK,Pkmin="+Pmin+">=0，地基承载力满足规范要求");
	            textField24.setText("Pkmax="+Px+"≤1.2*Pk,Pkmin="+Pmin+">=0，地基承载力满足规范要求");
	            textField24.setBackground(color);
	        } else {
	        	if(Px > 1.2 * Pk && Pmin >= 0){
		            System.out.println("PKmax="+Px+">1.2*PK,Pkmin="+Pmin+">=0,地基承载力不满足规范要求");
		            textField24.setText("Pkmax="+Px+">1.2*Pk,Pkmin="+Pmin+">=0,地基承载力不满足规范要求");
		            //textField24.setFont(font);
		    		//设置前景色（也就是字体颜色）
		            textField24.setForeground(Color.RED);
		            textField24.setBackground(color);
	        	}else{
	        		if(Px > 1.2 * Pk && Pmin < 0){
			            System.out.println("PKmax="+Px+">1.2*PK,Pkmin="+Pmin+"<0,地基承载力不满足规范要求");
	    	            textField24.setText("Pkmax="+Px+">1.2*Pk,Pkmin="+Pmin+"<0,地基承载力不满足规范要求");
	    	            //textField24.setFont(font);
	    	    		//设置前景色（也就是字体颜色）
	    	            textField24.setForeground(Color.RED);
	    	            textField24.setBackground(color);
	        		}else{
	        			if(Px <= 1.2 * Pk && Pmin< 0){
	        	            System.out.println("PKmax="+Px+">1.2*PK,Pkmin="+Pmin+"<0,地基承载力不满足规范要求");
	        	            textField24.setText("Pkmax="+Px+">1.2*Pk,Pkmin="+Pmin+"<0,地基承载力不满足规范要求");
	        	            //textField24.setFont(font);
	        	    		//设置前景色（也就是字体颜色）
	        	            textField24.setForeground(Color.RED);
	        	            textField24.setBackground(color);
	        			}else{
	        				  textField24.setText("计算错误！！");
		        	            //textField24.setFont(font);
		        	    		//设置前景色（也就是字体颜色）
		        	            textField24.setForeground(Color.RED);
		        	            textField24.setBackground(color);
	        			}	
	        		}	        		
	        	}

	        }
		} 
		} else if (e.getSource() == clearButton) {
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
			textField5.setText("");

			textField6.setText("");
			textField7.setText("");
			textField8.setText("");
			textField9.setText("");
//			textField10.setText("");

//			textField11.setText("");
			textField12.setText("");
			textField13.setText("");
			textField14.setText("");
			textField15.setText("");

			textField16.setText("");
			textField17.setText("");
			textField18.setText("");
			textField19.setText("");
			textField20.setText("");
			
			textField21.setText("");
			textField22.setText("");
			textField23.setText("");
			textField24.setText("");
		}
	}
	
	// 将参数转换为数字类型，如果无法转换则抛出NumberFormatException异常
    public static Number getNumber(Object value) throws NumberFormatException {
        if (value instanceof String) {
            return Double.parseDouble((String) value);
        } else if (value instanceof Character) {
            return (int) ((Character) value);
        } else {
            throw new NumberFormatException();
        }
    }
	
	public static void main(String[] args) {
		new PipeSupportCalculator();
	}
}