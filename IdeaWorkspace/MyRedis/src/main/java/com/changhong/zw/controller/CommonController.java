package com.changhong.zw.controller;

import com.changhong.zw.jdbc.mapper.BoardroomMapper;
import com.changhong.zw.jdbc.service.CommonService;
import com.changhong.zw.restapi.RestApiResponse;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 16-8-7.
 */

@Controller
@RequestMapping("/common")
public class CommonController {
    @Resource
    private CommonService commonService;

    /**
     * 根据city获取会议室列表
     * */
    @RequestMapping(value = "rooms",method = RequestMethod.POST)
    @ResponseBody
    public RestApiResponse getRooms(String city){
        RestApiResponse restApiResponse = new RestApiResponse();
        try {
            restApiResponse = commonService.getRooms(city);
        }catch (Exception e){

        }
        return restApiResponse;
    }

    /***
     * 根据room_i获取会议室的信息和预定信息
     * */
    @RequestMapping(value = "/roomInfo",method = RequestMethod.POST)
    @ResponseBody
    public Object getRoomInfo(String id){
        RestApiResponse restApiResponse = new RestApiResponse();
        try {
            restApiResponse = commonService.getRooms(id);
        }catch (Exception e){

        }
        return restApiResponse;
    }
}