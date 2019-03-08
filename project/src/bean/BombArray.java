package bean;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import State.Hero;




public class BombArray {
	/*���û�ȡ��Ļ������Dimension�����Ĭ�Ϲ��߰����û�ȡ��Ļ��С����*/	
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*���������߽�*/
	private int bgX  = dim.width;
	private int bgY  = dim.height;
	
	/*����ը������*/
	private ArrayList<Bomb> bbs = new ArrayList<Bomb>() ;
	
	public int bombspeed = 20 ; //ը���ٶ� 
	public int bombnum = 6 ; //ը������
	
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
	
	/*����ը��*/
	public void draw(Graphics g)
	{
		for(int i = 0; i < bbs.size(); i++) 
		{
			bbs.get(i).paint(g) ;
		}
	}
	
	/*ը����ʼ��*/
	public void newBomb()
	{
		if(bbs.size() >= bombnum)
			return ;
		
		Bomb bomb = new Bomb() ;
		/*ը���������*/
		bomb.setX((int)(Math.random()*bgX)) ; //ը��������
		
		bomb.setR(30) ; //ը����Ӧ�뾶
		
		bomb.setSpeed(bombspeed) ; //ը���ٶ�
		
		bomb.setY(-bomb.getR()) ; //ը��������
		bbs.add(bomb) ;
		
	}
	
	/*ը���ƶ�����*/
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
	
	/*���ը����ս����ײ*/
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
