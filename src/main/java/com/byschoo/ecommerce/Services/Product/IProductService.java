package com.byschoo.ecommerce.Services.Product;

import java.util.List;

import com.byschoo.ecommerce.Entities.Category;
import com.byschoo.ecommerce.Entities.Product;

public interface IProductService {
    Product saveCategory(Product product, Category category);
    Product updateCategory(Product product, Category category);
    List<Product> getAllProducts();
    List<Product> findAllByCategory(Category category);
}
