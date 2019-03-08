package State;

import javax.swing.JOptionPane;

public  class Hero3rdState extends HeroState {
	
	public Hero3rdState(HeroState state){
		hero = state.hero;
	}
	public void check() {
		int score =hero.getScore();
		if(score>3000){
			JOptionPane.showMessageDialog(null, "Fourth Level ~~~ ");
			hero.setCurrentState(new Hero4thState(this));
			hero.setBlood(200);
			hero.setLevel(4);
		}
		//½µ¼¶
		else if(hero.getBlood()<0){
			JOptionPane.showMessageDialog(null, "too bad");
			hero.setCurrentState(new Hero2ndState(this));
			hero.setBlood(200);
			hero.setLevel(2);
			hero.setScore(1000);
		}
	}

}
