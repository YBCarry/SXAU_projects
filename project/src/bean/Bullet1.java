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
	
	private int x ; //子弹横坐标
	private int y ; //子弹纵坐标
	private int r ; //子弹感应半径
	
	/*读入子弹图片*/
	private Image bulletImg = new ImageIcon("img/fire.png").getImage() ;
	
	/*子弹画板*/
	public void paint(Graphics g) {
		
		/*初始化子弹位置*/
		g.drawImage(bulletImg, x - r, y - r, null) ;
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
	
	/*战机子弹和敌机碰撞*/
	public boolean isHit( EnemyPlane1 ep) {
		if((getX() - ep.getX()) * (getX() - ep.getX()) +
				(getY() - ep.getY()) * (getY() - ep.getY()) <= 
					(getR() + ep.getR()) * (getR() + ep.getR())) { //勾股定理
			return true ;
		}
		else
		{
			return false ;
		}
	}
	/*战机子弹和Boss碰撞*/
	public boolean isHit(Boss boss) {
		if((getX() - boss.getX()) * (getX() - boss.getX()) +
				(getY() - boss.getY()) * (getY() - boss.getY()) <= 
					(getR() + boss.getR()) * (getR() + boss.getR())) { //勾股定理
			return true ;
		}
		else
		{
			return false ;
		}
	}
	
	/*战机子弹和炸弹碰撞*/
	public boolean isHit( Bomb bomb) {
		if((getX() - bomb.getX()) * (getX() - bomb.getX()) +
				(getY() - bomb.getY()) * (getY() - bomb.getY()) <= 
					(getR() + bomb.getR()) * (getR() + bomb.getR())) { //勾股定理
			return true ;
		}
		else
		{
			return false ;
		}
	}
}
