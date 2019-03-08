package bean;

import java.awt.Graphics;
import java.awt.Image;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;

import State.Hero;




public class EnemyPlane1 {
	private int x ; //�л�������
	private int y ; //�л�������
	private int r ; //�л���Ӧ�뾶
	private int speed ; //�л��½��ٶ�
	private Image epImg = null ; //�л�ͼƬ
	private int epNum ; //�л�����
	
	/*ʵ�ֵл�����������֣�14�֣�*/
	public EnemyPlane1() {
		epNum = (int)(Math.random()*14)+1 ;
		
		/*��õл�����ֵ*/
		DecimalFormat df = new DecimalFormat("00") ; //�����ֽ��и�ʽ��
		String ep = null ;
		
		/*����л�ͼƬ*/
		epImg = new ImageIcon("img/ep" + ep + ".png").getImage() ;
	}
	
	public void changeImg() {
		
		epNum = (int)(Math.random()*14)+1 ;
		
		DecimalFormat df = new DecimalFormat("00") ;
		String ep = df.format(epNum) ;
		epImg = new ImageIcon("img/ep" + ep + ".png").getImage() ; 
	}
 	
	/*�л�����*/
	public void paint(Graphics g) {
		
		/*��ʼ��ս��λ��*/
		g.drawImage(epImg, x - r, y - r, null) ;
		
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
	
	/*�л���ս����ײ*/
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
