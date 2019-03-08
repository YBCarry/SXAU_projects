package bean;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import State.Hero;






public class BloodbagArray {
	
	/*利用获取屏幕对象类Dimension，获得默认工具包调用获取屏幕大小方法*/	
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*声明背景边界*/
	private int bgX  = dim.width;
	private int bgY  = dim.height;
	
	/*定义血包子弹队列*/
	private ArrayList<Bloodbag> bgs = new ArrayList<Bloodbag>() ;
	
	public int bloodbagspeed = 15 ; //血包速度 
	public int bloodbagnum = 3 ; //血包数量 
	
	public int getNum()
	{
		return bgs.size() ;
	}
	public Bloodbag get(int i)
	{
		return bgs.get(i) ;
	}
	public void remove(int i)
	{
		bgs.remove(i) ;
	}
	public void draw(Graphics g)
	{
		for(int i = 0; i < bgs.size(); i++) 
		{
			bgs.get(i).paint(g) ;
		}
	}
	
	/*血包初始化*/
	public void newBloodbag()
	{
		if(bgs.size() >= bloodbagnum)
			return ;
		
		Bloodbag bloodbag = new Bloodbag() ;
		/*血包随机出现*/
		bloodbag.setX((int)(Math.random()*bgX)) ; //血包横坐标
		
		bloodbag.setR(15) ; //血包感应半径
		/*血包速度*/
		bloodbag.setSpeed(bloodbagspeed) ;
		
		bloodbag.setY(-bloodbag.getR()) ; //血包纵坐标
		bgs.add(bloodbag) ;
		
	}
	
	/*血包移动函数*/
	public void move()
	{
		for(int k = 0; k < bgs.size(); k++)
		{
			Bloodbag bloodbag = bgs.get(k) ;
			if(bloodbag.getY() >= bgY) 
			{
				bgs.remove(k) ;				
			}
			else
			{
				bloodbag.setY(bloodbag.getY() + bloodbag.getSpeed()) ;
			}
		}
	}
	
	/*多个血包与战机碰撞*/
	public void hit(Hero hero)
	{
		for(int i = 0; i < bgs.size(); i++)
		{
			Bloodbag tmp = bgs.get(i) ;
			
			boolean b = (tmp.isHit(hero) ) ;
			if(b == true) 
			{
				bgs.remove(i) ;
				if(hero.getBlood() < 200)
				{
					hero.setBlood(hero.getBlood() + 15) ;
				}
			}
		}
	}
	
}
