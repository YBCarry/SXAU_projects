package Legend;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 全屏游戏界面
 * @author dsq
 *
 */
public class Started1 extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public Started1() {
		JFrame frame = new JFrame("LEGEND") ;
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
		
		/*添加面板到窗体中*/
		MyPanel1 panel = new MyPanel1(dim) ;
		frame.add(panel) ;
		
		/*添加键盘监听事件*/
		frame.addKeyListener(panel) ;
		/*添加鼠标监听事件*/
		panel.addMouseMotionListener(panel) ;
		
		/*添加线程*/
		Thread th = new Thread(panel) ;
		th.start() ;
		
		frame.setSize(dim) ;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		
		frame.setVisible(true) ;
		frame.setResizable(false) ; 
		
	}

}
