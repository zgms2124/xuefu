package com.zgms.xuefu;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zgms.xuefu.easyexcel.dto.Klass;
import com.zgms.xuefu.mapper.ClassMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/9/6 - 09 - 06 - 19:19
 * @version： 1.0
 * @功能：
 */
@SpringBootTest
public class ClassTest {

    @Autowired
    ClassMapper classMapper;

    @Test
    public void insertClass() {
        String str = new String("软件");
        int start = 2101, end = 2106;
        for (int i = start; i <= end; i++) {
            classMapper.insert(str + i);
        }
    }

    @Test
    public void collectClass(){
        for(int i=2301;i<=2306;i++){
            String fileName = "E:\\qq文件\\2124305427\\download\\软工"+i+".xlsx";
            System.out.println("当前读取的是："+fileName);
            collectClass(fileName);
//            System.out.println("计科"+i);

        }

    }

    public void collectClass(String fileName) {
        List<Klass> list = new ArrayList<>();
        String[][] classIfo = new String[15][7];
        int i = 0;
//        String fileName="E:\\qq文件\\2124305427\\download\\23级计通课表\\2301.xsxl"
        EasyExcel.read(fileName, Klass.class, new AnalysisEventListener() {
            @Override
            public void invoke(Object o, AnalysisContext analysisContext) {
                Klass klass = (Klass) o;
                list.add(klass);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println("解析完成");
            }
        }).sheet().doRead();
        for(Klass klass:list){
            classIfo[i][0] = klass.getClassNameSun();
            classIfo[i][1] = klass.getClassNameMon();
            classIfo[i][2] = klass.getClassNameTus();
            classIfo[i][3] = klass.getClassNameWes();
            classIfo[i][4] = klass.getClassNameThur();
            classIfo[i][5] = klass.getClassNameFri();
            classIfo[i][6] = klass.getClassNameSat();
            if(++i==15) break;
        }

        for(int j=0;j<5;j++){
            String part2=""+(j*2+1)+(j*2+2);
            for(int k=1;k<7;k++){
                String part1="周"+daySelect(k);
                String part3=classIfo[j*3][k];
                if(part3!=null){
                    String info=part1+part2+part3;
                    System.out.println(part1+part2+part3);
                }
            }
        }
    }

    private String daySelect(int k) {
        switch (k){
            case 1: return "一";
            case 2: return "二";
            case 3: return "三";
            case 4: return "四";
            case 5: return "五";
            case 6: return "六";
            case 7: return "日";
        }
        return null;
    }

}
