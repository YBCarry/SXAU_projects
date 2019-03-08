package bean;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import State.Hero;




public class BBullet {
	
	private int x ; //Boss子弹横坐标
	private int y ; //Boss子弹纵坐标
	private int r ; //Boss子弹感应半径
	
	/*读入Boss子弹图片*/
	private Image bbImg = new ImageIcon("img/bb.png").getImage() ;
	
	/*Boss子弹画板*/
	public void paint(Graphics g) {

		/*初始化Boss子弹位置*/
		g.drawImage(bbImg, x - r, y - r, null) ;
		
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
	
	/*boss子弹和战机碰撞 */
	public boolean isHit( Hero hero) {
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
