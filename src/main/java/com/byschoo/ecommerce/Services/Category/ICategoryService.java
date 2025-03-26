package com.byschoo.ecommerce.Services.Category;

import java.util.List;

import com.byschoo.ecommerce.Entities.Category;

public interface ICategoryService {
    Category saveCategory(Category category);
    Category updateCategory(Category category);
    void disablingCategory(Long id);
    void enablingCategory(Long id);
    Category findCategoryById(Long id);
    Category findByCategoryName(String categoryName);
    Boolean existsByCategoryName(String categoryName);
    Boolean existsByCategoryNameAndIdNot(String categoryName, Long id);
    List<Category> findAllByIsDisabled(Boolean isDisabled);
}
