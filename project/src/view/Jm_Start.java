package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controler.DB_Operater;


public class Jm_Start extends JFrame implements MouseListener {
	
	//��ť
	private JButton bt_signup ;
	private JButton bt_login ;
	private JTextField tf_name ;
	private JPasswordField tf_pass ;
	
	private ValidCode vcode;
	
	
	//���ݿ�
	DB_Operater db_Operater ;
	
	public Jm_Start() {
		//���ݿ�
		db_Operater = new DB_Operater() ;

		this.setTitle("LegendCarry Start") ;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
		// �ı���
		JPanel p_text1 = new JPanel() ;
		JPanel p_text2 = new JPanel() ; 
		JPanel p_text3 = new JPanel() ;
		
		tf_name = new JTextField(20) ;
		tf_pass = new JPasswordField(20) ;
		vcode = new ValidCode() ;
		
		p_text1.add(new Label("�û���")) ;
		p_text2.add(new Label("��    ��")) ;
		p_text3.add(new Label("��֤��")) ;
		
		p_text1.add(tf_name) ;
		p_text2.add(tf_pass) ;
		p_text3.add(vcode) ;
		// ��ť��
		JPanel p_bt = new JPanel() ;
		bt_signup = new JButton("ע���˺�") ;
		bt_login = new JButton("��½��Ϸ") ;
		p_bt.add(bt_signup) ;
		p_bt.add(bt_login) ;

		// ������
		bt_signup.addMouseListener(this) ;
		bt_login.addMouseListener(this) ;
		
		this.add(p_text1, BorderLayout.NORTH) ;
		this.add(p_text2, BorderLayout.CENTER) ;
		this.add(p_text3, BorderLayout.CENTER) ;
		this.add(p_bt, BorderLayout.SOUTH) ;
		this.setBounds((dim.width - 400) / 2, (dim.height - 300) / 2, 350, 150) ;// �������
		this.setResizable(false) ;// ��С���ɸı�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		this.setVisible(true) ;
	}
	// ������
	public static void main(String[] args) {
		new Jm_Start() ;
	}

	@Override
	//�����Ӧ�¼�
	public void mouseClicked(MouseEvent e) {
		//��ȡ�ı�������  ȥ����β�ո�
		String name = tf_name.getText().toString().trim() ;
		String pass = tf_pass.getText().toString().trim() ;
		
		if (e.getSource() == bt_signup) {
			if (name.equals("") || name.length() == 0 || pass.equals("") || pass.length() == 0) {
				// ���������Ϣ
				JOptionPane.showMessageDialog(null, "�������˺ź�����!") ;
			} else {
				if (db_Operater.check(name, pass)) {
					JOptionPane.showMessageDialog(null, "���˺��Ѵ���!����������") ;
				} else {
					db_Operater.add(name, pass) ;
					JOptionPane.showMessageDialog(null, "ע��ɹ������½��") ;
				}
			}
		} else if (e.getSource() == bt_login) { 
			if (name.equals("") || name.length() == 0 || pass.equals("") || pass.length() == 0) {
				// ���������Ϣ
				JOptionPane.showMessageDialog(null, "�������˺ź�����!") ;
			} else {
				if( db_Operater.check(name, pass) ) {  //��½�ɹ�
					// ��ת������
					this.setVisible(false);
					int level = db_Operater.get(name, "level") ;
					int grade = db_Operater.get(name, "grade") ;
					int time = db_Operater.get(name, "time") ;
					 new Start(level, grade, name, time) ;
				} else {
					// ����ת ���������Ϣ
					JOptionPane.showMessageDialog(null, "��������ȷ���˺ź�����!") ;
				}
			}
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

