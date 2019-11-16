package com.inclination.scaffold.utils;



/**
 * 自定义mapper
 * @author 123
 * @param <T>
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T>, SelectByIdsMapper<T> {
}