package bean;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Boss {
	
	private int x ; //Boss横坐标
	private int y ; //Boss纵坐标
	private int r ; //Boss感应半径
	private int allBlood ; //总血量
	private int blood ; //当前血量

	private Dimension di = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*读入Boss图片*/
	private Image bossImg = new ImageIcon("img/boss1.png").getImage() ;
	
	public void paint(Graphics g) {
		
		/*初始化Boss位置*/
		g.drawImage(bossImg,Boss.this.getX() - r,Boss.this.getY() - r,null) ;
		
		/*血条*/
		g.setColor(Color.WHITE) ;
		g.fillRect(680, 20, allBlood, 15) ; //总血量
		
		g.setColor(Color.GREEN) ;
		g.fillRect(680, 20, blood, 15) ; //当前血量
	}
	
	/*Boss移动*/
	public static boolean flag = true ; //设置转弯标识
	/*左移*/
	public void moveLeft() {

		x -= 2;
		if(x == 400) //Boss移动左边界
		{
			flag = false ;
			
		}
	}
	/*右移*/
	public void moveRight() {

		x += 2;
		if(x == di.width - 400) //Boss移动右边界
		{
			flag = true ;
			
		}
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

	public int getAllBlood() {
		return allBlood ;
	}

	public void setAllBlood(int allBlood) {
		this.allBlood = allBlood ;
	}

	public int getBlood() {
		return blood ;
	}

	public void setBlood(int blood) {
		this.blood = blood ;
	}

}
