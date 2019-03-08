package controler;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameWin extends JPanel {
	
	/*���û�ȡ��Ļ������Dimension�����Ĭ�Ϲ��߰����û�ȡ��Ļ��С����*/
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ; 
	
	/*���������߽�*/
	private int bgX  = dim.width ;
	private int bgY  = dim.height ;
	
	/*������Ϸ����ͼƬ*/
	private Image goImg = new ImageIcon("img/GameOver.jpg").getImage() ;
	private Image bossImg = new ImageIcon("img/boss1.png").getImage() ;
	
	public void paint(Graphics g) {
		g.drawImage(goImg, 0, 0, bgX, bgY, null) ;
		g.drawImage(bossImg, 0, 0, bgX, bgY, null) ;
		
	}

}
