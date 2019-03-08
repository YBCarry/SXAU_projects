package bean;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import State.Hero;




public class Bloodbag {
	
	private int x ; //血包横坐标
	private int y ; //血包纵坐标
	private int r ; //血包感应半径
	private int speed ; //血包下降速度
	
	/*读入血包图片*/
	private Image bloodbagImg = new ImageIcon("img/lives.png").getImage() ;
	
	/*血包画板*/
	public void paint(Graphics g) {
		
		g.drawImage(bloodbagImg, x - r, y - r, null) ;
		
	}
	
	/*get、set实现*/
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
	
	/*血包和战机碰撞*/
	public boolean isHit(Hero hero) {
		if((getX() - hero.getX()) * (getX() - hero.getX()) +
				(getY() - hero.getY()) * (getY() - hero.getY()) <= 
					(getR() - hero.getR()) * (getR() - hero.getR())) { //勾股定理
			return true ;
		}
		else
		{
			return false ;
		}
	}

}
