package com.zgms.xuefu.tool;

import com.zgms.xuefu.pojo.Building;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/8/4 - 08 - 04 - 11:40
 * @version： 1.0
 * @功能：
 */
public class InitDmtr {
    public static List<Building> initDmtr(){
        Scanner scanner=new Scanner(System.in);
        HashSet<String> set=new HashSet<>();
        List<Building> list=new LinkedList<>();
        while(true){
            String str=scanner.next();
            if("over".equals(str)){
                break;
            }
            int i=0;
            for(;i<str.length();i++){
                if(str.charAt(i)=='-') break;
            }
            str=String.valueOf(str.toCharArray(),0,i);
            if(!set.contains(str)){
                set.add(str);
                list.add(new Building(LocalDateTime.now(),LocalDateTime.now(),str));
            }
        }
        return list;

    }
}
