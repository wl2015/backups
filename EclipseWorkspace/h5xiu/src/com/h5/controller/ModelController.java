package com.h5.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.h5.entity.Admin;
import com.h5.entity.Model;
import com.h5.entity.Music;
import com.h5.service.ModelService;
import com.h5.util.Constants;
import com.h5.util.CryptogramUtil;
import com.h5.util.DateUtil;
import com.h5.util.SystemUtil;
import com.h5.util.ValidateUtil;

@Controller
public class ModelController {

	@Inject
	private ModelService modelService;
	
	private static final Logger logger = Logger.getLogger(ModelController.class);
	
	/**
	 * 管理员：获取所有模板
	 * */
	@RequestMapping("/admin/getAllModel")
	@ResponseBody
	public Map<String, Object> getAllModel(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		//模板id暂未加密
		List<Model> modelList = modelService.doGetAllModel();
		
		if (modelList.size() != 0){
			result.put("modelList", modelList);
			result.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
		}
		else{
			result.put("code", Constants.AJAX_FAIL_ALERT_CODE);
		}
		
		return result;
	}
	
	/**
	 * 管理员：预览模板
	 * @throws Exception 
	 * */
	@RequestMapping("/admin/previewModel")
	public String previewModel(HttpServletRequest request, ModelMap map) throws Exception{
		String modelId = ValidateUtil.valiStrIsEmpty(request.getParameter("modelId"));
		String key = ValidateUtil.valiStrIsEmpty((String)request.getSession().getAttribute(Constants.ENCRYPT_CODE));
		modelId = CryptogramUtil.decrypt(modelId, key);
		
		Model model = modelService.doGetModelById(modelId);
		
		map.put("model", model);
		
		return "指定页面";
	}
	
	/**
	 * 管理员：编辑模板
	 * @throws Exception 
	 * */
	@RequestMapping("/admin/editModel")
	public String editModel(HttpServletRequest request, ModelMap map) throws Exception{
		String modelId = ValidateUtil.valiStrIsEmpty(request.getParameter("modelId"));
		String key = ValidateUtil.valiStrIsEmpty((String)request.getSession().getAttribute(Constants.ENCRYPT_CODE));
		modelId = CryptogramUtil.decrypt(modelId, key);
		
		Model model = modelService.doEditModel(modelId);
		
		map.put("model", model);
		
		return "指定page";
	}
	
	/**
	 * 管理员：保存模板
	 * @throws Exception 
	 * */
	@RequestMapping("/admin/saveModel")
	@ResponseBody
	public Map<String, Object> saveModel(HttpServletRequest request) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Model model = new Model();
		
		String modelId = ValidateUtil.valiStrIsEmpty(request.getParameter("id"));
		String key = (String)request.getSession().getAttribute(Constants.ENCRYPT_CODE);
		String content = ValidateUtil.valiStrIsEmpty(request.getParameter("content"));
		int lastReviseTime = DateUtil.convertCurrentDTTMtoInt();
		String lastReviseIp = SystemUtil.getRequestIp(request);
		//注：前端请求必须给musicId赋值，-1表示无音乐，0表示在此以前无背景音乐
		String musicId = ValidateUtil.valiStrIsEmpty(request.getParameter("musicId"));
		
		model.setId(CryptogramUtil.decrypt(modelId, key));
		model.setContent(content);
		model.setLastReviseTime(lastReviseTime);
		model.setLastReviseIp(lastReviseIp);
		
		if (musicId.equals("-1")){
			Music music = new Music();
			
			String musicName = request.getParameter("musicName");
			String musicAddr = request.getParameter("musicAddr");
			
			music.setId(musicId);
			music.setName(musicName);
			music.setMusicAddr(musicAddr);
			music.setCreateTime(lastReviseTime);
			music.setCreateIp(lastReviseIp);
			
			model.setMusic(music);
		}
		boolean result = modelService.doSaveModel(model);
		
		if(result){
			resultMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
		}
		else{
			resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
		}
		
		return resultMap;
	}
	
	/**
	 * 管理员删除模板
	 * @throws Exception 
	 * */
	@RequestMapping("/admin/deleteModel")
	@ResponseBody
	public Map<String, Object> deleteModel(HttpServletRequest request) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String id = ValidateUtil.valiStrIsEmpty(request.getParameter("id"));
		String key = (String)request.getSession().getAttribute(Constants.ENCRYPT_CODE);
		id = CryptogramUtil.decrypt(id, key);
		
		if(modelService.doDeleteModel(id)){
			resultMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
		}
		else{
			resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
		}
		
		return resultMap;
	}
	
	/**
	 * 管理员复制模板
	 * @throws Exception 
	 * */
	@RequestMapping("/admin/copyModel")
	@ResponseBody
	public Map<String, Object> copyModel(HttpServletRequest request) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String id = ValidateUtil.valiStrIsEmpty(request.getParameter("id"));
		String key = (String)request.getSession().getAttribute(Constants.ENCRYPT_CODE);
		id = CryptogramUtil.decrypt(id, key);
		String title = ValidateUtil.valiStrIsEmpty(request.getParameter("newTitle"));
		//存放二维码的访问地址
		String uploadUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + "/wedding321_uploads/upload_json.jsp";
		
		boolean result = modelService.doCopyModel(id, title, key, uploadUrl);
		
		if(result){
			resultMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
		}
		else{
			resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
		}
		
		return resultMap;
	}
	
	/**
	 * 管理员：下线模板
	 * @throws Exception 
	 * */
	@RequestMapping("/admin/offLineModel")
	@ResponseBody
	public Map<String , Object> offLineModel(HttpServletRequest request) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String id = ValidateUtil.valiStrIsEmpty(request.getParameter("id"));
		String key = (String)request.getSession().getAttribute(Constants.ENCRYPT_CODE);
		id = CryptogramUtil.decrypt(id, key);
		
		boolean result = modelService.doOffLineModel(id);
		
		if (result){
			resultMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
		}
		else{
			resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
		}
		
		return resultMap;
	}
	
	/**
	 * 管理员：新建模板
	 * @throws Exception 
	 * */
	@RequestMapping("/admin/createModel")
	@ResponseBody
	public Map<String, Object> createModel(HttpServletRequest request) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String key = (String)request.getSession().getAttribute(Constants.ENCRYPT_CODE);
		Admin admin = (Admin)request.getSession().getAttribute(Constants.LOGINUSER_SESSION_NAME_USER);
		String adminId = CryptogramUtil.decrypt(String.valueOf(admin.getAdminId()), key);
		
		Model model = getCreateModel(request);
		model.setAuthorId(adminId);
		
		String modelId = modelService.doAddModel(model);
		
		if (!modelId.equals("-1")){
			//存放二维码的访问链接
			String uploadUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + "/wedding321_uploads/upload_json.jsp";
			//二维码中存入的作品的url
//			String QrCodeUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + "/intoProduct?id="+CryptogramUtil.encrypt(productId, key);
			String QrCodeUrl = "HTTP://192.168.191.1:8080/h5xiu/intoProduct?id="+CryptogramUtil.encrypt(modelId, key);
			//返回二维码的存放地址
			String qrCodeAddr = SystemUtil.getQrcode(uploadUrl, QrCodeUrl);
			//将二维码存入数据库
			if (modelService.doSetQrCodeAddrById(modelId, qrCodeAddr)){
				resultMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
			}
			else{
				resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
			}
		}
		else{
			resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
		}
		
		return resultMap;
	}
	
	/**
	 * 获取新建模板的信息
	 * */
	private Model getCreateModel(HttpServletRequest request){
		Model model = new Model();
		//暂时未过滤脚本
		String title = ValidateUtil.valiStrIsEmpty(request.getParameter("title"));
		String describe = ValidateUtil.valiStrIsEmpty(request.getParameter("describe"));
		String surfaceAddr = ValidateUtil.valiStrIsEmpty(request.getParameter("surfaceAddr"));
		int createTime = DateUtil.convertCurrentDTTMtoInt();
		String createIp = SystemUtil.getRequestIp(request);
		
		logger.debug("\n\ntitle:"+title+"    describe:"+describe+"\n\n");
		
		model.setTitle(title);
		model.setDescribe(describe);
		model.setSurfaceAddr(surfaceAddr);
		model.setCreateIp(createIp);
		model.setCreateTime(createTime);
		
		return model;
	}
	
	/**
	 * 用户：查看模板,前台传入模板类别id，传入0则表示查看所有模板
	 * @throws Exception 
	 * */
	@RequestMapping("/user/getModelByType")
	@ResponseBody
	public Map<String, Object> getModelByType(HttpServletRequest request) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String key = (String)request.getSession().getAttribute(Constants.ENCRYPT_CODE);
		int typeId = Integer.parseInt(ValidateUtil.valiStrIsEmpty(request.getParameter("typeId")));
		List<Model> modelList = modelService.doGetModelByType(key, typeId);
		
		if (modelList.size() != 0){
			resultMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
			resultMap.put("modelList", modelList);
		}
		else{
			resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
		}
		
		return resultMap;
	}
}
