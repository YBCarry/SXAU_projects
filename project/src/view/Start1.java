package view;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * ��Ϸ��������
 * @author dsq
 *
 */
public class Start1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyFrame() ; //MyFrameʵ��
		
		/*������������*/
		TestMusic sound = new TestMusic("music/6.wav") ;
		InputStream stream = new ByteArrayInputStream(sound.getSamples()) ;
		// play the sound
		sound.play(stream) ;
	}

}
