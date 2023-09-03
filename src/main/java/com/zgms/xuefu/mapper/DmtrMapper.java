package com.zgms.xuefu.mapper;

import com.zgms.xuefu.pojo.Dmtr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

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

}
