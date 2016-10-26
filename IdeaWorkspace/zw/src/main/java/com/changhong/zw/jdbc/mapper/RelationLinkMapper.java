package com.changhong.zw.jdbc.mapper;

import com.changhong.zw.jdbc.entity.RelationLinkEntity;

import java.util.List;

/**
 * Created by admin on 16-7-27.
 */
public interface RelationLinkMapper {
    List<RelationLinkEntity> selectLinkList();
}
