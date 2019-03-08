package State;

import javax.swing.JOptionPane;

public class Hero4thState extends HeroState {

	public Hero4thState(HeroState state){
		hero = state.hero;
	}

	@Override
	public void check() {
		// TODO Auto-generated method stub
		int score = hero.getScore();
		if(score>4000){
			JOptionPane.showMessageDialog(null, "Fifth Level ~~~");
			//第四关到第五关			
			hero.setCurrentState(new Hero5thState(this));
			hero.setBlood(200);
			hero.setLevel(5);
		}
		//降级
		else if(hero.getBlood()<0){
			JOptionPane.showMessageDialog(null, "too bad");
			hero.setCurrentState(new Hero3rdState(this));
			hero.setBlood(200);
			hero.setLevel(3);
			hero.setScore(2000);
		}

	}

}
