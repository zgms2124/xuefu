package com.zgms.xuefu;

import com.zgms.xuefu.mapper.BuildingMapper;
import com.zgms.xuefu.pojo.Building;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.zgms.xuefu.tool.InitDmtr.initDmtr;

@SpringBootApplication
public class XuefuApplication {
    public static void main(String[] args) {
        SpringApplication.run(XuefuApplication.class, args);
        String resource = "mybatis-config.xml";
        try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
            // 创建SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 获取SqlSession
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                // 获取DmtrMapper接口的实例
                BuildingMapper buildingMapper = sqlSession.getMapper(BuildingMapper.class);

                // 创建要插入的Dmtr对象
                // 设置其他属性...
                List<Building> list=initDmtr();
                for(Building building :list){
                    buildingMapper.insert(building);
                }
                // 调用插入方法进行插入数据


                // 提交事务
                sqlSession.commit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("hello");
    }

}
