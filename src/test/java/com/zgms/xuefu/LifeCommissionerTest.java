package com.zgms.xuefu;

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
 * @Date：2023/9/23 - 09 - 23 - 19:46
 * @version： 1.0
 * @功能：
 */
@SpringBootTest
public class LifeCommissionerTest {

    @Autowired
    LifeCommissonerMapper lifeCommissonerMapper;

    @Autowired
    BuildingMapper buildingMapper;

//    @Test
    public String[][][] generateStudent(){
        String[][] sh14=generateDmtr("升华14栋");
        String[][] sh27=generateDmtr("升华27栋");
        String[][][] sh28=generateDmtr28("升华28栋北");
        String[][] sh29=generateDmtr("升华29栋");
        String[][] sh39=generateDmtr("升华39栋");
        String[][] td2=generateDmtr("铁道2舍");
        String[][] td11=generateDmtr("铁道11舍");
        String[][] tdnew2=generateDmtr("铁道新2舍");
        String[][] tdnew1=generateDmtr("铁道新1舍");
        String[][][] dmtr=new String[40][5][];
        for(int i=0;i<20;i++){
            System.out.println("第"+(i+1)+"周安排");
            System.out.println(" 升华14栋 升华28栋北 升华27栋 升华29栋 升华39栋");
            for(int j=0;j<5;j++){
//                if(j==0) System.out.print("星期7");
//                else System.out.println("星期"+j);
                dmtr[i][j]=(sh14[i][j]+"\t"+sh28[i][j][0]+"、"+sh28[i][j][1]+"\t"+sh27[i][j]+"\t"+sh29[i][j]+"\t"+sh39[i][j]).split(" ");
                dmtr[i+20][j]=(td2[i][j]+"\t"+td11[i][j]+"\t"+tdnew2[i][j]+"\t"+tdnew1[i][j]).split(" ");
                System.out.println(sh14[i][j]+"\t"+sh28[i][j][0]+"、"+sh28[i][j][1]+"\t"+sh27[i][j]+"\t"+sh29[i][j]+"\t"+sh39[i][j]);
//                System.out.println(td2[i][j]+"\t"+td11[i][j]+"\t"+tdnew2[i][j]+"\t"+tdnew1[i][j]);
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
        return dmtr;
    }

    private String[][][] generateDmtr28(String name) {
        Queue<String> dmtrname=new ArrayDeque<>();
        List<LifeCommissioner> list= lifeCommissonerMapper.selectByBuilding(buildingMapper.selectId(name));
        for(LifeCommissioner lifeCommissioner :list){
            dmtrname.add(lifeCommissioner.getName());
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

    @Test
    void insertSTudent(){
        initStudent("升华28栋北");
        initStudent("升华39栋");
    }

    private void initStudent(String building) {
        System.out.println("当前录入同学楼栋："+building);
        Scanner scanner=new Scanner(System.in);
        while(true){
            String name=scanner.nextLine();
            if("over".equals(name)){
                break;
            }
            LifeCommissioner lifeCommissioner =new LifeCommissioner(LocalDateTime.now(), LocalDateTime.now(), name,buildingMapper.selectId(building),0,2023);
            lifeCommissonerMapper.insert(lifeCommissioner);
            System.out.println("录入成功！");
        }
    }
}
