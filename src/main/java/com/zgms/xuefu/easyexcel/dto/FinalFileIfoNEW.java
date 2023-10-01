package com.zgms.xuefu.easyexcel.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/9/24 - 09 - 24 - 18:57
 * @version： 1.0
 * @功能：
 */
//@OnceAbsoluteMerge(firstRowIndex = 1,lastRowIndex = 1,firstColumnIndex = 0,lastColumnIndex = 7)

@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER )//表头样式
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER )//内容样式
@ColumnWidth(14)
public class FinalFileIfoNEW {
    @ContentLoopMerge(eachRow = 10)
    @ExcelProperty("专业")
    private String major;
    @ContentLoopMerge(eachRow = 2)
    @ExcelProperty(value = "日期")
    private String day;
    @ExcelProperty("年级")
    private String section;
    @ExcelProperty("统计宿舍数")
    private String countDmtr;
    @ExcelProperty("熄灯宿舍数")
    private String downLight;
    @ExcelProperty("熄灯率")
    private String probability;

    @ColumnWidth(45)
    @ExcelProperty("未熄灯寝室")
    private String unDownLight;

    @ColumnWidth(25)
    @ExcelProperty("辅导员")
    private String teacher;
    @ColumnWidth(27)
    @ExcelProperty("查灯人员")
    private String lifeCommissioner;

    public FinalFileIfoNEW(String major, String day, String section, String countDmtr, String downLight, String probability, String unDownLight, String teacher, String lifeCommissioner) {
        this.major = major;
        this.day = day;
        this.section = section;
        this.countDmtr = countDmtr;
        this.downLight = downLight;
        this.probability = probability;
        this.unDownLight = unDownLight;
        this.teacher = teacher;
        this.lifeCommissioner = lifeCommissioner;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCountDmtr() {
        return countDmtr;
    }

    public void setCountDmtr(String countDmtr) {
        this.countDmtr = countDmtr;
    }

    public String getDownLight() {
        return downLight;
    }

    public void setDownLight(String downLight) {
        this.downLight = downLight;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getUnDownLight() {
        return unDownLight;
    }

    public void setUnDownLight(String unDownLight) {
        this.unDownLight = unDownLight;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getLifeCommissioner() {
        return lifeCommissioner;
    }

    public void setLifeCommissioner(String lifeCommissioner) {
        this.lifeCommissioner = lifeCommissioner;
    }

    @Override
    public String toString() {
        return "FinalFileIfoNEW{" +
                       "major='" + major + '\'' +
                       ", day='" + day + '\'' +
                       ", section='" + section + '\'' +
                       ", countDmtr='" + countDmtr + '\'' +
                       ", downLight='" + downLight + '\'' +
                       ", probability='" + probability + '\'' +
                       ", unDownLight='" + unDownLight + '\'' +
                       ", teacher='" + teacher + '\'' +
                       ", lifeCommissioner='" + lifeCommissioner + '\'' +
                       '}';
    }
}
