package com.dao.impl;

import java.sql.SQLException;
import java.util.List;




import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.LabDao;
import com.model.Lab;







public class LabDaoImpl extends HibernateDaoSupport implements  LabDao{


	public void deleteBean(Lab bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Lab bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Lab selectBean(String where) {
		List<Lab> list = this.getHibernateTemplate().find("from Lab " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Lab "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Lab> selectBeanList(final int start,final int limit,final String where) {
		return (List<Lab>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Lab> list = session.createQuery("from Lab "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Lab bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
