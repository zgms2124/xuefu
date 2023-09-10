package com.zgms.xuefu.mapper;

import com.zgms.xuefu.pojo.Dmtr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/9/6 - 09 - 06 - 19:17
 * @version： 1.0
 * @功能：
 */
@Mapper
public interface ClassMapper {

    @Options(keyProperty = "id",useGeneratedKeys = true)
    @Select("insert into tb_class(name) values (#{name})")
    public void insert(String name);

    @Select("select id from tb_class where name=#{name}")
    public int selectByname(String name);
}
