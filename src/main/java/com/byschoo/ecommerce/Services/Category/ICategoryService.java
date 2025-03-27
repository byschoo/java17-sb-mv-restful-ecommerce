package com.byschoo.ecommerce.Services.Category;

import java.util.List;

import com.byschoo.ecommerce.DTO.CategoryDTO;
import com.byschoo.ecommerce.Entities.Category;

public interface ICategoryService {
    Category saveCategory(Category category);
    Category updateCategory(CategoryDTO categoryDTO);
    void disablingCategory(Long id);
    void enablingCategory(Long id);
    CategoryDTO findCategoryById(Long id);
    CategoryDTO findByCategoryName(String categoryName);
    Boolean existsByCategoryName(String categoryName);
    Boolean existsByCategoryNameAndIdNot(String categoryName, Long id);
    List<CategoryDTO> findAllByIsDisabled(Boolean isDisabled);
}
