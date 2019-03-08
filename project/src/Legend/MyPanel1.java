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
	
	/*声明背景边界*/
	private int bgX  = dim.width;
	private int bgY  = dim.height;
	
	/*战机实现*/
	public static Hero1 hero = new Hero1() ;
	
	public EnemyArray enemyArray = new EnemyArray();
	
	public Bullet1Array bullet1Array =new Bullet1Array();
	
	/*定义敌机子弹队列*/
	public EPBullet1Array epbulletArray= new EPBullet1Array();
	
	/*定义炸弹队列*/
	public BombArray bombArray =new BombArray();
	
	/*Boss实现*/
	public static Boss boss = new Boss() ;
	/*定义Boss子弹队列*/
	public BBulletArray bossbulletArray =new BBulletArray();
	
	/*插入游戏结束图片*/
	private Image goImg = new ImageIcon("img/GameOver.jpg").getImage() ;
	private Image bossImg = new ImageIcon("img/boss1.png").getImage() ;
	
	public MyPanel1(Dimension dim) {		
		hero.init();		
		boss.init();				
		/*炸弹初始化*/
		bombArray.newBomb();		
		enemyArray.init();
	}	
	
	/*实现背景图片*/
	private Image bgImg = new ImageIcon().getImage() ;
	
	
	/*游戏面板*/
	public void paint(Graphics g) {
		super.paint(g) ;//表示画之前先擦除上一次的痕迹（在paint()方法中第一行添加）
		/*1  绘制背景*/
		/*不同关卡（1-5 ）不同背景*/
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
		
		/*2  绘制战机*/
		hero.paint(g) ;
		
		/*3  绘制战机子弹*/
		bullet1Array.draw(g);
		
		/*4  绘制敌机群*/
		enemyArray.draw(g);
		/*  绘制敌机子弹*/
		epbulletArray.draw(g);
		
		/*5  绘制炸弹*/
		if(hero.getScore() >= 200)
		{			
			bombArray.draw(g);
		}
		/*6 绘制Boss*/
		if(hero.getScore() >= 500)
		{
			boss.paint(g) ;
			bossbulletArray.draw(g);
		}
				
		/*7  游戏结束*/
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

	/*重写线程中的run()*/
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		int cnt = 0 ; //初始化战机弹夹
		int bcnt = 0 ; //初始化Boss弹夹
		int epcnt = 0 ; //初始化敌机弹夹
		
		while(true) {
			/*战机生命值大于0——继续游戏*/
			if(hero.getBlood() > 0 && boss.getBlood() > 0) 
			{
				/*敌机群*/
				enemyArray.move();
				enemyArray.hit(hero);
				
				/*控制战机子弹发射间隔*/
				if(cnt % 10 == 0) //让子弹每隔15的倍数发射一颗子弹
				{
					bullet1Array.newBullet(hero);
	 			}
				cnt++ ;
				
				/*战机子弹*/
				bullet1Array.move();
				bullet1Array.hit(hero, enemyArray);
				
				///////////////////////////////////
				/*控制敌机子弹发射间隔*/
				if(epcnt % 4 == 0) 
				{
					epbulletArray.newEPBullet(enemyArray); ;
	 			}
				epcnt++ ;				
				/*敌机子弹*/
				epbulletArray.move();
				epbulletArray.hit(hero);
				
				//bomb
				if(hero.getScore() >= 200)
				{
					/*炸弹*/
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
					
					/*控制Boss子弹发射间隔*/
					if(bcnt % 20 == 0) 
					{
						bossbulletArray.newBossBullet(boss);;
		 			}
					bcnt++ ;
				}		
						
			}	
				try {
					/*控制运动速度*/
					Thread.sleep(25) ; //动一次休眠25ms
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace() ;
				}
				
				/*重画*/ 
				repaint() ; //实现运动
		}
	}

	/*重写鼠标监听事件*/
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

	/*重写键盘监听事件*/
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
