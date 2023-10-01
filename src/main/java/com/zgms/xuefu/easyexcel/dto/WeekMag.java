package com.zgms.xuefu.easyexcel.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/9/18 - 09 - 18 - 23:19
 * @version： 1.0
 * @功能：
 */
public class WeekMag {

    @ExcelProperty("日期")
    private String day;

    @ColumnWidth(20)
    @ExcelProperty("查灯人员")
    private String name;

    @ColumnWidth(114)
    @ExcelProperty("宿舍")
    private String dmtr;

    public WeekMag(String day, String name, String dmtr) {
        this.day = day;
        this.name = name;
        this.dmtr = dmtr;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDmtr() {
        return dmtr;
    }

    public void setDmtr(String dmtr) {
        this.dmtr = dmtr;
    }

    @Override
    public String toString() {
        return "LifeCommissioner{" +
                       "day='" + day + '\'' +
                       ", name='" + name + '\'' +
                       ", dmtr='" + dmtr + '\'' +
                       '}';
    }
}
