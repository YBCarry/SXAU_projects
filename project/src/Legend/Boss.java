package Legend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Boss {
	
	private int x ; //Boss������
	private int y ; //Boss������
	private int r ; //Boss��Ӧ�뾶
	private int allBlood ; //��Ѫ��
	private int blood ; //��ǰѪ��

	private Dimension di = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*����BossͼƬ*/
	private Image bossImg = new ImageIcon("img/boss1.png").getImage() ;
	
	public void paint(Graphics g) {
		
		/*��ʼ��Bossλ��*/
		g.drawImage(bossImg,Boss.this.getX() - r,Boss.this.getY() - r,null);
		
		/*Ѫ��*/
		g.setColor(Color.WHITE) ;
		g.fillRect(680, 20, allBlood, 15) ; //��Ѫ��
		
		g.setColor(Color.GREEN) ;
		g.fillRect(680, 20, blood, 15) ; //��ǰѪ��
	}

	/*get��setʵ��*/
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getAllBlood() {
		return allBlood;
	}

	public void setAllBlood(int allBlood) {
		this.allBlood = allBlood;
	}

	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}
	
	public void init()
	{
		/*Boss��ʼ��*/
		setX(680) ; //Boss������
		setY(100) ; //Boss������
		setR(100) ; //Boss��Ӧ�뾶
		setAllBlood(500) ;
		setBlood(500) ;
	}
	
}
