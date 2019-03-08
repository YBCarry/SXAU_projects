package State;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import memento.Memento;

//����ģʽ
public  class Hero {  //����¼ģʽ��ԭ����
	
	private int x ; //ս��������
	private int y ; //ս��������
	private int r ; //ս����Ӧ�뾶
	private int allBlood ; //��Ѫ��
	private int blood ; //��ǰѪ��
	private int score ; //�÷�
	//private int level ; //�ȼ�
	//private Image heroImg = null  ;  //ս��ͼƬ
	
	
	/*����ģʽ1*/
	private static Hero hero = new Hero() ;
	private Hero () {}
	/*�ṩȫ�ַ��ʵ�*/
	public static Hero getHero () {
		
		return hero ;
	}
	
	
	/*����¼ģʽ*/
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

	/*����ս��ͼƬ*/
	private Image heroImg = new ImageIcon("img/hero.png").getImage() ;

	/*ս���ȼ�*/
	private int level = 1 ; 
	
	private HeroState currentState = new Hero1stState(this) ;  //���ó�ʼ��      
	
	/*get��setʵ��*/
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
		currentState.check();//������״̬
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
////			/*����ս��ͼƬ*/
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
//			g.setFont(new Font("����",Font.BOLD,30));
//			g.drawString("������ "+ this.score, 0, 100);
//			g.setColor(c);
//			g.drawString("�ȼ��� "+ this.level,0, 300);
			

			/*��ʼ��ս��λ��*/
			g.drawImage(heroImg,Hero.this.getX() - r, Hero.this.getY() - r, null) ;     
			
			/*Ѫ��*/
			g.setColor(Color.WHITE) ;
			g.fillRect(40, 40, allBlood, 30) ; //��Ѫ��
			
			g.setColor(Color.RED) ;
			g.fillRect(40, 40, blood, 30) ; //��ǰѪ��
			
			/*�÷֡��ȼ���¼*/
			g.setColor(Color.WHITE) ;
			g.setFont(new Font("Times New Roman", Font.BOLD, 30)) ;
			g.drawString("Score:" + score, 1700, 40) ;
			g.drawString("Lv:" + level, 1700, 80);

			
		}
		
		/*����ս���˶���ʽ(���̿���)*/
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


