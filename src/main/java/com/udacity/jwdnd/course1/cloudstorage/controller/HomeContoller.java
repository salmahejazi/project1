package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/home")
public class HomeContoller {

    @Autowired
    private FileService FileService;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private NoteService noteService;



    public HomeContoller(FileService FileService) {
        this.FileService = FileService;
    }

    @RequestMapping("/home")
    public String getHomePage(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         System.out.println(auth);
        model.addAttribute("pageFiles", this.FileService.getAllFiles());
       // model.addAttribute("pageCredentials", this.credentialService.getAllCredentials(11));
       //model.addAttribute("pageNotes", this.noteService.getAllNotes(11));
        return "home";
    }

  /*  @GetMapping
    public String getFiles(Authentication Authentication, Model model) {
        Integer UID = UserService.getUser(Authentication.getName()) ;
        model.addAttribute("Files", this.FileService.getAllFiles(UID));
//files.getBytes()
        return "home";
    }

   @PostMapping
    public String postChatMessage(Authentication authentication, ChatForm chatForm, Model model) {
        chatForm.setUsername(authentication.getName());
        this.messageService.addMessage(chatForm);
        chatForm.setMessageText("");
        model.addAttribute("chatMessages", this.messageService.getChatMessages());
        return "chat";
    }*/





}