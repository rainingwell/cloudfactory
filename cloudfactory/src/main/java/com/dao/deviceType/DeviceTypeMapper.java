package com.dao.deviceType;

import com.pojo.DeviceType;
import com.pojo.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceTypeMapper {

    //获取设备列表
    List<DeviceType> getDeviceTypeList(@Param("currentPageNo")int currentPageNo, @Param("pageSize") int pageSize);

    //增加产品类别
    int addDeviceType(DeviceType deviceType);

    int deleteDeviceType(@Param("id")String id);

    int updateDeviceType(DeviceType deviceType);
}
