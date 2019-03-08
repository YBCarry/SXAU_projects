package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class JDBCTest {
	
	public static Connection getConnection() {
		
		   String driver="com.mysql.jdbc.Driver" ;   //获取mysql数据库的驱动类  
		   String url="jdbc:mysql://localhost:3306/LegendCarry" ; //连接数据库  
		   String name="root" ;//连接mysql的用户名  
		   String pwd="3150997" ;//连接mysql的密码  
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
		//获取statement对象，静态，无参数
		Statement statement = cc.createStatement() ;
		//查询数据
		String sql = "select * from user" ;
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) {
			System.out.print(rs.getString("username") + " " + rs.getString("password") + " ") ;
		}
		//动态对象
		String sql1 = "select * from user where username=?" ;
		PreparedStatement p = cc.prepareStatement(sql1) ;
		//第一个问号=“level1”
		p.setString(1, "user1");;
		ResultSet rs1=p.executeQuery();
		while(rs1.next()) {
			System.out.println("动态参数");
			System.out.println(rs1.getString("username"));
		}

	}
	
}

