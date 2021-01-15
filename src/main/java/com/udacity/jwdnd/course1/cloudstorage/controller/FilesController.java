package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Controller
@RequestMapping("/file")
public class FilesController {
private FileService fileService;

        @Autowired
        private UserService userService;

        public FilesController(FileService fileService) {
            this.fileService = fileService;
        }

        @PostMapping("/files")
        public String postNewFile(@RequestParam("fileUpload") MultipartFile fileUpload, Model model, Authentication authentication) throws IOException {
            int userId = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()).getUserId();
            fileService.addFile(new File(null, fileUpload.getOriginalFilename(), fileUpload.getContentType(), fileUpload.getSize(), userId, fileUpload.getBytes()));
            return "redirect:/home";
        }

        @GetMapping("/files/{fileId}")
        public String deleteFile(@PathVariable(value = "fileId") Integer fileId, Model model) {
            fileService.deleteFile(fileId);
            return "redirect:/home";
        }

    @GetMapping("/download/{id}")
    @ResponseBody
    public ResponseEntity<?> getFileById(@PathVariable(name = "id") String idS, org.springframework.security.core.Authentication authentication) {
        Integer userId = userService.getUser(authentication.getName()).getUserId();
        Integer id = Integer.valueOf(idS);
        File file = fileService.getFile(id, userId);
        ByteArrayResource resource = new ByteArrayResource(file.getFileData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFilename())
                .contentType(MediaType.valueOf(file.getContentType()))
                .contentLength( file.getFileSize() )
                .body(resource);
    }

    @GetMapping("/delete")
    public String deleteFile(@RequestParam("id") String idS, Model model) {
        Integer id = Integer.valueOf(idS);
        Integer rawAffected = fileService.deleteFile(id);
        if (rawAffected < 1) model.addAttribute("success", false);
        else model.addAttribute("success", true);
        return "result";
    }
    }