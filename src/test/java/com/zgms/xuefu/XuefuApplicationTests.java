package com.zgms.xuefu;

import com.zgms.xuefu.mapper.BuildingMapper;
import com.zgms.xuefu.mapper.DmtrMapper;
import com.zgms.xuefu.mapper.MajorMapper;
import com.zgms.xuefu.mapper.LifeCommissonerMapper;
import com.zgms.xuefu.pojo.Building;
import com.zgms.xuefu.pojo.Dmtr;
import com.zgms.xuefu.pojo.Major;
import com.zgms.xuefu.pojo.LifeCommissioner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
class XuefuApplicationTests {

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private BuildingMapper buildingMapper;

    @Autowired
    private DmtrMapper dmtrMapper;

    @Autowired
    private LifeCommissonerMapper lifeCommissonerMapper;

    @Test
    void contextLoads() {
        majorMapper.select();
    }

    @Test
    void insertMajor(){
        List<Major> list= initMajor();
        for(Major major:list){
            majorMapper.insert(major);
        }
    }

    private List<Major> initMajor() {
        List<Major> list=new LinkedList<>();
        list.add(new Major( LocalDateTime.now(),LocalDateTime.now(),"测试"));
//        list.add(new Major( LocalDateTime.now(),LocalDateTime.now(),"软工类"));
//        list.add(new Major( LocalDateTime.now(),LocalDateTime.now(),"计科"));
//        list.add(new Major( LocalDateTime.now(),LocalDateTime.now(),"信安"));
//        list.add(new Major( LocalDateTime.now(),LocalDateTime.now(),"大数据"));
//        list.add(new Major( LocalDateTime.now(),LocalDateTime.now(),"物联网"));
//        list.add(new Major( LocalDateTime.now(),LocalDateTime.now(),"软工"));
//        list.add(new Major( LocalDateTime.now(),LocalDateTime.now(),"通信"));
        return list;
    }


//    public static void main(String[] args) {
//        insertDmtr();
//    }

    @Test
    void insertBuilding(){
        System.out.println("hello");

        List<Building> list= initBuilding();
//        List<Dmtr> list=new LinkedList<>();
        for(Building building :list){
            buildingMapper.insert(building);
        }
    }

    public static List<Building> initBuilding(){
        //问题核心，在单元测试里面如何使用Scanner---->配置VM，添加-Deditable.java.test.console=trueidea
        //可能导致idea打不开---->上网查找，修改该配置，将另外一个VM配置也添加上
        Scanner scanner=new Scanner(System.in);
        HashSet<String> set=new HashSet<>();
        set.add("升华28栋北");
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








    public void initDmtr(){
        Scanner scanner=new Scanner(System.in);
        while(true){
            String str=scanner.nextLine();
            if("over".equals(str)){
                break;
            }
            int i=0;
            StringBuilder majorBuilder=new StringBuilder();
            StringBuilder buildingBuilder =new StringBuilder();
            for(;i<str.length();i++){
                if(str.charAt(i)>='0'&&str.charAt(i)<='9'||str.charAt(i)=='T') break;
                majorBuilder.append(str.charAt(i));
            }
            i++;
            String major=majorBuilder.toString();
            for(;i<str.length();i++){
                if(str.charAt(i)!='*'&&str.charAt(i)!=' '&&str.charAt(i)!='\t'&&!(str.charAt(i)>='0'&&str.charAt(i)<='9')) break;
            }
            for(;i<str.length();i++){
                if(str.charAt(i)=='*'||str.charAt(i)==' '||str.charAt(i)=='\t') break;
                buildingBuilder.append(str.charAt(i));
            }
            String building=buildingBuilder.toString();
            int num=0;
            for(;i<str.length();i++){
                if(str.charAt(i)>='0'&&str.charAt(i)<='9') {
                    num*=10;
                    num+=+str.charAt(i)-'0';
                }
            }
            Integer buildingId=buildingMapper.selectId(building);
            Integer majorid=majorMapper.selectId(major);
            dmtrMapper.insert(new Dmtr(LocalDateTime.now(),LocalDateTime.now(),num,buildingId,majorid));
        }
    }

    @Test
    void insertSTudent(){
        initStudent("铁道新2舍");
    }

    private void initStudent(String building) {
        System.out.println("当前录入同学楼栋："+building);
        Scanner scanner=new Scanner(System.in);
        while(true){
            String name=scanner.nextLine();
            if("over".equals(name)){
                break;
            }
            LifeCommissioner lifeCommissioner =new LifeCommissioner(LocalDateTime.now(), LocalDateTime.now(), name,buildingMapper.selectId(building),0);
            lifeCommissonerMapper.insert(lifeCommissioner);
            System.out.println("录入成功！");
        }
    }

    @Test
    public void insertTDDmtr(){
        Scanner scanner=new Scanner(System.in);
        while(true){
            int num=scanner.nextInt();
            if(num==0) break;
            dmtrMapper.insert(new Dmtr(LocalDateTime.now(),LocalDateTime.now(),num,buildingMapper.selectId("铁道新1舍"),majorMapper.selectId("通信")));
        }
    }



//    @Test
//    public void deletes(){
//        System.out.println(lifeCommissonerMapper.selectCount());
//        Scanner scanner=new Scanner(System.in);
//        while (true){
//            String name=scanner.nextLine();
//            lifeCommissonerMapper.delete(name);
//        }
//    }

    @Test
    public void countNoCheck(){
        HashSet<Integer> set=initSet();
        countSet(set);
    }

    public HashSet<Integer> initSet(){
        HashSet<Integer> set=new HashSet<>();
        System.out.println("统计总宿舍开始");
        Scanner scanner=new Scanner(System.in);
        int cnt=1;
        while(true){
            String str=scanner.next();
            if("over".equals(str)) break;
            String[] strs=str.split("，");
            for(String cur:strs){
                String[] dd=cur.split("-");
                if(dd.length==1) set.add(Integer.valueOf(dd[0]));
                else {
                    for (int i = Integer.valueOf(dd[0]); i <= Integer.valueOf(dd[1]); i++) {
                        set.add(i);
                    }
                }
            }
        }
        System.out.println("统计总宿舍结束");
        return set;
    }

    public void countSet(HashSet<Integer> set){
        Scanner scanner=new Scanner(System.in);
        while(true){
            String str=scanner.next();
            if("over".equals(str)) break;
            String[]list=str.split("、");
            for(String cur:list){
                set.remove(Integer.valueOf(cur));
            }
        }

        for(int cur:set) System.out.println(cur);
    }

    @Test
    public void selectStudent(){
        List<LifeCommissioner> list= lifeCommissonerMapper.selectByBuilding(buildingMapper.selectId("铁道新2舍"));
        for(LifeCommissioner lifeCommissioner :list){
            System.out.println(lifeCommissioner.getName());
        }

}


    @Test
    public void devideNum(){

        Scanner scanner=new Scanner(System.in);
        while (true){
            int a=scanner.nextInt(),b=scanner.nextInt();
            System.out.println((float) a/b);
        }
    }

    @Test
    public void ttt(){
        Scanner scanner=new Scanner(System.in);
        while (true){
            String str=scanner.nextLine();
            if("over".equals(str)) break;
            lifeCommissonerMapper.setCnt(lifeCommissonerMapper.selectByName(str).getId(),lifeCommissonerMapper.selectCount(str)+1,LocalDateTime.now());
        }
    }
}
