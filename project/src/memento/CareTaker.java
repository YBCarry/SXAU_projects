package memento;

import controler.DB_Operater;

/**
 * 
 * @author dsq
 *
 *看守人
 */

public class CareTaker {
	
	private Memento me = new Memento() ;
	
	 /*数据库相关参数*/
	 private DB_Operater db_Operater ;
	 private String name ;
	 
	 public Memento getMemento(String name) {
		 
		 /*数据库*/
		 this.name = name ;
		 db_Operater = new DB_Operater() ;
		 
		/*1.去数据库获取战机的score和level*/
		int score = db_Operater.get(name, "grade") ;
		int level = db_Operater.get(name, "level") ;
		
		/*2.把获取到的score和level封装到Memento对象中*/
		me.setScore(score) ;
		me.setLevel(level) ;

		return me ;
	}
	
	/*保存备忘录*/
	public void saveMemento(String name, Memento me) {
		
		 /*数据库*/
		 this.name = name ;
		 db_Operater = new DB_Operater() ;
		 
		db_Operater.update(name, me.getLevel(), me.getScore()) ;
		System.out.println("update ...");
	}
}
