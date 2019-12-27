package com.inclination.scaffold.infrastraction.otherSystem.jenkins;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;

public interface JenkinsService {

    boolean createJobByJenkinsClient(String url, String username, String password, String jobName, String gitUrl,String env) throws URISyntaxException, IOException, JAXBException;

    boolean createUser(String username,String password,String email,String jenkinsurl) throws Exception;

    boolean createMyView(String username, String password, String jenkinsUrl, String viewName);

    boolean updatePwd(String username,String password,String newPwd,String email,String jenkinsUrl);
}
