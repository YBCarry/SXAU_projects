package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controler.DB_Operater;



/**
 * ��Ϸ��ʼ������
 * @author dsq
 *
 */
public class MyFrame extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L ; //���л�
	
	/*��ť*/
	private JButton bt_signup ; //��¼�˺�
	private JButton bt_login ; //ע���˺�
	
	/*�����ı���*/
	private JTextField tf_name ;  //�û���
	private JPasswordField tf_pass ;  //����
	private JTextField tf_vcode ;  //�û���
	
	/*��֤��*/
	private ValidCode vcode ;  //
	/*��ǩ*/
	private JLabel jLabel = new JLabel("Carry  ��Ȩ����") ; //��Ȩ��Ϣ

	/*���ݿ�*/
	DB_Operater db_Operater ;
	
	/*����*/
	public MyFrame() {
		
		/*���ݿ�*/
		db_Operater = new DB_Operater() ;
		
		this.setTitle("LegendCarry Start") ;  //��������
//		JFrame frame = new JFrame("BEGIN") ; 
		
		
		// ����ͼƬ  
        ImageIcon background = new ImageIcon("img/index.jpg") ;  
        // �ѱ���ͼƬ��ʾ��һ����ǩ����  
        JLabel label = new JLabel(background) ;  
        // �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������  
        label.setBounds(0, 0, this.getWidth(), this.getHeight()) ;  
        // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��  
        JPanel imagePanel = (JPanel) this.getContentPane() ;  
        imagePanel.setOpaque(false) ;  
        // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����  
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)) ;
		
		
		/*�������*/
		JPanel p_text1 = new JPanel() ;  //�����û���
		JPanel p_text2 = new JPanel() ;  //��������
		JPanel p_text6 = new JPanel() ;  //������֤��У���
		JPanel p_text7 = new JPanel() ;  //������֤��ͼƬ
		JPanel p_text8 = new JPanel() ;  //������֤��
		JPanel p_text3 = new JPanel() ;  //���ñ���ͼƬ
		JPanel p_text4 = new JPanel() ;  //���ð�Ȩ��Ϣ
		
		JPanel p_text5 = new JPanel() ;  //�����û��������롢��֤�롢��ť
		
		/*�����ı����С*/
		tf_name = new JTextField(10) ;
		tf_pass = new JPasswordField(10) ;
		tf_vcode = new JTextField(5) ;
		vcode = new ValidCode() ;
		/*���ñ�ǩ��������*/
		jLabel.setFont(new Font("����",0,15)) ;
		
		p_text1.add(new Label("�û���")) ;
		p_text2.add(new Label("��    ��")) ;
		p_text6.add(new Label("��֤��")) ;
		
		p_text1.add(tf_name) ;
		p_text2.add(tf_pass) ;
		p_text6.add(tf_vcode) ;
		
		p_text6.add(vcode) ;
		p_text3.add(label) ;
		p_text4.add(jLabel) ;
		
		/*��ť��*/
		JPanel p_bt = new JPanel() ;
		bt_signup = new JButton("ע���˺�") ;
		bt_login = new JButton("��½��Ϸ") ;
		p_bt.add(bt_signup) ;
		p_bt.add(bt_login) ;

		
		p_text8.add(p_text6, BorderLayout.WEST) ;
		p_text8.add(p_text7, BorderLayout.EAST) ;
		
		p_text5.add(p_text1, BorderLayout.NORTH) ;
		p_text5.add(p_text2, BorderLayout.CENTER) ;
		p_text5.add(p_text6, BorderLayout.CENTER) ;
		p_text5.add(p_bt, BorderLayout.SOUTH) ;
		
		/*ʵ�ּ�����ť*/
		bt_signup.addMouseListener(this) ;
		bt_login.addMouseListener(this) ;	
		
		/*���û�ȡ��Ļ������Dimension�����Ĭ�Ϲ��߰����û�ȡ��Ļ��С����*/
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ; //��װ�߶ȿ��
	
		this.add(p_text5, BorderLayout.NORTH) ;
		this.add(p_text3, BorderLayout.CENTER) ;
		this.add(p_text4, BorderLayout.SOUTH) ;
		this.setBounds((dim.width - 800) / 2, (dim.height - 600) / 2, 800, 600) ;// �����С��������ʾ   
		this.setResizable(false) ;// �����С���ɸı�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;  //����رշ�ʽ
		this.setVisible(true) ;  //����ɼ�	

 	}

	
	/*��������*/
	@Override
	//�����Ӧ�¼�
	public void mouseClicked(MouseEvent e) {
		//��ȡ�ı�������  ȥ����β�ո�
		String name = tf_name.getText().toString().trim() ;
		String pass = tf_pass.getText().toString().trim() ;
		String vcode = tf_vcode.getText().toString().trim() ;
		
		if (e.getSource() == bt_signup) {
			if (name.equals("") || name.length() == 0 || pass.equals("") || pass.length() == 0) {
				// ���������Ϣ
				JOptionPane.showMessageDialog(null, "�������˺ź�����!") ;
			} else if(vcode.equals("") || vcode.length() == 0) {
				// ���������Ϣ
				JOptionPane.showMessageDialog(null, "��������֤��!") ;
			} else{
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
			} else if(vcode.equals("") || vcode.length() == 0) {
				// ���������Ϣ
				JOptionPane.showMessageDialog(null, "��������֤��!") ;
			} else if(tf_vcode.equals(vcode.getBytes())) {
				// ���������Ϣ
				JOptionPane.showMessageDialog(null, "��֤�����!") ;
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


