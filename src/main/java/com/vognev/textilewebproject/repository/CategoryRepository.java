package com.vognev.textilewebproject.repository;

import com.vognev.textilewebproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * textilewebproject_2  30/09/2021-13:53
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

   List<Category> findAll();
}

