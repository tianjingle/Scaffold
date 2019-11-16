package com.inclination.scaffold.domain;

import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.infrastraction.repository.ToolProjectPoMapper;
import com.inclination.scaffold.infrastraction.repository.po.ToolProjectPo;
import com.inclination.scaffold.utils.ModelMapUtils;
import tk.mybatis.mapper.entity.Example;

public class DToolProject {
	
	private Integer id;
	
	private String name;

	private String url;

	private String userName;
	
	private String userPassword;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void toolProjectAdd(ToolProjectPoMapper toolProjectPoMapper) throws TException {
		// TODO Auto-generated method stub
		ToolProjectPo po=ModelMapUtils.map(this, ToolProjectPo.class);
		Example example=new Example(ToolProjectPo.class);
		Example.Criteria criteria=example.createCriteria();
		criteria.andEqualTo("name",this.name);
		if(toolProjectPoMapper.selectCountByExample(example)>0){
			throw new TException(TErrorCode.ERROR_EXISIT_TOOLPROJECTURL_CODE,TErrorCode.ERROR_EXISIT_TOOLPROJECTURL_MSG);
		}else{
			if(toolProjectPoMapper.insert(po)!=1){
				throw new TException(TErrorCode.ERROR_INSERT_TOOLPROJECTURL_CODE,TErrorCode.ERROR_INSERT_TOOLPROJECTURL_MSG);
			}
		}
		
	}

	public void toolProjectModify(ToolProjectPoMapper toolprojecturlMapper) throws TException {
		// TODO Auto-generated method stub
		ToolProjectPo po=ModelMapUtils.map(this, ToolProjectPo.class);
		Example example=new Example(ToolProjectPo.class);
		Example.Criteria criteria=example.createCriteria();
		criteria.andEqualTo("name",this.name);
		if(toolprojecturlMapper.selectCountByExample(example)>0){
			throw new TException(TErrorCode.ERROR_EXISIT_TOOLPROJECTURL_CODE,TErrorCode.ERROR_EXISIT_TOOLPROJECTURL_MSG);
		}else{
			if(toolprojecturlMapper.updateByPrimaryKey(po)!=1){
				throw new TException(TErrorCode.ERROR_UPDATE_TOOLPROJECTURL_CODE,TErrorCode.ERROR_UPDATE_TOOLPROJECTURL_MSG);
			}
		}
	}

	public void toolProjectDelete(ToolProjectPoMapper toolprojecturlMapper) throws TException {
		// TODO Auto-generated method stub
		ToolProjectPo po=ModelMapUtils.map(this, ToolProjectPo.class);
		if(toolprojecturlMapper.deleteByPrimaryKey(this.id)!=1){
			throw new TException(TErrorCode.ERROR_DELETE_TOOLPROJECTURL_CODE,TErrorCode.ERROR_DELETE_TOOLPROJECTURL_MSG);
		}
	}
	
	
}
