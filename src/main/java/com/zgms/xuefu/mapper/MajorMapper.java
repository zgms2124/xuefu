package com.zgms.xuefu.mapper;

import com.zgms.xuefu.pojo.Major;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/8/4 - 08 - 04 - 9:57
 * @version： 1.0
 * @功能：
 */
@Mapper
public interface MajorMapper {

    @Options(keyProperty = "id",useGeneratedKeys = true)
    public void insert(Major major);

    @Select("select *from tb_major")
    public List<Major> select();

    public int selectId(String name);

    @Select("select name from tb_major where id =#{id}")
    public String selectName(int id);
}
