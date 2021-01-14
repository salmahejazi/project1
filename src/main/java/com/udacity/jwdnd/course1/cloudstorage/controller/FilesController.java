package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Controller
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
            fileService.addFile(new Files(null, fileUpload.getOriginalFilename(), fileUpload.getContentType(), fileUpload.getSize(), userId, fileUpload.getBytes()));
            return "redirect:/home";
        }

        @GetMapping("/files/{fileId}")
        public String deleteFile(@PathVariable(value = "fileId") Integer fileId, Model model) {
            fileService.deleteFile(fileId);
            return "redirect:/home";
        }
    }