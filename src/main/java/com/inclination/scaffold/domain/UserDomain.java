package com.inclination.scaffold.domain;

import com.inclination.scaffold.infrastraction.repository.UserMapper;
import com.inclination.scaffold.infrastraction.repository.po.User;
import com.inclination.scaffold.infrastraction.repository.po.UserExample;

import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.constant.exception.TException;

/**
 * @Description: tianjingle业务实现
 * @Author: jeecg-boot
 * @Date:   2019-07-06
 * @Version: V1.0
 */
 
public class UserDomain {

    public boolean countByEqualName(User po,UserMapper userMapping){
        UserExample example=new UserExample();
		UserExample.Criteria criteria=example.createCriteria();
		criteria.andIdEqualTo(po.getId());
		return userMapping.countByExample(example)>0?true:false;
    }

	public void add(User po,UserMapper userMapping) throws TException {
		// TODO Auto-generated method stub
		if(countByEqualName(po,userMapping)){
			throw new TException(TErrorCode.ERROR_EXISIT_MENU_CODE,TErrorCode.ERROR_EXISIT_MENU_MSG);
		}else if(userMapping.insert(po)!=1){
			throw new TException(TErrorCode.ERROR_INSERT_MENU_CODE,TErrorCode.ERROR_INSERT_MENU_MSG);
		}
	}

	public void modify(User po,UserMapper userMapping) throws TException {
		// TODO Auto-generated method stub
		if(countByEqualName(po,userMapping)){
			throw new TException(TErrorCode.ERROR_EXISIT_MENU_CODE,TErrorCode.ERROR_EXISIT_MENU_MSG);
		}else if(userMapping.updateByPrimaryKey(po)!=1){
			throw new TException(TErrorCode.ERROR_UPDATE_MENU_CODE,TErrorCode.ERROR_UPDATE_MENU_MSG);
		}
	}

	public void delete(String id,UserMapper userMapping) throws TException {
		// TODO Auto-generated method stub
		if(userMapping.deleteByPrimaryKey(Integer.valueOf(id))!=1){
			throw new TException(TErrorCode.ERROR_DELETE_MENU_CODE,TErrorCode.ERROR_DELETE_MENU_MSG);
		}
	}
	}