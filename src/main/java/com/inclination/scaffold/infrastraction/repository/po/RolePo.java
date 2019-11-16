package com.inclination.scaffold.infrastraction.repository.po;

import javax.persistence.*;

@Table(name = "role")
public class RolePo {
    @Id
    private Integer id;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 菜单id
     */
    @Column(name = "menu_id")
    private String menuId;

    /**
     * 内容描述
     */
    private String content;

    /**
     * 标志
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
     * 获取角色名称
     *
     * @return role_name - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 获取菜单id
     *
     * @return menu_id - 菜单id
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单id
     *
     * @param menuId 菜单id
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    /**
     * 获取内容描述
     *
     * @return content - 内容描述
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容描述
     *
     * @param content 内容描述
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取标志
     *
     * @return flag - 标志
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置标志
     *
     * @param flag 标志
     */
    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}