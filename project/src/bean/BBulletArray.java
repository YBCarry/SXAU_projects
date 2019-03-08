package bean;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import State.Hero;




public class BBulletArray {
	
	/*利用获取屏幕对象类Dimension，获得默认工具包调用获取屏幕大小方法*/
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*声明背景边界*/
	private int bgX  = dim.width ;
	private int bgY  = dim.height ;
	public int bbulletspeed=10 ; //Boss子弹速度

	/*定义Boss子弹队列*/
	private ArrayList<BBullet> bs = new ArrayList<BBullet>() ;
	
	/*绘制Boss子弹*/
	public void draw(Graphics g)
	{
		for(int i = 0; i < bs.size(); i++) 
		{
			bs.get(i).paint(g) ;
		}
	}
	
	/*Boss子弹移动函数*/
	public void move()
	{
		for(int j = 0; j < bs.size(); j++)
		{
			BBullet bbullet = bs.get(j) ;
			if(bbullet.getY() >= bgY) 
			{
				bs.remove(j) ;
			}
			else
			{
				bbullet.setY(bbullet.getY() + bbulletspeed) ;
			}
		}
	}
	
	/*Boss子弹初始化*/
	public void newBossBullet(Boss boss) {
		BBullet bbullet = new BBullet() ;
		bbullet.setR(30) ; //子弹感应半径
		bbullet.setX(boss.getX()) ; //子弹横坐标
		bbullet.setY(boss.getY()) ; //子弹纵坐标
		bs.add(bbullet) ;		
	}
	
	/*多个Boss子弹与战机碰撞——战机血量减少10*/
	public void hit(Hero hero)
	{
		for(int i = 0; i < bs.size(); i++)
		{
			BBullet bullet = bs.get(i) ;
			
				boolean b = bullet.isHit(hero) ;				
				if(b == true)
				{
					bs.remove(i) ;
					
					hero.setBlood(hero.getBlood() - 10) ;
					
				}
		}
	}
	
}
