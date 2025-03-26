package com.byschoo.ecommerce.Services.Product;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.byschoo.ecommerce.DTO.ProductDTO;
import com.byschoo.ecommerce.Entities.Category;
import com.byschoo.ecommerce.Entities.Product;
import com.byschoo.ecommerce.Repositories.IProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    
    private final IProductRepository productRepository;

    private final ModelMapper modelMapper;

    // POSTMAPPING ------------------------------------------------------------------------------------
    @Override
    public Product saveProduct(ProductDTO productDTO, Category category) {
        Product product = modelMapper.map(productDTO, Product.class);

        product.setCategory(category);
        return productRepository.save(product);        
    }
    
    @Override
    public Product updateProduct(ProductDTO productDTO, Category category) {
        // Verificar si el producto existe en la base de datos
        Product existingProduct = productRepository.findById(productDTO.getId())
            .orElseThrow(() -> new IllegalArgumentException("El producto con ID " + productDTO.getId() + " no existe."));
    
        // Actualizar los atributos del producto uno por uno
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setImageUrl(productDTO.getImageUrl());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setStock(productDTO.getStock());
        existingProduct.setCategory(category);
    
        // Guardar el producto actualizado
        return productRepository.save(existingProduct);
    }
    
    @Override
    public List<Product> findAllByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }
    
    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
            .map(product -> modelMapper.map(product, ProductDTO.class))
            .collect(Collectors.toList());
    }

}
