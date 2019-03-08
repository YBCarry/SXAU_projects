package view;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 游戏的启动类
 * @author dsq
 *
 */
public class Start1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyFrame() ; //MyFrame实现
		
		/*背景音乐载入*/
		TestMusic sound = new TestMusic("music/6.wav") ;
		InputStream stream = new ByteArrayInputStream(sound.getSamples()) ;
		// play the sound
		sound.play(stream) ;
	}

}
