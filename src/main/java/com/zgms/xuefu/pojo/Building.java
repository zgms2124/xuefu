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
 * @Date：2023/8/4 - 08 - 04 - 10:57
 * @version： 1.0
 * @功能：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Building {
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String name;

    public Building(LocalDateTime createTime, LocalDateTime updateTime, String name) {
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.name = name;
    }
}
