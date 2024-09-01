package com.zgms.xuefu.easyexcel.dto;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/10/27 - 10 - 27 - 8:40
 * @version： 1.0
 * @功能：
 */
public class SingalWeekLifeCommissioner {

    private String date;
    private String name;
    private boolean flag;



    public SingalWeekLifeCommissioner() {
    }

    @Override
    public String toString() {
        return "SingalWeekLifeCommissioner{" +
                "name='" + name + '\'' +
                ", flag=" + flag +
                ", date='" + date + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SingalWeekLifeCommissioner(String date, String name, boolean flag) {
        this.date = date;
        this.name = name;
        this.flag = flag;
    }
}
