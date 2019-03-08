package bean;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import State.Hero;






public class BloodbagArray {
	
	/*���û�ȡ��Ļ������Dimension�����Ĭ�Ϲ��߰����û�ȡ��Ļ��С����*/	
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*���������߽�*/
	private int bgX  = dim.width;
	private int bgY  = dim.height;
	
	/*����Ѫ���ӵ�����*/
	private ArrayList<Bloodbag> bgs = new ArrayList<Bloodbag>() ;
	
	public int bloodbagspeed = 15 ; //Ѫ���ٶ� 
	public int bloodbagnum = 3 ; //Ѫ������ 
	
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
	
	/*Ѫ����ʼ��*/
	public void newBloodbag()
	{
		if(bgs.size() >= bloodbagnum)
			return ;
		
		Bloodbag bloodbag = new Bloodbag() ;
		/*Ѫ���������*/
		bloodbag.setX((int)(Math.random()*bgX)) ; //Ѫ��������
		
		bloodbag.setR(15) ; //Ѫ����Ӧ�뾶
		/*Ѫ���ٶ�*/
		bloodbag.setSpeed(bloodbagspeed) ;
		
		bloodbag.setY(-bloodbag.getR()) ; //Ѫ��������
		bgs.add(bloodbag) ;
		
	}
	
	/*Ѫ���ƶ�����*/
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
	
	/*���Ѫ����ս����ײ*/
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
