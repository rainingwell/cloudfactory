package com.dao.device;

import com.pojo.Capacity;
import com.pojo.Device;
import com.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceMapper {
    //获取设备列表
    List<Device> getDeviceList(@Param("currentPageNo")int currentPageNo, @Param("pageSize") int pageSize);

    int addDevice(Device device);

    int deleteDevice(@Param("id")String id);

    int updateDevice(Device device);

    int updateDeviceStatus(@Param("id")String id,@Param("devicestatus")String devicestatus);

    int rent(@Param("id")String id,@Param("rentstatus")String rentstatus,@Param("factoryid")String factoryid);

    List<Capacity> getCapacityList();

    int updateCapacity(@Param("deviceid")String deviceid,@Param("productidnew")String productidnew,@Param("productidold")String productidold,@Param("capacity")String capacity);

    int deleteCapacity(@Param("deviceid")String deviceid,@Param("productid")String productid);

    int addCapacity(@Param("deviceid")String deviceid,@Param("productid")String productid,@Param("capacity")String capacity);

}
