package com.zgms.xuefu;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zgms.xuefu.easyexcel.consts.Consts;
import com.zgms.xuefu.easyexcel.dto.DmtrIfo;
import com.zgms.xuefu.easyexcel.dto.FinalFileIfoNEW;
import com.zgms.xuefu.easyexcel.dto.UnDownLight;
import com.zgms.xuefu.mapper.BuildingMapper;
import com.zgms.xuefu.mapper.DmtrMapper;
import com.zgms.xuefu.mapper.LifeCommissonerMapper;
import com.zgms.xuefu.mapper.MajorMapper;
import com.zgms.xuefu.pojo.Dmtr;
import com.zgms.xuefu.pojo.LifeCommissioner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private LifeCommissonerMapper lifeCommissonerMapper;

    @Test
    public void addYear() {
        List<Integer> idList = dmtrMapper.selectIdByBuildingAndMajor(buildingMapper.selectId("铁道新2舍"));
        for (int cur : idList) {
            dmtrMapper.addYear(cur, 2021);
        }
    }

    @Test
    void insetrDmtr() {
//        initDmtr();
//        initDmtrByNum("大数据","升华14栋");
//        initDmtrByNum("计算机","升华14栋");
//        initDmtrByNum("信安","升华14栋");
//
//        initDmtrByNum("大数据","升华27栋");
        initDmtrByNum("计算机", "升华27栋");
        initDmtrByNum("信安", "升华27栋");

        initDmtrByNum("通信", "铁道11舍");
        initDmtrByNum("软工", "铁道11舍");
        initDmtrByNum("通信", "铁道新2舍");
        initDmtrByNum("软工", "铁道新2舍");


//        initDmtrByNum("计通","升华28栋北");
//        initDmtrByNum("软工类","升华28栋北");
//        initDmtrByNum("计通","升华39栋");
//        initDmtrByNum("软工类","升华39栋");

    }

    private void initDmtrByNum(String major, String building) {
        System.out.println("现在导入的是" + building + major);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int begin = scanner.nextInt();
            int over = scanner.nextInt();
            if (begin == 0 && over == 0) break;
            for (int i = begin; i <= over; i++) {
                dmtrMapper.insert(new Dmtr(LocalDateTime.now(), LocalDateTime.now(),
                        i, buildingMapper.selectId(building), majorMapper.selectId(major)));
            }
        }
    }


    public String getDay(int i) {
        if (i == 0) return "周日";
        if (i == 1) return "周一";
        if (i == 2) return "周二";
        if (i == 3) return "周三";
        return "周四";

    }


    @Test
    public void selectDmtr() {
        selectDmtrSingal("升华14栋");
        selectDmtrSingal("升华28栋北");
        selectDmtrSingal("升华27栋");
        selectDmtrSingal("升华29栋");
        selectDmtrSingal("升华39栋");
        selectDmtrSingal("铁道2舍");
        selectDmtrSingal("铁道11舍");
        selectDmtrSingal("铁道新2舍");
        selectDmtrSingal("铁道新1舍");
    }

    @Test
    public void selectDmtrSingal(String str) {
        List<Dmtr> sh14 = dmtrMapper.selectByBuilding(buildingMapper.selectId(str));
        HashSet<Integer> set14 = new HashSet<>();
        for (Dmtr dmtr : sh14) {
            set14.add(dmtr.getNum());
        }
        int[] arr = new int[set14.size()];
        int r = 0;
        for (int num : set14) {
            arr[r++] = num;
        }
        Arrays.sort(arr);
        boolean flag1 = false;
        System.out.println(str);
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) System.out.print(arr[i]);
            else {
                if (arr[i] != arr[i - 1] + 1) {
                    if (flag1)
                        System.out.print("-" + arr[i - 1] + "，" + arr[i]);
                    else System.out.print("，" + arr[i]);
                    flag1 = false;
                } else {
                    flag1 = true;
                }
            }
        }
        if (flag1) {
            System.out.println("-" + arr[arr.length - 1]);
        }
        System.out.println();
    }

    public ArrayList countDmtr(ArrayList<String> dmtrList) {

//        HashSet<Integer> set=new HashSet<>();
//        List<Integer> list1= dmtrMapper.selectByBuildingAndMajor(majorMapper.selectId("软工"), buildingMapper.selectId("铁道新1舍"));
//        List<Integer> list2= dmtrMapper.selectByBuildingAndMajor(majorMapper.selectId("软工"), buildingMapper.selectId("铁道2舍"));
//        for(int cur:list1) set.add(cur);
//        for(int cur:list2) set.add(cur);
//        System.out.println(set.size());
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(countDmtrSigal("升华14栋", dmtrList, 1));
        arrayList.addAll(countDmtrSigal("升华28栋北", dmtrList, 7));
        arrayList.addAll(countDmtrSigal("升华27栋", dmtrList, 13));
        arrayList.addAll(countDmtrSigal("升华29栋", dmtrList, 19));
        arrayList.addAll(countDmtrSigal("升华39栋", dmtrList, 25));
        arrayList.addAll(countDmtrSigal("铁道2舍", dmtrList, 31));
        arrayList.addAll(countDmtrSigal("铁道11舍", dmtrList, 37));
        arrayList.addAll(countDmtrSigal("铁道新2舍", dmtrList, 43));
        arrayList.addAll(countDmtrSigal("铁道新1舍", dmtrList, 49));
//        List<Integer> list3= dmtrMapper.selectByBuildingAndMajor(majorMapper.selectId("物联网"), buildingMapper.selectId("升华14栋"));
//        for(int cur:list3) set.add(cur);
//        System.out.println(set.size());
        return arrayList;
    }

    public ArrayList<String> countDmtrSigal(String building, ArrayList<String> arrayList, int begin) {
//        System.out.println("查询"+dmtr);
//        System.out.println("输入“结束”,退出该宿舍的查询");
//        Scanner scanner=new Scanner(System.in);
        ArrayList<String> ifo = new ArrayList<>();
        for (int r = 0; r < 5; r++) {
            String str = arrayList.get(begin + r);
            if (str == null || "无".equals(str)) continue;
            if ("结束".equals(str)) break;
            List<Integer> listNum = new LinkedList<>();
            int num = 0;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c <= '9' && c >= '0') {
                    num = num * 10 + (c - '0');
                } else {
                    if (num != 0)
                        listNum.add(num);
                    num = 0;
                }
            }
            if (num != 0)
                listNum.add(num);
            HashMap<String, List<Integer>> map = new HashMap<>();
            for (int cur : listNum) {
                List<Integer> list = dmtrMapper.selectByBuildingAndNum(buildingMapper.selectId(building), cur);
                if (list != null && list.size() > 0) {
                    String major = majorMapper.selectName(list.get(0));
                    if (!map.containsKey(major)) {
                        map.put(major, new LinkedList<>());
                    }
                    map.get(major).add(cur);
                }
            }
            for (Map.Entry<String, List<Integer>> curMap : map.entrySet()) {
                String major = curMap.getKey();
                List<Integer> numList = curMap.getValue();

                StringBuilder stringBuilder=new StringBuilder(major+"，"+r+"，"+building+"，");
                for(int cur:numList){
                    stringBuilder.append(cur+"、");
                }
                ifo.add(stringBuilder.toString());
//                System.out.print(numList.get(0));
//                numList.remove(0);
//                for(int cur:numList){
//                    System.out.print("、"+cur);
//                }
//                System.out.println();
            }

//            System.out.println("是否继续输入？");
        }
        return ifo;
    }

    @Test
    public void generateFinalExcel() {
        int week = HelpTest.getCurrentWeek();
        week=4;
        ArrayList<DmtrIfo> unDownLightList = new ArrayList<>();
        String fileName1 = "C:\\Users\\maker\\Desktop\\文件\\日常文件\\学服2023\\查灯情况\\反馈表\\第"+week+"周查灯情况汇总表.xlsx";
        String fileName2 = "C:\\Users\\maker\\Desktop\\文件\\日常文件\\学服2023\\查灯情况\\汇总表\\第"+week+"周查灯情况.xlsx";
        EasyExcel.read(fileName1, DmtrIfo.class, new AnalysisEventListener() {
            @Override
            public void invoke(Object o, AnalysisContext analysisContext) {
                System.out.println(o);
                unDownLightList.add((DmtrIfo) o);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println("解析完成");
            }
        }).sheet().doRead();

//        List<FinalFileIfo> list2=new LinkedList<>();
//        list2.add(new FinalFileIfo("9.17","21级","15","15","100%","无","宋晓萌","黎祥、杨俊芬"));
//        list2.add(new FinalFileIfo("9.17","22级","48","48","100%","无","何星霖","黎祥、杨俊芬"));
        List<FinalFileIfoNEW> list3 = new LinkedList<>();
//        list3.add(new FinalFileIfoNEW("信安","9.17","21级","15","15","100%","无","宋晓萌","黎祥、杨俊芬"));
//        list3.add(new FinalFileIfoNEW("信安","9.17","22级","48","48","100%","无","何星霖","黎祥、杨俊芬"));
//        ExcelWriter excelWriter1=EasyExcel.write(fileName2,FinalFileIfo.class).build();

        ExcelWriter excelWriter2 = EasyExcel.write(fileName2, FinalFileIfoNEW.class).build();
//        WriteSheet writeSheet=EasyExcel.writerSheet("人员信息").build();
//        excelWriter1.write(list2,writeSheet);
//        excelWriter2.write(list3,writeSheet);
//        excelWriter1.finish();
//
//        excelWriter2.finish();
//        EasyExcel.write(fileName2, FinalFileIfo.class).sheet("信息").doWrihete(list2);

//        for(DmtrIfo dmtrIfo:unDownLightList){
//            System.out.println(dmtrIfo.getIfo());
//        }
        ArrayList<String> unDownLightStrList = new ArrayList<>();
        int r=0;
        for (DmtrIfo dmtrIfo : unDownLightList) {
            unDownLightStrList.add(dmtrIfo.getIfo());
        }
        ArrayList<String> dmtrIfo = countDmtr(unDownLightStrList);

        LinkedList<UnDownLight>[][] unLight信安=getUndownLight("信安",dmtrIfo);
        LinkedList<UnDownLight>[][] unLight大数据=getUndownLight("大数据",dmtrIfo);
        LinkedList<UnDownLight>[][] unLight计算机=getUndownLight("计算机",dmtrIfo);
        LinkedList<UnDownLight>[][] unLight23=getUndownLight("23",dmtrIfo);
        LinkedList<UnDownLight>[][] unLight通信=getUndownLight("通信",dmtrIfo);
        LinkedList<UnDownLight>[][] unLight软工=getUndownLight("软工",dmtrIfo);
//
        List<String> lifeCommissionerList = Person.SH(generateStudent()[week]);
        lifeCommissionerList.addAll(Person.TD(generateStudent()[20 + week]));


        System.out.println(lifeCommissionerList.size());
        writeFinalList("信安", list3, lifeCommissionerList,unLight信安);
        writeFinalList("大数据", list3, lifeCommissionerList,unLight大数据);
        writeFinalList("计算机", list3, lifeCommissionerList,unLight计算机);
        writeFinalList23(list3, lifeCommissionerList,unLight23);
        writeFinalList("通信", list3, lifeCommissionerList,unLight通信);
        writeFinalList("软工", list3, lifeCommissionerList,unLight软工);

        for (String str : lifeCommissionerList) {
            System.out.println(str);
        }
        EasyExcel.write(fileName2, FinalFileIfoNEW.class).sheet("信息").doWrite(list3);

        System.out.println(dmtrIfo);
        for(int i=0;i<5;i++){
            for(int j=0;j<2;j++){
                System.out.println(unLight信安[i][j]);
            }
        }
    }

    public LinkedList<UnDownLight>[][] getUndownLight(String major,ArrayList<String> arrayList){
        LinkedList<UnDownLight>[][] unDownLights=new LinkedList[5][2];
        for(String str:arrayList){
            String[] ifo=str.split("，");
            if(ifo[0].equals(major)){
                int day=Integer.valueOf(ifo[1]);
                int year=buildingMapper.selectYearByBuilding(ifo[2])-2021;

                UnDownLight dmtr=new UnDownLight(ifo[2],new LinkedList<>());
                String []dmtrs=ifo[3].split("、");
                for(int i=0;i<dmtrs.length;i++){
                    dmtr.getDmtrList().add(Integer.valueOf(dmtrs[i]));
                }
                if(unDownLights[day][year]==null){
                    unDownLights[day][year]=new LinkedList<>();
                }
                unDownLights[day][year].add(dmtr);
            }
            else if("23".equals(major)){
                int day=Integer.valueOf(ifo[1]);
                String []dmtrs=ifo[3].split("、");

                UnDownLight dmtr=new UnDownLight(ifo[2],new LinkedList<>());
                for(int i=0;i<dmtrs.length;i++){
                    dmtr.getDmtrList().add(Integer.valueOf(dmtrs[i]));
                }
                if("软工类".equals(ifo[0])){
                    if(unDownLights[day][0]==null){
                        unDownLights[day][0]=new LinkedList<>();
                    }
                    unDownLights[day][0].add(dmtr);
                }
                else if("计通".equals(ifo[0])){
                    if(unDownLights[day][1]==null){
                        unDownLights[day][1]=new LinkedList<>();
                    }
                    unDownLights[day][1].add(dmtr);
                }
            }
        }
        return unDownLights;
    }

    public String[][][] generateStudent() {
        String[][] sh14 = generateDmtr("升华14栋", false);
        String[][] sh27 = generateDmtr("升华27栋", false);
        String[][] sh28 = generateDmtr("升华28栋北", true);
        String[][] sh29 = generateDmtr("升华29栋", false);
        String[][] sh39 = generateDmtr("升华39栋", false);
        String[][] td2 = generateDmtr("铁道2舍", false);
        String[][] td11 = generateDmtr("铁道11舍", false);
        String[][] tdnew2 = generateDmtr("铁道新2舍", false);
        String[][] tdnew1 = generateDmtr("铁道新1舍", false);
        String[][][] dmtr = new String[40][5][];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 5; j++) {
                dmtr[i][j] = (sh14[i][j] + "\t" + sh28[i][j] + "\t" + sh27[i][j] + "\t" + sh29[i][j] + "\t" + sh39[i][j]).split("\t");
                dmtr[i + 20][j] = (td2[i][j] + "\t" + td11[i][j] + "\t" + tdnew2[i][j] + "\t" + tdnew1[i][j]).split("\t");
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(dmtr[3][i][j]);
            }
        }
        return dmtr;
    }

    private String[][] generateDmtr(String name, boolean flag) {
        Queue<String> dmtrname = new ArrayDeque<>();
        List<LifeCommissioner> list = lifeCommissonerMapper.selectByBuilding(buildingMapper.selectId(name));
        for (LifeCommissioner lifeCommissioner : list) {
            dmtrname.add(lifeCommissioner.getName());
        }
        String[][] dmtr = new String[20][5];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 5; j++) {
                String str1 = dmtrname.poll();
                dmtrname.add(str1);

                if (flag) {
                    String str2 = dmtrname.poll();
                    dmtrname.add(str2);
                    dmtr[i][j] = str1 + "、" + str2;
                } else {
                    dmtr[i][j] = str1;
                }
            }
        }
        return dmtr;
    }

    public void writeFinalList(String major, List<FinalFileIfoNEW> fileList, List<String> lifeCommissionerList, LinkedList<UnDownLight>[][] undownLight) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                String majorAndSection = major + "2" + (j+1);
                String section=2021+j+"级";
                List<String> dayList = getNowWeek();
                List<Integer> dmtrList = dmtrMapper.countByMajorAndYear(majorMapper.selectId(major), 2020 + j+1);
                HashSet<Integer> dmtrSet = new HashSet<>(dmtrList);
                int dmtrCount = dmtrSet.size();
                String teacher = (String) Consts.getConstantValue(majorAndSection);

                int unLightCount=0;
                String undownLightStr=new String();
                if(undownLight[i][j]==null){
                    undownLightStr="无";
                }
                else {
                    for(UnDownLight un:undownLight[i][j]){
                        unLightCount+=un.getDmtrList().size();
                        undownLightStr=undownLightStr+un.getBuilding();
                        for(int dmtrNum:un.getDmtrList()){
                            undownLightStr+=dmtrNum+"、";
                        }
                        undownLightStr=undownLightStr.substring(0,undownLightStr.length()-1);
                        undownLightStr+="，";
                    }
                    undownLightStr=undownLightStr.substring(0,undownLightStr.length()-1);
                }

                fileList.add(new FinalFileIfoNEW(major, dayList.get(i), section, String.valueOf(dmtrCount), String.valueOf(dmtrCount-unLightCount), devideNum(dmtrCount-unLightCount, dmtrCount), undownLightStr, teacher
                        , lifeCommissionerList.get(0)));
                if (!lifeCommissionerList.isEmpty()) {
                    lifeCommissionerList.remove(0);
                }

            }
        }
    }

    public void writeFinalList23(List<FinalFileIfoNEW> fileList, List<String> lifeCommissionerList, LinkedList<UnDownLight>[][] undownLight) {

        for (int i = 0; i < 5; i++) {
            List<String> dayList = getNowWeek();
            List<Integer> dmtrList1 = dmtrMapper.countByMajorAndYear(majorMapper.selectId("软工类"), 2023);
            HashSet<Integer> dmtrSet1 = new HashSet<>(dmtrList1);
            int dmtrCount = dmtrSet1.size();
            String teacher = (String) Consts.getConstantValue("软工类23");
            int unLightCount=0;
            String undownLightStr=new String();
            if(undownLight[i][0]==null){
                undownLightStr="无";
            }
            else {
                for(UnDownLight un:undownLight[i][0]){
                    unLightCount+=un.getDmtrList().size();
                    undownLightStr=undownLightStr+un.getBuilding();
                    for(int dmtrNum:un.getDmtrList()){
                        undownLightStr+=dmtrNum+"、";
                    }
                    undownLightStr=undownLightStr.substring(0,undownLightStr.length()-1);
                    undownLightStr+="，";
                }
                undownLightStr=undownLightStr.substring(0,undownLightStr.length()-1);
            }

            fileList.add(new FinalFileIfoNEW("23级", dayList.get(i), "软件工程", String.valueOf(dmtrCount), String.valueOf(dmtrCount - unLightCount), devideNum(dmtrCount - unLightCount, dmtrCount), undownLightStr, teacher
                    , lifeCommissionerList.get(0)));
            lifeCommissionerList.remove(0);



            List<Integer> dmtrList2 = dmtrMapper.countByMajorAndYear(majorMapper.selectId("计通"), 2023);
            HashSet<Integer> dmtrSet2 = new HashSet<>(dmtrList2);
            dmtrCount = dmtrSet2.size();
            teacher = (String) Consts.getConstantValue("计通23");

            unLightCount=0;
            undownLightStr=new String();
            if(undownLight[i][1]==null){
                undownLightStr="无";
            }
            else {
                for(UnDownLight un:undownLight[i][1]){
                    unLightCount+=un.getDmtrList().size();
                    undownLightStr=undownLightStr+un.getBuilding();
                    for(int dmtrNum:un.getDmtrList()){
                        undownLightStr+=dmtrNum+"、";
                    }
                    undownLightStr=undownLightStr.substring(0,undownLightStr.length()-1);
                    undownLightStr+="，";
                }
                undownLightStr=undownLightStr.substring(0,undownLightStr.length()-1);
            }
            fileList.add(new FinalFileIfoNEW("23级", dayList.get(i), "计通", String.valueOf(dmtrCount), String.valueOf(dmtrCount - unLightCount), devideNum(dmtrCount - unLightCount, dmtrCount), undownLightStr.toString(), teacher
                    , lifeCommissionerList.get(0)));
            lifeCommissionerList.remove(0);

        }
    }

    public List<String> getNowWeek() {
        List<String> list = new LinkedList<>();
        LocalDate currentDate = LocalDate.now();
        LocalDate lastSunday = currentDate.with(DayOfWeek.SUNDAY).minusWeeks(1);
        String lastSundayFormatted = lastSunday.format(DateTimeFormatter.ofPattern("M.d"));
        list.add(lastSundayFormatted);
        LocalDate monday = lastSunday.plusDays(1);
        for (int i = 0; i < 4; i++) {
            String formattedDate = monday.plusDays(i).format(DateTimeFormatter.ofPattern("M.d"));
            list.add(formattedDate);
        }
        return list;
    }

    public String devideNum(int a, int b) {
        return String.format("%.1f", ((float) a / b) * 100) + "%";
    }

}
