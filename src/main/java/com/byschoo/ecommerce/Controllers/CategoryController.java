package com.byschoo.ecommerce.Controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.byschoo.ecommerce.Controllers.Responses.SuccessResponse;
import com.byschoo.ecommerce.Entities.Category;
import com.byschoo.ecommerce.Services.Category.ICategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final ICategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<SuccessResponse> saveCategory(@RequestBody Category category) {
        if (categoryService.existsByCategoryName(category.getCategoryName())) {
            return new ResponseEntity<>(
                SuccessResponse.builder()
                    .mensaje("Category already exists")
                    .dateTime(LocalDateTime.now())
                    .build(),
                HttpStatus.CONFLICT
            );
        }
        return new ResponseEntity<>(
            SuccessResponse.builder()
                .mensaje("Category saved successfully")
                .object(categoryService.saveCategory(category))
                .dateTime(LocalDateTime.now())
                .build(),
            HttpStatus.CREATED
        );
    }

    @PostMapping("/update")
    public ResponseEntity<SuccessResponse> updateCategory(@RequestBody Category category) {
        // Verificar si la categoría con el ID proporcionado existe
        Category existingCategory = categoryService.findCategoryById(category.getId());
    
        // Verificar si el nuevo nombre ya existe en otra categoría
        if (!categoryService.existsByCategoryNameAndIdNot(category.getCategoryName(), category.getId())) {
            
            // Actualizar los datos de la categoría existente
            existingCategory.setCategoryName(category.getCategoryName());
            existingCategory.setDescription(category.getDescription());
            existingCategory.setImageUrl(category.getImageUrl());
            existingCategory.setIsDisabled(category.getIsDisabled());
        }

        // Guardar los cambios
        Category updatedCategory = categoryService.updateCategory(existingCategory);    
    
        return new ResponseEntity<>(
            SuccessResponse.builder()
                .mensaje("Category updated successfully")
                .object(updatedCategory)
                .dateTime(LocalDateTime.now())
                .build(),
            HttpStatus.CREATED
        );
    }

    @PatchMapping("/disabling/{id}")
    public ResponseEntity<SuccessResponse> disablingCategory(@PathVariable Long id) {
        categoryService.disablingCategory(id);
        return new ResponseEntity<>(
            SuccessResponse.builder()
                .mensaje("Category disabled successfully")
                .dateTime(LocalDateTime.now())
                .build(),
            HttpStatus.OK
        );
    }

    @PatchMapping("/enabling/{id}")
    public ResponseEntity<SuccessResponse> enablingCategory(@PathVariable Long id) {
        categoryService.enablingCategory(id);
        return new ResponseEntity<>(
            SuccessResponse.builder()
                .mensaje("Category enabled successfully")
                .dateTime(LocalDateTime.now())
                .build(),
            HttpStatus.OK
        );
    }

    @GetMapping("/findAllEnabledCategories")
    public ResponseEntity<Map<String, Object>> findAllEnabledCategories(@RequestParam(defaultValue = "false") Boolean isDisabled) {
        // Obtener todas las categorías habilitadas desde el servicio
        List<Category> categories = categoryService.findAllByIsDisabled(isDisabled);
    
        // Envolver cada categoría en un Map con la clave "category"
        List<Map<String, Object>> wrappedCategories = categories.stream()
            .map(category -> Map.<String, Object>of("category", category)) // Especificar explícitamente el tipo del Map
            .toList();
    
        // Construir la respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Categories retrieved successfully");
        response.put("category", wrappedCategories);
        response.put("dateTime", LocalDateTime.now());
    
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findAllDisabledCategories")
    public ResponseEntity<SuccessResponse> findAllDisabledCategories(@RequestParam(defaultValue = "true") Boolean isDisabled) {
        return new ResponseEntity<>(
            SuccessResponse.builder()
                .mensaje("Categories retrieved successfully")
                .object(categoryService.findAllByIsDisabled(isDisabled))
                .dateTime(LocalDateTime.now())
                .build(),
            HttpStatus.OK
        );
    }

    @GetMapping("/findById")
    public ResponseEntity<SuccessResponse> findCategoryById(@RequestParam Long id) {
        return new ResponseEntity<>(
            SuccessResponse.builder()
                .mensaje("Category retrieved successfully")
                .object(categoryService.findCategoryById(id))
                .dateTime(LocalDateTime.now())
                .build(),
            HttpStatus.OK
        );
    }

    @GetMapping("/findByName")
    public ResponseEntity<SuccessResponse> findByCategoryName(@RequestParam String categoryName) {
        return new ResponseEntity<>(
            SuccessResponse.builder()
                .mensaje("Category retrieved successfully")
                .object(categoryService.findByCategoryName(categoryName))
                .dateTime(LocalDateTime.now())
                .build(),
            HttpStatus.OK
        );
    }
}
