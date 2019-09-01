package com.inclination.scaffold.utils;

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

import org.modelmapper.ModelMapper;

public class ModelMapUtils {

	private static ModelMapper modelMapper=new ModelMapper();
	
	public static <T,V> T map(V v,Class<T> cls){
		if(v==null){
			return null;
		}
		return modelMapper.map(v,cls);
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
	public static Map<String,Object> obj2Map(Object obj){
		if(obj==null){
			return null;
		}
		Map<String,Object>map=new HashMap<>();
		try {
			BeanInfo beaninfo=Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDes=beaninfo.getPropertyDescriptors();
			for(PropertyDescriptor property:propertyDes){
				String key=property.getName();
				if(key.compareToIgnoreCase("class")==1){
					continue;
				}
				Method getter=property.getReadMethod();
				Object value=null;
				value=getter!=null?getter.invoke(obj):null;
				map.put(key, value);
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}
