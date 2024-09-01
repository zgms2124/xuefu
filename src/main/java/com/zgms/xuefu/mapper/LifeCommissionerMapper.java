package com.zgms.xuefu.mapper;

import com.zgms.xuefu.pojo.LifeCommissioner;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 学习JAVA
 *
 * @项目名称：
 * @子庚木上
 * @Date：2023/10/27 - 10 - 27 - 10:53
 * @version： 1.0
 * @功能：
 */
@Mapper
public interface LifeCommissionerMapper {
    @Options(keyProperty = "id",useGeneratedKeys = true)
    @Select("insert ignore into tb_life_commissioner(name,building,create_time, update_time, cnt,year,un_cnt,klass)\n" +
                    "        VALUES (#{name},#{building},#{createTime}, #{updateTime}, #{cnt},#{year},#{unCnt},#{klass})")
    public void insert(LifeCommissioner lifeCommissioner);

    @Select("select * from tb_life_commissioner")
    public List<LifeCommissioner> selectAll();

    @Select("select cnt from tb_life_commissioner where name=#{name}")
    public int selectCount(String name);

    @Select("select un_cnt from tb_life_commissioner where name=#{name}")
    public int selectUnCount(String name);

    @Select("select un_write_date from tb_life_commissioner where name=#{name}")
    public String selectUnWriteDate(String name);

    @Select("select *from tb_life_commissioner where building=#{building}")
    public List<LifeCommissioner> selectByBuilding(int building);

    @Select("select * from tb_life_commissioner where name=#{name}")
    public LifeCommissioner selectByName(String name);

    @Select("select * from tb_life_commissioner where year=#{year}")
    public List<LifeCommissioner> selectByYear(int year);

    @Delete("delete from tb_life_commissioner where name=#{name}")
    public void delete(String name);

    @Update("update tb_life_commissioner set un_cnt=#{unCnt},update_time=#{updatetime} where id=#{id}")
    public void setUnCnt(@Param("id") int id,@Param("unCnt") int unCnt,@Param("updatetime") LocalDateTime updatetime);

    @Update("update tb_life_commissioner set un_write_date=#{unWriteDate},update_time=#{updatetime} where id=#{id}")
    public void setUnWriteDate(@Param("id") int id,@Param("unWriteDate") String unWriteDate,@Param("updatetime") LocalDateTime updatetime);


    @Update("update tb_life_commissioner set cnt=#{cnt},update_time=#{updatetime} where id=#{id}")
    public void setCnt(@Param("id") int id,@Param("cnt") int cnt,@Param("updatetime") LocalDateTime updatetime);

}
