package com.example.warehouseappjparelations.entity;

import com.example.warehouseappjparelations.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Supplier extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String phoneNumber;

}
