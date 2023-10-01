package com.zgms.xuefu;

import com.zgms.xuefu.easyexcel.consts.Consts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/9/25 - 09 - 25 - 21:28
 * @version： 1.0
 * @功能：
 */
public class HelpTest {
    public static int getCurrentWeek() {
        try {
            // 创建日期格式化器
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // 获取当前系统日期
            Date currentDate = new Date();

            // 解析第一周的第一天日期
            Date firstWeekStartDate = dateFormat.parse(Consts.FIRST_WEEK_START_DATE);

            // 计算日期差值（毫秒数）
            long timeDiff = currentDate.getTime() - firstWeekStartDate.getTime();

            // 将毫秒数转换为天数差值
            int dayDiff = (int) (timeDiff / (1000 * 60 * 60 * 24));

            // 计算当前是第几周
            int currentWeek = dayDiff / 7 + 1;

            return currentWeek;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // 发生异常时返回-1，可以根据实际情况进行处理
        }
    }
}
