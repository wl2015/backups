package com.wedding321.image;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.wedding321.upload.FileUploader;
import com.wedding321.utils.PathUtil;

public class CutImag{
	
	public final static int USER_LOGO_WIDTH = 100;
	public final static int USER_LOGO_HEIGHT = 100;
	
	protected static Logger logger = LogManager.getLogger(CutImag.class);

		    /**
		     * 截图
		     * @param srcPath
		     * @param startX
		     * @param startY
		     * @param width
		     * @param height
		     */
	    public static String saveToLogoVersion(String imgAbsPath,int startX,int startY,int width,int height, int mappingImgW, int mappingImgH) throws IOException {
	    	File imgFile = new File(imgAbsPath);
			String imgType = imgAbsPath.substring(imgAbsPath.lastIndexOf(".")+1);
			
			if (logger.isDebugEnabled()) {
				logger.debug("original imgFile is located in " + imgAbsPath + ", the cutting from (" + startX + ", " + startY + "), width=" + width + " height=" + height + " when original image is " + mappingImgW + " * " + mappingImgH);
			}

            BufferedImage bufImg = ImageIO.read(imgFile);
           /* double scaleRate = (double)bufImg.getWidth() / mappingImgW;*/
            double scaleRateX = (double)bufImg.getWidth() / mappingImgW;
            double scaleRateY = (double)bufImg.getHeight() / mappingImgH;
			if (logger.isDebugEnabled()) {
				logger.debug(" original imgFile size is " + bufImg.getWidth() + " * " + bufImg.getHeight() + " , then scale rate is " + scaleRateX+":"+ scaleRateY);
			}
			BufferedImage tmpImg = bufImg.getSubimage((int)(scaleRateX * startX), (int)(scaleRateY * startY), (int)(scaleRateX * width), (int)(scaleRateY * height));
	    	/*BufferedImage tmpImg = bufImg.getSubimage((int)(scaleRate * startX), (int)(scaleRate * startY), (int)(scaleRate * width), (int)(scaleRate * height));*/
	    	BufferedImage resultImg = null;
	    	resultImg = tmpImg;
	    	if(tmpImg.getWidth() == USER_LOGO_WIDTH && tmpImg.getHeight() == USER_LOGO_HEIGHT){
	    		resultImg = tmpImg;
	    	}
	    	else {
	            resultImg = new BufferedImage(USER_LOGO_WIDTH, USER_LOGO_HEIGHT, BufferedImage.TYPE_INT_RGB);
	            Graphics g = resultImg.getGraphics();
	            g.drawImage(tmpImg.getScaledInstance(USER_LOGO_WIDTH, USER_LOGO_HEIGHT, Image.SCALE_FAST), 0, 0, null); // 绘制缩放后的图
	            g.dispose();
	    	}
	    	
	    	FileUploader fileSaver = FileUploader.getInstance();
			String newfileNameNoExt = PathUtil.createFileSaveName();
			String savePathInRootDir = PathUtil.createSavePathInUploadRootDir("image", newfileNameNoExt);
			Object[] resourceDirAndURL = fileSaver.getResourceDirAndURL(10*1024);  //logo 的图像文件通常都比较小，这里统一认为不超过10K

			File logoFile = new File( ((File)resourceDirAndURL[0]).getAbsolutePath() + "/" + savePathInRootDir, newfileNameNoExt + "." + imgType);
			File logoFileDir = logoFile.getParentFile();
			if (!logoFileDir.exists()) {
				logoFileDir.mkdirs();
			}
			ImageIO.write(resultImg, imgType, logoFile);
			
			/*JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(new FileOutputStream(logoFile));  
	        JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(resultImg);  
	        jep.setQuality((float) 1.0, true);  
	        encoder.encode(resultImg, jep); */
			
			deleteImgFileAndAllEmptyParentFile(imgFile);
			
			String fileAccessURL = (String)resourceDirAndURL[1] + "/" + savePathInRootDir + "/" + newfileNameNoExt + "." + imgType;
			return fileAccessURL;
		}
		
		
	    private static void deleteImgFileAndAllEmptyParentFile(File imgFile) {
	    	File imgParentFile = imgFile.getParentFile();
			imgFile.delete();
			while(imgParentFile.list().length == 0) {
				imgParentFile.delete();
				imgParentFile = imgParentFile.getParentFile();
			}
	    }
}
