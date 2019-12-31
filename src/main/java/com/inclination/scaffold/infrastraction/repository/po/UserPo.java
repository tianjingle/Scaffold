package com.inclination.scaffold.infrastraction.repository.po;


import javax.persistence.*;

@Table(name = "user")
public class UserPo {
    @Id
    private Integer id;

    /**
     * 用户登录号，手机号
     */
    @Column(name = "login_id")
    private String loginId;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户密码
     */
    @Column(name = "user_password")
    private String userPassword;

    /**
     * 用户电子邮箱
     */
    @Column(name = "user_emil")
    private String userEmil;

    /**
     * 用户权限标志
     */
    @Column(name = "ro_id")
    private Integer roId;

    @Column(name = "org_id")
    private Integer orgId;

    @Column(name = "org_Name")
    private String orgName;


    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户登录号，手机号
     *
     * @return login_id - 用户登录号，手机号
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * 设置用户登录号，手机号
     *
     * @param loginId 用户登录号，手机号
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId == null ? null : loginId.trim();
    }

    /**
     * 获取用户名称
     *
     * @return user_name - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取用户密码
     *
     * @return user_password - 用户密码
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 设置用户密码
     *
     * @param userPassword 用户密码
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    /**
     * 获取用户电子邮箱
     *
     * @return user_emil - 用户电子邮箱
     */
    public String getUserEmil() {
        return userEmil;
    }

    /**
     * 设置用户电子邮箱
     *
     * @param userEmil 用户电子邮箱
     */
    public void setUserEmil(String userEmil) {
        this.userEmil = userEmil == null ? null : userEmil.trim();
    }

    /**
     * 获取用户权限标志
     *
     * @return ro_id - 用户权限标志
     */
    public Integer getRoId() {
        return roId;
    }

    /**
     * 设置用户权限标志
     *
     * @param roId 用户权限标志
     */
    public void setRoId(Integer roId) {
        this.roId = roId;
    }
}