package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;


    public NoteController(NoteService noteService, UserService userService){
        this.noteService = noteService;
        this.userService = userService;
    }



    @GetMapping(value= "/getNote/{noteId}")
        @ResponseBody
        public Note getNote(@PathVariable(name = "noteId") String noteID) {
            Integer noteId = Integer.parseInt(noteID);
            return noteService.getNotes(noteId);
        }

    @PostMapping("/add")
    public String createNote(Note note, Authentication authentication, Model model) {
        Integer userId = userService.getUser(authentication.getName()).getUserId();
        note.setUserId(userId);
        Integer rowAffected = noteService.addNote(note);
        if (rowAffected < 1) model.addAttribute("success", false);
        else model.addAttribute("success", true);
        return "redirect:/home";
    }
}
