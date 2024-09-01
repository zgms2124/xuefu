package com.zgms.xuefu.easyexcel.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/10/1 - 10 - 01 - 12:52
 * @version： 1.0
 * @功能：
 */
@ColumnWidth(14)
public class UnWriteLifeCommissioner {
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("年级")
    private int year;
    @ExcelProperty("班级")
    private String klass;
    @ExcelProperty("安排查灯次数")
    private int cnt;
    @ExcelProperty("未查灯次数")
    private int unCnt;
    @ExcelProperty("比例")
    private String percent;

    @Override
    public String toString() {
        return "UnWriteLifeCommissioner{" +
                       "name='" + name + '\'' +
                       ", year=" + year +
                       ", klass='" + klass + '\'' +
                       ", cnt=" + cnt +
                       ", unCnt=" + unCnt +
                       ", percent='" + percent + '\'' +
                       '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getKlass() {
        return klass;
    }

    public void setKlass(String klass) {
        this.klass = klass;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getUnCnt() {
        return unCnt;
    }

    public void setUnCnt(int unCnt) {
        this.unCnt = unCnt;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public UnWriteLifeCommissioner(String name, int year, String klass, int cnt, int unCnt, String percent) {
        this.name = name;
        this.year = year;
        this.klass = klass;
        this.cnt = cnt;
        this.unCnt = unCnt;
        this.percent = percent;
    }
}
