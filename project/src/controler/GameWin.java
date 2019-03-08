package controler;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameWin extends JPanel {
	
	/*利用获取屏幕对象类Dimension，获得默认工具包调用获取屏幕大小方法*/
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ; 
	
	/*声明背景边界*/
	private int bgX  = dim.width ;
	private int bgY  = dim.height ;
	
	/*插入游戏结束图片*/
	private Image goImg = new ImageIcon("img/GameOver.jpg").getImage() ;
	private Image bossImg = new ImageIcon("img/boss1.png").getImage() ;
	
	public void paint(Graphics g) {
		g.drawImage(goImg, 0, 0, bgX, bgY, null) ;
		g.drawImage(bossImg, 0, 0, bgX, bgY, null) ;
		
	}

}
