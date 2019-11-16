package com.inclination.scaffold.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.inclination.scaffold.infrastraction.repository.ToolProjectPoMapper;
import com.inclination.scaffold.infrastraction.repository.UserPoMapper;
import com.inclination.scaffold.infrastraction.repository.po.ToolProjectPo;
import com.inclination.scaffold.infrastraction.repository.po.UserPo;
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
	 * 创建git服务
	 */
	@Autowired
	private RepositoryCreateImp repositoryCreate;
	
	/**
	 * 创建jenkins服务
	 */
	@Autowired
	private JenkinsServiceImp jenkinsService;
	
	/**
	 * 创建apollo服务
	 */
	@Autowired
	private ApolloProjectCreateImp apolloProjectCreate;
	
	/**
	 * 数据库
	 */
	@Autowired
	private UserPoMapper userMapping;
	
	/**
	 * 创建工具软件管理的数据库服务
	 */
	@Autowired
	private ToolProjectPoMapper toolprojecturlMapper;


	@Override
	@Transactional
	public void createUser(UserDto dto) throws Exception {
		// TODO Auto-generated method stub
		ModelMapUtils.map(dto, User.class).userCreate(userMapping);
		/**
		 * 创建用户的脚手架的用户环境（jenkins、git、apollo）
		 */
		createUserEnvironment(dto);
	}

	@Override
	public UserManagerQryResponse userFind(@Valid UserQryByPages request) {
		// TODO Auto-generated method stub
		UserPo po=ModelMapUtils.map(request,UserPo.class);
		Page hpage=PageHelper.startPage((int)request.getPage(), request.getLimit());
		List<com.inclination.scaffold.infrastraction.repository.po.UserPo> list=userMapping.selectBySelective(po);
		UserManagerQryResponse response = new UserManagerQryResponse();
		response.setList(ModelMapUtils.map(list, UserManageResponse.class));
		response.PageBaseQueryEntity(request.getPage(),request.getLimit(), 
				(int)hpage.getPages(),(int)hpage.getTotal());
		return response;
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
	    ModelMapUtils.map(dto, User.class).update(userMapping);
	}
	/**
	 * 创建用户成功之后，给用户创建jenkins、git、apollo账户
	 * @param dto 
	 * @throws Exception
	 */
	public void createUserEnvironment(UserDto dto) throws Exception{
		String username=dto.getUserName();
		String password=dto.getUserPassword();
		String email=dto.getUserEmil();
		
		//查数据库获取各种系统的地址信息
		Example example = new Example(ToolProjectPo.class);
		Example.Criteria criteria=example.createCriteria();
		criteria.andIsNotNull("id");
		List<ToolProjectPo> list=toolprojecturlMapper.selectByExample(example);
		Map<String,Object> map=new HashMap<String,Object>();
		for (ToolProjectPo toolprojecturl : list) {
			map.put(toolprojecturl.getName(), toolprojecturl);
		}
		ToolProjectPo git=(ToolProjectPo)map.get("gitUrl");
		String gitUrl=git.getUrl();
		ToolProjectPo jenkins=(ToolProjectPo)map.get("jenkinsUrl");
		String jenkinsUrl=jenkins.getUrl()+"securityRealm/createAccount";
		System.out.println(jenkinsUrl);
		ToolProjectPo apollo=(ToolProjectPo)map.get("apolloUrl");
		String apolloUrl=apollo.getUrl();
		/**
		 * apollo 管理员的账户和密码
		 */
		String apolloUsername=apollo.getUserName();
		String apollopassword=apollo.getUserPassword();
		
		//create git`s user
		boolean gitResult=repositoryCreate.createUser(username, password, email, gitUrl);
		if(!gitResult){
			throw new Exception("git用户创建失败..");
		}
		/**
		 * create jenkins user
		 */
		boolean jenkinsResult=jenkinsService.createUser(username, password, email, jenkinsUrl);
		
		if(!jenkinsResult){
			throw new Exception("jenkins用户创建失败");
		}
		/**
		 * create apollo user
		 */
		boolean apolloResult=apolloProjectCreate.createUser(username,password,email,apolloUrl,apolloUsername,apollopassword);
		if(!apolloResult){
			throw new Exception("apollo创建失败..");
		}
		
		/**
		 * these is place where add role for this user
		 * 
		 * ignore code....please complate 
		 */
		
		boolean myview=jenkinsService.createMyView(username,password,jenkins.getUrl(),username);
		
		if(!myview){
			throw new Exception("jenkins视图创建失败");
		}
		
		/**
		 * create git org 
		 */
		boolean gitOrg=repositoryCreate.createOrg(username,password,gitUrl,username);
		
		if(!gitOrg){
			throw new Exception("创建git组织");
		}
		/**
		 * create apollo org
		 */
/*		boolean apolloOrg=apolloProjectCreate.createOrg(username);
		
		if(!apolloOrg){
			throw new Exception("apollo部门创建失败");
		}*/
		
		
		
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
}
