package com.example.gbspringlesson11.controllers;

import com.example.gbspringlesson11.converter.OrderLineConverter;
import com.example.gbspringlesson11.dto.OrderLineDto;
import com.example.gbspringlesson11.entities.OrderLine;
import com.example.gbspringlesson11.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final OrderLineConverter orderLineConverter;


    @GetMapping
    public List<OrderLineDto> getCart() {
        List<OrderLineDto> orderLineDtoList = new ArrayList<>();
        List<OrderLine> orderLineList = cartService.getCart();
        orderLineList.forEach(ol -> orderLineDtoList.add(orderLineConverter.dtoFromOrderLine(ol)));
        return orderLineDtoList;
    }
    @PostMapping
    public OrderLineDto addOrderLine(
            @RequestParam Long productId,
            @RequestParam String productTitle,
            @RequestParam Integer productCost) {
        OrderLine orderLine = new OrderLine(productId,productTitle,productCost,1);
        OrderLine orderLine1 = cartService.add(orderLine);
        return orderLineConverter.dtoFromOrderLine(orderLine1);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        cartService.deleteById(id);
    }
    @GetMapping("/change_count")
    public void changeCost(@RequestParam Integer delta, @RequestParam Long id){
        cartService.changeCount(id, delta);
    }

}
