//package view;
//
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.Image;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.image.BufferedImage;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
//
///**
// * ��Ϸ��ʼ������
// * @author dsq
// *
// */
//public class MyFrame1 extends JFrame implements ActionListener {
//
//	private static final long serialVersionUID = 1L ; //���л�
//	
//	/*��ť*/
//	private JButton jButton1 = new JButton("Begin") ; //��ʼ��Ϸ
//	private JButton jButton2 = new JButton("Exit") ; //�˳���Ϸ
//	
//	/*��ǩ*/
//	private JLabel jLabel = new JLabel("Carry  ��Ȩ����") ; //��Ȩ��Ϣ
//	
//	/*����*/
//	public MyFrame1() {
//		JFrame frame = new JFrame("BEGIN") ; //��������
//		
//		/*ʵ�ּ�����ť*/
//		jButton1.addActionListener(this) ;
//		jButton2.addActionListener(this) ;
//		
//		/*���ñ�ǩλ��*/
//		jLabel.setBounds(10, 540, 200, 40) ;
//		/*���ñ�ǩ��������*/
//		jLabel.setFont(new Font("����",0,15)) ;
//		
//		JPanel jPanel = new JPanel() ; //�������
//		
//		/*�������ť*/
//		jPanel.add(jButton1) ;
//		jPanel.add(jButton2) ;
//			
//		frame.add(jLabel) ;//��ǩ�ŵ�������
//		
//		frame.add(jPanel) ; //���ŵ�������
//		
//		/*���û�ȡ��Ļ������Dimension�����Ĭ�Ϲ��߰����û�ȡ��Ļ��С����*/
//		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ; //��װ�߶ȿ��
//		
//		frame.setBounds((dim.width - 1000)/2, (dim.height - 615)/2, 1000, 615) ; //�����С�����м�λ����ʾ��
//		
//		// ����ͼƬ  
//        ImageIcon background = new ImageIcon("img/index.jpg") ;  
//        // �ѱ���ͼƬ��ʾ��һ����ǩ����  
//        JLabel label = new JLabel(background) ;  
//        // �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������  
//        label.setBounds(0, 0, this.getWidth(), this.getHeight()) ;  
//        // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��  
//        JPanel imagePanel = (JPanel) this.getContentPane() ;  
//        imagePanel.setOpaque(false) ;  
//        // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����  
//        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)) ;
//		jPanel.add(label) ;
//		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;  //����رշ�ʽ
//		frame.setVisible(true) ; //����ɼ�
//		frame.setResizable(false) ; //���岻�ɸı�
// 	}
//
//	
//	/*��������*/
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		
//		/*�ж�*/
//		if(e.getSource() == jButton1)
//		{
//		    new Started1 () ; //������Ϸ��ȫ����
//			
//		}
//			
//		if(e.getSource() == jButton2) 
//		{
//			System.exit(0) ; //�˳�
//		}
//	}
//
//}
