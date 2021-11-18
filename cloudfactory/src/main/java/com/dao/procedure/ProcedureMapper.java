package com.dao.procedure;

import com.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface ProcedureMapper {
    String purchase(@Param("orderno")String orderno, @Param("productid")String productid, @Param("ordernum")int ordernum, @Param("deliverDate")String deliverDate, @Param("deaddate")String deaddate, @Param("userid")String userid, @Param("address")String address,@Param("mobile") String mobile);

    String bid(@Param("bid_id")String bid_id);

    String rent(@Param("deviceid")String deviceid,@Param("begindate")String begindate,@Param("length")String length,@Param("factoryid")String factoryid);

    String capacity(@Param("deviceid")String deviceid,@Param("productid")String productid,@Param("capacity")String capacity);

    String schedule(@Param("sid")String sid,@Param("deviceid")String deviceid,@Param("orderid")String orderid,@Param("starttime")String starttime,@Param("endtime")String endtime);
}
