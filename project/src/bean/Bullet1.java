package bean;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;


/**
 * 
 * @author dsq
 *
 */
public class Bullet1 {
	
	private int x ; //�ӵ�������
	private int y ; //�ӵ�������
	private int r ; //�ӵ���Ӧ�뾶
	
	/*�����ӵ�ͼƬ*/
	private Image bulletImg = new ImageIcon("img/fire.png").getImage() ;
	
	/*�ӵ�����*/
	public void paint(Graphics g) {
		
		/*��ʼ���ӵ�λ��*/
		g.drawImage(bulletImg, x - r, y - r, null) ;
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
	
	/*ս���ӵ��͵л���ײ*/
	public boolean isHit( EnemyPlane1 ep) {
		if((getX() - ep.getX()) * (getX() - ep.getX()) +
				(getY() - ep.getY()) * (getY() - ep.getY()) <= 
					(getR() + ep.getR()) * (getR() + ep.getR())) { //���ɶ���
			return true ;
		}
		else
		{
			return false ;
		}
	}
	/*ս���ӵ���Boss��ײ*/
	public boolean isHit(Boss boss) {
		if((getX() - boss.getX()) * (getX() - boss.getX()) +
				(getY() - boss.getY()) * (getY() - boss.getY()) <= 
					(getR() + boss.getR()) * (getR() + boss.getR())) { //���ɶ���
			return true ;
		}
		else
		{
			return false ;
		}
	}
	
	/*ս���ӵ���ը����ײ*/
	public boolean isHit( Bomb bomb) {
		if((getX() - bomb.getX()) * (getX() - bomb.getX()) +
				(getY() - bomb.getY()) * (getY() - bomb.getY()) <= 
					(getR() + bomb.getR()) * (getR() + bomb.getR())) { //���ɶ���
			return true ;
		}
		else
		{
			return false ;
		}
	}
}
