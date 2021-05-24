package com.example.warehouseappjparelations.controller;

import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;
    @PostMapping("/upload")
    public Result uploadPhoto(MultipartHttpServletRequest request) throws IOException {
        Result result = attachmentService.uploadPhoto(request);
        return result;
    }
    @GetMapping("/download/{id}")
    public Result download(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        return attachmentService.getFile(id,response);
    }
}
