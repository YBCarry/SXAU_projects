package mybatis;   

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**  
 * ����ʱ�䣺2018-1-19 ����04:09:42  
 * ��Ŀ���ƣ�LegendCarry_Improve  
 * @author YBCarry  
 * @version 1.0   
 * @since JDK 1.6.0_21  
 * �ļ����ƣ�MybatisUtils.java  
 * ��˵����  
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
  

