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
    @ExcelProperty(index = 2,value = "宿舍信息")
    private String ifo;

    public DmtrIfo() {
    }

    public String getIfo() {
        return ifo;
    }

    public void setIfo(String ifo) {
        this.ifo = ifo;
    }

    public DmtrIfo(String ifo) {
        this.ifo = ifo;
    }

    @Override
    public String toString() {
        return "DmtrIfo{" +
                       "ifo='" + ifo + '\'' +
                       '}';
    }
}
