package com.dao.product;

import com.pojo.Product;
import com.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
    //获取设备列表
    List<Product> getProductList(@Param("currentPageNo")int currentPageNo, @Param("pageSize") int pageSize);

    //获取设备总数
    List<Product> getProductCount();

    int addProduct(Product product);

    int deleteProduct(@Param("id")String id);

    int updateProduct(Product product);
}
