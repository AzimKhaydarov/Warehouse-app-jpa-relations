package com.example.warehouseappjparelations.repository;

import com.example.warehouseappjparelations.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByFirstNameAndLastNameAndPhoneNumber( String first_name, String last_name, String phone_number);
    boolean existsByPhoneNumber(String phone_number);
}
