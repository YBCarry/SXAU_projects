package controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JDBCTool;


/**
 * 
 * @author dsq
 * 与数据库相关的操作
 *
 */

public class DB_Operater {
	
	Connection connection = null ;
	PreparedStatement pstmt = null ;
	ResultSet resultSet = null ;
	
	//judge the db has a user's information by name and password
	public boolean exist(String name) {
		String sql = "select * from user where user.username='" + name + "'" ;
		try {
			connection = (Connection)JDBCTool.getConnection() ;
			pstmt = connection.prepareStatement(sql) ;
			ResultSet rs = pstmt.executeQuery() ;
			//如果查询的ResultSet里有超过一行的记录，则登录成功
			if(rs.next()) {
				System.out.println("username") ;
				System.out.println("level") ;
				System.out.println("grade") ;
				return true ;
			}
			return false ;
		}catch(Exception e){
			e.printStackTrace() ;
		}finally {
			JDBCTool.releaseDB(resultSet, pstmt, connection) ;
		}
		return false ;
	}
	
		//登陆
	
		public boolean check(String name,String pass) {
		String sql = "select * from user where user.username='" + name + "'and user.password='" + pass + "'" ;
		try {
			connection = (Connection)JDBCTool.getConnection() ;
			pstmt = connection.prepareStatement(sql) ;
			ResultSet rs = pstmt.executeQuery() ;
			//如果查询的ResultSet里有超过一行的记录，则登录成功
			if(rs.next()) {
				System.out.println("username") ;
				System.out.println("password") ;
				System.out.println("level") ;
				System.out.println("grade") ;
				return true ;
			}
			return false ;
		} catch(Exception e) {
			e.printStackTrace() ;
		} finally {
			JDBCTool.releaseDB(resultSet, pstmt, connection) ;
		}
		return false ;
	}
		
		// update the user's level and grade
		public void update(String name, int level, int grade) {
			String sql = "update user set level=?,grade=? where username=?";
			try {
				connection = (Connection)JDBCTool.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql); 
				pstmt.setInt(1, level);
				pstmt.setInt(2, grade);
				pstmt.setString(3, name);
				pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCTool.releaseDB(resultSet, pstmt, connection);
			}
		}
		// update time by name
		public void update(String name, String parameter, String type) {
			String sql1 = "update user set time=? where username=?";
			String sql2 = "update user set blood=? where username=?";
			String sql3 = "update user set maxgrade=? where username=?";
			try {
				connection = (Connection) JDBCTool.getConnection();
				PreparedStatement pstmt = null;
				if (type.equals("time")) {
					pstmt = connection.prepareStatement(sql1);
				} 
				else if (type.equals("blood")) {
					pstmt = connection.prepareStatement(sql2);
				}
				else if (type.equals("maxgrade")) {
					pstmt = connection.prepareStatement(sql3);
				}
				pstmt.setInt(1, Integer.parseInt(parameter));
				pstmt.setString(2, name);
				pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCTool.releaseDB(resultSet, pstmt, connection);
			}
		}
		
		//注册
		
		public void add(String name,String pass) {
			String sql="insert into user values(?,?,?,?,?,?,?)" ;
			try {
				connection=(Connection)JDBCTool.getConnection() ;
				pstmt=connection.prepareStatement(sql) ;
				pstmt.setString(1, name) ;
				pstmt.setString(2, pass) ;
				pstmt.setInt(3, 0) ;
				pstmt.setInt(4, 0) ;
				pstmt.setInt(5, 0) ;
				pstmt.setInt(6, 0) ;
				pstmt.setInt(7, 0) ;
				pstmt.execute() ;
				System.out.println("注册成功请登录") ;
			}catch(Exception e) {
				e.getStackTrace() ;
			}finally {
				JDBCTool.releaseDB(resultSet, pstmt, connection) ;
			}
		}
		
		// get grade or level by name
		public int get(String name, String type) {
			String sql = "select * from user where username=?";
			try {
				connection = (Connection) JDBCTool.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql); 
				pstmt.setString(1, name);
				ResultSet rs = pstmt.executeQuery();
				// 如果查询的ResultSet里有超过一条的记录，则登录成功
				if (rs.next()) {
					if (type.equals("level")) {
						return rs.getInt("level");
					} 
					else if (type.equals("grade")) {
						return rs.getInt("grade");
					}
					else if (type.equals("time")) {
						return rs.getInt("time");
					}
					else if (type.equals("blood")) {
						return rs.getInt("blood");
					}
					else if (type.equals("maxgrade")) {
						return rs.getInt("maxgrade");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCTool.releaseDB(resultSet, pstmt, connection);
			}
			return 0;
		}

}
