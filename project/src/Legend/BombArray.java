package Legend;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

public class BombArray {
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*声明背景边界*/
	private int bgX  = dim.width;
	private int bgY  = dim.height;
	
	private ArrayList<Bomb> bbs = new ArrayList<Bomb>() ;
	
	public int bombspeed=5;  
	public int bombnum=5;
	
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
	public void draw(Graphics g)
	{
		for(int i = 0; i < bbs.size(); i++) 
		{
			bbs.get(i).paint(g) ;
		}
	}
	
	public void newBomb()
	{
		if(bbs.size()>=bombnum)
			return;
		
		Bomb bomb=new Bomb();
		/*炸弹随机出现*/
		bomb.setX((int)(Math.random()*bgX)) ; //炸弹横坐标
		
		bomb.setR(30) ; //炸弹感应半径
		/*炸弹速度*/
		bomb.setSpeed(bombspeed) ;
		
		bomb.setY(-bomb.getR()) ; //炸弹纵坐标
		bbs.add(bomb) ;
		
	}
	
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
	
	// 多个炸弹 和 hero
	public void hit(Hero1 hero)
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
