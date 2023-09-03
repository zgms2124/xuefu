package com.zgms.xuefu.mapper;

import com.zgms.xuefu.pojo.Building;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/8/4 - 08 - 04 - 10:55
 * @version： 1.0
 * @功能：
 */
@Mapper
public interface BuildingMapper {

    @Options(keyProperty = "id",useGeneratedKeys = true)
    public void insert(Building building);

    public int selectId(String name);
}
