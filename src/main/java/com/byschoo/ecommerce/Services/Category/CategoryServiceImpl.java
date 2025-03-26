package com.byschoo.ecommerce.Services.Category;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.byschoo.ecommerce.Entities.Category;
import com.byschoo.ecommerce.Exceptions.Custom.ResourceNotFoundException;
import com.byschoo.ecommerce.Exceptions.Custom.ResourceAlreadyExistsException;
import com.byschoo.ecommerce.Repositories.ICategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
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
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(
                String.format("Category with ID %d not found in the database", id), 
                "Exc-4006", 
                HttpStatus.NOT_FOUND
            )
        );
    }

    @Override
    public Category findByCategoryName(String categoryName) {
        if(existsByCategoryName(categoryName)) {
            return categoryRepository.findByCategoryName(categoryName).get();
        } else {
            throw new ResourceNotFoundException(
                String.format("Category with name %s not found in the database", categoryName), 
                "Exc-4006", 
                HttpStatus.NOT_FOUND
            );
        }
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
    public List<Category> findAllByIsDisabled(Boolean isDisabled) {
        return categoryRepository.findAllByIsDisabled(isDisabled);
    }

}
