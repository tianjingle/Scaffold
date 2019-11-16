package com.inclination.scaffold.api.interfaces;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inclination.scaffold.api.request.project.ProjectManagerCreateRequest;
import com.inclination.scaffold.application.project.ProjectInformationDto;
import com.inclination.scaffold.application.users.UserDto;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.utils.InputStreamRunnable;
import com.inclination.scaffold.utils.ModelMapUtils;
import com.inclination.scaffold.utils.RestTemplateUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
@RestController
@ApiModel
public class test {

	@Value("${test:123}")
	private String test;
	
	@Value("${common:123}")
	private String common;
	
	
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String getCommon() {
		return common;
	}
	public void setCommon(String common) {
		this.common = common;
	}
	@GetMapping(value="/test-jenkins")
	@ApiOperation(value="测试jenkins",notes="发送短信")
	public void sendSms() throws Exception{
		//smsService.sendMsg();
		String username="zhuxinyao";
		String password="zhuxinyao";
		String email="zhuxinyao@yxsj.com.cn";
		String jenkinsurl="http://127.0.0.1:9001/securityRealm/createAccount";
		MultiValueMap<String,Object> param=new LinkedMultiValueMap<>();
		param.add("username", username);
		param.add("password1", password);
		param.add("password2", password);
		param.add("fullname", username);
		param.add("email", email);
		ResponseEntity<String> response=RestTemplateUtil.submitForm(param, jenkinsurl, null, null);
		boolean contains=response.getBody().contains(username);
		if(!contains){
			throw new Exception();
		}
		System.out.println(contains);
	}
	@GetMapping(value="/test-git")
	@ApiOperation(value="测试jenkins",notes="发送短信")
	public void createGitUser() throws Exception{
		String username="zhuxinyao";
		String password="zhuxinyao";
		String email="zhuxinyao@yxsj.com.cn";
		MultiValueMap<String,Object> param=new LinkedMultiValueMap<>();
		param.add("user_name",username);
		param.add("password", password);
		param.add("retype", password);
		param.add("email", email);
		String gitUrl="http://127.0.0.1:9002";
		ResponseEntity<String> response=RestTemplateUtil.submitForm(param, gitUrl+"/user/sign_up", null, null);
		int status=response.getStatusCodeValue();
		if(status==302){
			System.out.println("创建成功");
		}else if(status==200){
			String responseStr=response.getBody();
			if(responseStr.contains("The username is already taken")){
				throw new TException("123","git 用户已经存在");
			}
			if(responseStr.contains("The email address is already used")){
				throw new TException("123123","git邮箱已经使用了");
			}
			System.out.println("创建成功");
		}
		System.out.println("创建失败");
	}
	@GetMapping(value="/test-apollo")
	@ApiOperation(value="测试apollo",notes="测试apollo")
	public void testApollo(){
		System.out.println(test+"--------------"+common);
		// TODO Auto-generated method stub
		String apolloUsername="apollo";
		String apollopassword="admin";
		String apolloUrl="http://127.0.0.1:8070/";
		String username="wanghuying";
		String password="wanghuying";
		String email="wanghuying@qq.com";
		Map<String,Object> param=new HashMap<>();
		param.put("username", username);
		param.put("password", password);
		param.put("email", email);
		ResponseEntity<String> response=RestTemplateUtil.sendJson(param, apolloUrl+"/users", apolloUsername, apollopassword);
		if(response.getStatusCodeValue()==200){
			System.out.println("创建用户成功");
		}
		System.out.println("fail");
		//return test+"-"+common;
	}
	@PostMapping(value="/push-git")
	@ApiOperation(value="推送到git",notes="推送到git")
	public void pushGit(@Valid @RequestBody ProjectManagerCreateRequest request,HttpSession session) throws IOException{
		UserDto dto=(UserDto) session.getAttribute("CurrentUser");
		ProjectInformationDto projectDto=ModelMapUtils.map(request, ProjectInformationDto.class);
		projectDto.setCreatetime(new Date());
		projectDto.setLoginid(dto.getLoginid());
		String artifactId=projectDto.getArtifactid();
		File f=new File(System.getProperty("user.dir"));
		String protectPath=f.getParent()+"/config1.0";
		String gitUrl=projectDto.getGiturl();
		//项目路径
		File file=new File(protectPath);
		File newFile=new File(file.getParent()+"/"+projectDto.getArtifactid());
		String home=projectDto.getGroupid();
		String packagePath=home.replaceAll("\\.",".");
		//将代码上传到git
		try{
			//FileUtils.deleteDirectory(file.getParentFile());
			Git git=Git.cloneRepository()
					.setCredentialsProvider(new UsernamePasswordCredentialsProvider(dto.getUsername(),dto.getUserpassword()))
					.setURI(gitUrl)
					.setDirectory(newFile)
					.call();
			SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm");

			String cmd=" mvn archetype:generate"
					+ " -DgroupId=net.cnki.demo"
					+ " -DartifactId="+projectDto.getArtifactid()
					+ " -Dversion=1.0.0-SNAPSHOT"
					+ " -Dpackage=net.cnki.demo"
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
			
			git.push().setCredentialsProvider(new UsernamePasswordCredentialsProvider(dto.getUsername(),dto.getUserpassword())).call();
			
			git.close();
			
		}catch(GitAPIException e){
			e.printStackTrace();
		}
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