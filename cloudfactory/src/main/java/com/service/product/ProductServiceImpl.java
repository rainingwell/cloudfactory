package com.service.product;

import com.dao.product.ProductMapper;
import com.dao.user.UserMapper;
import com.pojo.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Resource
    ProductMapper productMapper;
    @Override
    public List<Product> getProductList(int currentPageNo, int pageSize) {
        return productMapper.getProductList(currentPageNo,pageSize);
    }

    @Override
    public int getProductCount() {
        if(productMapper.getProductCount()!=null){
            return productMapper.getProductCount().size();
        }else{
            return 0;
        }
    }

    @Override
    public boolean addProduct(Product product) {
         if(productMapper.addProduct(product)>0){
             return true;
         }else {
             return false;
         }
    }

    @Override
    public boolean deletaProduct(String id) {
        if(productMapper.deleteProduct(id)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        if(productMapper.updateProduct(product)>0){
            return true;
        }else {
            return false;
        }
    }
}
