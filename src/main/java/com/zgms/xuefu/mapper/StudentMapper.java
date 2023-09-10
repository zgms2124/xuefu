package com.zgms.xuefu.mapper;

import com.zgms.xuefu.pojo.Student;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
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

    @Select("select * from tb_life_commissioner")
    public List<Student> selectAll();

    @Select("select count(*) from tb_life_commissioner")
    public int selectCount();

    @Select("select *from tb_life_commissioner where building=#{building}")
    public List<Student> selectByBuilding(int building);

    @Select("select * from tb_life_commissioner where name=#{name}")
    public Student selectByName(String name);

    @Delete("delete from tb_life_commissioner where name=#{name}")
    public void delete(String name);

    @Update("update tb_life_commissioner set cnt=#{cnt},update_time=#{updatetime} where id=#{id}")
    public void setCnt(@Param("id") int id,@Param("cnt") int cnt,@Param("updatetime") LocalDateTime updatetime);
}
