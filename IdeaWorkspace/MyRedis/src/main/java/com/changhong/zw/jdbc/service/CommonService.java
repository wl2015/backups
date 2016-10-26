package com.changhong.zw.jdbc.service;

import com.changhong.zw.jdbc.entity.BoardroomEntity;
import com.changhong.zw.jdbc.entity.BoardroomUseEntity;
import com.changhong.zw.jdbc.mapper.BoardroomMapper;
import com.changhong.zw.jdbc.mapper.BoardroomUseMapper;
import com.changhong.zw.jdbc.mapper.UserMapper;
import com.changhong.zw.restapi.RestApiResponse;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 16-8-8.
 */
@Service
public class CommonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonService.class);
    @Resource
    private UserMapper userMapper;
    @Resource
    private BoardroomMapper boardroomMapper;
    @Resource
    private BoardroomUseMapper boardroomUseMapper;


    public RestApiResponse getRooms(String city){
        RestApiResponse restApiResponse = new RestApiResponse();
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("city",city);
        List<BoardroomEntity> list = boardroomMapper.selectRoomInfoByCity(map);
        List<JSONObject> returnList = new ArrayList<JSONObject>();
        for(int i=0; i < list.size(); i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", list.get(i).getRoomId());
            jsonObject.put("name", list.get(i).getName());
            returnList.add(jsonObject);
        }
        restApiResponse.setCode(0);
        restApiResponse.setData(returnList);
        return restApiResponse;
    }

    public RestApiResponse getRoomInfo(String id){
        RestApiResponse restApiResponse = new RestApiResponse();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("roomId",id);
        BoardroomUseEntity boardroomUseEntity = boardroomUseMapper.selectRoomUseInfoByRoomId(map);
        BoardroomEntity boardroomEntity = boardroomMapper.selectRoomInfoByRoomId(map);


        String name="第一会议室";
        String address = "地址：xxxxx";
        String type = "类型:小型会议室（至多容纳10人）";
        map.put("name", name);
        map.put("address", address);
        map.put("type", type);
        JSONObject jsonObject = new JSONObject();
        int[] aa= {83,84,91};
        jsonObject.put("times",aa);
        jsonObject.put("userName","小明");

        JSONObject jsonObject2 = new JSONObject();
        int[] bb= {93,94,101,102};
        jsonObject2.put("times",bb);
        jsonObject2.put("userName","小张");
        List<JSONObject> list = new ArrayList<JSONObject>();
        list.add(jsonObject);
        list.add(jsonObject2);
        map.put("data",list);

        return restApiResponse;
    }
}
