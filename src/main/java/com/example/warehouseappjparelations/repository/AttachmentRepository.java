package com.example.warehouseappjparelations.repository;

import com.example.warehouseappjparelations.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  AttachmentRepository extends JpaRepository<Attachment, Integer> {

}
