package com.service.product;

import com.pojo.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    List<Product> getProductList(int currentPageNo,int pageSize);

    int getProductCount();

    boolean addProduct(Product product);

    boolean deletaProduct(String id);

    boolean updateProduct(Product product);
}
