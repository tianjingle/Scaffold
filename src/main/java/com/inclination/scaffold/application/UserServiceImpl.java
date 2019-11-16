package com.inclination.scaffold.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.inclination.scaffold.infrastraction.repository.UserMapper;

import com.inclination.scaffold.api.entity.UserVo;
import com.inclination.scaffold.api.entity.UserFindByPageVo;
import com.inclination.scaffold.application.service.UserService;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.domain.UserDomain;
import com.inclination.scaffold.infrastraction.repository.po.User;
import com.inclination.scaffold.infrastraction.repository.po.UserExample;
import com.inclination.scaffold.utils.ModelMapUtils;
import com.inclination.scaffold.utils.ViewData;

/**
 * @Description: tianjingle业务实现
 * @Author: jeecg-boot
 * @Date:   2019-07-06
 * @Version: V1.0
 */
@Service
public class UserServiceImpl implements UserService{

	/**
	 * @param mapper
	 */
	@Autowired
	private UserMapper userMapping;
	/**
	 * 业务逻辑
	 */
	private UserDomain domain=new UserDomain();
	
	@Override
	@Transactional
	public void UserAdd(UserVo entity) throws TException {
		// TODO Auto-generated method stub
		User po=ModelMapUtils.map(entity, User.class);
		domain.add(po,userMapping);
	}
	@Override
	@Transactional
	public void UserModify(UserVo entity) throws TException {
		// TODO Auto-generated method stub
		User po=ModelMapUtils.map(entity, User.class);
		domain.modify(po,userMapping);
	}
	@Transactional
	public void UserDelete(String id) throws TException {
		// TODO Auto-generated method stub
		domain.delete(id,userMapping);
	}
	public ViewData findByPage(UserFindByPageVo request) {
		// TODO Auto-generated method stub
		User po=ModelMapUtils.map(request, User.class);
		Page hpage=PageHelper.startPage((int)request.getPage(), request.getLimit());
		UserExample example=new UserExample();
		UserExample.Criteria criteria=example.createCriteria();
		List<User> list=userMapping.selectByExample(example);
		 ViewData response = new ViewData();
		response.setData(list);
		response.PageBaseQueryEntity(request.getPage(),request.getLimit(), 
				(int)hpage.getPages(),(int)hpage.getTotal());
		return response;
	}
	@Override
	public ViewData findAll(UserVo entity) {
		//TODO Auto-generated method stub
		User po=ModelMapUtils.map(entity, User.class);
		UserExample example=new UserExample();
		UserExample.Criteria criteria=example.createCriteria();
		List<User> list=userMapping.selectByExample(example);
		ViewData response = new ViewData();
		response.setData(list);
		return response;
		
	}
}
