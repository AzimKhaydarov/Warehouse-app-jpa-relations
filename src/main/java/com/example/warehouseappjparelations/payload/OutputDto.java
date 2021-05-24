package com.example.warehouseappjparelations.payload;

import lombok.Data;

import java.util.Date;

@Data
public class OutputDto {
    private Date date;
    private Integer warehouseId, clientId, currencyId;
    private String code,invoiceNumber;
}
