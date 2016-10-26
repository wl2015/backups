package com.changhong.zw.jdbc.mapper;

import com.changhong.zw.jdbc.entity.BoardroomEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 16-8-8.
 */
public interface BoardroomMapper {
    List<BoardroomEntity> selectRoomInfoByCity(Map<String,Object> map);

    BoardroomEntity selectRoomInfoByRoomId(Map<String,Object> map);
}
