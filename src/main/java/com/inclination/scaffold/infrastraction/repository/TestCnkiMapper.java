package com.inclination.scaffold.infrastraction.repository;

import com.inclination.scaffold.infrastraction.repository.po.TestCnki;
import com.inclination.scaffold.infrastraction.repository.po.TestCnkiExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TestCnkiMapper {
    /**
     * @mbg.generated countByExample
     */
    long countByExample(TestCnkiExample example);

    /**
     * @mbg.generated deleteByExample
     */
    int deleteByExample(TestCnkiExample example);

    /**
     * @mbg.generated deleteByPrimaryKey
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @mbg.generated insert
     */
    int insert(TestCnki record);

    /**
     * @mbg.generated insertSelective
     */
    int insertSelective(TestCnki record);

    /**
     * @mbg.generated selectByExample
     */
    List<TestCnki> selectByExampleWithRowbounds(TestCnkiExample example, RowBounds rowBounds);

    /**
     * @mbg.generated selectByExample
     */
    List<TestCnki> selectByExample(TestCnkiExample example);

    /**
     * @mbg.generated selectByPrimaryKey
     */
    TestCnki selectByPrimaryKey(Integer id);

    /**
     * @mbg.generated updateByExampleSelective
     */
    int updateByExampleSelective(@Param("record") TestCnki record, @Param("example") TestCnkiExample example);

    /**
     * @mbg.generated updateByExample
     */
    int updateByExample(@Param("record") TestCnki record, @Param("example") TestCnkiExample example);

    /**
     * @mbg.generated updateByPrimaryKeySelective
     */
    int updateByPrimaryKeySelective(TestCnki record);

    /**
     * @mbg.generated updateByPrimaryKey
     */
    int updateByPrimaryKey(TestCnki record);
}