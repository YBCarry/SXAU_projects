package Legend;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

public class EPBullet1Array {
private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*���������߽�*/
	private int bgX  = dim.width;
	private int bgY  = dim.height;

	/*�����л��ӵ�����*/
	public int epNum=10 ;
	public int enemyid=0;
	
	public int epBulletDamage=5;

	/*����л��ӵ�����*/
	private ArrayList<EPBullet1> epbts = new ArrayList<EPBullet1>() ;
	
	/*�л��ӵ�*/
	public void newEPBullet(EnemyArray eparray) {
		
		System.out.println("newEPBullet size "+epbts.size());
		if(epbts.size()>=epNum)
			return;
		
		//ÿ����һ���ɻ����ӵ�
		EnemyPlane1 ep=eparray.get(enemyid);
		
		EPBullet1 epbullet = new EPBullet1() ;
		epbullet.setR(10) ; //�ӵ���Ӧ�뾶
		epbullet.setX(ep.getX()); //�ӵ�������
		epbullet.setY(ep.getY()) ; //�ӵ�������
	
		
		epbts.add(epbullet) ;

		//
		enemyid++;
		if(enemyid>=eparray.getNum())
			enemyid=0;
	}
	
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
	
	public void draw(Graphics g)
	{
		for(int k = 0; k < epbts.size(); k++) 
		{
			epbts.get(k).paint(g) ;
		}
	}
	
	// ����л��ӵ� �� hero
	public void hit(Hero1 hero)
	{
		for(int i = 0; i < epbts.size(); i++)
		{
			EPBullet1 tmp = epbts.get(i) ;
			
			boolean b = (tmp.isHit(hero) ) ;
			if(b == true) 
			{
				epbts.remove(i) ;
				hero.setBlood(hero.getBlood() - epBulletDamage) ;
			}
		}
	}
}
