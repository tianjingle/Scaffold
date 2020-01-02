package com.inclination.scaffold.application;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import com.inclination.scaffold.application.project.ProjectManagerService;
import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.infrastraction.repository.OrganizationPoMapper;
import com.inclination.scaffold.infrastraction.repository.UserPoMapper;
import com.inclination.scaffold.infrastraction.repository.po.OrganizationPo;
import com.inclination.scaffold.infrastraction.repository.po.UserPo;
import com.inclination.scaffold.utils.ViewData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.inclination.scaffold.api.request.user.UserQryByPages;
import com.inclination.scaffold.api.response.user.UserManageResponse;
import com.inclination.scaffold.api.response.user.UserManagerQryResponse;
import com.inclination.scaffold.application.users.UserDto;
import com.inclination.scaffold.application.users.UserService;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.domain.User;
import com.inclination.scaffold.utils.ModelMapUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * 
 * @author tianjingle
 * @time 2019/5/15 all right protect 
 * @discription user manage
 */
@Service
public class UserServiceImp implements UserService {



	/**
	 * 数据库
	 */
	@Autowired
	private UserPoMapper userMapping;


	/**
	 * 项目管理服务
	 */
	@Autowired
	private ProjectManagerService projectManagerService;


	@Override
	@Transactional
	public void createUser(UserDto dto) throws Exception {
		// TODO Auto-generated method stub
		ModelMapUtils.map(dto, User.class).userCreate(userMapping);
		/**
		 * 用户主要有超级管理员、高级管理员（部门负责人）、普通开发者
		 * 创建用户的脚手架的用户环境（jenkins、git、apollo）
		 */
		if (dto.getRoId()==2||dto.getRoId()==3){
			projectManagerService.createUserOtherSystem(dto);
		}
		if(dto.getRoId()==2){
			projectManagerService.createUserEnvironment(dto);
		}

	}

	@Override
	public ViewData userFind(@Valid UserQryByPages request) {
		// TODO Auto-generated method stub
		UserPo po=ModelMapUtils.map(request,UserPo.class);
		Page hpage=PageHelper.startPage((int)request.getPage(), request.getLimit());
		List<com.inclination.scaffold.infrastraction.repository.po.UserPo> list=userMapping.selectBySelective(po);
		UserManagerQryResponse response = new UserManagerQryResponse();
		response.setList(ModelMapUtils.map(list, UserManageResponse.class));
		response.PageBaseQueryEntity(request.getPage(),request.getLimit(), 
				(int)hpage.getPages(),(int)hpage.getTotal());
		return ViewData.success(ModelMapUtils.map(list, UserManageResponse.class), (int) hpage.getPages(),hpage.getTotal());
	}

	@Override
	@Transactional
	public void deleteUser(UserDto dto) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(dto, User.class).delete(userMapping);
	}

	@Override
	@Transactional
	public void modifyUser(UserDto dto) throws TException {
		// TODO Auto-generated method stub
		UserPo po=ModelMapUtils.map(dto, User.class).update(userMapping);
	    if (po.getUserName().equals(dto.getUserName())&&!dto.getUserPassword().equals(po.getUserPassword())){
			if (!projectManagerService.updateUserPassword(dto,po)){
				throw new TException(TErrorCode.ERROR_UPDATE_USER_CODE,TErrorCode.ERROR_UPDATE_USER_MSG);
			}
		}
	}

	@Override
	public UserDto usersLogin(UserDto dto) {
		// TODO Auto-generated method stub
		com.inclination.scaffold.infrastraction.repository.po.UserPo po=
				ModelMapUtils.map(dto,com.inclination.scaffold.infrastraction.repository.po.UserPo.class);
		Example example=new Example(UserPo.class);
		Example.Criteria criteria=example.createCriteria();
		criteria.andEqualTo("userName",po.getUserName());
		criteria.andEqualTo("userPassword",po.getUserPassword());
		List<com.inclination.scaffold.infrastraction.repository.po.UserPo> list=
				userMapping.selectByExample(example);
		if(null!=list&&list.size()>0){
			return ModelMapUtils.map(list.get(0), UserDto.class);
		}
		return null;
	}

	@Override
	public ViewData batchRemove(String userId) {
		Example example=new Example(UserPo.class);
		Example.Criteria criteria=example.createCriteria();
		criteria.andIn("id", Arrays.asList(userId.split(",")));
		userMapping.deleteByExample(example);
		return ViewData.success(true);
	}
}
