package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.Category;
import com.vognev.textilewebproject.model.MyUser;
import com.vognev.textilewebproject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * textilewebproject_2  10/10/2021-7:50
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void save(Category category){
        categoryRepository.save(category);
    }

    public Category getById(int id){
        return categoryRepository.getById(id);
    }

    public void delete(Category category){
        categoryRepository.delete(category);
    }
}
