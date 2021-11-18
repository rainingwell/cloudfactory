package com.dao.factory;

import com.pojo.Factory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactoryMapper {
    //获取设备列表
    List<Factory> getFactoryList(@Param("currentPageNo")int currentPageNo, @Param("pageSize") int pageSize);

    //获取设备总数
    List<Factory> getFactoryCount();

    //删除设备
    int deleteFactoryByUserId(@Param("userid")String userid);

    //updateFactory
    int updateFactory(@Param("id")String id,@Param("status")String status);
}
