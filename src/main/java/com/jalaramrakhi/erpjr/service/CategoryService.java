package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.entity.Category;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CategoryService {
    public ResponseEntity<List<Category>> getAllCategories();
    public ResponseEntity<Category> getSingleCategory(Integer id);
    public ResponseEntity<Category> addNewCategory(Category category, HttpServletRequest request);
    public ResponseEntity<Category> updateCategory(Integer id, Category category);
    public ResponseEntity<Category> deleteCategory(Integer id);
}
