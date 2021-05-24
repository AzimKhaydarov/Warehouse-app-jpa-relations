package com.example.warehouseappjparelations.payload;

import lombok.Data;

import java.util.Date;

@Data
public class InputProductDto {
    private Integer productId;
    private Double amount, price;
    private Date expireDate;
    private Integer inputId;

}
