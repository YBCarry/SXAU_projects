package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import State.Hero;

import memento.CareTaker;

import controler.DB_Operater;


public class Start extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 293145988993785462L ; //序列化
	
	/*按钮*/
	private JButton button_start = new JButton() ;  //开始游戏
	private JButton button_end = new JButton() ;  //结束游戏
	private JButton button_contanue = new JButton() ;  //继续游戏
	
	/*标签*/
	private JLabel jLabel = new JLabel("Carry  版权所有") ; //版权信息
	
	private int level, grade, time ;
	private String name ;
	
	private Hero hero  = Hero.getHero() ;
	
	public Start(int level, int grade, String name, int time) {
		
		this.level = level ;
		this.grade = grade ;
		this.name = name ;
		this.time = time ;
		
		DB_Operater db_Operater = new DB_Operater() ;
		
		JFrame frame = new JFrame("Read for Play") ;
		/*利用获取屏幕对象类Dimension，获得默认工具包调用获取屏幕大小方法*/
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
		
		frame.setBounds((dim.width - 300) / 2, (dim.height - 400) / 2, 400, 200) ;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		
		button_start.setText("开始游戏") ;
		button_start.addActionListener(this) ;  //为开始游戏按钮增加动作监听器
		button_contanue.setText("继续游戏") ;
		button_contanue.addActionListener(this) ;  //为开始游戏按钮增加动作监听器
		button_end.setText("结束游戏") ;
		button_end.addActionListener(this) ;  //为结束游戏按钮增加动作监听器
		
		JPanel panel1 = new JPanel() ;
		JPanel panel2 = new JPanel() ;
		JPanel panel3 = new JPanel() ;
		JPanel panel4 = new JPanel() ;
		JPanel panel5 = new JPanel() ;
		JPanel panel6 = new JPanel() ;
		
		JTextField Level = new JTextField() ;
		JTextField Grade = new JTextField() ;
		JTextField Time = new JTextField() ;
		
		Level.setEnabled(false) ;
		Grade.setEnabled(false) ;
		Time.setEnabled(false) ;
		
		Level.setText(level + "") ;
		Grade.setText(grade + "") ;
		Time.setText(time + "秒") ;
		
		panel1.add(button_start) ;
		panel1.add(button_contanue) ;
		panel1.add(button_end) ;
		panel2.add(new Label("等级:")) ;
		panel2.add(Level) ;
		panel3.add(new Label("成绩:")) ;
		panel3.add(Grade) ;
		panel4.add(new Label("上次游戏累计在线:")) ;
		panel4.add(Time) ;
		panel6.add(jLabel) ;
		
		panel5.add(panel2, BorderLayout.NORTH) ;
		panel5.add(panel3, BorderLayout.CENTER) ;
		panel5.add(panel4, BorderLayout.SOUTH) ;
		
		frame.add(panel1, BorderLayout.NORTH) ;
		frame.add(panel5, BorderLayout.CENTER) ;
//		frame.add(panel3, BorderLayout.CENTER) ;
//		frame.add(panel4, BorderLayout.SOUTH) ;
		frame.add(panel6, BorderLayout.SOUTH) ;
		
		frame.setResizable(false) ;  //窗体大小不可改变
		frame.setVisible(true) ;
		
	}
	
	public void actionPerformed(ActionEvent e) {  //游戏开始
		
		if(e.getSource() == button_start) {
			
			new Started(level, grade, name) ;
		}
		
		if(e.getSource() == button_contanue) {
			
			/*进行备忘录测试*/
			/*添加备忘录*/
			CareTaker ct = new CareTaker() ;
			ct.saveMemento(name, Hero.getHero().CreateMemento()) ;
			new Started(hero.getLevel(), hero.getScore(), name) ;
			ct.getMemento(name) ;

		}
		
		
		if(e.getSource() == button_end) {  //游戏结束
			
			/*进行备忘录测试*/
			/*添加备忘录*/
			CareTaker ct = new CareTaker() ;
			ct.saveMemento(name, Hero.getHero().CreateMemento()) ;
			ct.getMemento(name) ;
			
			System.exit(0) ;
				
		}
	}

}
