package com.wangxy.base.mapper;

import com.wangxy.base.pojo.UlExample;
import com.wangxy.base.pojo.UlKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UlMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_ul
     *
     * @mbggenerated Sun May 26 01:49:50 CST 2019
     */
    int countByExample(UlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_ul
     *
     * @mbggenerated Sun May 26 01:49:50 CST 2019
     */
    int deleteByExample(UlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_ul
     *
     * @mbggenerated Sun May 26 01:49:50 CST 2019
     */
    int deleteByPrimaryKey(UlKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_ul
     *
     * @mbggenerated Sun May 26 01:49:50 CST 2019
     */
    int insert(UlKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_ul
     *
     * @mbggenerated Sun May 26 01:49:50 CST 2019
     */
    int insertSelective(UlKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_ul
     *
     * @mbggenerated Sun May 26 01:49:50 CST 2019
     */
    List<UlKey> selectByExample(UlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_ul
     *
     * @mbggenerated Sun May 26 01:49:50 CST 2019
     */
    int updateByExampleSelective(@Param("record") UlKey record, @Param("example") UlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_ul
     *
     * @mbggenerated Sun May 26 01:49:50 CST 2019
     */
    int updateByExample(@Param("record") UlKey record, @Param("example") UlExample example);
}