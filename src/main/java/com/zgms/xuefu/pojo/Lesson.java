package com.zgms.xuefu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/9/6 - 09 - 06 - 19:37
 * @version： 1.0
 * @功能：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    private Integer id;
    private String name;
    private int klass;

    public Lesson(String name, int klass) {
        this.name = name;
        this.klass = klass;
    }
}