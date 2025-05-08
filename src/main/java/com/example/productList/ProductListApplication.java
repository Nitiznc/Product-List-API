package com.example.productList;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Product List API documentation",
				description = "product service api",
				version = "v1",
				contact = @Contact(
						name = "Nitish Chaudhary",
						email = "nitishnc123@gmail.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "sharepoint url product service api",
				url = "example.com"
		)
)
@SpringBootApplication
public class ProductListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductListApplication.class, args);
	}

}
