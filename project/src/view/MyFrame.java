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
 * 游戏开始界面类
 * @author dsq
 *
 */
public class MyFrame extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L ; //序列化
	
	/*按钮*/
	private JButton bt_signup ; //登录账号
	private JButton bt_login ; //注册账号
	
	/*输入文本框*/
	private JTextField tf_name ;  //用户名
	private JPasswordField tf_pass ;  //密码
	private JTextField tf_vcode ;  //用户名
	
	/*验证码*/
	private ValidCode vcode ;  //
	/*标签*/
	private JLabel jLabel = new JLabel("Carry  版权所有") ; //版权信息

	/*数据库*/
	DB_Operater db_Operater ;
	
	/*窗体*/
	public MyFrame() {
		
		/*数据库*/
		db_Operater = new DB_Operater() ;
		
		this.setTitle("LegendCarry Start") ;  //窗体命名
//		JFrame frame = new JFrame("BEGIN") ; 
		
		
		// 背景图片  
        ImageIcon background = new ImageIcon("img/index.jpg") ;  
        // 把背景图片显示在一个标签里面  
        JLabel label = new JLabel(background) ;  
        // 把标签的大小位置设置为图片刚好填充整个面板  
        label.setBounds(0, 0, this.getWidth(), this.getHeight()) ;  
        // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明  
        JPanel imagePanel = (JPanel) this.getContentPane() ;  
        imagePanel.setOpaque(false) ;  
        // 把背景图片添加到分层窗格的最底层作为背景  
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)) ;
		
		
		/*部署面板*/
		JPanel p_text1 = new JPanel() ;  //放置用户名
		JPanel p_text2 = new JPanel() ;  //放置密码
		JPanel p_text6 = new JPanel() ;  //放置验证码校验框
		JPanel p_text7 = new JPanel() ;  //放置验证码图片
		JPanel p_text8 = new JPanel() ;  //放置验证码
		JPanel p_text3 = new JPanel() ;  //放置背景图片
		JPanel p_text4 = new JPanel() ;  //放置版权信息
		
		JPanel p_text5 = new JPanel() ;  //放置用户名、密码、验证码、按钮
		
		/*设置文本框大小*/
		tf_name = new JTextField(10) ;
		tf_pass = new JPasswordField(10) ;
		tf_vcode = new JTextField(5) ;
		vcode = new ValidCode() ;
		/*设置标签文字属性*/
		jLabel.setFont(new Font("黑体",0,15)) ;
		
		p_text1.add(new Label("用户名")) ;
		p_text2.add(new Label("密    码")) ;
		p_text6.add(new Label("验证码")) ;
		
		p_text1.add(tf_name) ;
		p_text2.add(tf_pass) ;
		p_text6.add(tf_vcode) ;
		
		p_text6.add(vcode) ;
		p_text3.add(label) ;
		p_text4.add(jLabel) ;
		
		/*按钮栏*/
		JPanel p_bt = new JPanel() ;
		bt_signup = new JButton("注册账号") ;
		bt_login = new JButton("登陆游戏") ;
		p_bt.add(bt_signup) ;
		p_bt.add(bt_login) ;

		
		p_text8.add(p_text6, BorderLayout.WEST) ;
		p_text8.add(p_text7, BorderLayout.EAST) ;
		
		p_text5.add(p_text1, BorderLayout.NORTH) ;
		p_text5.add(p_text2, BorderLayout.CENTER) ;
		p_text5.add(p_text6, BorderLayout.CENTER) ;
		p_text5.add(p_bt, BorderLayout.SOUTH) ;
		
		/*实现监听按钮*/
		bt_signup.addMouseListener(this) ;
		bt_login.addMouseListener(this) ;	
		
		/*利用获取屏幕对象类Dimension，获得默认工具包调用获取屏幕大小方法*/
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ; //封装高度宽度
	
		this.add(p_text5, BorderLayout.NORTH) ;
		this.add(p_text3, BorderLayout.CENTER) ;
		this.add(p_text4, BorderLayout.SOUTH) ;
		this.setBounds((dim.width - 800) / 2, (dim.height - 600) / 2, 800, 600) ;// 界面大小及居中显示   
		this.setResizable(false) ;// 窗体大小不可改变
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;  //窗体关闭方式
		this.setVisible(true) ;  //窗体可见	

 	}

	
	/*按键监听*/
	@Override
	//鼠标响应事件
	public void mouseClicked(MouseEvent e) {
		//获取文本框内容  去掉首尾空格
		String name = tf_name.getText().toString().trim() ;
		String pass = tf_pass.getText().toString().trim() ;
		String vcode = tf_vcode.getText().toString().trim() ;
		
		if (e.getSource() == bt_signup) {
			if (name.equals("") || name.length() == 0 || pass.equals("") || pass.length() == 0) {
				// 输出错误信息
				JOptionPane.showMessageDialog(null, "请输入账号和密码!") ;
			} else if(vcode.equals("") || vcode.length() == 0) {
				// 输出错误信息
				JOptionPane.showMessageDialog(null, "请输入验证码!") ;
			} else{
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
			} else if(vcode.equals("") || vcode.length() == 0) {
				// 输出错误信息
				JOptionPane.showMessageDialog(null, "请输入验证码!") ;
			} else if(tf_vcode.equals(vcode.getBytes())) {
				// 输出错误信息
				JOptionPane.showMessageDialog(null, "验证码错误!") ;
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


