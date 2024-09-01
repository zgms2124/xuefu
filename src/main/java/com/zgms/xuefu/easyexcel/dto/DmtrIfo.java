package com.zgms.xuefu.easyexcel.dto;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/9/24 - 09 - 24 - 0:21
 * @version： 1.0
 * @功能：
 */
public class DmtrIfo {

    @ExcelProperty(index = 0)
    private String dataIfo;
    @ExcelProperty(index = 1,value = "查灯人员")
    private String studentIfo;
    @ExcelProperty(index = 2,value = "宿舍信息")
    private String lightIfo;

    public DmtrIfo() {
    }

    @Override
    public String toString() {
        return "DmtrIfo{" +
                "dataIfo='" + dataIfo + '\'' +
                ", studentIfo='" + studentIfo + '\'' +
                ", lightIfo='" + lightIfo + '\'' +
                '}';
    }

    public String getDataIfo() {
        return dataIfo;
    }

    public void setDataIfo(String dataIfo) {
        this.dataIfo = dataIfo;
    }

    public String getStudentIfo() {
        return studentIfo;
    }

    public void setStudentIfo(String studentIfo) {
        this.studentIfo = studentIfo;
    }

    public String getLightIfo() {
        return lightIfo;
    }

    public void setLightIfo(String lightIfo) {
        this.lightIfo = lightIfo;
    }
}
