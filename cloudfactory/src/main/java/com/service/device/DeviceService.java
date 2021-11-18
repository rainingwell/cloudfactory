package com.service.device;

import com.pojo.Capacity;
import com.pojo.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceService {
    //获取设备列表
    List<Device> getDeviceList(int currentPageNo,  int pageSize);

    boolean addDevice(Device device);

    boolean deleteDevice(String id);

    boolean updateDevice(Device device);

    boolean updateDeviceStatus(String id,String devicestatus);

    boolean rent(String id,String rentstatus,String factoryid);

    List<Capacity> getCapacityList();

    boolean updateCapacity(String devcieid,String productidnew,String productidold,String Capacity);

    boolean deleteCapacity(String devcieid,String productid);

    boolean addCapacity(String deviceid,String productid,String capacity);

}
