package com.wedding321.utils;

import java.util.UUID;

public class PathUtil {

	public static String createSavePathInUploadRootDir(String fileType, String fileName) {

		//产生目录结构的算法：hash目录
		// 和不同的16进制数相与，会得到不同的结果：0x0f(<16),0x1f(<32),0x3f(<64),0x7f(<128),0xff(<256)
		//按位相与操作。 得到 hashCode码二进制的后四位
		int dir1 = fileName.hashCode() & 0x1f;//一级目录名  共有32种可能。 文件名为0-31
		int dir2 = fileName.hashCode()>>5 & 0x1f;  //向右移动5位，前面补上5个0 然后与0x1f相与， 二级目录，0-31。注，右移多少位取决于与运算的16进制数转换为2进制后，有多少个1。
		//判断该目录是否存在
		String savePathInUploadRootDir = fileType + "/" + dir1 + "/" + dir2;
		
		return savePathInUploadRootDir;
	}
	
	public static String createFileSaveName() {
	//	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	//	String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000);
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
