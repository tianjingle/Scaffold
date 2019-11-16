package com.inclination.scaffold.domain;

import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.infrastraction.repository.UserMapper;
import com.inclination.scaffold.utils.ModelMapUtils;

public class User {
    
	private int userId;
	
	private String loginid;
	
	private String username;
	
	private String userpassword;
	
	private String useremil;

    private Integer roid;
    

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUseremil() {
		return useremil;
	}

	public void setUseremil(String useremil) {
		this.useremil = useremil;
	}

	public Integer getRoid() {
		return roid;
	}

	public void setRoid(Integer roid) {
		this.roid = roid;
	}

	public void userCreate(UserMapper userMapper) throws TException{
		com.inclination.scaffold.infrastraction.repository.po.User po=
				ModelMapUtils.map(this, com.inclination.scaffold.infrastraction.repository.po.User.class);
		Integer count=(Integer) userMapper.findUserByNamePass(po);
        if(count!=0){
        	throw new TException(TErrorCode.ERROR_INSERT_USER_CODE,TErrorCode.ERROR_INSERT_USER_MSG);
        }else if(userMapper.insert(po)!=1){
			throw new TException(TErrorCode.ERROR_CREATE_USER_CODE,TErrorCode.ERROR_CREATE_USER_MSG);
		}
	}

	public void delete(UserMapper userMapping) throws TException {
		// TODO Auto-generated method stub
		if(userMapping.deleteByPrimaryKey(this.userId)!=1){
			throw new TException(TErrorCode.ERROR_DELETE_USER_CODE,TErrorCode.ERROR_DELETE_USER_MSG);
		}
	}

	public void update(UserMapper userMapping) throws TException {
		// TODO Auto-generated method stub
		com.inclination.scaffold.infrastraction.repository.po.User po=
				ModelMapUtils.map(this, com.inclination.scaffold.infrastraction.repository.po.User.class);
		Integer count=(Integer) userMapping.findUserByNamePass(po);
        if(count!=1){
        	throw new TException(TErrorCode.ERROR_INSERT_USER_CODE,TErrorCode.ERROR_INSERT_USER_MSG);
        }else if(userMapping.updateByPrimaryKeySelective(po)!=1){
			throw new TException(TErrorCode.ERROR_UPDATE_USER_CODE,TErrorCode.ERROR_UPDATE_USER_MSG);
		}
	}
}
