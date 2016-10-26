package com.changhong.zw.jdbc.mapper;

import com.changhong.zw.jdbc.entity.UserEntity;

import java.util.Map;

/**
 * Created by admin on 16-8-8.
 */
public interface UserMapper {
    UserEntity selectUserInfoByUserId(Map<String,Object> map);
}
