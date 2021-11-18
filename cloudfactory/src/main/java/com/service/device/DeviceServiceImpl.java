package com.service.device;

import com.dao.device.DeviceMapper;
import com.pojo.Capacity;
import com.pojo.Device;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService{
    @Resource
    private  DeviceMapper deviceMapper;
    @Override
    public List<Device> getDeviceList(int currentPageNo, int pageSize) {
       return deviceMapper.getDeviceList(currentPageNo,pageSize);
    }

    @Override
    public boolean addDevice(Device device) {
        if(deviceMapper.addDevice(device)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteDevice(String id) {
        if(deviceMapper.deleteDevice(id)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateDevice(Device device) {
        if (deviceMapper.updateDevice(device)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateDeviceStatus(String id, String devicestatus) {
        if (deviceMapper.updateDeviceStatus(id,devicestatus)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean rent(String id,String rentstatus,String factoryid) {
        if (deviceMapper.rent(id,rentstatus,factoryid)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Capacity> getCapacityList() {
        return deviceMapper.getCapacityList();
    }

    @Override
    public boolean updateCapacity(String devcieid, String productidnew, String productidold,String Capacity) {
        if (deviceMapper.updateCapacity(devcieid,productidnew,productidold,Capacity)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteCapacity(String devcieid, String productid) {
        if (deviceMapper.deleteCapacity(devcieid,productid)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean addCapacity(String deviceid, String productid, String capacity) {
        if (deviceMapper.addCapacity(deviceid,productid,capacity)>0){
            return true;
        }else {
            return false;
        }
    }
}
