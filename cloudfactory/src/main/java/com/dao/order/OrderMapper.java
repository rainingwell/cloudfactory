package com.dao.order;

import com.pojo.Bid;
import com.pojo.Factory;
import com.pojo.Order;
import com.sun.xml.internal.ws.api.message.saaj.SaajStaxWriter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    //获取订单列表
    List<Order> getOrderList(@Param("currentPageNo")int currentPageNo, @Param("pageSize") int pageSize);

    int addOrder(Order order);

    int deleteOrder(@Param("id")String id);

    int updateOrder(Order order);

    int updatestatus(@Param("id")String id,@Param("orderstatus")String orderstatus);

    List<Bid> getBidList(@Param("orderid")String orderid);

    int updatebidstatus(@Param("id")String id,@Param("status")String status);

    List<Bid> getAllBidList();

    int addbid(Bid bid);
}
