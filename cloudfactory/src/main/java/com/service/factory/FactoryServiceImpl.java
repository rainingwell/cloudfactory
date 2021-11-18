package com.service.factory;

import com.dao.factory.FactoryMapper;
import com.dao.product.ProductMapper;
import com.pojo.Factory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FactoryServiceImpl implements FactoryService{
    @Resource
    FactoryMapper factoryMapper;
    @Override
    public List<Factory> getFactoryList(int currentPageNo, int pageSize) {
        return factoryMapper.getFactoryList(currentPageNo,pageSize);
    }

    @Override
    public int getFactoryCount() {
       if(factoryMapper.getFactoryCount()!=null){
           return factoryMapper.getFactoryCount().size();
       }else {
           return 0;
       }
    }

    @Override
    public boolean deleteFactory(String userid) {
        if(factoryMapper.deleteFactoryByUserId(userid)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateFactory(String id, String status) {
        if (factoryMapper.updateFactory(id,status)>0){
            return true;
        }else {
            return false;
        }
    }
}
