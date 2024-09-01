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
 * @Date：2023/9/3 - 09 - 03 - 15:08
 * @version： 1.0
 * @功能：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LifeCommissioner {
    private Integer id;
    private String name;
    private int building;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer cnt;
    private int year;
    private Integer unCnt;
    private String unWriteDate;
    private String klass;

    public LifeCommissioner(String name, int building, LocalDateTime createTime, LocalDateTime updateTime, Integer cnt, int year, Integer unCnt, String klass) {
        this.name = name;
        this.building = building;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.cnt = cnt;
        this.year = year;
        this.unCnt = unCnt;
        this.klass = klass;
    }
}
