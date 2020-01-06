package com.inclination.scaffold.infrastraction.otherSystem;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.csxiaoshang.xml.XmlUtils;
import com.csxiaoshang.xml.model.root.Project;
import com.google.common.base.Strings;
import com.inclination.http.rest.RestTemplateUtil;
import com.inclination.scaffold.constant.config.OtherSystemProperties;
import com.inclination.scaffold.infrastraction.otherSystem.jenkins.JenkinsService;
import com.offbytwo.jenkins.JenkinsServer;
import com.scaffold.PipelineUtils;
import com.scaffold.root.FlowDefinition;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSON;

import javax.xml.bind.JAXBException;


@Service
public class JenkinsServiceImpl implements JenkinsService {

	/***
	 * 组件配置
	 */
	@Autowired
	private OtherSystemProperties otherSystemProperties;


	/**
	 * 创建jenkins任务
	 * @param url
	 * @param username
	 * @param password
	 * @param jobName
	 * @param gitUrl
	 * @param env
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws JAXBException
	 */
	public boolean createJobByJenkinsClient(String url, String username, String password, String jobName, String gitUrl,String env,String org) throws URISyntaxException, IOException, JAXBException {
        JenkinsServer jenkins=new JenkinsServer(new URI(url),username,password);
		Project project=null;
		FlowDefinition flowDefinition=new FlowDefinition();
		try {
			project= XmlUtils.getProject();
			XmlUtils.setGitUrl(project,gitUrl);
			XmlUtils.setHudsonShell(project,"ls -l");


			PipelineUtils.setGitInfo(flowDefinition,gitUrl,null);
			PipelineUtils.setGogInfo(flowDefinition,password,jobName);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
//		String config=XmlUtils.getConfigXml(project);
		String config=PipelineUtils.writeXml(flowDefinition);
		System.out.println(config);
		jenkins.createJob(jobName+"-"+env,config);
		MultiValueMap<String,Object> param=new LinkedMultiValueMap<>();
		param.add("name", jobName+"-"+env);
		RestTemplateUtil.submitForm(param,url+"/view/"+org+"/addJobToView",username,password);
		if("dev".equals(env)&& !Strings.isNullOrEmpty(otherSystemProperties.getMonitorView())){
			MultiValueMap<String,Object> param2=new LinkedMultiValueMap<>();
			param2.add("name",jobName+"-"+env);
			RestTemplateUtil.submitForm(param2, url+"/view/"+otherSystemProperties.getMonitorView()+"/addJobToView", username, password);
		}
	    return true;
	}


	/**
	 * 创建jenkins用户
	 * @param username
	 * @param password
	 * @param email
	 * @param jenkinsurl
	 * @return
	 * @throws Exception
	 */
	public boolean createUser(String username,String password,String email,String jenkinsurl) throws Exception{
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
		return true;
	}

	/**
	 * 创建jenkins视图
	 * @param username
	 * @param password
	 * @param jenkinsUrl
	 * @param viewName
	 * @return
	 */
	public boolean createMyView(String username, String password, String jenkinsUrl, String viewName) {
		// TODO Auto-generated method stub
			MultiValueMap<String,Object> param=new LinkedMultiValueMap<>();
			param.add("name",  viewName);
			param.add("mode", "hudson.model.ListView");
			Map<String,String> map=new HashMap<>();
			map.put("name", viewName);
			map.put("mode","hudson.model.ListView");
			String paramStr=JSON.toJSONString(map);
			param.add("json", paramStr);
			//创建Monitor视图
			MultiValueMap<String,Object> param2=new LinkedMultiValueMap<>();
			param2.add("name", viewName+"-monitor");
			param2.add("mode", "com.smartcodeltd.jenkinsci.plugins.buildmonitor.BuilderView");
			Map<String,String> map2=new HashMap<>();
			map2.put("name", viewName+"-monitor");
			map2.put("mode", "com.smartcodeltd.jenkinsci.plugins.buildmonitor.BuildMonitorView");
			String paramStr2=JSON.toJSONString(map2);
			param2.add("json", paramStr2);
			try{
				ResponseEntity<String> response=RestTemplateUtil.submitForm(param, jenkinsUrl+"createView", username, password);
				if(response.getStatusCodeValue()==302){
					return true;
				}
			}catch(Exception e){
				return false;
			}
			return false;
	}

	/**
	 * 修改jenkins用户密码
	 * @param username
	 * @param password
	 * @param newPwd
	 * @param email
	 * @param jenkinsUrl
	 * @return
	 */
	public boolean updatePwd(String username,String password,String newPwd,String email,String jenkinsUrl){
		MultiValueMap<String,Object> param=new LinkedMultiValueMap<>();
		param.add("_.fullName", username);
		param.add("_.description" ,"");
		param.add("showCondition","Chinese");
		param.add("email.address", email);
		param.add("_.primaryViewName", "");
		param.add("providerId", "default");
		param.add("user.password", newPwd);
		param.add("user.password2", newPwd);
		param.add("_.authorizedKeys", "");
		param.add("insensitiveSearch", "on");
		param.add("core:apply", "");
		Map<String,Object> json=new HashMap<>();
		json.put("fullName", username);
		json.put("description", "");

		Map<String,Object> userProperty1=new HashMap<>();
		userProperty1.put("showCondition","Chinese");
		json.put("userProperty1",userProperty1);
		Map<String,Object> userProperty3=new HashMap<>();
		userProperty3.put("address", email);
		json.put("userProperty3",userProperty3);


		Map<String,Object> userProperty6=new HashMap<>();
		userProperty6.put("primaryViewName","");
		json.put("userProperty6",userProperty6);
		Map<String,Object> userProperty7=new HashMap<>();
		userProperty6.put("providerId", "default");
		json.put("userProperty7", userProperty7);


		Map<String,Object> userProperty9=new HashMap<>();
		userProperty9.put("password", newPwd);
		userProperty9.put("password2", newPwd);
		userProperty9.put("$redact","[password,password2]");
		json.put("userProperty9", userProperty9);


		Map<String,Object> userProperty10=new HashMap<>();
		userProperty10.put("authorizedKeys","");
		json.put("userProperty10", userProperty10);

		Map<String,Object> userProperty12=new HashMap<>();
		userProperty12.put("insensitiveSearch", true);
		json.put("userProperty12", userProperty12);

		json.put("core:apply", "");


		Object endJson=JSON.toJSON(json);
		param.add("json", endJson);
		String jenkinsTargetUrl=jenkinsUrl+"securityRealm/user/"+username+"/configSubmit";
		ResponseEntity<String> response=RestTemplateUtil.submitForm(param, jenkinsTargetUrl, username, password);
		if(response.getStatusCodeValue()==302){
			return true;
		}
		return false;
	}
}
