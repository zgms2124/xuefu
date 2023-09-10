package com.zgms.xuefu.mapper;

import com.zgms.xuefu.pojo.Dmtr;
import com.zgms.xuefu.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/8/5 - 08 - 05 - 11:17
 * @version： 1.0
 * @功能：
 */
@Mapper
public interface DmtrMapper {
    @Options(keyProperty = "id",useGeneratedKeys = true)
    public void insert(Dmtr dmtr);

    @Select("select * from tb_dmtr where building=#{building}")
    public List<Dmtr> selectByBuilding(int building);

    @Select("select num from tb_dmtr where building=#{building} and major=#{major}")
    public List<Integer> selectByBuildingAndMajor(@Param("major") int major, @Param("building") int building);


    @Select("select major from tb_dmtr where num=#{num} and building=#{building}")
    public List<Integer> selectByBuildingAndNum(@Param("building") int building, @Param("num") int num);
}
