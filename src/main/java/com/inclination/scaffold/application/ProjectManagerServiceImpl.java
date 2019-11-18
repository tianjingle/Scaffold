package com.inclination.scaffold.application;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inclination.scaffold.infrastraction.repository.ProjectPoMapper;
import com.inclination.scaffold.infrastraction.repository.po.ProjectPo;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.inclination.scaffold.application.project.ProjectInformationDto;
import com.inclination.scaffold.application.project.ProjectManagerGitCreateDto;
import com.inclination.scaffold.application.project.ProjectManagerService;
import com.inclination.scaffold.application.users.UserDto;
import com.inclination.scaffold.utils.InputStreamRunnable;
import com.inclination.scaffold.utils.ModelMapUtils;


@Service
public class ProjectManagerServiceImpl implements ProjectManagerService{

	private RestTemplate restTemplate = new RestTemplate();
	
	/**
	 * 注入jenkins 服务
	 */
	@Autowired
	private JenkinsServiceImpl jenkinsService;
	
	/**
	 * 项目管理的服务
	 */
	@Autowired
	private ProjectPoMapper projectMpper;
	/**
	 * 当用户登录成功之后，点击创建工程，并填写好项目名称的时候，点击创建创建仓库即运行此段代码
	 * 此处的username、password为管理员分配给用户的账号
	 * orgModel为组织的编号
	 */
	@Override
	public void createGitRepository(ProjectManagerGitCreateDto map, UserDto dto) {
		// TODO Auto-generated method stub
		String orgModel=dto.getUserName()+"-org";
		String username=dto.getUserName();
		String password=dto.getUserPassword();
		String artifactId=map.getProjectName();
		/**
		 * gitUrl 为git的地址 这里从前端传递过来
		 */
		String gitUrl=map.getGitUrl();
		String userMsg=username+":"+password;
		String base64userMsg=Base64.encodeBase64String(userMsg.getBytes());
		HttpHeaders header=new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON_UTF8);
		header.set("Authorization", "Basic "+base64userMsg);
		
		Map<String,Object> param=new HashMap<>();
		param.put("auto_init", false);
		param.put("description", "");
		param.put("gitignores", "");
		param.put("license", "");
		param.put("name", artifactId);
		param.put("private", false);
		param.put("readme", "README.md");
		String paramStr=JSONObject.toJSONString(param);
		HttpEntity<String> entity=new HttpEntity<>(paramStr,header);
		try{
			ResponseEntity<String> resEntity=this.restTemplate.exchange(gitUrl+"api/v1/org/"+orgModel+"/repos", HttpMethod.POST,entity,String.class);
			String responseStr=resEntity.getBody();
			System.out.println("仓库创建成功...");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("仓库创建失败...");
		}
	}
	@Override
	public void createScaffoldProject(ProjectInformationDto projectDto,UserDto dto) {
		// TODO Auto-generated method stub
		crateGitProject(projectDto,dto);
		
		/**
		 * 创建apollo项目
		 */
		crateApolloProject(projectDto,dto);
	}
	
	public void crateApolloProject(ProjectInformationDto projectDto, UserDto dto){
		
		String username=dto.getUserName();
		String password=dto.getUserPassword();
		String apollourl=projectDto.getApolloUrl();
		String artifactId=projectDto.getArtifactId();
		String usermsg=username+":"+password;
		String base64usermsg=Base64.encodeBase64String(usermsg.getBytes());
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("Authorization", "Basic "+base64usermsg);
		Map<String,Object> param=new HashMap<>();
		param.put("appId", artifactId);
		param.put("name", artifactId);
		param.put("orgId", username);
		param.put("orgName", username);
		param.put("ownerName", username);
		List<String> admins=new ArrayList<>();
		admins.add(username);
		param.put("admins", admins);
		String paramStr=JSON.toJSONString(param);
		HttpEntity<String> entity=new HttpEntity<>(paramStr,headers);
		try{
			ResponseEntity<String> resEntity=this.restTemplate.exchange(apollourl+"/apps", HttpMethod.POST,entity,String.class);
		}catch(Exception e){
			
		}
		
	}

	public void crateGitProject(ProjectInformationDto projectDto, UserDto dto){
		String artifactId=projectDto.getVersion();
		String packageName=projectDto.getArtifactId();
		String protectPath=System.getProperty("user.dir")+"/config"+artifactId;
		String packagePath=packageName.replaceAll("\\.","/");
		String gitUrl=projectDto.getGitUrl();
		//项目路径
		File file=new File(protectPath);
		//将代码上传到git
		try{
			FileUtils.deleteDirectory(file.getParentFile());
			Git git=Git.cloneRepository()
					.setCredentialsProvider(new UsernamePasswordCredentialsProvider(dto.getUserName(),dto.getUserPassword()))
					.setURI(gitUrl)
					.setDirectory(file)
					.call();
			SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm");
			String dateStr=format.format(new Date());
			
			String cmd="mvn archetype:generate"
					+ " -DgroupId="+projectDto.getGroupId()
					+ " -DartifactId="+projectDto.getArtifactId()
					+ " -Dversion=1.0.0-SNAPSHOT"
					+ " -Dpackage="+projectDto.getGroupId()
			    	+ " -DdevIp=127.0.0.3"
					+ " -DarchetypeGroupId=com.example"
					+ " -DarchetypeArtifactId=demo-archetype"
					+ " -DarchetypeVersion=0.0.1-SNAPSHOT"
				 	+ " -B"
					+ " -DarchetypeCatalog=local"
					+ " -DinteractiveMode=false";
			String output=executeCommand(cmd,file.getParentFile());
			System.out.println(output);
			git.add().addFilepattern(".").call();
			
			git.commit().setMessage("上传到仓库..").call();
			
			git.push().setCredentialsProvider(new UsernamePasswordCredentialsProvider(dto.getUserName(),dto.getUserPassword())).call();
			
			git.close();
			
		}catch(GitAPIException e){
			e.printStackTrace();
		}catch(IOException e1){
			e1.printStackTrace();
		}
		String []envs={"dev","sit","uat"};
		//创建jenkins job
		//查数据库获取各种系统的地址信息

		
		String jenkinsUrl=projectDto.getJenkinsUrl();
		String username=dto.getUserName();
		String password=dto.getUserPassword();
		String jobName=projectDto.getArtifactId()+"-Center";
		String orgModel="test";
		
		
		RequestStatus result=null;
		for (String env : envs) {
			result=jenkinsService.createJob(jenkinsUrl,dto.getUserName(),dto.getUserPassword(),jobName,gitUrl,env);
			if(!result.value()){
				break;
			}
			
		}
		createInvoke(username,password,jenkinsUrl,gitUrl,orgModel,jobName,"dev");
		if(result.value()){
			projectMpper.insert(ModelMapUtils.map(projectDto, ProjectPo.class));
			//这里保存到数据库
		}
		
	}

	private void createInvoke(String username, String password, String jenkinsUrl, String gitUrl, String orgModel,
			String jobName, String env) {
		// TODO Auto-generated method stub
		
		//创建git钩子
		RestTemplate restTemplate=new RestTemplate();
		String userMsg=username+":"+password;
		String base64UserMsg=Base64.encodeBase64String(userMsg.getBytes());
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("Authorization", "Basic" +base64UserMsg);
		Map<String,Object> param=new HashMap<>();
		param.put("active", true);
		Map<String,Object> config=new HashMap<>();
		config.put("url", jenkinsUrl+"/gogs-webhook/?job="+jobName+"-"+env);
		config.put("content_type", "json");
		param.put("config", config);
		List<String> events=new ArrayList<>();
		events.add("push");
		param.put("events", events);
		param.put("type", "gogs");
		String paramStr=JSONObject.toJSONString(param);
		System.out.println(paramStr);
		HttpEntity<String> entity=new HttpEntity<>(paramStr,headers);
		ResponseEntity<String> resEntity=restTemplate.exchange(gitUrl.substring(0,gitUrl.indexOf("/"))+"/api/v1/repos/"+orgModel+"/"+jobName+"/hooks",HttpMethod.POST,entity,String.class);
		String responseStr=resEntity.getBody();
		System.out.println(responseStr);
	}

	private String executeCommand(String cmd, File file) {
		// TODO Auto-generated method stub
		//根据运行环境判断运行命令
		String osName=System.getProperty("os.name");
		System.out.println(osName);
		String []command={"","",cmd};
		if(osName.contains("Windows")){
			command[0]="cmd.exe";
			command[1]="/c";
		}else{
			command[0]="/bin/sh";
			command[1]="-c";
		}
		
		StringBuffer output=new StringBuffer();
		Process p;
		InputStreamReader inputStreamReader=null;
		BufferedReader reader=null;
		try{
			p=Runtime.getRuntime().exec(command,null,file);
			Thread t=new Thread(new InputStreamRunnable(p.getErrorStream()));
			t.start();
			inputStreamReader=new InputStreamReader(p.getInputStream(),"utf-8");
			reader=new BufferedReader(inputStreamReader);
			String line="";
			while((line=reader.readLine())!=null){
				output.append(line+"\n");
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				inputStreamReader.close();
				reader.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return output.toString();
	}
}
