package com.zgms.xuefu.easyexcel.dto;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/9/21 - 09 - 21 - 16:02
 * @version： 1.0
 * @功能：
 */
public class Klass {
    private String day;
    private String classNameSun;
    private String classNameMon;
    private String classNameTus;
    private String classNameWes;
    private String classNameThur;
    private String classNameFri;
    private String classNameSat;

    public Klass() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getClassNameSun() {
        return classNameSun;
    }

    public void setClassNameSun(String classNameSun) {
        this.classNameSun = classNameSun;
    }

    public String getClassNameMon() {
        return classNameMon;
    }

    public void setClassNameMon(String classNameMon) {
        this.classNameMon = classNameMon;
    }

    public String getClassNameTus() {
        return classNameTus;
    }

    public void setClassNameTus(String classNameTus) {
        this.classNameTus = classNameTus;
    }

    public String getClassNameWes() {
        return classNameWes;
    }

    public void setClassNameWes(String classNameWes) {
        this.classNameWes = classNameWes;
    }

    public String getClassNameThur() {
        return classNameThur;
    }

    public void setClassNameThur(String classNameThur) {
        this.classNameThur = classNameThur;
    }

    public String getClassNameFri() {
        return classNameFri;
    }

    public void setClassNameFri(String classNameFri) {
        this.classNameFri = classNameFri;
    }

    public String getClassNameSat() {
        return classNameSat;
    }

    public void setClassNameSat(String classNameSat) {
        this.classNameSat = classNameSat;
    }

    public Klass(String day, String classNameSun, String classNameMon, String classNameTus, String classNameWes, String classNameThur, String classNameFri, String classNameSat) {
        this.day = day;
        this.classNameSun = classNameSun;
        this.classNameMon = classNameMon;
        this.classNameTus = classNameTus;
        this.classNameWes = classNameWes;
        this.classNameThur = classNameThur;
        this.classNameFri = classNameFri;
        this.classNameSat = classNameSat;
    }

    @Override
    public String toString() {
        return "klass{" +
                       "day='" + day + '\'' +
                       ", classNameSun='" + classNameSun + '\'' +
                       ", classNameMon='" + classNameMon + '\'' +
                       ", classNameTus='" + classNameTus + '\'' +
                       ", classNameWes='" + classNameWes + '\'' +
                       ", classNameThur='" + classNameThur + '\'' +
                       ", classNameFri='" + classNameFri + '\'' +
                       ", classNameSat='" + classNameSat + '\'' +
                       '}';
    }
}
