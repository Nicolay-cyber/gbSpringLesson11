package com.example.gbspringlesson11.controllers;
import com.example.gbspringlesson11.validators.ProductValidator;
import com.example.gbspringlesson11.converter.ProductConverter;
import com.example.gbspringlesson11.dto.ProductDto;
import com.example.gbspringlesson11.entities.Product;
import com.example.gbspringlesson11.exceptions.ResourceNotFoundException;
import com.example.gbspringlesson11.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;

    @GetMapping
    public Page<ProductDto> getAllProducts(
        @RequestParam(name = "p", defaultValue = "1") Integer page,
        @RequestParam(name = "min_cost", required = false) Integer minCost,
        @RequestParam(name = "max_cost", required = false) Integer maxCost,
        @RequestParam(name = "title_part", required = false) String titlePart
    ) {
            if (page < 1) {
                page = 1;
            }
            return productService.find(minCost, maxCost, titlePart, page).map(
                    p -> productConverter.dtoFromProduct(p)
            );
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return productConverter.dtoFromProduct(product);
    }

    @GetMapping("/change_cost")
    public void changeCost(@RequestParam Integer delta, @RequestParam Long id){
        productService.changeCost(delta, id);
    }

    @PostMapping
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.productFromDto(productDto);
        product = productService.save(product);
        return productConverter.dtoFromProduct(product);
    }
    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        productValidator.validate(productDto);
        Product product = productService.update(productDto);
        return productConverter.dtoFromProduct(product);
    }
}
