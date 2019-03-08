package bean;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import State.Hero;




public class BBullet {
	
	private int x ; //Boss�ӵ�������
	private int y ; //Boss�ӵ�������
	private int r ; //Boss�ӵ���Ӧ�뾶
	
	/*����Boss�ӵ�ͼƬ*/
	private Image bbImg = new ImageIcon("img/bb.png").getImage() ;
	
	/*Boss�ӵ�����*/
	public void paint(Graphics g) {

		/*��ʼ��Boss�ӵ�λ��*/
		g.drawImage(bbImg, x - r, y - r, null) ;
		
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
	
	/*boss�ӵ���ս����ײ */
	public boolean isHit( Hero hero) {
		if((getX() - hero.getX()) * (getX() - hero.getX()) +
				(getY() - hero.getY()) * (getY() - hero.getY()) <= 
					(getR() + hero.getR()) * (getR() + hero.getR())) { //���ɶ���
			return true ;
		}
		else
		{
			return false ;
		}
	}
}
