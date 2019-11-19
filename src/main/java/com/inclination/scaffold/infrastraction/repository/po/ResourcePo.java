package com.inclination.scaffold.infrastraction.repository.po;

import javax.persistence.*;

@Table(name = "resource")
public class ResourcePo {


    @Id
    private Integer id;

    /**
     * 资源名称
     */
    @Column(name = "resource_name")
    private String resourceName;

    /**
     * 资源路径h5
     */
    @Column(name = "resource_url")
    private String resourceUrl;

    /**
     * 资源描述
     */
    private String content;

    /**
     * 其他标志
     */
    private String flag;

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
     * 获取资源名称
     *
     * @return resource_name - 资源名称
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * 设置资源名称
     *
     * @param resourceName 资源名称
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    /**
     * 获取资源路径h5
     *
     * @return resource_url - 资源路径h5
     */
    public String getResourceUrl() {
        return resourceUrl;
    }

    /**
     * 设置资源路径h5
     *
     * @param resourceUrl 资源路径h5
     */
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
    }

    /**
     * 获取资源描述
     *
     * @return content - 资源描述
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置资源描述
     *
     * @param content 资源描述
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取其他标志
     *
     * @return flag - 其他标志
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置其他标志
     *
     * @param flag 其他标志
     */
    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}