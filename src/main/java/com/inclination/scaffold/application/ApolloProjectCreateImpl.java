package com.inclination.scaffold.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.inclination.scaffold.utils.RestTemplateUtil;

@Service
public class ApolloProjectCreateImpl {

	private RestTemplate restTemplate=new RestTemplate();
	
	
	public void crateApolloProject(){
		String username="tianjingle";
		String password="tianjingle";
		String apollourl="";
		String artifactId="test";
		String usermsg=username+":"+password;
		String base64usermsg= Base64.encodeBase64String(usermsg.getBytes());
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


	public boolean createUser(String username, String password, String email, String apolloUrl, String apolloUsername,
			String apollopassword) {
		// TODO Auto-generated method stub
		Map<String,Object> param=new HashMap<>();
		param.put("username", username);
		param.put("password", password);
		param.put("email", email);
		ResponseEntity<String> response=RestTemplateUtil.sendJson(param, apolloUrl+"/users", apolloUsername, apollopassword);
		if(response.getStatusCodeValue()==200){
			return true;
		}
		return false;
	}


	public boolean createOrg(String username) {
		// TODO Auto-generated method stub
/*		ServerConfig serverConfig=serverConfigMapper.selectServerConfig(3);
		List<Organization> organizations=JSON.parseArray(serverConfig.getValue(),Organization.class);
		Organization organization=new Organization();
		organization.setOrgId(username);
		orgnization.setOrgName(username);
		orgnizations.add(orgnization);
		
		String value=JSON.toJSONString(organizations);
		int i=serverConfigMapper.updateServerConfig(3,value);*/
		
	/*	if(i>0){
			return true;
		}*/
		return false;
		
		
		
	}
	
	
}
