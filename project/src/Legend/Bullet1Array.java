package Legend;

import java.awt.Graphics;
import java.util.ArrayList;

public class Bullet1Array {
	/*����ս���ӵ�����*/
	private ArrayList<Bullet1> bts = new ArrayList<Bullet1>() ;


	public void draw(Graphics g)
	{
		for(int j = 0; j < bts.size(); j++) 
		{
			bts.get(j).paint(g) ;
		}
	}
	
	public void move()
	{
		for(int i = 0; i < bts.size(); i++)
		{
			Bullet1 bullet = bts.get(i) ;
			if(bullet.getY() <= 0 - bullet.getR())
			{
				bts.remove(i) ;
				//bts.add(bullet) ;
			}
			else
			{
				bullet.setY(bullet.getY() - 10) ;
			}
			
		}
	}

	/*ս���ӵ�*/
	public void newBullet(Hero1 hero) {
		Bullet1 bullet = new Bullet1() ;
		bullet.setR(50) ; //�ӵ���Ӧ�뾶
		bullet.setX(hero.getX()) ; //�ӵ�������
		bullet.setY(hero.getY()) ; //�ӵ�������
		bts.add(bullet);		
	}
	
	/*���ս���ӵ���л���ײ */
	public void hit(Hero1 hero,EnemyArray enemyArray)
	{
		for(int i = 0; i < bts.size(); i++)
		{
			Bullet1 bullet = bts.get(i) ;
			/*ս���ӵ���л���ײ������������*/
			for(int j = 0; j < enemyArray.getNum(); j++)
			{
				EnemyPlane1 ep = enemyArray.get(j) ;
				boolean b = bullet.isHit(ep) ;
				
				if(b == true)
				{
					bts.remove(i) ;
					enemyArray.remove(j);
					enemyArray.NewEnemy();				
					///////// hero score
					hero.setScore(hero.getScore() + 50) ;
					
				}
			}
		}
	}
	
	/*���ս���ӵ���BOSS��ײ */
	public void hit(Hero1 hero,Boss boss)
	{
		for(int i = 0; i < bts.size(); i++)
		{
			Bullet1 bullet = bts.get(i) ;
			
				boolean b = bullet.isHit(boss) ;
				
				if(b == true)
				{
					bts.remove(i) ;
					boss.setBlood(boss.getBlood() - 10) ;
					///////// hero score
					hero.setScore(hero.getScore() + 50) ;
					
				}
		}
	}
	
	/*���ս���ӵ���ը����ײ */
	public void hit(Hero1 hero,BombArray bombArray)
	{
		for(int i = 0; i < bts.size(); i++)
		{
			Bullet1 bullet = bts.get(i) ;
			
			for(int j=0;j<bombArray.getNum();j++)
			{
				boolean b = bullet.isHit(bombArray.get(j)) ;
				
				if(b == true)
				{
					bts.remove(i) ;
					bombArray.remove(j);
					// new bomb
					bombArray.newBomb();
					// hero score
					hero.setScore(hero.getScore() + 100) ;
				}
			}
		}
	}
}
