package com.byschoo.ecommerce.Controllers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.byschoo.ecommerce.Controllers.Responses.SuccessResponse;
import com.byschoo.ecommerce.DTO.ProductDTO;
import com.byschoo.ecommerce.Entities.Category;
import com.byschoo.ecommerce.Services.Category.ICategoryService;
import com.byschoo.ecommerce.Services.Product.IProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final IProductService productService;
    private final ICategoryService categoryService;

    
    @PostMapping("/save")
    public ResponseEntity<SuccessResponse> saveProduct(@RequestBody ProductDTO productDTO, @RequestParam Long categoryId) {
        // Verificar si la categoría con el ID proporcionado existe
        Category category = categoryService.findCategoryById(categoryId);
        
        // Guardar el producto
        return new ResponseEntity<>(
                SuccessResponse.builder()
                    .mensaje("Product saved successfully")
                    .object(productService.saveProduct(productDTO, category))
                    .dateTime(LocalDateTime.now())
                    .build(),
                HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<SuccessResponse> updateProduct(@RequestBody ProductDTO productDTO, @RequestParam Long categoryId) {
        // Verificar si la categoría con el ID proporcionado existe
        Category category = categoryService.findCategoryById(categoryId);
        
        // Actualizar el producto
        return new ResponseEntity<>(
                SuccessResponse.builder()
                    .mensaje("Product updated successfully")
                    .object(productService.updateProduct(productDTO, category))
                    .dateTime(LocalDateTime.now())
                    .build(),
                HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<SuccessResponse> getAllProducts() {
        return new ResponseEntity<>(
                SuccessResponse.builder()
                    .mensaje("All products")
                    .object(productService.getAllProducts())
                    .dateTime(LocalDateTime.now())
                    .build(),
                HttpStatus.OK);
    }

    @GetMapping("/byCategory")
    public ResponseEntity<SuccessResponse> getAllProductsByCategory(@RequestParam Long categoryId) {
        // Verificar si la categoría con el ID proporcionado existe
        Category category = categoryService.findCategoryById(categoryId);
        
        return new ResponseEntity<>(
                SuccessResponse.builder()
                    .mensaje("All products by category")
                    .object(productService.findAllByCategory(category))
                    .dateTime(LocalDateTime.now())
                    .build(),
                HttpStatus.OK);
    }

}
