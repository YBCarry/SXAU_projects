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
// * ȫ����Ϸ����
// * @author dsq
// *
// */
//public class Started1 extends JFrame {
//	
//	private static final long serialVersionUID = 1L; //���л�
//	
//	public Started1() {
//		JFrame frame = new JFrame("LEGEND") ; //��������
//		
//		/*���û�ȡ��Ļ������Dimension�����Ĭ�Ϲ��߰����û�ȡ��Ļ��С����*/
//		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
//		
//		/*�����嵽������*/
//		MyPanel111 panel = new MyPanel111(dim) ;
//		frame.add(panel) ;
//		
//		/*��Ӽ��̼����¼�*/
//		frame.addKeyListener(panel) ;
//		/*����������¼�*/
//		panel.addMouseMotionListener(panel) ;
//		
//		/*����߳�*/
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
