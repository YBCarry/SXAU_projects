package Legend;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

public class EnemyArray {
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*声明背景边界*/
	private int bgX  = dim.width;
	private int bgY  = dim.height;

	/*声明敌机数量*/
	private int epNum = 14 ;

	/*定义敌机队列*/
	private ArrayList<EnemyPlane1> eps = new ArrayList<EnemyPlane1>() ;
	
	public EnemyPlane1 get(int i)
	{
		return eps.get(i);
	}
	public int getNum()
	{
		return eps.size();
	}
	public void remove(int i)
	{
		eps.remove(i);
	}
	
	//////////////////////////////////////////
	public void init()
	{
		/*敌机初始化*/
		for(int i = 0; i < epNum; i++) 
		{
			EnemyPlane1 ep = new EnemyPlane1() ;
			/*敌机随机出现*/
			float eX = (float)(Math.random()*bgX) ;
			float eY = (float)(Math.random()*bgX) ;
			
			ep.setX((int)eX) ; //敌机横坐标
			ep.setY((int)eY) ; //敌机纵坐标
			ep.setR(30) ; //敌机感应半径
			
			/*敌机速度随机*/
			ep.setSpeed((int)(Math.random()*6) + 1) ;
			eps.add(ep) ;
		}
	}
	
	public void draw(Graphics g)
	{
		for(int i = 0; i < eps.size(); i++) 
		{
			eps.get(i).paint(g) ;
		}
	
	}
	
	public void move()
	{
		for(int i = 0; i < eps.size(); i++)
		{
			EnemyPlane1 ep = eps.get(i) ;
			if(ep.getY() >= bgY) 
			{
				eps.remove(i) ;
				// new enemy
				ep.setX((int)(Math.random()*bgX)) ;
				ep.setY(0 - ep.getR()) ;
				ep.setSpeed((int)(Math.random()*6) + 1) ;
				ep.changeImg() ;
				eps.add(ep) ;
			}
			else
			{
				ep.setY(ep.getY() + ep.getSpeed()) ;
			}
		}
	}
	
	public void hit(Hero1 hero)
	{
		for(int i = 0; i < eps.size(); i++)
		{
			EnemyPlane1 ep = eps.get(i) ;
			
			/*战机与敌机碰撞——生命减少*/
			boolean b = (ep.isHit(hero) ) ;
			if(b == true) 
			{
				eps.remove(i) ;
				//  新敌机
				ep.setX((int)(Math.random()*bgX)) ;
				ep.setY(0 - ep.getR()) ;
				ep.setSpeed((int)(Math.random()*6) + 1) ;
				ep.changeImg() ;
				eps.add(ep) ;
				
				hero.setBlood(hero.getBlood() - 30) ;
			}
		}
	}
	
	public void NewEnemy()
	{
		EnemyPlane1 ep=new EnemyPlane1();
		
		ep.setX((int)(Math.random()*bgX)) ;
		ep.setY(0 - ep.getR()) ;
		ep.setSpeed((int)(Math.random()*6) + 1) ;
		ep.changeImg() ;
		eps.add(ep) ;
	}
}
