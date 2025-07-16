package com.coffeehouse.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TopSellingItem {
    private String name;
    private int totalSold;
}