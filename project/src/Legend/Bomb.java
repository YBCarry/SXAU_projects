package Legend;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Bomb {
	
	private int x ; //炸弹横坐标
	private int y ; //炸弹纵坐标
	private int r ; //炸弹感应半径
	private int speed ; //炸弹下降速度
	
	/*读入炸弹图片*/
	private Image bombImg = new ImageIcon("img/ep15.png").getImage() ;
	
	/*炸弹画板*/
	public void paint(Graphics g) {
		
		g.drawImage(bombImg, x - r, y - r, null) ;
		
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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/*炸弹和战机碰撞*/
	public boolean isHit(Hero1 hero) {
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
