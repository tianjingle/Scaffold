package com.inclination.scaffold.infrastraction.repository.po;

import javax.persistence.*;

@Table(name = "menu")
public class MenuPo {
    @Id
    private Integer id;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单描述
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
     * 获取菜单名称
     *
     * @return menu_name - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * 获取菜单描述
     *
     * @return content - 菜单描述
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置菜单描述
     *
     * @param content 菜单描述
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