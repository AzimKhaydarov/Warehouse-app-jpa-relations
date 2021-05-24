package com.example.warehouseappjparelations.payload;

import com.example.warehouseappjparelations.entity.Warehouse;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Set;

@Data
public class UserDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String code;
    private String password;
    private boolean active;

    private Set<Integer> warehouseList;
}
