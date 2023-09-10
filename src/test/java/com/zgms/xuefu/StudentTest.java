package com.zgms.xuefu;

import com.zgms.xuefu.mapper.StudentMapper;
import com.zgms.xuefu.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/9/10 - 09 - 10 - 23:25
 * @version： 1.0
 * @功能：
 */
@SpringBootTest
public class StudentTest {

    @Autowired
    StudentMapper studentMapper;

    @Test
    public void initSetCnt(){
        List<Student> list=studentMapper.selectAll();
        for(Student student:list){
            studentMapper.setCnt(student.getId(),0,LocalDateTime.now());
        }
    }

    @Test
    public void addCnt(){
        Scanner scanner=new Scanner(System.in);
        while (true){
            String name=scanner.nextLine();
            Student student=studentMapper.selectByName(name);
            studentMapper.setCnt(student.getId(),student.getCnt()+1, LocalDateTime.now());
        }
    }
}
