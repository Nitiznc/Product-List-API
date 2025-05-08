package com.example.productList.mapper;

import com.example.productList.dto.ProductDTO;
import com.example.productList.entity.Category;
import com.example.productList.entity.Product;

public class ProductMapper {
    public static ProductDTO toProductDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().getId()
        );
    }

    public static Product toProductEntity(ProductDTO productDTO, Category category) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        return product;
    }
}
