package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class JDBCTest {
	
	public static Connection getConnection() {
		
		   String driver="com.mysql.jdbc.Driver" ;   //��ȡmysql���ݿ��������  
		   String url="jdbc:mysql://localhost:3306/LegendCarry" ; //�������ݿ�  
		   String name="root" ;//����mysql���û���  
		   String pwd="3150997" ;//����mysql������  
		   try {
			   Class.forName(driver) ;
			   Connection conn=DriverManager.getConnection(url, name, pwd) ;
			   return conn ;
		   }
		   catch (ClassNotFoundException e) {
				e.printStackTrace() ;
				return null ;
			} 
		   catch (SQLException e) {
				e.printStackTrace() ;
				return null ;
			}
	}
	
	public static void closeAll( Connection conn, PreparedStatement ps, ResultSet rs ) {
		try {
			if ( rs != null ) {
				rs.close() ;
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		try {
			if ( ps != null ) {
				ps.close();
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		try {
			if ( conn != null ) {
				conn.close();
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException {
	
		Connection cc = JDBCTest.getConnection() ;
		if(!cc.isClosed())
		System.out.println("Succeed connection mysql!") ;
		//��ȡstatement���󣬾�̬���޲���
		Statement statement = cc.createStatement() ;
		//��ѯ����
		String sql = "select * from user" ;
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) {
			System.out.print(rs.getString("username") + " " + rs.getString("password") + " ") ;
		}
		//��̬����
		String sql1 = "select * from user where username=?" ;
		PreparedStatement p = cc.prepareStatement(sql1) ;
		//��һ���ʺ�=��level1��
		p.setString(1, "user1");;
		ResultSet rs1=p.executeQuery();
		while(rs1.next()) {
			System.out.println("��̬����");
			System.out.println(rs1.getString("username"));
		}

	}
	
}

