package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import controler.DB_Operater;
import controler.MyPanel1;


/**
 * 全屏游戏界面
 * @author dsq
 *
 */
public class Started extends JFrame {
	
	private static final long serialVersionUID = -375194464537045807L ; //序列化
	
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
		
		JFrame frame = new JFrame("LEGEND") ; //窗体命名
		
		/*利用获取屏幕对象类Dimension，获得默认工具包调用获取屏幕大小方法*/
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
		/*设置窗体为全屏*/
		frame.setBounds(0, 0, dim.width, dim.height) ;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		/*添加面板到窗体中*/
		MyPanel1 panel = new MyPanel1(dim, grade, name) ;
		frame.add(panel) ;
		
		/*添加键盘监听事件*/
		frame.addKeyListener(panel) ;
		/*添加鼠标监听事件*/
		panel.addMouseMotionListener(panel) ;
		
		/*添加线程*/
		Thread th = new Thread(panel) ;
		th.start() ;  //启动线程
		
		frame.setVisible(true) ;
		frame.setResizable(false) ; 
		
	}

}
