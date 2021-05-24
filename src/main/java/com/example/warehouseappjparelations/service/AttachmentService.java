package com.example.warehouseappjparelations.service;

import com.example.warehouseappjparelations.entity.Attachment;
import com.example.warehouseappjparelations.entity.AttachmentContent;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.repository.AttachmentContentRepository;
import com.example.warehouseappjparelations.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public Result uploadPhoto(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> photoNames = request.getFileNames();
        MultipartFile file = request.getFile(photoNames.next());
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);
        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setAttachment(savedAttachment);
        attachmentContent.setBytes(file.getBytes());
        attachmentContentRepository.save(attachmentContent);
        return new Result("Photo added successfully!", true, savedAttachment.getId());

    }
    public Result getFile(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> byId = attachmentRepository.findById(id);
        if (byId.isPresent()) {
            Attachment attachment = byId.get();
            Optional<AttachmentContent> byAttachmentId = attachmentContentRepository.findByAttachmentId(id);
            if (byAttachmentId.isPresent()) {
                AttachmentContent attachmentContent = byAttachmentId.get();
                response.setHeader("Content-Disposition", "" +
                        "attachment;filename=\"" + attachment.getName() + "\"");
                response.setContentType(attachment.getContentType());
                FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
            }else {
                return new Result("not found",false);
            }
        }
        return new Result("download successfully",true);
    }
}
