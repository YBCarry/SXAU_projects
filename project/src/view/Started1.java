//package view;
//
//import java.awt.Dimension;
//import java.awt.Toolkit;
//
//import javax.swing.JFrame;
//
//import Controler.MyPanel111;
//
///**
// * 全屏游戏界面
// * @author dsq
// *
// */
//public class Started1 extends JFrame {
//	
//	private static final long serialVersionUID = 1L; //序列化
//	
//	public Started1() {
//		JFrame frame = new JFrame("LEGEND") ; //窗体命名
//		
//		/*利用获取屏幕对象类Dimension，获得默认工具包调用获取屏幕大小方法*/
//		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
//		
//		/*添加面板到窗体中*/
//		MyPanel111 panel = new MyPanel111(dim) ;
//		frame.add(panel) ;
//		
//		/*添加键盘监听事件*/
//		frame.addKeyListener(panel) ;
//		/*添加鼠标监听事件*/
//		panel.addMouseMotionListener(panel) ;
//		
//		/*添加线程*/
//		Thread th = new Thread(panel) ;
//		th.start() ;
//		
//		frame.setSize(dim) ;
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
//		
//		frame.setVisible(true) ;
//		frame.setResizable(false) ; 
//		
//	}
//
//}
