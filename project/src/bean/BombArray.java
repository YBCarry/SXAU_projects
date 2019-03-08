package bean;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import State.Hero;




public class BombArray {
	/*利用获取屏幕对象类Dimension，获得默认工具包调用获取屏幕大小方法*/	
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*声明背景边界*/
	private int bgX  = dim.width;
	private int bgY  = dim.height;
	
	/*定义炸弹队列*/
	private ArrayList<Bomb> bbs = new ArrayList<Bomb>() ;
	
	public int bombspeed = 20 ; //炸弹速度 
	public int bombnum = 6 ; //炸弹数量
	
	public int getNum()
	{
		return bbs.size();
	}
	public Bomb get(int i)
	{
		return bbs.get(i);
	}
	public void remove(int i)
	{
		bbs.remove(i);
	}
	
	/*绘制炸弹*/
	public void draw(Graphics g)
	{
		for(int i = 0; i < bbs.size(); i++) 
		{
			bbs.get(i).paint(g) ;
		}
	}
	
	/*炸弹初始化*/
	public void newBomb()
	{
		if(bbs.size() >= bombnum)
			return ;
		
		Bomb bomb = new Bomb() ;
		/*炸弹随机出现*/
		bomb.setX((int)(Math.random()*bgX)) ; //炸弹横坐标
		
		bomb.setR(30) ; //炸弹感应半径
		
		bomb.setSpeed(bombspeed) ; //炸弹速度
		
		bomb.setY(-bomb.getR()) ; //炸弹纵坐标
		bbs.add(bomb) ;
		
	}
	
	/*炸弹移动函数*/
	public void move()
	{
		for(int k = 0; k < bbs.size(); k++)
		{
			Bomb bomb = bbs.get(k) ;
			if(bomb.getY() >= bgY) 
			{
				bbs.remove(k) ;				
			}
			else
			{
				bomb.setY(bomb.getY() + bomb.getSpeed()) ;
			}
		}
	}
	
	/*多个炸弹与战机碰撞*/
	public void hit(Hero hero)
	{
		for(int i = 0; i < bbs.size(); i++)
		{
			Bomb tmp = bbs.get(i) ;
			
			boolean b = (tmp.isHit(hero) ) ;
			if(b == true) 
			{
				bbs.remove(i) ;
				hero.setBlood(hero.getBlood() - 40) ;
			}
		}
	}
}
