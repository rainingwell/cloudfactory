package com.dao.ProductType;
import com.pojo.Product;
import com.pojo.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductTypeMapper {
    //获取设备列表
    List<ProductType> getProductTypeList(@Param("currentPageNo")int currentPageNo, @Param("pageSize") int pageSize);

    //获取设备总数
    List<ProductType> getProductTypeCount();

    //增加产品类别
    int addProductType(ProductType productType);

    int deleteProductType(@Param("id")String id);

    int updateProductType(ProductType productType);
}
