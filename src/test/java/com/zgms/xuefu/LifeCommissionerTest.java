package com.zgms.xuefu;

import com.alibaba.excel.EasyExcel;
import com.zgms.xuefu.easyexcel.dto.SingalWeekLifeCommissioner;
import com.zgms.xuefu.easyexcel.dto.UnWriteLifeCommissioner;
import com.zgms.xuefu.mapper.BuildingMapper;
import com.zgms.xuefu.mapper.LifeCommissionerMapper;
import com.zgms.xuefu.pojo.LifeCommissioner;
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
 * @Date：2023/9/23 - 09 - 23 - 19:46
 * @version： 1.0
 * @功能：
 */
@SpringBootTest
public class LifeCommissionerTest {

    @Autowired
//    static
    LifeCommissionerMapper lifeCommissonerMapper;

    @Autowired
    BuildingMapper buildingMapper;

    //    @Test
    public String[][][] generateStudent() {
        String[][] sh14 = generateDmtr("升华14栋");
        String[][] sh27 = generateDmtr("升华27栋");
        String[][][] sh28 = generateDmtr28("升华28栋北");
        String[][] sh29 = generateDmtr("升华29栋");
        String[][] sh39 = generateDmtr("升华39栋");
        String[][] td2 = generateDmtr("铁道2舍");
        String[][] td11 = generateDmtr("铁道11舍");
        String[][] tdnew2 = generateDmtr("铁道新2舍");
        String[][] tdnew1 = generateDmtr("铁道新1舍");
        String[][][] dmtr = new String[40][5][];
        for (int i = 0; i < 20; i++) {
            System.out.println("第" + (i + 1) + "周安排");
            System.out.println(" 升华14栋 升华28栋北 升华27栋 升华29栋 升华39栋");
            for (int j = 0; j < 5; j++) {
//                if(j==0) System.out.print("星期7");
//                else System.out.println("星期"+j);
                dmtr[i][j] = (sh14[i][j] + "\t" + sh28[i][j][0] + "、" + sh28[i][j][1] + "\t" + sh27[i][j] + "\t" + sh29[i][j] + "\t" + sh39[i][j]).split(" ");
                dmtr[i + 20][j] = (td2[i][j] + "\t" + td11[i][j] + "\t" + tdnew2[i][j] + "\t" + tdnew1[i][j]).split(" ");
                System.out.println(sh14[i][j] + "\t" + sh28[i][j][0] + "、" + sh28[i][j][1] + "\t" + sh27[i][j] + "\t" + sh29[i][j] + "\t" + sh39[i][j]);
//                System.out.println(td2[i][j]+"\t"+td11[i][j]+"\t"+tdnew2[i][j]+"\t"+tdnew1[i][j]);
            }
        }

        for (int i = 0; i < 20; i++) {
            System.out.println("第" + (i + 1) + "周安排");
            System.out.println("铁道2舍    铁道11舍   铁道新2舍   铁道新1舍");
            for (int j = 0; j < 5; j++) {
//                if(j==0) System.out.print("星期7");
//                else System.out.println("星期"+j);
                System.out.println(td2[i][j] + "\t" + td11[i][j] + "\t" + tdnew2[i][j] + "\t" + tdnew1[i][j]);
            }
        }
        return dmtr;
    }

    private String[][][] generateDmtr28(String name) {
        Queue<String> dmtrname = new ArrayDeque<>();
        List<LifeCommissioner> list = lifeCommissonerMapper.selectByBuilding(buildingMapper.selectId(name));
        for (LifeCommissioner lifeCommissioner : list) {
            dmtrname.add(lifeCommissioner.getName());
        }
        String[][][] dmtr = new String[20][5][2];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 5; j++) {
                dmtr[i][j][0] = dmtrname.poll();
                dmtrname.add(dmtr[i][j][0]);
                dmtr[i][j][1] = dmtrname.poll();
                dmtrname.add(dmtr[i][j][1]);
            }
        }
        return dmtr;
    }

    private String[][] generateDmtr(String name) {
        Queue<String> dmtrname = new ArrayDeque<>();
        List<LifeCommissioner> list = lifeCommissonerMapper.selectByBuilding(buildingMapper.selectId(name));
        for (LifeCommissioner lifeCommissioner : list) {
            dmtrname.add(lifeCommissioner.getName());
        }
        String[][] dmtr = new String[20][5];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 5; j++) {
                dmtr[i][j] = dmtrname.poll();
                dmtrname.add(dmtr[i][j]);
            }
        }
        return dmtr;
    }

    @Test
    void insertSTudent() {
        initStudent("升华28栋北");
        initStudent("升华39栋");
    }

    private void initStudent(String building) {
        System.out.println("当前录入同学楼栋：" + building);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String name = scanner.nextLine();
            if ("over".equals(name)) {
                break;
            }
            LifeCommissioner lifeCommissioner = new LifeCommissioner( name, buildingMapper.selectId(building),LocalDateTime.now(), LocalDateTime.now(), 0,2023,0,"");
            lifeCommissonerMapper.insert(lifeCommissioner);
            System.out.println("录入成功！");
        }
    }

    //    @Test
    public void insertUnWrite(List<SingalWeekLifeCommissioner> studentList) {
        for (SingalWeekLifeCommissioner studentIfo : studentList) {
            String str=studentIfo.getName();
            String[] strings=str.split("、");
            boolean flag=studentIfo.isFlag();
            for(String student:strings){
                LifeCommissioner lifeCommissioner=lifeCommissonerMapper.selectByName(student);
                if(lifeCommissioner==null) continue;
                if(flag)
                    lifeCommissonerMapper.setUnCnt(lifeCommissonerMapper.selectByName(student).getId(), lifeCommissonerMapper.selectUnCount(student) + 1, LocalDateTime.now());
                lifeCommissonerMapper.setCnt(lifeCommissioner.getId(), lifeCommissonerMapper.selectCount(student) + 1, LocalDateTime.now());
            }
            }


    }
    @Test
    public void testss(){
        String str=new String("98.25%");
        System.out.println(Double.valueOf(str.substring(0,str.length()-1)));

    }

    @Test
    public void generateUnWrite() {
        int week = HelpTest.getCurrentWeek();
        String filename = "C:\\Users\\maker\\Desktop\\文件\\日常文件\\学服2023\\查灯情况\\未查灯人员汇总\\截至到第" + week + "周未查灯人员表.xlsx";
//        ExcelWriter excelWriter=EasyExcel.write(filename,Week.class).build();
//        WriteSheet writeSheet=EasyExcel.writerSheet("人员信息").build();
//        for(List<WeekMag> cur:weeklist){
//            excelWriter.write(cur,writeSheet);
//        }
//        excelWriter.finish();

        List<LifeCommissioner> lifeCommissionerList = lifeCommissonerMapper.selectAll();
        List<UnWriteLifeCommissioner> unWriteLifeCommissionerList = new ArrayList<>();
        for (LifeCommissioner lifeCommissioner : lifeCommissionerList) {
                unWriteLifeCommissionerList.add(new UnWriteLifeCommissioner(lifeCommissioner.getName(), lifeCommissioner.getYear(),lifeCommissioner.getKlass(),lifeCommissioner.getCnt(),lifeCommissioner.getUnCnt(), String.format("%.2f",100.0*lifeCommissioner.getUnCnt() /lifeCommissioner.getCnt())+"%"));        }
        unWriteLifeCommissionerList.sort((o1, o2) -> (int) (Double.valueOf(o2.getPercent().substring(0,o2.getPercent().length()-1))- Double.valueOf(o1.getPercent().substring(0,o1.getPercent().length()-1))));
        EasyExcel.write(filename, UnWriteLifeCommissioner.class).sheet("信息").doWrite(unWriteLifeCommissionerList);

    }

    @Test
    public void test(){
        Scanner scanner=new Scanner(System.in);
        while (true){
            String str=scanner.nextLine();
            String major=new String();
            String banji=new String();
            String name =new String();
            int i=0;
            while(!(str.charAt(i)<='9'&&str.charAt(i)>='0')){
                major+=str.charAt(i);
                i++;
            }
            while((str.charAt(i)<='9'&&str.charAt(i)>='0')){
                banji+=str.charAt(i);
                i++;
            }
            while(i<str.length()&&!(str.charAt(i)<='9'&&str.charAt(i)>='0')){
                name+=str.charAt(i);
                i++;
            }
            System.out.println();
            major=major+banji.substring(2);
            lifeCommissonerMapper.insert(new LifeCommissioner(name,1,LocalDateTime.now(),LocalDateTime.now(),0,2023,0,major));
            System.out.print(major+name);
        }
    }
    @Test
    public void countLife(){
        List<LifeCommissioner> list=lifeCommissonerMapper.selectByYear(2023);
        List<Integer> list1=new ArrayList<>();
        List<Integer> list2=new ArrayList<>();

        for(LifeCommissioner lifeCommissioner:list){
            String ifo=lifeCommissioner.getKlass();
            String klass=ifo.substring(0,2);
            String num=ifo.substring(2);
            if(klass.equals("计通")){
                list1.add(Integer.valueOf(num));
            }
            else{
                list2.add(Integer.valueOf(num));
            }

        }
        list1.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        list2.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        System.out.println("计通"+list1);
        System.out.println("软工"+list2);
    }


}
