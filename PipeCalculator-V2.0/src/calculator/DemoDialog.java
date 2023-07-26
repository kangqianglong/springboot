package calculator;

import javax.swing.*;
import java.awt.*;

public class DemoDialog extends JDialog {
    //construct method 构造方法初始化弹窗样式
	public DemoDialog (){
        this.setTitle("供热管道查表");
        this.setVisible(true);
        //this.setLocation(600,700);
        this.setSize(700, 800);
        //add one label
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
		
//        Container contentPane1 = this.getContentPane();
//        JLabel jLabel = new JLabel("再容器中添加标签");
//        contentPane1.add(jLabel);
//        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
        
        // 图片的设置     
        Container contentPane = this.getContentPane();
		ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(
				"/images/计算表.png"));
		JLabel labelImage = new JLabel(imageIcon);
		//labelImage.setPreferredSize(new Dimension(600, 300)); // 设置 JLabel 的大小
		contentPane.add(labelImage);
		labelImage.setHorizontalAlignment(SwingConstants.CENTER);

    }
}

