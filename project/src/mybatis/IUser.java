package mybatis;   

import java.util.List;

/**  
 * 创建时间：2018-1-19 下午03:32:12  
 * 项目名称：LegendCarry_Improve  
 * @author YBCarry  
 * @version 1.0   
 * @since JDK 1.6.0_21  
 * 文件名称：IUser.java  
 * 类说明：  
 */
public interface IUser {
	
	// 接口中的添加方法
	   public void add(User user);

	// 接口中的更改方法
	   public void update1(User user);
	   public void update2(User user);
	   
	// 接口中的查看方法
	   public List<User> check();
	   public List<User> get();

}
  

