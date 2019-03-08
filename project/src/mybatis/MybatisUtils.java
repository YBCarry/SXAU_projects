package mybatis;   

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**  
 * 创建时间：2018-1-19 下午04:09:42  
 * 项目名称：LegendCarry_Improve  
 * @author YBCarry  
 * @version 1.0   
 * @since JDK 1.6.0_21  
 * 文件名称：MybatisUtils.java  
 * 类说明：  
 */
public class MybatisUtils {
	
	public static SqlSession getSession(){
		String con="conf.xml";
		Reader r = null;
		try {
			r = Resources.getResourceAsReader(con);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(r);
		SqlSession session = sessionFactory.openSession();
		return session;
	}

}
  

