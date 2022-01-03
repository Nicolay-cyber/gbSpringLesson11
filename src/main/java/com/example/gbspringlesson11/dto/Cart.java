package com.example.gbspringlesson11.dto;

import com.example.gbspringlesson11.entities.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {
    private List<OrderLineDto> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addProduct(Product product) {
        if (addProduct(product.getId())) {
            return;
        }
        items.add(new OrderLineDto(product));
        recalculate();
    }

    public void changeCount(Long id, int delta){
        Iterator<OrderLineDto> iter = items.iterator();
        while (iter.hasNext()) {
            OrderLineDto o = iter.next();
            if (o.getId().equals(id)) {
                o.changeQuantity(delta);
                if (o.getCount() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }    }

    public boolean addProduct(Long id) {
        for (OrderLineDto o : items) {
            if (o.getId().equals(id)) {
                o.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void removeProduct(Long id) {
        items.removeIf(o -> o.getId().equals(id));
        recalculate();
    }

    public void clear() {
        items.clear();
        totalPrice = 0;
    }

    private void recalculate() {
        totalPrice = 0;
        for (OrderLineDto o : items) {
            totalPrice += o.getTotalCost();
        }
    }
}
