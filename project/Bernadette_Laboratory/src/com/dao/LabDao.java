package com.dao;

import java.util.List;

import com.model.Lab;




public interface LabDao  {
	
	
	
	public void insertBean(Lab bean);
	
	public void deleteBean(Lab bean);
	
	public void updateBean(Lab bean);

	public Lab selectBean(String where);
	
	public List<Lab> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
