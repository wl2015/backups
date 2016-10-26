package com.cn.changhong.jdbc.mapper;



import com.cn.changhong.jdbc.entity.IntegralEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 16-7-27.
 */
public interface IntegralMapper {
    List<IntegralEntity> selectIntegralListByTime(Map<String, Object> map);
    List<IntegralEntity> selectIntegralList();
    void updateIntegralInfoByTime(Map<String, Object> map);
}
