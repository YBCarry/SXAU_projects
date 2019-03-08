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
	
	private static final long serialVersionUID = 293145988993785462L ; //���л�
	
	/*��ť*/
	private JButton button_start = new JButton() ;  //��ʼ��Ϸ
	private JButton button_end = new JButton() ;  //������Ϸ
	private JButton button_contanue = new JButton() ;  //������Ϸ
	
	/*��ǩ*/
	private JLabel jLabel = new JLabel("Carry  ��Ȩ����") ; //��Ȩ��Ϣ
	
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
		/*���û�ȡ��Ļ������Dimension�����Ĭ�Ϲ��߰����û�ȡ��Ļ��С����*/
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
		
		frame.setBounds((dim.width - 300) / 2, (dim.height - 400) / 2, 400, 200) ;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		
		button_start.setText("��ʼ��Ϸ") ;
		button_start.addActionListener(this) ;  //Ϊ��ʼ��Ϸ��ť���Ӷ���������
		button_contanue.setText("������Ϸ") ;
		button_contanue.addActionListener(this) ;  //Ϊ��ʼ��Ϸ��ť���Ӷ���������
		button_end.setText("������Ϸ") ;
		button_end.addActionListener(this) ;  //Ϊ������Ϸ��ť���Ӷ���������
		
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
		Time.setText(time + "��") ;
		
		panel1.add(button_start) ;
		panel1.add(button_contanue) ;
		panel1.add(button_end) ;
		panel2.add(new Label("�ȼ�:")) ;
		panel2.add(Level) ;
		panel3.add(new Label("�ɼ�:")) ;
		panel3.add(Grade) ;
		panel4.add(new Label("�ϴ���Ϸ�ۼ�����:")) ;
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
		
		frame.setResizable(false) ;  //�����С���ɸı�
		frame.setVisible(true) ;
		
	}
	
	public void actionPerformed(ActionEvent e) {  //��Ϸ��ʼ
		
		if(e.getSource() == button_start) {
			
			new Started(level, grade, name) ;
		}
		
		if(e.getSource() == button_contanue) {
			
			/*���б���¼����*/
			/*��ӱ���¼*/
			CareTaker ct = new CareTaker() ;
			ct.saveMemento(name, Hero.getHero().CreateMemento()) ;
			new Started(hero.getLevel(), hero.getScore(), name) ;
			ct.getMemento(name) ;

		}
		
		
		if(e.getSource() == button_end) {  //��Ϸ����
			
			/*���б���¼����*/
			/*��ӱ���¼*/
			CareTaker ct = new CareTaker() ;
			ct.saveMemento(name, Hero.getHero().CreateMemento()) ;
			ct.getMemento(name) ;
			
			System.exit(0) ;
				
		}
	}

}
