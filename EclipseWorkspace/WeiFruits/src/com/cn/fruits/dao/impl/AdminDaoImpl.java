package com.cn.fruits.dao.impl;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cn.fruits.bean.AdminBean;
import com.cn.fruits.dao.AdminDao;
import com.cn.fruits.entity.AdminEntity;
import com.cn.fruits.util.CommonUtil;

@Repository
public class AdminDaoImpl implements AdminDao {
  @Inject
  private SessionFactory sessionFactory;

  /**
   * 查询管理员是否已登录，若登录返回admin
   * @param AdminEntity
   * @return AdminBean
   * */
  public AdminBean doLoginDao(AdminEntity admin) throws Exception {
    AdminEntity adminEntity = new AdminEntity();
    Session session = sessionFactory.getCurrentSession();
    String hql = "from AdminEntity where adminAccount=? and adminPassword = ?";
    Query query = session.createQuery(hql);
    query.setParameter(0, admin.getAdminAccount());
    query.setParameter(1, admin.getAdminPassword());
    adminEntity = (AdminEntity) query.uniqueResult();
    AdminBean loginAdmin = new AdminBean();
    if(adminEntity!=null){
      loginAdmin = CommonUtil.changeAdminEntityIntoAdminBean(adminEntity);
    }
    return loginAdmin;
  }

  /***
   *  根据adminId修改密码
   *  修改成功返回true，失败返回false
   * */
  public boolean updateAdminPassWordByAdminIdDao(int adminId, String passWord) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "UPDATE AdminEntity A set A.adminPassword = ? WHERE A.adminId = ?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, passWord);
    query.setParameter(1, adminId);
    int result = query.executeUpdate();
    if(result == 0){
      return false;
    }
    return true;
  }
}
