package com.example.warehouseappjparelations.payload;

import com.example.warehouseappjparelations.entity.template.AbstractEntity;
import lombok.Data;


@Data
public class CategoryDto {
  private String name;
  private Integer parentCategoryId;
  private boolean active;

}
