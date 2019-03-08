package State;

import javax.swing.JOptionPane;


public  class Hero2ndState extends HeroState {
	public Hero2ndState(HeroState state){
		hero = state.hero;
	}

	@Override
	public void check() {
		// TODO Auto-generated method stub
		int score = hero.getScore();
		if(score>2000){
			JOptionPane.showMessageDialog(null, "Third Level ~~~");
			//第二关到第三关			
			hero.setCurrentState(new Hero3rdState(this));
			hero.setBlood(200);
			hero.setLevel(3);
		}
		
		//降级
		else if(hero.getBlood() < 0){
			JOptionPane.showMessageDialog(null, "too bad");
			hero.setCurrentState(new Hero1stState(this));
			hero.setBlood(200);
			hero.setLevel(1);
			hero.setScore(0);
		}

	}

}

