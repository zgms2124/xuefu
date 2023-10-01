package com.zgms.xuefu.easyexcel.consts;

import java.time.LocalDateTime;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/9/24 - 09 - 24 - 19:08
 * @version： 1.0
 * @功能：
 */
public final class Consts {
    public static final String FIRST_WEEK_START_DATE = "2023-09-03";
    public static String 信安21="许宁";
    public static String 信安22="刘烁烁";
    public static String 计算机21="迟旭";
    public static String 计算机22="陈司宇";
    public static String 大数据21="许宁";
    public static String 大数据22="刘烁烁";
    public static String 软工21="宋晓萌";
    public static String 软工22="何星霖";
    public static String 通信21="宋晓萌";
    public static String 通信22="何星霖";

    public static String 计通23="吾勒盼·巴特尔、姜雨辰";
    public static String 软工类23="袁熠";


    public static Object getConstantValue(String constantName) {
        try {
            // 使用反射获取常量值
            Class<?> clazz = Consts.class;
            java.lang.reflect.Field field = clazz.getDeclaredField(constantName);
            return field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // 如果常量名不存在或访问失败，可以根据需求处理异常情况
            e.printStackTrace();
            return null; // 或者抛出自定义异常
        }
    }
}
