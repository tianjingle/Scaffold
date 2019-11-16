package com.inclination.scaffold.utils;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.ids.SelectByIdsMapper;

/**
 * 自定义mapper
 * @author 123
 * @param <T>
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T>, SelectByIdsMapper<T> {
}