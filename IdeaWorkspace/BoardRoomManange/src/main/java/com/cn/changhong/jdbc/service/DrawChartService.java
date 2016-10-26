package com.cn.changhong.jdbc.service;

import com.cn.changhong.jdbc.entity.*;
import com.cn.changhong.jdbc.mapper.*;
import com.cn.changhong.restapi.RestApiResponse;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 16-7-27.
 * 绘制图表专用服务
 */
@Service
public class DrawChartService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DrawChartService.class);

    @Resource
    private IntegralMapper integralMapper;


    /**
     * 获取折线图信息
     * */

    public RestApiResponse getBrokenLineInfo(Map<String, Object> map){
        RestApiResponse restApiResponse = new RestApiResponse();
        List<IntegralEntity> integralEntities = new ArrayList<IntegralEntity>();
        Map<String,Object> data = new HashMap<String,Object>();

        List<String> times = new ArrayList<String>();
        List<Integer> adds = new ArrayList<Integer>();
        List<Integer> reduces = new ArrayList<Integer>();
        List<Integer> actuals = new ArrayList<Integer>();

        integralEntities = integralMapper.selectIntegralListByTime(map);
        for(IntegralEntity entity:integralEntities){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String time = dateFormat.format(entity.getTime());
            times.add(time);
            adds.add(entity.getAdd());
            reduces.add(entity.getReduce());
            actuals.add(entity.getActual());
        }

        //拼接json
        JSONObject addList = new JSONObject();
        addList.put("itemName","积分收入");
        addList.put("itemCount", adds);

        JSONObject reduceList = new JSONObject();
        reduceList.put("itemName", "积分支出");
        reduceList.put("itemCount", reduces);

        JSONObject actualList = new JSONObject();
        actualList.put("itemName", "积分净值");
        actualList.put("itemCount", actuals);

        List<JSONObject> list = new ArrayList<JSONObject>();
        list.add(addList);
        list.add(reduceList);
        list.add(actualList);

        data.put("itemData", list);
        data.put("timeList", times);

        restApiResponse.setData(data);
        restApiResponse.setCode(0);
        return restApiResponse;
    }
    /**
     * 柱状图
     * */
     public RestApiResponse getPillarChartInfo(){
        RestApiResponse restApiResponse = new RestApiResponse();
        List<IntegralEntity> integralEntities = new ArrayList<IntegralEntity>();
        Map<String,Object> data = new HashMap<String,Object>();

        List<String> times = new ArrayList<String>();
        List<Integer> adds = new ArrayList<Integer>();
        List<Integer> reduces = new ArrayList<Integer>();
        List<Integer> actuals = new ArrayList<Integer>();
        integralEntities = integralMapper.selectIntegralList();
         LOGGER.debug("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        LOGGER.debug("aaaaaaaaaaaaa:"+integralEntities.size());
        for(IntegralEntity entity:integralEntities){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String time = dateFormat.format(entity.getTime());
            times.add(time);
            adds.add(entity.getAdd());
            reduces.add(entity.getReduce());
            actuals.add(entity.getActual());
        }

        //拼接json
        JSONObject addList = new JSONObject();
        addList.put("itemName","积分收入");
        addList.put("itemCount", adds);

        JSONObject reduceList = new JSONObject();
        reduceList.put("itemName", "积分支出");
        reduceList.put("itemCount", reduces);

        JSONObject actualList = new JSONObject();
        actualList.put("itemName", "积分净值");
        actualList.put("itemCount", actuals);

        List<JSONObject> list = new ArrayList<JSONObject>();
        list.add(addList);
        list.add(reduceList);
        list.add(actualList);

        data.put("itemData", list);
        data.put("timeList", times);

        restApiResponse.setData(data);
        restApiResponse.setCode(0);
        return restApiResponse;
    }

    //修改积分信息
    public RestApiResponse changeIntegralInfo(Map<String, Object> map){
        RestApiResponse restApiResponse = new RestApiResponse();
        integralMapper.updateIntegralInfoByTime(map);
        restApiResponse.setCode(0);
        return restApiResponse;
    }
  }
