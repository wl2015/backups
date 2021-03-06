package com.h5.service;

import java.util.List;

import com.h5.entity.Model;

public interface ModelService {

	public List<Model> doGetAllModel();

	public Model doGetModelById(String modelId);

	public Model doEditModel(String modelId);

	public boolean doSaveModel(Model model);

	public boolean doDeleteModel(String id);

	public boolean doCopyModel(String id, String title, String key,
			String uploadUrl);

	public boolean doOffLineModel(String id);

	public String doAddModel(Model model);

	public boolean doSetQrCodeAddrById(String modelId, String qrCodeAddr);

	public List<Model> doGetModelByType(String key, int typeId);

}
