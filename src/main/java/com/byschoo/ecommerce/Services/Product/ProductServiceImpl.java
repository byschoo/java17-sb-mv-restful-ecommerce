package com.byschoo.ecommerce.Services.Product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.byschoo.ecommerce.Entities.Category;
import com.byschoo.ecommerce.Entities.Product;
import com.byschoo.ecommerce.Repositories.IProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    
    private final IProductRepository productRepository;
    
    @Override
    public Product saveCategory(Product product, Category category) {
        product.setCategory(category);
        return productRepository.save(product);        
    }
    
    @Override
    public Product updateCategory(Product product, Category category) {
        product.setCategory(category);
        return productRepository.save(product);
    }
    
    @Override
    public List<Product> findAllByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }
    
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
