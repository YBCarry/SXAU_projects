package Legend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Hero1 {
	private int x ; //战机横坐标
	private int y ; //战机纵坐标
	private int r ; //战机感应半径
	private int allBlood ; //总血量
	private int blood ; //当前血量
	private int score ; //得分
	
	
	private Dimension di = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*读入战机图片*/
	private Image heroImg = new ImageIcon("img/hero.png").getImage() ;
	
	/*战机画板*/
	public void paint(Graphics g) {
		
		/*初始化战机位置*/
		g.drawImage(heroImg,Hero1.this.getX() - r,Hero1.this.getY() - r,null);
		
		/*血条*/
		g.setColor(Color.WHITE) ;
		g.fillRect(40, 40, allBlood+4, 30) ; //总血量
		
		g.setColor(Color.RED) ;
		g.fillRect(42, 42, blood, 26) ; //当前血量
		
		/*得分记录*/
		g.setColor(Color.WHITE) ;
		g.setFont(new Font("Times New Roman", Font.BOLD, 30)) ;
		g.drawString("SCORE:" + score, 40, 100) ;
		
	}

	/*定义战机运动方式*/
	public void moveUp() {
		y -= 25;
		if (y < 0)
			y += 10;
	}

	public void moveDown() {
		y += 25;
		if (y > di.height - 40)
			y -= 10;
	}

	public void moveLeft() {
		x -= 25;
		if (x < 0)
			x += 10;
	}

	public void moveRight() {
		x += 25;
		if (x > di.width - 40)
			x -= 10;
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

	public int getAllBlood() {
		return allBlood;
	}

	public void setAllBlood(int allBlood) {
		this.allBlood = allBlood;
	}

	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		/*战机左移*/
		if(e.getKeyCode() == KeyEvent.VK_A)
		{
			 moveLeft() ;
		}
		/*战机下移*/
		if(e.getKeyCode()==KeyEvent.VK_S)
		{
			 moveDown() ;
		}
		/*战机右移*/
		if(e.getKeyCode()==KeyEvent.VK_D)
		{
			moveRight() ;
		}
		/*战机上移*/
		if(e.getKeyCode()==KeyEvent.VK_W)
		{
			moveUp() ;
		}
	}
	
	public void init()
	{
		/*战机初始化*/
		setX(900) ; //战机横坐标
		setY(950) ; //战机纵坐标
		setR(50) ; //战机感应半径
		setAllBlood(200) ;
		setBlood(200) ;
		setScore(0) ;
	}
}
