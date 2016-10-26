package com.wedding321.upload;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.wedding321.utils.PathUtil;


public class FileUploader {
	
	private static FileUploader instance = null;
	
	//定义允许上传的文件扩展名
	private static HashMap<String, String[]> extMap = new HashMap<String, String[]>();
	static {
		extMap.put("image", new String[] {"gif","jpg","jpeg","png","bmp"} );
		extMap.put("flash", new String[] {"swf","flv"});
		extMap.put("media", new String[] {"swf","flv","mp3","wav","wma","wmv","mid","avi","mpg","asf","rm","rmvb"});
		extMap.put("file", new String[] {"doc","docx","xls","xlsx","ppt","htm","html","txt","zip","rar","gz","bz2"});
	}
	
	
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	//
	// 以下为配置项
	//
	private boolean isParameterInited = false;
	
	private int fileReceiveThreshold = 1;
	private File tmpReceiveDir = null;
	private int fileMaxSize = 1;
	private int totalMaxSize = 1;
	private String largeUploadRootDirURL = null;
	private String middleUploadRootDirURL = null;
	private String smallUploadRootDirURL = null;
	private File largeUploadRootDir = null;
	private File middleUploadRootDir = null;
	private File smallUploadRootDir = null;
	private int middleUploadThreshold = 1;
	private int largeUploadThreshold = 1;
	
	
	private FileUploader() {
	}
	
	public static FileUploader getInstance() {
		if (instance == null) {
			synchronized (FileUploader.class) {
				if (instance == null) {
					instance = new FileUploader();
				}
			}
		}
		return instance;
	}
	
	/**
	 * 保存上传文件成功则返回文件的访问URL列表
	 * 
	 * @param request
	 * @param fileType
	 * @return 
	 * @throws UploadException
	 */
	public List<String> saveUploads(HttpServletRequest request, String fileType) throws UploadException {
		if (!isParameterInited) {
			initParameters(request);
		}
		
		List<FileItem> items = receiveUploadFiles(request);
		
		List<String> fileUrlList = new ArrayList<String>(items.size());
		Iterator<FileItem> itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = itr.next();
			String fileName = item.getName();
			
			if (!item.isFormField()) {
				//检查扩展名
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				
				if(!extMap.containsKey(fileType)){
					throw new UploadException("系统暂不支持您上传的文件类型。");
				}
				boolean isAllowedExtName = false;;
				for (String elem : extMap.get(fileType)) {
					if (elem.equals(fileExt)) {
						isAllowedExtName = true;
					}
				}
				if(!isAllowedExtName){
					throw new UploadException("上传文件扩展名是不允许的扩展名。\n只允许" + Arrays.toString(extMap.get(fileType)) + "格式。");
				}

				try{
					String fileAccessURL = saveFile(fileType, item, fileExt);
					fileUrlList.add(request.getServletContext().getContextPath() + fileAccessURL);
					
				}catch(Exception ex){
					item.delete();
					throw new UploadException("上传文件失败。", ex);
				}
			}
		}
		
		return fileUrlList;
	}

	private String saveFile(String fileType, FileItem item, String fileExt) throws Exception {
		String newfileNameNoExt = PathUtil.createFileSaveName();
		String savePathInRootDir = PathUtil.createSavePathInUploadRootDir(fileType, newfileNameNoExt);
		Object[] resourceDirAndURL = getResourceDirAndURL(item.getSize());

		File uploadedFile = new File( ((File)resourceDirAndURL[0]).getAbsolutePath() + "/" + savePathInRootDir, newfileNameNoExt + "." + fileExt);
		File uploadedFileDir = uploadedFile.getParentFile();
		if (!uploadedFileDir.exists()) {
			uploadedFileDir.mkdirs();
		}
		item.write(uploadedFile);
		
//		if (fileType.equals("image") && isCompressNeed(item.getSize(), fileExt) ) {
//			String fileAccessURL = compressImage(uploadedFile, newfileNameNoExt, fileExt, savePathInRootDir);
//		}
		
		String fileAccessURL = (String)resourceDirAndURL[1] + "/" + savePathInRootDir + "/" + newfileNameNoExt + "." + fileExt;
		return fileAccessURL;
	}

//	private String compressImage(File original, String resourceRootDir, String originalFileNameNoExt, String fileExtName, String savePathInRootDir) {
//		FileOutputStream out = null;
//		try {
//			BufferedImage oriImg = ImageIO.read(original);
//			File tmpResult = new File (tmpReceiveDir + "/" + originalFileNameNoExt + ".jpg");
//			out = new FileOutputStream(tmpResult);
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//			encoder.encode(oriImg);
//
//			Object[] resourceDirAndURL = getResourceDirAndURL(tmpResult.length());
//			File compressedFile = new File ( ((File)resourceDirAndURL[0]).getAbsolutePath() + "/" + savePathInRootDir, originalFileNameNoExt + ".jpg");
//			
//		}catch(Exception ex) {
//			logger.error("Can not compress the image file. " + ex.getMessage(), ex);
//			return null;
//		}finally {
//			if (out != null) {
//				try {
//					out.close();
//				}
//				catch(Exception ex) {
//					logger.warn("Can not close the outputStream." + ex.getMessage());
//				}
//			}
//		}
//	}

	@SuppressWarnings("unused")
	private List<FileItem> receiveUploadFiles(HttpServletRequest request) throws UploadException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		factory.setRepository(tmpReceiveDir);//设置临时目录
		factory.setSizeThreshold(fileReceiveThreshold);//允许此次上传的最大内存占用byte。
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setFileSizeMax(fileMaxSize);// 单个文件最大值byte
		sfu.setSizeMax(totalMaxSize);//所有上传文件的总和最大值byte

		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = null;
		upload.setHeaderEncoding("UTF-8");
		try {
			items = upload.parseRequest(request);
		}
		catch(SizeLimitExceededException ex) {
			if (items != null && !items.isEmpty() ) {
				for (FileItem elem : items) {
					elem.delete();
				}
			}
			throw new UploadException("上传文件大小超过限制。", ex);
		}
		catch(FileUploadException ex) {
			if (items != null && !items.isEmpty() ) {
				for (FileItem elem : items) {
					elem.delete();
				}
			}
			throw new UploadException(ex);
		}
		return items;
	}
	
	private void initParameters(HttpServletRequest request) throws UploadException {
		// 配置的长度限制单位均认为是KB。
		fileReceiveThreshold = Integer.parseInt(request.getServletContext().getInitParameter("fileReceiveThreshold")) * 1024;
		fileMaxSize = Integer.parseInt(request.getServletContext().getInitParameter("fileReceiveSingleMaxSize")) * 1024;
		totalMaxSize = Integer.parseInt(request.getServletContext().getInitParameter("fileReceiveTotalMaxSize")) * 1024;
		
		largeUploadRootDirURL = request.getServletContext().getInitParameter("largeUploadRootDirURL");
		largeUploadRootDir = new File(request.getServletContext().getRealPath(largeUploadRootDirURL) );
		if (!largeUploadRootDir.isDirectory()) {
			largeUploadRootDir.mkdirs();
		}
		if(!largeUploadRootDir.canWrite()){
			throw new UploadException("上传目录1没有写权限，请联系系统管理员。");			
		}
		
		middleUploadRootDirURL = request.getServletContext().getInitParameter("middleUploadRootDirURL");
		middleUploadRootDir = new File(request.getServletContext().getRealPath(middleUploadRootDirURL) );
		if (!middleUploadRootDir.isDirectory()) {
			middleUploadRootDir.mkdirs();
		}
		if(!middleUploadRootDir.canWrite()){
			throw new UploadException("上传目录2没有写权限，请联系系统管理员。");			
		}
		
		smallUploadRootDirURL = request.getServletContext().getInitParameter("smallUploadRootDirURL");
		smallUploadRootDir = new File(request.getServletContext().getRealPath(smallUploadRootDirURL) );
		if (!smallUploadRootDir.isDirectory()) {
			smallUploadRootDir.mkdirs();
		}
		if(!smallUploadRootDir.canWrite()){
			throw new UploadException("上传目录3没有写权限，请联系系统管理员。");			
		}
		
		tmpReceiveDir = new File (largeUploadRootDir.getAbsolutePath() + "/" + request.getServletContext().getInitParameter("fileReceiveTempDir") );
		if (!tmpReceiveDir.isDirectory()) {
			tmpReceiveDir.mkdirs();
		}
		if(!tmpReceiveDir.canWrite()){
			throw new UploadException("上传临时目录没有写权限，请联系系统管理员。");			
		}
		
		middleUploadThreshold = Integer.parseInt(request.getServletContext().getInitParameter("middleUploadThreshold")) * 1024;
		largeUploadThreshold = Integer.parseInt(request.getServletContext().getInitParameter("largeUploadThreshold")) * 1024;
		
		logger.info("parameter is set as: " + "fileReceiveThreshold=" + fileReceiveThreshold + ", tmpReceiveDir=" + tmpReceiveDir.getAbsolutePath()
				+ ", fileMaxSize=" + fileMaxSize + ", totalMaxSize=" + totalMaxSize + ", largeUploadRootDirURL=" + largeUploadRootDirURL
				+ ", largeUploadRootDir=" + largeUploadRootDir.getAbsolutePath() + ", middleUploadRootDirURL=" + middleUploadRootDirURL
				+ ", middleUploadRootDir=" + middleUploadRootDir.getAbsolutePath() + ", smallUploadRootDirURL=" + smallUploadRootDirURL
				+ ", smallUploadRootDir=" + smallUploadRootDir.getAbsolutePath() + ", middleUploadThreshold=" + middleUploadThreshold
				+ ", largeUploadThreshold=" + largeUploadThreshold
				);
		
		isParameterInited = true;
	}
	
	
	/**
	 * 返回对应resource的文件目录和访问URL
	 * @param fileSize
	 * @return  数组只包含2个对象，0-文件目录，1-访问URL
	 * 
	 */
	public Object[] getResourceDirAndURL(long fileSize) {
		if (fileSize >= largeUploadThreshold) {
			return new Object[] {largeUploadRootDir, largeUploadRootDirURL};
		}
		else if (fileSize >= middleUploadThreshold) {
			return new Object[] {middleUploadRootDir, middleUploadRootDirURL};
		}
		else {
			return new Object[] {smallUploadRootDir, smallUploadRootDirURL};
		}
	}
 
}
