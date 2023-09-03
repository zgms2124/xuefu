package com.zgms.xuefu;

import com.zgms.xuefu.mapper.BuildingMapper;
import com.zgms.xuefu.mapper.DmtrMapper;
import com.zgms.xuefu.mapper.MajorMapper;
import com.zgms.xuefu.mapper.StudentMapper;
import com.zgms.xuefu.pojo.Building;
import com.zgms.xuefu.pojo.Dmtr;
import com.zgms.xuefu.pojo.Major;
import com.zgms.xuefu.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
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
    private StudentMapper studentMapper;

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

    @Test
    void insetrDmtr(){
//        initDmtr();
        initDmtrByNum("大数据","升华14栋");
        initDmtrByNum("计算机","升华14栋");
        initDmtrByNum("信安","升华14栋");

        initDmtrByNum("大数据","升华27栋");
        initDmtrByNum("计算机","升华27栋");
        initDmtrByNum("信安","升华27栋");

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
    public void generateStudent(){
        String[][] sh14=new String[20][5];
        String[][] sh27=new String[20][5];
        String[][][] sh28=new String[20][5][2];
        String[][] sh29=new String[20][5];
        Queue<String> sh14name=new ArrayDeque<>();
        Queue<String> sh27name=new ArrayDeque<>();
        Queue<String> sh28name=new ArrayDeque<>();
        Queue<String> sh29name=new ArrayDeque<>();
        List<Student> list14=studentMapper.selectByBuilding(buildingMapper.selectId("升华14栋"));
        List<Student> list27=studentMapper.selectByBuilding(buildingMapper.selectId("升华27栋"));
        List<Student> list28=studentMapper.selectByBuilding(buildingMapper.selectId("升华28栋北"));
        List<Student> list29=studentMapper.selectByBuilding(buildingMapper.selectId("升华29栋"));
        for(Student student:list14){
            sh14name.add(student.getName());
        }
        for(Student student:list27){
            sh27name.add(student.getName());
        }
        for(Student student:list28){
            sh28name.add(student.getName());
        }
        for(Student student:list29){
            sh29name.add(student.getName());
        }
        for(int i=0;i<20;i++){
            System.out.println("第"+(i+1)+"周安排");
            System.out.println(" 升华14栋 28栋北 升华27栋 升华29栋");
            for(int j=0;j<5;j++){
//                if(j==0) System.out.print("星期7");
//                else System.out.println("星期"+j);
                sh14[i][j]=sh14name.poll();
                sh14name.add(sh14[i][j]);

                sh27[i][j]=sh27name.poll();
                sh27name.add(sh27[i][j]);

                sh28[i][j][0]=sh28name.poll();
                sh28name.add(sh28[i][j][0]);

                sh28[i][j][1]=sh28name.poll();
                sh28name.add(sh28[i][j][1]);

                sh29[i][j]=sh29name.poll();
                sh29name.add(sh29[i][j]);


                System.out.println(sh14[i][j]+"\t"+sh28[i][j][0]+"、"+sh28[i][j][1]+"\t"+sh27[i][j]+"\t"+sh29[i][j]);
            }
        }
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
        initStudent("升华14栋");
    }

    private void initStudent(String building) {
        System.out.println("当前录入同学楼栋："+building);
        Scanner scanner=new Scanner(System.in);
        while(true){
            String name=scanner.nextLine();
            if("over".equals(name)){
                break;
            }
            Student student=new Student(LocalDateTime.now(), LocalDateTime.now(), name,buildingMapper.selectId(building) );
            studentMapper.insert(student);
            System.out.println("录入成功！");
        }
    }


}
