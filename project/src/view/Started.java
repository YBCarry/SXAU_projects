package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import controler.DB_Operater;
import controler.MyPanel1;


/**
 * ȫ����Ϸ����
 * @author dsq
 *
 */
public class Started extends JFrame {
	
	private static final long serialVersionUID = -375194464537045807L ; //���л�
	
	int level, grade ;
	String name ;
	DB_Operater db_Operater ;
	int lastTime ;
	
	public Started(int level, int grade, String name) {
		
		this.level = level ;
		this.grade = grade ;
		this.name = name ;
		
		db_Operater = new DB_Operater() ;
		lastTime = (int)System.currentTimeMillis() ;
		
		JFrame frame = new JFrame("LEGEND") ; //��������
		
		/*���û�ȡ��Ļ������Dimension�����Ĭ�Ϲ��߰����û�ȡ��Ļ��С����*/
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
		/*���ô���Ϊȫ��*/
		frame.setBounds(0, 0, dim.width, dim.height) ;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		/*�����嵽������*/
		MyPanel1 panel = new MyPanel1(dim, grade, name) ;
		frame.add(panel) ;
		
		/*��Ӽ��̼����¼�*/
		frame.addKeyListener(panel) ;
		/*����������¼�*/
		panel.addMouseMotionListener(panel) ;
		
		/*����߳�*/
		Thread th = new Thread(panel) ;
		th.start() ;  //�����߳�
		
		frame.setVisible(true) ;
		frame.setResizable(false) ; 
		
	}

}
