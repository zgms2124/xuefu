package com.zgms.xuefu.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/9/3 - 09 - 03 - 15:08
 * @version： 1.0
 * @功能：
 */
@Data
@NoArgsConstructor
public class LifeCommissioner {
    private Integer id;
    private Integer cnt;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String name;
    private int building;
    private int year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LifeCommissioner(LocalDateTime createTime, LocalDateTime updateTime, String name, int building, Integer cnt, int year) {
        this.id = id;
        this.cnt = cnt;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.name = name;
        this.building = building;
        this.year = year;
    }

    public LifeCommissioner(LocalDateTime createTime, LocalDateTime updateTime, String name, int building, int cnt) {
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.name = name;
        this.building = building;
        this.cnt=cnt;
    }
}
