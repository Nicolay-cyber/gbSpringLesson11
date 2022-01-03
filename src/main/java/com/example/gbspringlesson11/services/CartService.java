package com.example.gbspringlesson11.services;

import com.example.gbspringlesson11.dto.Cart;
import com.example.gbspringlesson11.entities.Product;
import com.example.gbspringlesson11.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productsService;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void addProductByIdToCart(Long productId) {
        if (!getCurrentCart().addProduct(productId)) {
            Product product = productsService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Невозможно добавить продукт в корзину. Продукт не найдет, id: " + productId));
            getCurrentCart().addProduct(product);
        }
    }

    public void deleteProductByIdFromCart(Long id) {
        getCurrentCart().removeProduct(id);
    }

    public void changeCount(Long id, int delta) {
        getCurrentCart().changeCount(id, delta);
    }

    public void clear() {
        getCurrentCart().clear();
    }
}
