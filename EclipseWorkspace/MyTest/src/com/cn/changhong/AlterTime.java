package com.cn.changhong;

import net.sf.json.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 本程序用于校准windows系统的时间
 * Created by Administrator on 16-9-12.
 */
public class AlterTime {
    /**
     * post请求url
     * @param url
     * @return
     */
    public String doPost(String url)
    {
        String res=null;
        CloseableHttpClient closeableHttpClient= HttpClients.createDefault();
        CloseableHttpResponse response=null;
        HttpPost post=new HttpPost(url);
        try {
            response=closeableHttpClient.execute(post);
            if(response.getStatusLine().getStatusCode()==200)
            {
                res= EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 更新windows系统的时间
     * @param date
     */
    public void alterTime(Date date)
    {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");
        try {
            Runtime.getRuntime().exec("cmd /c date "+dateFormat.format(date));
            Runtime.getRuntime().exec("cmd /c time "+timeFormat.format(date));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(dateFormat.format(date));
        System.out.println(timeFormat.format(date));
    }

    public static void main(String[] args)
    {
        AlterTime alterTime=new AlterTime();
        String res=alterTime.doPost("http://api.k780.com:88/?app=life.time&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json");

        System.out.println("接口获取的数据为："+res);
        JSONObject jsonObject=JSONObject.fromObject(res);
        long time=Long.parseLong(jsonObject.getJSONObject("result").getString("timestamp"));
        Date date=new Date(time*1000l);
        System.out.println("提取出的时间为："+date);
        System.out.println("当前系统时间为："+new Date());
        alterTime.alterTime(date);
        System.out.println("校准后时间为："+new Date());

    }
}
