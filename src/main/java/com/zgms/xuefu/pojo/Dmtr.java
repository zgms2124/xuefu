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
 * @Date：2023/8/5 - 08 - 05 - 11:19
 * @version： 1.0
 * @功能：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dmtr {
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private int num;
    private int building;
    private int major;

    public Dmtr(LocalDateTime createTime, LocalDateTime updateTime, int num, int building, int major) {
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.num = num;
        this.building = building;
        this.major = major;
    }
}
