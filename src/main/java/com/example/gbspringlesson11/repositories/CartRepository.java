package com.example.gbspringlesson11.repositories;

import com.example.gbspringlesson11.entities.OrderLine;
import com.example.gbspringlesson11.exceptions.CartException;
import com.example.gbspringlesson11.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CartRepository {
    List<OrderLine> orderLineList;

    @PostConstruct
    public void init() {
        orderLineList = new ArrayList<>(List.of(
                new OrderLine(4L,"Banana",150,1),
                new OrderLine(5L,"Orange",101, 1)
        ));
    }

    public List<OrderLine> getCart() {
        return Collections.unmodifiableList(orderLineList);
    }

    public void deleteById(Long id) {
        orderLineList.removeIf(ol -> ol.getId().equals(id));
    }

    public Integer changeCount(Long id, Integer delta) {
        OrderLine orderLine = orderLineList.stream().filter(ol -> ol.getId().equals(id)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Unknown order line"));
        orderLine.setCount(orderLine.getCount() + delta);
        return orderLine.getCount();
    }
    public OrderLine add(OrderLine orderLine){
        try{
            OrderLine oldOrderLine = orderLineList.stream().filter(ol -> ol.getId().equals(orderLine.getId())).findFirst().orElseThrow(() -> new ResourceNotFoundException(""));
            oldOrderLine.setCount(oldOrderLine.getCount() + 1);
        }catch (ResourceNotFoundException e){
            orderLineList.add(orderLine);
        }
        return orderLineList.stream().filter(ol -> ol.getId().equals(orderLine.getId())).findFirst().orElseThrow(() -> new CartException("Ошибка при djpdhfotyb"));
    }

/*
    public OrderLine add(OrderLine orderLine){
        OrderLine oldOrderLine = orderLineList.stream().filter(ol -> ol.getId().equals(orderLine.getId())).findFirst().orElseThrow(() -> new CartException("Ошибка при добавлении продукта в корзину"));
        if(Objects.equals(oldOrderLine.getId(), orderLine.getId())){
            oldOrderLine.setCount(oldOrderLine.getCount() + 1);
        }
        else {
            orderLineList.add(orderLine);
        }
        return orderLineList.stream().filter(ol -> ol.getId().equals(orderLine.getId())).findFirst().orElseThrow(() -> new CartException("Ошибка при добавлении продукта в корзину"));
    }
*/

}
