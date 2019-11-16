package com.inclination.scaffold.application;

import java.util.List;

import com.inclination.scaffold.infrastraction.repository.ToolProjectPoMapper;
import com.inclination.scaffold.infrastraction.repository.po.ToolProjectPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inclination.scaffold.api.response.toolproject.ToolProjectQryAllResponse;
import com.inclination.scaffold.api.response.toolproject.ToolProjectResponse;
import com.inclination.scaffold.application.toolproject.ToolProjectDto;
import com.inclination.scaffold.application.toolproject.ToolProjectService;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.domain.DToolProject;
import com.inclination.scaffold.utils.ModelMapUtils;
import tk.mybatis.mapper.entity.Example;

@Service
public class ToolProjectServiceImp implements ToolProjectService {

	/**
	 * 创建工具软件管理的数据库服务
	 */
	@Autowired
	private ToolProjectPoMapper toolProjectPoMapper;
	/**
	 * 创建工具软件的添加
	 * @throws TException 
	 */
	@Override
	@Transactional
	public void addToolProject(ToolProjectDto map) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(map, DToolProject.class).toolProjectAdd(toolProjectPoMapper);
	}
	@Override
	@Transactional
	public void modifyToolProject(ToolProjectDto map) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(map, DToolProject.class).toolProjectModify(toolProjectPoMapper);
	}
	@Override
	@Transactional
	public void deleteToolProject(ToolProjectDto dto) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(dto, DToolProject.class).toolProjectDelete(toolProjectPoMapper);
	}
	@Override
	public ToolProjectQryAllResponse findAll() {
		// TODO Auto-generated method stub
		Example example = new Example(ToolProjectPo.class);
		Example.Criteria criteria=example.createCriteria();
		criteria.andIsNotNull("Id");
		List<ToolProjectPo> list=toolProjectPoMapper.selectByExample(example);
		ToolProjectQryAllResponse response=new ToolProjectQryAllResponse();
		response.setList(ModelMapUtils.map(list, ToolProjectResponse.class));
		return response;
	}

}
