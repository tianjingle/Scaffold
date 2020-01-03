package com.inclination.scaffold.application;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.google.common.base.Strings;
import com.inclination.scaffold.application.project.ProjectManagerService;
import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.infrastraction.repository.UserPoMapper;
import com.inclination.scaffold.infrastraction.repository.po.UserPo;
import com.inclination.scaffold.utils.ViewData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.inclination.scaffold.api.request.user.UserQryByPages;
import com.inclination.scaffold.api.response.user.UserManageResponse;
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
	@Transactional(rollbackFor = Exception.class)
	public void createUser(UserDto dto, UserDto adminDto) throws Exception {
		// TODO Auto-generated method stub
		if (adminDto.getRoId()==2){
            dto.setOrgName(adminDto.getOrgName());
            dto.setRoId(3);
            dto.setOrgId(adminDto.getOrgId());
		}
		if (dto.getRoId()==0){
			throw new TException("","权限不能为空");
		}
		if (dto.getOrgId()==0){
			throw new TException("","组织机构不能为空");
		}
		if (Strings.isNullOrEmpty(dto.getOrgName())){
			throw new TException("","组织机构不能为空");
		}
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
	public ViewData userFind(@Valid UserQryByPages request, UserDto userdto) {
		// TODO Auto-generated method stub
		UserPo po=ModelMapUtils.map(request,UserPo.class);
		Page hpage=PageHelper.startPage((int)request.getPage(), request.getLimit());
		if (userdto.getRoId()==2){
			po.setOrgId(userdto.getOrgId());
			List<com.inclination.scaffold.infrastraction.repository.po.UserPo> list=userMapping.selectBySelectiveByAdmin(po);
			return ViewData.success(ModelMapUtils.map(list, UserManageResponse.class), (int) hpage.getPages(),hpage.getTotal());
		}else if (userdto.getRoId()==1){
			List<com.inclination.scaffold.infrastraction.repository.po.UserPo> list=userMapping.selectBySelective(po);
			return ViewData.success(ModelMapUtils.map(list, UserManageResponse.class), (int) hpage.getPages(),hpage.getTotal());
		}
		return ViewData.error("权限不够");
	}

	@Override
	@Transactional
	public void deleteUser(UserDto dto) throws TException {
		// TODO Auto-generated method stub
		ModelMapUtils.map(dto, User.class).delete(userMapping);
	}

	@Override
	@Transactional(rollbackFor =Exception.class)
	public void modifyUser(UserDto dto, HttpSession session) throws TException {
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
