package com.inclination.scaffold.application;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Strings;
import com.inclination.scaffold.api.request.project.ProjectQryByPage;
import com.inclination.scaffold.constant.config.OtherSystemProperties;
import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.infrastraction.otherSystem.git.GitService;
import com.inclination.scaffold.infrastraction.otherSystem.jenkins.JenkinsService;
import com.inclination.scaffold.infrastraction.repository.ProjectPoMapper;
import com.inclination.scaffold.infrastraction.repository.po.ProjectPo;
import com.inclination.scaffold.infrastraction.repository.po.UserPo;
import com.inclination.scaffold.utils.ViewData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inclination.scaffold.application.project.ProjectInformationDto;
import com.inclination.scaffold.application.project.ProjectManagerService;
import com.inclination.scaffold.application.users.UserDto;
import com.inclination.scaffold.utils.ModelMapUtils;
import tk.mybatis.mapper.entity.Example;

import javax.xml.bind.JAXBException;


@Service
public class ProjectManagerServiceImpl implements ProjectManagerService{


	/**
	 * 基础组件的配置信息
	 */
	@Autowired
	private OtherSystemProperties projectProperties;

	/***
	 * git服务
	 */
	@Autowired
	private GitService gitService;

	/**
	 * 注入jenkins 服务
	 */
	@Autowired
	private JenkinsService jenkinsService;
	
	/**
	 * 项目管理的服务
	 */
	@Autowired
	private ProjectPoMapper projectMpper;

	/**
	 * 创建git服务
	 */
	@Autowired
	private RepositoryCreateImpl repositoryCreate;


	@Override
	public void createScaffoldProject(ProjectInformationDto projectDto,UserDto dto) throws URISyntaxException, TException {
		// TODO Auto-generated method stub
//		if(dto.getRoId()!=2){
//			if (dto.getRoId()==1){
//				throw new TException(TErrorCode.ERROR_CREATE_PROJECT_ROOT_CODE,TErrorCode.ERROR_CREATE_PROJECT_ROOT_MSG);
//			}else{
//				throw new TException(TErrorCode.ERROR_CREATE_PROJECT_CREATE_CODE,TErrorCode.ERROR_CREATE_PROJECT_CREATE_MSG);
//			}
//		}

		if (gitService.createGitRepository(projectDto.getArtifactId(),dto)){
			try {
				try {
					crateScaffoldProject(projectDto,dto);
				} catch (TException e) {
					e.printStackTrace();
					throw new TException(TErrorCode.ERROR_GIT_PROJECT_CREATE_CODE,TErrorCode.ERROR_GIT_PROJECT_CREATE_MSG);
				}
			} catch (IOException | JAXBException | TException e) {
				e.printStackTrace();
			}
		}
		/**
		 * 创建apollo项目
		 */
		//crateApolloProject(projectDto,dto);
	}

	@Override
	public ViewData doSearchProject(ProjectQryByPage request, UserDto dto) {
		Example example=new Example(ProjectPo.class);
		if (dto.getRoId()==1&&Strings.isNullOrEmpty(request.getArtifactId())){

		}else if(dto.getRoId()==1&&!Strings.isNullOrEmpty(request.getArtifactId())){
			example.createCriteria().andEqualTo("artifactId",request.getArtifactId());
		}else if (dto.getRoId()!=1&&!Strings.isNullOrEmpty(request.getArtifactId())){
			example.createCriteria().andEqualTo("artifactId",request.getArtifactId()).andEqualTo("gitOrg",dto.getOrgName());
		}else{
			example.createCriteria().andEqualTo("gitOrg",dto.getOrgName());
		}
		example.setOrderByClause("id desc");
		Page hpage= PageHelper.startPage((int)request.getPage(), request.getLimit());
		List<ProjectPo> list=projectMpper.selectByExample(example);
		return ViewData.success(list,hpage.getPages(),hpage.getTotal());
	}



	public boolean crateScaffoldProject(ProjectInformationDto projectDto, UserDto dto) throws URISyntaxException, IOException, JAXBException, TException {
		String artifactId=projectDto.getArtifactId();
		String gitUrl=projectProperties.getGitUrl()+""+dto.getOrgName()+"-org/"+artifactId+".git";
		if (!gitService.crateGitProject(projectDto,dto)){
			throw new TException(TErrorCode.ERROR_SCAFFOLD_PROJECT_CREATE_CODE,TErrorCode.ERROR_SCAFFOLD_PROJECT_CREATE_MSG);
		}
		String []envs={"dev"};
		if (Strings.isNullOrEmpty(projectProperties.getEnv())&&projectProperties.getEnv().contains(",")) {
			envs=projectProperties.getEnv().split(",");
		}else{
			envs=new String[]{"dev"};
		}
		//创建jenkins job
		//查数据库获取各种系统的地址信息
		String jenkinsUrl=projectProperties.getJenkinsUrl();
		String username=dto.getUserName();
		String password=dto.getUserPassword();
		String jobName=projectDto.getArtifactId()+"-service";
		String orgModel=dto.getOrgName() +"-org";
		boolean flag=false;
		for (String env : envs) {
			flag=jenkinsService.createJobByJenkinsClient(jenkinsUrl,dto.getUserName(),dto.getUserPassword(),jobName,gitUrl,env,dto.getOrgName());
			if(!flag){
				return false;
			}
		}
		gitService.createInvoke(username,password,jenkinsUrl,gitUrl,orgModel,jobName,"dev");
		if(flag){
			ProjectPo po=ModelMapUtils.map(projectDto, ProjectPo.class);
			po.setGitUrl(gitUrl);
			po.setGitOrg(dto.getOrgName());
			po.setJenkinsUrl(jenkinsUrl+"job/"+jobName+"-dev");
			po.setApolloUrl("");
			po.setCreateTime(new Date());
			projectMpper.insert(po);
		}
		return true;
	}

	/**
	 * 创建用户成功之后，给用户创建jenkins、git、apollo账户
	 * @param dto
	 * @throws Exception
	 */
	public void createUserEnvironment(UserDto dto) throws Exception {
		String username = dto.getUserName();
		String password = dto.getUserPassword();
		String gitUrl = projectProperties.getGitUrl();
//		String apolloUrl=toolProjectProperties.getJenkinsUrl();
		/**
		 * apollo 管理员的账户和密码
		 */
//		String apolloUsername=toolProjectProperties.get
//		String apollopassword=toolProjectProperties.getUserPassword();
		//create git`s user

		/**
		 * create apollo user
		 */
//		boolean apolloResult=apolloProjectCreate.createUser(username,password,email,apolloUrl,apolloUsername,apollopassword);
//		if(!apolloResult){
//			throw new Exception("apollo创建失败..");
//		}
//
		boolean myview = jenkinsService.createMyView(username, password, projectProperties.getJenkinsUrl(), dto.getOrgName());
		if (!myview) {
			throw new Exception("jenkins视图创建失败");
		}
		/**
		 * create git org
		 */
		boolean gitOrg = repositoryCreate.createOrg(username, password, gitUrl, dto.getOrgName());
		if (!gitOrg) {
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

	/**
	 * 修改其他系统的用户密码
	 *
     * @param newUser
     * @param oldUser
     * @return
	 */
	@Override
	public boolean updateUserPassword(UserDto newUser, UserPo oldUser) {
		try {
			gitService.updateUserPassword(newUser,oldUser);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		jenkinsService.updatePwd(newUser.getUserName(),oldUser.getUserPassword(),newUser.getUserPassword(),newUser.getUserEmil(),projectProperties.getJenkinsUrl());
		return false;
	}

	@Override
	public void createUserOtherSystem(UserDto dto) throws Exception {
		String username = dto.getUserName();
		String password = dto.getUserPassword();
		String email = dto.getUserEmil();

		String gitUrl = projectProperties.getGitUrl();
		String jenkinsUrl = projectProperties.getJenkinsUrl() + "securityRealm/createAccount";
		boolean gitResult = repositoryCreate.createUser(username, password, email, gitUrl);
		if (!gitResult) {
			throw new Exception("git用户创建失败..");
		}
		/**
		 * create jenkins user
		 */
		boolean jenkinsResult = jenkinsService.createUser(username, password, email, jenkinsUrl);

		if (!jenkinsResult) {
			throw new Exception("jenkins用户创建失败");
		}
	}


}
