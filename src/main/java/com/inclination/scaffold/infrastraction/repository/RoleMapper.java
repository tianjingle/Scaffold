package com.inclination.scaffold.infrastraction.repository;

import com.inclination.scaffold.infrastraction.repository.po.Role;
import com.inclination.scaffold.infrastraction.repository.po.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    long countByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    int deleteByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    int insert(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    int insertSelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    List<Role> selectByExampleWithRowbounds(RoleExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    List<Role> selectByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    Role selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Sun Jun 02 15:52:19 CST 2019
     */
    int updateByPrimaryKey(Role record);

    /**
     * 按照条件进行查询
     * @param po
     */
    List<Role> selectBySelective(Role po);

    /**
     * 根据权限名称进行查询
     * @param po 查询条件
     * @return  次数
     */
	int countByRoleName(Role po);
}