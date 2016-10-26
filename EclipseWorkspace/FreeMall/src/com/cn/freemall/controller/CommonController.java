package com.cn.freemall.controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 16-8-7.
 */

@Controller
@RequestMapping("/common")
public class CommonController {
    @RequestMapping(value = "rooms",method = RequestMethod.POST)
    @ResponseBody
    public Object getRooms(String city){
        List<JSONObject> list = new ArrayList<JSONObject>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","1");
        jsonObject.put("name","第一会议室");
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("id","1");
        jsonObject2.put("name","第一会议室");
        list.add(jsonObject);
        list.add(jsonObject2);
        return list;
    }
}
