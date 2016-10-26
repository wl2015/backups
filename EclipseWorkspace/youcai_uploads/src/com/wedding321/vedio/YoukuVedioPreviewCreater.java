package com.wedding321.vedio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.wedding321.upload.UploadException;
import com.wedding321.utils.PathUtil;


public class YoukuVedioPreviewCreater {

	private static final Logger logger = LogManager.getLogger(YoukuVedioPreviewCreater.class);
	
	private static final String VEDIO_PREVIEW_BASE_PATH = "vedios";
	
	private String rootPath;
	
	private String vedioUrl;
	
	public YoukuVedioPreviewCreater(String rootPath, String vedioUrl) throws UploadException {
		this.rootPath = rootPath;
		this.vedioUrl = velidateInputUrlIsYoukuVedio(vedioUrl);
	}
	
	public String genarateVedioPreview() throws UploadException, IOException {
		
		String picUrl = getVedioPreviewPicPathByFlashAddr();
		if (picUrl == null) 
			throw new UploadException("when genarateVedioPreview get vedioPreviewLogoUrl is null");
		
		if (logger.isDebugEnabled()) {
				logger.debug("\n\n\n" + picUrl + "\n\n\n");
		}
		
		System.out.println("\n\n\n"+picUrl+"\n\n\n");
		
		// 使用文件输入输出流进行图片下载
		URL picNetUrl = new URL(picUrl);
		
		String newfileNameNoExt = PathUtil.createFileSaveName();
		String savePathInRootDir = PathUtil.createSavePathInUploadRootDir("pre", newfileNameNoExt);
		String filePathAndName = new StringBuffer().append(VEDIO_PREVIEW_BASE_PATH).append("/").append(savePathInRootDir).append(newfileNameNoExt).append(".jpg").toString();
		String fileFullPathAndName = new StringBuffer(rootPath).append("/").append(filePathAndName).toString();
		
		File vedioPreviewPicFile = new File(fileFullPathAndName);
		
		File vedioPreviewPicFileDir = vedioPreviewPicFile.getParentFile();
		
		if (!vedioPreviewPicFileDir.exists()) {
			vedioPreviewPicFileDir.mkdirs();
		}
		BufferedInputStream inStream = new BufferedInputStream(picNetUrl.openStream());
		
		BufferedOutputStream outStream = new BufferedOutputStream(
				new FileOutputStream(vedioPreviewPicFile));

		byte[] dataBuf = new byte[2048];
		int count = 0;
		while ((count = inStream.read(dataBuf)) != -1) {
			outStream.write(dataBuf, 0, count);
		}
		
		outStream.flush();
		outStream.close();
		inStream.close();
		
		return filePathAndName;
	}
	
	@SuppressWarnings("unused")
	private String getVedioPreviewPicPathByVedioAddr() throws UploadException, IOException {
		
		if (vedioUrl == null) 
			throw new UploadException("when getVedioPreviewPicPath input vedioUrl is null");
		
		String idPrefix = "id_";
		
		int startPisotion = vedioUrl.indexOf(idPrefix) + idPrefix.length();
		
		String vedioId = vedioUrl.substring(startPisotion, vedioUrl.indexOf(".html"));
		
		StringBuffer vedioAllMessageUrlBuffer = new StringBuffer("http://v.youku.com/player/getPlayList/VideoIDS/")
		.append(vedioId).append("/timezone/+08/version/5/source/out?password=&ran=2513&n=3");
		
		URL vedioAllMessageUrl = new URL(vedioAllMessageUrlBuffer.toString());
		
		InputStreamReader urlInputStream = new InputStreamReader(vedioAllMessageUrl.openStream());
		
		BufferedReader vedioMessageReader = new BufferedReader(urlInputStream);
		
		String[] messages = vedioMessageReader.readLine().split("\\/");
		
		urlInputStream.close();
		vedioMessageReader.close();
		
		if (logger.isDebugEnabled()) {
			logger.debug("\n\n\nget vedio message is:");
			for (String message : messages) {
				logger.debug(message);
				System.out.println("message:" + message);
			}
			logger.debug("\n\n\n");
		}
		
		String target = messages[3];
		
		String previewImgId = target.substring(0, target.indexOf("\""));
		
		String previewImgUri = new StringBuffer("http://g2.ykimg.com/").append(previewImgId).toString();
		
		return previewImgUri;
	}
	
	private String getVedioPreviewPicPathByFlashAddr() throws UploadException, IOException {
		
		if (vedioUrl == null) 
			throw new UploadException("when getVedioPreviewPicPath input vedioUrl is null");
		
		String idPrefix = "/sid/";
		
		int startPisotion = vedioUrl.indexOf(idPrefix) + idPrefix.length();
		
		String vedioId = vedioUrl.substring(startPisotion, vedioUrl.lastIndexOf("/"));
		
		StringBuffer vedioAllMessageUrlBuffer = new StringBuffer("http://v.youku.com/player/getPlayList/VideoIDS/")
		.append(vedioId).append("/timezone/+08/version/5/source/out?password=&ran=2513&n=3");
		
		URL vedioAllMessageUrl = new URL(vedioAllMessageUrlBuffer.toString());
		
		InputStreamReader urlInputStream = new InputStreamReader(vedioAllMessageUrl.openStream());
		
		BufferedReader vedioMessageReader = new BufferedReader(urlInputStream);
		
		String messages = vedioMessageReader.readLine();
		
		urlInputStream.close();
		vedioMessageReader.close();
		
		JSONObject datas = new JSONObject(messages);
		
		JSONObject logoJson = datas.getJSONArray("data").getJSONObject(0);
		
		String previewImgUri = logoJson.getString("logo");
		
		if (logger.isDebugEnabled()) {
			logger.debug("get vedio preview logo url is:" + previewImgUri);
		}
		
		return previewImgUri;
	}
	
	public String getGenarateFailedPreviewPic(String basePath) {
		
		String defaultVedioPreviewPicUrl = "de/ZJ/htmlp/zjhihus.png";
		
		return basePath + "/" +defaultVedioPreviewPicUrl;
	}
	
	private String velidateInputUrlIsYoukuVedio(String vedioUrl) throws UploadException {
		String urlPrefix = "http://player.youku.com/player.php";
		String urlSuffix = "v.swf";
		if (vedioUrl.startsWith(urlPrefix) && vedioUrl.endsWith(urlSuffix)) {
			return vedioUrl;
		}
		throw new UploadException("input string is not come from youku website");
	}
	
}
