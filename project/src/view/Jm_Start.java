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
	
	//按钮
	private JButton bt_signup ;
	private JButton bt_login ;
	private JTextField tf_name ;
	private JPasswordField tf_pass ;
	
	private ValidCode vcode;
	
	
	//数据库
	DB_Operater db_Operater ;
	
	public Jm_Start() {
		//数据库
		db_Operater = new DB_Operater() ;

		this.setTitle("LegendCarry Start") ;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;
		// 文本框
		JPanel p_text1 = new JPanel() ;
		JPanel p_text2 = new JPanel() ; 
		JPanel p_text3 = new JPanel() ;
		
		tf_name = new JTextField(20) ;
		tf_pass = new JPasswordField(20) ;
		vcode = new ValidCode() ;
		
		p_text1.add(new Label("用户名")) ;
		p_text2.add(new Label("密    码")) ;
		p_text3.add(new Label("验证码")) ;
		
		p_text1.add(tf_name) ;
		p_text2.add(tf_pass) ;
		p_text3.add(vcode) ;
		// 按钮栏
		JPanel p_bt = new JPanel() ;
		bt_signup = new JButton("注册账号") ;
		bt_login = new JButton("登陆游戏") ;
		p_bt.add(bt_signup) ;
		p_bt.add(bt_login) ;

		// 监听器
		bt_signup.addMouseListener(this) ;
		bt_login.addMouseListener(this) ;
		
		this.add(p_text1, BorderLayout.NORTH) ;
		this.add(p_text2, BorderLayout.CENTER) ;
		this.add(p_text3, BorderLayout.CENTER) ;
		this.add(p_bt, BorderLayout.SOUTH) ;
		this.setBounds((dim.width - 400) / 2, (dim.height - 300) / 2, 350, 150) ;// 界面居中
		this.setResizable(false) ;// 大小不可改变
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		this.setVisible(true) ;
	}
	// 主函数
	public static void main(String[] args) {
		new Jm_Start() ;
	}

	@Override
	//鼠标响应事件
	public void mouseClicked(MouseEvent e) {
		//获取文本框内容  去掉首尾空格
		String name = tf_name.getText().toString().trim() ;
		String pass = tf_pass.getText().toString().trim() ;
		
		if (e.getSource() == bt_signup) {
			if (name.equals("") || name.length() == 0 || pass.equals("") || pass.length() == 0) {
				// 输出错误信息
				JOptionPane.showMessageDialog(null, "请输入账号和密码!") ;
			} else {
				if (db_Operater.check(name, pass)) {
					JOptionPane.showMessageDialog(null, "该账号已存在!请重新输入") ;
				} else {
					db_Operater.add(name, pass) ;
					JOptionPane.showMessageDialog(null, "注册成功，请登陆！") ;
				}
			}
		} else if (e.getSource() == bt_login) { 
			if (name.equals("") || name.length() == 0 || pass.equals("") || pass.length() == 0) {
				// 输出错误信息
				JOptionPane.showMessageDialog(null, "请输入账号和密码!") ;
			} else {
				if( db_Operater.check(name, pass) ) {  //登陆成功
					// 跳转主界面
					this.setVisible(false);
					int level = db_Operater.get(name, "level") ;
					int grade = db_Operater.get(name, "grade") ;
					int time = db_Operater.get(name, "time") ;
					 new Start(level, grade, name, time) ;
				} else {
					// 不跳转 输出错误信息
					JOptionPane.showMessageDialog(null, "请输入正确的账号和密码!") ;
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

