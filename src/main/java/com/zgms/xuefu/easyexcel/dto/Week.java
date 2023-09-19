package com.zgms.xuefu.easyexcel.dto;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/9/19 - 09 - 19 - 8:18
 * @version： 1.0
 * @功能：
 */
public class Week {
    @ExcelProperty(value = "日期",index = 0)
    private String day;

    public Week() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Week(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Week{" +
                       "day='" + day + '\'' +
                       '}';
    }
}
