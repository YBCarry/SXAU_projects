package controler;
//package Controler;
//
//import java.awt.Dimension;
//import java.awt.Image;
//import java.awt.event.KeyAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseMotionAdapter;
//import java.util.ArrayList;
//import java.util.Iterator;
//
//import javax.swing.ImageIcon;
//import javax.swing.JPanel;
//
//import model.Hero;
//
//public class MyPanel2 extends JPanel implements Runnable{
//	private int bgX;
//	private int bgY;
//	
//	//������4������by  bby
//	
//	private int by;
//	private int bby;
//	
//	//Plane p = new Plane(200, 200, 50);
//    //	Plane[] planes = new Plane[10];
//	ArrayList<Plane> planes = new ArrayList<Plane>();
//	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
//    private Image img = new ImageIcon("img/GameOver.jpg").getImage();
//	Hero hero;//����һ��Ӣ����Ķ���
//	Image bgImg = new ImageIcon("img/bg"+(int)(Math.random()*5+1) + ".jpg").getImage();
//	//Bullet bullet;//����һ���ӵ���Ķ���
//	@Override
//	
//	
//	public void paint(Graphics g) { //��Plane��paint��һ��
//		// TODO Auto-generated method stub
//		super.paint(g);//������һ�λ���ͼ��
//		//p.paint(g);
//		
//		//������
//		//������
//		this.inintbg();
//		g.drawImage(bgImg, 0, by,bgX,bgY, null);
//		g.drawImage(bgImg, 0, bby,bgX,bgY, null);
//		move();
//		
//		/*g.drawImage(bgImg, 0, 0, bgX, bgY, null);*/
//		//���л�
//		//��һ��
//		for(int i=0;i<planes.size();i++){
//			Plane p = planes.get(i);
//			p.paint(g);
//		}
//
//		for(int i=0;i<bullets.size();i++){
//			Bullet bullet = bullets.get(i);
//			bullet.paint(g);
//		}
//		//��hero
//		//g.setColor(Color.black);
//		hero.paint(g);
//		if(hero.getBlood()==0){
//			g.drawImage(img, 0, 0, bgX, bgY, null);
//		}
//		//�ڶ���
//		Iterator<Plane> it = planes.iterator();
//		while(it.hasNext()){
//			Plane p = it.next();
//			p.paint(g);
//		}
//
//		
//	}
//     //�л�����
//	private void initbg() {
//		int bgNum =hero.getLevel()%5+1;
//		bgImg = new ImageIcon("img/bg"+bgNum+".jpg").getImage();
//		
//		
//	}
//	
//	//������
//	public void move(){
//		by +=3;
//		bby +=3;
//		if(by>bgY){
//			by = bby-bgY;
//			
//		}
//		if(bby>bgY){
//			bby = by-bgY;
//			
//		}
//		
//	}
//
//	//�л�����
//	public void inintbg(){
//		int bgNum = hero.getLevel()%5+1;
//		bgImg = new ImageIcon("img/bg"+bgNum+".jpg").getImage();
//		
//		
//	}
//	public MyPanel2(Dimension dim) {
//		bgX = dim.width;
//		bgY = dim.height;
//		by=0;
//		bby=by-bgY;
//		
//		//this.setBackground(Color.gray);
//		
//		for(int i=0;i<10;i++){
//			Plane p = new Plane((int)(Math.random()*(bgX-60)+30),(int)(Math.random()*(bgY*0.6)),30);
//			p.setSpeed((int)(Math.random()*5+1));
//			planes.add(p);
//		}
//		
//		hero = new Hero(bgX/2,bgY-60,50);//Ӣ�۶���ĳ�ʼλ��
//		this.addMouseMotionListener(new MyMonitor());//���һ�����������������
//		this.addKeyListener(new MyMonitor2());
//		this.setFocusable(true);
//		new Thread(this).start();
//	}
//
//	@Override
//	public void run() {//ʹԲ��������
//		// TODO Auto-generated method stub
//		int y;
//		int count = 0;
//		while(true){
//			if(hero.getBlood()>0){
//			if(count % 4==0){
//				Bullet bullet = new Bullet(hero.getX(),hero.getY()-hero.getR(),50);
//				bullets.add(bullet);
//			}
//			count++;
//			//�÷ɻ���
//			for(int i=0;i<planes.size();i++){
//				Plane p = planes.get(i);
//				y = p.getY()+p.getSpeed();
//				p.setY(y);
//				if(y>bgY){//Բ����Խ�������
//					p.setY(0);
//					p.setX((int)(Math.random()*(bgX-60)+30));
//					hero.setScore(hero.getScore()-10);
//				}
//				if(isHit(p, hero)){
//					p.setY(0);
//					p.setX((int)(Math.random()*(bgX-60)+30));
//					hero.setBlood(hero.getBlood()-10);
//				}
//			}
//			//���ӵ���
//			for(int i=0;i<bullets.size();i++){
//				Bullet b = bullets.get(i);
//				y = b.getY()-20;
//				if(y<0){
//					bullets.remove(i);
//				}
//				b.setY(y);
//				for(int j=0;j<planes.size();j++){
//					Plane p = planes.get(j);
//					if(isHit(b,p)){
//						bullets.remove(b);
//						p.setY(0);
//						p.setX((int)(Math.random()*(bgX-60)+30));
//						p.change();
//						hero.setScore(hero.getScore()+10);
//					}
//				}
//				
//			}
//			//bullet.setY(bullet.getY()-1);
//			
//			try {
//				Thread.sleep(10);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			repaint();
//		}
//		
//		}
//	}
//	//�жϷɻ���hero�Ƿ���ײ
//	private boolean isHit(Plane p, Hero hero2) {
//		// TODO Auto-generated method stub
//		if(((hero.getX()-p.getX())*(hero.getX()-p.getX())+(hero.getY()-p.getY())*(hero.getY()-p.getY()))
//				<=((hero.getR()+p.getR())*(hero.getR()+p.getR()))){
//			return true;
//		}
//		return false;
//	}
//
//
//	//�ж��ӵ��Ƿ���зɻ�
//	private boolean isHit(Bullet b, Plane p) {
//		// TODO Auto-generated method stub
//		if(((b.getX()-p.getX())*(b.getX()-p.getX())+(b.getY()-p.getY())*(b.getY()-p.getY()))
//				<=((b.getR()+p.getR())*(b.getR()+p.getR()))){
//			return true;
//		}
//		return false;
//	}
//
//	private class MyMonitor extends MouseMotionAdapter{
//
//		@Override
//		public void mouseDragged(MouseEvent e) {//����϶�����
//			// TODO Auto-generated method stub
//			super.mouseDragged(e);
//			if(hero.getBlood()>0){
//				hero.setX(e.getX());
//				hero.setY(e.getY());
//			}
//		}
//
////		@Override
////		public void mouseMoved(MouseEvent e) {//����ƶ�����
////			// TODO Auto-generated method stub
////			//super.mouseDragged(arg0);
////			hero.setX(e.getX());
////			hero.setY(e.getY());
////			
////		}
//		
//		
//	}
//	private class MyMonitor2 extends KeyAdapter{
//
//		@Override
//		public void keyPressed(KeyEvent e) {
//			// TODO Auto-generated method stub
//			super.keyPressed(e);
//			int code = e.getKeyCode();
//			if(code==KeyEvent.VK_LEFT){
//				hero.setX(hero.getX()-20);
//			}else if(code==KeyEvent.VK_RIGHT){
//				hero.setX(hero.getX()+20);
//			}else if(code==KeyEvent.VK_UP){
//				hero.setY(hero.getY()-20);
//			}else if(code==KeyEvent.VK_DOWN){
//				hero.setY(hero.getY()+20);
//			}
//		}
//		
//	}
//		
//}
//
//	
//
//	
//	
//
//
//
//
//
//
//
//
//
//	
//	
