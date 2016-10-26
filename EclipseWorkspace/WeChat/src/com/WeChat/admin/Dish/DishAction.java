package com.WeChat.admin.Dish;

import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.util.ArrayList;  
import java.util.Date;  
import java.util.HashMap;
import java.util.List;  
  
import org.apache.struts2.ServletActionContext;  
  
import com.WeChat.entity.Dish;
import com.opensymphony.xwork2.ActionSupport;  
  
public class DishAction extends ActionSupport {  
   // private static final long serialVersionUID = 572146812454l;  
    private static final int BUFFER_SIZE = 16 * 1024;  
    private File myFile;    
   // private List<String> contentType = new ArrayList<String>();  
    private String fileName;    //文件名  
    private String imageFileName;
	private String imagepath;

	private Dish dish; 
    private Dishservice dishservice=new DishserviceImpl();
    private List<Dish> dishList=new ArrayList<Dish>();
    private HashMap<String,String> dishnamelist=new HashMap<String,String>();
    private HashMap<Integer,String> pageList=new HashMap<Integer,String>();

	private String returnMes;

	public String getReturnMes() {
		return returnMes;
	}

	public HashMap<Integer, String> getPageList() {
		return pageList;
	}

	public HashMap<String, String> getDishnamelist() {
		return dishnamelist;
	}

	public void setDishnamelist(HashMap<String, String> dishnamelist) {
		this.dishnamelist = dishnamelist;
	}

	//用作分页显示的
    private int pageSize=3;
	private int pageNum;
	private int totalpage;
	private int totalnum;
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalpage() {
		return totalpage;
	}
	public int getTotalnum() {
		return totalnum;
	}

	public List<Dish> getDishList() {
		return dishList;
	}

	public void setDishList(List<Dish> dishList) {
		this.dishList = dishList;
	}

	//获取所有的菜品名
	public String getDishNameList(){
		dishnamelist=dishservice.getDishNameList();
		return "";
	}
	private static void copy(File src, File dst) {  
        try {  
            InputStream in = null;  
            OutputStream out = null;  
            try {  
                in = new BufferedInputStream(new FileInputStream(src),  
                        BUFFER_SIZE);  
                out = new BufferedOutputStream(new FileOutputStream(dst),  
                        BUFFER_SIZE);  
                byte[] buffer = new byte[BUFFER_SIZE];  
                while (in.read(buffer) > 0) {  
                    out.write(buffer);  
                }  
            } finally {  
                if (null != in) {  
                    in.close();  
                }  
                if (null != out) {  
                    out.close();  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    private static String getExtention(String fileName) {  
        int pos = fileName.lastIndexOf(".");  
        return fileName.substring(pos);  
    }  
  
   //添加菜品
    public String uploadDish() {  
        if (myFile == null)  
            return INPUT;  
        else{
            imageFileName=new Date().getTime()+ getExtention(this.getMyFileFileName()) ; 
            if(!(new File(ServletActionContext.getServletContext()+"/imagePath").exists())){
            	new File(ServletActionContext.getServletContext()+"/imagePath").mkdirs();
            }
            File imageFile = new File(ServletActionContext.getServletContext()  //得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)     
                    .getRealPath("imagePath")     
                    + "/" + imageFileName);   
            copy(myFile, imageFile);  //把图片写入到上面设置的路径里  
            imagepath="imagePath/"+imageFileName;//是要放到数据库的地址，从imagePath开始
            //System.out.println(imageFileName+"............................uploaddishaction");
           // System.out.println(imageFile.getPath()+"............................uploaddishaction");
        }  
        dish.setPic(imagepath);
        
        
        //System.out.println(getDish().getDish_name()+getDish().getPic()+".................................uploaddishaction");
        boolean b=dishservice.setDish(getDish());
        if(b){
        	returnMes="添加成功";
        }
        else{
        	returnMes="添加失败";
        }
        dish=null;
        return SUCCESS;  
    }  
    //获取所有菜品信息
    public String getAllDish(){
    	if(ServletActionContext.getRequest().getParameter("pageNum")==null){
    		if(getPageNum()!= 0){
    			pageNum=getPageNum();
    		}
    		else{
    			pageNum=1;
    		}
		}
		else{
		pageNum=Integer.parseInt(ServletActionContext.getRequest().getParameter("pageNum"));
		}
		totalnum=dishservice.getDishNum();
		totalpage=(totalnum-1)/pageSize +1;
    	dishList=dishservice.getAllDish(pageNum);
    	
    	for(int i=1;i <=totalpage; i++){
    		pageList.put(i,"第"+i+"页");
    	}
    	
    	return "backDishlist";
    }
    //修改菜品
    public String modifyDish(){
    	/*//上传大图片
    	if (image1 != null){
	    	image1FileName=new Date().getTime()+ getExtention(this.getImage1FileName());
	    	File image1File= new File(ServletActionContext.getServletContext()  //得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)     
	                .getRealPath("imagePath")     
	                + "/" + image1FileName); 
	    	 copy(image1, image1File);  //把图片写入到上面设置的路径里  
	    	 dish.setB_pic("imagePath/"+image1FileName);
    	} 
    	//上传小图片
    	if (image2 != null){
	    	image2FileName=new Date().getTime()+ getExtention(this.getImage2FileName());
	     	File image2File= new File(ServletActionContext.getServletContext()  //得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)     
	                 .getRealPath("imagePath")     
	                 + "/" + image2FileName); 
	     	 copy(image2, image2File);  //把图片写入到上面设置的路径里  
	     	 dish.setS_pic("imagePath/"+image2FileName);
    	}*/
      if(myFile != null){
    
            imageFileName=new Date().getTime()+ getExtention(this.getMyFileFileName()) ;  
            File imageFile = new File(ServletActionContext.getServletContext()  //得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)     
                    .getRealPath("imagePath")     
                    + "/" + imageFileName);   
            copy(myFile, imageFile);  //把图片写入到上面设置的路径里  
            imagepath="imagePath/"+imageFileName;//是要放到数据库的地址，从imagePath开始
            //System.out.println(imageFileName+"............................uploaddishaction");
            //System.out.println(imageFile.getPath()+"............................uploaddishaction");
        
        dish.setPic(imagepath);
       // dish.setS_pic(imagepath.get(1));
        
        System.out.println(getDish().getDish_name()+getDish().getPic()+".................................uploaddishaction");      
  	  
      }
       boolean b=dishservice.modifyDish(dish);
        if(b){returnMes="修改成功";}
        else{
        	returnMes="修改失败";
        }
        if(ServletActionContext.getRequest().getParameter("pageNum")==null){
        	if(getPageNum()!= 0){
    			pageNum=getPageNum();
    		}
    		else{
    			pageNum=1;
    		}			
		}
		else{
		pageNum=Integer.parseInt(ServletActionContext.getRequest().getParameter("pageNum"));
		}
		totalnum=dishservice.getDishNum();
		totalpage=(totalnum-1)/pageSize +1;
    	dishList=dishservice.getAllDish(pageNum);
    	for(int i=1;i <=totalpage; i++){
    		pageList.put(i,"第"+i+"页");
    	}
    	return "backDishlist";
    }
   // 删除菜品
    public String deleteDish(){
    	boolean b=dishservice.deleteDish(dish);
    	if(b){
        	returnMes="删除成功";
        }
        else{
        	returnMes="删除失败";
        }
    	if(ServletActionContext.getRequest().getParameter("pageNum")==null){
    		if(getPageNum()!= 0){
    			pageNum=getPageNum();
    		}
    		else{
    			pageNum=1;
    		}		
		}
		else{
		pageNum=Integer.parseInt(ServletActionContext.getRequest().getParameter("pageNum"));
		}
		totalnum=dishservice.getDishNum();
		totalpage=(totalnum-1)/pageSize +1;
    	dishList=dishservice.getAllDish(pageNum);
    	for(int i=1;i <=totalpage; i++){
    		pageList.put(i,"第"+i+"页");
    	}
    	return "backDishlist";
    }
    //获取dish通过id
    public String getDishByid(){
    	 //System.out.println(getDish().getDish_id()+".............................dishaction");
    	if(ServletActionContext.getRequest().getParameter("pageNum")==null){
    		if(getPageNum()!= 0){
    			pageNum=getPageNum();
    		}
    		else{
    			pageNum=1;
    		}		
		}
		else{
		pageNum=Integer.parseInt(ServletActionContext.getRequest().getParameter("pageNum"));
		}
    	dish=dishservice.getDishByid(getDish());
    	
    	return "backModifyDish";
    }
    public String getMyFileFileName() {  
        return fileName;  
    }  
  
    public void setMyFileFileName(String fileName) {  
        this.fileName = fileName;  
    }  
  
    /*public List<String> getImagepath() {
		return imagepath;
	}

	public void setImagepath(List<String> imagepath) {
		this.imagepath = imagepath;
	}
    public List<File> getMyFile() {  
        return myFile;  
    }  
  
    public void setMyFile(List<File> myFile) {  
        this.myFile = myFile;  
    }  */
  
   /* public List<String> getContentType() {  
        return contentType;  
    }  
  
    public void setContentType(List<String> contentType) {  
        this.contentType = contentType;  
    }*/  
  
  
   /* public List<String> getMyFileFileName() {  
        return fileName;  
    }  
  
    public void setMyFileFileName(List<String> fileName) {  
        this.fileName = fileName;  
    }  
  
  
    public List<String> getImageFileName() {  
        return imageFileName;  
    }  
  
    public void setImageFileName(List<String> imageFileName) {  
        this.imageFileName = imageFileName;  
    }  */
  
 
  
    public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public static int getBufferSize() {  
        return BUFFER_SIZE;  
    }  
	public Dishservice getDishservice() {
			return dishservice;
		}

	public void setDishservice(Dishservice dishservice) {
			this.dishservice = dishservice;
		}
	/* public File getImage1() {
			return image1;
		}

		public void setImage1(File image1) {
			this.image1 = image1;
		}

		

		public String getImage1ContentType() {
			return image1ContentType;
		}

		public void setImage1ContentType(String image1ContentType) {
			this.image1ContentType = image1ContentType;
		}

		public File getImage2() {
			return image2;
		}

		public void setImage2(File image2) {
			this.image2 = image2;
		}

		

		public String getImage2ContentType() {
			return image2ContentType;
		}

		public void setImage2ContentType(String image2ContentType) {
			this.image2ContentType = image2ContentType;
		}
		public String getImage1FileName() {
			return image1FileName;
		}

		public void setImage1FileName(String image1FileName) {
			this.image1FileName = image1FileName;
		}
		public String getImage2FileName() {
			return image2FileName;
		}

		public void setImage2FileName(String image2FileName) {
			this.image2FileName = image2FileName;
		}*/
}
