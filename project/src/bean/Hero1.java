package bean;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Hero1 {
	private int x ; //ս��������
	private int y ; //ս��������
	private int r ; //ս����Ӧ�뾶
	private int allBlood ; //��Ѫ��
	private int blood ; //��ǰѪ��
	private int score ; //�÷�
	
	
	private Dimension di = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*����ս��ͼƬ*/
	private Image heroImg = new ImageIcon("img/hero.png").getImage() ;
	
	/*ս������*/
	public void paint(Graphics g) {
		
		/*��ʼ��ս��λ��*/
		g.drawImage(heroImg,Hero1.this.getX() - r,Hero1.this.getY() - r,null);
		
		/*Ѫ��*/
		g.setColor(Color.WHITE) ;
		g.fillRect(40, 40, allBlood, 30) ; //��Ѫ��
		
		g.setColor(Color.RED) ;
		g.fillRect(40, 40, blood, 30) ; //��ǰѪ��
		
		/*�÷ּ�¼*/
		g.setColor(Color.WHITE) ;
		g.setFont(new Font("Times New Roman", Font.BOLD, 30)) ;
		g.drawString("SCORE:" + score, 1700, 40) ;
		
	}

	/*����ս���˶���ʽ(���̿���)*/
	public void moveUp() {
		y -= 25 ;
		if (y < 0)
			y += 10 ;
	}

	public void moveDown() {
		y += 25 ;
		if (y > di.height - 40)
			y -= 10 ;
	}

	public void moveLeft() {
		x -= 25 ;
		if (x < 0)
			x += 10 ;
	}

	public void moveRight() {
		x += 25 ;
		if (x > di.width - 40)
			x -= 10 ;
	}
	
	/*get��setʵ��*/
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

	public int getScore() {
		return score ;
	}

	public void setScore(int score) {
		this.score = score ;
	}
}
