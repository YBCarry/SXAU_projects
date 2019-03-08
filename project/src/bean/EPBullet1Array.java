package bean;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import State.Hero;




public class EPBullet1Array {
	
	/*利用获取屏幕对象类Dimension，获得默认工具包调用获取屏幕大小方法*/
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
	
	/*声明背景边界*/
	private int bgX  = dim.width;
	private int bgY  = dim.height;
	
	public int epNum = 10 ; //敌机子弹数量
	public int enemyid = 0 ; //敌机子弹ID

	/*定义敌机子弹队列*/
	private ArrayList<EPBullet1> epbts = new ArrayList<EPBullet1>() ;
	
	/*敌机子弹*/
	public void newEPBullet(EnemyPlane1Array eparray) {
		
//		System.out.println("newEPBullet size "+epbts.size());
		if(epbts.size() >= epNum)
			return;
		
		//每次让一个飞机发子弹
		EnemyPlane1 ep=eparray.get(enemyid);
		
		EPBullet1 epbullet = new EPBullet1() ;
		epbullet.setR(20) ; //子弹感应半径
		epbullet.setX(ep.getX()); //子弹横坐标
		epbullet.setY(ep.getY()) ; //子弹纵坐标
	
		
		epbts.add(epbullet) ;

		enemyid++;
		if(enemyid >= eparray.getNum())
		{
			enemyid = 0 ;
		}
	}
	
	/*敌机子弹移动函数*/
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
	
	/*绘制敌机子弹*/
	public void draw(Graphics g)
	{
		for(int k = 0; k < epbts.size(); k++) 
		{
			epbts.get(k).paint(g) ;
		}
	}
	
	/*多个敌机子弹与炸弹碰撞 */
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
