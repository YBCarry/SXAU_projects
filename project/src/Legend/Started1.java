package Legend;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * ȫ����Ϸ����
 * @author dsq
 *
 */
public class Started1 extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public Started1() {
		JFrame frame = new JFrame("LEGEND") ;
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
		
		/*�����嵽������*/
		MyPanel1 panel = new MyPanel1(dim) ;
		frame.add(panel) ;
		
		/*��Ӽ��̼����¼�*/
		frame.addKeyListener(panel) ;
		/*����������¼�*/
		panel.addMouseMotionListener(panel) ;
		
		/*����߳�*/
		Thread th = new Thread(panel) ;
		th.start() ;
		
		frame.setSize(dim) ;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		
		frame.setVisible(true) ;
		frame.setResizable(false) ; 
		
	}

}
