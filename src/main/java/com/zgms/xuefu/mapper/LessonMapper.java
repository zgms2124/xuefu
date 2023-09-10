package com.zgms.xuefu.mapper;

import com.zgms.xuefu.pojo.Lesson;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/9/6 - 09 - 06 - 19:36
 * @version： 1.0
 * @功能：
 */
@Mapper
public interface LessonMapper {
    @Insert("insert into tb_lesson(name, klass) VALUES (#{name},#{klass})")
    @Options(keyProperty = "id",useGeneratedKeys = true)
    public void insert(Lesson lesson);
}
