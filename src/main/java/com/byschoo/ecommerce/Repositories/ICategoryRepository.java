package com.byschoo.ecommerce.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.byschoo.ecommerce.Entities.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryName(String categoryName);
    Optional<Category> findByCategoryNameAndIdNot(String categoryName, Long id);
    Boolean existsByCategoryName(String categoryName);
    Boolean existsByCategoryNameAndIdNot(String categoryName, Long id);
    List<Category> findAllByIsDisabled(Boolean isDisabled);

}
