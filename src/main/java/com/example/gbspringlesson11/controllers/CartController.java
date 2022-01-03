package com.example.gbspringlesson11.controllers;

import com.example.gbspringlesson11.dto.Cart;
import com.example.gbspringlesson11.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable Long id) {
        cartService.addProductByIdToCart(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.getCurrentCart().clear();
    }

    @DeleteMapping("/{id}")
    public void deleteProductFromCart(@PathVariable Long id) {
        cartService.deleteProductByIdFromCart(id);
    }

    @PostMapping("/change_count")
    public void changeCount(
            @RequestParam Long id,
            @RequestParam int delta) {
        cartService.changeCount(id, delta);
    }

}

