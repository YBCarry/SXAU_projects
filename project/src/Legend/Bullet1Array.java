package Legend;

import java.awt.Graphics;
import java.util.ArrayList;

public class Bullet1Array {
	/*定义战机子弹队列*/
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

	/*战机子弹*/
	public void newBullet(Hero1 hero) {
		Bullet1 bullet = new Bullet1() ;
		bullet.setR(50) ; //子弹感应半径
		bullet.setX(hero.getX()) ; //子弹横坐标
		bullet.setY(hero.getY()) ; //子弹纵坐标
		bts.add(bullet);		
	}
	
	/*多个战机子弹与敌机碰撞 */
	public void hit(Hero1 hero,EnemyArray enemyArray)
	{
		for(int i = 0; i < bts.size(); i++)
		{
			Bullet1 bullet = bts.get(i) ;
			/*战机子弹与敌机碰撞——分数增加*/
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
	
	/*多个战机子弹与BOSS碰撞 */
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
	
	/*多个战机子弹与炸弹碰撞 */
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
