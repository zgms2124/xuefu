package com.zgms.xuefu;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/9/23 - 09 - 23 - 19:40
 * @version： 1.0
 * @功能：
 */
public class Person {
    public static void main(String []args){
        System.out.println(String.valueOf("升华28栋北-119".toCharArray(),7,3));
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();

        // 获取当前日期所在周的上一周的周日
        LocalDate lastSunday = currentDate.with(DayOfWeek.SUNDAY).minusWeeks(1);

        // 输出上一周的周日日期
        String lastSundayFormatted = lastSunday.format(DateTimeFormatter.ofPattern("M.d"));
        System.out.println(lastSundayFormatted);

        // 输出本周的周一至周四的日期
        LocalDate monday = lastSunday.plusDays(1);
        for (int i = 0; i < 4; i++) {
            // 格式化日期为A.B格式
            String formattedDate = monday.plusDays(i).format(DateTimeFormatter.ofPattern("M.d"));

            // 输出日期

            System.out.println();
            System.out.println(formattedDate);
        }
        SH();
        TD();
    }
    public static void SH(){
        Scanner sc=new Scanner(System.in);
        String [][]str1=new String[5][4];
        for(int i=0;i<5;i++){
            for(int j=0;j<4;j++){
                str1[i][j]=sc.next();
            }
        }
        System.out.println("信安：");
        for(int i=0;i<5;i++){
            System.out.println(str1[i][0]+"、"+str1[i][2]);
            System.out.println(str1[i][1]+"、"+str1[i][3]);

        }
        System.out.println("计科：");
        for(int i=0;i<5;i++){
            System.out.println(str1[i][0]+"、"+str1[i][2]);
            System.out.println(str1[i][1]+"、"+str1[i][3]);
        }
        System.out.println("大数据：");
        for(int i=0;i<5;i++){
            System.out.println(str1[i][0]+"、"+str1[i][2]);
            System.out.println(str1[i][1]+"、"+str1[i][3]);
        }
//        System.out.println("物联网：");
//        for(int i=0;i<5;i++){
//            System.out.println(str1[i][2]+"，"+str1[i][3]);
//            System.out.println();
//        }
//        System.out.println("23级：");
//        for(int i=0;i<5;i++){
//            System.out.println(str1[i][1]+"，"+str1[i][2]+"，"+str1[i][4]);
//            System.out.println(str1[i][1]+"，"+str1[i][2]+"，"+str1[i][4]);
//        }
    }
    public static void TD(){
        Scanner sc=new Scanner(System.in);
        String [][]str1=new String[5][4];
        for(int i=0;i<5;i++){
            for(int j=0;j<4;j++){
                str1[i][j]=sc.next();
            }
        }
        System.out.println("铁道：");
        for(int i=0;i<5;i++){
            System.out.print(str1[i][1]+"、"+str1[i][2]+"\n"+str1[i][0]+"、"+str1[i][3]+"\n");
        }
    }
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int n = colsum.length;
        int sum = 0, two = 0;
        for (int i = 0; i < n; ++i) {
            if (colsum[i] == 2) {
                two++;
            }
            sum += colsum[i];
        }
        if (sum != upper + lower ||upper<two||lower<two) {
            return new ArrayList<>();
        }
        upper -= two;
        lower -= two;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < 2; ++i) {
            res.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < n; ++i) {
            if (colsum[i] == 2) {
                res.get(0).add(1);
                res.get(1).add(1);
            } else if (colsum[i] == 1) {
                if (upper > 0) {
                    res.get(0).add(1);
                    res.get(1).add(0);
                    --upper;
                } else if(lower>0){
                    res.get(0).add(0);
                    res.get(1).add(1);
                }
                else{
                    return new ArrayList<>();
                }
            } else {
                res.get(0).add(0);
                res.get(1).add(0);
            }
        }
        return res;
    }

}
