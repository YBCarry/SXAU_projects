package State;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import memento.Memento;

//单件模式
public  class Hero {  //备忘录模式的原发者
	
	private int x ; //战机横坐标
	private int y ; //战机纵坐标
	private int r ; //战机感应半径
	private int allBlood ; //总血量
	private int blood ; //当前血量
	private int score ; //得分
	//private int level ; //等级
	//private Image heroImg = null  ;  //战机图片
	
	
	/*单例模式1*/
	private static Hero hero = new Hero() ;
	private Hero () {}
	/*提供全局访问点*/
	public static Hero getHero () {
		
		return hero ;
	}
	
	
	/*备忘录模式*/
	public Memento CreateMemento() {
		
		Memento me = new Memento() ;
		
		me.setScore(hero.getScore()) ;
		me.setLevel(hero.getLevel()) ;
		
		return me ;
	}
	
	public void restoreMemento(Memento me) {
		
		score = me.getScore() ;
		level = me.getLevel() ;
	}
	
	
	
	
	private Dimension di = Toolkit.getDefaultToolkit().getScreenSize() ; 

	/*读入战机图片*/
	private Image heroImg = new ImageIcon("img/hero.png").getImage() ;

	/*战机等级*/
	private int level = 1 ; 
	
	private HeroState currentState = new Hero1stState(this) ;  //设置初始类      
	
	/*get、set实现*/
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public HeroState getCurrentState() {
		return currentState;
	}
	public void setCurrentState(HeroState currentState) {
		this.currentState = currentState;
	}
	
	public int getBlood() {
		return blood;
	}
	public void setBlood(int blood) {
		this.blood = blood;
	}

	public int getAllBlood() {
		return allBlood ;
	}

	public void setAllBlood(int allBlood) {
		this.allBlood = allBlood ;
	}

	    public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
		currentState.check();//检测调用状态
	}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getR() {
			return r;
		}
		public void setR(int r) {
			this.r = r;
		}
//		public Hero(int x, int y, int r) {
//			//super();
//			this.x = x;
//			this.y = y;
//			this.r = r;
////			/*读入战机图片*/
////			heroImg = new ImageIcon("img/hero.png").getImage() ;
////		
//		}

		
		public void paint(Graphics g){ 
//			Color c = g.getColor();
//			g.setColor(Color.blue);
//			//g.fillOval(x-r,y-r,r*2,r*2);
//			g.drawImage(img, x-r-6, y-r, null);
//			g.setColor(Color.orange);
//			g.fillRect(10, 20, allBlood, 50);
//			g.setColor(Color.red);
//			g.fillRect(10, 20, blood, 50);
//			g.setFont(new Font("宋体",Font.BOLD,30));
//			g.drawString("分数： "+ this.score, 0, 100);
//			g.setColor(c);
//			g.drawString("等级： "+ this.level,0, 300);
			

			/*初始化战机位置*/
			g.drawImage(heroImg,Hero.this.getX() - r, Hero.this.getY() - r, null) ;     
			
			/*血条*/
			g.setColor(Color.WHITE) ;
			g.fillRect(40, 40, allBlood, 30) ; //总血量
			
			g.setColor(Color.RED) ;
			g.fillRect(40, 40, blood, 30) ; //当前血量
			
			/*得分、等级记录*/
			g.setColor(Color.WHITE) ;
			g.setFont(new Font("Times New Roman", Font.BOLD, 30)) ;
			g.drawString("Score:" + score, 1700, 40) ;
			g.drawString("Lv:" + level, 1700, 80);

			
		}
		
		/*定义战机运动方式(键盘控制)*/
		public void moveUp() {
			y -= 25 ;
			if (y < 0)
				y += 10 ;
		}

		public void moveDown() {
			y += 25 ;
			if (y > di.height - 40)
				y -= 10 ;
		}

		public void moveLeft() {
			x -= 25 ;
			if (x < 0)
				x += 10 ;
		}

		public void moveRight() {
			x += 25 ;
			if (x > di.width - 40)
				x -= 10 ;
		}
}


