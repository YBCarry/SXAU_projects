package bean;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import State.Hero;




public class EnemyPlane1Array {
	
	/*利用获取屏幕对象类Dimension，获得默认工具包调用获取屏幕大小方法*/
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*声明背景边界*/
	private int bgX  = dim.width;
	private int bgY  = dim.height;

	/*定义敌机队列*/
	private ArrayList<EnemyPlane1> eps = new ArrayList<EnemyPlane1>() ;
	
	private int epNum = 14 ; //敌机数量
	
	public EnemyPlane1 get(int i)
	{
		return eps.get(i) ;
	}
	public int getNum()
	{
		return eps.size() ;
	}
	public void remove(int i)
	{
		eps.remove(i) ;
	}
	
	/*敌机函数*/
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
	
	/*绘制敌机*/
	public void draw(Graphics g)
	{
		for(int i = 0; i < eps.size(); i++) 
		{
			eps.get(i).paint(g) ;
		}
	
	}
	
	/*敌机移动函数*/
	public void move()
	{
		for(int i = 0; i < eps.size(); i++)
		{
			EnemyPlane1 ep = eps.get(i) ;
			if(ep.getY() >= bgY) 
			{
				eps.remove(i) ;
				//新敌机
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
	
	public void hit(Hero hero)
	{
		for(int i = 0; i < eps.size(); i++)
		{
			EnemyPlane1 ep = eps.get(i) ;
			
			/*战机与敌机碰撞——生命减少40*/
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
				
				hero.setBlood(hero.getBlood() - 40) ;
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
