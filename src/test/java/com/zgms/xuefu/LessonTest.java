package com.zgms.xuefu;

import com.zgms.xuefu.mapper.ClassMapper;
import com.zgms.xuefu.mapper.LessonMapper;
import com.zgms.xuefu.pojo.Lesson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/9/6 - 09 - 06 - 19:39
 * @version： 1.0
 * @功能：
 */
@SpringBootTest
public class LessonTest {

    @Autowired
    LessonMapper lessonMapper;

    @Autowired
    ClassMapper classMapper;

    @Test
    public void insert(){
        Scanner scanner=new Scanner(System.in);
        while (true){
            String str =scanner.nextLine();
            if("over".equals(str)) break;
            lessonMapper.insert(new Lesson(str,classMapper.selectByname("计科2201")));
        }
    }
}
