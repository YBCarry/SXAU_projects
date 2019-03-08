package bean;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import State.Hero;




/**
 * 
 * @author dsq
 *
 */
public class EPBullet1 {
	
	private int x ; //�ӵ�������
	private int y ; //�ӵ�������
	private int r ; //�ӵ���Ӧ�뾶
	
	/*�����ӵ�ͼƬ*/
	private Image epbulletImg = new ImageIcon("img/ebullet1.png").getImage() ;
	
	/*�ӵ�����*/
	public void paint(Graphics g) {
		
		g.drawImage(epbulletImg, x - r, y - r, null) ;
		
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
	
	/* �л��ӵ��� hero */
	public boolean isHit(Hero hero) {
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
