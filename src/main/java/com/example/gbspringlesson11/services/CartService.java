package com.example.gbspringlesson11.services;

import com.example.gbspringlesson11.entities.OrderLine;
import com.example.gbspringlesson11.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private CartRepository cartRepository;
    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public List<OrderLine> getCart(){
        return cartRepository.getCart();
    }
    public OrderLine add(OrderLine orderLine){
        return cartRepository.add(orderLine);
    }
    public void deleteById(Long id){
        cartRepository.deleteById(id);
    }
    public void changeCount(Long id, Integer delta){
        if(cartRepository.changeCount(id, delta) < 1){
            deleteById(id);
        }
    }


}
