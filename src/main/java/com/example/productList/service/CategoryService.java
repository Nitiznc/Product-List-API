package com.example.productList.service;

import com.example.productList.dto.CategoryDTO;
import com.example.productList.entity.Category;
import com.example.productList.exception.CategoryAlreadyExistException;
import com.example.productList.mapper.CategoryMapper;
import com.example.productList.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories(){
        return categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDTO).toList();
    }

    public CategoryDTO getCategoryById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return CategoryMapper.toCategoryDTO(category);
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryDTO.getName());
        if (optionalCategory.isPresent()){
            throw new CategoryAlreadyExistException(
                    "Category " + categoryDTO.getName() + " already exit!"
            );
        }
        Category category = CategoryMapper.toCategoryEntity(categoryDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }

    public String deleteCategoryById(Long id){
        categoryRepository.deleteById(id);
        return "Category id " + id +" has been deleted successfully";
    }
}
