package com.byschoo.ecommerce.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.byschoo.ecommerce.Entities.Category;
import com.byschoo.ecommerce.Entities.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(Category category);

}
