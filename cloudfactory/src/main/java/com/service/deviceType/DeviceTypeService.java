package com.service.deviceType;

import com.pojo.DeviceType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceTypeService {
    //获取设备列表
    List<DeviceType> getDeviceTypeList(@Param("currentPageNo")int currentPageNo, @Param("pageSize") int pageSize);

    //增加产品类别
    boolean addDeviceType(DeviceType deviceType);

    boolean deleteDeviceType(@Param("id")String id);

    boolean updateDeviceType(DeviceType deviceType);
}
