package com.inclination.scaffold.infrastraction.repository.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "http_trace")
public class TttpTracePo {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 项目id，scaffold的项目id
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 时间标志
     */
    @Column(name = "time_token")
    private Integer timeToken;

    /**
     * 请求信息
     */
    @Column(name = "request_info")
    private String requestInfo;

    /**
     * 返回信息
     */
    @Column(name = "reponse_info")
    private String reponseInfo;

    /**
     * 状态
     */
    private Integer stata;

    /**
     * 创建的时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 预留字段
     */
    private String other;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
     * 获取时间标志
     *
     * @return time_token - 时间标志
     */
    public Integer getTimeToken() {
        return timeToken;
    }

    /**
     * 设置时间标志
     *
     * @param timeToken 时间标志
     */
    public void setTimeToken(Integer timeToken) {
        this.timeToken = timeToken;
    }

    /**
     * 获取请求信息
     *
     * @return request_info - 请求信息
     */
    public String getRequestInfo() {
        return requestInfo;
    }

    /**
     * 设置请求信息
     *
     * @param requestInfo 请求信息
     */
    public void setRequestInfo(String requestInfo) {
        this.requestInfo = requestInfo == null ? null : requestInfo.trim();
    }

    /**
     * 获取返回信息
     *
     * @return reponse_info - 返回信息
     */
    public String getReponseInfo() {
        return reponseInfo;
    }

    /**
     * 设置返回信息
     *
     * @param reponseInfo 返回信息
     */
    public void setReponseInfo(String reponseInfo) {
        this.reponseInfo = reponseInfo == null ? null : reponseInfo.trim();
    }

    /**
     * 获取状态
     *
     * @return stata - 状态
     */
    public Integer getStata() {
        return stata;
    }

    /**
     * 设置状态
     *
     * @param stata 状态
     */
    public void setStata(Integer stata) {
        this.stata = stata;
    }

    /**
     * 获取创建的时间
     *
     * @return create_time - 创建的时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建的时间
     *
     * @param createTime 创建的时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取预留字段
     *
     * @return other - 预留字段
     */
    public String getOther() {
        return other;
    }

    /**
     * 设置预留字段
     *
     * @param other 预留字段
     */
    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }
}