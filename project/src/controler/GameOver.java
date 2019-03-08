package controler;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameOver extends JPanel {
	
	/*���û�ȡ��Ļ������Dimension�����Ĭ�Ϲ��߰����û�ȡ��Ļ��С����*/
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ; 
	
	/*���������߽�*/
	private int bgX  = dim.width ;
	private int bgY  = dim.height ;
	
	/*������Ϸ����ͼƬ*/
	private Image goImg = new ImageIcon("img/GameOver.jpg").getImage() ;
	
	public void paint(Graphics g) {
		g.drawImage(goImg, 0, 0, bgX, bgY, null) ;
		
	}

}
