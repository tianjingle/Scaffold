package com.inclination.scaffold.infrastraction.otherSystem;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.inclination.scaffold.application.project.ProjectInformationDto;
import com.inclination.scaffold.application.users.UserDto;
import com.inclination.scaffold.constant.config.OtherSystemProperties;
import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.infrastraction.otherSystem.git.GitService;
import com.inclination.scaffold.infrastraction.repository.po.UserPo;
import com.inclination.scaffold.utils.CMDExecuteUtil;
import com.inclination.scaffold.utils.MyRestTemplate;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GitServiceImpl implements GitService {


    /**
     * 配置信息
     */
    @Autowired
    private OtherSystemProperties projectProperties;

    private RestTemplate restTemplate = new RestTemplate();

    private MyRestTemplate myRestTemplate=new MyRestTemplate();

    static String _csrf="";

    static String lang="zh-CN";

    static String i_like_gitea="";

    static String macaron_flash="";
    /**
     * 创建git仓库
     * @param artifactId
     * @param dto
     * @return
     */
    public boolean createGitRepository(String artifactId, UserDto dto) {
        // TODO Auto-generated method stub
        String orgModel=dto.getUserName()+"-org";
        String username=dto.getUserName();
        String password=dto.getUserPassword();
        String gitUrl=projectProperties.getGitUrl();
        String userMsg=username+":"+password;
        String base64userMsg= Base64.encodeBase64String(userMsg.getBytes());
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
        String paramStr= JSONObject.toJSONString(param);
        HttpEntity<String> entity=new HttpEntity<>(paramStr,header);
        try{
            ResponseEntity<String> resEntity=this.restTemplate.exchange(gitUrl+"api/v1/org/"+orgModel+"/repos", HttpMethod.POST,entity,String.class);
            String responseStr=resEntity.getBody();
            System.out.println("仓库创建成功...");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("仓库创建失败...");
            return false;
        }
        return true;
    }

    /**
     * 创建git项目
     * @param projectDto
     * @param dto
     * @return
     */
    public boolean crateGitProject(ProjectInformationDto projectDto, UserDto dto){
        String artifactId = projectDto.getArtifactId();
        String protectPath = System.getProperty("user.dir") + "/project-temp/" + artifactId;
        String gitUrl = projectProperties.getGitUrl() + "" + dto.getUserName() + "-org/" + artifactId + ".git";
        File file = new File(protectPath);
        try {
            FileUtils.deleteDirectory(file.getParentFile());
            Git git = Git.cloneRepository()
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider(dto.getUserName(), dto.getUserPassword()))
                    .setURI(gitUrl)
                    .setDirectory(file)
                    .call();
            String cmd=projectProperties.getScript();
            if (Strings.isNullOrEmpty(cmd)){
                throw new TException(TErrorCode.ERROR_NO_MODEL_MAVEN_CODE,TErrorCode.ERROR_NO_MODEL_MAVEN_MSG);
            }
            cmd= MessageFormat.format(cmd, projectDto.getGroupId(), projectDto.getArtifactId(),projectDto.getVersion(),projectDto.getGroupId()+"."+projectDto.getArtifactId());
            String output = CMDExecuteUtil.executeCommand(cmd, file.getParentFile());
            System.out.println(output);
            git.add().addFilepattern(".").call();
            git.commit().setMessage("上传到仓库..").call();
            git.push().setCredentialsProvider(new UsernamePasswordCredentialsProvider(dto.getUserName(), dto.getUserPassword())).call();
            git.close();
        } catch (GitAPIException | IOException | TException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /***
     * 创建git勾子
     * @param username
     * @param password
     * @param jenkinsUrl
     * @param gitUrl
     * @param orgModel
     * @param jobName
     * @param env
     */
    public void createInvoke(String username, String password, String jenkinsUrl, String gitUrl, String orgModel,String jobName, String env) {
        // TODO Auto-generated method stu
        //创建git钩子
        RestTemplate restTemplate=new RestTemplate();
        String userMsg=projectProperties.getGitAdmin()+":"+projectProperties.getGitPassword();
        String base64userMsg=Base64.encodeBase64String(userMsg.getBytes());
        HttpHeaders header=new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
        header.set("Authorization", "Basic "+base64userMsg);
        Map<String,Object> param=new HashMap<>();
        param.put("active", true);
        Map<String,Object> config=new HashMap<>();
        config.put("url", jenkinsUrl+"gogs-webhook/?job="+jobName+"-"+env);
        config.put("content_type", "json");
        param.put("config", config);
        List<String> events=new ArrayList<>();
        events.add("push");
        param.put("events", events);
        param.put("type", "gogs");
        String paramStr=JSONObject.toJSONString(param);
        System.out.println(paramStr);
        HttpEntity<String> entity=new HttpEntity<>(paramStr,header);
        String api=projectProperties.getGitUrl()+"api/v1/repos/"+orgModel+"/"+jobName.replace("-service","")+"/hooks";
        ResponseEntity<String> resEntity=restTemplate.exchange(api,HttpMethod.POST,entity,String.class);
        String responseStr=resEntity.getBody();
        System.out.println(responseStr);
    }

    @Override
    public boolean updateUserPassword(UserDto newUser, UserPo oldUser) throws UnsupportedEncodingException {
        String gitUpdatePwdUrl=projectProperties.getGitUrl()+"user/settings/account";
        MultiValueMap<String,Object> param=new LinkedMultiValueMap<>();
        setCrsf(gitUpdatePwdUrl,oldUser);
        param.add("_csrf",_csrf);
        param.add("old_password",oldUser.getUserPassword());
        param.add("password",newUser.getUserPassword());
        param.add("retype",newUser.getUserPassword());

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        header.add("Cookie","_csrf="+_csrf+"; i_like_gitea="+i_like_gitea+"; lang="+lang);
        header.add("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity(param, header);
        ResponseEntity<String> resEntity=myRestTemplate.exchange(gitUpdatePwdUrl,HttpMethod.POST,entity,String.class,new Object[0]);
        if(resEntity.getStatusCodeValue()==302){
            header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            header.add("Cookie","_csrf="+_csrf+"; i_like_gitea="+i_like_gitea+"; lang="+lang+"; macaron_flash="+macaron_flash);
            header.add("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            entity = new HttpEntity(null, header);
            myRestTemplate.exchange(gitUpdatePwdUrl,HttpMethod.GET,entity,String.class,new Object[0]);
            return true;
        }else if (resEntity.getStatusCodeValue()==400){
            doSetCrsf(myRestTemplate.responseHeader,oldUser);
        }
        return false;
    }

    private void setCrsf(String gitUpdatePwdUrl, UserPo oldUser) {
        try{
            myRestTemplate.exchange(projectProperties.getGitUrl(),HttpMethod.GET,null,String.class,new Object[0]);
        }catch (Exception e){

        }
        doSetCrsf(myRestTemplate.responseHeader, oldUser);
    }

    private void doSetCrsf(HttpHeaders responseHeader, UserPo oldUser){
        doSetInnerCookies(responseHeader);
        MultiValueMap<String,Object> param=new LinkedMultiValueMap<>();
        param.add("_csrf",_csrf);
        param.add("user_name",oldUser.getUserPassword());
        param.add("password",oldUser.getUserPassword());
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        header.add("Cookie","_csrf="+_csrf+"; i_like_gitea="+i_like_gitea+"; lang="+lang);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity(param, header);
        myRestTemplate.exchange(projectProperties.getGitUrl()+"user/login",HttpMethod.POST,entity,String.class,new Object[0]);
        doSetInnerCookies(myRestTemplate.responseHeader);
        try{
            myRestTemplate.exchange(projectProperties.getGitUrl(),HttpMethod.GET,null,String.class,new Object[0]);
        }catch (Exception e){

        }
        doSetInnerCookies(myRestTemplate.responseHeader);
    }


    public void doSetInnerCookies(HttpHeaders responseHeader){
        if (responseHeader.containsKey("Set-Cookie")){
            List<String> cookies=responseHeader.get("Set-Cookie");
            List<Object> list=cookies.stream().filter(c->c.contains("_csrf=")||c.contains("i_like_gitea")||c.contains("lang")).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(list)){
                String tian=list.toString();
                String temp=tian.replace("[","");
                temp=temp.replace("]","");
                String[] spit=temp.split(",");
                for (int i=0;i<spit.length;i++) {
                    String[] cookieInfo = spit[i].split(";");
                    String[] a = cookieInfo[0].split("=");
                    if (a[0].contains("_csrf")) {
                        if (a.length>1){
                            _csrf = a[1].replace("%3D%3D","==");
                        }else{
                            _csrf="";
                        }
                    } else if (a[0].contains("i_like_gitea")) {
                        i_like_gitea =a[1];
                    } else if (a[0].contains("lang")) {
                        lang =a[1];
                    }else if (a[0].contains("macaron_flash")){
                        macaron_flash=a[1];
                    }
                }
            }
        }
    }
}
