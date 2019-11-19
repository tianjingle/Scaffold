package com.inclination.scaffold.application;

import java.util.List;

import com.inclination.scaffold.infrastraction.repository.UserPoMapper;
import com.inclination.scaffold.infrastraction.repository.po.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.inclination.scaffold.api.entity.UserVo;
import com.inclination.scaffold.api.entity.UserFindByPageVo;
import com.inclination.scaffold.application.service.UsersService;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.domain.UserDomain;
import com.inclination.scaffold.utils.ModelMapUtils;
import com.inclination.scaffold.utils.ViewData;
import tk.mybatis.mapper.entity.Example;

/**
 * @Description: tianjingle业务实现
 * @Author: jeecg-boot
 * @Date:   2019-07-06
 * @Version: V1.0
 */
@Service
public class UsersServiceImpl implements UsersService {

	/**
	 * @param mapper
	 */
	@Autowired
	private UserPoMapper userMapping;
	/**
	 * 业务逻辑
	 */
	private UserDomain domain=new UserDomain();
	
	@Override
	@Transactional
	public void UserAdd(UserVo entity) throws TException {
		// TODO Auto-generated method stub
		UserPo po=ModelMapUtils.map(entity, UserPo.class);
		domain.add(po,userMapping);
	}
	@Override
	@Transactional
	public void UserModify(UserVo entity) throws TException {
		// TODO Auto-generated method stub
		UserPo po=ModelMapUtils.map(entity, UserPo.class);
		domain.modify(po,userMapping);
	}
	@Override
	@Transactional
	public void UserDelete(String id) throws TException {
		// TODO Auto-generated method stub
		domain.delete(id,userMapping);
	}
	@Override
	public ViewData findByPage(UserFindByPageVo request) {
		// TODO Auto-generated method stub
		UserPo po=ModelMapUtils.map(request, UserPo.class);
		Page hpage=PageHelper.startPage((int)request.getPage(), request.getLimit());
		Example example=new Example(UserPo.class);
		Example.Criteria criteria=example.createCriteria();
		criteria.orLike("loginId", po.getLoginId())
				.orLike("userName", po.getUserName())
				.orLike("userEmil", po.getUserEmil());
		List<UserPo> list=userMapping.selectByExample(example);
		 ViewData response = new ViewData();
		response.setData(list);
		response.PageBaseQueryEntity(request.getPage(),request.getLimit(), 
				(int)hpage.getPages(),(int)hpage.getTotal());
		return response;
	}
	@Override
	public ViewData findAll(UserVo entity) {
		List<UserPo> list=userMapping.selectAll();
		ViewData response = new ViewData();
		response.setData(list);
		return response;
	}
}
