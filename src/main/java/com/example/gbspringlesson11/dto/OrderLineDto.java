package com.example.gbspringlesson11.dto;

import com.example.gbspringlesson11.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineDto {
    private Long id;
    private String title;
    private int cost;
    private int count;
    private int totalCost;

    public OrderLineDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.count = 1;
        this.cost = product.getCost();
        this.totalCost = product.getCost();
    }

    public void changeQuantity(int delta) {
        this.count += delta;
        this.totalCost = this.count * this.cost;
    }
}
