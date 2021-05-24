package com.example.warehouseappjparelations.payload;

import lombok.Data;

@Data
public class ProductDto {
   private String name;
   private Integer categoryId;
   private Integer photoId;
   private Integer code;
   private Integer measurementId;
   private boolean status;
}
