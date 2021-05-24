package com.example.warehouseappjparelations.payload;

import lombok.Data;

import java.util.Date;

@Data
public class InputDto {
    private Date date;
    private Integer warehouseId, supplierId, currencyId;
    private String code,invoiceNumber;
}
