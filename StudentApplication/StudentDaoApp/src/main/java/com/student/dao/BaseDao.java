package com.student.dao;

public interface BaseDao {
	
	public boolean save(Object obj) throws Exception;

	public boolean update(Object obj) throws Exception;

	public boolean saveOrUpdate(Object obj) throws Exception;

	public boolean delete(Object obj) throws Exception;


}
