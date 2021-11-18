package com.service.productType;

import com.pojo.Product;
import com.pojo.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductTypeService {
    //获取设备列表
    List<ProductType> getProductTypeList(int currentPageNo, int pageSize);

    //获取设备总数
   int getProductTypeCount();

   boolean addProductType(ProductType productType);

   boolean deleteProductType(String id);

   boolean updateProductType(ProductType productType);
}
