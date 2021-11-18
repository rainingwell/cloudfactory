package com.service.order;

import com.pojo.Bid;
import com.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {
    //获取订单列表
    List<Order> getOrderList(int currentPageNo,int pageSize);

    boolean deleteOrder(String id);

    boolean updateOrder(Order order);

    boolean updatestatus(String id,String orderstatus);

    boolean addOrder(Order order);

    List<Bid> getBidList(String orderid);

    boolean updatebidstatus(String id,String status);

    List<Bid> getAllBidList();

    boolean addbid(Bid bid);
}
