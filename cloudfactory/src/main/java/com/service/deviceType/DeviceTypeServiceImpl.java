package com.service.deviceType;

import com.dao.deviceType.DeviceTypeMapper;
import com.dao.factory.FactoryMapper;
import com.pojo.DeviceType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeviceTypeServiceImpl implements DeviceTypeService{
    @Resource
    DeviceTypeMapper deviceTypeMapper;

    @Override
    public List<DeviceType> getDeviceTypeList(int currentPageNo, int pageSize) {
        return deviceTypeMapper.getDeviceTypeList(currentPageNo,pageSize);
    }

    @Override
    public boolean addDeviceType(DeviceType deviceType) {
        if(deviceTypeMapper.addDeviceType(deviceType)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteDeviceType(String id) {
        if (deviceTypeMapper.deleteDeviceType(id)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateDeviceType(DeviceType deviceType) {
        if(deviceTypeMapper.updateDeviceType(deviceType)>0){
            return true;
        }else {
            return false;
        }
    }
}
