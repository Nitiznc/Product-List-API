package com.example.productList.controller;


import com.example.productList.dto.ProductDTO;
import com.example.productList.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Product Rest api",
        description = "Perform crud operations"
)
@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @Operation(
            summary = "Fetch the all Products",
            description = "All products will available here when user hit this api"
    )
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(
            summary = "Fetch  Product By Id",
            description = "Product will available here when user hit this api with id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){
        ProductDTO productDTO = productService.getProductById(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Create Product",
            description = "Create product will available here when user hit this api"
    )
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        ProductDTO createProduct = productService.createProduct(productDTO);
        return new ResponseEntity<>(createProduct, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update Products",
            description = "Update products will available here when user hit this api"
    )
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Long id){
        ProductDTO productdto = productService.updateProduct(id, productDTO);
        return new ResponseEntity<>(productdto, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Product",
            description = "product will deleted here when user hit this api with id"
    )
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        String message = productService.deleteProduct(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
