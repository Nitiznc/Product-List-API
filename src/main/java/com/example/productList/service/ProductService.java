package com.example.productList.service;

import com.example.productList.dto.ProductDTO;
import com.example.productList.entity.Category;
import com.example.productList.entity.Product;
import com.example.productList.exception.CategoryNotFoundException;
import com.example.productList.mapper.ProductMapper;
import com.example.productList.repository.CategoryRepository;
import com.example.productList.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll().stream().map(ProductMapper::toProductDTO).toList();
    }

    public ProductDTO getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductMapper.toProductDTO(product);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {

        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category id: " + productDTO.getCategoryId() + " not found"
                        )
                );

        Product product = ProductMapper.toProductEntity(productDTO, category);
        product = productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO){
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = productRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        productRepository.save(product);
        return ProductMapper.toProductDTO(product);

    }

    public String deleteProduct(Long id){
        productRepository.deleteById(id);
        return "Product " + id + " has been deleted successfully";
    }
}
