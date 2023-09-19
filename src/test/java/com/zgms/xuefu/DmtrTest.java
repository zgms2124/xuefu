package com.zgms.xuefu;

import com.zgms.xuefu.mapper.BuildingMapper;
import com.zgms.xuefu.mapper.DmtrMapper;
import com.zgms.xuefu.mapper.MajorMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        List<Integer> idList=dmtrMapper.selectIdByBuildingAndMajor(buildingMapper.selectId("升华14栋"));
        for(int cur:idList){
            dmtrMapper.addYear(cur,2021);
        }
    }
}
