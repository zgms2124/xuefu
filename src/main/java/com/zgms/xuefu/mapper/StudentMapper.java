package com.zgms.xuefu.mapper;

import com.zgms.xuefu.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/9/3 - 09 - 03 - 15:09
 * @version： 1.0
 * @功能：
 */
@Mapper
public interface StudentMapper {

    @Options(keyProperty = "id",useGeneratedKeys = true)
    public void insert(Student student);

    @Select("select * from tb_student")
    public List<Student> selectAll();

    @Select("select *from tb_student where building=#{building}")
    public List<Student> selectByBuilding(int building);
}
