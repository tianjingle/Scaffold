package com.inclination.scaffold.domain;

import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.infrastraction.repository.UserPoMapper;
import com.inclination.scaffold.infrastraction.repository.po.UserPo;
import com.inclination.scaffold.utils.ModelMapUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public class User {
    
	private int id;
	
	private String loginId;
	
	private String userName;
	
	private String userPassword;
	
	private String userEmil;

    private Integer roId;

	private int orgId;

	private String orgName;

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

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
		Example example=new Example(UserPo.class);
		Example.Criteria criteria=example.createCriteria();
		Integer count=0;
		if (po.getRoId()==2){
			criteria=example.createCriteria();
			criteria.andEqualTo("roId",2);
			criteria.andEqualTo("orgName",po.getOrgName());
			count=(Integer) userMapper.selectCountByExample(example);
			if (count>0){
				throw new TException(TErrorCode.ERROR_CREATE_SUPER_USER_CODE,TErrorCode.ERROR_CREATE_SUPER_USER_MSG);
			}
		}
		criteria.andEqualTo("loginId",po.getLoginId()).andEqualTo("userPassword",po.getUserPassword());
		count=(Integer) userMapper.selectCountByExample(example);
        if(count>0){
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

	public UserPo update(UserPoMapper userMapping) throws TException {
		// TODO Auto-generated method stub
		com.inclination.scaffold.infrastraction.repository.po.UserPo po=
				ModelMapUtils.map(this, com.inclination.scaffold.infrastraction.repository.po.UserPo.class);
		Example example=null;
		example=new Example(UserPo.class);
		example.createCriteria().andEqualTo("id",po.getId());
		List<UserPo> target= userMapping.selectByExample(example);
		example.createCriteria().andEqualTo("loginId",po.getLoginId()).andEqualTo("userPassword",po.getUserPassword());
		List<UserPo>users= userMapping.selectByExample(example);
        if(users.size()>1){
        	throw new TException(TErrorCode.ERROR_INSERT_USER_CODE,TErrorCode.ERROR_INSERT_USER_MSG);
        }else if(userMapping.updateByPrimaryKeySelective(po)!=1){
			throw new TException(TErrorCode.ERROR_UPDATE_USER_CODE,TErrorCode.ERROR_UPDATE_USER_MSG);
		}
        return target.get(0);
	}
}
