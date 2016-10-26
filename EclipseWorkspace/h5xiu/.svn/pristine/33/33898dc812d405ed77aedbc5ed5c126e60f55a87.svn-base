package com.h5.dao;

import java.io.Serializable;
import java.util.List;



import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * 
 * @author Paul Iverson
 *
 * @param <T>
 */
public abstract class BaseDao<T> {
	
	@Inject
	private SessionFactory sessionFactory;
	
	private Logger logger = Logger.getLogger(BaseDao.class);
	

	/**
	 * 获取对应的实体类
	 * @return
	 */
	abstract protected Class<T> getEntity();

	/**
	 * 
	 * @return 获取session链接
	 */
	public Session getSession(){
		/*Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();	
		trans.commit();
		trans.rollback();
		session.close();*/
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 根据具体条件查找
	 * @param hql 语句
	 * @param values 参数
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
	
	/**
	 * 添加对象
	 * @param object对象
	 * @return
	 */
	public Serializable add(Object object){
		 
		return getSession().save(object);
		
	}
	
	
	/**
	 * 根据对象ID删除对象
	 * @param id 对象id
	 */
	public void delete(int id){
		getSession().createQuery("delete " + getEntity() + " where id =" + id);
	}
	
	
	
	/**
	 * 更新对象数据
	 * @param obj 对象
	 */
	public void update(Object obj){
		getSession().update(obj);;
	}
	
	/**
	 * 根据ID查询对应对象
	 * @param id 对象id
	 * @return
	 */
	public Object getById(int id){
		return getSession().createQuery("from " + getEntity() + " where id="+id).uniqueResult();
	}
	
	/**
	 * 根据phone_num查询对应对象
	 * @param phone_num 对象phone_num
	 * @return
	 */
	public Object getByPhoneNum(String phoneNum){
		return getSession().createQuery("from " + getEntity() + " where phone_num="+phoneNum).uniqueResult();
	}
	
	/**
	 * 获取该对象全部列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAllList(){
		logger.debug("\n\n\n-----------from " + getEntity().getName());
		return getSession().createQuery("from " + getEntity().getName()).list();
	}
	
	/**
	 * 获取某一页对象数据
	 * @param page 获取的页数
	 * @param one_page_nums 每一页的记录数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getPageList(int page, int one_page_nums){
		if (page < 0){
			return null;
		}
		int totalNums = Integer.parseInt(getSession().createQuery("select count(*) from "+ getEntity())
				.uniqueResult().toString());
		int startPage = (page - 1) * one_page_nums;
		if (startPage > totalNums){
			return null;
		}
		int endPage = startPage + one_page_nums > totalNums ? totalNums : startPage + one_page_nums;
		return getSession().createQuery("from " + getEntity() + " where 1=1")
				.list().subList(startPage, endPage);
		
	}
}
