package com.inclination.scaffold.domain;

import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.infrastraction.repository.ResourcePoMapper;
import com.inclination.scaffold.infrastraction.repository.po.ResourcePo;
import com.inclination.scaffold.utils.ModelMapUtils;
import tk.mybatis.mapper.entity.Example;

public class DResource {

	private Integer id;

	private String resourceName;

	private String resourceUrl;

	private String content;

	private String flag;


	public Integer getId() {
		return id;
	}

	public String getResourceName() {
		return resourceName;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public String getContent() {
		return content;
	}

	public String getFlag() {
		return flag;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 资源的添加
	 * 
	 * @param resourceMapper
	 *            数据库映射
	 * @throws TException
	 *             抛出错误
	 */
	public void resourceAdd(ResourcePoMapper resourceMapper) throws TException {
		// TODO Auto-generated method stub
		ResourcePo po = ModelMapUtils.map(this, ResourcePo.class);
		Example example =new Example(ResourcePo.class);
		Example.Criteria criteria=example.createCriteria();
		criteria.andEqualTo("resourceName",this.resourceName);
		if (resourceMapper.selectCountByExample(example)>0) {
			throw new TException(TErrorCode.ERROR_EXISIT_RESOURCE_CODE, TErrorCode.ERROR_EXISIT_RESOURCE_MSG);
		} else if (resourceMapper.insert(po) != 1) {
			throw new TException(TErrorCode.ERROR_INSERT_RESOURCE_CODE, TErrorCode.ERROR_INSERT_RESOURCE_MSG);
		}
	}

	/**
	 * 资源的修改
	 * 
	 * @param resourceMapper
	 *            数据库映射
	 * @throws TException
	 */
	public void resourceModify(ResourcePoMapper resourceMapper) throws TException {
		// TODO Auto-generated method stub
		ResourcePo po = ModelMapUtils.map(this, ResourcePo.class);
		Example example =new Example(ResourcePo.class);
		example.createCriteria().andEqualTo("resourceName",this.resourceName);
		if (resourceMapper.selectCountByExample(po) != 0) {
			throw new TException(TErrorCode.ERROR_EXISIT_RESOURCE_CODE, TErrorCode.ERROR_EXISIT_RESOURCE_MSG);
		} else if (resourceMapper.updateByPrimaryKey(po) != 1) {
			throw new TException(TErrorCode.ERROR_UPDATE_RESOURCE_CODE, TErrorCode.ERROR_UPDATE_RESOURCE_MSG);
		}
	}

	/**
	 * 资源的删除
	 * 
	 * @param resourceMapper
	 *            数据库映射
	 * @throws TException
	 */
	public void resourceDelete(ResourcePoMapper resourceMapper) throws TException {
		// TODO Auto-generated method stub
		if (resourceMapper.deleteByPrimaryKey(this.id) != 1) {
			throw new TException(TErrorCode.ERROR_DELETE_RESOURCE_CODE, TErrorCode.ERROR_DELETE_RESOURCE_MSG);
		}
	}

}
