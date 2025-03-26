package com.byschoo.ecommerce.Services.Product;

import java.util.List;

import com.byschoo.ecommerce.DTO.ProductDTO;
import com.byschoo.ecommerce.Entities.Category;
import com.byschoo.ecommerce.Entities.Product;

public interface IProductService {
    Product saveProduct(ProductDTO productDTO, Category category);
    Product updateProduct(ProductDTO productDTO, Category category);
    List<ProductDTO> getAllProducts();
    List<Product> findAllByCategory(Category category);
}
