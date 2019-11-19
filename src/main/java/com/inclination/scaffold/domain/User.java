package com.inclination.scaffold.domain;

import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.infrastraction.repository.UserPoMapper;
import com.inclination.scaffold.utils.ModelMapUtils;

public class User {
    
	private int id;
	
	private String loginId;
	
	private String userName;
	
	private String userPassword;
	
	private String userEmil;

    private Integer roId;



	public int getId() {
		return id;
	}

	public void setId(int userId) {
		this.id = userId;
	}

	public String getLoginid() {
		return loginId;
	}

	public void setLoginid(String loginid) {
		this.loginId = loginid;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public String getUserEmil() {
		return userEmil;
	}


	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setUserEmil(String userEmil) {
		this.userEmil = userEmil;
	}

	public Integer getRoId() {
		return roId;
	}

	public void setRoId(Integer roId) {
		this.roId = roId;
	}

	public void userCreate(UserPoMapper userMapper) throws TException{
		com.inclination.scaffold.infrastraction.repository.po.UserPo po=
				ModelMapUtils.map(this, com.inclination.scaffold.infrastraction.repository.po.UserPo.class);
		Integer count=(Integer) userMapper.findUserByNamePass(po);
        if(count!=0){
        	throw new TException(TErrorCode.ERROR_INSERT_USER_CODE,TErrorCode.ERROR_INSERT_USER_MSG);
        }else if(userMapper.insert(po)!=1){
			throw new TException(TErrorCode.ERROR_CREATE_USER_CODE,TErrorCode.ERROR_CREATE_USER_MSG);
		}
	}

	public void delete(UserPoMapper userMapping) throws TException {
		// TODO Auto-generated method stub
		if(userMapping.deleteByPrimaryKey(this.id)!=1){
			throw new TException(TErrorCode.ERROR_DELETE_USER_CODE,TErrorCode.ERROR_DELETE_USER_MSG);
		}
	}

	public void update(UserPoMapper userMapping) throws TException {
		// TODO Auto-generated method stub
		com.inclination.scaffold.infrastraction.repository.po.UserPo po=
				ModelMapUtils.map(this, com.inclination.scaffold.infrastraction.repository.po.UserPo.class);
		Integer count=(Integer) userMapping.findUserByNamePass(po);
        if(count!=1){
        	throw new TException(TErrorCode.ERROR_INSERT_USER_CODE,TErrorCode.ERROR_INSERT_USER_MSG);
        }else if(userMapping.updateByPrimaryKeySelective(po)!=1){
			throw new TException(TErrorCode.ERROR_UPDATE_USER_CODE,TErrorCode.ERROR_UPDATE_USER_MSG);
		}
	}
}
