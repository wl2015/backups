package com.h5.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.h5.dao.BaseDao;
import com.h5.dao.ModelDao;
import com.h5.entity.Model;
import com.h5.entity.Music;

@Repository
public class ModelDaoImpl extends BaseDao<Model> implements ModelDao {

	private static final Logger logger = Logger.getLogger(ModelDaoImpl.class);
	
	private static final int INSERT_OR_UPDATE_SUCCESS = 1; 
	
	@Override
	protected Class<Model> getEntity() {
		return Model.class;
	}

	/**
	 * 管理员获取所有模板信息
	 * */
	public List<Model> getAllModel() {
		String hql = "SELECT new Model(m.id, m.title, m.describe, m.surfaceAddr, m.createTime, m.lastReviseTime) FROM Model m ORDER BY m.createTime DESC";
		
		Query query = super.createQuery(hql);
		List<Model> modelList = query.list();
		
		return modelList;
	}

	/**
	 * 管理员：根据模板id获取模板的部分信息
	 * */
	public Model getModelById(String modelId) {
		String hql = "SELECT new Model(m.id, m.musicId, m.qrCodeAddr, m.content) FROM Model m WHERE m.id=?";
		
		Query query = super.createQuery(hql);
		query.setInteger(0, Integer.parseInt(modelId));
		List<Model> modelList = query.list();
		
		return modelList.get(0);
	}

	/**
	 * 管理员：根据音乐id获取音乐信息
	 * */
	public Music getMusicById(String musicId) {
		String hql = "SELECT new Music(m.id, m.name, m.musicAddr) FROM Music m WHERE m.id=?";
		
		Query query = super.createQuery(hql);
		query.setInteger(0, Integer.parseInt(musicId));
		List<Music> musicList = query.list();
		
		return musicList.get(0);
	}
	
	/**
	 * 管理员编辑模板：获取模板的详细信息
	 * */
	public Model editModel(String modelId) {
		String hql = "SELECT new Model(m.id, m.musicId, m.content) FROM Model m WHERE m.id=?";
		
		Query query = super.createQuery(hql);
		query.setInteger(0, Integer.parseInt(modelId));
		List<Model> modelList = query.list();
		
		return modelList.get(0);
	}

	/**
	 * 管理员：保存模板
	 * */
	public boolean saveModel(Model model) {
		String hql = "UPDATE Model m SET m.content=?, m.lastReviseTime=?, m.lastReviseIp=? WHERE m.id=?";
		Query query = createQuery(hql);
		
		query.setString(0, model.getContent());
		query.setInteger(1, model.getLastReviseTime());
		query.setString(2, model.getLastReviseIp());
		query.setInteger(3, Integer.parseInt(model.getId()));
		
		int result = query.executeUpdate();
		
		if (result == INSERT_OR_UPDATE_SUCCESS){
			return true;
		}
		
		return false;
	}

	/**
	 * 管理员：保存音乐信息
	 * */
	public boolean saveMusic(Music music, String modelId) {
		if (music.getId().equals("0")){
			try {
				//待测试
				add(music);
				
			} catch (Exception e) {
				return false;
			}
		}
		else{
			try {
				update(music);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 管理员：根据id删除模板
	 * */
	public boolean deleteModel(String id) {
		try{
			delete(Integer.parseInt(id));
		}
		catch(Exception e){
			return false;
		}
		return true;
	}

	/**
	 * 获取模板的详细信息
	 * */
	public Model getDetailModelById(String id) {
		return (Model)super.getById(Integer.parseInt(id));
	}

	/**
	 * 将新的模板存入数据库
	 * */
	public String copyModel(Model newModel) {
		try{
			add(newModel);
		}
		catch(Exception e){
			return "-1";
		}
		logger.debug("\n\nnewModelId:"+newModel.getId()+"\n\n");
		return newModel.getId();
	}

	/**
	 * 将对应的模板的二维码存入数据库
	 * */
	public boolean setQrCodeAddr(String newModelId, String qrCodeAddr) {
		return false;
	}

	/**
	 * 管理员：将模板下线
	 * */
	public boolean offLineModel(String id) {
		String hql = "UPDATE Model m SET m.status=2 WHERE m.id=?";
		
		Query query = createQuery(hql);
		query.setInteger(0, Integer.parseInt(id));
		int result = query.executeUpdate();
		
		if (result == INSERT_OR_UPDATE_SUCCESS){
			return true;
		}
		return false;
	}

	/**
	 * 管理员：添加模板
	 * */
	public String addModel(Model model) {
		try{
			add(model);
		}
		catch(Exception e){
			return "-1";
		}
		logger.debug("\n\nnewModelId:"+model.getId()+"\n\n");
		return model.getId();
	}

	/**
	 * 用户：获取根据模板typeId获取模板，当typeId=0时，则获取所有模板
	 * */
	public List<Model> getModelByType(int typeId) {
		String hql = "";
		List<Model> musicList = null;
		if (typeId == 0){
			hql = "SELECT new Model(m.id, m.title, m.qrCodeAddr, m.content, m.createTime) FROM Model m ORDER BY m.createTime desc";
			
			Query query = super.createQuery(hql);
			musicList = query.list();
		}else{
			hql = "SELECT new Model(m.id, m.title, m.qrCodeAddr, m.content, m.createTime) FROM Model m WHERE m.typeId=? ORDER BY m.createTime desc";
			
			Query query = super.createQuery(hql);
			query.setInteger(0, typeId);
			musicList = query.list();
		}
		
		return musicList;
	}

}
