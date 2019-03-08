package Legend;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

public class BBulletArray {
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*���������߽�*/
	private int bgX  = dim.width;
	private int bgY  = dim.height;
	public int bbulletspeed=5;

	private ArrayList<BBullet> bs = new ArrayList<BBullet>() ;
	
	public void draw(Graphics g)
	{
		/*����Boss�ӵ�*/
		for(int i = 0; i < bs.size(); i++) 
		{
			bs.get(i).paint(g) ;
		}
	}
	
	public void move()
	{
		/*Boss�ӵ�*/
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
	
	/*Boss�ӵ�*/
	public void newBossBullet(Boss boss) {
		BBullet bbullet = new BBullet() ;
		bbullet.setR(30) ; //�ӵ���Ӧ�뾶
		bbullet.setX(boss.getX()) ; //�ӵ�������
		bbullet.setY(boss.getY()) ; //�ӵ�������
		bs.add(bbullet) ;		
	}
	
	/*���Boss�ӵ���ս����ײ����ս��Ѫ������*/
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
