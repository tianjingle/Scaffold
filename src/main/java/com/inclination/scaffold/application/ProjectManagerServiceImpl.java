package com.inclination.scaffold.application;
import java.io.IOException;
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



	@Override
	public void createScaffoldProject(ProjectInformationDto projectDto,UserDto dto) throws URISyntaxException {
		// TODO Auto-generated method stub
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
	public ViewData doSearchProject(ProjectQryByPage request, String loginId) {
		Example example=new Example(ProjectPo.class);
		if (!Strings.isNullOrEmpty(request.getArtifactId())){
			example.createCriteria().andEqualTo("artifactId",request.getArtifactId());
			example.setOrderByClause("id desc");
		}else{
			example.createCriteria().andEqualTo("loginId",loginId);
			example.setOrderByClause("id desc");
		}
		Page hpage= PageHelper.startPage((int)request.getPage(), request.getLimit());
		List<ProjectPo> list=projectMpper.selectByExample(example);
		return ViewData.success(list,hpage.getPages(),hpage.getTotal());
	}



	public boolean crateScaffoldProject(ProjectInformationDto projectDto, UserDto dto) throws URISyntaxException, IOException, JAXBException, TException {
		String artifactId=projectDto.getArtifactId();
		String gitUrl=projectProperties.getGitUrl()+""+dto.getUserName()+"-org/"+artifactId+".git";
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
		String orgModel=dto.getUserName() +"-org";
		boolean flag=false;
		for (String env : envs) {
			flag=jenkinsService.createJobByJenkinsClient(jenkinsUrl,dto.getUserName(),dto.getUserPassword(),jobName,gitUrl,env);
			if(!flag){
				return false;
			}
		}
		gitService.createInvoke(username,password,jenkinsUrl,gitUrl,orgModel,jobName,"dev");
		if(flag){
			ProjectPo po=ModelMapUtils.map(projectDto, ProjectPo.class);
			po.setGitUrl(gitUrl);
			po.setGitOrg(orgModel);
			po.setJenkinsUrl(jenkinsUrl+"job/"+jobName+"-dev");
			po.setApolloUrl("");
			po.setCreateTime(new Date());
			projectMpper.insert(po);
		}
		return true;
	}


}
