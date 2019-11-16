package com.inclination.scaffold.domain;

import com.inclination.scaffold.infrastraction.repository.UserPoMapper;

import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.infrastraction.repository.po.UserPo;
import tk.mybatis.mapper.entity.Example;

/**
 * @Description: tianjingle业务实现
 * @Author: jeecg-boot
 * @Date:   2019-07-06
 * @Version: V1.0
 */
 
public class UserDomain {

    public boolean countByEqualName(UserPo po, UserPoMapper userMapping){
		Example example=new Example(UserPo.class);
		Example.Criteria criteria=example.createCriteria();
		criteria.andEqualTo("Id",po.getId());
		return userMapping.selectCountByExample(example)>0?true:false;
    }

	public void add(UserPo po,UserPoMapper userMapping) throws TException {
		// TODO Auto-generated method stub
		if(countByEqualName(po,userMapping)){
			throw new TException(TErrorCode.ERROR_EXISIT_MENU_CODE,TErrorCode.ERROR_EXISIT_MENU_MSG);
		}else if(userMapping.insert(po)!=1){
			throw new TException(TErrorCode.ERROR_INSERT_MENU_CODE,TErrorCode.ERROR_INSERT_MENU_MSG);
		}
	}

	public void modify(UserPo po,UserPoMapper userMapping) throws TException {
		// TODO Auto-generated method stub
		if(countByEqualName(po,userMapping)){
			throw new TException(TErrorCode.ERROR_EXISIT_MENU_CODE,TErrorCode.ERROR_EXISIT_MENU_MSG);
		}else if(userMapping.updateByPrimaryKey(po)!=1){
			throw new TException(TErrorCode.ERROR_UPDATE_MENU_CODE,TErrorCode.ERROR_UPDATE_MENU_MSG);
		}
	}

	public void delete(String id,UserPoMapper userMapping) throws TException {
		// TODO Auto-generated method stub
		if(userMapping.deleteByPrimaryKey(Integer.valueOf(id))!=1){
			throw new TException(TErrorCode.ERROR_DELETE_MENU_CODE,TErrorCode.ERROR_DELETE_MENU_MSG);
		}
	}
	}