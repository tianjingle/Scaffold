package com.inclination.scaffold.infrastraction.repository.po;

import java.io.Serializable;

public class Resource implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column resource.id
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column resource.resourcename
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    private String resourcename;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column resource.resourceurl
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    private String resourceurl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column resource.content
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column resource.flag
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    private String flag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table resource
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resource.id
     *
     * @return the value of resource.id
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resource.id
     *
     * @param id the value for resource.id
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resource.resourcename
     *
     * @return the value of resource.resourcename
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    public String getResourcename() {
        return resourcename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resource.resourcename
     *
     * @param resourcename the value for resource.resourcename
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    public void setResourcename(String resourcename) {
        this.resourcename = resourcename == null ? null : resourcename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resource.resourceurl
     *
     * @return the value of resource.resourceurl
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    public String getResourceurl() {
        return resourceurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resource.resourceurl
     *
     * @param resourceurl the value for resource.resourceurl
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    public void setResourceurl(String resourceurl) {
        this.resourceurl = resourceurl == null ? null : resourceurl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resource.content
     *
     * @return the value of resource.content
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resource.content
     *
     * @param content the value for resource.content
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resource.flag
     *
     * @return the value of resource.flag
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    public String getFlag() {
        return flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resource.flag
     *
     * @param flag the value for resource.flag
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resource
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", resourcename=").append(resourcename);
        sb.append(", resourceurl=").append(resourceurl);
        sb.append(", content=").append(content);
        sb.append(", flag=").append(flag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}