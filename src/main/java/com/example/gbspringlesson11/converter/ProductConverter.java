package com.example.gbspringlesson11.converter;

import com.example.gbspringlesson11.dto.ProductDto;
import com.example.gbspringlesson11.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public Product productFromDto(ProductDto productDto){
        return new Product(productDto.getId(),productDto.getTitle(),productDto.getCost());
    }
    public ProductDto dtoFromProduct(Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getCost());
    }
}
