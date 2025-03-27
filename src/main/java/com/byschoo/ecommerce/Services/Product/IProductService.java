package com.byschoo.ecommerce.Services.Product;

import java.util.List;

import com.byschoo.ecommerce.DTO.CategoryDTO;
import com.byschoo.ecommerce.DTO.ProductDTO;
import com.byschoo.ecommerce.Entities.Product;

public interface IProductService {
    Product saveProduct(ProductDTO productDTO, CategoryDTO categoryDTO);
    Product updateProduct(ProductDTO productDTO, CategoryDTO categoryDTO);
    List<ProductDTO> getAllProducts();
    List<ProductDTO> findAllByCategory(CategoryDTO categoryDTO);
}
