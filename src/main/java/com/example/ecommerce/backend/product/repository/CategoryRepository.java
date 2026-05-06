package com.example.ecommerce.backend.product.repository;

import com.example.ecommerce.backend.product.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByCode(String code);
    
    @Query("""
           SELECT c FROM Category c
           WHERE c.isActive = true
           AND (:name IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')))
           AND (:code IS NULL OR c.code = :code)
           """)
    Page<Category> searchCategories(String name, String code, Pageable pageable);
}
