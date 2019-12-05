package com.htyl.park.common.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @description 创建表的mapper
 * @author: weiguang
 * @create: 10:33 上午 2019/11/15
 **/
public interface BaseMapper {

    int createTable(@Param("partition") Integer partition);
}
