package controler;
//
//
//package Controler;
//
//import java.awt.*;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseMotionListener;
//
//import javax.swing.*;
//
//import model.Hero;
//
//import bean.BBulletArray;
//import bean.Bloodbag;
//import bean.BloodbagArray;
//import bean.Bomb;
//import bean.BombArray;
//import bean.Boss;
//import bean.Bullet1Array;
//import bean.EPBullet1Array;
//import bean.EnemyPlane1Array;
//
//
//
//public class MyPanel111 extends JPanel  implements Runnable,MouseMotionListener,KeyListener{
//	
//	private static final long serialVersionUID = 1L; //���л�
//
//	/*���û�ȡ��Ļ������Dimension�����Ĭ�Ϲ��߰����û�ȡ��Ļ��С����*/
//	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ; 
//	
//	/*���������߽�*/
//	private int bgX  = dim.width;
//	private int bgY  = dim.height;
//	
//	/*ս��ʵ��*/
//	public static Hero hero = new Hero() ;
//	
//	/*����ս���ӵ�����*/
//	public Bullet1Array bullet1Array =new Bullet1Array();
//	
//	/*����л�����*/
//	public EnemyPlane1Array enemyArray = new EnemyPlane1Array();
//	
//	/*����л��ӵ�����*/
//	public EPBullet1Array epbulletArray= new EPBullet1Array();
//	
//	/*Bossʵ��*/
//	public static Boss boss = new Boss() ;
//	
//	/*����Boss�ӵ�����*/
//	private BBulletArray bossbulletArray = new BBulletArray() ;
//	
//	/*ը��ʵ��*/
//	public static Bomb bomb = new Bomb() ;
//	
//	/*����ը������*/
//	public BombArray bombArray =new BombArray();
//	
//	/*Ѫ��ʵ��*/
//	public static Bloodbag bloodbag = new Bloodbag() ;
//	
//	/*����Ѫ������*/
//	public BloodbagArray bloodbagArray =new BloodbagArray();
//	
//	/*������Ϸ����ͼƬ*/
//	private Image goImg = new ImageIcon("img/GameOver.jpg").getImage() ;
//	private Image bossImg = new ImageIcon("img/boss1.png").getImage() ;
//	
//	/*��¼��Ϸʱ��*/
//	long a = System.currentTimeMillis() ;
//	
//	public MyPanel111(Dimension dim) {
//		
//		/*ս����ʼ��*/
//		hero.setX(900) ; //ս��������
//		hero.setY(950) ; //ս��������
//		hero.setR(50) ; //ս����Ӧ�뾶
//		hero.setAllBlood(200) ;
//		hero.setBlood(200) ;
//		hero.setScore(0) ;
//		
//		/*Boss��ʼ��*/
//		boss.setX(1500) ; //Boss������
//		boss.setY(100) ; //Boss������
//		boss.setR(100) ; //Boss��Ӧ�뾶
//		boss.setAllBlood(500) ;
//		boss.setBlood(500) ;
//			
//		/*ը����ʼ��*/
//		bombArray.newBomb();
//		
//		/*Ѫ����ʼ��*/
//		bloodbagArray.newBloodbag();
//		
//		/*�л����г�ʼ��*/
//		enemyArray.init();
//		
//	}
//	
//	/*ʵ�ֱ���ͼƬ*/
//	private Image bgImg = new ImageIcon().getImage() ;	
//
//	/*��Ϸ���*/
//	public void paint(Graphics g) {
//		
//		super.paint(g) ;//��ʾ��֮ǰ�Ȳ�����һ�εĺۼ�����paint()�����е�һ����ӣ�
//		/*1  ���Ʊ���*/
//		/*��ͬ�ؿ���1-5 ����ͬ����*/
//		if(hero.getScore() < 1000)
//		{
//			bgImg = new ImageIcon("img/bg1.jpg").getImage() ;
//		}
//		else if(hero.getScore() < 2000)
//		{
//			bgImg = new ImageIcon("img/bg2.jpg").getImage() ;
//		}
//		else if(hero.getScore() < 3000)
//		{
//			bgImg = new ImageIcon("img/bg3.jpg").getImage() ;
//		}
//		else if(hero.getScore() < 4000)
//		{
//			bgImg = new ImageIcon("img/bg4.jpg").getImage() ;
//		}
//		else
//		{
//			bgImg = new ImageIcon("img/bg5.jpg").getImage() ;
//		}
//		
//		g.drawImage(bgImg, 0, 0, dim.width, dim.height, null) ;
//		
//		/*2  ����ս��*/
//		hero.paint(g) ;
//		
//		/*3  ����ս���ӵ�*/
//		bullet1Array.draw(g);
//		
//		/*4  ���Ƶл�Ⱥ*/
//		enemyArray.draw(g);
//		
//		/*5  ���Ƶл��ӵ�*/
//		epbulletArray.draw(g);
//		
//		/*6  ����ը��*/
//		if(hero.getScore() >= 2500)
//		{
//			bombArray.draw(g);
//		}
//		
//		/*7  ����Ѫ��*/
//		if(hero.getScore() >= 3500)
//		{
//			bloodbagArray.draw(g);
//		}
//		
//		/*7 ����Boss*/
//		if(hero.getScore() >= 5000)
//		{
//			boss.paint(g) ;
//			bossbulletArray.draw(g);
//		}
//	
//		/*8  ��Ϸ����*/
//		if(hero.getBlood() <= 0)
//		{
//			g.drawImage(goImg, 0, 0, bgX, bgY, null) ;
//		}
//		if(hero.getScore() >= 5000)
//		{
//			if(boss.getBlood() <= 0)
//			{
//				g.drawImage(goImg, 0, 0, bgX, bgY, null) ;
//				g.drawImage(bossImg, 0, 0, bgX, bgY, null) ;
//				g.drawString("I Will Come Back Again~~~", 500, 400);
//				g.drawString("Customs clearance time:" + (System.currentTimeMillis() - a)/1000f + "S", 900, 700) ;
//				g.drawString("Final Blood:" + hero.getBlood(),550, 900) ;
//				g.drawString("Final Score:" + hero.getScore(),550, 950) ;
//				g.drawString("Legend-Carry:" + (0.4*hero.getBlood()+0.6*hero.getScore())*10 + "!",550, 1000)  ;
//				
//			}
//		}
//		
//	}
//
//	/*��д�߳��е�run()*/
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		
//		int cnt = 0 ; //��ʼ��ս������
//		int bcnt = 0 ; //��ʼ��Boss����
//		int epcnt = 0 ; //��ʼ���л�����
//		
//		while(true) {
//			
//		/*ս������ֵ����0����������Ϸ*/
//		if(hero.getBlood() > 0 && boss.getBlood() > 0) 
//		{
//			/*�л�Ⱥ*/
//			enemyArray.move();
//			enemyArray.hit(hero);
//			
//			/*����ս���ӵ�������*/
//			if(cnt % 10 == 0) //���ӵ�ÿ��10�ı�������һ���ӵ�
//			{
//				bullet1Array.newBullet(hero);
// 			}
//			cnt++ ;
//			
//			/*ս���ӵ�*/
//			bullet1Array.move();
//			bullet1Array.hit(hero, enemyArray);				
//			
//			/*���Ƶл��ӵ�������*/
//			if(epcnt % 3 == 0) 
//			{
//				epbulletArray.newEPBullet(enemyArray); ;
// 			}
//			epcnt++ ;				
//			/*�л��ӵ�*/
//			epbulletArray.move();
//			epbulletArray.hit(hero);
//			
//			/*ը��*/
//			if(hero.getScore() >= 2500)
//			{
//				/*ը��*/
//				bombArray.move();
//				bombArray.hit(hero);
//				bullet1Array.hit(hero, bombArray);					
//				if(epcnt % 15 == 0) 
//				{
//					bombArray.newBomb();
//	 			}
//			}
//			
//			/*Ѫ��*/
//			if(hero.getScore() >= 3500)
//			{
//				/*Ѫ��*/
//				bloodbagArray.move();
//				bloodbagArray.hit(hero);
//					
//				if(epcnt % 15 == 0) 
//				{
//					bloodbagArray.newBloodbag();
//	 			}
//			}
//			
//			// boss
//			if(hero.getScore() >= 5000)
//			{
//				bossbulletArray.move();
//				bossbulletArray.hit(hero);
//				bullet1Array.hit(hero, boss);
//				
//				/*Boss�ƶ�*/
//				if(boss.flag == true) 
//				{
//					boss.moveLeft() ; //����
//					
//				}
//				
//				if(boss.flag == false) 
//				{
//					boss.moveRight() ; //����
//				}
//				
//				/*����Boss�ӵ�������*/
//				if(bcnt % 20 == 0) 
//				{
//					bossbulletArray.newBossBullet(boss);;
//	 			}
//				bcnt++ ;
//			}		
//		}	
//			
//			
//			try {
//				/*�����˶��ٶ�*/
//				Thread.sleep(25) ; //��һ������25ms
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace() ;
//			}
//			
//			/*�ػ�*/ 
//			repaint() ; //ʵ���˶�
//		}
//	}
//
//	/*��д�������¼�*/
//	@Override
//	public void mouseDragged(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//		hero.setX(e.getX()) ;
//		hero.setY(e.getY()) ;
//	}
//
//	@Override
//	public void mouseMoved(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	/*��д���̼����¼�*/
//	@Override
//	public void keyPressed(KeyEvent e) {
//		// TODO Auto-generated method stub
//
//		/*ս������*/
//		if(e.getKeyCode() == KeyEvent.VK_A)
//		{
//			hero.moveLeft() ;
//		}
//		/*ս������*/
//		if(e.getKeyCode()==KeyEvent.VK_S)
//		{
//			hero.moveDown() ;
//		}
//		/*ս������*/
//		if(e.getKeyCode()==KeyEvent.VK_D)
//		{
//			hero.moveRight() ;
//		}
//		/*ս������*/
//		if(e.getKeyCode()==KeyEvent.VK_W)
//		{
//			hero.moveUp() ;
//		}
//		repaint() ;
//	}
//
//	@Override
//	public void keyReleased(KeyEvent e) {
//		// TODO Auto-generated method stub
//	
//	}
//
//	@Override
//	public void keyTyped(KeyEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
