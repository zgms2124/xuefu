package com.zgms.xuefu;

import com.zgms.xuefu.mapper.ClassMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/9/6 - 09 - 06 - 19:19
 * @version： 1.0
 * @功能：
 */
@SpringBootTest
public class ClassTest {

    @Autowired
    ClassMapper classMapper;

    @Test
    public void insertClass(){
        String str=new String("软件");
        int start=2101,end=2106;
        for(int i=start;i<=end;i++){
            classMapper.insert(str+i);
        }
    }
}
