package Legend;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

public class EnemyArray {
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*���������߽�*/
	private int bgX  = dim.width;
	private int bgY  = dim.height;

	/*�����л�����*/
	private int epNum = 14 ;

	/*����л�����*/
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
		/*�л���ʼ��*/
		for(int i = 0; i < epNum; i++) 
		{
			EnemyPlane1 ep = new EnemyPlane1() ;
			/*�л��������*/
			float eX = (float)(Math.random()*bgX) ;
			float eY = (float)(Math.random()*bgX) ;
			
			ep.setX((int)eX) ; //�л�������
			ep.setY((int)eY) ; //�л�������
			ep.setR(30) ; //�л���Ӧ�뾶
			
			/*�л��ٶ����*/
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
			
			/*ս����л���ײ������������*/
			boolean b = (ep.isHit(hero) ) ;
			if(b == true) 
			{
				eps.remove(i) ;
				//  �µл�
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
