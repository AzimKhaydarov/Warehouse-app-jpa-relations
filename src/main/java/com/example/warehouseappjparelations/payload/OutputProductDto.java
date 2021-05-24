package com.example.warehouseappjparelations.payload;

import lombok.Data;

@Data
public class OutputProductDto {
    private Integer productId;
    private Double amount, price;
    private Integer outputId;

}
