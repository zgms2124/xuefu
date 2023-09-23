package com.zgms.xuefu;

import com.zgms.xuefu.mapper.BuildingMapper;
import com.zgms.xuefu.mapper.DmtrMapper;
import com.zgms.xuefu.mapper.MajorMapper;
import com.zgms.xuefu.pojo.Dmtr;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/9/18 - 09 - 18 - 12:34
 * @version： 1.0
 * @功能：
 */
@SpringBootTest
public class DmtrTest {

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private BuildingMapper buildingMapper;

    @Autowired
    private DmtrMapper dmtrMapper;

    @Test
    public void addYear(){
        List<Integer> idList=dmtrMapper.selectIdByBuildingAndMajor(buildingMapper.selectId("铁道新2舍"));
        for(int cur:idList){
            dmtrMapper.addYear(cur,2021);
        }
    }

    @Test
    void insetrDmtr(){
//        initDmtr();
//        initDmtrByNum("大数据","升华14栋");
//        initDmtrByNum("计算机","升华14栋");
//        initDmtrByNum("信安","升华14栋");
//
//        initDmtrByNum("大数据","升华27栋");
//        initDmtrByNum("计算机","升华27栋");
//        initDmtrByNum("信安","升华27栋");


        initDmtrByNum("软工","铁道新2舍");
        initDmtrByNum("通信","铁道11舍");

    }

    private void initDmtrByNum(String major,String building) {
        System.out.println("现在导入的是"+building+major);
        Scanner scanner=new Scanner(System.in);
        while(true){
            int begin=scanner.nextInt();
            int over=scanner.nextInt();
            if(begin==0&&over==0) break;
            for(int i=begin;i<=over;i++){
                dmtrMapper.insert(new Dmtr(LocalDateTime.now(),LocalDateTime.now(),
                        i,buildingMapper.selectId(building),majorMapper.selectId(major)));
            }
        }
    }


    @Test
    public void countDmtr(){

        HashSet<Integer> set=new HashSet<>();
        List<Integer> list1= dmtrMapper.selectByBuildingAndMajor(majorMapper.selectId("软工"), buildingMapper.selectId("铁道新1舍"));
        List<Integer> list2= dmtrMapper.selectByBuildingAndMajor(majorMapper.selectId("软工"), buildingMapper.selectId("铁道2舍"));
        for(int cur:list1) set.add(cur);
        for(int cur:list2) set.add(cur);
        System.out.println(set.size());

        countDmtrSigal("升华14栋");
        countDmtrSigal("升华28栋北");
        countDmtrSigal("升华27栋");
        countDmtrSigal("升华29栋");
        countDmtrSigal("铁道2舍");
        countDmtrSigal("铁道11舍");
        countDmtrSigal("铁道新2舍");
        countDmtrSigal("铁道新1舍");
        List<Integer> list3= dmtrMapper.selectByBuildingAndMajor(majorMapper.selectId("物联网"), buildingMapper.selectId("升华14栋"));
        for(int cur:list3) set.add(cur);
        System.out.println(set.size());
    }

    public void countDmtrSigal(String dmtr){
        System.out.println("查询"+dmtr);
        System.out.println("输入“结束”,退出该宿舍的查询");
        Scanner scanner=new Scanner(System.in);
        while  (true){
            String str=scanner.nextLine();
            if("结束".equals(str)) break;
            List<Integer> listNum=new LinkedList<>();
            int num=0;
            for(int i=0;i<str.length();i++){
                char c=str.charAt(i);
                if(c<='9'&&c>='0') {
                    num=num*10+(c-'0');
                }
                else {
                    if(num!=0)
                        listNum.add(num);
                    num=0;
                }
            }
            if(num!=0)
                listNum.add(num);
            HashMap<String,List<Integer>> map=new HashMap<>();
            for(int cur:listNum){
                List<Integer> list=dmtrMapper.selectByBuildingAndNum(buildingMapper.selectId(dmtr),cur);
                if(list!=null&&list.size()>0) {
                    String major= majorMapper.selectName(list.get(0));
                    if(!map.containsKey(major)){
                        map.put(major,new LinkedList<>());
                    }
                    map.get(major).add(cur);
                }
            }
            for(Map.Entry<String,List<Integer>> curMap:map.entrySet()){
                System.out.print(curMap.getKey());
                List<Integer> numList=curMap.getValue();
                System.out.print(numList.get(0));
                numList.remove(0);
                for(int cur:numList){
                    System.out.print("、"+cur);
                }
                System.out.println();
            }

            System.out.println("是否继续输入？");
        }
    }


    @Test
    public void selectDmtr(){
        selectDmtrSingal("升华14栋");
        selectDmtrSingal("升华28栋北");
        selectDmtrSingal("升华27栋");
        selectDmtrSingal("升华29栋");
        selectDmtrSingal("铁道2舍");
        selectDmtrSingal("铁道11舍");
        selectDmtrSingal("铁道新2舍");
        selectDmtrSingal("铁道新1舍");
    }

    @Test
    public void selectDmtrSingal(String str){
        List<Dmtr> sh14=dmtrMapper.selectByBuilding(buildingMapper.selectId(str));
        HashSet<Integer> set14=new HashSet<>();
        for(Dmtr dmtr:sh14){
            set14.add(dmtr.getNum());
        }
        int []arr =new int[set14.size()];
        int r=0;
        for(int num:set14){
            arr[r++]=num;
        }
        Arrays.sort(arr);
        boolean flag1=false;
        System.out.println(str);
        for(int i=0;i<arr.length;i++){
            if(i==0) System.out.print(arr[i]);
            else{
                if(arr[i]!=arr[i-1]+1) {
                    if(flag1)
                    System.out.print("-"+arr[i-1]+"，"+arr[i]);
                    else System.out.print("，"+arr[i]);
                    flag1=false;
                }
                else{
                    flag1=true;
                }
            }
        }
        if(flag1){
            System.out.println("-"+arr[arr.length-1]);
        }
        System.out.println();
    }
}
