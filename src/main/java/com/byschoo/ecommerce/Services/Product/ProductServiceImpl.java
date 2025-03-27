package com.byschoo.ecommerce.Services.Product;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.byschoo.ecommerce.DTO.CategoryDTO;
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
    public Product saveProduct(ProductDTO productDTO, CategoryDTO categoryDTO) {
        Product product = modelMapper.map(productDTO, Product.class);

        product.setCategory(modelMapper.map(categoryDTO, Category.class));
        return productRepository.save(product);        
    }
    
    @Override
    public Product updateProduct(ProductDTO productDTO, CategoryDTO categoryDTO) {
        // Verificar si el producto existe en la base de datos
        Product existingProduct = productRepository.findById(productDTO.getId())
            .orElseThrow(() -> new IllegalArgumentException("El producto con ID " + productDTO.getId() + " no existe."));
    
        // Actualizar los atributos del producto uno por uno
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setImageUrl(productDTO.getImageUrl());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setStock(productDTO.getStock());
        existingProduct.setCategory(modelMapper.map(categoryDTO, Category.class));
    
        // Guardar el producto actualizado
        return productRepository.save(existingProduct);
    }
    
    @Override
    public List<ProductDTO> findAllByCategory(CategoryDTO categoryDTO) {
        // Mapear el CategoryDTO a Category
        Category category = modelMapper.map(categoryDTO, Category.class);
    
        // Usar el repositorio para obtener los productos y mapearlos a ProductDTO
        return productRepository.findAllByCategory(category).stream()
            .map(product -> modelMapper.map(product, ProductDTO.class))
            .collect(Collectors.toList());
    }
    
    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
            .map(product -> modelMapper.map(product, ProductDTO.class))
            .collect(Collectors.toList());
    }

}
