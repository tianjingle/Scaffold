package com.inclination.scaffold.application;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * 创建仓库
 * @author 乐乐
 *
 */
@Service
public class RepositoryCreateImpl {


	/**
	 * 创建git用户
	 * @param username 用户的名称
	 * @param password 用户的密码
	 * @param email 用户email
	 * @param gitUrl git的地址
	 * @return 是否创建成功
	 * @throws Exception
	 */
	public boolean createUser(String username,String password,String email,String gitUrl) throws Exception{
		MultiValueMap<String,Object> param=new LinkedMultiValueMap<>();
		param.add("user_name",username);
		param.add("password", password);
		param.add("retype", password);
		param.add("email", email);
		ResponseEntity<String> response=RestTemplateUtil.submitForm(param, gitUrl+"/user/sign_up", null, null);
		int status=response.getStatusCodeValue();
		if(status==302){
			return true;
		}else if(status==200){
			String responseStr=response.getBody();
			if(responseStr.contains("The username is already taken")){
				throw new Exception("git 用户已经存在");
			}
			if(responseStr.contains("The email address is already used")){
				throw new Exception("git邮箱已经使用了");
			}
			return true;
		}
		return false;
	}

	public boolean createOrg(String username, String password, String gitUrl, String orgName) {
		// TODO Auto-generated method stub
		Map<String,Object> param=new HashMap<>();
		param.put("username",orgName+"-org");
		param.put("full_name", orgName+"-org");
		param.put("description", orgName+"-org");
		param.put("website", "田景乐");
		ResponseEntity<String> response=RestTemplateUtil.sendJson(param, gitUrl+"api/v1/orgs", username, password);
		if(response.getStatusCodeValue()==201){
			return true;
		}
		return false;
	}


}
