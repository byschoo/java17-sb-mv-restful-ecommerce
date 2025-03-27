package com.byschoo.ecommerce.Services.Category;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.byschoo.ecommerce.DTO.CategoryDTO;
import com.byschoo.ecommerce.Entities.Category;
import com.byschoo.ecommerce.Exceptions.Custom.ResourceNotFoundException;
import com.byschoo.ecommerce.Exceptions.Custom.ResourceAlreadyExistsException;
import com.byschoo.ecommerce.Repositories.ICategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Category saveCategory(Category category) {
        // Verificar si la categoría ya existe
        if (categoryRepository.existsByCategoryName(category.getCategoryName())) {
            throw new ResourceAlreadyExistsException(
                "Category already exists",
                "Exc-409-01",
                HttpStatus.CONFLICT
            );
        }
        return categoryRepository.save(category);
        
    }

    @Override
    public Category updateCategory(CategoryDTO categoryDTO) {        
        // Verificar si la categoría con el ID proporcionado existe
        Category existingCategory = categoryRepository.findById(categoryDTO.getId()).orElseThrow(
            () -> new ResourceNotFoundException(
                String.format("Category with ID %d not found in the database", categoryDTO.getId()), 
                "Exc-4006", 
                HttpStatus.NOT_FOUND
            )
        );
    
        // Verificar si el nuevo nombre ya existe en otra categoría
        if (categoryRepository.existsByCategoryNameAndIdNot(categoryDTO.getCategoryName(), categoryDTO.getId())) {
            throw new ResourceAlreadyExistsException(
                "Category with the same name already exists",
                "Exc-409-01",
                HttpStatus.CONFLICT
            );
        }

        // Actualizar los atributos de la categoría uno por uno
        existingCategory.setCategoryName(categoryDTO.getCategoryName());
        existingCategory.setDescription(categoryDTO.getDescription());
    
        // Guardar la categoría actualizada
        return categoryRepository.save(existingCategory);
    }

    @Override
    public void disablingCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(
                String.format("Category with ID %d not found in the database", id), 
                "Exc-4006", 
                HttpStatus.NOT_FOUND
            )
        );

        category.setIsDisabled(true);
        categoryRepository.save(category);
    }

    @Override
    public void enablingCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(
                String.format("Category with ID %d not found in the database", id), 
                "Exc-4006", 
                HttpStatus.NOT_FOUND
            )
        );
                
        category.setIsDisabled(false);
        categoryRepository.save(category);
    }

    @Override
    public CategoryDTO findCategoryById(Long id) {
        // Buscar la categoría en la base de datos
        Category category = categoryRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(
                String.format("Category with ID %d not found in the database", id), 
                "Exc-4006", 
                HttpStatus.NOT_FOUND
            )
        );
    
        // Mapear la categoría a CategoryDTO
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO findByCategoryName(String categoryName) {
        // Verificar si la categoría existe
        Category category = categoryRepository.findByCategoryName(categoryName)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format("Category with name: '%s', not found in the database", categoryName),
                "Exc-4006",
                HttpStatus.NOT_FOUND
            ));
    
        // Mapear la categoría a CategoryDTO
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public Boolean existsByCategoryName(String categoryName) {
        return categoryRepository.existsByCategoryName(categoryName);
    }

    @Override
    public Boolean existsByCategoryNameAndIdNot(String categoryName, Long id) {
        Boolean exists = categoryRepository.existsByCategoryNameAndIdNot(categoryName, id);
        if (Boolean.TRUE.equals(exists)) {
            throw new ResourceAlreadyExistsException(
                    "Category with the same name already exists",
                    "Exc-409-01",
                    HttpStatus.CONFLICT
            );
        }
        return exists;
    }

    @Override
    public List<CategoryDTO> findAllByIsDisabled(Boolean isDisabled) {
        return categoryRepository.findAllByIsDisabled(isDisabled).stream()
            .map(category -> modelMapper.map(category, CategoryDTO.class)) // Cambiar a CategoryDTO
            .collect(Collectors.toList());
    }

}
