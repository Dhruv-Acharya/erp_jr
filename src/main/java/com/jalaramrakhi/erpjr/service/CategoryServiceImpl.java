package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.Exceptions.CategoryMissingInformationException;
import com.jalaramrakhi.erpjr.Exceptions.CategoryNotFoundException;
import com.jalaramrakhi.erpjr.entity.Category;
import com.jalaramrakhi.erpjr.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        Assert.notNull(categoryRepository, "CategoryRepository must not be null!");
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> allCategories = categoryRepository.findAll();

        return new ResponseEntity<List<Category>>(allCategories, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Category> getSingleCategory(Integer id) {
        Category getCategory = findCategoryIfExists(id);
        return new ResponseEntity<Category>(getCategory, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Category> addNewCategory(Category category, HttpServletRequest request) {
        if(null != category.getHSN_code()) {
            categoryRepository.saveAndFlush(category);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Location", categoryUrlHelper(category, request));
            return new ResponseEntity<Category>(category, responseHeaders, HttpStatus.CREATED);
        }
        else {
            throw new CategoryMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<Category> updateCategory(Integer id, Category category) {
        Category existingCategory = findCategoryIfExists(id);

        if(null != category.getCategory_name()) {
            BeanUtils.copyProperties(category, existingCategory);

            // Ensure ID remains unchanged
            existingCategory.setHSN_code(id);

            Category updatedCategory = categoryRepository.saveAndFlush(existingCategory);
            return new ResponseEntity<Category>(updatedCategory, HttpStatus.OK);
        } else {
            throw new CategoryMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<Category> deleteCategory(Integer id) {
        Category existingCategory = findCategoryIfExists(id);
        categoryRepository.delete(existingCategory);
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }

    private String categoryUrlHelper(Category category, HttpServletRequest request) {
        StringBuilder resourcePath = new StringBuilder();

        resourcePath.append(request.getRequestURL());
        resourcePath.append("/");
        resourcePath.append(category.getHSN_code());

        return resourcePath.toString();
    }

    private Category findCategoryIfExists(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);

        if(category.isPresent()){
            return category.get();
        }
        else {
            throw new CategoryNotFoundException();
        }
    }
}
