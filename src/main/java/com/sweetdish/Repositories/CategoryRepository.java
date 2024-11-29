package com.sweetdish.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetdish.Modulers.Categories;

public interface CategoryRepository extends JpaRepository<Categories, Long>{
    boolean existsByName(String name);  // Check existence
}
