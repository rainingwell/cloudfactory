package com.service.factory;

import com.pojo.Factory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FactoryService {
    List<Factory> getFactoryList(int currentPageNo,int pageSize);

    int getFactoryCount();

    boolean deleteFactory(String userid);

    boolean updateFactory(String id,String status);
}
