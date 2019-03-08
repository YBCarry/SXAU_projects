package Legend;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class BBullet {
	
	private int x ; //�ӵ�������
	private int y ; //�ӵ�������
	private int r ; //�ӵ���Ӧ�뾶

	/*�����ӵ�ͼƬ*/
	private Image bbImg = new ImageIcon("img/bb.png").getImage() ;
	
	/*�ӵ�����*/
	public void paint(Graphics g) {

		/*��ʼ���ӵ�λ��*/
		g.drawImage(bbImg, x - r, y - r, null) ;
		
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
	
	/*boss�ӵ���ս�� */
	public boolean isHit( Hero1 hero) {
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
