package com.inclination.scaffold.infrastraction.otherSystem;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.csxiaoshang.xml.XmlUtils;
import com.csxiaoshang.xml.model.root.Project;
import com.inclination.http.rest.RestTemplateUtil;
import com.inclination.scaffold.infrastraction.otherSystem.jenkins.JenkinsService;
import com.offbytwo.jenkins.JenkinsServer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSON;

import javax.xml.bind.JAXBException;


@Service
public class JenkinsServiceImpl implements JenkinsService {

	public boolean createJobByJenkinsClient(String url, String username, String password, String jobName, String gitUrl,String env) throws URISyntaxException, IOException, JAXBException {
        JenkinsServer jenkins=new JenkinsServer(new URI(url),username,password);
		Project project=null;
		try {
			project= XmlUtils.getProject();
			XmlUtils.setGitUrl(project,gitUrl);
			XmlUtils.setHudsonShell(project,"ls -l");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String config=XmlUtils.getConfigXml(project);
		System.out.println(config);
		jenkins.createJob(jobName+"-"+env,config);
		MultiValueMap<String,Object> param=new LinkedMultiValueMap<>();
		param.add("name", jobName+"-"+env);
		RestTemplateUtil.submitForm(param,url+"/view/"+username+"/addJobToView",username,password);
		if("dev".equals(env)||"sit".equals(env)){
			MultiValueMap<String,Object> param2=new LinkedMultiValueMap<>();
			param2.add("name",jobName+"-"+env);
			RestTemplateUtil.submitForm(param2, url+"/view/monitor/addJobToView", username, password);
		}
	    return true;
	}



//创建用户
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
	
	public boolean updatePwd(String username,String password,String newPwd,String email,String jenkinsUrl){
		MultiValueMap<String,Object> param=new LinkedMultiValueMap<>();
		param.add("_.fullName", username);
		param.add("_.description" ,"");
		param.add("_.", username);
		param.add("_.apiToken", "Token is hidden");
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
		Map<String,Object> userProperty2=new HashMap<>();
		userProperty2.put("address", email);
		json.put("userProperty2",userProperty2);
		Map<String,Object> userProperty5=new HashMap<>();
		userProperty5.put("primaryViewName","");
		json.put("userProperty5",userProperty5);
		Map<String,Object> userProperty6=new HashMap<>();
		userProperty6.put("providerId", "default");
		json.put("userProperty6", userProperty6);
		Map<String,Object> userProperty8=new HashMap<>();
		userProperty8.put("password", newPwd);
		userProperty8.put("password2", newPwd);
		json.put("userProperty8", userProperty8);
		Map<String,Object> userProperty9=new HashMap<>();
		userProperty9.put("zuthorizedKeys","");
		json.put("userProperty9", userProperty9);
		Map<String,Object> userProperty10=new HashMap<>();
		userProperty10.put("insensitiveSearch", true);
		json.put("userProperty10", userProperty10);
		json.put("core:apply", "");
		param.add("json", JSON.toJSON(json));
		ResponseEntity<String> response=RestTemplateUtil.submitForm(param, jenkinsUrl+"/securityRealm/user/"+username+"/configSubmit", username, password);
		if(response.getStatusCodeValue()==302){
			return true;
		}
		return false;
	}
	
	
}
