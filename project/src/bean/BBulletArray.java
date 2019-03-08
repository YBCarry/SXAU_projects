package bean;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import State.Hero;




public class BBulletArray {
	
	/*���û�ȡ��Ļ������Dimension�����Ĭ�Ϲ��߰����û�ȡ��Ļ��С����*/
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*���������߽�*/
	private int bgX  = dim.width ;
	private int bgY  = dim.height ;
	public int bbulletspeed=10 ; //Boss�ӵ��ٶ�

	/*����Boss�ӵ�����*/
	private ArrayList<BBullet> bs = new ArrayList<BBullet>() ;
	
	/*����Boss�ӵ�*/
	public void draw(Graphics g)
	{
		for(int i = 0; i < bs.size(); i++) 
		{
			bs.get(i).paint(g) ;
		}
	}
	
	/*Boss�ӵ��ƶ�����*/
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
	
	/*Boss�ӵ���ʼ��*/
	public void newBossBullet(Boss boss) {
		BBullet bbullet = new BBullet() ;
		bbullet.setR(30) ; //�ӵ���Ӧ�뾶
		bbullet.setX(boss.getX()) ; //�ӵ�������
		bbullet.setY(boss.getY()) ; //�ӵ�������
		bs.add(bbullet) ;		
	}
	
	/*���Boss�ӵ���ս����ײ����ս��Ѫ������10*/
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
