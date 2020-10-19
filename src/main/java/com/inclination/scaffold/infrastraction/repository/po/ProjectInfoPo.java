package com.inclination.scaffold.infrastraction.repository.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "project_info")
public class ProjectInfoPo {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 项目id，scaffold的项目id
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 项目名称
     */
    @Column(name = "project_name")
    private String projectName;

    /**
     * 服务监控检测地址
     */
    @Column(name = "health_url")
    private String healthUrl;

    /**
     * java应用堆内存监控地址
     */
    @Column(name = "dump_url")
    private String dumpUrl;

    /**
     * 项目基本信息
     */
    @Column(name = "info_url")
    private String infoUrl;

    /**
     * 项目名称
     */
    @Column(name = "http_trance_url")
    private String httpTranceUrl;

    /**
     * 项目配置信息
     */
    @Column(name = "properties_url")
    private String propertiesUrl;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 其他信息
     */
    private String other;

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
     * 获取项目id，scaffold的项目id
     *
     * @return project_id - 项目id，scaffold的项目id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 设置项目id，scaffold的项目id
     *
     * @param projectId 项目id，scaffold的项目id
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取项目名称
     *
     * @return project_name - 项目名称
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * 设置项目名称
     *
     * @param projectName 项目名称
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    /**
     * 获取服务监控检测地址
     *
     * @return health_url - 服务监控检测地址
     */
    public String getHealthUrl() {
        return healthUrl;
    }

    /**
     * 设置服务监控检测地址
     *
     * @param healthUrl 服务监控检测地址
     */
    public void setHealthUrl(String healthUrl) {
        this.healthUrl = healthUrl == null ? null : healthUrl.trim();
    }

    /**
     * 获取java应用堆内存监控地址
     *
     * @return dump_url - java应用堆内存监控地址
     */
    public String getDumpUrl() {
        return dumpUrl;
    }

    /**
     * 设置java应用堆内存监控地址
     *
     * @param dumpUrl java应用堆内存监控地址
     */
    public void setDumpUrl(String dumpUrl) {
        this.dumpUrl = dumpUrl == null ? null : dumpUrl.trim();
    }

    /**
     * 获取项目基本信息
     *
     * @return info_url - 项目基本信息
     */
    public String getInfoUrl() {
        return infoUrl;
    }

    /**
     * 设置项目基本信息
     *
     * @param infoUrl 项目基本信息
     */
    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl == null ? null : infoUrl.trim();
    }

    /**
     * 获取项目名称
     *
     * @return http_trance_url - 项目名称
     */
    public String getHttpTranceUrl() {
        return httpTranceUrl;
    }

    /**
     * 设置项目名称
     *
     * @param httpTranceUrl 项目名称
     */
    public void setHttpTranceUrl(String httpTranceUrl) {
        this.httpTranceUrl = httpTranceUrl == null ? null : httpTranceUrl.trim();
    }

    /**
     * 获取项目配置信息
     *
     * @return properties_url - 项目配置信息
     */
    public String getPropertiesUrl() {
        return propertiesUrl;
    }

    /**
     * 设置项目配置信息
     *
     * @param propertiesUrl 项目配置信息
     */
    public void setPropertiesUrl(String propertiesUrl) {
        this.propertiesUrl = propertiesUrl == null ? null : propertiesUrl.trim();
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

    /**
     * 获取其他信息
     *
     * @return other - 其他信息
     */
    public String getOther() {
        return other;
    }

    /**
     * 设置其他信息
     *
     * @param other 其他信息
     */
    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }
}