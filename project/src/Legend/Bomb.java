package Legend;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Bomb {
	
	private int x ; //ը��������
	private int y ; //ը��������
	private int r ; //ը����Ӧ�뾶
	private int speed ; //ը���½��ٶ�
	
	/*����ը��ͼƬ*/
	private Image bombImg = new ImageIcon("img/ep15.png").getImage() ;
	
	/*ը������*/
	public void paint(Graphics g) {
		
		g.drawImage(bombImg, x - r, y - r, null) ;
		
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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/*ը����ս����ײ*/
	public boolean isHit(Hero1 hero) {
		if((getX() - hero.getX()) * (getX() - hero.getX()) +
				(getY() - hero.getY()) * (getY() - hero.getY()) <= 
					(getR() - hero.getR()) * (getR() - hero.getR())) { //���ɶ���
			return true ;
		}
		else
		{
			return false ;
		}
	}

}
