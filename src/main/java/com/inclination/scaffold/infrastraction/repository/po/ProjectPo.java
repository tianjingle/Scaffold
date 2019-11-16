package com.inclination.scaffold.infrastraction.repository.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "project")
public class ProjectPo {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 登陆用户的手机号
     */
    @Column(name = "login_id")
    private String loginId;

    /**
     * git项目url
     */
    @Column(name = "git_url")
    private String gitUrl;

    /**
     * jenkins项目url
     */
    @Column(name = "jenkins_url")
    private String jenkinsUrl;

    /**
     * apollo项目url
     */
    @Column(name = "apollo_url")
    private String apolloUrl;

    /**
     * 项目名称
     */
    @Column(name = "artifact_id")
    private String artifactId;

    /**
     * 基础包名
     */
    @Column(name = "group_id")
    private String groupId;

    /**
     * 版本号
     */
    private String version;

    /**
     * git的组织
     */
    @Column(name = "git_org")
    private String gitOrg;

    /**
     * apollo工程的组织
     */
    @Column(name = "apollo_org")
    private String apolloOrg;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取登陆用户的手机号
     *
     * @return login_id - 登陆用户的手机号
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * 设置登陆用户的手机号
     *
     * @param loginId 登陆用户的手机号
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId == null ? null : loginId.trim();
    }

    /**
     * 获取git项目url
     *
     * @return git_url - git项目url
     */
    public String getGitUrl() {
        return gitUrl;
    }

    /**
     * 设置git项目url
     *
     * @param gitUrl git项目url
     */
    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl == null ? null : gitUrl.trim();
    }

    /**
     * 获取jenkins项目url
     *
     * @return jenkins_url - jenkins项目url
     */
    public String getJenkinsUrl() {
        return jenkinsUrl;
    }

    /**
     * 设置jenkins项目url
     *
     * @param jenkinsUrl jenkins项目url
     */
    public void setJenkinsUrl(String jenkinsUrl) {
        this.jenkinsUrl = jenkinsUrl == null ? null : jenkinsUrl.trim();
    }

    /**
     * 获取apollo项目url
     *
     * @return apollo_url - apollo项目url
     */
    public String getApolloUrl() {
        return apolloUrl;
    }

    /**
     * 设置apollo项目url
     *
     * @param apolloUrl apollo项目url
     */
    public void setApolloUrl(String apolloUrl) {
        this.apolloUrl = apolloUrl == null ? null : apolloUrl.trim();
    }

    /**
     * 获取项目名称
     *
     * @return artifact_id - 项目名称
     */
    public String getArtifactId() {
        return artifactId;
    }

    /**
     * 设置项目名称
     *
     * @param artifactId 项目名称
     */
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId == null ? null : artifactId.trim();
    }

    /**
     * 获取基础包名
     *
     * @return group_id - 基础包名
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置基础包名
     *
     * @param groupId 基础包名
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    /**
     * 获取版本号
     *
     * @return version - 版本号
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置版本号
     *
     * @param version 版本号
     */
    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    /**
     * 获取git的组织
     *
     * @return git_org - git的组织
     */
    public String getGitOrg() {
        return gitOrg;
    }

    /**
     * 设置git的组织
     *
     * @param gitOrg git的组织
     */
    public void setGitOrg(String gitOrg) {
        this.gitOrg = gitOrg == null ? null : gitOrg.trim();
    }

    /**
     * 获取apollo工程的组织
     *
     * @return apollo_org - apollo工程的组织
     */
    public String getApolloOrg() {
        return apolloOrg;
    }

    /**
     * 设置apollo工程的组织
     *
     * @param apolloOrg apollo工程的组织
     */
    public void setApolloOrg(String apolloOrg) {
        this.apolloOrg = apolloOrg == null ? null : apolloOrg.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}