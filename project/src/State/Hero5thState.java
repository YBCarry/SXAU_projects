package State;

import javax.swing.JOptionPane;

import controler.GameWin;


public class Hero5thState extends HeroState {
	
	public Hero5thState(HeroState state){
		hero = state.hero;
	}
	public void check(){
		int score =hero.getScore();
		if(score>5000){
//			GameWin gw = new GameWin() ;
		}
		//½µ¼¶
		if(hero.getBlood() < 0 && hero.getLevel() == 4){
			JOptionPane.showMessageDialog(null, "too bad");
			hero.setCurrentState(new Hero4thState(this));
			hero.setBlood(200);
			hero.setLevel(4);
			hero.setScore(3000);
		}
	}


}
