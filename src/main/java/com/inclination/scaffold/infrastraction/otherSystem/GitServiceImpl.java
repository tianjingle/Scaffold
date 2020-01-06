package com.inclination.scaffold.infrastraction.otherSystem;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.inclination.scaffold.application.project.ProjectInformationDto;
import com.inclination.scaffold.application.users.UserDto;
import com.inclination.scaffold.constant.config.OtherSystemProperties;
import com.inclination.scaffold.constant.exception.TErrorCode;
import com.inclination.scaffold.constant.exception.TException;
import com.inclination.scaffold.infrastraction.otherSystem.git.GitService;
import com.inclination.scaffold.infrastraction.otherSystem.git.vo.GitUserDto;
import com.inclination.scaffold.infrastraction.repository.UserPoMapper;
import com.inclination.scaffold.infrastraction.repository.po.UserPo;
import com.inclination.scaffold.utils.CMDExecuteUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Example;

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

    @Autowired
    private UserPoMapper userPoMapper;


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
    public boolean createGitRepository(String artifactId, UserDto dto) throws TException {
        // TODO Auto-generated method stub
        if (dto.getRoId()==3){
            addCurrentUser2Org(dto);
        }
        String orgModel=dto.getOrgName()+"-org";
        String username=dto.getUserName();
        String password=dto.getUserPassword();
        String gitUrl=projectProperties.getGitUrl();
        String targertUrl=gitUrl+"api/v1/org/"+orgModel+"/repos";
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
            ResponseEntity<String> resEntity=this.restTemplate.exchange(targertUrl, HttpMethod.POST,entity,String.class);
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
     * 把当前git用户添加到团队里
     * @param dto
     */
    private void addCurrentUser2Org(UserDto dto) throws TException {
        Example example=new Example(UserPo.class);
        example.createCriteria().andEqualTo("roId","2").andEqualTo("orgId",dto.getOrgId());
        List<UserPo> list=userPoMapper.selectByExample(example);
        if (list.size()<1){
            throw new TException("","当前机构没有组织者");
        }
        String orgUrl=projectProperties.getGitUrl()+"api/v1/orgs/"+dto.getOrgName()+"-org/teams";
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        if (null != list.get(0).getUserName() && null != list.get(0).getUserPassword()) {
            String userMsg = list.get(0).getUserName() + ":" + list.get(0).getUserPassword();
            String base64UserMsg = Base64.encodeBase64String(userMsg.getBytes());
            header.set("Authorization", "Basic " + base64UserMsg);
        }

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity(null, header);
        ResponseEntity<String> resEntity = restTemplate.exchange(orgUrl, HttpMethod.GET, entity, String.class, new Object[0]);
        List<GitUserDto> view=JSON.parseArray(resEntity.getBody(), GitUserDto.class);

        String add2Org=projectProperties.getGitUrl()+"api/v1/teams/"+view.get(0).getId()+"/members/"+dto.getUserName();
        header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        if (null != list.get(0).getUserName() && null != list.get(0).getUserPassword()) {
            String userMsg = list.get(0).getUserName() + ":" + list.get(0).getUserPassword();
            String base64UserMsg = Base64.encodeBase64String(userMsg.getBytes());
            header.set("Authorization", "Basic " + base64UserMsg);
        }
        entity = new HttpEntity(null, header);
        resEntity = restTemplate.exchange(add2Org, HttpMethod.PUT, entity, String.class, new Object[0]);
        System.out.println(resEntity);
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
        String gitUrl = projectProperties.getGitUrl() + "" + dto.getOrgName() + "-org/" + artifactId + ".git";
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
        //config.put("url", jenkinsUrl+"gogs-webhook/?job="+jobName+"-"+env);
        config.put("url", jenkinsUrl+"generic-webhook-trigger/invoke?token="+jobName);
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


    /**
     * 更新git账户
     * @param newUser
     * @param oldUser
     * @return
     */
    public boolean updateUserGit(UserDto newUser, UserPo oldUser){
        MultiValueMap<String,Object> param=new LinkedMultiValueMap<>();
        param.add("active",true);
        param.add("admin",false);
        param.add("allow_create_organization",true);
        param.add("allow_git_hook",true);
        param.add("allow_import_local",true);
        param.add("email",newUser.getUserEmil());
        param.add("full_name","");
        param.add("location","");
        param.add("login_name",oldUser.getUserName());
        param.add("max_repo_creation","-1");
        param.add("password",newUser.getUserPassword());
        param.add("must_change_password",true);
        param.add("prohibit_login",true);
        param.add("website","");
        param.add("source_id",0);
        String url=projectProperties.getGitUrl()+"api/v1/admin/users/"+oldUser.getUserName();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        if (null != projectProperties.getGitAdmin() && null != projectProperties.getGitPassword()) {
            String userMsg = projectProperties.getGitAdmin() + ":" + projectProperties.getGitPassword();
            String base64UserMsg = Base64.encodeBase64String(userMsg.getBytes());
            header.set("Authorization", "Basic " + base64UserMsg);
        }
        RestTemplate restTemplate1 = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity(param, header);
        try{
            restTemplate1.patchForObject(url, entity, String.class, new Object[0]);
        }catch (Exception e){
            return false;
        }
        return true;

    }
    /**
     * 修改用户密码
     * @param newUser
     * @param oldUser
     * @return
     * @throws UnsupportedEncodingException
     */
    @Override
    public boolean updateUserPassword(UserDto newUser, UserPo oldUser){
        return updateUserGit(newUser,oldUser);
    }


    /**
     * 获取cookie
     * @param responseHeader
     */
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
