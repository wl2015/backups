package com.changhong.zw.controller;

import com.changhong.zw.jdbc.service.DrawChartService;
import com.changhong.zw.restapi.RestApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by admin on 16-7-27.
 */
@Controller
@RequestMapping(value = "/draw/")
public class DrawChartController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DrawChartController.class);

    @Autowired
    private DrawChartService drawChartService;

    /**
     * 折线图
     *String begin,String end
     * */
    @RequestMapping(value = "drawbrokenline", method = RequestMethod.POST)
    @ResponseBody
    public RestApiResponse drawBrokenLine(String begin,String end){
        RestApiResponse restApiResponse = new RestApiResponse();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("startTime",begin);
        map.put("endTime",addOneDay(end));
        try{
        restApiResponse = drawChartService.getBrokenLineInfo(map);
        }catch (Exception e){
            LOGGER.error("brokenline error",e.getMessage());
        }
        return restApiResponse;
    }

    /**
     * 热力图
     * */
     @RequestMapping(value = "drawheatchart", method = RequestMethod.GET)
     @ResponseBody
     public RestApiResponse drawHeatChart(){
         RestApiResponse restApiResponse = new RestApiResponse();
         try{
            restApiResponse = drawChartService.getHeatChartInfo();
         }catch (Exception e){
             LOGGER.error("heatChart error",e.getMessage());
         }
         return restApiResponse;
     }
    /**
     * 关系图
     * */
    @RequestMapping(value = "drawrelationchart",method = RequestMethod.GET)
    @ResponseBody
    public RestApiResponse drawRelationChart(){
        RestApiResponse restApiResponse = new RestApiResponse();
        try{
            restApiResponse = drawChartService.getRelationChartInfo();
        }catch (Exception e){
            LOGGER.error("drawRelationChart error:",e.getMessage());
        }
        return restApiResponse;
    }
    /**
     * 柱状图
     * */
    @RequestMapping(value = "drawpillarchart",method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public RestApiResponse drawPillarChart(){
        RestApiResponse restApiResponse = new RestApiResponse();
        try{
            restApiResponse = drawChartService.getPillarChartInfo();
        }catch (Exception e){
            LOGGER.error("",e.getMessage());
        }
        return restApiResponse;
    }
    /**
     * 饼图
     * */
    @RequestMapping(value = "drawcircularchart",method = RequestMethod.GET)
    @ResponseBody
    public RestApiResponse drawCircularChart(){
        RestApiResponse restApiResponse = new RestApiResponse();
        try{
            restApiResponse = drawChartService.getCircularChartInfo();
        }catch (Exception e){
            LOGGER.error("",e.getMessage());
        }
        return restApiResponse;
    }

    /**
     * 把yyyyMMdd格式的日期，加一天
     * */
    private String addOneDay(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cl = Calendar.getInstance();
        try {
            Date date = sdf.parse(dateStr);
            cl.setTime(date);
            cl.add(Calendar.DAY_OF_MONTH, 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(cl.getTime());
    }

    /**
     * 修改积分信息
     * */
    @RequestMapping(value = "changeintegral",method = RequestMethod.POST)
    @ResponseBody
    public RestApiResponse changeIntegral(String add,String reduce){
        RestApiResponse restApiResponse = new RestApiResponse();
        try{
            String time = "20160727";
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("add",Integer.parseInt(add));
            map.put("reduce",Integer.parseInt(reduce));
            map.put("actual",Integer.parseInt(add)+Integer.parseInt(reduce));
            map.put("startTime",time);
            map.put("endTime",addOneDay(time));
            restApiResponse = drawChartService.changeIntegralInfo(map);
        }catch (Exception e){
            LOGGER.error("updateIntegral error：",e.getMessage());
        }
        return restApiResponse;
    }
  }
