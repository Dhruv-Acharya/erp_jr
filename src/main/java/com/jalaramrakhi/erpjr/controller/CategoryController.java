package com.jalaramrakhi.erpjr.controller;

import com.jalaramrakhi.erpjr.entity.Category;
import com.jalaramrakhi.erpjr.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RequestMapping(value = "/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // List All Categories
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategories() throws Throwable {
        return categoryService.getAllCategories();
    }

    // Get One Category
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> getSingleCategory(@PathVariable Integer id) {
        return categoryService.getSingleCategory(id);
    }

    // Add New Category
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Category> createNewCategory(@RequestBody Category category, HttpServletRequest req) {
        return categoryService.addNewCategory(category, req);
    }

    // Update Category with PATCH
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    // Delete Category
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Category> deleteCategory(@PathVariable Integer id) {
        return categoryService.deleteCategory(id);
    }
}
