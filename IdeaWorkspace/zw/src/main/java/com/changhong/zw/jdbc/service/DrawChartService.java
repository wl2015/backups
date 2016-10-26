package com.changhong.zw.jdbc.service;

import com.changhong.zw.jdbc.entity.*;
import com.changhong.zw.jdbc.mapper.*;
import com.changhong.zw.restapi.RestApiResponse;
import net.sf.json.JSONObject;
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
    @Resource
    private HeatMapper heatMapper;

    @Resource
    private IntegralMapper integralMapper;

    @Resource
    private RelationMapper relationMapper;

    @Resource
    private PeopleMapper peopleMapper;

    @Resource
    private RelationLinkMapper relationLinkMapper;

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
     * 获取热力图信息
     * */
    public RestApiResponse getHeatChartInfo(){
      RestApiResponse restApiResponse = new RestApiResponse();
      List<HeatEntity> heatEntities = new ArrayList<HeatEntity>();

      heatEntities = heatMapper.selectHeatList();

      JSONObject json = new JSONObject();
      List<JSONObject> list = new ArrayList<JSONObject>();
      for(HeatEntity entity:heatEntities){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",entity.getName());
        jsonObject.put("value",entity.getHeat());
        list.add(jsonObject);
      }
      json.put("listData",list);
      restApiResponse.setData(json);
      restApiResponse.setCode(0);
      return restApiResponse;
    }

    /**
     * 关系图
     * */
    public RestApiResponse getRelationChartInfo(){
        RestApiResponse restApiResponse = new RestApiResponse();

        List<RelationEntity> relationEntities = new ArrayList<RelationEntity>();
        relationEntities = relationMapper.selectRelationList();
        List<RelationLinkEntity> relationLinkEntities = new ArrayList<RelationLinkEntity>();
        relationLinkEntities = relationLinkMapper.selectLinkList();

        List<JSONObject> relationlist = new ArrayList<JSONObject>();
        for(RelationEntity entity:relationEntities){
            JSONObject json = new JSONObject();
            json.put("name",entity.getName());
            json.put("x",entity.getLeft());
            json.put("y",entity.getRight());
            relationlist.add(json);
        }

        List<JSONObject> linkList = new ArrayList<JSONObject>();
        for(RelationLinkEntity entity : relationLinkEntities){
            JSONObject json = new JSONObject();
            json.put("source",entity.getSource());
            json.put("target",entity.getTarget());
            linkList.add(json);
        }

        Map<String,Object> map = new HashMap<String,Object>();

        map.put("data", relationlist);
        map.put("links", linkList);
        restApiResponse.setData(map);
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
     * 饼图
     * */
    public RestApiResponse getCircularChartInfo(){
        RestApiResponse restApiResponse = new RestApiResponse();

        List<PeopleEntity> peopleEntities = new ArrayList<PeopleEntity>();
        peopleEntities = peopleMapper.selectPeopleList();
        int manCount = 0;
        int womanCount = 0;
        for(PeopleEntity entity : peopleEntities){
            if(entity.getSex() == 0){
                manCount = manCount + 1;
            }
            else{
                womanCount = womanCount + 1;
            }
        }
        JSONObject jsonMan = new JSONObject();
        jsonMan.put("itemName","男");
        jsonMan.put("itemCount",manCount);
        JSONObject jsonWoman = new JSONObject();
        jsonWoman.put("itemName","女");
        jsonWoman.put("itemCount",womanCount);
        List<JSONObject> list = new ArrayList<JSONObject>();
        list.add(jsonMan);
        list.add(jsonWoman);

        restApiResponse.setData(list);
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
