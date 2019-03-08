package bean;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import State.Hero;




public class EPBullet1Array {
	
	/*���û�ȡ��Ļ������Dimension�����Ĭ�Ϲ��߰����û�ȡ��Ļ��С����*/
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*���������߽�*/
	private int bgX  = dim.width;
	private int bgY  = dim.height;
	
	public int epNum = 10 ; //�л��ӵ�����
	public int enemyid = 0 ; //�л��ӵ�ID

	/*����л��ӵ�����*/
	private ArrayList<EPBullet1> epbts = new ArrayList<EPBullet1>() ;
	
	/*�л��ӵ�*/
	public void newEPBullet(EnemyPlane1Array eparray) {
		
//		System.out.println("newEPBullet size "+epbts.size());
		if(epbts.size() >= epNum)
			return;
		
		//ÿ����һ���ɻ����ӵ�
		EnemyPlane1 ep=eparray.get(enemyid);
		
		EPBullet1 epbullet = new EPBullet1() ;
		epbullet.setR(20) ; //�ӵ���Ӧ�뾶
		epbullet.setX(ep.getX()); //�ӵ�������
		epbullet.setY(ep.getY()) ; //�ӵ�������
	
		
		epbts.add(epbullet) ;

		enemyid++;
		if(enemyid >= eparray.getNum())
		{
			enemyid = 0 ;
		}
	}
	
	/*�л��ӵ��ƶ�����*/
	public void move()
	{
		for(int i = 0; i < epbts.size(); i++)
		{
			EPBullet1 bullet = epbts.get(i) ;
			if(bullet.getY() > bgY)
			{
				epbts.remove(i) ;				
			}
			else
			{
				bullet.setY(bullet.getY() + 10) ;
			}
			
		}
	}
	
	/*���Ƶл��ӵ�*/
	public void draw(Graphics g)
	{
		for(int k = 0; k < epbts.size(); k++) 
		{
			epbts.get(k).paint(g) ;
		}
	}
	
	/*����л��ӵ���ը����ײ */
	public void hit(Hero hero)
	{
		for(int i = 0; i < epbts.size(); i++)
		{
			EPBullet1 tmp = epbts.get(i) ;
			
			boolean b = (tmp.isHit(hero) ) ;
			if(b == true) 
			{
				epbts.remove(i) ;
				hero.setBlood(hero.getBlood() - 25) ;
			}
		}
	}
}
