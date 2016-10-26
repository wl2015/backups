package com.changhong.zw.jdbc.mapper;


import com.changhong.zw.jdbc.entity.HeatEntity;

import java.util.List;

/**
 * Created by admin on 16-7-27.
 */
public interface HeatMapper {
    List<HeatEntity> selectHeatList();
}
