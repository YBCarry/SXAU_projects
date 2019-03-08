package State;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import controler.GameOver;



public class Hero1stState extends HeroState {
	
	public Hero1stState(Hero hero){
		this.hero = hero;
	}
	public Hero1stState(HeroState state){
		this.hero =state.hero;
	}

	@Override
	public void check() {
		// TODO Auto-generated method stub
		int score = hero.getScore();
		if(score > 1000 && hero.getLevel() == 1){
			JOptionPane.showMessageDialog(null, "Second Level ~~~");
			//由第一关到第二关
			hero.setCurrentState(new Hero2ndState(this));
			hero.setBlood(200);
			hero.setLevel(2);
		}
	}

}