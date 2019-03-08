//package view;
//
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.Image;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.image.BufferedImage;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
//
///**
// * 游戏开始界面类
// * @author dsq
// *
// */
//public class MyFrame1 extends JFrame implements ActionListener {
//
//	private static final long serialVersionUID = 1L ; //序列化
//	
//	/*按钮*/
//	private JButton jButton1 = new JButton("Begin") ; //开始游戏
//	private JButton jButton2 = new JButton("Exit") ; //退出游戏
//	
//	/*标签*/
//	private JLabel jLabel = new JLabel("Carry  版权所有") ; //版权信息
//	
//	/*窗体*/
//	public MyFrame1() {
//		JFrame frame = new JFrame("BEGIN") ; //窗体命名
//		
//		/*实现监听按钮*/
//		jButton1.addActionListener(this) ;
//		jButton2.addActionListener(this) ;
//		
//		/*设置标签位置*/
//		jLabel.setBounds(10, 540, 200, 40) ;
//		/*设置标签文字属性*/
//		jLabel.setFont(new Font("黑体",0,15)) ;
//		
//		JPanel jPanel = new JPanel() ; //声明面板
//		
//		/*面板增添按钮*/
//		jPanel.add(jButton1) ;
//		jPanel.add(jButton2) ;
//			
//		frame.add(jLabel) ;//标签放到窗体上
//		
//		frame.add(jPanel) ; //面板放到窗体上
//		
//		/*利用获取屏幕对象类Dimension，获得默认工具包调用获取屏幕大小方法*/
//		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ; //封装高度宽度
//		
//		frame.setBounds((dim.width - 1000)/2, (dim.height - 615)/2, 1000, 615) ; //窗体大小（在中间位置显示）
//		
//		// 背景图片  
//        ImageIcon background = new ImageIcon("img/index.jpg") ;  
//        // 把背景图片显示在一个标签里面  
//        JLabel label = new JLabel(background) ;  
//        // 把标签的大小位置设置为图片刚好填充整个面板  
//        label.setBounds(0, 0, this.getWidth(), this.getHeight()) ;  
//        // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明  
//        JPanel imagePanel = (JPanel) this.getContentPane() ;  
//        imagePanel.setOpaque(false) ;  
//        // 把背景图片添加到分层窗格的最底层作为背景  
//        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)) ;
//		jPanel.add(label) ;
//		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;  //窗体关闭方式
//		frame.setVisible(true) ; //窗体可见
//		frame.setResizable(false) ; //窗体不可改变
// 	}
//
//	
//	/*按键监听*/
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		
//		/*判断*/
//		if(e.getSource() == jButton1)
//		{
//		    new Started1 () ; //进入游戏（全屏）
//			
//		}
//			
//		if(e.getSource() == jButton2) 
//		{
//			System.exit(0) ; //退出
//		}
//	}
//
//}
