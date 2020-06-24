package com.inclination.scaffold.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: tianjingle
 * @Date: 2019/6/27 8:22
 */

/**
 * @author 123
 */
public class ModelMapUtils {

	private static ModelMapper modelMapper=new ModelMapper();

	public static <T,V> T map(V v,Class<T> cls){
		if(v==null){
			return null;
		}
		T object = null;
		try {
			object = cls.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		BeanUtils.copyProperties(v,object);
		return object;
	}
	public static <T,V> List<T>map(List<V> lv,Class<T> cls){
		if(lv==null){
			return new ArrayList<T>();
		}
		List<T> result=new ArrayList<>();
		for(V v:lv){
			result.add(modelMapper.map(v,cls));
		}
		return result;
	}
	public static <T,V>List<T>mapNew(List<V> lv,Class<?> clazz) throws IllegalAccessException, InstantiationException {
        if(lv==null){
            return new ArrayList<T>();
        }
        List<T> result=new ArrayList<>();
		Object c= null;
        for(V v:lv){
            c=(T) clazz.newInstance();
			BeanUtils.copyProperties(v, c);
            result.add((T) c);
        }
        return result;
    }
	public static Map<String,Object> obj2Map(Object obj){
        if(obj == null){
                    return null;
        }
         Map<String, Object> map = new HashMap<String, Object>(16);
         try {
             BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
             PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                 String key = property.getName();
                 // 过滤class属性
                 if (!"class".equals(key)) {
                     // 得到property对应的getter方法
                     Method getter = property.getReadMethod();
                     Object value = getter.invoke(obj);
                     map.put(key, value);
                 }
             }
         } catch (Exception e) {
             System.out.println("transBean2Map Error " + e);
         }
         return map;
    }

	public static Object map2Obj(Map<String,Object> map,Class<?> clazz){
		Object obj=null;
		if(map==null){
			return null;
		}
		try {
			obj=clazz.newInstance();
			BeanInfo beaninfo=Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDes=beaninfo.getPropertyDescriptors();
			for(PropertyDescriptor property:propertyDes){
				Method setter=property.getWriteMethod();
				if(setter!=null){
					setter.invoke(obj, map.get(property.getName()));
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static Map<? extends String,? extends String[]> obj2Map(Map map, Class<String[]> aClass) {
		if(map==null){
			return null;
		}
		Map<String,String[]>tempMap=new HashMap<>(16);
			for(Object key : map.keySet()){
				tempMap.put((String) key,new String[]{map.get(key).toString()});
			}
		return tempMap;
	}
}
