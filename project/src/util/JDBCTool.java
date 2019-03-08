package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBC的工具类
 * 
 * 其中包含：数据库连接，关闭数据库资源等方法
 */

/**
 *  1、 加载数据库驱动
	2、 创建并获取数据库链接 
	3、 创建jdbc statement对象
	4、 设置sql语句 
	5、 设置sql语句中的参数(使用 
               preparedStatement) 
	6、 通过statement执行sql并获取结果
	7、 对sql执行结果进行解析处理 
	8、 释放资源(resultSet、preparedstatement、connection) 
 */

public class JDBCTool {

	//数据库连接
	
	public static Connection getConnection() throws Exception{
		//创建properties类读取配置文件
		Properties properties = new Properties() ;
		//加载资源  传入输入流
		InputStream inStream = JDBCTool.class.getClassLoader().getResourceAsStream("jdbc.properties") ;
//		try {
//			properties.load(inStream);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace() ;
//		}
		
		properties.load(inStream);
		
		//1.准备获取连接需要的四个字符串：user、password、url、jdbcDriver
		String user = properties.getProperty("user") ;
		String password = properties.getProperty("password") ;
		String url = properties.getProperty("url") ;
		String jdbcDriver = properties.getProperty("jdbcDriver") ;
		
//		   String driver="com.mysql.jdbc.Driver" ;   //获取mysql数据库的驱动类  
//		   String url="jdbc:mysql://localhost:3306/LegendCarry" ; //连接数据库  
//		   String name="root" ;//连接mysql的用户名  
//		   String pwd="3150997" ;//连接mysql的密码  
		
		//2.加载驱动
		Class.forName(jdbcDriver) ;
		
		//3.获取数据库连接
		Connection conn = DriverManager.getConnection(url, user, password) ;
		return conn ;
		}
	
	//释放连接
	
	public static void releaseDB(ResultSet resultSet,Statement statement,Connection connection) {
		if(resultSet != null) {
			try {
				resultSet.close() ;
			} catch(SQLException e) {
				e.printStackTrace() ;
			}
		}
		
		if(statement != null) {
			try {
				statement.close() ;
			}catch(SQLException e) {
				e.printStackTrace() ;
			}
		}
		
		if(connection != null) {
			try {
				connection.close() ;
			}catch(SQLException e) {
				e.printStackTrace() ;
			}
		}
	}
		
}
