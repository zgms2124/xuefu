package com.zgms.xuefu.easyexcel.dto;

import java.util.List;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/9/26 - 09 - 26 - 9:52
 * @version： 1.0
 * @功能：
 */
public class UnDownLight {
    private String building;
    private List<Integer> dmtrList;

    public UnDownLight(String building, List<Integer> dmtrList) {
        this.building = building;
        this.dmtrList = dmtrList;
    }

    public String getBuilding() {
        return building;
    }

    @Override
    public String toString() {
        return "UnDownLight{" +
                       "building='" + building + '\'' +
                       ", dmtrList=" + dmtrList +
                       '}';
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public List<Integer> getDmtrList() {
        return dmtrList;
    }

    public void setDmtrList(List<Integer> dmtrList) {
        this.dmtrList = dmtrList;
    }
}
