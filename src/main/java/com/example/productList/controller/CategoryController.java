package com.example.productList.controller;


import com.example.productList.dto.CategoryDTO;
import com.example.productList.exception.CategoryAlreadyExistException;
import com.example.productList.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Category Rest api",
        description = "Perform crud operations"
)
@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    @Operation(
            summary = "Fetch the all categories",
            description = "All categories will available here when user hit this api"
    )
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<CategoryDTO> allCategories = categoryService.getAllCategories();
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    @Operation(
            summary = "Fetch the all category by Id",
            description = "Category will available here when user hit this api with id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id){
        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Create category",
            description = "Create category will available here when user hit this api"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Delete category",
            description = "Delete categories will available here when user hit this api"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        String message = categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
