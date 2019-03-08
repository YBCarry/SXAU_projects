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
	
	private int x ; //子弹横坐标
	private int y ; //子弹纵坐标
	private int r ; //子弹感应半径
	
	/*读入子弹图片*/
	private Image epbulletImg = new ImageIcon("img/ebullet1.png").getImage() ;
	
	/*子弹画板*/
	public void paint(Graphics g) {
		
		g.drawImage(epbulletImg, x - r, y - r, null) ;
		
	}

	/*get、set实现*/
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
	
	/* 敌机子弹和 hero */
	public boolean isHit(Hero hero) {
		if((getX() - hero.getX()) * (getX() - hero.getX()) +
				(getY() - hero.getY()) * (getY() - hero.getY()) <= 
					(getR() + hero.getR()) * (getR() + hero.getR())) { //勾股定理
			return true ;
		}
		else
		{
			return false ;
		}
	}
}
