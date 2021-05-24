package com.example.warehouseappjparelations.entity;

import com.example.warehouseappjparelations.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Category extends AbstractEntity {


    @ManyToOne
    private Category parentCategory;



}
