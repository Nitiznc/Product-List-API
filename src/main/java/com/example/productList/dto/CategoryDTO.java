package com.example.productList.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Schema(
        name = "Category",
        description = "Its hold category information with their products"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private List<ProductDTO> products;

}
