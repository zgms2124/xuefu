package com.zgms.xuefu;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.zgms.xuefu.easyexcel.dto.Week;
import com.zgms.xuefu.easyexcel.dto.WeekMag;
import com.zgms.xuefu.mapper.BuildingMapper;
import com.zgms.xuefu.mapper.LifeCommissonerMapper;
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
 * @Date：2023/9/10 - 09 - 10 - 23:25
 * @version： 1.0
 * @功能：
 */
@SpringBootTest
public class WeekMagTest {

    @Autowired
    LifeCommissonerMapper lifeCommissonerMapper;

    @Autowired
    BuildingMapper buildingMapper;

    @Test
    public void initSetCnt(){
        List<LifeCommissioner> list= lifeCommissonerMapper.selectAll();
        for(LifeCommissioner lifeCommissioner :list){
            lifeCommissonerMapper.setCnt(lifeCommissioner.getId(),0,LocalDateTime.now());
        }
    }

    @Test
    public void addCnt(){
        Scanner scanner=new Scanner(System.in);
        while (true){
            String name=scanner.nextLine();
            LifeCommissioner lifeCommissioner = lifeCommissonerMapper.selectByName(name);
            lifeCommissonerMapper.setCnt(lifeCommissioner.getId(), lifeCommissioner.getCnt()+1, LocalDateTime.now());
        }
    }

    @Test
    public void generateWeekArrangement() {
        for (int i=0;i<20;i++){
            generateSinaglWeek(i);
        }

    }
    private void generateSinaglWeek(int week){
        String[][] sh14=generateDmtr("升华14栋");
        String[][] sh27=generateDmtr("升华27栋");
        String[][] sh28b=generateDmtr28("升华28栋北");
        String[][] sh29=generateDmtr("升华29栋");
        String[][] td2=generateDmtr("铁道2舍");
        String[][] td11=generateDmtr("铁道11舍");
        String[][] tdnew2=generateDmtr("铁道新2舍");
        String[][] tdnew1=generateDmtr("铁道新1舍");

        List<List<WeekMag>> weeklist=new LinkedList<>();
        weeklist.add(generateSingal("升华14栋",sh14,"宿舍信息：542-549，601-638，640-649，701-727",week));
        weeklist.add(generateSingal("升华28栋北",sh28b,"宿舍信息：102-136，201-208，221-234，326-328，334-337，701-718，720-725",week));
        weeklist.add(generateSingal("升华27栋",sh27,"宿舍信息：410，412-419，421-437",week));
        weeklist.add(generateSingal("升华29栋",sh29,"宿舍信息：108，112-117，201-213，506-510",week));
        weeklist.add(generateSingal("铁道2舍",td2,"宿舍信息：110、114、126、130、319、326、328、329、330、333、401-410，413-415，419，421-433，511-529",week));
        weeklist.add(generateSingal("铁道十一舍",td11,"宿舍信息：201-206，401-410，412，413，501-510，512-519，601-610，612-619",week));
        weeklist.add(generateSingal("铁道新二舍",tdnew2,"宿舍信息：101-108，124-128，224",week));
        weeklist.add(generateSingal("铁道新一舍",tdnew1,"宿舍信息：203-214",week));


        String filename="C:\\Users\\maker\\Desktop\\文件\\日常文件\\学服2023\\查灯安排\\第"+(week+1)+"周查灯安排表.xlsx";
//        ExcelWriter excelWriter=EasyExcel.write(filename,Week.class).build();
//        WriteSheet writeSheet=EasyExcel.writerSheet("人员信息").build();
//        for(List<WeekMag> cur:weeklist){
//            excelWriter.write(cur,writeSheet);
//        }
//        excelWriter.finish();
        LinkedList<WeekMag> list=new LinkedList<>();
        for(int i=0;i<8;i++){
            list.addAll(weeklist.get(i));
        }
        EasyExcel.write(filename, WeekMag.class).sheet("信息").doWrite(list);

    }

    private String[][] generateDmtr28(String name) {
        Queue<String> dmtrname=new ArrayDeque<>();
        List<LifeCommissioner> list= lifeCommissonerMapper.selectByBuilding(buildingMapper.selectId(name));
        for(LifeCommissioner lifeCommissioner :list){
            dmtrname.add(lifeCommissioner.getName());
        }
        String[][] dmtr=new String[20][5];
        for(int i=0;i<20;i++){
            for(int j=0;j<5;j++){
                String str1=dmtrname.poll();
                dmtrname.add(str1);

                String str2=dmtrname.poll();
                dmtrname.add(str2);
                dmtr[i][j]=str1+"、"+str2;
            }
        }
        return dmtr;
    }

    private String[][] generateDmtr(String name) {
        Queue<String> dmtrname=new ArrayDeque<>();
        List<LifeCommissioner> list= lifeCommissonerMapper.selectByBuilding(buildingMapper.selectId(name));
        for(LifeCommissioner lifeCommissioner :list){
            dmtrname.add(lifeCommissioner.getName());
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

    private List<WeekMag> generateSingal(String building, String[][] lifeCms, String ifo,int week) {
        List<WeekMag> weekMags=new LinkedList<>();
        weekMags.add(new WeekMag(" "," "," "));
        weekMags.add(new WeekMag(" "," "," "));
        weekMags.add(new WeekMag(" ",building,ifo));
        weekMags.add(new WeekMag("周日",lifeCms[week][0]," "));
        weekMags.add(new WeekMag("周一",lifeCms[week][1]," "));
        weekMags.add(new WeekMag("周二",lifeCms[week][2]," "));
        weekMags.add(new WeekMag("周三",lifeCms[week][3]," "));
        weekMags.add(new WeekMag("周四",lifeCms[week][4]," "));
        return weekMags;
    }

}
