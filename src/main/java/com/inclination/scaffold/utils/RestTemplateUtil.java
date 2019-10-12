package com.inclination.scaffold.utils;

import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

public class RestTemplateUtil {

	private static RestTemplate restTemplate=new RestTemplate();
	
	public static ResponseEntity<String> submitForm(MultiValueMap<String,Object> param,String url,String adminName,String adminPwd){
		HttpHeaders header=new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		if(null!=adminName&&null!=adminPwd){
			String userMsg=adminName+":"+adminPwd;
			String base64UserMsg= Base64.encodeBase64String(userMsg.getBytes());
			header.set("Authorization", "Basic "+base64UserMsg);
		}
		HttpEntity<MultiValueMap<String,Object>> entity=new HttpEntity<>(param,header);
		ResponseEntity<String> resEntity=restTemplate.exchange(url, HttpMethod.POST,entity,String.class);
		return resEntity;
	}


	public static ResponseEntity<String> sendJson(Map<String, Object> param, String url, String adminName,
			String adminPwd) {
		// TODO Auto-generated method stub
		HttpHeaders header=new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON_UTF8);
		if(null!=adminName&&null!=adminPwd){
			String userMsg=adminName+":"+adminPwd;
			String base64UserMsg=Base64.encodeBase64String(userMsg.getBytes());
			header.set("Authorization", "Basic "+base64UserMsg);
		}
		String paramStr=JSON.toJSONString(param);
		HttpEntity<String> entity=new HttpEntity<>(paramStr,header);
		ResponseEntity<String> resEntity=restTemplate.exchange(url, HttpMethod.POST,entity,String.class);
		return resEntity;
	}
	
}
