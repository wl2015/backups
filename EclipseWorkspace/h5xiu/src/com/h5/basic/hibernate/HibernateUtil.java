package com.h5.basic.hibernate;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	/**
	 * 
	 * @return 获取session链接
	 */
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 根据具体条件查找
	 * @param hql语句
	 * @param values参数
	 * @return 查询结果
	 */
	public Query createQuery(String hql, Object...values ){
		Query query = null;
		query = getSession().createQuery(hql);
		for (int i = 0; i < values.length; i++){
			query.setParameter(i, values[i]);
		}
		return query;
	}
	
	public Serializable add(Object object){
		return getSession().save(object);
	}
	
}
