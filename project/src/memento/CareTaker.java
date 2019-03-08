package memento;

import controler.DB_Operater;

/**
 * 
 * @author dsq
 *
 *������
 */

public class CareTaker {
	
	private Memento me = new Memento() ;
	
	 /*���ݿ���ز���*/
	 private DB_Operater db_Operater ;
	 private String name ;
	 
	 public Memento getMemento(String name) {
		 
		 /*���ݿ�*/
		 this.name = name ;
		 db_Operater = new DB_Operater() ;
		 
		/*1.ȥ���ݿ��ȡս����score��level*/
		int score = db_Operater.get(name, "grade") ;
		int level = db_Operater.get(name, "level") ;
		
		/*2.�ѻ�ȡ����score��level��װ��Memento������*/
		me.setScore(score) ;
		me.setLevel(level) ;

		return me ;
	}
	
	/*���汸��¼*/
	public void saveMemento(String name, Memento me) {
		
		 /*���ݿ�*/
		 this.name = name ;
		 db_Operater = new DB_Operater() ;
		 
		db_Operater.update(name, me.getLevel(), me.getScore()) ;
		System.out.println("update ...");
	}
}
