package Legend;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

public class BBulletArray {
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*声明背景边界*/
	private int bgX  = dim.width;
	private int bgY  = dim.height;
	public int bbulletspeed=5;

	private ArrayList<BBullet> bs = new ArrayList<BBullet>() ;
	
	public void draw(Graphics g)
	{
		/*绘制Boss子弹*/
		for(int i = 0; i < bs.size(); i++) 
		{
			bs.get(i).paint(g) ;
		}
	}
	
	public void move()
	{
		/*Boss子弹*/
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
	
	/*Boss子弹*/
	public void newBossBullet(Boss boss) {
		BBullet bbullet = new BBullet() ;
		bbullet.setR(30) ; //子弹感应半径
		bbullet.setX(boss.getX()) ; //子弹横坐标
		bbullet.setY(boss.getY()) ; //子弹纵坐标
		bs.add(bbullet) ;		
	}
	
	/*多个Boss子弹与战机碰撞——战机血量减少*/
	public void hit(Hero1 hero)
	{
		for(int i = 0; i < bs.size(); i++)
		{
			BBullet bullet = bs.get(i) ;
			
				boolean b = bullet.isHit(hero) ;				
				if(b == true)
				{
					bs.remove(i) ;
					
					///////// hero blood
					hero.setBlood(hero.getBlood()-5);
					
				}
		}
	}
	
}
