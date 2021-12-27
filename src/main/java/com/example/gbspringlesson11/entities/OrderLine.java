package com.example.gbspringlesson11.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLine {
    private Long id;
    private String title;
    private Integer cost;
    private Integer count;
}
