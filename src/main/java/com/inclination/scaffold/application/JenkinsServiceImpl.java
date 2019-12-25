package com.inclination.scaffold.application;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.inclination.http.rest.RestTemplateUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSON;
import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.common.RequestStatus;


@Service
public class JenkinsServiceImpl {

	public RequestStatus createJob(String url, String username, String password, String jobName, String gitUrl,
			String env) {
		// TODO Auto-generated method stub
		JenkinsClient client = JenkinsClient.builder().endPoint(url).credentials(username + ":" + password).build();
        //System.getProperty("user.dir") 项目的地址
		File config=new File(System.getProperty("user.dir")+"/script/jenkinsConfig-"+env+".xml");
		SAXReader reader=new SAXReader();
		Document document=null;
		try{
			//解析xml
			document=reader.read(config);
			Element root=document.getRootElement();
			//修改git地址
			//得到git地址节点
			Node node=getChildNodes(root,"hudson.plugins.git.UserRemoteConfig");
			
			if(node instanceof Element){
				Element element=(Element) node;
				Element urlEle=element.element("url");
				if(null!=urlEle){
					urlEle.setText(gitUrl);
				}else{
					urlEle=DocumentHelper.createElement("url");
					urlEle.setText(gitUrl);
					element.add(urlEle);
				}
			}
			//修改权限
			Node authorization=getChildNodes(root,"hud.security.AuthorizationMatrixProperty");
			if(null!=authorization&&authorization instanceof Element){
				Element auth=(Element) authorization;
				List<Element> permissions=auth.elements("permission");
				for (Element element : permissions) {
				   String permissionText=element.getText();
				   permissionText=permissionText.substring(0,permissionText.indexOf(":"))+":"+username;
				   element.setText(permissionText);
				}
			}
			
		}catch(DocumentException e){
			e.printStackTrace();
		}
		
		
		//创建jenkins job
		RequestStatus create=client.api().jobsApi().create(null, jobName+"-"+env, document.asXML());
		if(create.value()){
			MultiValueMap<String,Object> param=new LinkedMultiValueMap<>();
			param.add("name", jobName+"-"+env);
			RestTemplateUtil.submitForm(param,url+"/view/"+username+"/addJobToView",username,password);
			if("dev".equals(env)||"sit".equals(env)){
				MultiValueMap<String,Object> param2=new LinkedMultiValueMap<>();
				param2.add("name",jobName+"-"+env);
				RestTemplateUtil.submitForm(param2, url+"/view/"+username+"-monitor/addJobToView", username, password);
			}
		}
		return create;
	}

	private Node getChildNodes(Node next2, String name) {
		// TODO Auto-generated method stub
		
		if(null!=next2.getName()&&next2.getName().equals(name)){
			return next2;
		}
		Node chileNodes=null;
		if(next2 instanceof Element){
			Element elem=(Element)next2;
			Iterator<Node> it=elem.nodeIterator();
			while(it.hasNext()){
				Node next=it.next();
				chileNodes=getChildNodes(next,name);
				if(chileNodes!=null){
					break;
				}
			}
		}
		return chileNodes;
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
				//ResponseEntity<String> response2=RestTemplateUtil.submitForm(param2, jenkinsUrl, username, password);
				if(response.getStatusCodeValue()==302){ //&&response2.getStatusCodeValue()==302
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
