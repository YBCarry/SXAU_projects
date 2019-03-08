package bean;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import State.Hero;




public class Bloodbag {
	
	private int x ; //Ѫ��������
	private int y ; //Ѫ��������
	private int r ; //Ѫ����Ӧ�뾶
	private int speed ; //Ѫ���½��ٶ�
	
	/*����Ѫ��ͼƬ*/
	private Image bloodbagImg = new ImageIcon("img/lives.png").getImage() ;
	
	/*Ѫ������*/
	public void paint(Graphics g) {
		
		g.drawImage(bloodbagImg, x - r, y - r, null) ;
		
	}
	
	/*get��setʵ��*/
	public int getX() {
		return x ;
	}

	public void setX(int x) {
		this.x = x ;
	}

	public int getY() {
		return y ;
	}

	public void setY(int y) {
		this.y = y ;
	}

	public int getR() {
		return r ;
	}

	public void setR(int r) {
		this.r = r ;
	}

	public int getSpeed() {
		return speed ;
	}

	public void setSpeed(int speed) {
		this.speed = speed ;
	}
	
	/*Ѫ����ս����ײ*/
	public boolean isHit(Hero hero) {
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
