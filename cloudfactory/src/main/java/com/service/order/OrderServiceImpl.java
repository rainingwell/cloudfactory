package com.service.order;


import com.dao.order.OrderMapper;
import com.pojo.Bid;
import com.pojo.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Resource
    private OrderMapper orderMapper;
    @Override
    public List<Order> getOrderList(int currentPageNo, int pageSize) {
        return orderMapper.getOrderList(currentPageNo,pageSize);
    }

    @Override
    public boolean deleteOrder(String id) {
        if (orderMapper.deleteOrder(id)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateOrder(Order order) {
        if (orderMapper.updateOrder(order)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updatestatus(String id, String orderstatus) {
        if (orderMapper.updatestatus(id,orderstatus)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean addOrder(Order order) {
        if (orderMapper.addOrder(order)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Bid> getBidList(String orderid) {
        return orderMapper.getBidList(orderid);
    }

    @Override
    public boolean updatebidstatus(String id, String status) {
        if (orderMapper.updatebidstatus(id,status)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Bid> getAllBidList() {
        return orderMapper.getAllBidList();
    }

    @Override
    public boolean addbid(Bid bid) {
        if (orderMapper.addbid(bid)>0){
            return true;
        }else {
            return false;
        }
    }
}
