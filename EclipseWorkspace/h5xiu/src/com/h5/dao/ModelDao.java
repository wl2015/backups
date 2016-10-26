package com.h5.dao;

import java.util.List;

import com.h5.entity.Model;
import com.h5.entity.Music;

public interface ModelDao {

	public List<Model> getAllModel();

	public Model getModelById(String modelId);

	public Music getMusicById(String musicId);

	public Model editModel(String modelId);

	public boolean saveModel(Model model);

	public boolean saveMusic(Music music, String string);

	public boolean deleteModel(String id);

	public Model getDetailModelById(String id);

	public String copyModel(Model newModel);

	public boolean setQrCodeAddr(String newModelId, String qrCodeAddr);

	public boolean offLineModel(String id);

	public String addModel(Model model);

	public List<Model> getModelByType(int typeId);

}
