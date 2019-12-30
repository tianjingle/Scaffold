package com.inclination.scaffold.infrastraction.otherSystem.git;

import com.inclination.scaffold.application.project.ProjectInformationDto;
import com.inclination.scaffold.application.users.UserDto;
import com.inclination.scaffold.infrastraction.repository.po.UserPo;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

public interface GitService {

    /**
     * 创建仓库
     * @param artifactId
     * @param dto
     * @return
     */
    boolean createGitRepository(String artifactId, UserDto dto);

    /**
     * 创建git项目
     * @param projectDto
     * @param dto
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws JAXBException
     */
    boolean crateGitProject(ProjectInformationDto projectDto, UserDto dto) throws URISyntaxException, IOException, JAXBException;

    /**
     * 创建勾子
     * @param username
     * @param password
     * @param jenkinsUrl
     * @param gitUrl
     * @param orgModel
     * @param jobName
     * @param env
     */
    void createInvoke(String username, String password, String jenkinsUrl, String gitUrl, String orgModel,String jobName, String env);

    /**
     * 修改gitea账户
     * @param newUser
     * @param oldUser
     */
    boolean updateUserPassword(UserDto newUser, UserPo oldUser) throws UnsupportedEncodingException;
}
