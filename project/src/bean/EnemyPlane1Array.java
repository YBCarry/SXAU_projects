package bean;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import State.Hero;




public class EnemyPlane1Array {
	
	/*���û�ȡ��Ļ������Dimension�����Ĭ�Ϲ��߰����û�ȡ��Ļ��С����*/
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*���������߽�*/
	private int bgX  = dim.width;
	private int bgY  = dim.height;

	/*����л�����*/
	private ArrayList<EnemyPlane1> eps = new ArrayList<EnemyPlane1>() ;
	
	private int epNum = 14 ; //�л�����
	
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
	
	/*�л�����*/
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
	
	/*���Ƶл�*/
	public void draw(Graphics g)
	{
		for(int i = 0; i < eps.size(); i++) 
		{
			eps.get(i).paint(g) ;
		}
	
	}
	
	/*�л��ƶ�����*/
	public void move()
	{
		for(int i = 0; i < eps.size(); i++)
		{
			EnemyPlane1 ep = eps.get(i) ;
			if(ep.getY() >= bgY) 
			{
				eps.remove(i) ;
				//�µл�
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
			
			/*ս����л���ײ������������40*/
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
