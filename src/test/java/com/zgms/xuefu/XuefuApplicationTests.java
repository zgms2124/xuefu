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
        String[][] sh14=generateDmtr("升华14栋");
        String[][] sh27=generateDmtr("升华27栋");
        String[][][] sh28=generateDmtr28("升华28栋北");
        String[][] sh29=generateDmtr("升华29栋");
        String[][] td2=generateDmtr("铁道2舍");
        String[][] td11=generateDmtr("铁道11舍");
        String[][] tdnew2=generateDmtr("铁道新2舍");
        String[][] tdnew1=generateDmtr("铁道新1舍");
        for(int i=0;i<20;i++){
            System.out.println("第"+(i+1)+"周安排");
            System.out.println(" 升华14栋 28栋北 升华27栋 升华29栋");
            for(int j=0;j<5;j++){
//                if(j==0) System.out.print("星期7");
//                else System.out.println("星期"+j);
                System.out.println(sh14[i][j]+"\t"+sh28[i][j][0]+"、"+sh28[i][j][1]+"\t"+sh27[i][j]+"\t"+sh29[i][j]);
            }
        }

        for(int i=0;i<20;i++){
            System.out.println("第"+(i+1)+"周安排");
            System.out.println("铁道2舍    铁道11舍   铁道新2舍   铁道新1舍");
            for(int j=0;j<5;j++){
//                if(j==0) System.out.print("星期7");
//                else System.out.println("星期"+j);
                System.out.println(td2[i][j]+"\t"+td11[i][j]+"\t"+tdnew2[i][j]+"\t"+tdnew1[i][j]);
            }
        }
    }

    private String[][][] generateDmtr28(String name) {
        Queue<String> dmtrname=new ArrayDeque<>();
        List<Student> list=studentMapper.selectByBuilding(buildingMapper.selectId(name));
        for(Student student:list){
            dmtrname.add(student.getName());
        }
        String[][][] dmtr=new String[20][5][2];
        for(int i=0;i<20;i++){
            for(int j=0;j<5;j++){
                dmtr[i][j][0]=dmtrname.poll();
                dmtrname.add(dmtr[i][j][0]);
                dmtr[i][j][1]=dmtrname.poll();
                dmtrname.add(dmtr[i][j][1]);
            }
        }
        return dmtr;
    }

    private String[][] generateDmtr(String name) {
        Queue<String> dmtrname=new ArrayDeque<>();
        List<Student> list=studentMapper.selectByBuilding(buildingMapper.selectId(name));
        for(Student student:list){
            dmtrname.add(student.getName());
        }
        String[][] dmtr=new String[20][5];
        for(int i=0;i<20;i++){
            for(int j=0;j<5;j++){
                dmtr[i][j]=dmtrname.poll();
                dmtrname.add(dmtr[i][j]);
            }
        }
        return dmtr;
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
            Student student=new Student(LocalDateTime.now(), LocalDateTime.now(), name,buildingMapper.selectId(building),0);
            studentMapper.insert(student);
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

    @Test
    public void selectDmtr(){
        List<Dmtr> sh14=dmtrMapper.selectByBuilding(buildingMapper.selectId("升华28栋北"));
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
        for(int num:arr){
            System.out.print(num+"、");
        }
    }

    @Test
    public void deletes(){
        System.out.println(studentMapper.selectCount());
        Scanner scanner=new Scanner(System.in);
        while (true){
            String name=scanner.nextLine();
            studentMapper.delete(name);
        }
    }

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
        List<Student> list=studentMapper.selectByBuilding(buildingMapper.selectId("铁道新2舍"));
        for(Student student:list){
            System.out.println(student.getName());
        }

}
    @Test
    public void countDmtr(){

//        HashSet<Integer> set=new HashSet<>();
//        List<Integer> list1= dmtrMapper.selectByBuildingAndMajor(majorMapper.selectId("软工"), buildingMapper.selectId("铁道新1舍"));
//        List<Integer> list2= dmtrMapper.selectByBuildingAndMajor(majorMapper.selectId("软工"), buildingMapper.selectId("铁道2舍"));
//        for(int cur:list1) set.add(cur);
//        for(int cur:list2) set.add(cur);
//        System.out.println(set.size());

//        countDmtrSigal("升华14栋");
//        countDmtrSigal("升华28栋北");
//        countDmtrSigal("升华27栋");
//        countDmtrSigal("升华29栋");
        countDmtrSigal("铁道2舍");
//        countDmtrSigal("铁道11舍");
//        countDmtrSigal("铁道新2舍");
//        countDmtrSigal("铁道新1舍");
//        List<Integer> list3= dmtrMapper.selectByBuildingAndMajor(majorMapper.selectId("物联网"), buildingMapper.selectId("升华14栋"));
//        for(int cur:list3) set.add(cur);
//        System.out.println(set.size());
    }

    public void countDmtrSigal(String dmtr){
        System.out.println("记录"+dmtr);
            Scanner scanner=new Scanner(System.in);
            String str=scanner.nextLine();
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

        System.out.println("记录结束");
    }

    @Test
    public void devideNum(){

        Scanner scanner=new Scanner(System.in);
        while (true){
            int a=scanner.nextInt(),b=scanner.nextInt();
            System.out.println((float) a/b);
        }
    }
}
