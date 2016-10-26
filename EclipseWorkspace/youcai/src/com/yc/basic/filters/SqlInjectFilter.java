package com.yc.basic.filters;

import java.io.IOException;
import java.util.Map;
import java.util.Set; 

import javax.servlet.Filter; 
import javax.servlet.FilterChain; 
import javax.servlet.FilterConfig; 
import javax.servlet.ServletException; 
import javax.servlet.ServletRequest; 
import javax.servlet.ServletResponse; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.yc.util.Constants;


 //方数据库攻击拦截器
public class SqlInjectFilter implements Filter { 
  private static final Logger log = Logger.getLogger(SqlInjectFilter.class);
  
    //private static List<String> invalidsql = new ArrayList<String>(50);

    private static String error = "/error.jsp"; 
    //用于测试时打印输出过滤过程
    private static boolean debug = false; 
    @Override 
    public void destroy() { 
         
    } 
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, 
            FilterChain fc) throws IOException, ServletException { 
      
      //防sql攻击关键字符
      //select insert delete from update create destory drop alter and or like exec count chr mid master truncate char declare  - ' % ;
      String [] invalidString = new String[]{"select","insert","delete","from","update","create","destory","drop","alter","or","like","exec","count","chr","mid","master","truncate","char","declare","--","'","%",";","<",">"};
      
        if(debug){ 
            System.out.println("prevent sql inject filter works\n\n\n"); 
        } 
        HttpServletRequest request = (HttpServletRequest)req; 
        HttpServletResponse response = (HttpServletResponse)res; 
        Map<String, String> params = request.getParameterMap(); 
        Set<String> keys = params.keySet();
        //System.out.println("keys"+keys.toString()+"keys.size():"+keys.size()+"........params.size():"+params.size()+"................name"+req.getParameter("name"));
        for(String key : keys){ 
            String value = request.getParameter(key); 
            if(debug){ 
                System.out.println("process params <key, value>: <"+key+", "+value+">"); 
            }
            for(String word : invalidString){ 
                if(word.equalsIgnoreCase(value) || value.contains(word)){ 
                  System.out.println("has invalid param:"+value+"....invalid type:"+word);
                    if(value.contains("<")){ 
                        value = value.replace("<", "<"); 
                    } 
                    if(value.contains(">")){ 
                        value = value.replace(">", ">"); 
                    } 
                    value.replaceAll(word, "");
                    String hdAccep = request.getHeader("accept");
                    String hdXReq = request.getHeader("X-Requested-With");
                    if ( (hdAccep != null && hdAccep.indexOf("application/json") > -1) || (hdXReq != null && hdXReq.indexOf("XMLHttpRequest") > -1) ) { //如果是异步请求
                      //JSON格式返回 
                      JSONObject obj = new JSONObject();
                      obj.put("code", Constants.INVALID_SQL_CHAR_EEROR);
                      obj.put("msg", "存在非法字符:"+word+"，请先去掉你非法字符");
                      
                      response.setCharacterEncoding(request.getCharacterEncoding());
                      response.setContentType("application/json");
                        try {
                            response.getWriter().println(obj.toString()); 
                            return ;
                        } catch (IOException e) {  
                          log.error("Exception happens when write error message to response.", e);
                          return;
                        }
                    }  else {
                    	log.debug("\n\n\n\n-----redirct");
                    	response.sendRedirect(request.getContextPath()+error);
                    	return;
                    }
                    //request.getSession().setAttribute("sqlInjectError", "the request parameter \""+value+"\" contains keyword: \""+word+"\""); 
                   //如果含有非法字符，则重定向到错误提示页面
                   // System.out.println("request.getContextPath()+error:"+request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+error);
                     
                    //return; 
                } 
            } 
        } 
        fc.doFilter(req, res); 
    } 
    
    public void init(FilterConfig conf) throws ServletException { 
        //String sql = conf.getInitParameter("invalidsql"); 
        String errorpage = conf.getInitParameter("error"); 
       // String de = conf.getInitParameter("debug"); 
        if(errorpage != null){ 
            error = errorpage; 
        } 
        
        /*if(sql != null){ 
            invalidsql = Arrays.asList(sql.split(" ")); 
            System.out.println("invalidsql.get(0)"+invalidsql.get(0));
        }*/ 
       /* if(de != null && Boolean.parseBoolean(de)){ 
            debug = true; 
            System.out.println("PreventSQLInject Filter staring..."); 
            System.out.println("print filter details"); 
            System.out.println("invalid words as fllows (split with blank):"); 
            for(String s : invalidsql){ 
                System.out.print(s+" "); 
            } 
            System.out.println(); 
            System.out.println("error page as fllows"); 
            System.out.println(error); 
            System.out.println(); 
        } */
    }
  
  
} 
