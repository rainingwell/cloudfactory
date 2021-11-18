package com.service.productType;

import com.dao.ProductType.ProductTypeMapper;
import com.pojo.ProductType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService{
    @Resource
    ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> getProductTypeList(int currentPageNo, int pageSize) {
        return productTypeMapper.getProductTypeList(currentPageNo,pageSize);
    }

    @Override
    public int getProductTypeCount() {
        if(productTypeMapper.getProductTypeCount()!=null){
            return productTypeMapper.getProductTypeCount().size();
        }else{
            return 0;
        }
    }

    @Override
    public boolean addProductType(ProductType productType) {
        if (productTypeMapper.addProductType(productType)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteProductType(String id) {
        if (productTypeMapper.deleteProductType(id)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateProductType(ProductType productType) {
        if (productTypeMapper.updateProductType(productType)>0){
            return true;
        }else {
            return false;
        }
    }
}
