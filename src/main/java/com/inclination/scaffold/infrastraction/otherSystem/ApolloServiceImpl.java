package com.inclination.scaffold.infrastraction.otherSystem;

import com.alibaba.fastjson.JSON;
import com.inclination.scaffold.application.project.ProjectInformationDto;
import com.inclination.scaffold.application.users.UserDto;
import com.inclination.scaffold.infrastraction.otherSystem.apollo.ApolloService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * apollo组件
 */
@Service
public class ApolloServiceImpl implements ApolloService {



    private RestTemplate restTemplate = new RestTemplate();


    public void crateApolloProject(ProjectInformationDto projectDto, UserDto dto){
        String username=dto.getUserName();
        String password=dto.getUserPassword();
        String apollourl=projectDto.getApolloUrl();
        String artifactId=projectDto.getArtifactId();
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
        String paramStr= JSON.toJSONString(param);
        HttpEntity<String> entity=new HttpEntity<>(paramStr,headers);
        try{
            ResponseEntity<String> resEntity=this.restTemplate.exchange(apollourl+"/apps", HttpMethod.POST,entity,String.class);
        }catch(Exception e){
        }
    }
}
