package bean;

import java.awt.Graphics;
import java.awt.Image;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;

import State.Hero;




public class EnemyPlane1 {
	private int x ; //敌机横坐标
	private int y ; //敌机纵坐标
	private int r ; //敌机感应半径
	private int speed ; //敌机下降速度
	private Image epImg = null ; //敌机图片
	private int epNum ; //敌机数量
	
	/*实现敌机种类随机出现（14种）*/
	public EnemyPlane1() {
		epNum = (int)(Math.random()*14)+1 ;
		
		/*获得敌机索引值*/
		DecimalFormat df = new DecimalFormat("00") ; //对数字进行格式化
		String ep = null ;
		
		/*读入敌机图片*/
		epImg = new ImageIcon("img/ep" + ep + ".png").getImage() ;
	}
	
	public void changeImg() {
		
		epNum = (int)(Math.random()*14)+1 ;
		
		DecimalFormat df = new DecimalFormat("00") ;
		String ep = df.format(epNum) ;
		epImg = new ImageIcon("img/ep" + ep + ".png").getImage() ; 
	}
 	
	/*敌机画板*/
	public void paint(Graphics g) {
		
		/*初始化战机位置*/
		g.drawImage(epImg, x - r, y - r, null) ;
		
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
	
	/*敌机和战机碰撞*/
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
