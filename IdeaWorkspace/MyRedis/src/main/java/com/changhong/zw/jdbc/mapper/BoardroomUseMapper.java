package com.changhong.zw.jdbc.mapper;

import com.changhong.zw.jdbc.entity.BoardroomUseEntity;

import java.util.Map;

/**
 * Created by admin on 16-8-8.
 */
public interface BoardroomUseMapper {
    BoardroomUseEntity selectRoomUseInfoByRoomId(Map<String,Object> map);
}
