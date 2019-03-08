package Legend;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;


public class MyPanel1 extends JPanel  implements Runnable,MouseMotionListener,KeyListener{
	
	private static final long serialVersionUID = 1L;

	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*���������߽�*/
	private int bgX  = dim.width;
	private int bgY  = dim.height;
	
	/*ս��ʵ��*/
	public static Hero1 hero = new Hero1() ;
	
	public EnemyArray enemyArray = new EnemyArray();
	
	public Bullet1Array bullet1Array =new Bullet1Array();
	
	/*����л��ӵ�����*/
	public EPBullet1Array epbulletArray= new EPBullet1Array();
	
	/*����ը������*/
	public BombArray bombArray =new BombArray();
	
	/*Bossʵ��*/
	public static Boss boss = new Boss() ;
	/*����Boss�ӵ�����*/
	public BBulletArray bossbulletArray =new BBulletArray();
	
	/*������Ϸ����ͼƬ*/
	private Image goImg = new ImageIcon("img/GameOver.jpg").getImage() ;
	private Image bossImg = new ImageIcon("img/boss1.png").getImage() ;
	
	public MyPanel1(Dimension dim) {		
		hero.init();		
		boss.init();				
		/*ը����ʼ��*/
		bombArray.newBomb();		
		enemyArray.init();
	}	
	
	/*ʵ�ֱ���ͼƬ*/
	private Image bgImg = new ImageIcon().getImage() ;
	
	
	/*��Ϸ���*/
	public void paint(Graphics g) {
		super.paint(g) ;//��ʾ��֮ǰ�Ȳ�����һ�εĺۼ�����paint()�����е�һ����ӣ�
		/*1  ���Ʊ���*/
		/*��ͬ�ؿ���1-5 ����ͬ����*/
		if(hero.getScore() < 200)
		{
			bgImg = new ImageIcon("img/bg1.jpg").getImage() ;
		}
		else if(hero.getScore() < 400)
		{
			bgImg = new ImageIcon("img/bg2.jpg").getImage() ;
		}
		else if(hero.getScore() < 600)
		{
			bgImg = new ImageIcon("img/bg3.jpg").getImage() ;
		}
		else if(hero.getScore() < 800)
		{
			bgImg = new ImageIcon("img/bg4.jpg").getImage() ;
		}
		else
		{
			bgImg = new ImageIcon("img/bg5.jpg").getImage() ;
		}
		
		g.drawImage(bgImg, 0, 0, dim.width, dim.height, null) ;
		
		/*2  ����ս��*/
		hero.paint(g) ;
		
		/*3  ����ս���ӵ�*/
		bullet1Array.draw(g);
		
		/*4  ���Ƶл�Ⱥ*/
		enemyArray.draw(g);
		/*  ���Ƶл��ӵ�*/
		epbulletArray.draw(g);
		
		/*5  ����ը��*/
		if(hero.getScore() >= 200)
		{			
			bombArray.draw(g);
		}
		/*6 ����Boss*/
		if(hero.getScore() >= 500)
		{
			boss.paint(g) ;
			bossbulletArray.draw(g);
		}
				
		/*7  ��Ϸ����*/
		if(hero.getBlood() <= 0)
		{
			g.drawImage(goImg, 0, 0, bgX, bgY, null) ;
		}
		
		////////////////
		if(hero.getScore() >= 100)
		{
			if(boss.getBlood() <= 0)
			{
				g.drawString("Game Win", 500, 400);
			}
		}
		
	}

	/*��д�߳��е�run()*/
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		int cnt = 0 ; //��ʼ��ս������
		int bcnt = 0 ; //��ʼ��Boss����
		int epcnt = 0 ; //��ʼ���л�����
		
		while(true) {
			/*ս������ֵ����0����������Ϸ*/
			if(hero.getBlood() > 0 && boss.getBlood() > 0) 
			{
				/*�л�Ⱥ*/
				enemyArray.move();
				enemyArray.hit(hero);
				
				/*����ս���ӵ�������*/
				if(cnt % 10 == 0) //���ӵ�ÿ��15�ı�������һ���ӵ�
				{
					bullet1Array.newBullet(hero);
	 			}
				cnt++ ;
				
				/*ս���ӵ�*/
				bullet1Array.move();
				bullet1Array.hit(hero, enemyArray);
				
				///////////////////////////////////
				/*���Ƶл��ӵ�������*/
				if(epcnt % 4 == 0) 
				{
					epbulletArray.newEPBullet(enemyArray); ;
	 			}
				epcnt++ ;				
				/*�л��ӵ�*/
				epbulletArray.move();
				epbulletArray.hit(hero);
				
				//bomb
				if(hero.getScore() >= 200)
				{
					/*ը��*/
					bombArray.move();
					bombArray.hit(hero);
					bullet1Array.hit(hero, bombArray);					
					if(epcnt % 20 == 0) 
					{
						bombArray.newBomb();
		 			}
				}
					
				// boss
				if(hero.getScore() >= 500)
				{
					bossbulletArray.move();
					bossbulletArray.hit(hero);
					bullet1Array.hit(hero, boss);
					
					/*����Boss�ӵ�������*/
					if(bcnt % 20 == 0) 
					{
						bossbulletArray.newBossBullet(boss);;
		 			}
					bcnt++ ;
				}		
						
			}	
				try {
					/*�����˶��ٶ�*/
					Thread.sleep(25) ; //��һ������25ms
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace() ;
				}
				
				/*�ػ�*/ 
				repaint() ; //ʵ���˶�
		}
	}

	/*��д�������¼�*/
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
		hero.setX(e.getX()) ;
		hero.setY(e.getY()) ;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/*��д���̼����¼�*/
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		hero.keyPressed(e);
		repaint() ;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
