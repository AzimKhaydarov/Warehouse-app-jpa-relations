package com.example.warehouseappjparelations.repository;

import com.example.warehouseappjparelations.entity.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository <AttachmentContent, Integer>{

    Optional<AttachmentContent> findByAttachmentId(Integer id);
}
