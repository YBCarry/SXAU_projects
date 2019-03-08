package controler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.*;



import State.Hero;
import bean.BBulletArray;
import bean.Bloodbag;
import bean.BloodbagArray;
import bean.Bomb;
import bean.BombArray;
import bean.Boss;
import bean.Bullet1Array;
import bean.EPBullet1Array;
import bean.EnemyPlane1Array;


public class MyPanel1 extends JPanel  implements Runnable,MouseMotionListener,KeyListener{
	
	private static final long serialVersionUID = 1L; //序列化

	/*利用获取屏幕对象类Dimension，获得默认工具包调用获取屏幕大小方法*/
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ; 
	
	/*声明背景边界*/
	private int bgX  = dim.width ;
	private int bgY  = dim.height ;
	
	/*背景动4步定义by  bby*/
	private int by ;  //第一幅背景图片的纵坐标
	private int bby ;  //第二幅背景图片的纵坐标
	
	/*战机实现*/
	public static Hero hero = Hero.getHero() ;
	
	/*定义战机子弹队列*/
	public Bullet1Array bullet1Array =new Bullet1Array() ;
	
	/*定义敌机队列*/
	public EnemyPlane1Array enemyArray = new EnemyPlane1Array() ;
	
	/*定义敌机子弹队列*/
	public EPBullet1Array epbulletArray= new EPBullet1Array() ;
	
	/*Boss实现*/
	public static Boss boss = new Boss() ;
	
	/*定义Boss子弹队列*/
	private BBulletArray bossbulletArray = new BBulletArray() ;
	
	/*炸弹实现*/
	public static Bomb bomb = new Bomb() ;
	
	/*定义炸弹队列*/
	public BombArray bombArray =new BombArray() ;
	
	/*血包实现*/
	public static Bloodbag bloodbag = new Bloodbag() ;
	
	/*定义血包队列*/
	public BloodbagArray bloodbagArray =new BloodbagArray() ;
	
	/*插入游戏结束图片*/
	private Image goImg = new ImageIcon("img/GameOver.jpg").getImage() ;
	private Image bossImg = new ImageIcon("img/boss1.png").getImage() ;
	
	/*记录游戏时间*/
	long a = System.currentTimeMillis() ;
	
	/*数据库相关参数*/
	private DB_Operater db_Operater ;
	private String name ;
	private int curTime, lastTime, grade ;
	
	public MyPanel1(Dimension dim, int grade, String name) {
		
		by = 0 ;
		bby = by - bgY ;
		
		lastTime = (int) System.currentTimeMillis() ;
		/*数据库*/
		this.name = name ;
		this.grade = grade ;
		db_Operater = new DB_Operater() ;
		
		/*战机初始化*/
		hero.setX(900) ; //战机横坐标
		hero.setY(950) ; //战机纵坐标
		hero.setR(50) ; //战机感应半径
		hero.setAllBlood(200) ;
		hero.setBlood(200) ;
		hero.setScore(0) ;
		
		/*Boss初始化*/
		boss.setX(1500) ; //Boss横坐标
		boss.setY(100) ; //Boss纵坐标
		boss.setR(100) ; //Boss感应半径
		boss.setAllBlood(500) ;
		boss.setBlood(500) ;
			
		/*炸弹初始化*/
		bombArray.newBomb();
		
		/*血包初始化*/
		bloodbagArray.newBloodbag();
		
		/*敌机队列初始化*/
		enemyArray.init();
		
	}
	
	/*实现背景图片*/
	private Image bgImg = new ImageIcon().getImage() ;	
	
	/*游戏面板*/
	public void paint(Graphics g) {
		
		super.paint(g) ;//表示画之前先擦除上一次的痕迹（在paint()方法中第一行添加）
		/*1  绘制背景*/
		/*不同关卡（1-5 ）不同背景*/
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
		
		//背景动
		this.inintbg() ;
		g.drawImage(bgImg, 0, by, bgX, bgY, null) ;
		g.drawImage(bgImg, 0, bby, bgX, bgY, null) ;
		move() ;
		
		//g.drawImage(bgImg, 0, 0, dim.width, dim.height, null) ;
		
		
		/*2  绘制战机*/
		hero.paint(g) ;
		
		/*3  绘制战机子弹*/
		bullet1Array.draw(g);
		
		/*4  绘制敌机群*/
		enemyArray.draw(g);
		
		/*5  绘制敌机子弹*/
		epbulletArray.draw(g);
		
		/*6  绘制炸弹*/
		if(hero.getScore() >= 2500)
		{
			bombArray.draw(g);
		}
		
		/*7  绘制血包*/
		if(hero.getScore() >= 3500)
		{
			bloodbagArray.draw(g);
		}
		
		/*7 绘制Boss*/
		if(hero.getScore() >= 5000)
		{
			boss.paint(g) ;
			bossbulletArray.draw(g);
		}
	
		/*8  游戏结束*/
		
		if(hero.getBlood() <= 0) {
			g.drawImage(goImg, 0, 0, bgX, bgY, null) ;
			
			/*更新数据*/
			int grade = hero.getScore() ;
			
			int level = (hero.getScore() >= 5000) ? 5 : (hero.getScore() / 1000) ;
			
			curTime = (int)System.currentTimeMillis() ;  //单位毫秒
			int time = (curTime - lastTime) / 1000 + db_Operater.get(name, "time") ;
			int temp = Math.max(grade, db_Operater.get(name, "maxgrade")) ;
			System.out.println("test:!!!" + temp) ;
			db_Operater.update(name, level, grade) ;
			db_Operater.update(name, time + "", "time") ;
			db_Operater.update(name, temp + "", "maxgrade") ;
			System.out.println("tetttt:" + db_Operater.get(name, "maxgrade")) ;
//			g.drawString("GAME OVER", (bgX - 450) / 2, (bgY - 50) / 2) ;
//			g.drawlmage(goImg, 0, 0, bgX, bgY, null) ;
			
			g.setFont(new Font("宋体", Font.BOLD, 20)) ;
			
			grade = db_Operater.get(name, "grade") ;
			int maxgrade = db_Operater.get(name, "maxgrade") ;
			
			g.drawString("当前得分:" + grade, (bgX - 100) / 2, bgY -180) ;
			g.drawString("最高得分:" + maxgrade, (bgX - 100) / 2, bgY -200) ;
		}
		
		if(hero.getScore() >= 5000) {
			if(boss.getBlood() <= 0) {
				g.drawImage(goImg, 0, 0, bgX, bgY, null) ;
				g.drawImage(bossImg, 0, 0, bgX, bgY, null) ;
				g.drawString("I Will Come Back Again~~~", 500, 400);
				g.drawString("Customs clearance time:" + (System.currentTimeMillis() - a)/1000f + "S", 900, 700) ;
				g.drawString("Final Blood:" + hero.getBlood(),550, 900) ;
				g.drawString("Final Score:" + hero.getScore(),550, 950) ;
				g.drawString("Legend-Carry:" + (0.4*hero.getBlood()+0.6*hero.getScore())*10 + "!",550, 1000)  ;
				
				/*更新数据*/
				int grade = hero.getScore() ;
				
				int level = (hero.getScore() >= 5000) ? 5 : (hero.getScore() / 1000) ;
				
				curTime = (int)System.currentTimeMillis() ;  //单位毫秒
				int time = (curTime - lastTime) / 1000 + db_Operater.get(name, "time") ;
				int temp = Math.max(grade, db_Operater.get(name, "maxgrade")) ;
				System.out.println("test:!!!" + temp) ;
				db_Operater.update(name, level, grade) ;
				db_Operater.update(name, time + "", "time") ;
				db_Operater.update(name, temp + "", "maxgrade") ;
				System.out.println("tetttt:" + db_Operater.get(name, "maxgrade")) ;
//				g.drawString("GAME OVER", (bgX - 450) / 2, (bgY - 50) / 2) ;
//				g.drawlmage(goImg, 0, 0, bgX, bgY, null) ;
				
				g.setFont(new Font("宋体", Font.BOLD, 20)) ;
				
				grade = db_Operater.get(name, "grade") ;
				int maxgrade = db_Operater.get(name, "maxgrade") ;
				
				g.drawString("当前得分:" + grade, (bgX - 100) / 2, bgY -180) ;
				g.drawString("最高得分:" + maxgrade, (bgX - 100) / 2, bgY -200) ;
				
			}
		}
		
	}
	
	//背景动
	public void move(){
		by += 3;
		bby += 3;
		if(by > bgY) {
			by = bby - bgY;
			
		}
		if(bby > bgY) {
			bby = by - bgY ;
			
		}
		
	}


	//切换背景
	public void inintbg(){
		int bgNum = hero.getLevel() % 5 + 1 ;
		bgImg = new ImageIcon("img/bg" + bgNum + ".jpg").getImage() ;
		
		
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
			if(cnt % 10 == 0) //让子弹每隔10的倍数发射一颗子弹
			{
				bullet1Array.newBullet(hero);
 			}
			cnt++ ;
			
			/*战机子弹*/
			bullet1Array.move();
			bullet1Array.hit(hero, enemyArray);				
			
			/*控制敌机子弹发射间隔*/
			if(epcnt % 3 == 0) 
			{
				epbulletArray.newEPBullet(enemyArray); ;
 			}
			epcnt++ ;				
			/*敌机子弹*/
			epbulletArray.move();
			epbulletArray.hit(hero);
			
			/*炸弹*/
			if(hero.getScore() >= 2500)
			{
				/*炸弹*/
				bombArray.move();
				bombArray.hit(hero);
				bullet1Array.hit(hero, bombArray);					
				if(epcnt % 15 == 0) 
				{
					bombArray.newBomb();
	 			}
			}
			
			/*血包*/
			if(hero.getScore() >= 3500)
			{
				/*血包*/
				bloodbagArray.move();
				bloodbagArray.hit(hero);
					
				if(epcnt % 15 == 0) 
				{
					bloodbagArray.newBloodbag();
	 			}
			}
			
			// boss
			if(hero.getScore() >= 5000)
			{
				bossbulletArray.move();
				bossbulletArray.hit(hero);
				bullet1Array.hit(hero, boss);
				
				/*Boss移动*/
				if(boss.flag == true) 
				{
					boss.moveLeft() ; //左移
					
				}
				
				if(boss.flag == false) 
				{
					boss.moveRight() ; //右移
				}
				
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

		/*战机左移*/
		if(e.getKeyCode() == KeyEvent.VK_A)
		{
			hero.moveLeft() ;
		}
		/*战机下移*/
		if(e.getKeyCode()==KeyEvent.VK_S)
		{
			hero.moveDown() ;
		}
		/*战机右移*/
		if(e.getKeyCode()==KeyEvent.VK_D)
		{
			hero.moveRight() ;
		}
		/*战机上移*/
		if(e.getKeyCode()==KeyEvent.VK_W)
		{
			hero.moveUp() ;
		}
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
